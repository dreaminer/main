<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<div class="tabContent" id="uploadTabContent">
    <div id="uploadTabContainer">
        <form id="uploadTabForm">
        	<input id="uploadTabWorkNum" name="workNum" value="" hidden>
            <input id="uploadTabTitle" type="text" name="workTitle" placeholder="Title" required>
            <input type="file" name="workFile" id="uploadWork" onchange="uploadFileThumbnail()" required>
            <label id="uploadTabImgArea" for="uploadWork"><img id="uploadTabImg" src="resources/img/upload.png" alt=""></label>
            <input id="uploadTabTag" type="text" name="workTag" placeholder="Tag" required>
            <input id="uploadTabDesc" type="text" name="workDesc" placeholder="Description" required>
            <input id="uploadTabPrice" type="number" name="workPrice" placeholder="Price" value=0>
            <div class="workP">
                <input type="radio" name="workStatus" id="workPublic"  value="y" checked>
                <label for="workPublic"><img src="resources/img/unlock.png" alt=""></label>
                <input type="radio" name="workStatus" id="workPrivate" value="n">
                <label for="workPrivate"><img src="resources/img/lock.png" alt=""></label>
            </div>
            <button type="button" id="uploadTabButton"></button>         
        </form>
    </div>
</div>