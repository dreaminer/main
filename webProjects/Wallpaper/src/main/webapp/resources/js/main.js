// 메인호출
function main() {
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "main", true);
	xhttp.send();
}

// 작품 상세 페이지
function workDetail(workNum) {
	document.getElementById("detail").style.display = "block";
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "openWorkDetail?workNum="+workNum, true);
	xhttp.send();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			// JSON형태의 String형태로 넘어온다
			let works = JSON.parse(this.responseText);
				
			// 제목, 이미지, 설명, 작가, 작가유저네임
			document.getElementById("detailworkTitle").innerHTML = works["detailWork"]["workTitle"];
			document.getElementById("detailworkImg").src = works["detailWork"]["work"];
			document.getElementById("detailworkImg").alt = works["detailWork"]["workNum"];
			document.getElementById("detailworkDesc").innerHTML = works["detailWork"]["workDesc"];
			document.getElementById("detailworkCreator").src = works["detailMember"]["profile"];
			document.getElementById("detailworkUsername").innerHTML = works["detailMember"]["userName"];
			
			let setWidth = document.getElementById("detailworkImg").offsetWidth;
			let setHeight = document.getElementById("detailworkImg").offsetHeight;
			let workOption = document.getElementById("workOption");
			workOption.style.height = setHeight + "px";

			// 댓글버튼, 팔로우, 좋아요, 구매, 수정
			if (works["emailId"] == null) {
				document.getElementById("detailworkReplyTab").style.display = "none";
				document.getElementById("detail_img").removeAttribute("onmouseover");
				document.getElementById("detail_img").removeAttribute("onmouseout");
			} else {
				document.getElementById("detail_img").setAttribute("onmouseover", "openOptionTab()");
				document.getElementById("detail_img").setAttribute("onomouseout", "closeOptionTab()");

				if (works["emailId"] == works["detailWork"]["emailId"]) {
					document.getElementById("workOptionFollow").style.display = "none";
					document.getElementById("workOptionLike").style.display = "none";
					document.getElementById("workOptionBuy").style.display = "none";
					
					let workOptionUpdate = document.getElementById("workOptionUpdate");
					workOptionUpdate.style.display = "flex";
					workOptionUpdate.setAttribute("onclick", "openUpdateTab('"+workNum+"')");
				} else {
					document.getElementById("workOptionFollow").style.display = "flex";
					document.getElementById("workOptionLike").style.display = "flex";
					document.getElementById("workOptionBuy").style.display = "flex";
					
					let followImg = document.getElementById("workOptionFollowImg");
					let buyImg = document.getElementById("workOptionBuyImg");
					let likeImg = document.getElementById("workOptionLikeImg");
					if (works["detailFollow"] == "1") {
						followImg.style.filter = "";
						followImg.alt = "on";
					} else {
						followImg.style.filter = "brightness(100)";
						followImg.alt = "off";
					}
					if (works["detailBuy"] == "1") {
						buyImg.style.filter = "";
						buyImg.alt = "on";
					} else {
						buyImg.style.filter = "brightness(100)";		
						buyImg.alt = "off";
					}
					if (works["detailLike"] == "1") {
						likeImg.style.filter = "";
						likeImg.alt = "on";
					} else {
						likeImg.style.filter = "brightness(100)";	
						likeImg.alt = "off";
					}
				}
			}			
			
			/*6가지 작품정보*/
			document.getElementById("detailworkDate").innerHTML = works["detailWork"]["workReg"];
			document.getElementById("detailworkPrice").innerHTML = works["detailWork"]["workPrice"];
			document.getElementById("detailworkReply").innerHTML = works["detailReplyCount"];
			document.getElementById("detailworkView").innerHTML = works["detailViewCount"];
			document.getElementById("detailworkLike").innerHTML = works["detailLikeCount"];
			document.getElementById("detailworkBuy").innerHTML = works["detailBuyCount"];
			
			// 작품태그
			let detailworkTag = document.getElementById("detailworkTag");
			if (detailworkTag.children[0] != undefined) {
			} else {
				let workTag = works["detailWork"]["workTag"].split("#");
				for (let i = 1; i < workTag.length; i++) {
					let span = document.createElement("span");
					span.innerHTML = workTag[i];
					detailworkTag.appendChild(span);
				}
			}
			
			// 댓글
			let replyList = works["detailReply"];
			let replyContent = document.getElementById("detailworkReplyList");
			for (let i = replyList.length-1; i >= 0; i--) {
				// List는 문자열화 한 다음 다시 파싱하여 사용한다
				let reply = JSON.parse(JSON.stringify(replyList[i]));
				let replyDiv = document.createElement("div");
				let replyImg = document.createElement("img");
				let replySpan = document.createElement("span");
				
				replyImg.setAttribute("src", reply["profile"]);
				replySpan.innerHTML = reply["reContent"];
				
				replyDiv.appendChild(replySpan);
				replyDiv.appendChild(replyImg);
				replyContent.appendChild(replyDiv);
			}
		}
	}
}

