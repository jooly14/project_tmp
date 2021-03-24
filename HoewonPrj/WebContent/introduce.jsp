<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
  	<link rel="stylesheet" href="css/introduce.css">
</head>
<body>
  <div id="wrap">
     <jsp:include page="header.jsp"></jsp:include>
        
             <div class="p1" id="p1">
                 <div class="b-img">
                      <h1>도서관 소개</h1>
                      <form action="#">
                      <div class="sear-box">
                       <select name="se1" class="se1">
                           <option value="o1" selected>자료검색</option>
                           <option value="o2">표제</option>
                           <option value="o3">저자</option>
                           <option value="o4">발행자</option>
                           <option value="o5">키워드</option>
                       </select>
                       <input type="search" placeholder="검색어를 입력하세요">
                       <input type="submit" value="검색">
                      </div>
       </form>
                 
                 </div>
             </div>
             <div class="page-index">
         <div class="page-index2">
           
             <ul>
                 <a href="#p1-2"><li>시설/자료현황</li></a>
                 <a href="#p2"><li>제1,2자료실</li></a>
                 <a href="#p3"><li>어린이자료실</li></a>
                 <a href="#p4"><li>연속간행물실</li></a>
                 <a href="#p5"><li>디지털자료실</li></a>
                 <a href="#p6"><li>자율학습실</li></a>
                 <a href="#p7"><li>조직/업무안내</li></a>
                 <a href="#p8"><li>찾아오시는길</li></a>
             </ul>
         </div>
     </div>
             
             
             
             <div class="p1-2" id="p1-2">
               <div class="wrap">
                <h2>시설/자료현황</h2>
                <div class="b1">
                   <img src="img/library-img.png">
                   
                    <p><br>위치 : 창원시 마산회원구 3ㆍ15대로 558(회원동 167-1)</p>
                    <p>구조 : 지하 1층, 지상 3층</p>
                    <p>대지면적 : 5,173㎡</p>
                    <p>연면적 : 2,007㎡</p>
                    <p>총 좌석 : 404석</p>
                </div>
                <div class="b2">
                  
                   <h4>층별 안내도</h4>
                   <div class="imgbox">
                      <span class="btnX">X</span>
                       <img class="fac-img1" src="img/img_fac_b1.png">
                       <img class="fac-img2" src="img/img_fac_1f.png">
                       <img class="fac-img3" src="img/img_fac_2f.png">
                       <img class="fac-img4" src="img/img_fac_3f.png">
                   </div>
                    <p>지하 1층</p>
                    
                    <p>1층</p>
                    
                    <p>2층</p>
                    
                    <p>3층</p>
                    <p>각 층마다 안내도를 보시려면 해당 층을 클릭해보세요~</p>
                    <i class="fas fa-bookmark"></i>
                </div>
                 <h3><i class="fas fa-feather-alt"></i> 층별 시설현황</h3>
                 <table>
                     <tr>
                        <td>층</td>
                         <td>시설명</td>
                         <td>좌석수</td>
                         <td>시설안내</td>
                     </tr>
                     <tr>
                       <td rowspan="3">지하 1층</td>
                       <td>시청각실</td>
                       <td>52</td>
                       <td>각종 행사 및 영화상영</td>
                     </tr>
                     <tr>
                     <td>소회의실</td>
                     <td>16</td>
                     <td>-</td>
                     </tr>
                     <tr>
                      <td>휴게실</td>
                      <td>20</td>
                      <td>휴식 및 자유 학습</td>
                      </tr>
                     <tr>
                         <td rowspan="2">1층</td>
                         <td>어린이자료실</td>
                         <td>40</td>
                         <td>유아 및 아동 도서 열람, 대출</td>
                     </tr>
                     <tr>
                     <td>사무실</td>
                     <td>-</td>
                     <td>도서관 사무, 도서관 자료 분류 및 정리</td>
                     </tr>
                     <tr>
                         <td rowspan="4">2층</td>
                         <td>제1자료실</td>
                         <td>-</td>
                         <td>일반도서 열람 및 대출, 참고자료 및 향토자료 열람</td>
                     </tr>
                     <tr>
                         <td>연속간행물실</td>
                         <td>14</td>
                         <td>신문, 잡지 등 열람</td>
                     </tr>
                     <tr>
                         <td>자율학습실(여자)</td>
                         <td>98</td>
                         <td>개인 자율 학습</td>
                     </tr>
                     <tr>
                     <td>휴게실</td>
                     <td>-</td>
                     <td>휴식공간</td>
                     </tr>
                     <tr>
                         <td rowspan="4">3층</td>
                         <td>제2자료실</td>
                         <td>16</td>
                         <td>일반도서 열람 및 대출</td>
                     </tr>
                     <tr>
                         <td>디지털자료실</td>
                         <td>34</td>
                         <td>인터넷, 문서편집, DVD, 전자도서 이용</td>
                     </tr>
                     <tr>
                         <td>자율학습실(남자)</td>
                         <td>114</td>
                         <td>개인 자율 학습</td>
                     </tr>
                     <tr>
                         <td>휴게실</td>
                         <td>-</td>
                         <td>휴식 공간</td>
                     </tr>
                 </table>
                 <h3><i class="fas fa-feather-alt"></i> 자료 현황</h3>
                    <h4><i class="far fa-check-circle"></i> 도서 현황</h4>
                     <table class="tb2 tb3">
                        <caption>2021년 1월 10일 현재</caption>
                         <tr>
                             <td>구분</td>
                             <td>총류</td>
                             <td>철학</td>
                             <td>종교</td>
                             <td>사회<br>과학</td>
                             <td>순수<br>과학</td>
                             <td>기술<br>과학</td>
                             <td>예술</td>
                             <td>어학</td>
                             <td>문학</td>
                             <td>역사</td>
                             <td>계</td>
                         </tr>
                            <tr>
                             <td>마산<br>회원<br>도서관</td>
                             <td>7,365</td>
                             <td>10,890</td>
                             <td>5,351</td>
                             <td>32,638</td>
                             <td>13,562</td>
                             <td>15,201</td>
                             <td>8,594</td>
                             <td>11,813</td>
                             <td>70,837</td>
                             <td>16,138</td>
                             <td>192,389</td>
                         </tr>
                     </table>
                <h4><i class="far fa-check-circle"></i> 연속간행물 현황</h4>
                       <table class="tb2">
                        <caption>2021년 1월 10일 현재</caption>
                         <tr>
                             <td>구분</td>
                             <td colspan="3">총계</td>
                             <td colspan="3">잡지소계</td>
                             <td colspan="3">국내잡지</td>
                             <td colspan="3">국외잡지</td>
                             <td colspan="3">신문</td>
                             <td colspan="3">학회지</td>
                         </tr>
                            <tr>
                             <td rowspan="2">계</td>
                             <td>계</td>
                             <td>구입</td>
                             <td>기증</td>
                             <td>계</td>
                             <td>구입</td>
                             <td>기증</td>
                             <td>계</td>
                             <td>구입</td>
                             <td>기증</td>
                             <td>계</td>
                             <td>구입</td>
                             <td>기증</td>
                             <td>계</td>
                             <td>구입</td>
                             <td>기증</td>
                             <td>계</td>
                             <td>구입</td>
                             <td>기증</td>
                         </tr>
                             <tr>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                         </tr>
                     </table>
                <h4><i class="far fa-check-circle"></i> 비도서 현황</h4>
                        <table class="tb2  tb4">
                        <caption>2021년 1월 10일 현재</caption>
                         <tr>
                             <td>구분</td>
                             <td>비도서계</td>
                             <td>DVD</td>
                             <td>E-BOOK</td>
                             <td>웹오디오북</td>
                             <td>오디오북</td>
                             <td>CD</td>
                             <td>CD-ROM</td>
                             <td>LD</td>
                             <td>LP</td>
                             <td>TAPE</td>
                             <td>VT</td>
                             <td>MAP</td>
                         </tr>
                            <tr>
                             <td>총계</td>
                             <td>6,584</td>
                             <td>5,002</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>1,554</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>0</td>
                             <td>28</td>
                             <td>0</td>
                         </tr>
                     </table>
                 </div>
             </div>
             
             
             
             <div class="p2" id="p2">
               <div class="wrap">
                <h2>제1,2자료실</h2>
                 
