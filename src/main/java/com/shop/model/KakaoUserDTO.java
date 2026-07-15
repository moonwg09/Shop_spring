package com.shop.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoUserDTO {

	private Long id; // 카카오가 주는 고유 회원번호
    private String nickname;
    
    public static KakaoUserDTO fromJson(Map<String, Object> attributes) {
        KakaoUserDTO dto = new KakaoUserDTO();
        dto.setId((Long) attributes.get("id"));
        
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        if (kakaoAccount != null) {
            
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            if (profile != null) {
                dto.setNickname((String) profile.get("nickname"));
            }
        }
        return dto;
    }
}