// 작품 상세창 닫을 때 
function closeDetailWindow() {
	document.getElementById("detail").style.display = "none";
	document.getElementById("workOptionUpdate").style.display = "none";
	document.getElementById("detailworkReplyContent").value = "";
	
	let parentTag = document.getElementById("detailworkTag");
	let childTag = parentTag.lastElementChild;
	while (childTag) {
		parentTag.removeChild(childTag);
		childTag = parentTag.lastElementChild;
	}
	let parentReply = document.getElementById("detailworkReplyList");
	let childReply = parentReply.lastElementChild;
	while (childReply) {
		parentReply.removeChild(childReply);
		childReply = parentReply.lastElementChild;
	}
}

// 팔로우 하기
function updateFollow() {
	let followImg = document.getElementById("workOptionFollowImg");
	let followStatus = followImg.alt;
	let workNum = document.getElementById("detailworkImg").alt;
	
	let xhttp = new XMLHttpRequest();
	xhttp.open("POST", "updateFollow", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("followStatus="+followStatus+"&workNum="+workNum);
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let result = this.responseText;

			if (result == "1") {
				if (followImg.alt == "on") {
					followImg.style.filter = "grayscale(100%) brightness(100)";
					followImg.alt = "off";					
				} else {
					followImg.style.filter = "";
					followImg.alt = "on";
				}

			} else {
				alert("팔로우에 실패했습니다");
			}
		}
	}
}

// 좋아요 하기
function updateLike() {
	let likeImg = document.getElementById("workOptionLikeImg");
	let likeStatus = likeImg.alt;
	let workNum = document.getElementById("detailworkImg").alt;	
	
	let xhttp = new XMLHttpRequest();
	xhttp.open("POST", "updateLike", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("likeStatus="+likeStatus+"&workNum="+workNum);
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let result = this.responseText;

			if (result == "1") {
				if (likeImg.alt == "on") {
					likeImg.style.filter = "grayscale(100%) brightness(100)";
					likeImg.alt = "off";					
				} else {
					likeImg.style.filter = "";
					likeImg.alt = "on";
				}

			} else {
				alert("좋아요에 실패했습니다");
			}
		}
	}
}

// 구매하기 
function updateBuy() {
	let buyImg = document.getElementById("workOptionBuyImg");
	let buyStatus = buyImg.alt;
	let workNum = document.getElementById("detailworkImg").alt;	
	
	if (buyStatus == "on") {
		alert("이미 구매한 작품입니다");
		return false;
	}
	
	let xhttp = new XMLHttpRequest();
	xhttp.open("POST", "updateBuy", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("workNum="+workNum);
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let result = this.responseText;

			if (result == "1") {
				buyImg.style.filter = "";
				buyImg.alt = "on";	
				alert("구매하였습니다");
			} else {
				alert("구매에 실패했습니다. 잔액이 부족합니다");
			}
		}
	}
	return true;
}

// 옵션탭 열기
function openOptionTab() {
	document.getElementById("workOption").style.display = "flex";
}

// 옵션탭 닫기
function closeOptionTab() {
	document.getElementById("workOption").style.display = "none";
}

// 댓글 생성
function createReply() {
	let workNum = document.getElementById("detailworkImg").alt;
	let replyContent = document.getElementById("detailworkReplyContent").value;

	
	if (replyContent == "") {
		alert("댓글 내용을 입력하세요");
		return false;
	}
	
	let xhttp = new XMLHttpRequest();
	xhttp.open("POST", "createReply", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("reContent=" + replyContent + "&workNum=" + workNum);
	
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status) {
			let result = JSON.parse(this.responseText);
			if ( result["successNum"] == "1") {
				document.getElementById("detailworkReplyContent").value = "";
				let replyContentAdd = document.getElementById("detailworkReplyList");
				let replyDiv = document.createElement("div");
				let replyImg = document.createElement("img");
				let replySpan = document.createElement("span");
				
				replyImg.setAttribute("src", result["member"]["profile"]);
				replySpan.innerHTML = replyContent;
				
				replyDiv.appendChild(replySpan);
				replyDiv.appendChild(replyImg);
				replyContentAdd.insertBefore(replyDiv, replyContentAdd.firstChild);
			} else {
				alert("댓글 등록에 실패했습니다");
			}
		}
	}
	return true;
}

