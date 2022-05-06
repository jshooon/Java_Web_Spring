package com.tjoeun.mapper.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tjoeun.vo.BoardVO;

@Mapper
public interface BoardMapper {
	
	int addBoard(BoardVO board);
	int addFileInfo(Map<String,Object> map);
	List<Map<String, Object>> boardList();
	List<Map<String, Object>> getDetail(int num);
	String getFilename(int num);
	int deleteFileInfo(int num);
	int delete(int num);
	int update(BoardVO board);
}
