package com.tjoeun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjoeun.mapper.board.BoardMapper;
import com.tjoeun.vo.BoardVO;

@Repository
public class BoardDAO {
	
	@Autowired
	private BoardMapper boardMapper;
	
	public boolean addBoard(BoardVO board) {
		return boardMapper.addBoard(board) > 0;
	}
	
	public boolean addFileInfo(Map<String,Object> map) {
		return boardMapper.addFileInfo(map) > 0;
	}
	
	public List<Map<String,Object>> boardList() {
		return boardMapper.boardList();
	}
	
	public List<Map<String, Object>> detail(int num) {
		return boardMapper.getDetail(num);
	}
	
	public String getFilename(int num) 
	{
		return boardMapper.getFilename(num);
	}

	public boolean delete(int num) {
		return boardMapper.delete(num) > 0;
	}

	public boolean deleteFileInfo(int num) {
		return boardMapper.deleteFileInfo(num) > 0;
	}

	public boolean update(BoardVO board) {
		return boardMapper.update(board) > 0;
	}
	
}