/*세션 갱신*/
function updateSession() {
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "updateSession", true);
	xhttp.send();
}

/*메인화면*/
function refreshMain() {
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "refreshMain", true);
	xhttp.send();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			// Array > JSONArray
			let works = JSON.parse(this.responseText);
			for (let i = 0; i < works.length; i++) {
				// JSONArray를 문자열화 한다음 다시 parsing
				let work = works[i];
				
				let img = document.createElement("img");
				img.setAttribute("src", work["work"]);
				img.setAttribute("alt", work["workNum"]);
				document.getElementById("workGallery").appendChild(img);
			}
		}
	}
}

/*로그인 창 열때*/ 
function openSigninWindow() {
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "openControlBox", true);
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 & this.status == 200) {
			let result = this.responseText;
			if (result == "0") {
				document.getElementById("signin_window").style.display = "block";
				document.getElementById("signinFailed").innerHTML = "";				
			} else {
				let controlBox = document.getElementById("controlBox");
				if (controlBox.style.display == "none") {
					controlBox.style.display = "grid";
				} else {
					controlBox.style.display = "none";
				}
			}
		}
	}
	xhttp.send();
}

/*로그인 창 닫을 떄*/
function closeSigninWindow() {
	document.getElementById("signin_window").style.display = "none";
	document.getElementById("signinEmail").value = "";
	document.getElementById("signinPw").value = "";
}

/*회원가입 창 열때*/
function openSignupWindow() {
	document.getElementById("signup_window").style.display = "block";
	document.getElementById("signin_window").style.display = "none";
	document.getElementById("signupForm").reset();
	document.getElementById("profileThumbnail").src = "resources/img/profile.png";
	document.getElementById("signupEmail").style.backgroundColor = "#ffffff";
	document.getElementById("inputPw").style.backgroundColor = "#ffffff";
	document.getElementById("chkPw").style.backgroundColor = "#ffffff";
	document.getElementById("signupUn").style.backgroundColor = "#ffffff";
}

/*회원가입 창 닫을 때*/
function closeSignupWindow() {
	document.getElementById("signup_window").style.display = "none";
}

/*로그인 시*/
function signinPro() {
	let emailId = document.getElementById("signinEmail").value;
	let pw = document.getElementById("signinPw").value;
	
	if (emailId == "" || pw == "") {
		alert("아이디와 비밀번호를 입력하여 주세요");
	} else {
		// 서버와의 사이드 XMLHttpRequest 객체 생성
		let xhttp = new XMLHttpRequest();
		// 객체 오픈
		xhttp.open("POST", "signinMember", true);
		// 객체에 응답시 로직 설정
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 & this.status == 200) {
				let result = this.responseText;
				if (result == "1") {
					window.location.href = "main";
				} else {					
					document.getElementById("signinFailed").innerHTML = "아이디 또는 비밀번호가 일치하지 않습니다";
					document.getElementById("signinPw").value = "";
					document.getElementById("signinPw").focus();
				}
			}
		}
		// 요청헤더를 설정하고 객체를 서버로 전송
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send("emailId=" + emailId + "&pw=" + pw);
	}
};

/*로그아웃 시*/
function signoutPro() {

	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "signoutMember", true);
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let result = this.responseText;
			if (result == '0') {
				alert("로그아웃을 다시 시도해 주세요")
			} else {
				window.location.href = "main";
			}
		}
	}
	xhttp.send();
}

/*프로필 사진 업로드시*/
function showThumbnail() {
	let thumbnail = document.getElementById("profileThumbnail");
	let file = document.getElementById("profile_upload").files[0];
	let reader = new FileReader();
	
	reader.onloadend = function() {
		thumbnail.src = reader.result;
	}
	
	if (file) {
		reader.readAsDataURL(file);
	} else {
		thumbnail.src = "";
	}
}

