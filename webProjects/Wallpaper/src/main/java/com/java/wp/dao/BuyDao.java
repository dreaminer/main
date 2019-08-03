package com.java.wp.dao;

import java.util.HashMap;
import java.util.List;

import com.java.wp.model.Member;

public interface BuyDao {
	// Service에서 연결할 DAO들의 목록, 구현은 따로
	List<HashMap<String, Object>> getBuyList(Member member);// 구매목록 조회
	int getSum(String emailId);								// 작가작품을 구매한 횟수 조회
	int getBuyCount(String workNum);						// 작품의 구매횟수 조회
	int getBuy(HashMap<String, Object> paramMap);			// 작품 구매여부 조회
	int updateBuy(HashMap<String, Object> paramMap);		// 구매
}	
