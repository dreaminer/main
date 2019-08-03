<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<div class="tabContent" id="creatorTabContent">
    <div id="creatorTabContainer">
        <div class="creatorItems">
            <div class="creatorPage">
                <div class="creatorPageLt">
                    <img class="work" src="${member.profile}">
                    <span id="creatorUserName"></span>
                </div>
                <div class="creatorPageRt">
                    <div class="creator_desc_follower"><img src="resources/img/follower.png" alt=""><span id="creatorFollowerSum"></span></div>
                    <div class="creator_desc_work"><img src="resources/img/work.png" alt=""><span id="creatorCreateWork"></span></div>
                    <div class="creator_desc_reply"><img src="resources/img/reply.png" alt=""><span id="creatorReplySum"></span></div>
                    <div class="creator_desc_view"><img src="resources/img/view.png" alt=""><span id="creatorViewSum"></span></div>
                    <div class="creator_desc_like"><img src="resources/img/like.png" alt=""><span id="creatorLikeSum"></span></div>
                    <div class="creator_desc_buy"><img src="resources/img/buy.png" alt=""><span id="creatorBuySum"></span></div>
                </div>
            </div>
            <div class="creatorWorks" id="creatorWorkList">
            </div>
        </div>
    </div>
</div>