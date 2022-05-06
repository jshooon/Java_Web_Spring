package com.tjoeun.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
// interface가 저장된 패키지
import com.tjoeun.mapper.UserMapper;
import com.tjoeun.vo.User;

@Repository
public class UserMybatisDAO
{
   @Autowired
   private UserMapper userMapper;

   public boolean idCheck(String uid) {
	   return (userMapper.getUserById(uid) == null);
   }
   
   public int insert(User u) {
      return userMapper.insertUser(u);
   }
   
    public int addAndGetKey(User u) {
        return userMapper.addAndGetKey(u);
    }
   public User selectById(String uid) {
      return userMapper.getUserById(uid);
    }
   
   public List<User> getUserList() {
      return userMapper.getUserList();
    }
   
   // MyBatis Pagenation
   public PageInfo<User> getListPage(int pageNum){
	   PageHelper.startPage(pageNum,3);
	   PageInfo<User> pageInfo = new PageInfo<>(userMapper.getUserList());
	   return pageInfo;
   }
   
   public List<Map<String,String>> getUserMap(){
      return userMapper.getUserMap();
   }

   public int update(User u) {
      return userMapper.updateUser(u);
   }
   
   public boolean updateByMap(Map<String,String> map) {
      return userMapper.updateByMap(map)>0;
   }
   
   public int delete(String id) {
      return userMapper.deleteUser(id);
   }

	public boolean login(User user) {
		return userMapper.login(user)!=null;
	}
}