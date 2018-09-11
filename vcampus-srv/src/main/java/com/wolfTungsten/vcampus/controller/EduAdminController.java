package com.wolfTungsten.vcampus.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampus.entity.Course;
import com.wolfTungsten.vcampus.entity.User;
import com.wolfTungsten.vcampus.entity.UserXCourse;
import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;

public class EduAdminController extends BaseController
{
	
	public EduAdminController() {
		super();
		this.pathMap.put("addCourse", addCourseHandle);
		this.pathMap.put("queryAllCourse", queryAllCourse);
		this.pathMap.put("deleteCourse", deleteCourse);
		this.pathMap.put("updateCourse", updateCourse);
		this.pathMap.put("selCourse", selCourseHandle);
		this.pathMap.put("schedule", scheduleHandle);
		this.pathMap.put("dropCourse", dropCourseHandle);
		this.pathMap.put("studentlist", studentlistHandle);
		this.pathMap.put("queryByName", queryByNameHandle);
		
		//this.pathMap.put("mark", value)
	}
	//已测试
	private BaseController.BaseHandle addCourseHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String name = (String)request.getParams().get(Course.NAME);
			int capacity = (int)(double)request.getParams().get(Course.CAPCITY);
			String lecturer = (String)request.getParams().get(Course.LECTURER);
			String week = (String)request.getParams().get(Course.WEEK);
			String classtime =(String)request.getParams().get(Course.CLASSTIME);
			String token = (String)request.getToken();
			String location =(String)request.getParams().get(Course.LOCATION);
			int credits = (int)(double)request.getParams().get(Course.CREDITS);
			try
			{
				checkToken(token);
				orm.courseRepository.addCourse(name, capacity, lecturer,week
						,classtime,location,credits);
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}		
		}
	};
	//已测试
	//前端传 token 
	private BaseController.BaseHandle queryAllCourse = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = (String)request.getToken();
			try
			{
				String userid = checkToken(token);
				ArrayList<HashMap<String,Object>> courseMaplist = 
				orm.courseRepository.queryAllCourses();
				User user = orm.userRepository.inquireById(userid);
				response.setSuccess(true);
				response.getBody().put("courseMaplist", courseMaplist);
				response.getBody().put(User.USERNAME, user.getUsername());
				response.getBody().put(User.CARDNUM, user.getCardnum());
				return response;
				
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		}
	};
	//未测试
	/**
	 * 搜索课程信息
	 */
	private BaseController.BaseHandle queryByNameHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String coursename = (String)request.getParams().get(Course.NAME);
			String token = request.getToken();
			
			
			try
			{
				checkToken(token);
				ArrayList<HashMap<String,Object>> courseMaplist =
						orm.courseRepository.queryByFlag(Course.NAME, coursename);
				response.setSuccess(true);
				response.getBody().put("courseMaplist", courseMaplist);
				return response;
				
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}

		}
	};
	
	//删除课程 管理端功能
	//已测试
	//前端传token 课程的uuid 
	private BaseController.BaseHandle deleteCourse = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = (String)request.getToken();
			String courseuuid = (String)request.getParams().get(Course.UUID);
			
			try
			{
				checkToken(token);
				orm.courseRepository.deleteCourse(courseuuid);
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		}
	};
	
    //更改课程信息 管理端功能
	//已测试
	//前端传token  ArrayList<LinkedTreeMap<String, Object>> courseMaplist
	private BaseController.BaseHandle updateCourse = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			ArrayList<LinkedTreeMap<String, Object>> courseMaplist =
					(ArrayList<LinkedTreeMap<String, Object>>) request.getParams().get("courseMaplist");
			String token = (String) request.getToken();
			
			try
			{
				checkToken(token);
				for(LinkedTreeMap<String, Object> courseMap:courseMaplist) {
					orm.courseRepository.updateBook(courseMap);				
				}
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
	//选课请求
	//前端传 token 和课程uuid 
	//已测试
	private BaseController.BaseHandle selCourseHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = (String)request.getToken();
			String courseuuid = (String)request.getParams().get(Course.UUID);
			
			try
			{
				String useruuid = checkToken(token);
				orm.userXCourseRepository.addUserXCourse(useruuid, courseuuid);
				
				
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
			
		}
	};
	//前端传token 和课程uuid 
	//退课
	private BaseController.BaseHandle dropCourseHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = (String)request.getToken();
			String courseuuid = (String)request.getParams().get(Course.UUID);
			
			try
			{
				String useuuid = checkToken(token);
				orm.userXCourseRepository.deleteUserXCourse(useuuid, courseuuid);
				response.setSuccess(true);
				return response;
				
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		
		}
	};
	//查学生课表
	//传token 
	//已测试
	private BaseController.BaseHandle scheduleHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = (String)request.getToken();			
			try
			{
				String useruuid = checkToken(token);
				//根据用户uuid 获取 userxcourse 信息hashmap链表
				ArrayList<HashMap<String, Object>> uxcList = 
						orm.userXCourseRepository.inquireByFlag(UserXCourse.USER_ID, useruuid);
				for(HashMap<String,Object> record : uxcList) {
					//根据关系的courseuuid 去查相应课程信息
					Course course = 
							orm.courseRepository.inquireById((String)record.get(UserXCourse.COURSE_ID));
					record.remove(UserXCourse.COURSE_ID);
					record.remove(UserXCourse.USER_ID);
					record.put(Course.NAME, course.getName());
					record.put(Course.LECTURER, course.getLecturer());
					record.put(Course.UUID, course.getUuid().toString());
					record.put(Course.CLASSTIME, course.getClasstime());
					record.put(Course.WEEK, course.getWeek());
				}
				response.getBody().put("recordMaplist", uxcList);
				response.setSuccess(true);
				return response;
			
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
			
		}
	};
	//已测试
	//学生名单
	private BaseController.BaseHandle studentlistHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
		
			
			String teachername = (String)request.getParams().get(Course.LECTURER);
			String courseName = (String)request.getParams().get(Course.NAME);
			
			try
			{
				checkToken(token);
				Course course = orm.courseRepository.inquireByFlags(teachername, courseName);
				ArrayList<HashMap<String, Object>> studentMaplist = new ArrayList<>();

				ArrayList<HashMap<String, Object>> coursemaplist = orm.userXCourseRepository
						.inquireByFlag(UserXCourse.COURSE_ID,course.getUuid());// 课程信息Map表
				for (HashMap<String, Object> record : coursemaplist)
				{
					HashMap<String, Object> student = new HashMap<>(); // 学生信息
					ArrayList<User> userlist = orm.userRepository
							.inquireByIds((String) record.get(UserXCourse.USER_ID));
					for (User user : userlist)
					{
						student.put(User.USERNAME, user.getUsername());
						student.put(User.CARDNUM, user.getCardnum());
					}
					studentMaplist.add(student);
				}
				response.getBody().put("studentMaplist", studentMaplist);
				response.setSuccess(true);
				return response;
		
				
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
			
		}
	};
	//前端传token 和课程 userXcourse的uuid
	private BaseController.BaseHandle markHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			String uuid = (String) request.getParams().get(UserXCourse.UUID);
			String score = (String) request.getParams().get(UserXCourse.SCORE);
			
			
			try
			{
				checkToken(token);
				orm.userXCourseRepository.updateUserXCourse(uuid, UserXCourse.SCORE, score);
				response.setSuccess(true);
				return response;
				
			} catch (SQLException e)
			{
				response.getBody().put("result", e.getMessage());
				response.setSuccess(false);
				e.printStackTrace();
				return response;
			}

		}
	};
	
	
}
