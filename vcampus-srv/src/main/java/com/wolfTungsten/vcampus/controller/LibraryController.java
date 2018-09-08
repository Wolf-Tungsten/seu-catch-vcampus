package com.wolfTungsten.vcampus.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampus.entity.Book;
import com.wolfTungsten.vcampus.entity.Token;
import com.wolfTungsten.vcampus.entity.UserXBook;
import com.wolfTungsten.vcampus.repository.UserXBookRepository;
import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;

public class LibraryController extends BaseController
{
	public LibraryController()
	{
		super();
		this.addHandle("addBook", addBookHandle);
		this.addHandle("updateBook", updateBookHandle);
		this.addHandle("deleteBook", deleteBookHandle);
		this.addHandle("queryAllBooks", queryAllHandle);
		this.addHandle("queryByName", queryByNameHandle);
		this.addHandle("queryByAuthor", queryByAuthorHandle);
		this.addHandle("borrowBook", borrowBookHandle);
		this.addHandle("returnBook", returnBookHandle);
		this.addHandle("renewBook", renewBookHandle);
		this.addHandle("borrowRecord", borrowRecordHandle);
		this.addHandle("queryByFlag", queryByFlagHandle);
	}

	private BaseController.BaseHandle addBookHandle = new BaseHandle()
	{

		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String name = (String) request.getParams().get(Book.NAME);
			String isbn = (String) request.getParams().get(Book.ISBN);
			String author = (String) request.getParams().get(Book.AUTHOR);
			String publisher = (String) request.getParams().get(Book.PUBLISHER);
			long createTime = System.currentTimeMillis() / 1000;
			long updateTime = createTime;
			String location = (String)request.getParams().get(Book.LOCATION);
			 String token = request.getToken();

			try
			{
				// 检查token
				 checkToken(token);
				orm.bookRepository.addBook(name, isbn, author, publisher, location,createTime, updateTime);
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				e.printStackTrace();
				System.out.println("添加书籍失败");
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				return response;

			}

		}
	};
	// 已测试
	private BaseController.BaseHandle deleteBookHandle = new BaseHandle()
	{
		/**
		 * 根据书本的uuid删除
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			ArrayList<String> bookUuidList = (ArrayList<String>) request.getParams().get("uuidList");
			// String token = (String) request.getParams().get(Token.TOKEN);

			for (String uuid : bookUuidList)
			{
				try
				{
					orm.bookRepository.deleteBookByUuid(uuid);
					orm.userXBookRepository.deleteUserXBook(uuid);
				} catch (SQLException e)
				{
					response.setSuccess(false);
					e.printStackTrace();
				}
			}
			response.setSuccess(true);
			return response;
		}
	};
	private BaseController.BaseHandle updateBookHandle = new BaseHandle()
	{

		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			ArrayList<LinkedTreeMap<String, Object>> booksMap = (ArrayList<LinkedTreeMap<String, Object>>) request
					.getParams().get("booksList");
			String token = (String) request.getParams().get("token");
			try
			{
				checkToken(token);
				for (int i = 0; i < booksMap.size(); i++)
				{

					orm.bookRepository.updateBook(booksMap.get(i));

				}
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				response.setSuccess(false);
				// response.getBody().put("Cause by: ", e.getMessage());
				e.printStackTrace();
				return response;
			}
			
		}
	};
	
	private BaseController.BaseHandle queryAllHandle = new BaseHandle()

	{

		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = (String) request.getParams().get("token");
			ArrayList<HashMap<String, Object>> booksinfoList = new ArrayList<>();
			try
			{
				checkToken(token);
				booksinfoList = orm.bookRepository.inquireAllBook();
				for(HashMap<String,Object> bookinfo:booksinfoList) {
					boolean isreturn = orm.userXBookRepository.checkBorrow((String)bookinfo.get(Book.UUID));
					bookinfo.put(UserXBook.ISRETURN, isreturn==true?true:false);		
				}
				response.getBody().put("booksInfoMapList", booksinfoList);
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				response.setSuccess(false);
				e.printStackTrace();
				return response;
			}
		}
	};
	private BaseController.BaseHandle queryByFlagHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response1 =  queryByNameHandle.work(request);
			Response response2 = queryByAuthorHandle.work(request);
			if(response1.getSuccess())
				return response1;
			else
				return response2;
		
			
		}
	};

	private BaseController.BaseHandle queryByNameHandle = new BaseHandle()
	{
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			ArrayList<HashMap<String, Object>> booksinfoList = new ArrayList<>();
			String name = (String) request.getParams().get(Book.NAME);
			
			String token = request.getToken();
			try
			{
				checkToken(token);
				booksinfoList = orm.bookRepository.inquireByFlag(Book.NAME, name);
				for(HashMap<String,Object> bookinfo:booksinfoList) {
					boolean isreturn = orm.userXBookRepository.checkBorrow((String)bookinfo.get(Book.UUID));
					bookinfo.put(UserXBook.ISRETURN, isreturn==true?true:false);		
				}
				response.getBody().put("booksInfoMapList", booksinfoList);
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				response.setSuccess(false);
				e.printStackTrace();
				return response;
			}
		}
	};

	private BaseController.BaseHandle queryByAuthorHandle = new BaseHandle()
	{

		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			ArrayList<HashMap<String, Object>> booksinfoList = new ArrayList<>();
			String author = (String) request.getParams().get(Book.AUTHOR);

			try
			{
				booksinfoList = orm.bookRepository.inquireByFlag(Book.AUTHOR, author);
				for(HashMap<String,Object> bookinfo:booksinfoList) {
					boolean isreturn = orm.userXBookRepository.checkBorrow((String)bookinfo.get(Book.UUID));
					bookinfo.put(UserXBook.ISRETURN, isreturn==true?true:false);		
				}
				response.getBody().put("booksInfoMapList", booksinfoList);
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				e.printStackTrace();
				response.setSuccess(false);
				return response;

			}

		}
	};
	/**
	 * 借书
	 */
	
	//重写
	private BaseController.BaseHandle borrowBookHandle = new BaseHandle()
	{

		@Override
		public Response work(Request request)
		{
			Response response = new Response();

			String bookuuid = (String) request.getParams().get(Book.UUID);
			String token = request.getToken();

			int isReturn = 0;
			try
			{
				String useruuid = checkToken(token);
				// 找到这本书
	
				LinkedTreeMap<String, Object> booksinfo = new LinkedTreeMap<>();
				booksinfo.put(Book.UUID, bookuuid);				
				orm.userXBookRepository.addUserXBook(useruuid, bookuuid, isReturn, System.currentTimeMillis() / 1000);
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			} catch (Exception e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}

		}
	};
	// 已测试
	/**
	 * 还书,前端借阅记录的uuid即可,还有token
	 */
	private BaseController.BaseHandle returnBookHandle = new BaseHandle()
	{

		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String uxbuuid = (String) request.getParams().get(UserXBook.UUID);
			String token = request.getToken();
			try
			{
				checkToken(token);
				orm.userXBookRepository.updateUserXBook(uxbuuid, UserXBook.ISRETURN, 1);
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", "还书失败");
				e.printStackTrace();
				return response;

			}

		}
	};
	/**
	 * 续借,前端传借阅记录的uuid
	 */
	// 已测试
	private BaseController.BaseHandle renewBookHandle = new BaseHandle()
	{

		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String uuid = (String) request.getParams().get(UserXBook.UUID);
			String token = (String) request.getParams().get("token");
			try
			{
				checkToken(token);
				long newReturndate = orm.userXBookRepository.checkRenew(uuid);
				orm.userXBookRepository.updateUserXBook(uuid, UserXBook.RETURNDATE, newReturndate);
				response.setSuccess(true);
				return response;
			} catch (Exception e)
			{
				response.getBody().put("result", e.getMessage());
				response.setSuccess(false);
				e.printStackTrace();
				return response;
			}

		}
	};
	/**
	 * 返回用户借阅记录 前端传token
	 */
	// 已测试
	private BaseController.BaseHandle borrowRecordHandle = new BaseHandle()
	{

		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			// 拿user_id
			try
			{
				String user_id = checkToken(token);
				ArrayList<HashMap<String, Object>> recordMaplist = orm.userXBookRepository
						.inquireByFlag(UserXBook.USER_ID, user_id);
				for (HashMap<String, Object> record : recordMaplist)
				{
					// -- 把借阅记录里的book_id查特么是哪本书然后把书的信息put给map
					Book book = orm.bookRepository.inquireById((String) record.get(UserXBook.BOOK_ID));
					record.remove(UserXBook.BOOK_ID);
					record.put(Book.AUTHOR, book.getAuthor());
					record.put(Book.NAME, book.getName());
					record.put(Book.PUBLISHER, book.getPublisher());
				}
				response.getBody().put("recordMaplist", recordMaplist);
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				response.setSuccess(false);
				e.printStackTrace();
				return response;
			}

		}
	};

}