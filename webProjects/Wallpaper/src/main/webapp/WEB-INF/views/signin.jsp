<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div id="signin_window" class="signin_background">
    <div class="signin_form_window">
        <p>Beyond</p>
        <p>The</p>
        <p>Photographs</p>
        <img id="cancleSigninWindow" src="resources/img/cancle.png" alt="" onclick="closeSigninWindow()">
        <div class="signin_form_img"></div>
        <div class="signin_form">
        	<div id="signinFailed"></div>
            <input id="signinEmail" type="email" placeholder="Input Your Email" maxlength="50" required>
            <input id="signinPw" type="password" placeholder="Input Your Password"  maxlength="50" required>
            <button id="signinButton" type="button" onclick="signinPro()">Sign In</button>
        </div>
        <span>Don't have an Account? <span class="signup_open" onclick="openSignupWindow()">Sign Up</span></span>
    </div>
</div>