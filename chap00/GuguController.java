package com.tjoeun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tjoeun.svc.GuguService;

@Controller
@RequestMapping("/gugu") //@RequestMapping 준다면 메소드에 @GetMapping /gugu를 따로 하지 않아도 된다.
public class GuguController {
	@Autowired
	private GuguService svc;
	
	@GetMapping("") // 파라미터에 Model을 선언한다면 spring이 알아서 Model객체를 생성해준다
					// m.addAttribute를 하여 request 영역에 뷰에 전달할 데이터를 영역에 저장 한다.
	public String gugu(Model m) {
		m.addAttribute("gugu", svc.getGugu());
		return "gugu";
	}
	
	@GetMapping("/{dan}") // url = /gugu/5 하위패스에다가 변수 값을 주자.
	// 데이터와 뷰를 같이 리턴하겠다라는 뜻.
	public ModelAndView getGugu2(@PathVariable("dan") int dan) { //@PathVariable : dan이라는 문자열을 넣어주고 숫자를 받아서 {dan}에 넣어준다.
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("gugu",svc.getGugu(dan));
		mv.setViewName("gugu");
		return mv;
	}
}
