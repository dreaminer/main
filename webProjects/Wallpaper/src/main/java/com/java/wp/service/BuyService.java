package com.java.wp.service;

import java.util.HashMap;
import java.util.List;


import com.java.wp.model.Member;

//work 관련 서비스들의 목록을 나열, 구현은 따로.
public interface BuyService {
	List<HashMap<String, Object>> getBuyList(Member member); 				// 구매목록 확인

	int getSum(String emailId);							// 작가의 구매수 조회

	int getBuyCount(String workNum);					// 작품의 구매수 조회

	int getBuy(HashMap<String, Object> paramMap);		// 해당 작품 구매여부

	int updateBuy(HashMap<String, Object> paramMap);	// 구매
}
