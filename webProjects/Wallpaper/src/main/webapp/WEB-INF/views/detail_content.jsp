<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<div class="detail_background" id="detail">
	<img class="cancle" src="resources/img/cancle.png" onclick="closeDetailWindow()">
	<div class="detail_content">
	    <div class="detail_title">
	        <p id="detailworkTitle"></p>
	    </div>
	    <div id="detail_img" onmouseover="openOptionTab()" onmouseout="closeOptionTab()">
	        <img id="detailworkImg" src="" alt="">
	        <div id="workOption">
		        <div id="workOptionFollow" onclick="updateFollow()"><img id="workOptionFollowImg" alt="" src="resources/img/optionFollow.png"></div>
		        <div id="workOptionLike" onclick="updateLike()"><img id="workOptionLikeImg" alt="" src="resources/img/optionLike.png"></div>
		        <div id="workOptionBuy" onclick="updateBuy()"><img id="workOptionBuyImg" alt="" src="resources/img/optionBuy.png"></div>
		        <div id="workOptionUpdate"><img id="workOptionUpdateImg" alt="" src="resources/img/optionUpdate.png"></div>	        
	        </div>
	    </div>
	    <div id="detailworkTag" class="detail_tag"></div>
	    <div id="detailworkDesc"></div>
	    <div class="detail_spec">
	        <div class="detail_artist">
	            <img id="detailworkCreator" src="" alt="">                                
	            <span id="detailworkUsername" class="author_nickname"></span>
	        </div>
	        <div class="detail_degrees">
	            <div class="desc_date"><img src="resources/img/date.png" alt=""><span id="detailworkDate"></span></div>
	            <div class="desc_price"><img src="resources/img/price.png" alt=""><span id="detailworkPrice"></span></div>
	            <div class="desc_reply"><img src="resources/img/reply.png" alt=""><span id="detailworkReply"></span></div>
	            <div class="desc_view"><img src="resources/img/view.png" alt=""><span id="detailworkView"></span></div>
	            <div class="desc_like"><img src="resources/img/like.png" alt=""><span id="detailworkLike"></span></div>
	            <div class="desc_buy"><img src="resources/img/buy.png" alt=""><span id="detailworkBuy"></span></div>
	        </div>
	    </div>
	    <div id="detailworkReplyTab" class="detail_reply_write">
	        <textarea id="detailworkReplyContent" placeholder="Reply Your Response in 30 letters"></textarea>
	        <label for="reply_submit"><img src="resources/img/reply2.png" alt="" onclick="createReply()"></label>
	        <input type="submit" id="reply_submit" hidden>
	    </div>
	    <div id="detailworkReplyList" class="reply_content"></div>
	</div>
</div>