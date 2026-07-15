package com.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.MemberVO;
import com.shop.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
		private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
		
		@Autowired
		private MemberService memberservice; 
	
		/* 회원가입 페이지 이동 */
		@RequestMapping(value = "join", method = RequestMethod.GET)
		public void loginGET() {
			
			logger.info("회원가입 페이지 진입");
		}
		
		@RequestMapping(value="/join", method=RequestMethod.POST)
		public String joinPOST(MemberVO member) throws Exception {
			
			logger.info("join 진입");
			System.out.println("화면에서 넘어온 아이디:" + member.getMemberId());
			
			
			memberservice.memberJoin(member);
			
			logger.info("join service 성공");
			
			return "redirect:/main";
			
		}
		
		/* 아이디 중복 검사 컨트롤러 */
		@RequestMapping(value = "/memberIdChk", method = RequestMethod.POST)
		@ResponseBody
		public String memberIdChkPOST(String memberId) throws Exception {
		    int result = memberservice.idCheck(memberId);
		    
		    if(result != 0) {
		        return "fail"; // 중복 아이디 존재
		    } else {
		        return "success"; // 가입 가능
		    }
		}
		
		/* 로그인 페이지 이동 */
		@RequestMapping(value = "login", method = RequestMethod.GET)
		public void joinGET() {
			
			logger.info("로그인 페이지 진입");
			
		}
		
		/* 로그인 */
		@RequestMapping(value="login", method=RequestMethod.POST)
		public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
			
			HttpSession session = request.getSession();
			MemberVO lvo = memberservice.memberLogin(member);
			
			if(lvo == null) {
				int result = 0;
				rttr.addFlashAttribute("result", result);
				return "redirect:/member/login";
			}
			
			session.setAttribute("member", lvo);
						
			return "redirect:/main";
		}

		/* 로그아웃 */
		@RequestMapping(value = "logout", method = RequestMethod.GET)
		public String logoutGET(HttpServletRequest request) throws Exception {

		    HttpSession session = request.getSession();
		    
		    session.invalidate();
		    
		    return "redirect:/main";
		}
}
