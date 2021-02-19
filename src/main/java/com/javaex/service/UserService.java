package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao uDao;
	
	@Autowired
	private BlogDao blogDao;
	
	
	//회원 등록
	public void join(UserVo uVo) {
		System.out.println("[UserService.join()] --> "+uVo);
		
		//회원 등록
		uDao.userInsert(uVo);
		
		//블로그 생성 : blogDao에 UserVo, map으로 넘기면 pk중복 오류남 
		BlogVo blogVo = new BlogVo(uVo.getId(), uVo.getUserName());
		blogDao.blogInsert(blogVo);

	}
	
	//로그인
	public UserVo login(UserVo uVo) {
		System.out.println("[UserService.login()] --> "+uVo);
		
		return uDao.selectUser(uVo);
	}
}
