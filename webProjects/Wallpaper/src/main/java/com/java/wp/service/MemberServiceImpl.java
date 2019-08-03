package com.java.wp.service;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.wp.dao.MemberDao;
import com.java.wp.model.Member;

//Service에서는 DAO를 Import
//DAO 객체 생성 후, 해당 DAO의 Method를 실행한다
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao md;
	
	@Override
	public int createMember(Member member, MultipartFile profile) {
		System.out.println("[MemberServiceImpl][createMember]");
		// 프로필 사진을 처리하는 로직
		// 프로필 사진 저장 위치
		String profilePath = "C:/Spring/SpringSrc/WallPaper/src/main/webapp/";
		String savefileName = "";
		
		if (profile.isEmpty()) {
			member.setProfile("resources/img/profile/default_profile.jpg");
		} else {
			System.out.println("[MemberServiceImpl][createMember][getOriginalFileName] : "+profile.getOriginalFilename());
			try {
				// File 객체를 새 경로로 생성한 후, 프로필 사진 데이터를 저장
				String fileExtension = FilenameUtils.getExtension(profile.getOriginalFilename());
				String dbPath = "resources/img/profile/" + member.getEmailId() + "_profile." + fileExtension;
				savefileName = profilePath + dbPath;
				File file = new File(savefileName);
				profile.transferTo(file);

				// 저장이 완료되면 dto에 경로 추가
				member.setProfile(dbPath);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}

		return md.createMember(member);
	}
	
	@Override
	public Member signinMember(Member member) {
		System.out.println("[MemberServiceImpl][signinMember]");
		return md.signinMember(member);	
	}
	
	@Override
	public int checkMember(Member member) {
		System.out.println("[MemberServiceImpl][checkMember]");
		return md.checkMember(member);
	}
	
	@Override
	public int checkUsername(Member member) {
		System.out.println("[MemberServiceImpl][checkUsername]");
		return md.checkUsername(member);
	}

	@Override
	public int updateMember(Member member) {
		System.out.println("[MemberServiceImpl][updateMember]");
		return md.updateMember(member);
	}

	@Override
	public Member getMemberInfo(Member member) {
		System.out.println("[MemberServiceImpl][getMemberInfo]");
		return md.getMemberInfo(member);
	}

	@Override
	public int updateAccount(Member member, int chargeAmount) {
		System.out.println("[MemberServiceImpl][updateAccount]");
		int originalAccount = member.getAccount();
		member.setAccount(originalAccount + chargeAmount); 
		return md.updateAccount(member);
	}

	@Override
	public Member getMember(String emailId) {
		System.out.println("[MemberServiceImpl][getMember]");
		return md.getMember(emailId);
	}

}
