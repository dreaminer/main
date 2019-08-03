<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<div class="update_background" id="update">
	<img class="cancle" src="resources/img/cancle.png" onclick="closeUpdateWindow()">
	<div class="update_content">
	    <div class="update_title">
	        <input id="updateworkTitle" value="">
	        <input type="checkbox" id="like_click">
	        <label class="click_like" for="like_click"><img class="setting" src="/resources/img/like.png" alt=""></label>
	        <input type="checkbox" id="buy_click">
	        <label class="click_buy" for="buy_click"><img class="setting" src="/resources/img/buy.png" alt=""></label>
	    </div>
	    <div class="update_img">
	        <img id="updateworkImg" src="" alt="">
	    </div>
	    <input id="updateworkTag" class="update_tag" value="">
	    <div class="update_spec">
	        <div class="update_artist">
	            <img id="updateworkCreator" src="" alt="">                                
	            <span id="updateworkUsername" class="author_nickname"></span>
	        </div>
	        <div class="update_degrees">
	            <div class="desc_date"><img src="resources/img/date.png" alt=""><span>14/08/02</span></div>
	            <div class="desc_price"><img src="resources/img/price.png" alt=""><span>6000</span></div>
	            <div class="desc_reply"><img src="resources/img/reply.png" alt=""><span>12</span></div>
	            <div class="desc_view"><img src="resources/img/view.png" alt=""><span>12678</span></div>
	            <div class="desc_like"><img src="resources/img/like.png" alt=""><span>2341</span></div>
	            <div class="desc_buy"><img src="resources/img/buy.png" alt=""><span>147</span></div>
	        </div>
	    </div>
	</div>
</div>