/*회원가입 아이디 유효확인*/
function checkIdAjax() {
	
	let string = undefined;
	stringBL = function(string, b, i, c) {
		for (b = i = 0; c = string.charCodeAt(i++); b += c >> 11 ? 3 : c >> 7 ? 2 : 1);
		return b;
	};
	
	/*서버로 보낼 데이터*/
	let target = document.getElementById("signupEmail").value;
	/*데이터가 있을 경우에만*/ 
	if (target != 'undefined') {
		/*서버와의 사이드 연결을 위한 XMLHttpRequest 객체 생성*/
		let xhttp = new XMLHttpRequest();
		/*객체 오픈*/
		xhttp.open("GET", "checkIdAjax?emailId="+target, true);
		/*객체 응답시 로직 설정*/
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 & this.status == 200) {
				let result = this.responseText;

				if (result == "0" & stringBL(target) > 4 & stringBL(target) < 51) {
					document.getElementById("signupEmail").style.backgroundColor = "#d9fab4";
				} else {
					document.getElementById("signupEmail").style.backgroundColor = "#fac8a7";
				}
			}
		}
		/*준비된 객체를 서버로 전송*/
		xhttp.send();
	}
}

/*회원가입 비밀번호 입력*/
function createPw() {
	let password = document.getElementById("inputPw").value;
	
	let string = undefined;
	stringBL = function(string, b, i, c) {
		for (b = i = 0; c = string.charCodeAt(i++); b += c >> 11 ? 3 : c >> 7 ? 2 : 1);
		return b;
	};
	
	if (stringBL(password) < 51 && stringBL(password) > 0) {
		document.getElementById("inputPw").style.backgroundColor = "#d9fab4";
	} else {
		document.getElementById("inputPw").style.backgroundColor = "#fac8a7";
	}
}

/*회원가입 비밀번호 확인*/
function checkPw() {
	let targetPw = document.getElementById("inputPw").value;
	let checkPw = document.getElementById("chkPw").value;
	
	if (targetPw == checkPw) {
		document.getElementById("chkPw").style.backgroundColor = "#d9fab4";
	} else {
		document.getElementById("chkPw").style.backgroundColor = "#fac8a7";
	}
}

/*유저네임 유효확인*/
function checkUnAjax() {
	let string = undefined;
	stringBL = function(string, b, i, c) {
		for (b = i = 0; c = string.charCodeAt(i++); b += c >> 11 ? 3 : c >> 7 ? 2 : 1);
		return b;
	};
	
	/*서버로 보낼 데이터*/
	let target = document.getElementById("signupUn").value;
	/*데이터가 있을 경우에만*/ 
	if (target != 'undefined') {
		/*서버와의 사이드 연결을 위한 XMLHttpRequest 객체 생성*/
		let xhttp = new XMLHttpRequest();
		/*객체 오픈*/
		xhttp.open("GET", "checkUnAjax?userName="+target, true);
		/*객체 응답시 로직 설정*/
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 & this.status == 200) {
				let result = this.responseText;
				
				if (result == "0" && stringBL(target) < 51 && stringBL(target) > 0) {
					document.getElementById("signupUn").style.backgroundColor = "#d9fab4";
				} else {
					document.getElementById("signupUn").style.backgroundColor = "#fac8a7";
				}
			}
		}
		/*준비된 객체를 서버로 전송*/
		xhttp.send();
	}
}

/*회원가입 최종확인*/
function signupFormChk() {
	if (document.getElementById("signupEmail").style.backgroundColor != "rgb(217, 250, 180)" || 
		document.getElementById("inputPw").style.backgroundColor != "rgb(217, 250, 180)" ||
		document.getElementById("chkPw").style.backgroundColor != "rgb(217, 250, 180)" || 
		document.getElementById("signupUn").style.backgroundColor != "rgb(217, 250, 180)" ) {
		alert("양식을 완성해 주세요");
		return false;
	} 
	return true;
}

/*회원정보 탭 오픈*/
function openInfoTab() {
	// 다른탭 OFF
	let tabContent = document.getElementsByClassName("tabContent");
	for (let i = 0; i < tabContent.length; i++) {
		tabContent[i].style.display = "none";
	}
	// 회원정보 탭 ON
	document.getElementById("infoTabContent").style.display = "flex";
	
	// 회원정보 AJAX
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "openInfoTab", true);
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log("responseText:" + xhttp.responseText);
			try {
				/*서버에서 Class보낸 정보를 받아 json으로 변환하여 JS객체에 저장*/
				let data = JSON.parse(xhttp.responseText);
				document.getElementById("infoTabProfile").src = data["profile"];
				document.getElementById("infoTabEmailId").innerHTML = data["emailId"];
				document.getElementById("infoTabcreateDate").innerHTML = data["createDate"];				
				document.getElementById("infoTabBadge").innerHTML = data["badge"];
				document.getElementById("infoTabUsername").value = data["userName"];
				document.getElementById("originalPw").value = data["pw"];
			} catch(err) {
				console.log(err.message + "in" + xhttp.responseText);
				return;
			}
		}
	}
	xhttp.setRequestHeader('Content-Type', 'application/json');
	xhttp.send();
}

