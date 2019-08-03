<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<div class="tabContent" id="infoTabContent">
    <div id="infoTabContainer">
    	<div id="infoTabForm">
			<img id="infoTabProfile" class="user_profile" src="" alt="">
			<div class="user_email"><span id="infoTabEmailId"></span></div>
			<div class="user_createDate"><span id="infoTabcreateDate"></span></div>
			<div class="badge">
			    <span id="infoTabBadge"></span>
			</div>
			<div class="user_nickname"><input type="text" id="infoTabUsername" value="" required></div>
			<div class="pw">
			    <input id="originalPw" type="password" value="" onclick="openUpdatePwTab()" readonly>
			    <div id="changePwBox">
			        <div class="pwchange"><input id="changePw" type="password" placeholder="Change Your Password"></div>
			        <div class="pwcheck"><input id="changePwChk" type="password" placeholder="Check Your Password"></div>
			    </div>
			</div>
			<button id="changePwButton" onclick="updateMember()">수정</button>
		</div>
    </div>
</div>