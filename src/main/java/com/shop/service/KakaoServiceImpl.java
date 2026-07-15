package com.shop.service;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import org.springframework.http.*;

import com.shop.model.KakaoTokenDTO;
import com.shop.model.KakaoUserDTO;

@Service
public class KakaoServiceImpl implements KakaoService {
	
	@Value("${kakao.client_id}")
	private String clientId;
	
	@Value("${kakao.redirect_uri}")
    private String redirectUri; 

	@Override
	public KakaoTokenDTO getAccessToken(String code) {
		
		System.out.println("서비스 단에서 읽어온 clientId: " + clientId);
		
		String tokenUrl = "https://kauth.kakao.com/oauth/token"; // 인증 서버 주소
        RestTemplate restTemplate = new RestTemplate();

        // HTTP Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // HTTP Body 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<KakaoTokenDTO> response = restTemplate.postForEntity(tokenUrl, request, KakaoTokenDTO.class);
        
        return response.getBody();
	}

	@Override
	public KakaoUserDTO getKakaoUserInfo(String accessToken) {
		String userInfoUrl = "https://kapi.kakao.com/v2/user/me"; // API 서버 주소
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(userInfoUrl, HttpMethod.POST, request, Map.class);
        
        return KakaoUserDTO.fromJson(response.getBody());
	}

}
