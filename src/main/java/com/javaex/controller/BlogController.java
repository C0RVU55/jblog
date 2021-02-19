package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BlogService;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value= "{id}", method= {RequestMethod.GET, RequestMethod.POST}) //{패스변수}
	public String blogMain(@PathVariable("id") String id) {
		System.out.println("[BlogController.blogMain()]");
		
		return "blog/blog-main";
	}
}
