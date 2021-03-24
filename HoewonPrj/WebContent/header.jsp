<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<div class="link-list">
     <h1 class="h11">도서관<br>바로가기</h1>
     <input type="checkbox" id="link-title">
     <label for="link-title">도서관<br>바로가기</label>
     <div class="link-list2">
         <ul>
             <a href="#"><li>의창</li></a>
             <a href="#"><li>고향의봄</li></a>
             <a href="#"><li>명곡</li></a>
             <a href="#"><li>성산</li></a>
             <a href="#"><li>상남</li></a>
            <a href="#"> <li>마산합포</li></a>
            <a href="#"> <li>마산회원</li></a>
            <a href="#"> <li>내서</li></a>
            <a href="#"> <li>중리초등</li></a>
            <a href="#"> <li>진해</li></a>
            <a href="#"> <li>동부</li></a>
         </ul>
         </div>
         <div class="link-top"><a href="#wrap"><h1>TOP<i class="fas fa-caret-up"></i></h1></a></div>
     </div>
     <div class="navgroup">
     <div class="logo-box">
         <a href="index.jsp"> <img class="logo-img" src="img/logo.png"></a>
     </div>
     <input type="checkbox" id="navg-box">
     <label for="navg-box"><i class="fas fa-bars"></i></label>
     <div class="navg-box">
         <label for="navg-box"><i class="fas fa-times"></i></label>
          <nav class="nav3">
           <ul>
               <li>로그인</li>
               <li>회원가입</li>
               <li>책이음회원가입신청</li>
           </ul>
        </nav>
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
        <nav class="nav4">
          <ul>
         <div class="div1">
             
              <li>
              <input type="radio" name="nav4-ra" id="nav4-r1" checked>
              <label for="nav4-r1">도서관이용<i class="fas fa-chevron-down"></i></label>
              <div class="div2">
              <ul>
                  <a href="libraryuse.jsp#p2"><li><span>이용시간/휴관일</span></li></a>
                  <a href="libraryuse.jsp#p3"><li><span>도서관회원가입안내</span></li></a>
                  <a href="libraryuse.jsp#p4"><li><span>도서대출/반납/예약</span></li></a>
                  <a href="libraryuse.jsp#p5"><li><span>도서관이용안내</span></li></a>
                  <a href="libraryuse.jsp#p6"><li><span>영상정보처리방침</span></li></a>
                  </ul></div>
              </li>
              <li>
               <input type="radio" name="nav4-ra" id="nav4-r2">
              <label for="nav4-r2">도서관소개<i class="fas fa-chevron-down"></i></label>
               <div class="div2">
              <ul>
                 <a href="introduce.jsp#p1-2"><li><span>시설/자료현황</span></li></a>
                  <a href="introduce.jsp#p2"><li><span>제1,2자료실</span></li></a>
                  <a href="introduce.jsp#p3"><li><span>어린이자료실</span></li></a>
                   <a href="introduce.jsp#p4"><li><span>연속간행물실</span></li></a>
                   <a href="introduce.jsp#p5"><li><span>디지털자료실</span></li></a>
                   <a href="introduce.jsp#p6"><li><span>자율학습실</span></li></a>
                    <a href="introduce.jsp#p7"><li><span>조직/업무안내</span></li></a>
                    <a href="introduce.jsp#p8"><li><span>찾아오시는길</span></li></a>
               </ul>
              </div>
              </li>
              <li>
               <input type="radio" name="nav4-ra" id="nav4-r3">
              <label for="nav4-r3">도서관서비스<i class="fas fa-chevron-down"></i></label>
               <div class="div2">
              <ul>
                 <li>전자도서/오디오북</li>
                    <li>영어전자도서</li>
                  <li>책이음서비스</li>
                  <li>타관대출반납</li>
                  <li>책바다(상호대차)</li>
                  <li>책나래(장애인무료택배)</li>
                  <li>사서에게물어보세요</li>
                  <li>북스타트</li>
                  <li>기타서비스</li>
                  <li>맘편한도서관서비스</li>
                  <li>다자녀더책서비스</li>
              </ul>
               </div>
              </li>
              <li>
               <input type="radio" name="nav4-ra" id="nav4-r4">
              <label for="nav4-r4">자료찾기<i class="fas fa-chevron-down"></i></label>
               <div class="div2">
               <ul>
                    <li>자료검색/예약</li>
                    <li>도서대출베스트</li>
                    <li>신착도서</li>
                    <li>권장도서</li>
                    <li>연속간행물검색</li>
                </ul>
                </div>
              </li>
              <li>
                <input type="radio" name="nav4-ra" id="nav4-r5">
              <label for="nav4-r5">문화행사<i class="fas fa-chevron-down"></i></label>
              <div class="div2">
               <ul>
                    <li>도서관주간</li>
                    <li>독서의달</li>
                    <li>독서교실</li>
                    <li>문화강좌</li>
                    <li>온라인수강신청</li>
                    <li>포토갤러리</li>
                    <li>독서회</li>
                    <li>영화상영</li>
                </ul>
                </div>
              </li>
              <li>
                 <input type="radio" name="nav4-ra" id="nav4-r6">
              <label for="nav4-r6">참여마당<i class="fas fa-chevron-down"></i></label>
                
                 <div class="div2">
               <ul>
                    <li>공지사항</li>
                    <li>도서관일정</li>
                    <li>도서관에바란다</li>
                    <li>자원봉사신청</li>
                    <li>분실물센터</li>
                    <li>도서관견학</li>
                    <li>도서기증</li>
                    <li>도서관행사참여</li>
                </ul>
              </div>
              </li>
          </div>
          </ul>
      </nav>
    </div>
     </div>
      <nav class="nav2">
          <ul>
         <div class="div1">
              <a href="libraryuse.jsp"><li>도서관이용</li></a>
              <a href="introduce.jsp"><li>도서관소개</li></a>
              <li>도서관서비스</li>
              <li>자료찾기</li>
              <li>문화행사</li>
              <li>참여마당</li>
          </div>
           <div class="popup">
             <div class="div2">
              <ul>
                  <a href="libraryuse.jsp#p2"><li><span>이용시간/휴관일</span></li></a>
                  <a href="libraryuse.jsp#p3"><li><span>도서관회원가입안내</span></li></a>
                  <a href="libraryuse.jsp#p4"><li><span>도서대출/반납/예약</span></li></a>
                  <a href="libraryuse.jsp#p5"><li><span>도서관이용안내</span></li></a>
                  <a href="libraryuse.jsp#p6"><li><span>영상정보처리방침</span></li></a>
              </ul>
               <ul>
                 <a href="introduce.jsp#p1-2"><li><span>시설/자료현황</span></li></a>
                  <a href="introduce.jsp#p2"><li><span>제1,2자료실</span></li></a>
                  <a href="introduce.jsp#p3"><li><span>어린이자료실</span></li></a>
                   <a href="introduce.jsp#p4"><li><span>연속간행물실</span></li></a>
                   <a href="introduce.jsp#p5"><li><span>디지털자료실</span></li></a>
                   <a href="introduce.jsp#p6"><li><span>자율학습실</span></li></a>
                    <a href="introduce.jsp#p7"><li><span>조직/업무안내</span></li></a>
                    <a href="introduce.jsp#p8"><li><span>찾아오시는길</span></li></a>
               </ul>
              <ul>
                 <li>전자도서/오디오북</li>
                    <li>영어전자도서</li>
                  <li>책이음서비스</li>
                  <li>타관대출반납</li>
                  <li>책바다(상호대차)</li>
                  <li>책나래(장애인무료택배)</li>
                  <li>사서에게물어보세요</li>
                  <li>북스타트</li>
                  <li>기타서비스</li>
                  <li>맘편한도서관서비스</li>
                  <li>다자녀더책서비스</li>
              </ul>
                <ul>
                    <li>자료검색/예약</li>
                    <li>도서대출베스트</li>
                    <li>신착도서</li>
                    <li>권장도서</li>
                    <li>연속간행물검색</li>
                </ul>
                
                <ul>
                    <li>도서관주간</li>
                    <li>독서의달</li>
                    <li>독서교실</li>
                    <li>문화강좌</li>
                    <li>온라인수강신청</li>
                    <li>포토갤러리</li>
                    <li>독서회</li>
                    <li>영화상영</li>
                </ul>
                <ul>
                    <li>공지사항</li>
                    <li>도서관일정</li>
                    <li>도서관에바란다</li>
                    <li>자원봉사신청</li>
                    <li>분실물센터</li>
                    <li>도서관견학</li>
                    <li>도서기증</li>
                    <li>도서관행사참여</li>
                </ul>
               
                </div>
           </div>
          </ul>
      </nav>
      <nav class="nav1">
           <ul>
               <li>로그인</li>
               <li>회원가입</li>
               <li>책이음회원가입신청</li>
           </ul>
        </nav>
        </div>
   