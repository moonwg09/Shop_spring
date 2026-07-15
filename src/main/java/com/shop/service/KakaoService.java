package com.shop.service;

import com.shop.model.KakaoTokenDTO;
import com.shop.model.KakaoUserDTO;

public interface KakaoService {

	// 인가 코드로 카카오 토큰 발급
    KakaoTokenDTO getAccessToken(String code);
    
    // 토큰으로 카카오 사용자 정보(닉네임) 가져오기
    KakaoUserDTO getKakaoUserInfo(String accessToken);
}
