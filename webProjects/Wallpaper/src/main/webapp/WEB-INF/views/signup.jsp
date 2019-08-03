<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="signup_window" class="signup_background">
    <div class="signup_form_window">
        <img id="cancleSignupWindow" src="resources/img/cancle.png" alt="" onclick="closeSignupWindow()">
        <div class="signup_form_img"></div>
        <div class="signup_form">
            <form id="signupForm" action="createMember" method="post" enctype="multipart/form-data" onsubmit="return signupFormChk()">
                <label for="profile_upload" class="profile"><img id="profileThumbnail" src="resources/img/profile.png" alt=""></label>
                <input name="profileImg" type="file" id="profile_upload" onchange="showThumbnail()" hidden>
                <input id="signupEmail" name="emailId" type="email" placeholder="Input Your Email" required="required" autocomplete="off" onkeyup="checkIdAjax()">
                <input id="inputPw" name="pw" type="password" class="pw" placeholder="Input Your Password" required="required" onkeyup="createPw()">
                <input id="chkPw" type="password" class="pwchk" placeholder="Check Your Password" required="required" onkeyup="checkPw()">
                <input id="signupUn" name="userName" type="text" class="username" placeholder="Make Your Username" required="required" onkeyup="checkUnAjax()">
                <input type="submit" value="Sign Up">
            </form>
        </div>
    </div>
</div>