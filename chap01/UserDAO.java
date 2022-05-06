package com.tjoeun.dao;

import java.io.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import com.tjoeun.vo.User;

@Repository
public class UserDAO 
{
	private static final String fname = "C:/test/users.txt";

	public boolean login(User user) 
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fname));
			String line = null;
			while((line=br.readLine())!=null) {
				String[] token = line.split("\\|");
				if(token[0].equals(user.getUid()) && 
						token[1].equals(user.getPwd())) {
					br.close();
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}

	public List<User> getList() 
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fname));
			List<User> list = new ArrayList<>();
			User user = null;
			String line = null;
			while((line=br.readLine())!=null) {
				String[] token = line.split("\\|");
				user = new User();
				user.setUid(token[0]);
				user.setPwd(token[1]);
				user.setName(token[2]);
				user.setPhone(token[3]);
				list.add(user);
			}
			br.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	public boolean save(User add) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fname, true));
			pw.println(add.getLine());
			pw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public User detail(String id) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
			String line = null;
			User u = null;
			while((line = br.readLine()) != null) {
				String[] token = line.split("\\|");
				if(token[0].equals(id)) {
					u = new User();
					u.setUid(token[0]);
					u.setPwd(token[1]);
					u.setName(token[2]);
					u.setPhone(token[3]);
					br.close();
					return u;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean update(User user) {
		List<User> list = getList();
		int idx = list.indexOf(user);
		if(idx < 0) {
			return false;
		}
			list.get(idx).setPhone(user.getPhone());
				return overWrite(list);
	}
		
	public boolean delete(String id) {
		List<User> list = getList();
		User u = new User();
		u.setUid(id);
		boolean deleted = list.remove(u);
		if(deleted) return overWrite(list);
		else return false;
	}
	

	private boolean overWrite(List<User> list) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fname));
			for(int i = 0; i < list.size(); i++) {
				pw.println(list.get(i));
			}
			pw.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	
	
	
}
