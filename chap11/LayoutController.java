package com.tjoeun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/layout") //http://localhost:8888/layout/1
public class LayoutController {
	
	@GetMapping("/1")
	public String layout01() {
		return "layout/01";
	}
	
	@GetMapping("/2")
	public String layout02() {
		return "layout/02";
	}
}
