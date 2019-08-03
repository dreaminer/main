package com.java.wp.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.java.wp.model.Member;
import com.java.wp.model.Work;

// work 관련 서비스들의 목록을 나열, 구현은 따로.
public interface WorkService {
	List<Work> getAllWorkList();						// work 목록를 구하는 Service
	List<Work> getWorkList(Member member);				// member work 목록를 구하는 Service
	int createWork(Work work, MultipartFile workFile);	// work 생성
	HashMap<String, Object> getSumList(String emailId);	// 작가의 work 관련 SumList를 구하는 Service
	Work getWork(String workNum);						// work정보 구하기
	int getWorkViewCount(String workNum);				// viewCount 조회
	void updateView(String workNum);					// 조회수 증가
	int updateWork(Work work);							// work 수정
	List<Work> getAllSelectWorkList(HashMap<String, Object> paramMap);	// filter work 목록 구하기
	List<Work> getAllTagSelectWorkList(HashMap<String, Object> paramMap);
	List<Work> getAllCreatorSelectWorkList(HashMap<String, Object> paramMap);
}
