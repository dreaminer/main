<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="controlBoxPanel">
    <label id="logoTabLabel"><img src="${sessionScope.member.profile}" alt=""></label>
    <label id="infoTabLabel" onclick="openInfoTab()">Info</label>
    <label id="accountTabLabel" onclick="openAccountTab()">Account</label>
    <label id="likeTabLabel" onclick="openLikeTab()">like</label>
    <label id="followTabLabel" onclick="openFollowTab()">Follow</label>
    <label id="galleryTabLabel" onclick="openGalleryTab()">Gallery</label>
    <label id="creatorTabLabel" onclick="openCreatorTab()">Creator</label>
    <label id="uploadTabLabel" onclick="openUploadTab()">Upload</label>    
    <label id="signoutTabLabel" onclick="signoutPro()">Signout</label>
</div>