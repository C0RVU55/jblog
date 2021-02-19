package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//로그인폼
	@RequestMapping(value="/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("[userController.loginForm()]");
		
		return "user/loginForm";
	}
	
	//회원가입폼
	@RequestMapping(value="/joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("[userController.loginForm()]");
		
		return "user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value="/join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo uVo) {
		System.out.println("[userController.join()] --> "+uVo);
		
		userService.join(uVo);
		
		return "user/joinSuccess";
	}
	
	//로그인
	@RequestMapping(value="/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo uVo, HttpSession session) {
		System.out.println("[userController.login()] --> "+uVo);
		
		UserVo authUser = userService.login(uVo);

		//로그인 실패
		if(authUser == null) {
			return "redirect:/user/loginForm?result=0";
		} 
		
		//로그인 성공
		session.setAttribute("authUser", authUser);
		
		return "main/index";
	}
	
	//로그아웃
	@RequestMapping(value="/logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("[userController.logout()]");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	
}
