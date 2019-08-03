package com.java.wp.dao;

import java.util.HashMap;
import java.util.List;

import com.java.wp.model.Followship;
import com.java.wp.model.Member;

public interface FollowDao {
	List<Followship> getFollowshipList(Member member);

	int getSum(String emailId);					// 한 팔로위의  팔로우 수 구하기

	int getFollowship(HashMap<String, Object> paramMap);	// 팔로우 조회

	int deleteFollow(HashMap<String, Object> paramMap);		// 팔로우 삭제

	int createFollow(HashMap<String, Object> paramMap);		// 팔로우 생성

	List<HashMap<String, Object>> getFollowList(Member sessionMember);	//팔로우 생성
}