/*비밀번호 변경창 팝업*/
function openUpdatePwTab() {
	document.getElementById("changePwBox").style.display = "block";
	document.getElementById("changePw").value = "";
	document.getElementById("changePwChk").value = "";
}

/*회원정보 수정*/
function updateMember() {
	let emailId = document.getElementById("infoTabEmailId").innerHTML;
	let userName = document.getElementById("infoTabUsername").value;
	let originalPw = document.getElementById("originalPw").value;
	let changePw = document.getElementById("changePw").value;
	let changePwChk = document.getElementById("changePwChk").value;
	
	if (changePw != changePwChk) {
		alert("비밀번호가 같지 않습니다");
		return false;
	}
	
	let xhttp = new XMLHttpRequest();
	xhttp.open("POST", "updateMember", true);
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let result = this.responseText;
			if (result == '0') {
				alert("양식을 다시 입력해 주세요");
				return false;
			} else {
				document.getElementById("infoTabUsername").value = userName;
				document.getElementById("changePwBox").style.display = "none";
				if (changePw != "") {
					document.getElementById("originalPw").value = changePw;					
				}
				updateSession();
			};
		};
	};
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	if (changePw == "") {
		xhttp.send("emailId=" + emailId + "&userName=" + userName + "&pw=" + originalPw);		
	} else {
		xhttp.send("emailId=" + emailId + "&userName=" + userName + "&pw=" + changePw);		
	}

	return true;
}

/*계좌정보 탭 오픈*/
function openAccountTab() {
	// 다른탭 OFF
	let tabContent = document.getElementsByClassName("tabContent");
	for (let i = 0; i < tabContent.length; i++) {
		tabContent[i].style.display = "none";
	}
	// 계좌정보 탭 ON
	document.getElementById("accountTabContent").style.display = "flex";
	// 계좌정보 AJAX
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "openInfoTab", true);
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log("responseText:" + xhttp.responseText);
			try {
				/*서버에서 Class보낸 정보를 받아 json으로 변환하여 JS객체에 저장*/
				let data = JSON.parse(xhttp.responseText);
				document.getElementById("accountTabAmount").innerHTML = data["account"]+"원";
			} catch(err) {
				console.log(err.message + "in" + xhttp.responseText);
				return;
			}
		}
	}
	xhttp.setRequestHeader('Content-Type', 'application/json');
	xhttp.send();
}

/*충전 탭 오픈*/
function openChargeTab() {
	document.getElementById("accountTabCharge").style.display = "block";
	document.getElementById("chargeButton").style.display = "block";
	document.getElementById("accountTabCharge").value = "";
}

/*계좌충전*/
function updateAccount() {
	let chargeAmount = document.getElementById("accountTabCharge").value;
	if (chargeAmount == "" || chargeAmount <= 0 ) {
		alert("충전금액을 입력하세요");
	} else {
		let xhttp = new XMLHttpRequest();
		xhttp.open("POST", "updateAccount", true);
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				let result = this.responseText;
				if (result == '1') {
					openAccountTab();
					document.getElementById("accountTabCharge").style.display = "none";
					document.getElementById("chargeButton").style.display = "none";
				} else {
					alert("충전이 되지 않았습니다. 다시 시도하세요");
				}
			}			
		}
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send("chargeAmount=" + chargeAmount);	
	}
}

