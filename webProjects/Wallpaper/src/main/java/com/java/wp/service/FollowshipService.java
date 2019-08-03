package com.java.wp.service;

import java.util.HashMap;
import java.util.List;

import com.java.wp.model.Followship;
import com.java.wp.model.Member;

public interface FollowshipService {

	List<Followship> getFollowshipList(Member member);		// 팔로우 구하는 서비스

	int getSum(String emailId);								// 한 팔로위의 총 팔로우 합 구하는 서비스

	int getFollowship(HashMap<String, Object> paramMap); 	// 팔로우 유무 조회

	int deleteFollow(HashMap<String, Object> paramMap);		// 팔로우 삭제

	int createFollow(HashMap<String, Object> paramMap);		// 팔로우 추가

	List<HashMap<String, Object>> getFollowList(Member sessionMember); // 팔로우 목록 구하기

}
