package com.java.wp.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.wp.model.Followship;
import com.java.wp.model.Member;

@Repository
public class FollowDaoImpl implements FollowDao {
	
	@Autowired
	private SqlSessionTemplate session;
		
	@Override
	public List<Followship> getFollowshipList(Member member) {
		System.out.println("[FollowDaoImpl][getFollowshipList]");
		return session.selectList("getFollowshipList", member);
	}

	@Override
	public int getSum(String emailId) {
		System.out.println("[FollowDaoImpl][getSum]");
		return session.selectOne("getFollowerSum", emailId);
	}

	@Override
	public int getFollowship(HashMap<String, Object> paramMap) {
		System.out.println("[FollowDaoImpl][getFollowship]");
		return session.selectOne("getFollowship", paramMap);
	}

	@Override
	public int deleteFollow(HashMap<String, Object> paramMap) {
		System.out.println("[FollowDaoImpl][deleteFollow]");
		return session.delete("deleteFollow", paramMap);
	}

	@Override
	public int createFollow(HashMap<String, Object> paramMap) {
		System.out.println("[FollowDaoImpl][createFollow]");
		return session.insert("createFollow", paramMap);
		
	}

	@Override
	public List<HashMap<String, Object>> getFollowList(Member sessionMember) {
		System.out.println("[FollowDaoImpl][getFollowList]");
		return session.selectList("getFollowList", sessionMember);
	}

}
