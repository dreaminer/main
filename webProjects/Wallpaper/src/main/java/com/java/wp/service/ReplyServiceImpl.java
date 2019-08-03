package com.java.wp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.wp.dao.ReplyDao;
import com.java.wp.model.Member;
import com.java.wp.model.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDao rd;
	
	@Override
	public List<Reply> getReplyList(Member member) {
		System.out.println("[ReplyServiceImpl][getReplyList]");
		return rd.getReplyList(member);
	}
	@Override
	public List<Reply> getReplyList(String workNum) {
		System.out.println("[ReplyServiceImpl][getReplyList]");
		return rd.getReplyList(workNum);
	}
	@Override
	public int getSum(String emailId) {
		System.out.println("[ReplyServiceImpl][getSum]");
		return rd.getSum(emailId);
	}
	@Override
	public int getReplyCount(String workNum) {
		System.out.println("[ReplyServiceImpl][getReplyCount]");
		return rd.getReplyCount(workNum);
	}
	@Override
	public int createReply(Reply reply) {
		System.out.println("[ReplyServiceImpl][createReply]");
		UUID uuid = UUID.randomUUID();
		String reNum = uuid.toString();
		reply.setReNum(reNum);
		return rd.createReply(reply);
	}


}
