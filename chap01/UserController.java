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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.tjoeun.svc.UserService;
import com.tjoeun.vo.User;

@Controller //localhost:8888/user/login
@SessionAttributes("uid") // uid 라는 파라미터변수나 ModelAttribute 속성은 세션에 저장
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserService svc;

	@GetMapping("/login")
	public String form()
	{
		return "user/loginForm";
	}

	@PostMapping("/login")
	@ResponseBody
	// 만약 이 이메소드의 파라미터에 SessionAttribute(uid)에 선언된
	//	uid 변수가 있다면 자동으로 세션에 저장된.
	//	Model에 uid를 저장하기 전에라도 uid파라미터에 값이 전달되면 
	//  자동으로 세션에 uid가 저장된다
	public Map<String, Boolean> login(User user, Model model)
	{
		Map<String, Boolean> map = new HashMap<>();
		boolean ok = svc.login(user);
		if(ok) {
			// @SessionAttributes("uid")에의해 세션영역에 저장된다.
			model.addAttribute("uid", user.getUid()); // uid라는 키를 사용하여 세션에 저장 선언.
		}
		map.put("ok", ok);
		return map;
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status)
	{
		status.setComplete();
		return "redirect:/user/login"; // response.sendRedirect("/user/login"); 와 같다.
	}
	
	@GetMapping("/list") /* 세션에 user가 저장되어 있지 않으면 오류가 발생하므로 required=false 설정함 */
	//uid세션영역에서 데이터를꺼내서 uid에넣는다.
	public String list(@SessionAttribute(name="uid", required=false) String uid, Model model)
	{	
		if(uid == null) {
			return "redirect:/user/login"; // 로그인 폼으로 되돌림.
		}else {
			List<User> list = svc.getList();
			model.addAttribute("list", list);
			return "user/userList";
		}
	}
	
	@GetMapping("/add")
	public String addEmp() {
		return "/user/addForm";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Map<String, Boolean> addEmp(User user) {
		boolean added = svc.add(user);
		Map<String, Boolean> map = new HashMap<>();
		map.put("added", added);
		return map;
	}
	
//	@GetMapping("/detail")
//	public ModelAndView detail(@RequestParam("id") String id) {
//		System.out.println(id);
//		
//		User detail = svc.detail(id);
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("detail", detail);
//		mv.setViewName("user/detail");
//		
//		return mv;
//	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam("id") String id, Model model) {		
		User detail = svc.detail(id);
		model.addAttribute("detail", detail);
		return "user/detail";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam String id, Model model) {
		User edit = svc.detail(id);
		model.addAttribute("edit", edit);
		return "user/edit";
	}
	
	@PostMapping("/update")
	@ResponseBody
	public Map<String, Object> update(User user){
		System.out.println(user);
		boolean updated = svc.update(user);
		Map<String, Object> map = new HashMap<>();
		map.put("updated", updated);
		return map;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam("id") String id){
		Map<String, Object> map = new HashMap<>();
		boolean deleted = svc.delete(id);
		map.put("deleted", deleted);
		return map;
	}
}

