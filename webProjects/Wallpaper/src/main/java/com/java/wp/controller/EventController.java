package com.java.wp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.wp.model.Member;
import com.java.wp.model.Reply;
import com.java.wp.model.Work;
import com.java.wp.service.BuyService;
import com.java.wp.service.FollowshipService;
import com.java.wp.service.LikeService;
import com.java.wp.service.MemberService;
import com.java.wp.service.ReplyService;
import com.java.wp.service.WorkService;

@Controller
public class EventController {
	
	// Controller에서는 Service를 Import;
	@Autowired
	private WorkService ws;
	@Autowired
	private MemberService ms;
	@Autowired
	private BuyService bs;
	@Autowired
	private FollowshipService fs;
	@Autowired
	private LikeService ls;
	@Autowired
	private ReplyService rs;
	
	// work의 전체목록
	@RequestMapping("/main")
	public String mainList(Model model) {
		System.out.println("[EventController][/main]");
		List<Work> works = ws.getAllWorkList();
		model.addAttribute("works", works);
		return "main";
	}
	// 메인 갱신
	@RequestMapping("/refreshMain")
	@ResponseBody
	public List<Work> refreshMain() {
		System.out.println("[EventController][refreshMain]");
		List<Work> works = ws.getAllWorkList();
		return works;
	}
	
	// 세션갱신
	@RequestMapping("/updateSession")
	public void updateSession(HttpSession httpSession) {
		System.out.println("[EventController][updateSession]");
		Member member = (Member) httpSession.getAttribute("member");
		httpSession.setAttribute("member", ms.getMemberInfo(member));

	}
	
	// work 생성
	@RequestMapping("/createWork")
	@ResponseBody
	public int createWork(@RequestParam("workTitle") String workTitle,
						  @RequestParam("workFile") MultipartFile workFile,
						  @RequestParam("workTag") String workTag,
						  @RequestParam("workDesc") String workDesc,
						  @RequestParam("workPrice") int workPrice,
						  @RequestParam("workStatus") String workStatus, Work work, HttpSession httpSession) {
		System.out.println("[EventController][/createWork]");
		Member member = (Member) httpSession.getAttribute("member");
		work.setEmailId(member.getEmailId());
		work.setWorkTitle(workTitle);
		work.setWorkTag(workTag);
		work.setWorkDesc(workDesc);
		work.setWorkPrice(workPrice);
		work.setWorkStatus(workStatus);
		return ws.createWork(work, workFile);
	}
	
	// Member 생성
	@RequestMapping(value="/createMember", method=RequestMethod.POST)
	public String createMember(@RequestParam("profileImg") MultipartFile profile, Member member, Model model) {
		System.out.println("[EventController][/createMember]");
		int createMemberResult = ms.createMember(member, profile);
		model.addAttribute("createMemberResult", createMemberResult);
		return "redirect:main";
	}
	
	// Member확인
	@RequestMapping(value="/checkIdAjax")
	@ResponseBody
	public int checkIdAjax(Member member) {
		System.out.println("[EventController][/checkIdAjax]");
		return ms.checkMember(member);
	}
	
	// userName 확인
	@RequestMapping(value="/checkUnAjax")
	@ResponseBody
	public int checkUnAjax(Member member) {
		System.out.println("[EventController][/cjeckUnAjax]");
		return ms.checkUsername(member);
	}
	
	// Member 로그인 : Client에서 emailId와 pw를 받아온다다
	@RequestMapping(value="/signinMember")
	@ResponseBody
	public int signinMember(Member member, HttpSession httpSession) {
		System.out.println("[EventController][signinMember]");
		Member memberSuccess = ms.signinMember(member);
		int signinSuccess = 0;
		if (memberSuccess != null) {
			signinSuccess = 1;
			httpSession.setAttribute("member", memberSuccess);
		} 
		return signinSuccess;
	}
	
