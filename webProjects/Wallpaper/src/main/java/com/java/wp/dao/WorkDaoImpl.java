package com.java.wp.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.wp.model.Member;
import com.java.wp.model.Work;

// DAO구현에서는 SqlSession객체를 생성
// MyBatis를 Method를 이용하여 XML에 정의된 
@Repository
public class WorkDaoImpl implements WorkDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<Work> getAllWorkList() {
		System.out.println("[WorkDaoImpl][getAllWorkList]");
		return session.selectList("getAllWorkList");
	}
	@Override
	public List<Work> getWorkList(Member member) {
		System.out.println("[WorkDaoImpl][getWorkList]");
		return session.selectList("getWorkList", member);
	}
	@Override
	public int createWork(Work work) {
		System.out.println("[WorkDaoImpl][createWork]");
		return session.insert("createWork", work);
	}
	@Override
	public HashMap<String, Object> getSumList(String emailId) {
		System.out.println("[WorkDaoImpl][getSumList]");
		return session.selectOne("getSumList", emailId);
	}
	@Override
	public Work getWorkList(String workNum) {
		System.out.println("[WorkDaoImpl][getWork]");
		return session.selectOne("getWork", workNum);
	}
	@Override
	public int getWorkViewCount(String workNum) {
		System.out.println("[WorkDaoImpl][getWorkViewCount]");
		return session.selectOne("getWorkViewCount", workNum);
	}
	@Override
	public void updateView(String workNum) {
		System.out.println("[WorkDaoImpl][updateView]");
		session.update("updateView", workNum);
		
	}
	@Override
	public int updateWork(Work work) {
		System.out.println("[WorkDaoImpl][updateWork]");
		return session.update("updateWork", work);
	}
	@Override
	public List<Work> getAllSelectWorkList(HashMap<String, Object> paramMap) {
		System.out.println("[WorkDaoImpl][getAllSelectWorkList]");
		return session.selectList("getAllSelectWorkList", paramMap);
	}
	@Override
	public List<Work> getAllTagSelectWorkList(HashMap<String, Object> paramMap) {
		System.out.println("[WorkDaoImpl][getAllTagSelectWorkList]");
		return session.selectList("getAllTagSelectWorkList", paramMap);
	}
	@Override
	public List<Work> getAllCreatorSelectWorkList(HashMap<String, Object> paramMap) {
		System.out.println("[WorkDaoImpl][getAllCreatorSelectWorkList]");
		return session.selectList("getAllCreatorSelectWorkList", paramMap);
	}
}
