package com.java.wp.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.wp.model.Member;

@Repository
public class BuyDaoImpl implements BuyDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<HashMap<String, Object>> getBuyList(Member member) {
		System.out.println("[BuyDaoImpl][getBuyList]");
		return session.selectList("getBuyList", member);
	}

	@Override
	public int getSum(String emailId) {
		System.out.println("[BuyDaoImpl][getSum]");
		return session.selectOne("getSumBuy", emailId);
	}

	@Override
	public int getBuyCount(String workNum) {
		System.out.println("[BuyDaoImpl][getBuyCount]");
		return session.selectOne("getBuyCount", workNum);
	}

	@Override
	public int getBuy(HashMap<String, Object> paramMap) {
		System.out.println("[BuyDaoImpl][getBuy]");
		return session.selectOne("getBuy", paramMap);
	}

	@Override
	public int updateBuy(HashMap<String, Object> paramMap) {
		System.out.println("[BuyDaoImpl][updateBuy]");
		return session.insert("createBuy", paramMap);
	}

}
