<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="tabContent" id="accountTabContent">
    <div id="accountTabContainer">
        <div id="accountTabForm">
            <img src="resources/img/coins.png" alt="">
            <span>현재 잔액</span>
            <label id="accountTabAmount" for="present_amount" onclick="openChargeTab()"></label>
            <input id="present_amount" type="checkbox">
            <input type="number" id="accountTabCharge" placeholder="Wanna Charge?">
            <button id="chargeButton" type="button" onclick="updateAccount()">충전</button>
        </div>
    </div>
</div>