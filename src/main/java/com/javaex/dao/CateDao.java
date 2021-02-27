package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CateVo;

@Repository
public class CateDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//카테고리 리스트
	public List<CateVo> selectCateList(String id) {
		System.out.println("[CateDao.selectCateList()]");
		
		return sqlSession.selectList("category.selectCateList", id);
	}
	
	//카테고리 입력
	public void cateInsert(CateVo cateVo) {
		System.out.println("[CateDao.cateInsert()] --> "+cateVo);
		
		sqlSession.insert("category.cateInsert", cateVo);
	}
	
	//카테고리 가져오기
	public CateVo selectCate(int cateNo) {
		System.out.println("[CateDao.selectCate()] --> "+cateNo);
		
		return sqlSession.selectOne("category.selectCate", cateNo);
	}

}
