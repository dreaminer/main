package com.java.wp.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.wp.dao.BuyDao;
import com.java.wp.model.Member;

@Service
public class BuyServiceImpl implements BuyService {
	
	@Autowired
	private BuyDao bd;
	
	@Override
	public List<HashMap<String, Object>> getBuyList(Member member) {
		System.out.println("[BuyServiceImpl][getBuyList]");
		return bd.getBuyList(member);
	}

	@Override
	public int getSum(String emailId) {
		System.out.println("[BuyServiceImpl][getSum]");
		return bd.getSum(emailId);
	}

	@Override
	public int getBuyCount(String workNum) {
		System.out.println("[BuyServiceImpl][getBuyCount]");
		return bd.getBuyCount(workNum);
	}

	@Override
	public int getBuy(HashMap<String, Object> paramMap) {
		System.out.println("[BuyServiceImpl][getBuy]");
		return bd.getBuy(paramMap);
	}

	@Override
	public int updateBuy(HashMap<String, Object> paramMap) {
		System.out.println("[BuyServiceImpl][updateBuy]");
		return bd.updateBuy(paramMap);
	}

}
