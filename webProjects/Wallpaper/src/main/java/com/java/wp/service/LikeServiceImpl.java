package com.java.wp.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.wp.dao.LikeDao;
import com.java.wp.model.Member;

@Service
public class LikeServiceImpl implements LikeService {
	
	@Autowired
	private LikeDao ld;
	
	@Override
	public List<HashMap<String, Object>> getLikeList(Member sessionMember) {
		System.out.println("[LikeServiceImpl][getLikeList]");
		return ld.getLikeList(sessionMember);
	}

	@Override
	public int getSum(String emailId) {
		System.out.println("[LikeServiceImpl][getSum]");
		return ld.getSum(emailId);
	}

	@Override
	public int getLikeCount(String workNum) {
		System.out.println("[LikeServiceImpl][getLikeCount]");
		return ld.getLikeCount(workNum);
	}

	@Override
	public int getLike(HashMap<String, Object> paramMap) {
		System.out.println("[LikeServiceImpl][getLike]");
		return ld.getLike(paramMap);
	}

	@Override
	public int deleteLike(HashMap<String, Object> paramMap) {
	System.out.println("[LikeServiceImpl][deleteLike]");
		return ld.deleteLike(paramMap);
	}

	@Override
	public int createLike(HashMap<String, Object> paramMap) {
		System.out.println("[LikeServiceImpl][createLike]");
		return ld.createLike(paramMap);
	}

}
