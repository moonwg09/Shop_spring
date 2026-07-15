package com.shop.mapper;

import com.shop.model.MemberVO;

public interface MemberMapper {
	
	/* 회원가입 */
	public void memberJoin(MemberVO member);
	
	/* 아이디 중복 검사 */
	public int idCheck(String memberId);
	
	/* 일반 로그인 */
	public MemberVO memberLogin(MemberVO member);
	
	/* 카카오 로그인 A: 카카오 고유 ID로 이미 가입된 사람인지 확인 */
	public MemberVO findMemberById(String memberId);
	
	/* 카카오 로그인 B: 가입 안 된 사람이면 회원가입 */
	public int insertKakaoMember(MemberVO member);
}
