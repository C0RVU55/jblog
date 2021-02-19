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
	public int userInsert(UserVo uVo) {
		System.out.println("[UserDao.insert()] --> "+uVo);
		
		return sqlSession.insert("user.userInsert", uVo);
	}
	
	//로그인 및 세션 정보 가져오기
	public UserVo selectUser(UserVo uVo) {
		System.out.println("[UserDao.selectOne()] --> "+uVo);
		
		return sqlSession.selectOne("user.selectUser", uVo);
	}
	
}
