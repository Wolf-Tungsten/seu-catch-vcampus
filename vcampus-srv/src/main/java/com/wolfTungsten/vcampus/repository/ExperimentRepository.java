package com.wolfTungsten.vcampus.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.google.gson.internal.LinkedTreeMap;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.wolfTungsten.vcampus.entity.Book;
import com.wolfTungsten.vcampus.entity.Experiment;

public class ExperimentRepository	extends CurdRepository<Experiment>
{

	public ExperimentRepository(ConnectionSource conn) throws SQLException
	{
		super(conn, Experiment.class);
	}
	
	public void addExperiment(String name,String courseUUID, String location,int duration,long startTime) throws SQLException {
		Experiment experiment = new Experiment();
		experiment.setDuration(duration);
		experiment.setLocation(location);
		experiment.setName(name);
		experiment.setStartTime(startTime);
		experiment.setCourseUUID(courseUUID);
		long timestamp = System.currentTimeMillis()/1000;
		experiment.setCreateTime(timestamp);
		experiment.setUpdateTime(timestamp);
		dao.create(experiment)	;
	}
	
	public void deleteExperiment(String uuid) throws SQLException {
		UUID Experimentuuid =UUID.fromString(uuid);
		dao.delete((PreparedDelete<Experiment>)dao.deleteBuilder()
				.where().eq(Experiment.UUID, Experimentuuid).prepare());
	}
	public Experiment queryById(String uuid) throws SQLException {
		UUID Experimentuuid = UUID.fromString(uuid);
		List<Experiment> list = dao.queryForEq(Experiment.UUID, Experimentuuid);
		return list.get(0);
	}
	public ArrayList<Experiment> queryByCourseUUID(String uuid) throws SQLException{
		UUID euuid = UUID.fromString(uuid);
		ArrayList<Experiment> list = (ArrayList<Experiment>) dao.queryForEq(Experiment.COURSE_UUID, euuid);
		return list;			
	}
	/**
	 * 返回所有实验信息
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<HashMap<String,Object>> queryAllExperiment() throws SQLException {
		ArrayList<Experiment> expList = new ArrayList<>();
		ArrayList<HashMap<String,Object>> expinfoList = new ArrayList<>();
		expList = (ArrayList<Experiment>)dao.queryForAll();
		for(Experiment exp : expList) {
			HashMap<String,Object> expinfo = new HashMap<>();
			expinfo.put(Experiment.UUID, exp.getUuid().toString());
			expinfo.put(Experiment.LOCATION, exp.getLocation());
			expinfo.put(Experiment.NAME, exp.getName());
			long startTime = exp.getStartTime();
			int duration = exp.getDuration();
			expinfo.put(Experiment.STARTTIME, caltime(startTime,duration));
			expinfoList.add(expinfo);
		}
		return expinfoList;
	}
	/**
	 * 根据columName 和value 返回相关实验信息
	 * @param columnname
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<HashMap<String,Object>>queryByFlag(String columnname,Object value) throws SQLException{
		ArrayList<Experiment> expList = new ArrayList<>();
		ArrayList<HashMap<String,Object>> expinfoList = new ArrayList<>();
		expList =(ArrayList<Experiment>)dao.queryForEq(columnname, value);
		for(Experiment exp : expList) {
			HashMap<String,Object> expinfo = new HashMap<>();
			expinfo.put(Experiment.UUID, exp.getUuid().toString());
			expinfo.put(Experiment.LOCATION, exp.getLocation());
			expinfo.put(Experiment.NAME, exp.getName());
			long startTime = exp.getStartTime();
			int duration = exp.getDuration();
			expinfo.put(Experiment.STARTTIME, caltime(startTime,duration));
			expinfoList.add(expinfo);
		}
		return expinfoList;	
	}
	public void update(LinkedTreeMap<String,Object>expsinfo)throws SQLException {
		UpdateBuilder<Experiment,String> updateBuilder = dao.updateBuilder();
		String expUuid = (String) expsinfo.get("uuid");
		updateBuilder.where().eq(Experiment.UUID, UUID.fromString(expUuid));
		expsinfo.remove(Experiment.UUID);
		for(String columnName:expsinfo.keySet()) {
			updateBuilder.updateColumnValue(columnName, expsinfo.get(columnName)).update();
		}

		
	}
	
	
	public String caltime(long timestamp,int duration) {
		Date date1 = new Date(timestamp*1000);	
		Date date2 = new Date((timestamp+(long)duration)*1000);
		
		StringBuilder sb = new StringBuilder();
		int min1 = date1.getMinutes();
		int min2 = date2.getMinutes();
		sb.append(date1.getHours()).append(":");
		if(min1<10)sb.append(0).append(min1);
		else sb.append(min1);
		sb.append("--").append(date2.getHours()).append(":");
		if(min2<10)sb.append(0).append(min2);
		else sb.append(min2);

		return sb.toString();			
	}
	
	
	
}
