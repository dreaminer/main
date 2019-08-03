package com.java.wp.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.wp.model.Member;
import com.java.wp.model.Reply;

@Repository
public class ReplyDaoImpl implements ReplyDao {

	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<Reply> getReplyList(Member member) {
		System.out.println("[ReplyDaoImpl][getReplyList]");
		return session.selectList("getReplyList", member);
	}

	@Override
	public int getSum(String emailId) {
		System.out.println("[ReplyDaoImpl][getSum]");
		return session.selectOne("getSumReply", emailId);
	}

	@Override
	public List<Reply> getReplyList(String workNum) {
		System.out.println("[ReplyDaoImpl][getReplyList]");
		return session.selectList("getReply", workNum);
	}

	@Override
	public int getReplyCount(String workNum) {
		System.out.println("[ReplyDaoImpl][getReplyCount]");
		return session.selectOne("getReplyCount", workNum);
	}

	@Override
	public int createReply(Reply reply) {
		System.out.println("[ReplyDaoImpl][createReply]");
		return session.insert("createReply", reply);
	}

}
