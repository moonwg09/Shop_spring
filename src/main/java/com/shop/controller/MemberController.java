package com.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.mapper.MemberMapper;
import com.shop.model.MemberVO;
import com.shop.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
		private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
		
		@Autowired
		MemberService memberservice; 
	
		//회원가입 페이지 이동
		@RequestMapping(value = "join", method = RequestMethod.GET)
		public void loginGET() {
			
			logger.info("회원가입 페이지 진입");
			
		}
		
		//로그인 페이지 이동
		@RequestMapping(value = "login", method = RequestMethod.GET)
		public void joinGET() {
			
			logger.info("로그인 페이지 진입");
			
		}
		
		/* 로그인 */
		@RequestMapping(value="login", method=RequestMethod.POST)
		public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
			
			/*
			 * System.out.println("login 메서드 진입"); System.out.println("전달된 데이터 : " +
			 * member);
			 */
			
			HttpSession session = request.getSession();
			MemberVO lvo = memberservice.memberLogin(member);
			
			if(lvo == null) {
				int result = 0;
				rttr.addFlashAttribute("result", result);
				return "redirect:/member/login";
			}
			
			session.setAttribute("member", lvo);
						
			return "redirect:/main;";
		}

}
