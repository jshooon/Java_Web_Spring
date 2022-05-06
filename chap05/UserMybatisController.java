/*
파일 이름 : UserMybatisController.java
작 성 자 : 지 성훈
작 성 일 : 2022. 03. 30.(수)
프로그램 설명 : MyBatis에 대한 실습 내용.
*/
package com.tjoeun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.github.pagehelper.PageInfo;
import com.tjoeun.dao.UserMybatisDAO;
import com.tjoeun.vo.User;

@Controller
@SessionAttributes("uid")
@RequestMapping("/mb") //localhost:8888/mb/
public class UserMybatisController {
	
	@Autowired
	private UserMybatisDAO dao;
	
	@GetMapping("/login")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public Map<String, Object> login(User user){
		System.out.println(user);
		Map<String, Object> map = new HashMap<>();
		boolean ok = dao.login(user);
		map.put("ok", ok);
		return map;
	}
	
//	@GetMapping("/user")
//	public String getUser(@RequestParam String uid) {
//		User user = dao.selectById(uid);
//		return user.toString();
//	}
	
	@GetMapping("/list")
	public String getList(Model m) {
		List<User> list= dao.getUserList();
		m.addAttribute("list", list);
		return "user/userList";
	}
	
	@GetMapping("/list2")
	public String getList2(Model m) {
		List<Map<String, String>> list= dao.getUserMap();
		m.addAttribute("list", list);
		return "user/userList";
	}
	
	@GetMapping("/page/{pageNum}")
//	@ResponseBody
	public String getPage(@PathVariable int pageNum, Model m) {
		PageInfo<User> pageInfo =  dao.getListPage(pageNum);
//		pageInfo.getNavigatepageNums();	// pageInfo.navigatepageNums
		m.addAttribute("pageInfo",pageInfo);
		return "/user/userPage";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam String id, Model m) {
		User detail = dao.selectById(id);
		m.addAttribute("detail", detail);
		return "user/detail";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam String id, Model m) {
		User edit = dao.selectById(id);
		m.addAttribute("edit", edit);
		return "user/edit";
	}
	
	@GetMapping("/add")
	public String addFrom() {
		return "/user/addForm";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Map<String, Object> addUser(User u, Model m) {
		Map<String, Object> map = new HashMap<>();
		map.put("added", dao.insert(u) > 0 ? true : false);
		return map;
	}
	
	@PostMapping("/idcheck")
	@ResponseBody
	public Map<String, Object> idcheck(@RequestParam String uid, Model m){
		Map<String, Object> map = new HashMap<>();
		map.put("idcheck", dao.idCheck(uid));
		return map;
	}
	
	@PostMapping("/update")
	@ResponseBody
	public Map<String, Object> update(User user){
		Map<String, Object> map = new HashMap<>();
		map.put("updated", dao.update(user) > 0 ? true : false);
		return map;
	}
	
	@PostMapping("/update2")
	@ResponseBody
	public Map<String, Boolean> update2(User user){
		// uid르 키워드로 하여 phone을 갱신한다.
		Map<String, String> pMap = new HashMap<>();
		pMap.put("uid", user.getUid());
		pMap.put("phone", user.getPhone());
		boolean updated = dao.updateByMap(pMap);
		// 데이터를 여러개의 테이블을 가져올 때에 사용한다.
		// VO가 없을 경우 사용할 수 있다.
		// VO를 한번 쓸 경우 Map을 사용한다.
		Map<String, Boolean> map = new HashMap<>();
		map.put("updated", updated);
		return map;
		
	}
	//-------------적용법----------------
//		Map<String, Object> testMap = new HashMap<>();
//		testMap.put("1", 5);
//		testMap.put("2", true);
//		testMap.put("3", 3.14);
//		testMap.put("4", "koon");
//		testMap.put("5", user);
	//--------------사용법----------------
//		Object obj = testMap.get("2");
//		boolean bool = (Boolean) obj;
	//--------------결론-------------------
	//정확한 자료형을 안다면 캐스팅할 필요없기 때문에
	//Object보다 자료형을 적용하면 된다.

	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam String id){
		Map<String, Object> map = new HashMap<>();
		map.put("deleted", dao.delete(id) > 0 ? true : false);
		return map;
	}
	

	
}
