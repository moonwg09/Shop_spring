package com.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.model.KakaoTokenDTO;
import com.shop.model.KakaoUserDTO;
import com.shop.model.MemberVO;
import com.shop.service.KakaoService;
import com.shop.service.MemberService;

@Controller
@RequestMapping("/auth")
public class KakaoController {
	
	@Autowired
    private KakaoService kakaoService; 

    @Autowired
    private MemberService memberService;
    
    @Value("${kakao.client_id}")
    private String clientId;
    
    @Value("${kakao.redirect_uri}")
    private String redirectUri;
	
	@GetMapping("/kakao/login")
    public String kakaoLogin() {
		String kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize"
                + "?client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&response_type=code";
                
        return "redirect:" + kakaoAuthUrl;
    }

	@GetMapping("/kakao/callback")
	public String kakaoCallback(@RequestParam("code") String code, HttpSession session) throws Exception {
		
        KakaoTokenDTO tokenInfo = kakaoService.getAccessToken(code);
        
        KakaoUserDTO kakaoUser = kakaoService.getKakaoUserInfo(tokenInfo.getAccessToken());
        
        MemberVO loginMember = memberService.loginOrRegister(kakaoUser);
        
        session.setAttribute("member", loginMember);
        
        return "redirect:/main";
		
	}
}
