package com.shop.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTests {

	@Autowired
	private MemberMapper membermapper;			//MemberMapper.java 인터페이스 의존성 주입

	/* 로그인 쿼리 mapper 메서드 테스트 */
	@Test
	public void memberLogin() throws Exception{
		
		MemberVO member = new MemberVO();  // MemberVO 변수 선언 및 초기화 
		
		member.setMemberId("admin23");
		member.setMemberPw("admin");
		
		membermapper.memberLogin(member);
		System.out.println("결과 값 : " + membermapper.memberLogin(member));
		
	}
	
}
