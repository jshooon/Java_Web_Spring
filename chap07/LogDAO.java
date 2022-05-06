package com.tjoeun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjoeun.mapper.log.LogMapper;

@Repository
public class LogDAO {

	// Autowired 사용하여 LogMapper 주입
	@Autowired
	private LogMapper log;
	
	public boolean addLog(String contents){
		return log.addLog(contents) > 0;
	}

	public boolean updateLog(Map<String, Object> pMap) {
		return log.updateLog(pMap) > 0;
	}
	
	public List<Map<String,Object>> getUserMap (){
		return log.getUserMap();
	}

	public List<Map<String, Object>> getLog(int num) {
		return log.getLog(num);
	}

	public int add_get_ai(Map<String, Object> map) {
		return log.add_get_ai(map);
	}
}
