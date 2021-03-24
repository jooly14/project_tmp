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

       <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
  />
  <link href="css/index.css" rel="stylesheet">
  <style>
  	.layer_popup{
  		width: 400px;
  		position: absolute;
  		z-index:600;
  		top:100px;
  		left: 5%;
  		background-color: white;
  		display: flex;
  		justify-content: space-between;
  		flex-flow: row wrap;
  		display:none;
  	}
   	.layer_popup img{
  		width: 100%;
  	} 
  	.layer_popup label{
  		font-size:15px;
  	}
  	.layer_popup .btn_box{
  		width:100%;
  		height:30px;
  		background-color: rgb(55,55,55);
  		display: flex;
  		align-items : center;
  		position:relative;
  	}
  	.layer_popup label[for="chk_pop"]{
  		width:70%;
  		color: white;
  		margin-bottom: 1px ;
  	}
  	.layer_popup input{
  		margin: 0 10px;
  	}
  	.layer_popup .close_pop{
  		position: absolute;
  		bottom:2px;
  		right:0;
  		width:15%;
  		font-size:15px;
  		border: none;
		padding:4px;
  		text-align: center;
  		background-color: transparent;
  		color: white;
  	}
  	.layer_popup .close_pop i{
  		color: white;
  		font-size: 15px; 
  	}
  </style>
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
           <div class="qm2"> <img src="img/time2.png"> <div class="qm1">대출조회<br>반납연기</div></div>
           <div class="qm2">  <img src="img/document.png"> <div class="qm1">도서예약<br>조회</div></div>
           <div class="qm2"> <img src="img/pen2.png"> <div class="qm1">희망도서<br>신청</div></div>
           <div class="qm2"> <img src="img/checkbox3.png"> <div class="qm1">온라인<br>수강신청</div></div>
           <div class="qm2"> <img src="img/handshake.png"> <div class="qm1">자원봉사<br>신청</div></div>
           <div class="qm2"> <img src="img/box.png"> <div class="qm1">책나래<br>장애인택배</div></div>
           <div class="qm2"> <img src="img/delivery.png"> <div class="qm1">타관대출<br>반납</div></div>
           <div class="qm2"> <img src="img/time2.png"> <div class="qm1">자율학습실<br>좌석현황</div></div>
           <div class="qm2"> <img src="img/desk2.png"> <div class="qm1">권장도서</div></div>
           <div class="qm2"> <img src="img/mom1.png"> <div class="qm1">맘편한<br>도서관<br>서비스</div></div>
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

       <div class="notice-title"><h1>공지사항</h1><span><i class="fas fa-plus"></i></span></div>
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
<!--       <section class="sec1">-->
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
             <img src="img/1588126869.jpg"><img src="img/igood.png" style="border:1px solid black;"><img src="img/ban3.png"></div>
<!--
             <input type="radio" name="ra1" id="ra11"><label for="ra11"></label>
             <input type="radio" name="ra1" id="ra12"><label for="ra12"></label>
             <input type="radio" name="ra1" id="ra13"><label for="ra13"></label>
