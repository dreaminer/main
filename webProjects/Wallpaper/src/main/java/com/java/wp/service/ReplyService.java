package com.java.wp.service;

import java.util.List;

import com.java.wp.model.Member;
import com.java.wp.model.Reply;

public interface ReplyService {

	List<Reply> getReplyList(Member member);		// 댓글 리스트 생성
	int getSum(String emailId);						// 작가의 댓글 수 조회
	List<Reply> getReplyList(String workNum);		// 댓글 리스트 조회
	int getReplyCount(String workNum);				// 작품의 댓글 수 조회
	int createReply(Reply reply);					// 댓글 생성

}
