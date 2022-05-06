package com.tjoeun.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tjoeun.vo.User;

@Repository
public class UserJdbcDAO {
	   private Connection conn;
	   private PreparedStatement pstmt; 
	   private ResultSet rs;
	   
	   // uid, pwd, name, phone
	   
	   
	   private Connection getConn() {
		      try{		         
		         String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&SSL=false";
		         Class.forName("com.mysql.cj.jdbc.Driver"); //JAVA에서 MySQL을 접속가능하도록  자바에는 데이터베이스를 연결하려는 인터페이스가 있다. 자바.sql
		         Connection conn = DriverManager.getConnection(url, "root", "tjoeun"); // DB연결
		         return conn; // 커넥션 객체가 return되어 연결됨.
		      }catch(Exception ex) {
		    	 System.err.println("DB 접속 실패!");
		    	 ex.printStackTrace();
		      }
		   return null;
	   }
	   
	   private void closeAll() {
		   try {
			   if(rs!=null) {
				   rs.close();
			   }
			   if(pstmt!=null) {
				   pstmt.close();
			   }			   
			   if(conn!=null) {
				   conn.close();
			   }
		    } catch (SQLException e) {
		    	e.printStackTrace();
		    }
		   
	   }
	   
	   
	   public boolean login(User user) {
		   conn = getConn();
		   String sql = "SELECT * FROM users WHERE uid = ? AND pwd = ?";
		   
		   try {
			   pstmt = conn.prepareStatement(sql);
			   pstmt.setString(1, user.getUid());
			   pstmt.setString(2, user.getPwd());
			   rs = pstmt.executeQuery(); // mysql전달 후 실행 결과 값 리턴.
			   
			   if(rs.next()) return true;
			   
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		   return false;
	   }
	   
//	   @GetMapping("/list")
//	   public String list(Model m) {
//		   List<UsersVO> list = getList();
//		   m.addAttribute("list", list);
//		   return "/users/usersList/";
//	   }
	   
	   
	   public List<User> getList(){
		   this.conn = getConn();
		   
		   try {
			   String sql = "SELECT * FROM users";
			this.pstmt = conn.prepareStatement(sql);
			this.rs = pstmt.executeQuery();
			
			List<User> list = new ArrayList<>();
						
			while(rs.next()) {
				String uid = rs.getString("UID");
				String pwd = rs.getString("PWD");
				String name = rs.getString("name");
				String phone = rs.getString("PHONE");
				
				User users = new User();
				users.setUid(uid);
				users.setPwd(pwd);
				users.setName(name);
				users.setPhone(phone);
				list.add(users);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		   return null;
	   }
	   
	   public boolean save(User user) {
		   this.conn = getConn();
		   
		   try {
			   String sql = "INSERT INTO users VALUES(?, ?, ?, ?)";
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setString(1, user.getUid());
			this.pstmt.setString(2, user.getPwd());
			this.pstmt.setString(3, user.getName());
			this.pstmt.setString(4, user.getPhone());
			
			int rows = pstmt.executeUpdate();
			return rows > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		   return false;
	   }
	   
	   public User detail(String id) {
		   this.conn = getConn();
		   
		   try {
			   String sql = "SELECT * FROM users WHERE uid = ?";
			   this.pstmt = conn.prepareStatement(sql);
			   this.pstmt.setString(1, id);
			   this.rs = pstmt.executeQuery();
			   
			   
			   User u = null;
			   if(rs.next()) {
				   String uid = rs.getString("UID");
				   String pwd = rs.getString("PWD");
				   String name = rs.getString("NAME");
				   String phone = rs.getString("PHONE");
				   
				    u = new User();
				   u.setUid(uid);
				   u.setPwd(pwd);
				   u.setName(name);
				   u.setPhone(phone);
			   }
			   return u;			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		   return null;
	   }
	   
	   public boolean update(User user) {
		   this.conn = getConn();
		   String sql = "UPDATE users SET phone = ? WHERE uid = ?";
		   
		   try {
			   this.pstmt = conn.prepareStatement(sql);
			   this.pstmt.setString(1, user.getPhone());
			   this.pstmt.setString(2, user.getUid());

			   int rows = pstmt.executeUpdate();
			   return rows > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		   return false;
	   }
	   
	   public boolean delete(String id) {
		   this.conn = getConn();
		   String sql = "DELETE FROM users WHERE uid = ?";
		   
		   try {
			this.pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int rows = pstmt.executeUpdate();
			
			return rows > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			closeAll();
		}
		   return false;
		   
	   }
}