-->
             </div></section>
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
    
     <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js" type="text/javascript"></script>
       <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js"></script>
     <script>
        $(function(){
            $(".p2 td").hover(function(){
                $(this).css("font-weight","bold"); 
               $(this).css("cursor","pointer"); 
                if($(this).index()==0){
                    $(this).next().css("font-weight","bold"); 
                }else if($(this).index()==1){
                     $(this).prev().css("font-weight","bold"); 
                }
            },function(){
                 $(this).css("font-weight","normal"); 
                 $(this).css("cursor","normal"); 
                if($(this).index()==0){
                    $(this).next().css("font-weight","normal"); 
                }else if($(this).index()==1){
                     $(this).prev().css("font-weight","normal"); 
                }
            });
            $(".p-quickmenu img").hover(function(){
                $(".menu-wrapin2 .qm1").eq($(this).index()).css("color","#295E52");
                $(".menu-wrapin2 .qm1").eq($(this).index()).css("text-decoration","underline");
                $(this).css("cursor","pointer");
            },function(){
                  $(".menu-wrapin2 .qm1").eq($(this).index()).css("color","black");
                $(".menu-wrapin2 .qm1").eq($(this).index()).css("text-decoration","none");
            });
             $(".p-quickmenu .qm1").hover(function(){
                $(this).css("color","#295E52");
                $(this).css("text-decoration","underline");
                $(this).css("cursor","pointer");
            },function(){
                  $(this).css("color","black");
                $(this).css("text-decoration","none");
            });
            $(".p5 .category ul li label").click(function(){
                var idx = $(this).text();
                $(".p5 .category ul li label").css("color","black");
                $(this).css("color","#295E52");
                 $(".new").css("display","none");
                 $(".best").css("display","none");
                $(".recommend").css("display","none");  $(".new").css("opacity","0");
                 $(".best").css("opacity","0");
                $(".recommend").css("opacity","0");
                if(idx=="신착도서"){
                    $(".new").css("display","flex");
                    $(".new").animate({
                        opacity:"1",
                    },500);
                }else if(idx=="인기도서"){
                     $(".best").css("display","flex");
                    $(".best").animate({
                        opacity:"1",
                    },500);
                }else if(idx=="권장도서"){
                     $(".recommend").css("display","flex");
                    $(".recommend").animate({
                        opacity:"1",
                    },500);
                }
            });
             $(".p5 .category ul li label").hover(function(){
                 $(this).css("color","#295E52");
             },function(){
                 var arr = [".new",".best",".recommend"];
                 for(var i=0;i<3;i++){
                     if($(arr[i]).css("display")=="flex"){
                         arr = i;
                         break;
                     }
                 }
                 if($(".p5 li label").eq(i).text()!=$(this).text()){
                    $(this).css("color","black");
                 }
             });
            $(".p5 .category ul li label").eq(0).css("color","#295E52");
            
            $(".link-top a").attr("href","#");
            $(".link-top a").click(function(e){
                e.preventDefault();
                $("html, body").animate({
                    scrollTop:"0"
                },500);
            });
            
            
            //////////달력////////////////////////////////////
            var today = new Date();
            var month = today.getMonth()+1;
            curday = new Date(today.getFullYear(),month-1,1);
            if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){ //31일까지
                var d = 1;
                var endday = 31;
               
            }else if(month==2){ //28일까지
                var d = 1;
                var endday = 28;
            }else{
                var d = 1;
                var endday = 30;
            }
             for(;d<endday+1;){
                var tr1 = $("<tr></tr>");
                if(d==1){
                    for(var i=0;i<7;i++){
                        var elm = $("<td></td>");
                        if(i>=curday.getDay()){
                            elm.text(d++);
                            if(i==1){
                                elm.addClass("holiday");
                                elm.css("position","relative");
                            }
                            if(d==today.getDate()){
                                elm.addClass("today");    
                            }
                        }
                        tr1.append(elm);
                    }
                }else{
                    for(var i=0;i<7;i++){
                        var elm = $("<td></td>");
                        if(d<=endday){
                            elm.text(d++);
                            if(i==1){
                                elm.addClass("holiday");
                                elm.css("position","relative");
                            }
                            if(d==today.getDate()){
                                elm.addClass("today");    
                            }
                        }
                        tr1.append(elm);
                    }
                }
                $(".sec1 table").append(tr1);
            }
            $(".sec1 table tr td").mouseenter(function(){
                $(this).css("cursor","pointer"); 
            });
            
            //////// 첫페이지 개관 여부 알림///////////////////
            if(today.getDay()==1){
                $(".p1 h1").html("<i class='fas fa-door-closed'></i> 오늘은 도서관 정기 휴관일입니다");
            }else{
                $(".p1 h1").html("<i class='fas fa-door-open'></i> 오늘은 도서관 개관일입니다");
            }
            ////////배너/////////////////////////////////
            var myInterval1;
            function startInterval(){
                myInterval1 = setInterval(function(){
                    $(".movebox").stop().animate({
                        left:"-300"
                    },500,function(){
                        var elm = $(this).children().eq(0).detach();
                        $(".movebox").append(elm);
                        $(".movebox").css("left","0");
                    });    
                },2000);
               
            }
            startInterval();
              var elm = $("<div></div");
                elm.text("STOP");
                elm.addClass("layer1");
                elm.css("background-color","rgba(2,59,72,.8)");
                elm.css("font-size","50px");
                elm.css("font-weight","bold");
                elm.css("color","white");
                elm.css("z-index","500");
                elm.css("width","300px");
                elm.css("height","180px");
                elm.css("position","absolute");
                elm.css("top","0");
                elm.css("left","0");
                elm.css("opacity","0");
                elm.css("display","none");
                elm.css("justify-content","center");
                elm.css("align-items","center");
                elm.css("cursor","pointer");
                $(".sec2 .banner").append(elm);
            $(".sec2").hover(function(){
                $(".layer1").css("display","flex");
                $(".layer1").stop().animate({
                    opacity:"1"
                },500);
            },function(){
                 $(".layer1").stop().animate({
                    opacity:"0"
                },500,function(){
                      $(".layer1").css("display","none");
                 });
            });
            $(".layer1").click(function(){
                if($(this).text()=="STOP"){
                    clearInterval(myInterval1);
                    $(this).text("START");
                }else{
                    startInterval();
                    $(this).text("STOP");
                }
            });
            $(".layer_popup label").mouseenter(function(){
            	$(this).css("cursor","pointer"); 
            });
            $(".close_pop").mouseenter(function(){
				$(this).css("cursor","pointer");            	
            });
            $(".close_pop").click(function(){
            	if($("#chk_pop").is(":checked")){
            		$.cookie("pop","false",{expires:1});
            	}
            	$(this).parent().parent().fadeOut();
            });
          	if($.cookie("pop")==undefined){
          		$(".layer_popup").css("display","flex");
          		$(".layer_popup").draggable();
          	}
          	$(".holiday").hover(function(){
          		var obj = $("<span></span>");
          		obj.css("background-color","gainsboro").css("width","70px").css("height","30px").css("position","absolute").css("z-index","100").css("top","25px").css("left","0px").css("font-size","15px").css("display","flex").css("justify-content","center").css("align-items","center");
          		obj.text("정기 휴무");
          		$(this).append(obj);
          	},function(){
          		$(this).children("span").detach();
          	});
        });
    </script>
</body>
</html>