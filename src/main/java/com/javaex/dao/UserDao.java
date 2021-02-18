package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//회원 등록
	public void insert(UserVo uVo) {
		System.out.println("[UserDao.insert()] --> "+uVo);
		
		sqlSession.insert("user.insert", uVo);
	}
	
	
}
