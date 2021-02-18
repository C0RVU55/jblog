package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao uDao;
	
	//회원 등록
	public void join(UserVo uVo) {
		System.out.println("[UserService.join()] --> "+uVo);
		
		uDao.insert(uVo);
	}
}
