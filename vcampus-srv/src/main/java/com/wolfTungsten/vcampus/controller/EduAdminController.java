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
			String token = (String)request.getToken();
			try
			{
				checkToken(token);
				orm.courseRepository.addCourse(name, capacity, lecturer);
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
				checkToken(token);
				ArrayList<HashMap<String,Object>> courseMaplist = 
				orm.courseRepository.queryAllCourses();
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
	public BaseController.BaseHandle deleteCourse = new BaseHandle()
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
	public BaseController.BaseHandle updateCourse = new BaseHandle()
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
	//未测试
	public BaseController.BaseHandle selCourseHandle = new BaseHandle()
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
	public BaseController.BaseHandle dropCourseHandle = new BaseHandle()
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
	public BaseController.BaseHandle scheduleHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = (String)request.getToken();
			
			try
			{
				String useruuid = checkToken(token);
				ArrayList<HashMap<String, Object>> uxcList = 
						orm.userXCourseRepository.inquireByFlag(UserXCourse.USER_ID, useruuid);
				for(HashMap<String,Object> record : uxcList) {
					Course course = 
							orm.courseRepository.inquireById((String)record.get(UserXCourse.COURSE_ID));
					record.remove(UserXCourse.COURSE_ID);
					record.remove(UserXCourse.USER_ID);
					record.put(Course.NAME, course.getName());
					record.put(Course.LECTURER, course.getLecturer());
					record.put(Course.UUID, course.getUuid().toString());
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
	public BaseController.BaseHandle studentlistHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
		
			String courseuuid = (String)request.getParams().get(Course.UUID);
			String teachername = (String)request.getParams().get(Course.LECTURER);
			String courseName = (String)request.getParams().get(Course.NAME);
			
			try
			{
				checkToken(token);
				ArrayList<Course> courselist = orm.courseRepository.inquireByFlags(teachername, courseName);
				ArrayList<HashMap<String, Object>> studentMaplist = new ArrayList<>();
				for(Course course : courselist) {
					ArrayList<HashMap<String, Object>> coursemaplist 
					= orm.userXCourseRepository.inquireByFlag(Course.UUID, course.getUuid().toString());
					for(HashMap<String,Object> record:coursemaplist) {
						HashMap<String,Object> studentList = new HashMap<>();
						User user = orm.userRepository.inquireById((String)record.get(UserXCourse.USER_ID));
						
						
					}
					
				}
				
				
				
			} catch (SQLException e)
			{
				
				e.printStackTrace();
			}
			
			
			
			
			return null;
		}
	};
	
}
