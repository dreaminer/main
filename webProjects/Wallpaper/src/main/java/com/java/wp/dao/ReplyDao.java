package com.java.wp.dao;

import java.util.List;

import com.java.wp.model.Member;
import com.java.wp.model.Reply;

public interface ReplyDao {
	List<Reply> getReplyList(Member member);

	int getSum(String emailId);					// 작가의 reply 개수 조회

	List<Reply> getReplyList(String workNum);	// reply 조회

	int getReplyCount(String workNum);			// 작품의 reply 개수 조회

	int createReply(Reply reply);				// 댓글 생성
}