	// 로그인 후, 컨트롤 박스 오픈 
	@RequestMapping(value="/openControlBox")
	@ResponseBody
	public int openControlBox(HttpSession httpSession) {
		System.out.println("[EventController][openControlBox]");
		Member presentMember = (Member) httpSession.getAttribute("member");
		int presentMemberNum = 0;
		if (presentMember != null) {
			presentMemberNum = 1;
		} 
		return presentMemberNum;
	}
	
	// signout
	@RequestMapping(value="/signoutMember")
	@ResponseBody
	public int signoutMember(HttpServletRequest request, HttpSession httpSession) {
		System.out.println("[EventController][signoutMember]");
		httpSession.invalidate();
		int signoutResult = 0;
		if ( request.getSession(false) == null) {
			signoutResult = 1;
		}
		return signoutResult;
	}
	
	// InfoTab, AccountTab open
	@RequestMapping(value="/openInfoTab")
	@ResponseBody
	public Member openInfoTab(HttpSession httpSession) {
		System.out.println("[EventController][openInfoTab]");
		Member member = (Member) httpSession.getAttribute("member");
		return ms.getMemberInfo(member);
	}
	
	// 회원정보 변경
	@RequestMapping(value="/updateMember")
	@ResponseBody
	public int updateMember(Member member) {
		System.out.println("[EventController][updateMember]");
		return ms.updateMember(member);
	}
	
	// Account 충전
	@RequestMapping(value="/updateAccount")
	@ResponseBody
	public int updateAccount(int chargeAmount, HttpSession httpSession) {
		System.out.println("[EventController][updateAccount]");
		Member member = (Member) httpSession.getAttribute("member");
		return ms.updateAccount(member, chargeAmount);
	}
	
	// Creator Tab Open
	@RequestMapping(value="/openCreatorTab")
	@ResponseBody
	public HashMap<String, Object> openCreatorTab(HttpSession httpSession) {
		System.out.println("[EventController][openCreatorTab]");
		HashMap<String, Object> creatorTab = new HashMap<String, Object>();
		
		Member member = (Member) httpSession.getAttribute("member");
		
		String userName = member.getUserName();
		String emailId = member.getEmailId();
		List<Work> workList = ws.getWorkList(member);
		int followerSum = fs.getSum(emailId);
		int replySum = rs.getSum(emailId);
		int likeSum = ls.getSum(emailId);
		int buySum = bs.getSum(emailId);
		
		creatorTab = (HashMap<String, Object>) ws.getSumList(emailId);
		if (creatorTab.get("viewCount") == null) {
			creatorTab.put("viewCount", 0);
		}

		creatorTab.put("followerSum", followerSum);
		creatorTab.put("replySum", replySum);
		creatorTab.put("likeSum", likeSum);
		creatorTab.put("buySum", buySum);
		creatorTab.put("userName", userName);
		creatorTab.put("workList", workList);
		
		return creatorTab;
	}
	
	// open workDetail
	@RequestMapping(value="/openWorkDetail")
	@ResponseBody
	public HashMap<String, Object> openWorkDetail(String workNum, HttpSession httpSession) {
		System.out.println("[EventController][openWorkDetail]");
		HashMap<String, Object> workDetail = new HashMap<String, Object>();
		
		// 뷰수 증가
		ws.updateView(workNum);
		
		Work work = ws.getWork(workNum);
		String emailId = work.getEmailId();
		Member member = ms.getMember(emailId);

		
		List<Reply> reply = rs.getReplyList(workNum);
		int replyCount = rs.getReplyCount(workNum);
		int viewCount = ws.getWorkViewCount(workNum);
		int likeCount = ls.getLikeCount(workNum);
		int buyCount = bs.getBuyCount(workNum);
		
		workDetail.put("detailWork", work);
		workDetail.put("detailMember", member);
		workDetail.put("detailReply", reply);
		workDetail.put("detailReplyCount", replyCount);
		workDetail.put("detailViewCount", viewCount);
		workDetail.put("detailLikeCount", likeCount);
		workDetail.put("detailBuyCount", buyCount);

		Member sessionMember = (Member) httpSession.getAttribute("member");
		if (sessionMember != null ) {
			String sessionEmailId = sessionMember.getEmailId();
			// 로그인 시에만 팔로우, 구매, 좋아요 조회가 가능
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sessionEmailId", sessionEmailId);
			paramMap.put("emailId", emailId);
			paramMap.put("workNum", workNum);
			
			int isFollow = fs.getFollowship(paramMap);
			int isBuy = bs.getBuy(paramMap);
			int isLike = ls.getLike(paramMap);

			workDetail.put("emailId", sessionEmailId);
			workDetail.put("detailFollow", isFollow);
			workDetail.put("detailBuy", isBuy);
			workDetail.put("detailLike", isLike);
		}

		return workDetail;
	}
	
