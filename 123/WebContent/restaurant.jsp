<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="restaurant.restaurantDTO" %>
<%@ page import="restaurant.restaurantDAO" %>
<%@ page import="resdat.resdatDAO" %>
<%@ page import="vo.DTO_AD" %>

<!--  
<%
	request.setCharacterEncoding("utf-8");
String id1 = null;
String nick1 = null;
int rnum1 = 1;
if((String)session.getAttribute("id")!=null){
id1 = (String)session.getAttribute("id");
nick1 = (String)session.getAttribute("nick");
}

ArrayList<restaurantDTO> rest;
resdatDAO rd = new resdatDAO();
Double avg_pyung = rd.getPyung(rnum1);
restaurantDAO red = new restaurantDAO();
restaurantDTO restau = red.getRestaurant(rnum1);

%>
-->
<head>
<title>Restaurant</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/gpstylerestau.css">
<link rel="stylesheet" type="text/css" href="css/loginstyle.css" />
<link rel="stylesheet" type="text/css" href="css/restaurant.css" />
<link rel="stylesheet"
	href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link rel='stylesheet' type='text/css'
	href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900,200italic,300italic,400italic,600italic,700italic,900italic'>

<link rel="stylesheet" type="text/css" href="css/Icomoon/style.css" />
<link rel="stylesheet" type="text/css" href="css/animated-header.css" />

<script type="text/javascript" src="_scripts/jquery-2.0.2.min.js"></script>
<script type="text/javascript" src="_scripts/jquery-ui-1.10.4.min.js"></script>
<script type="text/javascript" src="_scripts/jquery.isotope.min.js"></script>
<script type="text/javascript" src="_scripts/animated-header.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="_scripts/login.js"></script>
<script>
  
    //=================입력 Ajax
	function datdung() {
    	if(party.id1.value=="null"){
    		alert("로그인이 필요합니다.");
    	}
    	else if (party.dcontent.value == "") {
			alert("댓글을 입력하여주십시오.");
		} else {
			callAjax();
		}
	}
	function callAjax() {
		$.ajax({
			type : "post",
			url : "./resdat.jsp",
			data : {
				rnum : $('#rnum1').val(),
				dcontent : $('#dcontent').val(),
				id : $('#id1').val(),
				nick : $('#nick1').val(),
				pyung : $('#pyung').val()
			},
			success : whenSuccess,
			error : whenError
		});
	}
	function whenSuccess(resdata) {
		alert("댓글이 입력되었습니다.");
		$("#datgul").html(resdata);
		$('#dcontent').val("");
		$('#pyung').val("1");
	}
	function whenError() {
		alert("Error");
	}
	//=================평가별 정렬 Ajax
	function callAjaxP(s) {
		$.ajax({
			type : "post",
			url : "./resdatP.jsp",
			data : {
				pyung : s
			},
			success : whenSuccessP,
			error : whenErrorP
		});
	}
	function whenSuccessP(resdata) {
		$("#datgul").html(resdata);
	}
	function whenErrorP() {
		alert("Error");
	}
	//=================전체 정렬 Ajax
	function callAjaxALL() {
		$.ajax({
			type : "post",
			url : "./resdat2.jsp",
			data : {
			},
			success : whenSuccessALL,
			error : whenErrorALL
		});
	}
	function whenSuccessALL(resdata) {
		$("#datgul").html(resdata);
	}
	function whenErrorALL() {
		alert("Error");
	}
	
</script>
</head>
<body>
	<%@include file="header.jsp"%>
<%
DTO_AD article = (DTO_AD) request.getAttribute("article");
	String nowPage = (String) request.getAttribute("page");
