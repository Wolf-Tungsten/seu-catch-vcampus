/**
 * Classname : EduAdminController.java
 * 
 * 
 * @author 袁皓东
 */
package com.wolfTungsten.vcampus.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampus.entity.Course;
import com.wolfTungsten.vcampus.entity.Exam;
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
		this.pathMap.put("deleteCourse", deleteCourseHandle);
		this.pathMap.put("updateCourse", updateCourse);
		this.pathMap.put("selCourse", selCourseHandle);
		this.pathMap.put("schedule", scheduleHandle);
		this.pathMap.put("dropCourse", dropCourseHandle);
		this.pathMap.put("studentlist", studentlistHandle);
		this.pathMap.put("queryByName", queryByNameHandle);
		this.pathMap.put("mark", markHandle);
	}
	//已测试//时间字符串 2018-8-16/9:00-12:00
	private BaseController.BaseHandle addCourseHandle = new BaseHandle()
	{
		/**
		 * 增加新的课程
		 */
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
			String startTime = (String)request.getParams().get(Exam.STARTTIME);
			String[] str = startTime.split("/");
			String date_str = str[0];
			String duration = str[1];
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			
			try
			{
				long startTimestamp = sdf.parse(date_str).getTime()/1000;
				checkToken(token);
				orm.courseRepository.addCourse(name, capacity, lecturer,week
						,classtime,location,credits);
				Course course =orm.courseRepository.inquireByFlags(lecturer, name);
				orm.examRepository.addExam(name, course.getUuid().toString(), 
						startTimestamp, duration, location);
				response.setSuccess(true);
				
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
	//已测试
	//前端传 token 
	private BaseController.BaseHandle queryAllCourse = new BaseHandle()
	{
		/**
		 * 查询所有的课程
		 *
		 */
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
				ArrayList<HashMap<String, Object>> slectedcourse
					=orm.userXCourseRepository.inquireByFlag(UserXCourse.USER_ID, userid);
				for(HashMap<String,Object> record : slectedcourse) {
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
					record.put(Course.CAPCITY, course.getCapcity());
				}		
				User user = orm.userRepository.inquireById(userid);		
				response.setSuccess(true);
				response.getBody().put("courseMaplist", courseMaplist);
				response.getBody().put("selectedCourse", slectedcourse);
				response.getBody().put(User.USERNAME, user.getUsername());
				response.getBody().put(User.CARDNUM, user.getCardnum());
				return response;			
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}
		}
	};

	
	private BaseController.BaseHandle queryByNameHandle = new BaseHandle()
	{
		/**
		 * 按照名字查询课程
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String coursename = (String)request.getParams().get(Course.NAME);
			String lecturer =(String)request.getParams().get(Course.LECTURER);
			String token = request.getToken();

			try
			{
				checkToken(token);
				
				ArrayList<Course>courselist = orm.courseRepository.inquireByFlags2(lecturer, coursename);
				ArrayList<HashMap<String,Object>> coursemapList =new ArrayList<>();
				for(int i=0;i<courselist.size();i++) {
					HashMap<String,Object> coursemap = new HashMap<>();
					Course course = courselist.get(i);
					coursemap.put(Course.UUID, course.getUuid().toString());
					coursemap.put(Course.LECTURER, course.getLecturer());
					coursemap.put(Course.CLASSTIME, course.getClasstime());
					coursemap.put(Course.LOCATION, course.getLocation());
					coursemap.put(Course.CAPCITY, course.getCapcity());
					coursemap.put(Course.CREDITS, course.getCredits());
					coursemap.put(Course.WEEK, course.getWeek());
					coursemap.put(Course.NAME,course.getName());
					coursemapList.add(coursemap);
				}
				
				response.setSuccess(true);
				response.getBody().put("courseMaplist", coursemapList);
				return response;
				
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}

		}
	};
	

	private BaseController.BaseHandle deleteCourseHandle = new BaseHandle()
	{
		/**
		 * 删除商品
		 */
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
				orm.userXCourseRepository.deleteUXGbycourseid(courseuuid);
				orm.examRepository.deleteExam(courseuuid);
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}catch(Exception e) {
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
		/**
		 * 删除掉一门课
		 */
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
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
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
		/**
		 * 选课
		 */
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
			}catch(Exception e) {
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
		/**
		 *退课
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = (String)request.getToken();
			String courseuuid = (String)request.getParams().get(Course.UUID);
			
			try
			{
				String useruuid = checkToken(token);
				orm.userXCourseRepository.deleteUserXCourse(useruuid, courseuuid);
				response.setSuccess(true);
				return response;
				
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}catch(Exception e) {
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
		/**
		 * 查学生课表
		 */
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
			}catch(Exception e) {
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
		/**
		 * 显示学生名单
		 */
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
						.inquireByFlag(UserXCourse.COURSE_ID,course.getUuid().toString());// 课程信息Map表
				for (HashMap<String, Object> record : coursemaplist)
				{
					HashMap<String, Object> student = new HashMap<>(); // 学生信息
					User user = orm.userRepository.inquireById((String) record.get(UserXCourse.USER_ID));
					student.put(User.USERNAME, user.getUsername());
					student.put(User.CARDNUM, user.getCardnum());
					student.put(UserXCourse.UUID,(String)record.get(UserXCourse.UUID));
					student.put(UserXCourse.SCORE, (int)record.get(UserXCourse.SCORE));
					studentMaplist.add(student);
				}
				response.getBody().put("studentMaplist", studentMaplist);
				response.getBody().put(Course.NAME, course.getName());
				response.getBody().put(Course.LECTURER, course.getLecturer());
				response.setSuccess(true);
				return response;
		
				
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}catch(Exception e) {
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
		/**
		 * 为成绩打分
		 */
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			String uuid = (String) request.getParams().get(UserXCourse.UUID);
			int score = (int)(double) request.getParams().get(UserXCourse.SCORE);
	
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
			}catch(Exception e) {
				response.setSuccess(false);
				response.getBody().put("result", e.getMessage());
				e.printStackTrace();
				return response;
			}

		}
	};
	
	
	
}
