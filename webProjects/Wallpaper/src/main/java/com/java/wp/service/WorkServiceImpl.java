package com.java.wp.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.wp.dao.WorkDao;
import com.java.wp.model.Member;
import com.java.wp.model.Work;


// Service에서는 DAO를 Import
// DAO 객체 생성 후, 해당 DAO의 Method를 실행한다
@Service
public class WorkServiceImpl implements WorkService {
	
	@Autowired
	private WorkDao wd;
	
	@Override
	public List<Work> getAllWorkList() {
		System.out.println("[WorkServiceImpl][getAllWorkList]");
		return wd.getAllWorkList();
	}
	@Override
	public List<Work> getWorkList(Member member) {
		System.out.println("[WorkServiceImpl][getWorkList]");
		return wd.getWorkList(member);
	}
	@Override
	public int createWork(Work work, MultipartFile workFile) {
		System.out.println("[WorkServiceImpl][createWork]");

		// 작업 사진을 처리하는 로직
		// 작업 사진의 저장위치
		String workPath = "C:/Spring/SpringSrc/WallPaper/src/main/webapp/";
		String savefileName = "";
		
		try {
			// File 객체를 새 경로로 생성 한 후, 작업 사인 데이터를 저장
			String fileExtension = FilenameUtils.getExtension(workFile.getOriginalFilename());
			UUID uuid = UUID.randomUUID();
			String randomUUIDString = uuid.toString();
			String dbPath = "resources/img/work/" + randomUUIDString + "." + fileExtension;
			savefileName = workPath + dbPath;
			// 파일 객체 생성 후, 클라이언트로 부터 받은 파일데이터를 저장
			File file = new File(savefileName);
			workFile.transferTo(file);
			
			// 저장이 완료되면 dto에 경로 추가
			work.setWork(dbPath);
			work.setWorkNum(randomUUIDString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wd.createWork(work);
	}
	@Override
	public HashMap<String, Object> getSumList(String emailId) {
		System.out.println("[WorkServiceImpl][getSumList]");
		return wd.getSumList(emailId);
	}
	@Override
	public Work getWork(String workNum) {
		System.out.println("[WorkServiceImpl][getWork]");
		return wd.getWorkList(workNum);
	}
	@Override
	public int getWorkViewCount(String workNum) {
		System.out.println("[WorkServiceImpl][getWorkViewCount]");
		return wd.getWorkViewCount(workNum);
	}
	@Override
	public void updateView(String workNum) {
		System.out.println("[WorkServiceImpl][updateView]");
		wd.updateView(workNum);
	}
	@Override
	public int updateWork(Work work) {
		System.out.println("[WorkserviceImpl][updateWork]");
		return wd.updateWork(work);
	}
	@Override
	public List<Work> getAllSelectWorkList(HashMap<String, Object> paramMap) {
		System.out.println("[WorkServiceImpl][getAllSelectWorkList]");
		return wd.getAllSelectWorkList(paramMap);
	}
	@Override
	public List<Work> getAllTagSelectWorkList(HashMap<String, Object> paramMap) {
		System.out.println("[WorkServiceImpl][getAllTagSelectWorkList]");
		return wd.getAllTagSelectWorkList(paramMap);
	}
	@Override
	public List<Work> getAllCreatorSelectWorkList(HashMap<String, Object> paramMap) {
		System.out.println("[WorkServiceImpl][getAllCreatorSelectWorkList]");
		return wd.getAllCreatorSelectWorkList(paramMap);
	}

}