// 좋아요 탭 오픈 
function openLikeTab() {
	// 다른탭 OFF
	let tabContent = document.getElementsByClassName("tabContent");
	for (let i = 0; i < tabContent.length; i++) {
		tabContent[i].style.display = "none";
	}
	
	document.getElementById("likeTabContent").style.display = "block";
	let likeItems = document.getElementById("likeItems");
	
	let childItem = likeItems.lastElementChild;
	while (childItem) {
		likeItems.removeChild(childItem);
		childItem = likeItems.lastElementChild;
	}
	
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "openLikeTab", true);
	xhttp.send();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let result = JSON.parse(this.responseText);
			for (let i = 0; i < result.length; i++) {
				
				let likeItem = document.createElement("div");
				let likeImg = document.createElement("img");
				let likeSpan = document.createElement("span");
				let likeCancleImg = document.createElement("img");
				
				// 클릭시 작품으로 이동 할 수 있도록 class에 작품번호를 남겨놓는다
				likeItem.className = result[i]["workNum"];
				// 이미지를 표시
				likeImg.src = result[i]["work"];
				likeImg.setAttribute("onclick", "workDetail('"+result[i]["workNum"]+"')");
				// 제목을 표시
				likeSpan.innerHTML = result[i]["workTitle"];
				// 삭제
				likeCancleImg.src = "resources/img/cancle.png";
				likeCancleImg.setAttribute("class", "cancle");
				likeCancleImg.setAttribute("onclick", "deleteLike('"+result[i]["workNum"]+"')")
				// 연결
				likeItem.appendChild(likeImg);
				likeItem.appendChild(likeSpan);
				likeItem.appendChild(likeCancleImg);
				likeItems.appendChild(likeItem);
			}
			
		}
	}
}

// 좋아요 삭제
function deleteLike(workNum) {
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "deleteLike?workNum="+workNum, true);
	xhttp.send();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let result = this.responseText;
			if (result == "1") {
				openLikeTab();
			} else {
				alert("좋아요 취소를 실패했습니다");
			}
		}
	}
}

// 팔로우 	탭 오픈
function openFollowTab() {
	// 다른탭 OFF
	let tabContent = document.getElementsByClassName("tabContent");
	for (let i = 0; i < tabContent.length; i++) {
		tabContent[i].style.display = "none";
	}
	
	document.getElementById("followTabContent").style.display = "block";
	let followItems = document.getElementById("followItems");
	
	let childItem = followItems.lastElementChild;
	while (childItem) {
		followItems.removeChild(childItem);
		childItem = followItems.lastElementChild;
	}
	
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "openFollowTab", true);
	xhttp.send();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let result = JSON.parse(this.responseText);
			for (let i = 0; i < result.length; i++) {
				
				let followItem = document.createElement("div");
				let followImg = document.createElement("img");
				let followSpan = document.createElement("span");
				let followCancleImg = document.createElement("img");
				
				// 클릭시 작품으로 이동 할 수 있도록 class에 작품번호를 남겨놓는다
				followItem.className = result[i]["followee"];
				// 이미지를 표시
				followImg.src = result[i]["profile"];
				followImg.setAttribute("onclick", "memberDetail('"+result[i]["followee"]+"')");
				// 제목을 표시
				followSpan.innerHTML = result[i]["userName"];
				// 삭제
				followCancleImg.src = "resources/img/cancle.png";
				followCancleImg.setAttribute("class", "cancle");
				followCancleImg.setAttribute("onclick", "deleteFollow('"+result[i]["followee"]+"')")
				// 연결
				followItem.appendChild(followImg);
				followItem.appendChild(followSpan);
				followItem.appendChild(followCancleImg);
				followItems.appendChild(followItem);
			}
			
		}
	}
}

// 팔로우 삭제
function deleteFollow(followee) {
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "deleteFollow?followee="+followee, true);
	xhttp.send();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let result = this.responseText;
			if (result == "1") {
				openFollowTab();
			} else {
				alert("좋아요 취소를 실패했습니다");
			}
		}
	}
}

// 갤러리 탭 오픈
function openGalleryTab() {
	// 다른탭 OFF
	let tabContent = document.getElementsByClassName("tabContent");
	for (let i = 0; i < tabContent.length; i++) {
		tabContent[i].style.display = "none";
	}
	
	document.getElementById("galleryTabContent").style.display = "block";
	let galleryItems = document.getElementById("galleryItems");
	
	let childItem = galleryItems.lastElementChild;
	while (childItem) {
		galleryItems.removeChild(childItem);
		childItem = galleryItems.lastElementChild;
	}
	
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "openGalleryTab", true);
	xhttp.send();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let result = JSON.parse(this.responseText);
			for (let i = 0; i < result.length; i++) {
				
				let galleryItem = document.createElement("div");
				let galleryImg = document.createElement("img");
				let gallerySpan = document.createElement("span");
				
				// 클릭시 작품으로 이동 할 수 있도록 class에 작품번호를 남겨놓는다
				galleryItem.className = result[i]["workNum"];
				// 이미지를 표시
				galleryImg.src = result[i]["work"];
				galleryImg.setAttribute("onclick", "workDetail('"+result[i]["workNum"]+"')");
				// 제목을 표시
				gallerySpan.innerHTML = result[i]["workTitle"];
				// 연결
				galleryItem.appendChild(galleryImg);
				galleryItem.appendChild(gallerySpan);
				galleryItems.appendChild(galleryItem);
			}
			
		}
	}
}