%>

	<section style="background:#f6f6f6;">
		<form name="party">
		<input type="hidden" id="rnum1" name="rnum1" value="<%=rnum1%>">
			<div style="width: 1900px; height: 86px; align: center;"></div>
			<div class="section">
				<div class="section" style="width: 200px"></div>
				<!--  =================================================================-->
				<div id="conta">
					<div class="jumbotron" style="margin-bottom: 10px;">
						<h1>
							<%=article.getStore() %><font color="orange"><%=avg_pyung %></font>
						</h1>

						<p class="lead">(<%=article.getPostnum() %>)<br><%=article.getHome() %> &nbsp;&nbsp;<%=article.getDethome() %> 
						</p>
						<p>
							<a	class="btn btn-success" href="#" role="button">예약하기</a>
						</p>
					</div>

					<div class="restaur">
						<div id="restau2">
							<img src="restaurantUpload/<%=article.getFile()%>" width="300px" height="250px">
						</div>
						<div class="restauinfo">
							<h4>음식종류</h4>
							<p><%=article.getType() %></p>

							<h4>식당 주소</h4>
							<p><%=article.getPostnum() %>
								<br>
								<%=article.getHome() %> &nbsp;&nbsp;
								<%=article.getDethome() %>
							</p>

							<h4>전화번호</h4>
							<p><%=article.getCell() %></p>

							<h4>주차 여부</h4>
							<p>주차공간없음</p>
						</div>
						<div class="restauinfo">
							<h4>영업시간</h4>
							<p>11:30 - 22:00</p>

							<h4>쉬는시간</h4>
							<p>14:00 - 17:00</p>

							<h4>마지막 주문</h4>
							<p>21:00</p>

							<h4>가격대</h4>
							<p><%=article.getPrice() %></p>
						</div>

					</div>
				</div>
				<!--  =================================================================-->
				<!--  
					<%if((String)session.getAttribute("id")!=null){ %>
					<div class="datdat">
				<div style="text-align: center;">
					<input type="hidden" id="id1" name="id1" value="<%=id1%>"/>
						<input type="hidden" id="nick1" name="nick1" value="<%=nick1 %>" />
						<textarea style="height: 30px; width: 598px;" rows="2"
							placeholder="한줄 평을 남겨주세요." name="dcontent"
							onfocus="this.placeholder = ''" id="dcontent"
							onblur="this.placeholder = '한줄 평을 남겨주세요.'" style="border : none"></textarea>
					</div>
					<div style="border: solid 0px; text-align: center;">
						<select name="pyung" id="pyung">
							<option value="1">★</option>
							<option value="2">★★</option>
							<option value="3">★★★</option>
							<option value="4">★★★★</option>
							<option value="5">★★★★★</option>
						</select>
						<button type="button" class="btn btn-success"
							style="width: 500px;" id="datdu" name="datdu" onclick="datdung()">댓글
							등록</button>
					</div>
					<div class="button"
						style="clear: right; margin: 50px 5px 0px 0px; border: solid 0px; width:600px;">
						<button type="button" style="float: right; margin: 0px 5px 0px 0px;" class="btn btn-success" onclick="callAjaxP('1')">★</button>
						<button type="button" style="float: right; margin: 0px 5px 0px 0px;" class="btn btn-success" onclick="callAjaxP('2')">★★</button>
						<button type="button" style="float: right; margin: 0px 5px 0px 0px;" class="btn btn-success" onclick="callAjaxP('3')">★★★</button>
						<button type="button" style="float: right; margin: 0px 5px 0px 0px;" class="btn btn-success" onclick="callAjaxP('4')" >★★★★</button>
						<button type="button" style="float: right; margin: 0px 5px 0px 0px;" class="btn btn-success" onclick="callAjaxP('5')" >★★★★★</button>
						<button type="button" style="float: right; margin: 0px 5px 0px 0px;" class="btn btn-success" onclick="callAjaxALL()">전체정렬</button>
					</div></div>
				<%}else{%>    <div style="float:left;"><font style="color:red; font-size:0.6cm;">한줄 평을 작성하려면 로그인이 필요합니다.</font></div><% } %>
					
				
				<div id="datgul">
				   <%@include file="resdat2.jsp"%>
				</div>
			</div>

-->

			<!--  ========================오른쪽=========================================-->

			<div id="resta" background="">
				<div id="map">
					<!-- * 카카오맵 - 지도퍼가기 -->
					<!-- 1. 지도 노드 -->
					<div id="daumRoughmapContainer1576114191754"
						class="root_daum_roughmap root_daum_roughmap_landing"></div>

					<!--
	2. 설치 스크립트
	* 지도 퍼가기 서비스를 2개 이상 넣을 경우, 설치 스크립트는 하나만 삽입합니다.
-->
					<script charset="UTF-8" class="daum_roughmap_loader_script"
						src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>

					<!-- 3. 실행 스크립트 -->
					<script charset="UTF-8">
						new daum.roughmap.Lander({
							"timestamp" : "1576114191754",
							"key" : "w7sd",
							"mapWidth" : "500",
							"mapHeight" : "418"
						}).render();
					</script>
				</div>

				<div id="recommand">
					<div id="rt">주변 인기 식당</div>
					<div class="res">
						<div class="pic"></div>

						<div class="rescontent">
							<div class="retitle">족발</div>
							<div class="rename">식당이름 : 족발족발</div>
							<div class="reva">음식 종류 : 족발</div>
							<div class="relo">식당 위치 : 강남</div>
							<div class="repri">가격대 : 인당 12000원</div>
						</div>
					</div>
					<div class="res">
						<div class="pic"></div>
						<div class="rescontent">
							<div class="retitle">족발</div>
							<div class="rename">식당이름 : 족발족발</div>
							<div class="reva">음식 종류 : 족발</div>
							<div class="relo">식당 위치 : 강남</div>
							<div class="repri">가격대 : 인당 12000원</div>
						</div>
					</div>
					<div class="res">
						<div class="pic"></div>
						<div class="rescontent">
							<div class="retitle">족발</div>
							<div class="rename">식당이름 : 족발족발</div>
							<div class="reva">음식 종류 : 족발</div>
							<div class="relo">식당 위치 : 강남</div>
							<div class="repri">가격대 : 인당 12000원</div>
						</div>
					</div>
				</div>
			</div>

		</form>
	</section>

	<%@include file="footer.jsp"%>
</body>

</html>