<!--                 <img src="img/book-img%20(2).png">-->
                 <div class="b1">
                    <img src="img/img_ready.png">
                     <p>일반도서 열람 및 대출</p>
                     <p>참고자료 열람(관외 대출 불가)</p>
                     <p>전화번호 : 225-7477</p>
                     <p>위치 : 2~3층</p>
                     <h4>이용시간</h4>
                     <p>화~일요일 09:00~18:00</p>
                     
                 </div>
                 <div class="b2">
                 <i class="fas fa-bookmark"></i>
                 <h4>이용대상</h4>
                     <p>중학생 이상 이용 가능</p>
                     
                 <h4>이용안내</h4>
                     <table>
                         <tr>
                             <td>층별</td>
                             <td>제1자료실<br>(2층)</td>
                             <td>제2자료실<br>(2층)</td>
                         </tr>
                         <tr>
                             <td>소장<br>도서</td>
                             <td>일반도서(언어, 문학, 역사)<br>권장도서, 참고도서, 점자도서</td>
                             <td>일반도서(총류, 철학, 종교, 사회과학, 자연과학, 기술과학, 예술)</td>
                         </tr>
                         <tr>
                             <td>이용<br>서비스</td>
                             <td>도서 대출 반납, 자료 검색</td>
                             <td>회원증 발급,<br> 자료 검색,<br> 자료 복사</td>
                         </tr>
                         <tr>
                             <td>비고</td>
                             <td colspan="2">3층은 2층 정문을 경유하여<br> 출입 가능</td>
                         </tr>
                     </table>
                 </div>
                 
                 </div>
             </div>
             <div class="p3" id="p3">
                 <div class="wrap">
                    <h2>어린이 자료실</h2>
                     <div class="b1">
                    <img src="img/img_ready.png">
                     <p>유아 및 어린이 도서 열람, 대출</p>
                     <p>전화번호 : 225-7476</p>
                     <p>위치 : 1층</p>
                     <h4>이용시간</h4>
                     <p>화~일요일 09:00~18:00</p>
                     
                 </div>
                 <div class="b2">
                 <i class="fas fa-bookmark"></i>
                 <h4>이용대상</h4>
                     <p>유아, 초등학생</p>
                     
                 <h4>이용안내</h4>
                    <ul>
                        <li>유아 및 어린이 도서 열람, 대출</li>
                        <li>어린이 참고자료 열람<br>(관외 대출 안됨)</li>
                        <li>어린이 정기간행물 열람<br>(관외 대출 안됨)</li>
                        <li>도서관 회원증 발급</li>
                        <li>자료 검색</li>
                    </ul>
                 </div>
                 </div>
             </div>
             <div class="p4" id="p4">
                 <div class="wrap">
                     <h2>연속 간행물실</h2>
                      <div class="b1">
                    <img src="img/img_ready.png">
                     <p>신문, 잡지 등 각종 간행물 열람</p>
                     <p>위치 : 2층</p>
                     <h4>이용시간</h4>
                     <p>화~일요일 09:00~18:00</p>
                     
                 </div>
                 <div class="b2">
                 <i class="fas fa-bookmark"></i>
                 <h4>이용대상</h4>
                     <p>중학생 이상 이용 가능</p>
                     
                 <h4>이용안내</h4>
                    <ul>
                        <li>신문, 잡지, 향토자료, 취업정보자료 등 소장</li>
                        <li>연속간행물실 내 모든 자료는 대출되지 않으며, 열람과 복사는 가능(단, 과월호 잡지는 제외)</li>
                        <li>과월호 잡지대출<br> - 대상 : 3개월 경과된 과월호 잡지<br>