	// createReply
	@RequestMapping(value="/createReply")
	@ResponseBody
	public HashMap<String, Object> createReply(Reply reply, HttpSession httpSession) {
		System.out.println("[EventController][createReply]");
		HashMap<String, Object> resultCreateReply = new HashMap<String, Object>();
		Member member = (Member) httpSession.getAttribute("member");	
		reply.setEmailId(member.getEmailId());
		resultCreateReply.put("member", ms.getMemberInfo(member));
		resultCreateReply.put("successNum", rs.createReply(reply));
		return resultCreateReply;
	}
	
	//updateTab
	@RequestMapping(value="/updateTab")
	@ResponseBody
	public Work updateTab(String workNum) {
		System.out.println("[EventController][updateTab]");
		Work work = ws.getWork(workNum);
		return work;
	}
	
	//updateWork
	@RequestMapping(value="/updateWork")
	@ResponseBody
	public int updateWork(Work work) {
		System.out.println("[EventController][updateWork]");
		return ws.updateWork(work);
	}
	
	// updateFollow
	@RequestMapping(value="/updateFollow")
	@ResponseBody
	public int updateFollow(@RequestParam("followStatus") String followStatus, 
							@RequestParam("workNum") String workNum,
							HttpSession httpSession) {
		System.out.println("[EventController][updateFollow]");
		// 현재 맴버 
		Member sessionMember = (Member) httpSession.getAttribute("member");
		String sessionEmailId = sessionMember.getEmailId();
		// 작가
		Work work = ws.getWork(workNum);
		String emailId = work.getEmailId();
		// DTO
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sessionEmailId", sessionEmailId);
		paramMap.put("emailId", emailId);
		paramMap.put("workNum", workNum);
		// 현재 팔로우 상태
		int result = 0;
		if ("on".equals(followStatus)) {
			result = fs.deleteFollow(paramMap);
		} else {
			result = fs.createFollow(paramMap);
		}
		return result;
	}
	
	// updateLike
	@RequestMapping(value="/updateLike")
	@ResponseBody
	public int updateLike(@RequestParam("likeStatus") String likeStatus, 
						  @RequestParam("workNum") String workNum,
						  HttpSession httpSession) {
		System.out.println("[EventController][updateLike]");
		// 현재 맴버 
		Member sessionMember = (Member) httpSession.getAttribute("member");
		String sessionEmailId = sessionMember.getEmailId();
		// 작가
		Work work = ws.getWork(workNum);
		String emailId = work.getEmailId();
		// DTO
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sessionEmailId", sessionEmailId);
		paramMap.put("emailId", emailId);
		paramMap.put("workNum", workNum);
		// 현재 팔로우 상태
		int result = 0;
		if ("on".equals(likeStatus)) {
			result = ls.deleteLike(paramMap);
		} else {
			result = ls.createLike(paramMap);
		}
		return result;
	}
	// updateBuy
	@RequestMapping(value="/updateBuy")
	@ResponseBody
	public int updateBuy(@RequestParam("workNum") String workNum, HttpSession httpSession) {
		System.out.println("[EventController][updateBuy]");
		int result = 0;
		// 현재 맴버 
		Member sessionMember = (Member) httpSession.getAttribute("member");
		String sessionEmailId = sessionMember.getEmailId();
		// 보유 현금
		int account = sessionMember.getAccount();
		// 작품가격
		Work work = ws.getWork(workNum);
		int workPrice = work.getWorkPrice();
		
		if (account >= workPrice) {
			// DTO
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("sessionEmailId", sessionEmailId);
			paramMap.put("workNum", workNum);	
			paramMap.put("workPrice", workPrice);
			result = bs.updateBuy(paramMap);
			// 계좌차감
			workPrice *= -1;
			ms.updateAccount(sessionMember, workPrice);
		} else {
			result = 0;
		}
		return result;
	}
	
