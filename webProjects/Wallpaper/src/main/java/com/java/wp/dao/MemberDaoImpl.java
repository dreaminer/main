package com.java.wp.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.wp.model.Member;

//DAO구현에서는 SqlSession객체를 생성
//MyBatis를 Method를 이용하여 XML에 정의된 
@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public int createMember(Member member) {
		System.out.println("[MemberDaoImpl][createMember]");
		System.out.println("[Member][profitSum]: " +member.getProfitSum());
		System.out.println("[Member][account]: " +member.getAccount());
		System.out.println("[Member][grade]: " +member.getGrade());
		System.out.println("[Member][profile]: " +member.getProfile());
		System.out.println("[Member][pw]: " +member.getPw());
		System.out.println("[Member][badge]: " +member.getBadge());
		System.out.println("[Member][userName]: " +member.getUserName());
		System.out.println("[Member][emailId]: " +member.getEmailId());
		System.out.println("[Member][createDate]: " +member.getCreateDate());

		return session.insert("createMember", member);
	}
	@Override
	public Member signinMember(Member member) {
		System.out.println("[MemberDaoImpl][signinMember]");
		return session.selectOne("signinMember", member);
	}
	
	@Override
	public int checkMember(Member member) {
		System.out.println("[MemberDaoImpl][checkMember]");
		return session.selectOne("checkMember", member);
	}
	@Override 
	public int checkUsername(Member member) {
		System.out.println("[MemberDaoImpl][checkUsername]");
		return session.selectOne("checkUsername", member);
	}
	@Override
	public int updateMember(Member member) {
		System.out.println("[MemberDaoImpl][updateMember]");
		return session.update("updateMember", member);
	}
	@Override
	public Member getMemberInfo(Member member) {
		System.out.println("[MemberDaoImpl][getMemberInfo]");
		return session.selectOne("getMemberInfo", member);
	}
	@Override
	public int updateAccount(Member member) {
		System.out.println("[MemberDaoImpl][updateAccount]");
		return session.update("updateAccount", member);
	}
	@Override
	public Member getMember(String emailId) {
		System.out.println("[MemberDaoImpl][getMember]");
		return session.selectOne("getMember", emailId);
	}

}
