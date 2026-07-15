package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.MemberMapper;
import com.shop.model.KakaoUserDTO;
import com.shop.model.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper membermapper;
	
	@Override
	public void memberJoin(MemberVO member) throws Exception {
		
		membermapper.memberJoin(member);
		
	}
	
	@Override
    public int idCheck(String memberId) throws Exception {
        // 매퍼의 idCheck 메서드를 호출해서 DB 결과를 컨트롤러로 토스합니다.
        return membermapper.idCheck(memberId);
    }
	
	/* 로그인 */
	@Override
	public MemberVO memberLogin(MemberVO member) throws Exception {
		return membermapper.memberLogin(member);
	}
	
	/* 카카오 로그인, 자동 회원가입 통합 로직 */
	public MemberVO loginOrRegister(KakaoUserDTO kakaoUser) throws Exception {
		String kakaoUniqueId = "kakao_" + kakaoUser.getId();
		MemberVO existingMember = membermapper.findMemberById(kakaoUniqueId);
		
		if(existingMember != null) {
			return existingMember;
		} else {
			MemberVO newMember = new MemberVO();
			
			newMember.setMemberId(kakaoUniqueId);
			newMember.setMemberName(kakaoUser.getNickname());
			
			// NOT NULL 제약조건 우회를 위한 임시 값 세팅
			newMember.setMemberMail(kakaoUniqueId + "@kakao.com"); // 가상 이메일
            newMember.setMemberAddr1("카카오 로그인 회원");          // 주소1 기본값
            newMember.setMemberAddr2(" ");                          // 주소2 공백
            newMember.setMemberAddr3(" ");                          // 주소3 공백
            
            membermapper.insertKakaoMember(newMember);
            
            return membermapper.findMemberById(kakaoUniqueId);
		}
	}
}
