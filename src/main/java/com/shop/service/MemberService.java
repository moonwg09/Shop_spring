package com.shop.service;

import com.shop.model.KakaoUserDTO;
import com.shop.model.MemberVO;

public interface MemberService {

	/* 회원가입 */
	public void memberJoin(MemberVO member) throws Exception;
	
	/* 아이디 중복 검사 */
	public int idCheck(String memberId) throws Exception;
		
	/* 로그인 */
	public MemberVO memberLogin(MemberVO member) throws Exception;
	
	/* 카카오 로그인, 자동 회원가입 통합 로직 */
    public MemberVO loginOrRegister(KakaoUserDTO kakaoUser) throws Exception;
}
