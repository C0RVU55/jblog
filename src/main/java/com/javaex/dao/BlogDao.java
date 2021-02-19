package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//블로그 생성
	public void blogInsert(BlogVo blogVo) {
		System.out.println("[BlogDao.insert()] --> "+blogVo);
		
		sqlSession.insert("blog.blogInsert", blogVo);
	}
	
	//블로그 정보 가져오기
	public BlogVo selectBlog(String id) {
		System.out.println("[BlogDao.insert()] --> "+id);
		
		return sqlSession.selectOne("blog.selectBlog", id);
	}

}