	// openLikeTab
	@RequestMapping(value="/openLikeTab")
	@ResponseBody
	public List<HashMap<String, Object>> openLikeTab(HttpSession httpSession) {
		System.out.println("[EventCotroller][openLikeTab]");
		// 현재 맴버 
		Member sessionMember = (Member) httpSession.getAttribute("member");
		// Like List 구하기
		List<HashMap<String, Object>> likeList = ls.getLikeList(sessionMember);
		return likeList;
	}
	
	// deleteLike
	@RequestMapping(value="/deleteLike")
	@ResponseBody
	public int deleteLike(String workNum, HttpSession httpSession) {
		System.out.println("[EventCotroller][deleteLike]");
		// 현재 맴버 
		Member sessionMember = (Member) httpSession.getAttribute("member");
		String sessionEmailId = sessionMember.getEmailId();
		// DTO
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sessionEmailId", sessionEmailId);
		paramMap.put("workNum", workNum);
		return ls.deleteLike(paramMap);
	}
	
	//openFollowTab
	@RequestMapping(value="/openFollowTab")
	@ResponseBody
	public List<HashMap<String, Object>> openFollowTab(HttpSession httpSession) {
		System.out.println("[EventCotroller][openFollowTab]");
		// 현재 맴버 
		Member sessionMember = (Member) httpSession.getAttribute("member");
		// Follow List 구하기
		List<HashMap<String, Object>> followList = fs.getFollowList(sessionMember);
		return followList;
	}
	
	// deleteFollow
	@RequestMapping(value="/deleteFollow")
	@ResponseBody
	public int deleteFollow(String followee, HttpSession httpSession) {
		System.out.println("[EventCotroller][deleteFollow]");
		// 현재 맴버 
		Member sessionMember = (Member) httpSession.getAttribute("member");
		String sessionEmailId = sessionMember.getEmailId();
		// DTO
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sessionEmailId", sessionEmailId);
		paramMap.put("emailId", followee);
		return fs.deleteFollow(paramMap);
	}
	
	// openGalleryTab
	@RequestMapping(value="/openGalleryTab")
	@ResponseBody
	public List<HashMap<String, Object>> openGalleryTab(HttpSession httpSession) {
		System.out.println("[EventCotroller][openGalleryTab]");
		// 현재 맴버 
		Member sessionMember = (Member) httpSession.getAttribute("member");
		return bs.getBuyList(sessionMember);
	}
	
	// searchWork
	@RequestMapping(value="/search")
	@ResponseBody
	public List<Work> searchForWork(String workTitle, String tagOption, String creatorOption) {
		System.out.println("[EventCotroller][searchForWork]");
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		List<Work> workList = new ArrayList<Work>();
			
		paramMap.put("workTitle", workTitle);
		paramMap.put("tagOption", tagOption);
		paramMap.put("creatorOption", creatorOption);
		
		if ( "on".equals(tagOption)) {
			workList = ws.getAllTagSelectWorkList(paramMap);
		} else if ( "on".equals(creatorOption)) {
			workList = ws.getAllCreatorSelectWorkList(paramMap);
		} else {
			workList = ws.getAllSelectWorkList(paramMap);
		}
		return workList;
	}
}