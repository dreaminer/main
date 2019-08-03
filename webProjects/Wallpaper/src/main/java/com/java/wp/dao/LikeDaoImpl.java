package com.java.wp.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.wp.model.Member;

@Repository
public class LikeDaoImpl implements LikeDao {

	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<HashMap<String, Object>> getLikeList(Member sessionMember) {
		System.out.println("[LikeDaoImpl][getLikeList]");
		return session.selectList("getLikeList", sessionMember);
	}

	@Override
	public int getSum(String emailId) {
		System.out.println("[LikeDaoImpl][getSum]");
		return session.selectOne("getSumLike", emailId);
	}

	@Override
	public int getLikeCount(String workNum) {
		System.out.println("[LikeDaoImpl][getLikeCount]");
		return session.selectOne("getLikeCount", workNum);
	}

	@Override
	public int getLike(HashMap<String, Object> paramMap) {
		System.out.println("[LikeDaoImpl][getLike]");
		return session.selectOne("getLike", paramMap);
	}

	@Override
	public int createLike(HashMap<String, Object> paramMap) {
		System.out.println("[LikeDaoImpl][createLike]");
		return session.insert("createLike", paramMap);
	}

	@Override
	public int deleteLike(HashMap<String, Object> paramMap) {
		System.out.println("[LikeDaoImpl][deleteLike]");
		return session.delete("deleteLike", paramMap);
	}
}