/*작품수정 탭 오픈*/
function openUpdateTab(workNum) {
	// 다른탭 OFF
	let tabContent = document.getElementsByClassName("tabContent");
	for (let i = 0; i < tabContent.length; i++) {
		tabContent[i].style.display = "none";
	}
	closeDetailWindow();
	document.getElementById("controlBox").style.display = "grid";
	document.getElementById("uploadTabContent").style.display = "flex";

	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "updateTab?workNum="+workNum, true);
	xhttp.send();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let result = JSON.parse(this.responseText);
			document.getElementById("uploadTabWorkNum").value = result["workNum"];
			document.getElementById("uploadTabTitle").value = result["workTitle"];
			document.getElementById("uploadTabImg").src = result["work"];
			document.getElementById("uploadTabTag").value = result["workTag"];
			document.getElementById("uploadTabDesc").value = result["workDesc"];
			document.getElementById("uploadTabPrice").value = result["workPrice"];

			if (result["workStatus"] == "y") {
				document.getElementById("workPublic").checked = true;
				document.getElementById("workPrivate").checked = false;
			} else {
				document.getElementById("workPublic").checked = false;
				document.getElementById("workPrivate").checked = true;
			}
			document.getElementById("uploadTabButton").setAttribute("onclick", "updateWork()");
			document.getElementById("uploadTabButton").innerHTML = "작품수정";
		}
	}
}

/*작품수정*/
function updateWork() {
	/*HTML의 FORM 안의 내용을 저장*/
	let form 		= document.getElementById("uploadTabForm");
	/*해당 form을 가지고 FormData 객체 생성*/
	let formData 	= new FormData(form);
	/*XMLHttpRequest 객체 준비*/
	let xhttp 		= new XMLHttpRequest();
	xhttp.open("POST", "updateWork", true);
	xhttp.send(formData);
	
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let result = this.responseText;
			if (result == '0') {
				alert("작품이 업로드 돠지 않았습니다");
			} else {
				alert("작품이 업로드 되었습니다");
				document.getElementById("uploadTabForm").reset();
				document.getElementById("uploadTabImg").src = "resources/img/upload.png";
			}
		}
	}
	return true;
}

/*upload Tab*/
/*업로드 탭 오픈*/
function openUploadTab() {
	// 다른탭 OFF
	let tabContent = document.getElementsByClassName("tabContent");
	for (let i = 0; i < tabContent.length; i++) {
		tabContent[i].style.display = "none";
	}
	// 업로드 탭 ON
	document.getElementById("uploadTabContent").style.display = "flex";
	document.getElementById("uploadTabImg").src = "resources/img/upload.png";
	document.getElementById("uploadWork").value = "";
	document.getElementById("uploadTabForm").reset();
	document.getElementById("uploadTabButton").setAttribute("onclick", "createWork()");
	document.getElementById("uploadTabButton").innerHTML = "작품올리기";
}

/*업로드 사진 썸네일*/
function uploadFileThumbnail() {
	/*표시될 이미지 부분*/
	let thumbnail 	= document.getElementById("uploadTabImg");
	/*이미지 파일*/
	let file 		= document.getElementById("uploadWork").files[0];
	/*이미지 파일 리더객체 생성*/
	let reader 		= new FileReader();
	
	/*파일읽기 객체에 무언가 로드되면 그 주소를 이미지 주소로 저장*/
	reader.onloadend = function() {
		thumbnail.src = reader.result;
	}
	/*업로드 파일이 있다면 해당 파일의 주소를 파일읽기 객체에 로드*/
	if (file) {
		reader.readAsDataURL(file);
	} else {
		thumbnail.src = "";
	}
}

/*작품 공개여부*/
function workStatusIs() {

	if (document.getElementById("workPublic").checked) {
		document.getElementById("workStatus").value = "y";
		alert(document.getElementById("workStatus").value);
	} else {
		if (document.getElementById("workPrivate").checked) {
			document.getElementById("workStatus").value = "n";
			alert(document.getElementById("workStatus").value);
		} else {
			alert("작품의 공개여부를 선택해 주세요");
		}
	}
}

