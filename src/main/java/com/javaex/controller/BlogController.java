package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	/*
	1.로그인 다이어그램
	2.내블로그 관리	베이직, 카테고리, 글작성
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
	
	//내블로그 관리
	
}
