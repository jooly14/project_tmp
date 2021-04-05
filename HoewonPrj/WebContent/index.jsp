<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
	Class.forName("com.mysql.cj.jdbc.Driver");

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	String url = "jdbc:mysql://localhost:3307/hoewondb";
	String dbId = "root";
	String dbPw = "1234";
	
	String sql = "SELECT * FROM notice ORDER BY regdate DESC LIMIT 5";
	ArrayList<String> titles = new ArrayList<>();
	ArrayList<String> regdates = new ArrayList<>();
	try{
		conn = DriverManager.getConnection(url,dbId, dbPw);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		int i= 0;
		while(rs.next()){
			titles.add(rs.getString("title")) ;
			regdates.add(rs.getString("regdate"));
		}
		pageContext.setAttribute("titles", titles);
		pageContext.setAttribute("regdates", regdates);
	}catch(SQLException e){
		out.println(e.getMessage());
		e.printStackTrace();
	}finally{
		try{
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		}catch(SQLException e1){
			e1.printStackTrace();
		}
	}
%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
	<link href="css/index.css" rel="stylesheet">
</head>
<body>
	<div id="wrap">
	   <jsp:include page="header.jsp"></jsp:include>
	  
		<div class="layer_popup">
			<img src="img/layer_popup.jpg">
			<div class="btn_box">
				<input type="checkbox" id="chk_pop"><label for="chk_pop">24시간 동안 다시 열람하지 않습니다</label><div class="close_pop">닫기 <i class="fas fa-times"></i></div>
			</div>
		</div>
		<div class="p1">
	          <header>
	              <img class="back1" src="img/beatriz-perez-moya-XN4T2PVUUgk-unsplash%20(2).jpg">
	              <h1 class="open-info"><i class="fas fa-door-open"></i> 오늘은 도서관 개관일입니다</h1>
	              <h2 class="time-info"></h2>
	          </header>
	      </div>
	      <div class="p-search">
	          <form action="#">
	              <select name="se1" class="se1">
	                  <option value="o1" selected>자료검색</option>
	                  <option value="o2">표제</option>
	                  <option value="o3">저자</option>
	                  <option value="o4">발행자</option>
	                  <option value="o5">키워드</option>
	              </select>
	              <input type="search" placeholder="검색어를 입력하세요">
	              <input type="submit" value="검색">
	          </form>
	      </div>
	      <div class="p-quickmenu">
	          <div class="menu-wrapin1">
	              <div class="qm2"> <img src="img/time2.png">
	                  <div class="qm1">대출조회<br>반납연기</div>
	              </div>
	              <div class="qm2"> <img src="img/document.png">
	                  <div class="qm1">도서예약<br>조회</div>
	              </div>
	              <div class="qm2"> <img src="img/pen2.png">
	                  <div class="qm1">희망도서<br>신청</div>
	              </div>
	              <div class="qm2"> <img src="img/checkbox3.png">
	                  <div class="qm1">온라인<br>수강신청</div>
	              </div>
	              <div class="qm2"> <img src="img/handshake.png">
	                  <div class="qm1">자원봉사<br>신청</div>
	              </div>
	              <div class="qm2"> <img src="img/box.png">
	                  <div class="qm1">책나래<br>장애인택배</div>
	              </div>
	              <div class="qm2"> <img src="img/delivery.png">
	                  <div class="qm1">타관대출<br>반납</div>
	              </div>
	              <div class="qm2"> <img src="img/time2.png">
	                  <div class="qm1">자율학습실<br>좌석현황</div>
	              </div>
	              <div class="qm2"> <img src="img/desk2.png">
	                  <div class="qm1">권장도서</div>
	              </div>
	              <div class="qm2"> <img src="img/mom1.png">
	                  <div class="qm1">맘편한<br>도서관<br>서비스</div>
	              </div>
	          </div>
	          <div class="menu-wrap">
	              <img src="img/time2.png">
	              <img src="img/document.png">
	              <img src="img/pen2.png">
	              <img src="img/checkbox3.png">
	              <img src="img/handshake.png">
	              <img src="img/box.png">
	              <img src="img/delivery.png">
	              <img src="img/desk2.png">
	              <img src="img/book2.png">
	              <img src="img/mom1.png">
	              <div class="menu-wrapin2">
	                  <div class="qm1">대출조회<br>반납연기</div>
	                  <div class="qm1">도서예약<br>조회</div>
	                  <div class="qm1">희망도서<br>신청</div>
	                  <div class="qm1">온라인<br>수강신청</div>
	                  <div class="qm1">자원봉사<br>신청</div>
	                  <div class="qm1">책나래<br>장애인택배</div>
	                  <div class="qm1">타관대출<br>반납</div>
	                  <div class="qm1">자율학습실<br>좌석현황</div>
	                  <div class="qm1">권장도서</div>
	                  <div class="qm1">맘편한<br>도서관<br>서비스</div>
	              </div>
	          </div>
	      </div>
	      <div class="p2">
	          <div class="notice-title">
	              <h1>공지사항</h1><span><i class="fas fa-plus"></i></span>
	          </div>
	          <table>
				<c:forEach var="i" begin="0" end="4">
			            <tr>
			                <td>${titles.get(i)}</td>
			                <td>${regdates.get(i)}</td>
			            </tr>
           		</c:forEach>
	          </table>
	      </div>
	      <div class="p3">
	          <h1>이용시간 안내</h1>
	          <p>휴무일: 매주 월요일,
	              설/추석 연휴, 관공서의 공휴일(일요일 제외)</p>
	          <div class="tb-wrap">
	              <table>
	                  <tr>
	                      <td><span><i class="fas fa-child"></i></span></td>
	                  </tr>
	                  <tr>
	                      <td>어린이자료실<br>
	                          디지털자료실
	
	                      </td>
	                  </tr>
	                  <tr>
	                      <td>화~일<br>
	                          09:00 ~ 18:00
	                      </td>
	                  </tr>
	                  <tr>
	                      <td>225-7476,7479</td>
	                  </tr>
	              </table>
	              <table>
	                  <tr>
	                      <td><span><i class="fas fa-book"></i></span></td>
	                  </tr>
	                  <tr>
	
	                      <td>제1·2자료실<br>
	                          연속간행물실
	                      </td>
	                  </tr>
	                  <tr>
	                      <td>화~일<br>
	                          09:00 ~ 18:00
	                      </td>
	                  </tr>
	                  <tr>
	                      <td>225-7477</td>
	                  </tr>
	              </table>
	              <table>
	                  <tr>
	                      <td><span><i class="fas fa-chair"></i></span></td>
	                  </tr>
	                  <tr>
	                      <td>자율학습실
	                      </td>
	                  </tr>
	                  <tr>
	                      <td rowspan="3">
	                          미운영
	                      </td>
	                  </tr>
	                  <tr></tr>
	                  <tr></tr>
	              </table>
	          </div>
	      </div>
	      <div class="p4">
	          <div class="sec-wrap1">
	              <section class="sec1">
	                  <h1>도서관 일정</h1>
	                  <table>
	                      <tr>
	                          <td>일</td>
	                          <td>월</td>
	                          <td>화</td>
	                          <td>수</td>
	                          <td>목</td>
	                          <td>금</td>
	                          <td>토</td>
	                      </tr>
	                  </table>
	              </section>
	              <section class="sec2">
	                  <h1>소식 알림</h1>
	                  <div class="banner">
	                      <div class="movebox">
	                          <img src="img/1588126869.jpg"><img src="img/igood.png" style="border:1px solid black;"><img src="img/ban3.png">
	                      </div>
	                      <!--
	               <input type="radio" name="ra1" id="ra11"><label for="ra11"></label>
	               <input type="radio" name="ra1" id="ra12"><label for="ra12"></label>
	               <input type="radio" name="ra1" id="ra13"><label for="ra13"></label>
	      -->
	                  </div>
	              </section>
	              <section class="sec3">
	                  <h1>찾아오시는 길</h1>
	                  <div class="map"><iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3259.2730560878526!2d128.5713597156035!3d35.22457216248714!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x356f320377a1c0c7%3A0x8fec3a9aec608230!2z66eI7IKw7ZqM7JuQ64-E7ISc6rSA!5e0!3m2!1sko!2skr!4v1611914213604!5m2!1sko!2skr" width="300" height="170" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
	                  </div>
	              </section>
	          </div>
	      </div>
	      <div class="p5">
	          <div class="wrap5">
	              <div class="category">
	                  <ul>
	                      <li><label for="ra1">신착도서</label>
	                          <input type="radio" id="ra1" name="radio" checked>
	                          <div class="new">
	                              <table>
	                                  <tr>
	                                      <td><img src="img/new1.jpg"></td>
	                                  </tr>
	                                  <tr>
	                                      <td>물 밖은 여전히 그늘의 무채색이 깨진다</td>
	                                  </tr>
	                                  <tr>
	                                      <td>이현애</td>
	                                  </tr>
	                              </table>
	                              <table>
	                                  <tr>
	                                      <td><img src="img/new1.jpg"></td>
	                                  </tr>
	                                  <tr>
	                                      <td>물 밖은 여전히 그늘의 무채색이 깨진다</td>
	                                  </tr>
	                                  <tr>
	                                      <td>이현애</td>
	                                  </tr>
	                              </table>
	                              <table>
	                                  <tr>
	                                      <td><img src="img/new1.jpg"></td>
	                                  </tr>
	                                  <tr>
	                                      <td>물 밖은 여전히 그늘의 무채색이 깨진다</td>
	                                  </tr>
	                                  <tr>
	                                      <td>이현애</td>
	                                  </tr>
	                              </table>
	                              <table>
	                                  <tr>
	                                      <td><img src="img/new1.jpg"></td>
	                                  </tr>
	                                  <tr>
	                                      <td>물 밖은 여전히 그늘의 무채색이 깨진다</td>
	                                  </tr>
	                                  <tr>
	                                      <td>이현애</td>
	                                  </tr>
	                              </table>
	                              <table>
	                                  <tr>
	                                      <td><img src="img/new1.jpg"></td>
	                                  </tr>
	                                  <tr>
	                                      <td>물 밖은 여전히 그늘의 무채색이 깨진다</td>
	                                  </tr>
	                                  <tr>
	                                      <td>이현애</td>
	                                  </tr>
	                              </table>
	
	                          </div>
	                      </li>
	                      <li><label for="ra2">인기도서</label>
	                          <input type="radio" id="ra2" name="radio">
	                          <div class="best">
	                              <table>
	                                  <tr>
	                                      <td><img src="img/best1.jpg"></td>
	                                  </tr>
	                                  <tr>
	                                      <td>있어빌리티 교양수업</td>
	                                  </tr>
	                                  <tr>
	                                      <td>소피 콜린스</td>
	                                  </tr>
	                              </table>
	                              <table>
	                                  <tr>
	                                      <td><img src="img/best1.jpg"></td>
	                                  </tr>
	                                  <tr>
	                                      <td>있어빌리티 교양수업</td>
	                                  </tr>
	                                  <tr>
	                                      <td>소피 콜린스</td>
	                                  </tr>
	                              </table>
	                              <table>
	                                  <tr>
	                                      <td><img src="img/best1.jpg"></td>
	                                  </tr>
	                                  <tr>
	                                      <td>있어빌리티 교양수업</td>
	                                  </tr>
	                                  <tr>
	                                      <td>소피 콜린스</td>
	                                  </tr>
	                              </table>
	                              <table>
	                                  <tr>
	                                      <td><img src="img/best1.jpg"></td>
	                                  </tr>
	                                  <tr>
	                                      <td>있어빌리티 교양수업</td>
	                                  </tr>
	                                  <tr>
	                                      <td>소피 콜린스</td>
	                                  </tr>
	                              </table>
	                              <table>
	                                  <tr>
	                                      <td><img src="img/best1.jpg"></td>
	                                  </tr>
	                                  <tr>
	                                      <td>있어빌리티 교양수업</td>
	                                  </tr>
	                                  <tr>
	                                      <td>소피 콜린스</td>
	                                  </tr>
	                              </table>
	                          </div>
	                      </li>
	                      <li><label for="ra3">권장도서</label>
	                          <input type="radio" id="ra3" name="radio">
	                          <div class="recommend">
	                              <table>
	                                  <tr>
	                                      <td><img src="img/rec1.jpg"></td>
	                                  </tr>
	                                  <tr>
	                                      <td>우리 뇌를 컴퓨터에 업로드할 수 있을까?</td>
	                                  </tr>
	                                  <tr>
	                                      <td>임창환</td>
	                                  </tr>
	                              </table>
	                              <table>
	                                  <tr>
	                                      <td><img src="img/rec1.jpg"></td>
	                                  </tr>
	                                  <tr>
	                                      <td>우리 뇌를 컴퓨터에 업로드할 수 있을까?</td>
	                                  </tr>
	                                  <tr>
	                                      <td>임창환</td>
	                                  </tr>
	                              </table>
	                              <table>
	                                  <tr>
	                                      <td><img src="img/rec1.jpg"></td>
	                                  </tr>
	                                  <tr>
	                                      <td>우리 뇌를 컴퓨터에 업로드할 수 있을까?</td>
	                                  </tr>
	                                  <tr>
	                                      <td>임창환</td>
	                                  </tr>
	                              </table>
	                              <table>
	                                  <tr>
	                                      <td><img src="img/rec1.jpg"></td>
	                                  </tr>
	                                  <tr>
	                                      <td>우리 뇌를 컴퓨터에 업로드할 수 있을까?</td>
	                                  </tr>
	                                  <tr>
	                                      <td>임창환</td>
	                                  </tr>
	                              </table>
	                              <table>
	                                  <tr>
	                                      <td><img src="img/rec1.jpg"></td>
	                                  </tr>
	                                  <tr>
	                                      <td>우리 뇌를 컴퓨터에 업로드할 수 있을까?</td>
	                                  </tr>
	                                  <tr>
	                                      <td>임창환</td>
	                                  </tr>
	                              </table>
	
	                          </div>
	                      </li>
	                  </ul>
	              </div>
	          </div>
	      </div>
		<jsp:include page="footer.jsp"></jsp:include>
    
	</div>
	
    
	<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/index.js" charset="UTF-8"></script>
</body>
</html>