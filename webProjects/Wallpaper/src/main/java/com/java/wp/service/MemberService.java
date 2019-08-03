package com.java.wp.service;

import org.springframework.web.multipart.MultipartFile;

import com.java.wp.model.Member;

//work 관련 서비스들의 목록을 나열, 구현은 따로.
public interface MemberService {
	// member 생성 서비스
	int createMember(Member member, MultipartFile profile);
	// signin 서비스
	Member signinMember(Member member);
	// member 확인 서비스
	int checkMember(Member member);
	// userName 확인 서비스
	int checkUsername(Member member);
	// member 정보 변경 서비스
	int updateMember(Member member);
	// member 정보 조회 서비스
	Member getMemberInfo(Member member);
	Member getMember(String emailId);
	// account 충전 서비스
	int updateAccount(Member member, int chargeAmount);
}
