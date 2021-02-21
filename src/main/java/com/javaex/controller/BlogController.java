package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	/*
	1.로그인 다이어그램
	2.메인페이지 회색선 없애기
	3.내블로그 관리 카테고리(ajax), 글작성
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
	
	//블로그 관리2 카테고리(ajax)
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
	
	//${blogVo.id}/admin/category/add 서비스에서 카테고리dao에 인서트 
	@RequestMapping(value= "{id}/admin/category/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String cateAdd(@PathVariable("id") String id, 
							@RequestParam("cateName") String cateName,
							@RequestParam("description") String description) {
		System.out.println("[BlogController.adminCate()] --> "+cateName+" / "+description);
		
		
		return "";
	}
	
	//admin/category/remove
	
}
