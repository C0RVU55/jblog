package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.service.CateService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CateService cateService;
	
	/*
	[전체미완]완료한 부분: jblog메인, 회원가입(항목체크X), 로그인/로그아웃, 내블로그관리-기본설정변경
	
	***미완성 목록***
	3.내블로그 관리 카테고리(ajax) 추가, 글작성폼
	4.메인페이지
	5.회원가입 체크 항목
	*/
	
	//블로그 메인
	@RequestMapping(value= "{id}", method= {RequestMethod.GET, RequestMethod.POST}) //{패스변수}
	public String blogMain(@PathVariable("id") String id, Model model) {
		System.out.println("[BlogController.blogMain()]");
		
		//블로그 정보 select 해오기
		BlogVo blogVo = blogService.blogInfo(id);
		System.out.println("[BlogController.blogMain()] --> "+blogVo);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-main";
	}
	
	//블로그 관리1 베이직
	@RequestMapping(value= "{id}/admin/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminBasic(@PathVariable("id") String id, Model model, HttpSession session) {
		System.out.println("[BlogController.adminBasic()] --> ");
		
		//세션 넣어서 본인 아니면 안 뜨게 하기(userNo 안 쓸 건데 넣는 게 맞는지)
		int userNo = ((UserVo)session.getAttribute("authUser")).getUserNo();
		
		//블로그 정보 select 해오기
		BlogVo blogVo = blogService.blogInfo(id);
		System.out.println("[BlogController.adminBasic()] --> "+blogVo);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/admin/blog-admin-basic";
	}
	
	//블로그 관리1 베이직 - 설정 변경
	@RequestMapping(value= "{id}/admin/basic/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminBasicModify(@PathVariable("id") String id,
								@RequestParam("blogTitle") String blogTitle,
								@RequestParam(value="file", required=false) MultipartFile file) {
		System.out.println("[BlogController.adminBasicModify()] --> "+id+" / "+blogTitle+" / "+file);
		
		blogService.basicModify(id, blogTitle, file);
	
		return "redirect:/{id}/admin/basic";
	}
	
	//블로그 관리2 카테고리
	@RequestMapping(value= "{id}/admin/category", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminCate(@PathVariable("id") String id, HttpSession session, Model model) {
		System.out.println("[BlogController.adminCate()]");
		
		//세션 넣어서 본인 아니면 안 뜨게 하기(userNo 안 쓸 건데 넣는 게 맞는지)
		int userNo = ((UserVo)session.getAttribute("authUser")).getUserNo();
		
		//블로그 정보
		BlogVo blogVo = blogService.blogInfo(id);
		System.out.println("[BlogController.adminBasic()] --> "+blogVo);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/admin/blog-admin-cate";
	}
	
	//블로그 관리2 카테고리(ajax) 리스트
	@ResponseBody
	@RequestMapping(value= "/admin/cateList", method= {RequestMethod.GET, RequestMethod.POST})
	public List<CateVo> adminCate(@RequestParam("id") String id) { 
		System.out.println("[BlogController.adminCateList()]");
		
		return cateService.cateList(id);
	}

	//블로그 관리2 카테고리 추가
	@ResponseBody
	@RequestMapping(value= "/admin/cateAdd", method= {RequestMethod.GET, RequestMethod.POST})
	public CateVo cateAdd(@RequestParam("id") String id, 
						@RequestParam("cateName") String cateName,
						@RequestParam("description") String description) {
		System.out.println("[BlogController.adminCate()] --> "+id+" / "+cateName+" / "+description);
		
		CateVo cateVo = cateService.cateAdd(id, cateName, description);
		System.out.println(cateVo.toString());
		
		return cateVo;
	}
	
	//블로그 관리2 카테고리 삭제
	//admin/category/remove
	
}
