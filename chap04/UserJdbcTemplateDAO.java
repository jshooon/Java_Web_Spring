package com.tjoeun.dao;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tjoeun.vo.User;

@Repository
public class UserJdbcTemplateDAO 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	   public boolean login(User user) {
		   String sql = "SELECT * FROM users WHERE uid = ? AND pwd = ?";
		   try {
		   return jdbcTemplate.queryForObject(
					sql,
					new Object[] {user.getUid(), user.getPwd()},
					new int[] {Types.VARCHAR, Types.VARCHAR},
					(rs,i)-> {
						if(user.getUid().equals(rs.getString("UID")) && 
								user.getPwd().equals(rs.getString("PWD"))) {
							return true;
						} else {
							return false;
						}
						});	
			} catch(EmptyResultDataAccessException erda) {
				
				return false;
			}
	   }
	
	public List<User> getList(){
		String sql = "SELECT * FROM users";
		return jdbcTemplate.query(
			sql,
			(rs,i)-> {
				User u = new User();
				u.setUid(rs.getString("UID"));
				u.setPwd(rs.getString("PWD"));
				u.setName(rs.getString("NAME"));
				u.setPhone(rs.getString("PHONE"));
				return u;
			}
		);
	}
	
	public User detail(String id){
		String sql = "SELECT * FROM users WHERE uid = ?";
		return jdbcTemplate.queryForObject(
			sql,
			new Object[] {id},
			new int[] {Types.VARCHAR},
			// i = 데이터의 행 인덱스 번호.
			(rs,i)-> {
				User u = new User();
				u.setUid(rs.getString("UID")); // 객체가 컬럼의 값으로 초기화
				u.setPwd(rs.getString("PWD"));
				u.setName(rs.getString("NAME"));
				u.setPhone(rs.getString("PHONE"));
				return u; // 초기화된 객체가 리턴.
			}
		);
	}
	
	public boolean addUser(User user) {
	      String sql = "INSERT INTO users VALUES(?, ?, ?, ?)";
	         int rows = jdbcTemplate.update(
	        	   sql,
	               new Object[] {user.getUid(), user.getPwd(), user.getName(), user.getPhone()},
	               new int[] {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR}
	               );
	         return rows>0;
	   }
	
	public boolean update(User user) {
		String sql = "UPDATE users SET phone= ? WHERE uid = ?";
		int rows = jdbcTemplate.update(
				sql,
				new Object[] {user.getPhone(), user.getUid()},
				new int[] {Types.VARCHAR, Types.VARCHAR});
		return rows > 0;
	}
	
	public boolean delete(String id) {
		String sql = "DELETE FROM users WHERE uid = ?";
		int rows = jdbcTemplate.update(
				sql,
				new Object[] {id},
				new int[] {Types.VARCHAR});
		return rows > 0;
	}

	public boolean idcheck(User user) {
		String sql = "SELECT * FROM users WHERE uid = ?";
		try {
			return jdbcTemplate.queryForObject(
					sql,
					new Object[] {user.getUid()},
					new int[] {Types.VARCHAR},
					(rs,i)->{
//						return user.getUid().equals(rs.getString("UID")) ? false : true;
						return true;
					});
		} catch(EmptyResultDataAccessException erda) {
//			return true;
			return false;
		}
	}
}