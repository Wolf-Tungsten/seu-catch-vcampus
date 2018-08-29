package orm.Utils;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import orm.annotation.Column;
import orm.annotation.ID;
import orm.annotation.Table;

public class JdbcUtils<E>
{
	// 定义数据库驱动类的名称
	protected static String dbClassName = "com.hxtt.sql.access.AccessDriver";

	// 定义访问数据库的URL

	protected static String dbUrl = "jdbc:Access:///C:/Users/hdong/Desktop/solftP/vCampus.mdb";
	// 定义访问数据库的用户名
	protected static String dbUser;
	// 定义访问数据库的密码
	protected static String dbPwd;
	// private static Connection conn = null;

	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			if (conn == null)
			{
				Class.forName(dbClassName);
				conn = DriverManager.getConnection(dbUrl);
				System.out.println("Access数据库连接成功");
			}

		} catch (Exception ee)
		{
			ee.printStackTrace();

		}
		return conn;
	}

	/**
	 * 增删改
	 * 
	 */
	public static int excuteUpdate(String sql, Object[] params)
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		int result = -1;
		try
		{
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
			{
				pstmt.setObject(i + 1, params[i]); // 提供参数覆盖占位符
			}
			result = pstmt.executeUpdate(); // 执行sql语句

		} catch (SQLException ee)
		{
			System.out.println("更新出现异常");
			ee.printStackTrace();
			System.out.println(ee.getMessage());
		} finally
		{
			release(pstmt, connection);
		}

		return result;

	}

	public static void release(Statement stmt, Connection conn)
	{
		if (stmt != null)
		{
			try
			{
				stmt.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			stmt = null;
		}
		if (conn != null)
		{
			try
			{
				conn.close();
			} catch (SQLException ee)
			{
				ee.printStackTrace();
			}
			conn = null;
		}

	}

	/**
	 * 插入实体对象
	 * 
	 * @param element 实例化实体类
	 * @return
	 */
	public int add(E element)
	{
		if (element == null)
			throw new IllegalArgumentException("插入元素为空");
		//
		Class clazz = element.getClass();
		String tableName = getTableName(clazz);
		Field[] fields = clazz.getDeclaredFields();
		if (fields == null || fields.length == 0)
			throw new RuntimeException(element + "没有属性.");
		String sql = getInsertSql(tableName, fields.length);
		Object[] params = getSqlParams(element, fields);
		System.out.println("insertSql = " + sql);
		System.out.println(Arrays.toString(params));

		return JdbcUtils.excuteUpdate(sql, params);

	}

	/**
	 * 根据对象获取sql语句中占位符的参数
	 * 
	 * @param element
	 * @param fields
	 * @return sql语句的参数
	 */

	private Object[] getSqlParams(E element, Field[] fields)
	{
		Object[] params = new Object[fields.length];
		for (int i = 0; i < fields.length; i++)
		{
			fields[i].setAccessible(true);
			try
			{
				params[i] = fields[i].get(element);
			} catch (IllegalAccessException ee)
			{
				System.out.println(ee.getMessage());
				System.out.println("获取 " + element + "的属性值失败");
			}
		}

		return params;

	}

	/**
	 * 插入对象的sql语句
	 * 
	 * @param tableName 数据表名称
	 * @param length    字段个数吧
	 * @return 插入记录的sql 语句
	 */

	private String getInsertSql(String tableName, int length)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ").append(tableName).append(" values(");
		for (int i = 0; i < length; i++)
		{
			sql.append("?,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		return sql.toString();
	}

	/**
	 * 根据对象注解获取表名称
	 * 
	 * @param clazz ....
	 * @return
	 */
	private String getTableName(Class<E> clazz)
	{
		boolean existTableAnno = clazz.isAnnotationPresent(Table.class);

		if (!existTableAnno)
			throw new RuntimeException(clazz + "没有Table注解");
		Table tableAnno = (Table) clazz.getAnnotation(Table.class);
//		System.out.println(tableAnno.name());
		return tableAnno.name();

	}

	/**
	 * 更新一个对象
	 * 
	 * @param element 待更新对象
	 * @return 更新成功:1 失败:0
	 */
	public int update(E element)
	{
		if (element == null)
			throw new IllegalArgumentException("插入元素为空");
		//
		Class clazz = element.getClass();
		Field[] fields = clazz.getDeclaredFields();
		if (fields == null || fields.length == 0)
			throw new RuntimeException(element + "没有属性.");
		Object[] params = new Object[fields.length];
		String sql = getUpdateSqlAndParams(element, params);
		System.out.println("update sql = " + sql);
		System.out.println("params = " + Arrays.toString(params));
		return JdbcUtils.excuteUpdate(sql, params);

	}

	/**
	 * 获取更新语句sql 以及句中参数
	 * 
	 * @param element
	 * @param params
	 * @return
	 */
	public String getUpdateSqlAndParams(E element, Object[] params)
	{
		Class clazz = element.getClass();
		String tableName = getTableName(clazz);
		Field[] fields = clazz.getDeclaredFields();

		StringBuilder updateSql = new StringBuilder();
		updateSql.append("update ").append(tableName).append(" set");
		String idName = "";
		int index = 0;// 记录参数位置
		for (int i = 0; i < fields.length; i++)
		{
			fields[i].setAccessible(true);
			// 找到id对应的列名和值
			if (fields[i].isAnnotationPresent(ID.class))
			{
				idName = fields[0].getAnnotation(ID.class).name();
				try
				{
					params[params.length - 1] = fields[i].get(element);
					if (params[params.length - 1] == null)
					{
						throw new RuntimeException(element + "没有Id属性!");
					}
				} catch (IllegalAccessException e)
				{
					System.out.println(e.getMessage());
					System.out.println("获取" + element + "的属性值失败");
				}
			}
			boolean isPresent = fields[i].isAnnotationPresent(Column.class);
			if (isPresent)
			{
				Column column = fields[i].getAnnotation(Column.class);
				String columnName = column.name();
				updateSql.append(" ").append(columnName).append(" = ? ,");
				// update sql 的参数
				try
				{
					params[index++] = fields[i].get(element);// 添加参数到数组
				} catch (IllegalAccessException e)
				{
					System.out.println(e.getMessage());
					System.out.println("获取 " + element + "的属性值失败!");
				}
			}
		}
		updateSql.deleteCharAt(updateSql.length() - 1);
		updateSql.append("where ").append(idName).append(" = ? ");

		return updateSql.toString();
	}

	/**
	 * 根据id删除一条数据
	 * 
	 * @param id      要删除的Id
	 * @param element 要删除的类型
	 * @return
	 */
	/**
	 * 执行sql查询语句 返回ArrayList
	 * 
	 * @param sql
	 * @param params
	 * @param element
	 * @return
	 */
	public ArrayList<Object> excuteQuery(String sql, Object[] params, E element)
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		ArrayList<Object> list = new ArrayList<>();
		try
		{
			connection = getConnection();
			// sql = "select * from tb_Book where name = ?";
			pstmt = connection.prepareStatement(sql);
			// pstmt.setObject(1, "编译原理");
			if (params != null)
			{
				for (int i = 0; i < params.length; i++)
				{
					pstmt.setObject(i + 1, params[i]); // 提供参数覆盖占位符
				}
			}
			res = pstmt.executeQuery(); // 执行sql语句

			return rebuild(element, res);
		} catch (Exception ee)
		{
			System.out.println("查找出现异常");
			ee.printStackTrace();
			System.out.println(ee.getMessage());
		} catch (Throwable t)
		{
			t.printStackTrace();
		} finally
		{
			release(pstmt, connection);
		}
		if (res == null)
			System.out.println("result为空");
		return null;

	}

	/**
	 * 根据字段 删除 数据
	 * 
	 * @param id
	 * @param element
	 * @return
	 */
	public int delete(E element, String flag, E value)
	{
		if (element == null)
		{
			throw new IllegalArgumentException("删除对象类型为空");
		}
		Class clazz = element.getClass();
		String tablename = getTableName(clazz);
		Field[] fields = clazz.getDeclaredFields();
		String columnName = "";
		fields[0].setAccessible(true);
		String idName = fields[0].getAnnotation(ID.class).name();// 拿到id注解
		if (idName.equals(flag))
		{
			columnName = idName;
			try
			{
				if (fields[0].get(element) == null)
				{
					throw new RuntimeException(element + "没有Id属性!");
				}
			} catch (IllegalArgumentException e)
			{
				
				e.printStackTrace();
			} catch (IllegalAccessException e)
			{
				
				e.printStackTrace();
			}
		} else
		{
			for (int i = 1; i < fields.length; i++)
			{

				fields[i].setAccessible(true);
				columnName = fields[i].getAnnotation(Column.class).name();
				if (columnName.equals(flag))
					break;
			}
		}

		StringBuilder delSql = new StringBuilder();
		delSql.append("delete from ").append(tablename).append(" where ").append(columnName).append(" = ? ");
		Object[] params = new Object[] { value };
		System.out.println("delsql = " + delSql);
		System.out.println("params = " + Arrays.toString(params));
		return JdbcUtils.excuteUpdate(delSql.toString(), params);
	}

	/**
	 * 返回表中所有数据
	 * 
	 * @param element
	 * @return
	 */

	public ArrayList<Object> selectAll(E element)
	{
		Class clazz = element.getClass();
		String tablename = getTableName(clazz);
		StringBuilder selectAllsql = new StringBuilder();
		selectAllsql.append("select * from " + tablename);
		Object[] params = new Object[] { tablename };
		System.out.println("selectAllsql =" + selectAllsql.toString());
		System.out.println("params = " + Arrays.toString(params));
		return excuteQuery(selectAllsql.toString(), null, element);
//		Connection connection = getConnection();
//		PreparedStatement pstmt = null;
//		try
//		{
//			pstmt = connection.prepareStatement(selectAllsql.toString());
//			pstmt.setObject(1, tablename);
//			ResultSet res = pstmt.executeQuery();
//			
//			return res;
//		} catch (Exception e)
//		{
//			System.out.println("返回表中所有数据失败");
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//
//		} finally
//		{
//			// release(pstmt,connection);
//		}
//		return null;

	}

	/**
	 * 根据id 和类型获取单个实体数据
	 * 
	 * @param id
	 * @param element
	 * @return
	 */
	public ResultSet selectOneById(int id, E element)
	{
		Class clazz = element.getClass();
		Field[] fields = clazz.getDeclaredFields();
		String tablename = getTableName(clazz);

		String idName = fields[0].getAnnotation(ID.class).name();// 拿到id注解
		fields[0].setAccessible(true);
		String sql = "select * from " + tablename + " where _id= ?";
		Object[] params = new Object[] { id };

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try
		{
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setObject(1, params[0]);
			System.out.println("查找一个实体sql:" + sql);
			System.out.println("params = " + Arrays.toString(params));
			res = pstmt.executeQuery();
			return res;

		} catch (Exception e)
		{
			System.out.println("查找单个实体异常");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally
		{

			// release(pstmt,connection);
		}
		return null;
	}

	/**
	 * 根据返回实体数据表中某字段值相同的数据集，筛。
	 * 
	 * @param elment
	 * @param ColunmName
	 * @param parmas
	 * @return
	 */
	public ArrayList<Object> findByFlag(E element, String ColunmName, E parmas)
	{
		Class clazz = element.getClass();
		String tableName = getTableName(clazz);
		String sql = "select * from " + tableName + " where " + ColunmName + " = ?";

		Object[] parmass = new Object[] { parmas };
		System.out.println("findByFlag sql =" + sql);
		System.out.println("parmass[] = " + Arrays.toString(parmass));
		return excuteQuery(sql, parmass, element);

//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		ResultSet res = null;
//		
//		connection = getConnection();
//		try
//		{
//			System.out.println("查找一个实体sql:" + sql);
//			System.out.println("params = " + parmas.toString());
//			pstmt = connection.prepareStatement(sql);
//			pstmt.setObject(1, parmas.toString());
//			
//			
//			res = pstmt.executeQuery();
//			return res;
//		} catch (SQLException e)
//		{
//			System.out.println("查找某字段值相同实体集数据异常");
//			e.printStackTrace();
//		}finally {
//			
//		}
//		
//		
//		return null;
	}
/**
 * 根据表结构重构对象封装进ArrayList
 * @param element
 * @param res
 * @return
 */
	private ArrayList<Object> rebuild(E element, ResultSet res)
	{
		ArrayList<Object> list = new ArrayList<>();
		Class clz = element.getClass();
		try
		{
			// 得到传来的bean的所有方法
			Method[] methods = clz.getMethods();
			// 遍历结果集
			while (res.next())
			{
				ResultSetMetaData rsmd = res.getMetaData();// 查询结果元数据信息
				int count = rsmd.getColumnCount();// 查询结果列数
				// 得到bean的实例对象
				Object object = clz.newInstance();

				// 遍历结果集的列
				for (int i = 1; i <= count; i++)
				{
					String columnlLabel = rsmd.getColumnLabel(i);
					String methodName = "set" + columnlLabel;
					// 得到别名后 可以得到这个列的bean的set方法
					for (Method m : methods)
					{
						if (methodName.equals(m.getName()))
						{
							m.invoke(object, res.getObject(columnlLabel));
							break;
						}
					}
				}
				list.add(object);
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public static void main(String[] args)
	{

//    	JdbcUtils jdbu = new JdbcUtils();
//    	ResultSet res  = jdbu.findSameByst(new Book(), "name", "编译原理");
//    	try
//		{
//			while(res.next()) {
//				System.out.print("_id :" + res.getString("_id"));
//				System.out.print(" name :" + res.getString("name"));
//				System.out.print(" amount :" + res.getString("amount"));
//				System.out.print(" isbn :" + res.getString("isbn"));
//				System.out.print(" creatTime :" + res.getString("createTime"));
//				System.out.println(" updateTime :" + res.getString("updateTime"));
//			}
//		}catch(Exception ee) {
//			ee.printStackTrace();
//		}

	}

}
