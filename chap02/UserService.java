package com.tjoeun.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.dao.UserJdbcDAO;
import com.tjoeun.vo.User;

@Service
public class UserService
{
//	@Autowired
//	private UserDAO dao;
	
	@Autowired
	private UserJdbcDAO dao;
	
	public boolean login(User user)
	{
		boolean pass = dao.login(user);
		return pass;
	}

	public List<User> getList() 
	{
		return dao.getList();
	}

	public boolean add(User user) {
		return dao.save(user);
	}

	public User detail(String id) {
		return dao.detail(id);
	}

	public User edit(String id) {
		return dao.detail(id);
	}

	public boolean update(User user) {
		return dao.update(user);
	}

	public boolean delete(String id) {
		return dao.delete(id);
	}
}
