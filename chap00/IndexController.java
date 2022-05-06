package com.tjoeun.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@RestController // 리턴하는 값 요청한것이 바로 응답이 된다.
//그냥 Controller를 사용한다면, 브라우져로 바로 가지 않고 뷰의 경로가된다.
@Controller 
public class IndexController {
	@GetMapping("/") // 아무경로 없는 요청.
	public String index() {
		// viewPath라 생각 하면된다.
		return "index"; // 뷰의 경로 따라서 WEB-INF 디렉토리안에 index.jsp를 찾는다.
						// 응답의 뷰
	}
	
	// 제이슨 문자열로 응답만들기
	@GetMapping("/json/test")
	@ResponseBody // @ResponseBody 응답을 메소드로 바로 보내겠다는 것. ajax 사용시 사용한다.
	public String resString() {
		return "{\"key\" : \"value\"}" + "Hello!!!!!!!"; // 웹브라우져에 응답으로 보내진다.
	}
	
	
	// Map을 이용한 JSON 문자열 전송하기
	@GetMapping("/map/test")
	@ResponseBody // : 이 메소드는 응답의 본체다. 브라우져에 보낸다.
	public Map<String, Object> mapTest(){
		Map<String, Object> map = new HashMap<>();
		map.put("name", "smithid");
		map.put("level", 7);
		map.put("birth", java.sql.Date.valueOf("2000-01-01"));
		map.put("married", true);
		return map;
	}
	
	// View 연결
	@GetMapping("/view/test")
	public String viewTest() {
		
		return "index"; // jsp 경로.
	}
}
