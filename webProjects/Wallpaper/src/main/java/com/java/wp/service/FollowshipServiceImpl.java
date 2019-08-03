package com.java.wp.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.wp.dao.FollowDao;
import com.java.wp.model.Followship;
import com.java.wp.model.Member;

@Service
public class FollowshipServiceImpl implements FollowshipService {
	
	@Autowired
	private FollowDao fd;
	
	@Override
	public List<Followship> getFollowshipList(Member member) {
		System.out.println("[FollowshipServiceImpl][getFollowshipList]");
		return fd.getFollowshipList(member);
	}

	@Override
	public int getSum(String emailId) {
		System.out.println("[FollowshipServiceImpl][getSum]");
		return fd.getSum(emailId);
	}

	@Override
	public int getFollowship(HashMap<String, Object> paramMap) {
		System.out.println("[FollowshipServiceImpl][getFollowship]");
		return fd.getFollowship(paramMap);
	}

	@Override
	public int deleteFollow(HashMap<String, Object> paramMap) {
		System.out.println("[FollowshipServiceImpl][deleteFollow]");
		return fd.deleteFollow(paramMap);
	}

	@Override
	public int createFollow(HashMap<String, Object> paramMap) {
		System.out.println("[FollowshipServiceImpl][createFollow]");
		return fd.createFollow(paramMap);
	}

	@Override
	public List<HashMap<String, Object>> getFollowList(Member sessionMember) {
		System.out.println("[FollowshipServiceImpl][getFollowList]");
		return fd.getFollowList(sessionMember);
	}

}
