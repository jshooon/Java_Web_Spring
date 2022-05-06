package com.tjoeun.svc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuguService {
	private HttpServletRequest request;

	@Autowired // 
	public void setRequest(HttpServletRequest request) {
		
		this.request = request;
	}

//	public String getGugu() {
//		String sDan = request.getParameter("dan");
//		if(sDan==null || sDan.equals("")) {
//			sDan = "2";
//		}
//		int dan = Integer.parseInt(sDan);
//		String gStr ="";
//		for(int i = 1; i <= 9; i++) {
//			gStr += String.format("%d * %d = %d <br>", dan, i, dan * i);
//		}
//		return gStr;
//	}
	public String getGugu() {
		String sDan = request.getParameter("dan");
		if(sDan==null || sDan.equals("")) {
			sDan = "2";
		}
		int dan = Integer.parseInt(sDan);
		return getGugu(dan);
	}
	
	public String getGugu(int dan) {
		
		String gStr ="";
		for(int i = 1; i <= 9; i++) {
			gStr += String.format("%d * %d = %d <br>", dan, i, dan * i);
		}
		return gStr;
	}
}
