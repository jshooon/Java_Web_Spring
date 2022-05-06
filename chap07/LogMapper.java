/*
파일 이름 : LogMapper.java
작 성 자 : 지 성훈
작 성 일 : 2022. 03. 31.(목)
프로그램 설명 : MyBatis에 대한 실습 내용.
*/
package com.tjoeun.mapper.log;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
	
	//CRUD, AI필드 값 추출
	int addLog(String contents);

	int updateLog(Map<String, Object> pMap);
	
	List<Map<String,Object>> getUserMap();

	List<Map<String, Object>> getLog(int num);

	int add_get_ai(Map<String, Object> map);
}
