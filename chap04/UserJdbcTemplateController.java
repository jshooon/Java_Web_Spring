package com.tjoeun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tjoeun.dao.UserJdbcTemplateDAO;
import com.tjoeun.vo.User;

@Controller
@SessionAttributes("uid")
@RequestMapping("/jdbc")
public class UserJdbcTemplateController {
	
	@Autowired
	UserJdbcTemplateDAO dao;
	
//	@GetMapping("/list")
//	public String list()
//	{	
//			return dao.getList().toString();
//	}
	
	
	@GetMapping("/login")
	public String form()
	{
		return "user/loginForm";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public Map<String, Boolean> login(User user, Model model)
	{
		Map<String, Boolean> map = new HashMap<>();
		boolean ok = dao.login(user);
		if(ok) {
			// @SessionAttributes("uid")에의해 세션영역에 저장된다.
			model.addAttribute("uid", user.getUid()); // uid라는 키를 사용하여 세션에 저장 선언.
		}
		map.put("ok", ok);
		return map;
	}
	
	@PostMapping("/idcheck")
	@ResponseBody
	public Map<String, Boolean> idcheck(User user){
		System.out.println(user.getUid());
		Map<String, Boolean> map = new HashMap<>();
		boolean idcheck = dao.idcheck(user);
		map.put("idcheck", !idcheck); // !붙이면 부정으로 바뀜
		return map;
	}
	
	@GetMapping("/list") 
	public String list(@SessionAttribute(name="uid", required=false) String uid, Model model)
	{	
		if(uid == null) {
			return "redirect:/jdbc/login";
		}else {
			List<User> list = dao.getList();
			model.addAttribute("list", list);
			return "user/userList";
		}
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam("id") String id, Model model) {		
		User detail = dao.detail(id);
		model.addAttribute("detail", detail);
		return "user/detail";
	}
	
	@GetMapping("/add")
	public String addUser() {
		return "user/addForm";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Map<String, Boolean> addUser(User user) {
		boolean added = dao.addUser(user);
		Map<String, Boolean> map = new HashMap<>();
		map.put("added", added);
		return map;
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam String id, Model model) {
		User edit = dao.detail(id);
		model.addAttribute("edit", edit);
		return "user/edit";
	}
	
	@PostMapping("/update")
	@ResponseBody
	public Map<String, Boolean> update(User user) {
		boolean updated = dao.update(user);
		Map<String, Boolean> map = new HashMap<>();
		map.put("updated", updated);
		return map;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Boolean> delete(@RequestParam("id") String id) {
		boolean deleted = dao.delete(id);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", deleted);
		return map;
	}
	
//	@PostMapping
//	@ResponseBody
//	public Map<String, Object> delete(User user){
//		Map<String, Object> map = new HashMap<>();
//		boolean deleted = dao.delete();
//		map.put("deleted", deleted);
//	}
	
	
}
	
	
	

