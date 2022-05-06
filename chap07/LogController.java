/*
파일 이름 : LogController.java
작 성 자 : 지 성훈
작 성 일 : 2022. 03. 31.(목)
프로그램 설명 : MyBatis에 대한 실습 내용.
*/

package com.tjoeun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tjoeun.dao.LogDAO;

@RestController
//@Controller
@RequestMapping("/log") 
public class LogController {

		@Autowired
		LogDAO dao;
	
		@GetMapping("/test")
		public String test() {
			return "/log/test";
		}
		
		/* log 테이블에 저장할 데이터 준비 num, contents */
		
		@GetMapping("/add")
		public String add() {
			boolean added =  dao.addLog("contents2"); 
			return "added="+added;
		}
		
		@GetMapping("/update")
		public Map<String, Boolean> update(@RequestParam String contents, @RequestParam int num ){
			Map<String, Object> pMap = new HashMap<>();
			pMap.put("num", num);
			pMap.put("contents",contents);
			boolean updated = dao.updateLog(pMap);
			Map<String, Boolean> map = new HashMap<>();
			map.put("updated", updated);
			return map;
		}
		
		@GetMapping("/list")
		public List<Map<String,Object>> list(){
			List<Map<String,Object>> list = dao.getUserMap();
			return list;
		}
		
		@GetMapping("/getLog")
		public Map<String,Object> getLog(@RequestParam int num){
			Map<String, Integer> map = new HashMap<>();
			map.put("num", num);
			List<Map<String,Object>> getLog = dao.getLog(num);
			Map<String, Object> xmap = new HashMap<>();
			xmap.put("getLog", getLog);
			return xmap;
		}
		
		@GetMapping("/add_get_ai")
		@ResponseBody
		public String getAi(){
			Map<String, Object> map = new HashMap<>();
			map.put("contents", "world");
			dao.add_get_ai(map);
			return "add_get_ai : " + map.get("num");
		}
//		public String 
}
