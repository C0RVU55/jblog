package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//블로그 생성 (파라미터 UserService에서 받음)
	public void blogInsert(BlogVo blogVo) {
		System.out.println("[BlogDao.insert()] --> "+blogVo);
		
		sqlSession.insert("blog.blogInsert", blogVo);
	}
	
	//블로그 정보 가져오기
	public BlogVo selectBlog(String id) {
		System.out.println("[BlogDao.selectBlog()] --> "+id);
		
		return sqlSession.selectOne("blog.selectBlog", id);
	}
	
	//블로그 관리1 베이직 - 설정 변경 - 제목
	public void basicUpdate(BlogVo blogVo) {
		System.out.println("[BlogDao.basicUpdate()] --> "+blogVo);
		
		sqlSession.update("blog.basicUpdate", blogVo);
	}

	//블로그 관리1 베이직 - 설정 변경 - 제목, 로고
	public void basicUpdateAll(BlogVo blogVo) {
		System.out.println("[BlogDao.basicUpdateAll()] --> "+blogVo);
		
		sqlSession.update("blog.basicUpdateAll", blogVo);
	}
}
