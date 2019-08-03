package com.java.wp.dao;

import java.util.HashMap;
import java.util.List;

import com.java.wp.model.Member;
import com.java.wp.model.Work;

// Service에서 연결할 DAO들의 목록, 구현은 따로
public interface WorkDao {
	List<Work> getAllWorkList();						// 전체 work 목록을 얻는 것
	List<Work> getWorkList(Member member);				// member의 work 목록을 얻는 것
	int createWork(Work work);							// work를 생성
	HashMap<String, Object> getSumList(String emailId);	// 작가의 work관련 데이터 합 불러오기
	Work getWorkList(String workNum);					// work 정보 구하기
	int getWorkViewCount(String workNum);				// workView 조회
	void updateView(String workNum);					// 조회 수 증가
	int updateWork(Work work);							// 작품 수정
	List<Work> getAllSelectWorkList(HashMap<String, Object> paramMap);	// filter 작품 목록
	List<Work> getAllTagSelectWorkList(HashMap<String, Object> paramMap);
	List<Work> getAllCreatorSelectWorkList(HashMap<String, Object> paramMap);
}
