package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.MemberMapper;
import com.shop.model.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper membermapper;
	
	/* ·Î±×ÀÎ */
	@Override
	public MemberVO memberLogin(MemberVO member) throws Exception {
		return membermapper.memberLogin(member);
	}
}