- 권수 및 기한 : 1인 3권 2주간<br>
(* 1회에 한하여 1주일 연장 가능, 도서 대출 권수에 미포함)</li>
                        


                    </ul>
                 </div>
                 </div>
             </div>
               <div class="p5" id="p5">
                 <div class="wrap">
                     <h2>디지털 자료실</h2>
                      <div class="b1">
                    <img src="img/img_ready.png">
                     <p>인터넷 자료검색,문서편집,디지털 자료열람(관외대출 안됨)</p>
                     <p>전화번호 : 225-7479</p>
                     <p>위치 : 3층</p>
                     <h4>이용시간</h4>
                     <p>화~일요일 09:00~18:00</p>
                       <h4>이용대상</h4>
                     <p>중학생 이상 이용 가능</p>
                      <h4>컴퓨터 이용방법</h4>
                    <ul>
                        <li>좌석 예약 컴퓨터에서 회원가입하여 해당 좌석 예약 후 이용</li>
                        <li>1인 2시간까지 사용(2시간 연장 가능)</li>
                    </ul>
                 </div>
                 <div class="b2">
                 <i class="fas fa-bookmark"></i>
               
                 <h4>이용안내</h4>
                    <ul>
                        <li>디지털 자료(CD/DVD/전자도서), 문서 편집, 인터넷 검색 총 28석</li>
                        <li>노트북 2석(※ 개인 아이디는 지원하지 않음)</li>
                        <li>원문 서비스 : 국회도서관, 국립중앙도서관</li>
                        <li>프린터 : 복사카드 구매하여 사용(A4 1장당 60원)</li>
                        <li>스캐너 : 무료 이용</li>
                    </ul>
                   
                    <h4>CD/DVD 이용방법</h4>
                    <ul>
                        <li>안내데스크에 신분증을 제시하고 자료를 대출받아 지정 좌석에서 이용</li>
                        <li>DVD는 1일 1편 열람 가능</li>
                    </ul>
                    <h4>유의사항</h4>
                    <ul>
                        <li>타인 명의로 컴퓨터 예약 및 사용 금지</li>
                        <li>컴퓨터 사용 시 채팅, 게임, 음란, 유해사이트 등 이용 금지</li>
                    </ul>
                 </div>
                 </div>
             </div>
               <div class="p6" id="p6">
                 <div class="wrap">
                     <h2>자율학습실</h2>
                      <div class="b1">
                    <img src="img/img_ready.png">
                     <p>도서 및 자료를 열람할 수 있으며 학습공간으로도 제공</p>
                     <p>전화번호 : 관리담당 055-225-7461<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1층 안내실 055-225-7469</p>
                     <p>위치 : 2층,3층</p>
                     <h4>이용시간</h4>
                     <p>월~일 09:00~18:00(신정,설,추석연휴 휴관)</p>
                     <h4>이용대상</h4>
                     <p>중학생 이상 이용 가능</p>
                 </div>
                 <div class="b2">
                 <i class="fas fa-bookmark"></i>
                 
                  <h4>이용좌석수</h4>
                <ul>
                    <li>여자 학습실(2층) : 98석(칸막이)</li>
                    <li>남자 학습실(3층) : 114석(칸막이)</li>
                </ul>
                 <h4>이용안내</h4>
                    <ul>
                        <li>1층 로비(안내실 옆) 좌석발급기에서 좌석번호표 발급 후 이용</li>
                        <li>5시간마다 연장 신청 (종료시간 1시간 전부터 연장 가능)</li>
                        <li>퇴실 시 좌석발급기에서 반납 처리</li>
                    </ul>
                   <h4>유의사항</h4>
                    <ul>
                        <li>1인 1좌석 좌석번호표 발급 준수</li>
                        <li>낙서, 잡담, 음식물 반입, 노트북 사용 금지</li>
                        <li>개인소지품 관리 유의(도서관에서는 분실 및 도난에 대해 책임지지 않음)</li>
                    </ul>
                  


                 </div>
                 </div>
             </div>
             
             <div class="p7" id="p7">
                 <div class="wrap">
                     <h2>조직/업무안내</h2>
                     <table>
                         <tr>
                             <td>담당명</td>
                             <td>직원명</td>
                             <td>전화번호</td>
                             <td>담당업무</td>
                         </tr>
                         <tr>
                             <td>관장</td>
                             <td>이성호</td>
                             <td>225-7460</td>
                             <td>도서관업무 총괄</td>
                         </tr>
                         <tr>
                             <td rowspan="5">관리담당</td>
                             <td>김종식</td>
                             <td>225-7462</td>
                             <td>관리담당업무 총괄</td>
                         </tr>
                         <tr>
                             <td>조선영</td>
                             <td>225-7463</td>
                             <td>예산, 회계</td>
                         </tr>
                         <tr>
                             <td>최인수</td>
                             <td>225-7464</td>
                             <td>청사 및 시설 제반 관리</td>
                         </tr>
                         <tr>
                             <td>박지혜</td>
                             <td>225-7466</td>
                             <td>서무</td>
                         </tr>
                         <tr>
                             <td>강재규,전영기,이수정</td>
                             <td>225-7469</td>
                             <td>안내,치안유지</td>
                         </tr>
                         <tr>
                             <td rowspan="6">사서담당</td>
                             <td>김종범</td>
                             <td>225-7472</td>
                             <td>사서담당 업무 총괄</td>
                         </tr>
                         <tr>
                             <td>김민화</td>
                             <td>225-7473</td>
                             <td>행사, 문화강좌</td>
                         </tr>
                         <tr>
                             <td>김지혜</td>
                             <td>225-7474</td>
                             <td>자료구입,정리,통계</td>
                         </tr>
                         <tr>
                             <td>강유민</td>
                             <td>225-7467</td>
                             <td>1,2자료실 및 연속간행물실, 기타인력관리</td>
                         </tr>
                         <tr>
                             <td>박성식</td>
                             <td>225-7476</td>
                             <td>어린이자료실 및 어린이 문화행사 운영</td>
                         </tr>
                         <tr>
                             <td>김선혜</td>
                             <td>225-7475</td>
                             <td>도서 정리</td>
                         </tr>
                     </table>
                 </div>
             </div>
                          
             <div class="p8" id="p8">
                 <div class="wrap">
                     <h2>찾아오시는 길</h2>
   <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3259.2730560878526!2d128.5713597156035!3d35.22457216248714!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x356f320377a1c0c7%3A0x8fec3a9aec608230!2z66eI7IKw7ZqM7JuQ64-E7ISc6rSA!5e0!3m2!1sko!2skr!4v1611914213604!5m2!1sko!2skr"  frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
                     <h3><i class="fas fa-feather-alt"></i> 오시는 길</h3>
                     <ul>
                         <li>주소 : (51324) 경상남도 창원시 마산회원구 3ㆍ15대로 558(회원동)</li>
                         <li>전화 : 055-225-7471</li>
                         <li>팩스 : 055-225-4804</li>
                     </ul>
                 </div>
             </div>
    	<jsp:include page="footer.jsp"></jsp:include>
        
    </div>
    
    
          <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js" type="text/javascript"></script>
     <script>
        $(function(){
            $(".link-top a").attr("href","#");
            $(".link-top a").click(function(e){
                e.preventDefault();
                $("html, body").stop().animate({
                    scrollTop:"0"
                },500);
            });
            
            $(window).on("scroll resize",function(){
                if($(window).width()>1200){
                    if($("html, body").scrollTop()>=200){
                        $(".page-index").css("position","fixed");
                        $(".page-index").css("top","80px");
                    }else{
                         $(".page-index").css("position","inherit");
                        $(".page-index").css("top","0px");
                    }
                      $(".page-index").css("height","70px");
                    $(".page-index li").css({
                                            "font-size":"15px",
                                            "height":"30px",
                                            "padding-top":"10px"
                                            })
                }else if($(window).width()>800){
                          if($("html, body").scrollTop()>=200){
                        $(".page-index").css("position","fixed");
                        $(".page-index").css("top","80px");
                    }else{
                         $(".page-index").css("position","inherit");
                        $(".page-index").css("top","0px");
                    }
                      $(".page-index").css("height","100px");
                    $(".page-index li").css({
                                            "font-size":"15px",
                                            "height":"30px",
                                            "padding-top":"10px"
                                            })
                }else{
                   
                      $(".page-index").css("position","fixed");
                    $(".page-index").css("top","80px");
                    $(".page-index").css("height","80px");
                    $(".page-index li").css({
                                            "font-size":"13px",
                                            "height":"25px",
                                            "padding-top":"5px"
                                            })
                }
                     $(".page-index li").css("font-weight","normal");
                if($("html, body").scrollTop()>=$(".p8").offset().top-50){
                    $(".page-index li").eq(7).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p7").offset().top-50){
                    $(".page-index li").eq(6).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p6").offset().top-50){
                    $(".page-index li").eq(5).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p5").offset().top-50){
                    $(".page-index li").eq(4).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p4").offset().top-50){
                    $(".page-index li").eq(3).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p3").offset().top-50){
                    $(".page-index li").eq(2).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p2").offset().top-50){
                    $(".page-index li").eq(1).css("font-weight","bold");
                }else if($("html, body").scrollTop()>=$(".p1-2").offset().top-50){
                    $(".page-index li").eq(0).css("font-weight","bold");
                }
               $(".p1-2 .imgbox span").css("transform","translate("+($(window).innerWidth()*0.7/2-$(".p1-2 .imgbox span").innerWidth()/1.3)+"px,-"+($(window).innerHeight()*0.7/2-$(".p1-2 .imgbox span").innerHeight()/3)+"px)");

            });
           if($(window).width()>1200){
                     if($("html, body").scrollTop()>=200){
                        $(".page-index").css("position","fixed");
                        $(".page-index").css("top","80px");
                         
                    }else{
                         $(".page-index").css("position","inherit");
                        $(".page-index").css("top","0px");
                    }
                     $(".page-index").css("height","70px");
                    $(".page-index li").css({
                                            "font-size":"15px",
                                            "height":"30px",
                                            "padding-top":"10px"
            
                    });
               
               
            }else if($(window).width()>800){
                     
                  if($("html, body").scrollTop()>=200){
                        $(".page-index").css("position","fixed");
                        $(".page-index").css("top","80px");
                    }else{
                         $(".page-index").css("position","inherit");
                        $(".page-index").css("top","0px");
                    }
                      $(".page-index").css("height","100px");
                    $(".page-index li").css({
                                            "font-size":"15px",
                                            "height":"30px",
                                            "padding-top":"10px"
                                            });
                     
            }else{
                      $(".page-index").css("position","fixed");
                    $(".page-index").css("top","80px");
                    $(".page-index").css("height","80px");
                    $(".page-index li").css({
                                            "font-size":"13px",
                                            "height":"25px",
                                            "padding-top":"5px"
                                            });
            
            }
            $(".page-index a").click(function(e){
                e.preventDefault();
//                $(".page-index li").css("font-weight","normal");
//                $(this).children("li").css("font-weight","bold");
                var idx = $(this).index();
                if(idx==0){
                    idx = ".p1-2";
                }else{
                    idx = ".p"+(idx+1);
                }
                $("html, body").stop().animate({
                    scrollTop :$(idx).offset().top-50
                },500);
            });
//           
            $(".page-index a").hover(function(){
                $(this).children("li").css("font-weight","bold");
            },function(){
                 $(".page-index li").css("font-weight","normal");
                if($("html, body").scrollTop()>=$(".p8").offset().top-50){
                    $(".page-index li").eq(7).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p7").offset().top-50){
                    $(".page-index li").eq(6).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p6").offset().top-50){
                    $(".page-index li").eq(5).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p5").offset().top-50){
                    $(".page-index li").eq(4).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p4").offset().top-50){
                    $(".page-index li").eq(3).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p3").offset().top-50){
                    $(".page-index li").eq(2).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p2").offset().top-50){
                    $(".page-index li").eq(1).css("font-weight","bold");
                }else if($("html, body").scrollTop()>=$(".p1-2").offset().top-50){
                    $(".page-index li").eq(0).css("font-weight","bold");
                }
            });
            
            /////////////////층별 안내도 클릭시 보여주는 화면////////////////
            var imgboxidx;
            $(".p1-2 .b2 p").not($(".p1-2 .b2 p:nth-child(7)")).click(function(){
                
                imgboxidx = $(this).index();
                 $(".imgbox").css("display","flex");
                $(".imgbox").animate({
                    height:"100vh",
                },500,function(){
                     $(".imgbox img").eq(imgboxidx-2).css("display","block");
                    $(".imgbox img").eq(imgboxidx-2).animate({
                        opacity:"1"
                    },500);
                });
            });
            $(".imgbox span").click(function(){
                $(".imgbox img").eq(imgboxidx-2).animate({
                   opacity:"0"
                },500,function(){
                     $(".imgbox img").eq(imgboxidx-2).css("display","none");
                    $(".imgbox").animate({
                        height:"0"
                    },500,function(){
                         $(".imgbox").css("display","none");
                    });
                });
            });
            $(".imgbox span").css("cursor","pointer");
            $(".p1-2 .imgbox span").css("z-index","30");
               $(".p1-2 .imgbox span").css("transform","translate("+($(window).innerWidth()*0.7/2-$(".p1-2 .imgbox span").innerWidth()/1.3)+"px,-"+($(window).innerHeight()*0.7/2-$(".p1-2 .imgbox span").innerHeight()/3)+"px)");
        });
    </script>
</body>
</html>