package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CateDao;
import com.javaex.vo.CateVo;

@Service
public class CateService {

	@Autowired
	private CateDao cateDao;
	
	//리스트
	public List<CateVo> cateList(String id) {
		System.out.println("[CateService.cateList()]");
		
		return cateDao.selectCateList(id);
	}
	
	//카테고리 추가
	public CateVo cateAdd(String id, String cateName, String description) {
		System.out.println("[CateService.cateAdd()] --> "+id+" / "+cateName+" / "+description);
		
		CateVo cateVo = new CateVo(id, cateName, description);
		cateDao.cateInsert(cateVo);
		
		//추가한 카테고리 가져오기
		int cateNo = cateVo.getCateNo();
		System.out.println("cateNo --> "+cateNo);
		
		return cateDao.selectCate(cateNo);
	}
}
