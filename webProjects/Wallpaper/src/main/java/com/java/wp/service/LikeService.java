package com.java.wp.service;

import java.util.HashMap;
import java.util.List;

import com.java.wp.model.Member;

public interface LikeService {

	List<HashMap<String, Object>> getLikeList(Member sessionMember);	// 좋아요 목록 구하기

	int getSum(String emailId);							// 작가의 좋아요 수 조회

	int getLikeCount(String workNum);					// 작품의 좋아요 수 구하기

	int getLike(HashMap<String, Object> paramMap);		// 작품의 좋아요 여부

	int deleteLike(HashMap<String, Object> paramMap);	// 좋아요 취소

	int createLike(HashMap<String, Object> paramMap);	// 좋아요 생성

}