/*작품 업로드*/ 
function createWork() {
	if (document.getElementById("uploadWork").files[0] == undefined) {
		alert("작업물을 올려야 합니다");
		return false;
	}
	/*HTML의 FORM 안의 내용을 저장*/
	let form 		= document.getElementById("uploadTabForm");
	/*해당 form을 가지고 FormData 객체 생성*/
	let formData 	= new FormData(form);
	/*XMLHttpRequest 객체 준비*/
	let xhttp 		= new XMLHttpRequest();
	xhttp.open("POST", "createWork", true);
	xhttp.send(formData);
	
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			let result = this.responseText;
			if (result == '0') {
				alert("작품이 업로드 돠지 않았습니다");
			} else {
				alert("작품이 업로드 되었습니다");
				document.getElementById("uploadTabForm").reset();
				document.getElementById("uploadTabImg").src = "resources/img/upload.png";
			}
		}
	}
	return true;
}

/*creator Tab*/
/*작가탭 오픈*/
function openCreatorTab() {
	// 다른탭 OFF
	let tabContent = document.getElementsByClassName("tabContent");
	for (let i = 0; i < tabContent.length; i++) {
		tabContent[i].style.display = "none";
	}
	document.getElementById("creatorTabContent").style.display = "flex";
	// 비우기
	let parentNode = document.getElementById("creatorWorkList");
	while (parentNode.firstChild) {
		parentNode.removeChild(parentNode.firstChild);
	}
	let xhttp = new XMLHttpRequest();
	xhttp.open("GET", "openCreatorTab", true);
	xhttp.send();
	
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			try {
				/*서버에서 Class보낸 정보를 받아 json으로 변환하여 JS객체에 저장 String > JSON */
				let data = JSON.parse(xhttp.responseText);
				document.getElementById("creatorFollowerSum").innerHTML = data["followerSum"];
				document.getElementById("creatorCreateWork").innerHTML = data["workCount"];
				document.getElementById("creatorViewSum").innerHTML = data["viewCount"];
				document.getElementById("creatorReplySum").innerHTML = data["replySum"];
				document.getElementById("creatorLikeSum").innerHTML = data["likeSum"];
				document.getElementById("creatorBuySum").innerHTML = data["buySum"];
				document.getElementById("creatorUserName").innerHTML = data["userName"];
				/*Array > JSON*/
				for (let i = 0; i < data.workList.length; i++) {
					let works = data.workList[i];
					let work = JSON.parse(JSON.stringify(works));
					let img = document.createElement("img");
					img.setAttribute("src", work["work"]);
					img.setAttribute("value", work["workNum"]);
					img.setAttribute("class", "work");
					img.setAttribute("onclick", "workDetail('"+work["workNum"]+"')");
					document.getElementById("creatorWorkList").appendChild(img);
				}
				
			} catch(err) {
				alert(err);
				return;
			}
		}
	}
}

// 검색
function searchForWork() {

	let workTitle = document.getElementById("search_bar").value;
	let tagOption = document.getElementById("search_tag").value;
	let creatorOption = document.getElementById("search_artist").value;
	
	let workGallery = document.getElementById("workGallery")
	let item = workGallery.lastElementChild
	
	if (workTitle == "") {
		main();	
		}
	
	let xhttp = new XMLHttpRequest();
	xhttp.open("POST", "search", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("workTitle="+workTitle+"&tagOption="+tagOption+"&creatorOption="+creatorOption);

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {

			// 갤러리 제거
			while (item) {
				workGallery.removeChild(item);
				item = workGallery.lastElementChild;
			}
			
			let result = JSON.parse(this.responseText);
			
			// 검색결과가 없는 경우
			if (result.length == 0) {
				alert("검색결과가 없습니다");
			}
			
			for (let i = 0; i < result.length; i++) {
				let work = document.createElement("img");
				work.src = result[i]["work"];
				work.alt = result[i]["workNum"];
				work.setAttribute("onclick", "workDetail('"+result[i]["workNum"]+"')");
				
				workGallery.appendChild(work);
			}
		}
	}
}
// 옵션
function tagOption() {
	let search_tag = document.getElementById("search_tag");
	let search_artist = document.getElementById("search_artist");
	if (search_tag.checked == true) {
		search_tag.value = "on";
		search_artist.value = "off";
		search_artist.checked = false;
	} else {
		search_tag.value = "off";
	}
}
function creatorOption() {
	let search_tag = document.getElementById("search_tag");
	let search_artist = document.getElementById("search_artist");
	if (search_artist.checked == true) {
		search_artist.value = "on";
		search_tag.value = "off";
		search_tag.checked = false;
	} else {
		search_artist.value = "off";
	}
}