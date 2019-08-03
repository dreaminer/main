package com.java.wp.dao;

import com.java.wp.model.Member;

public interface MemberDao {
	// Service에서 연결할 DAO들의 목록, 구현은 따로
	int createMember(Member member);			// Member 생성
	Member signinMember(Member member);		// Member 로그인
	int checkMember(Member member);			// Member 유효확인
	int checkUsername(Member member);		// userName 유효확인
	int updateMember(Member member);			// Member 정보 변경
	Member getMemberInfo(Member member);		// Member 정보 조회
	Member getMember(String emailId);
	int updateAccount(Member member);		// Account 충전
}
