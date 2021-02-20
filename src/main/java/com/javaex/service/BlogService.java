package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	//블로그 메인 : id, userName, blogTitle, logoFile 가져오기
	public BlogVo blogInfo(String id) {
		System.out.println("[BlogService.blogInfo()] --> "+id);
		
		return blogDao.selectBlog(id);
	}

}
