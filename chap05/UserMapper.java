/*
파일 이름 : UserMapper.java
작 성 자 : 지 성훈
작 성 일 : 2022. 03. 30.(수)
프로그램 설명 : MyBatis에 대한 실습 내용.
*/
package com.tjoeun.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tjoeun.vo.User;

@Mapper
public interface UserMapper {
		// id만 받기 위하여 String
	   String login(User user);   

	   int loginCheck(User user);
	   int insertUser(User user); 	// 유저객체를 만든다.
	   int addAndGetKey(User user); // 
	    
	   User getUserById(String uid); // id를 가지고 유저 정보를 구한다. 
	   List<User> getUserList();
	   List<Map<String,String>> getUserMap();
	   
	   int updateUser(User u);
	   // key와 value로 표현.
	   // key는 항상 String 사용해도 상관 없다.
	   // Value는 내가 정할 값.
	   // Object로 한다면 다시 캐스팅을 해줘야 한다.
	   int updateByMap(Map<String,String> map);
	   
	   int deleteUser(String id);
	
	/* 위에 선언된 Dynamic SQL을 사용하는 메소드 선언 */
	   User findWithoutId(User user);
}
