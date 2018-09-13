package com.wolfTungsten.vcampus.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.internal.LinkedTreeMap;
import com.wolfTungsten.vcampus.entity.Course;
import com.wolfTungsten.vcampus.entity.Exam;
import com.wolfTungsten.vcampus.entity.Experiment;
import com.wolfTungsten.vcampus.entity.UserXCourse;
import com.wolfTungsten.vcampus.utils.Request;
import com.wolfTungsten.vcampus.utils.Response;

public class HelperController extends BaseController
{
	public HelperController() {
		super();
		this.pathMap.put("addExp", addExperimentController );
		this.pathMap.put("delExp", deleteExperimentController);
		this.pathMap.put("showStuExp",showExperimentsHandle);
		this.pathMap.put("updateExp", updateExperimentHandle);
		this.pathMap.put("addExam", addExamHandle);
		this.pathMap.put("delExam",deleteExamHandle);
		this.pathMap.put("showStuExam", showExamHandle);
		
	}
	//已测试
	private BaseController.BaseHandle addExperimentController = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			String name = (String)request.getParams().get(Experiment.NAME);
			String location = (String)request.getParams().get(Experiment.LOCATION);
			int duration =(int)(double)request.getParams().get(Experiment.DURATION);
			long startTime = (long)(double)request.getParams().get(Experiment.STARTTIME);
			String courseUUID = (String)request.getParams().get(Experiment.COURSE_UUID);
			
			try
			{
				checkToken(token);
				orm.experimentRepository.addExperiment(name, courseUUID, location, duration, startTime);
				response.setSuccess(true);
				return response;
			} catch (SQLException e)
			{
				response.setSuccess(false);
				response.getBody().put("resulst:", e.getMessage());
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
	//未测试
	private BaseController.BaseHandle deleteExperimentController = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			String  uuid = (String)request.getParams().get(Experiment.UUID);
			
			try
			{
				checkToken(token);
				orm.experimentRepository.deleteExperiment(uuid);
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
	private BaseController.BaseHandle updateExperimentHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			
			try
			{
				checkToken(token);
				LinkedTreeMap<String,Object>
				expinfomap = (LinkedTreeMap<String, Object>) request.getParams().get("expinfomap");
				orm.experimentRepository.update(expinfomap);
				response.setSuccess(true);
				return response ; 
				
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
	//前端传token 
	//学生实验表
	private BaseController.BaseHandle showExperimentsHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			
			try
			{	
				String uuid = checkToken(token);
				ArrayList<UserXCourse> courselist = orm.userXCourseRepository
						.queryByUUID(UserXCourse.USER_ID, uuid);	
				ArrayList<HashMap<String,Object>> expMapList = new ArrayList<>();
				for(UserXCourse uxc : courselist) {
					ArrayList<HashMap<String,Object>> expMaplist2 
					= orm.experimentRepository.queryByFlag(Experiment.COURSE_UUID,
							uxc.getCourse_id());
					Course course = orm.courseRepository.inquireById(uxc.getCourse_id());
					for(HashMap<String,Object> expmap:expMaplist2) {
						expmap.put(Course.LECTURER, course.getLecturer());
					}
					expMapList.addAll(expMaplist2);
				}
				response.setSuccess(true);
				response.getBody().put("expMaplist", expMapList);
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
	//未测试
	private BaseController.BaseHandle addExamHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			String name = (String)request.getParams().get(Experiment.NAME);
			String location = (String)request.getParams().get(Experiment.LOCATION);
			int duration =(int)(double)request.getParams().get(Experiment.DURATION);
			long startTime = (long)(double)request.getParams().get(Experiment.STARTTIME);
			String courseUUID = (String)request.getParams().get(Experiment.COURSE_UUID);
			
			try
			{
				checkToken(token);
				//orm.examRepository.addExam(name, courseUUID, startTime, duration, location);
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
	private BaseController.BaseHandle deleteExamHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			String uuid = (String) request.getParams().get(Exam.UUID);
			try
			{
				checkToken(token);
				orm.examRepository.deleteExam(uuid);
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
	//学生考试表
	public BaseController.BaseHandle showExamHandle = new BaseHandle()
	{
		
		@Override
		public Response work(Request request)
		{
			Response response = new Response();
			String token = request.getToken();
			
			try
			{
				String uuid = checkToken(token);
				ArrayList<UserXCourse> courselist = orm.userXCourseRepository
						.queryByUUID(UserXCourse.USER_ID, uuid);	//找到学生课表id信息
				ArrayList<HashMap<String,Object>> examMaplist = new ArrayList<>();
				for(UserXCourse uxc : courselist) {
					ArrayList<HashMap<String,Object>> expMaplist2 
					= orm.examRepository.queryByFlag(Experiment.COURSE_UUID,
							uxc.getCourse_id());//根据每个课程id去搜该课程下的考试
					Course course = orm.courseRepository.inquireById(uxc.getCourse_id());//得到该课程实体
					for(HashMap<String,Object> expmap:expMaplist2) {
						expmap.put(Course.LECTURER, course.getLecturer());//为该课程下每个考试赋上教师
						expmap.put(UserXCourse.SCORE, uxc.getScore());
					}
					examMaplist.addAll(expMaplist2);//该课程下对应的考试信息全并起来 
				}
				response.setSuccess(true);
				response.getBody().put("examMaplist", examMaplist);
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
	
	
}
