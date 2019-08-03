<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="search_N_gallery">
	<div class="searchBar">
	    <div id="searchForm">
	        <input type="search" id="search_bar" name="searchWork" placeholder="Text for Searching">
<!-- 	        <input type="checkbox" id="search_setting" -->
	<!--         <label class="sub_setting" for="search_setting"><img class="setting" src="resources/img/setting.png" alt=""></label>
	        <div class="setting_container">
	            <input type="checkbox" id="view_img" name="viewOption">
	            <label class="view_img" for="view_img"><img class="setting" src="resources/img/view.png" alt=""></label>
	            <input type="checkbox" id="like_img" name="likeOption">
	            <label class="like_img" for="like_img"><img class="setting" src="resources/img/like.png" alt=""></label>
	            <input type="checkbox" id="price_img" name="priceOption">
	            <label class="price_img" for="price_img"><img class="setting" src="resources/img/price.png" alt=""></label>
	            <input type="checkbox" id="buy_img" name="buyOption">
	            <label class="label_setting" for="buy_img"><img class="setting" src="resources/img/buy.png" alt=""></label>
	            <input type="checkbox" id="date_img" name="dateOption">
	            <label class="date_img" for="date_img"><img class="setting" src="resources/img/date.png" alt=""></label>
	            <input type="checkbox" id="reply_img" name="replyOption">
	            <label class="reply_img" for="reply_img"><img class="setting" src="resources/img/reply.png" alt=""></label>
	        </div> -->
	        <input type="checkbox" id="search_tag" name="tagOption" onchange="tagOption()" value="off">
	        <label class="tag_setting" for="search_tag"><img class="setting" src="resources/img/tag.png" alt=""></label>
	        <input type="checkbox" id="search_artist" name="creatorOption" onchange="creatorOption()" value="off">
	        <label class="artist_setting" for="search_artist"><img class="setting" src="resources/img/artist.png" alt=""></label>
	        <img id="searchButton" src="resources/img/search.png" alt="" onclick="searchForWork()">
	    </div>
	    <img class="signinButton" src="resources/img/user.png" alt="" onclick="openSigninWindow()">
    </div>
    <div class="gallery" id="workGallery">
    <c:forEach var="work" items="${works}">
 		<img class="work" src="${work.work }" alt="${work.workNum }" onclick="workDetail('${work.workNum}')">
    </c:forEach>
    </div>
</div>