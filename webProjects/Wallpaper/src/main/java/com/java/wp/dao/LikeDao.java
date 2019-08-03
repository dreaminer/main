package com.java.wp.dao;

import java.util.HashMap;
import java.util.List;

import com.java.wp.model.Member;

public interface LikeDao {
	List<HashMap<String, Object>> getLikeList(Member sessionMember);	// 좋아요 목록
	int getSum(String emailId);
	int getLikeCount(String workNum);					// 작품의 좋아요 수 구하기
	int getLike(HashMap<String, Object> paramMap);		// 작품의 좋아요 여부
	int createLike(HashMap<String, Object> paramMap);	// 좋아요 생성
	int deleteLike(HashMap<String, Object> paramMap);	// 좋아요 삭제
}
