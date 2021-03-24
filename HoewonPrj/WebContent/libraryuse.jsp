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
	<link href="css/libraryuse.css" rel="stylesheet">
 
</head>
<body>
  <div id="wrap">
     <jsp:include page="header.jsp"></jsp:include>
      <nav class="nav1">
           <ul>
               <li>로그인</li>
               <li>회원가입</li>
               <li>책이음회원가입신청</li>
           </ul>
        </nav>
        </div>
        
             <div class="p1" id="p1">
                 <div class="b-img">
                     <h1>도서관 이용</h1>
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
                 <a href="#p2"><li>이용시간/휴관일</li></a>
                 <a href="#p3"><li>도서관회원가입안내</li></a>
                 <a href="#p4"><li>도서대출/반납/예약</li></a>
                 <a href="#p5"><li>도서관이용안내</li></a>
                 <a href="#p6"><li>영상정보처리방침</li></a>

             </ul>
         </div>
     </div>
             
             
             <div class="p2" id="p2">
               <div class="wrap">
                <h2>이용시간/휴관일</h2>
                 <h3><i class="fas fa-feather-alt"></i> 도서관 실별 이용시간</h3>
                 <table>
                     <tr>
                        <td>실별</td>
                         <td>평일</td>
                         <td>토,일요일</td>
                     </tr>
                     <tr>
                       <td>어린이자료실</td>
                         <td colspan="2" rowspan="4">09:00~18:00</td>
                     </tr>
                     <tr>
                     <td>디지털실</td>
                     
                     </tr>
                     <tr> <td>제1,2자료실</td></tr>
                     <tr><td>연속간행물실</td></tr>
                     <tr><td>자율학습실</td>
                     <td colspan="2">09:00~18:00</td></tr>
                 </table>
                 <h3><i class="fas fa-feather-alt"></i> 휴관일</h3>
                     <table class="tb2">
                         <tr>
                             <td>정기 휴관일(자료실)</td>
                             <td>법정 공휴일(도서관 전체)</td>
                             <td>임시 휴관일</td>
                         </tr>
                         <tr>
                             <td><span class="red">매주 월요일</span><br>법정공휴일</td>
                             <td>신정,설,추석 연휴</td>
                             <td>도서관 사정에 의해<br>관장이 지정한 날</td>
                         </tr>
                     </table>
                 </div>
             </div>
             
             <div class="p3" id="p3">
                 <div class="wrap">
                     <h2>도서관 회원가입 안내</h2>
                     <h3><i class="fas fa-feather-alt"></i> 도서관 회원가입</h3>
                     <h4><i class="far fa-check-circle"></i> 가입대상</h4>
                     <p>누구나</p>
                     <h4><i class="far fa-check-circle"></i> 구비서류(재발급 포함)</h4>
                     <table>
                         <tr>
                             <td>대상</td>
                             <td colspan="2">구비서류</td>
                             <td>발급처</td>
                         </tr>
                         <tr>
                             <td>만 14세 미만자</td>
                             <td>법정대리인 날인,<br>주민등록등본</td>
                             <td rowspan="3">도서관 회원가입<br>신청서<br>(도서관 소정양식)</td>
                             <td>어린이자료실(1층)</td>
                         </tr>
                         <tr>
                             <td>중,고등학생</td>
                             <td>학생증</td>
                             <td rowspan="2">제1자료실(2층)</td>
                         </tr>
                         <tr>
                             <td>일반인</td>
                             <td>신분증(주민등록번호,<br>사진 포함)</td>
                         </tr>
                     </table>
                     <h4><i class="far fa-check-circle"></i> 도서관 회원증 재발급</h4>
                     <ul>
                         <li>필요서류 구비하여 신청 즉시 발급</li>
                         <li>4회(최초 1회 포함) 발급 시부터 1주일 후 회원증 발급 및 대출 가능</li>
                     </ul>
                     <h3><i class="fas fa-feather-alt"></i> 가족대출회원제도 운영</h3>
                     <h4><i class="far fa-check-circle"></i> 가입방법</h4>
                     <ul>
                         <li>대상 : 주소지가 동일한 직계존비속 중 2인 이상 개별 회원가입이 되어 있는 가족</li>
                         <li>구비서류 : 주민등록등본</li>
                         <li>장소 : 제1자료실, 어린이자료</li>
                     </ul>
                     <h4><i class="far fa-check-circle"></i> 대출 책수 및 기한</h4>
                     <p>회원가입된 가족수x5권, 14일간 대출</p>
                     
                     <h4><i class="far fa-check-circle"></i> 이용방법</h4>
                     <p>가족회원 중 누구나 본인의 도서관 회원증을 제시</p>
                 </div>
             </div>
             <div class="p4" id="p4">
                 <div class="wrap">
                     <h2>도서대출/반납예약</h2>
                     <h3><i class="fas fa-feather-alt"></i> 도서대출</h3>
                     <h4><i class="far fa-check-circle"></i> 대출방법</h4>
                     <ul>
                         <li>창원시 문화도서관사업소 도서관 회원 가입자</li>
                         <li>대출 권수 : 1인당 총10권(단,공공도서관마다 5권 제한 )<br>예) 의창도서관5권,성산도서관5권<br>
      의창도서관5권, 용지평생학습센터3권, 안민마을도서관2권</li>
                         <li>대출 기간 : 14일(2주)</li>
                     </ul>
                     <h3><i class="fas fa-feather-alt"></i> 도서반납</h3>
                     <ul>
                         <li>대출 업무시간 종료 후에는 1층 로비 도서 자동반납기에서 반납 가능</li>
                         <li>반납 예정일이 도서관 휴관일인 경우 다음날로 반납일 자동 변경</li>
                         <li>반납 예정일이 지나서 도서를 반납하였을 경우<br>
- 30일 미만 : 연체 일수만큼 대출 정지<br>
- 30일 이상 : 연체 일수의 2배 기간 대출 정지(최대 2년)<br>
※ 가족회원의 경우 한 명이 15일 이상 연체할 경우 가족 전체 대출정지 동일 적용<br></li>
                    <li>반납 연기는 1회에 한하여 가능, 반납 예정일로부터 7일 연장 가능<br>
- 반납 연기 방법<br>
<ol>
<li>
홈페이지 : 홈페이지 로그인 → 마이페이지(우측 상단) → 도서대출 내역조회 조회 → 반납연기도서 체크 → 반납연기 버튼 클릭</li>
<li>도서관 방문 : 도서관 회원증 제시</li>
 </ol>
- 반납 연기 불가 : 대출정지 회원, 연체 도서, 예약 도서<br>
- 가족회원 반납 연기 : 가족회원 중 한 명의 아이디로 로그인하면 가족 전체 일괄 반납 연기 가능<br></li>
                    <li>대출도서의 분실, 훼손 시 현물 변상(단, 도서가 절판일 경우 변상일 기준 정가 변상)</li>
                     </ul>
                     <h3><i class="fas fa-feather-alt"></i> 도서예약</h3>
                     <ul>
                     <li>예약 권수 : 1인 2권(1권당 2명까지 예약 가능)</li>
                     <li>예약 대상 : 대출 중인 도서</li>
                     <li>예약 방법 : 홈페이지 로그인 → 도서 검색 → 도서 선택 → 대출예약하기 버튼 클릭</li>
                     <li>예약도서 안내 : 예약도서 반납 시 예약자 휴대폰으로 대출 가능 안내 문자 발송</li>
                     <li>예약도서 대출 : 예약도서 반납일을 포함하여 3일 이내 대출 가능</li>
                </ul>
                 </div>
             </div>
             <div class="p5" id="p5">
                 <div class="wrap">
                     <h2>도서관 이용안내</h2>
                     <h3><i class="fas fa-feather-alt"></i> 도서관 이용방법과 예절</h3>
                     <ul>
                         <li>도서관 규칙이나 약속을 잘 지켜야 합니다. 대출 시 반납기한을 잘 지켜주세요. (대출기간 14일)</li>
                         <li>도서관에서는 공중도덕을 지켜야 합니다.</li>
                         <li>도서관 자료 및 시설을 소중히 다루어야 합니다. (책의 훼손 및 파손행위, 책을 훔치는 행위 금지)</li>
                         <li>자료실에서는 음식물을 섭취할 수 없으므로 휴게실을 이용하세요.</li>
                         <li>다른 사람들에게 방해가 되는 행위를 하지 않도록 합시다. (큰 소리로 떠드는 행위 등)</li>
                         <li>도서관 내에서는 휴대폰 사용을 자제해 주세요.</li>
                         <li>이용한 자료는 반납 책꽂이에 놓아주세요.</li>
                     </ul>
                 </div>
             </div>
             <div class="p6" id="p6">
                 <div class="wrap">
                     <h2>영상정보처리방침</h2>
                     <h3><i class="fas fa-feather-alt"></i> 영상정보처리기기의 설치 근거 및 설치 목적</h3>
                     <h4><i class="far fa-check-circle"></i> 본 도서관은 개인정보보호법 제25조 제1항에 따라 다음과 같은 목적으로 영상정보처리기기를 설치ㆍ운영 합니다.</h4>
                     <ul>
                         <li>시설물 안전관리 및 범죄(분실, 도난) 예방</li>
                         <li>주차 계도</li>
                     </ul>
                     <h3><i class="fas fa-feather-alt"></i> 설치대수, 설치 위치 및 촬영범위</h3>
                     <table>
                         <tr>
                             <td>설치 대수</td>
                             <td>설치 위치 및 촬영 범위</td>
                         </tr>
                         <tr>
                             <td>22대</td>
                             <td>건물내부,외부,주차장(고정식20m이내)</td>
                         </tr>
                     </table>
                     <h3><i class="fas fa-feather-alt"></i> 관리책임자 및 접근권한자</h3>
                     <h4><i class="far fa-check-circle"></i> 귀하의 영상정보를 보호하고 개인영상정보와 관련한 불만을 처리하기 위하여 아래와 같이 개인영상정보 보호책임자를 두고 있습니다.</h4>
                     <table class="tb2">
                         <tr>
                             <td></td>
                             <td>이름</td>
                             <td>직위</td>
                             <td>소속</td>
                             <td>연락처</td>
                         </tr>
                         <tr>
                             <td>관리책임자</td>
                             <td>회원도서관장</td>
                             <td>관장</td>
                             <td>회원도서관</td>
                             <td>055)225-7460</td>
                         </tr>
                         <tr>
                             <td>접근권한자</td>
                             <td>시설물 담당자<br>청원경찰</td>
                             <td>주무관<br>청원경찰</td>
                             <td>회원도서관</td>
                             <td>055)225-7460</td>
                         </tr>
                     </table>
                     <h3><i class="fas fa-feather-alt"></i> 영상정보의 촬영시간,보관기간,보관장소 및 처리방법</h3>
                     <table class="tb3">
                         <tr>
                             <td>촬영시간</td>
                             <td>보관기간</td>
                             <td>보관장소</td>
                         </tr>
                         <tr>
                             <td>24시간</td>
                             <td>촬영일로부터 5일</td>
                             <td>안내실</td>
                         </tr>
                     </table>
                     <ul>
                         <li>처리방법 : 개인영상정보의 목적외 이용, 제3자 제공, 파기, 열람 등 요구에 관한 사항을 기록ㆍ관리하고, 보관기간 만료시 복원이 불가능한 방법으로 영구 삭제(출력물의 경우 파쇄 또는 소각)합니다.</li>
                     </ul>
                     <h3><i class="fas fa-feather-alt"></i> 개인영상정보의 확인 방법 및 장소에 관한 사항</h3>
                     <ul>
                         <li>확인 방법 : 영상정보 관리책임자에게 미리 연락하고 본 도서관을 방문하시면 확인 가능합니다.</li>
                         <li>확인 장소 : 의창도서관 관리담당 055)225-7331</li>
                     </ul>
                     <h3><i class="fas fa-feather-alt"></i> 정보주체의 영상정보 열람 등 요구에 대한 조치</h3>
                     <h4><i class="far fa-check-circle"></i> 귀하는 개인영상정보에 관하여 열람 또는 존재확인ㆍ삭제를 원하는 경우 언제든지 영상정보처리기기 운영자에게 요구하실 수 있습니다. 단, 귀하가 촬영된 개인영상정보 및 명백히 정보주체의 급박한 생명, 신체, 재산의 이익을 위하여 필요한 개인영상정보에 한정됩니다. 본 도서관은 개인영상정보에 관하여 열람 또는 존재확인ㆍ삭제를 요구한 경우 지체없이 필요한 조치를 하겠습니다.<br>
※처리절차 : 개인정보(열람, 정정 · 삭제, 처리 정지)요수 → 관리책임자 검토 → 개인정보(열람, 일부열람, 열람 연기, 열람 거절)통지</h4>
                     <h3><i class="fas fa-feather-alt"></i> 영상정보의 안전성 확보조치</h3>
                     <h4><i class="far fa-check-circle"></i> 본 도서관에서 처리하는 영상정보는 관계자 외 출입제한 등을 통하여 안전하게 관리되고 있습니다. 또한 본 도서관은 개인영상정보보호를 위한 관리적 대책으로서 개인정보에 대한 접근 권한을 차등부여하고 있고, 개인영상정보의 위ㆍ변조 방지를 위하여 개인영상정보의 생성 일시, 열람시 열람 목적ㆍ열람자ㆍ열람 일시 등을 기록하여 관리하고 있습니다. 이 외에도 개인영상정보의 안전한 물리적 보관을 위하여 잠금장치를 설치하고 있습니다.</h4>
                     <h3><i class="fas fa-feather-alt"></i> 개인정보 처리방침 변경에 관한 사항</h3>
                     <h4><i class="far fa-check-circle"></i> 법령 · 정책 또는 보안기술의 변경에 따라 내용의 추가 · 삭제 및 수정이 있을 시에는 시행하기 최소 7일전에 본 도서관 홈페이지를 통해 변경사유 및 내용 등을 공지하도록 하겠습니다.
                     <ul>
                         <li>공고일자 : 2012년 5월 29일 / 시행일자 : 2014년 9월 5일</li>
                     </ul>
                     </h4>
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
                 console.log($("html, body").scrollTop());
                if($(window).width()>800){
                    if($("html, body").scrollTop()>=200){
                        $(".page-index").css("position","fixed");
                        $(".page-index").css("top","80px");
                    }else{
                         $(".page-index").css("position","inherit");
                        $(".page-index").css("top","0px");
                    }
                      $(".page-index").css("height","80px");
                    $(".page-index li").css({
                                            "font-size":"15px",
                                            "height":"30px",
                                            "padding-top":"10px"
                                            })
                }else{
                   
                      $(".page-index").css("position","fixed");
                    $(".page-index").css("top","80px");
                    $(".page-index").css("height","60px");
                    $(".page-index li").css({
                                            "font-size":"13px",
                                            "height":"25px",
                                            "padding-top":"10px"
                                            })
                }
                     $(".page-index li").css("font-weight","normal");
                if($("html, body").scrollTop()>=$(".p6").offset().top-20){
                    $(".page-index li").eq(4).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p5").offset().top-20){
                    $(".page-index li").eq(3).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p4").offset().top-20){
                    $(".page-index li").eq(2).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p3").offset().top-20){
                    $(".page-index li").eq(1).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p2").offset().top-20){
                    $(".page-index li").eq(0).css("font-weight","bold");
                }
            });
           if($(window).width()>800){
                     if($("html, body").scrollTop()>=200){
                        $(".page-index").css("position","fixed");
                        $(".page-index").css("top","80px");
                         
                    }else{
                         $(".page-index").css("position","inherit");
                        $(".page-index").css("top","0px");
                    }
                     $(".page-index").css("height","80px");
                    $(".page-index li").css({
                                            "font-size":"15px",
                                            "height":"30px",
                                            "padding-top":"10px"    
                                            })
                }else{
                      $(".page-index").css("position","fixed");
                    $(".page-index").css("top","80px");
                    $(".page-index").css("height","60px");
                    $(".page-index li").css({
                                            "font-size":"13px",
                                            "height":"25px",
                                            "padding-top":"10px"
                                            })
            }
//             var pageidx = -1;
            $(".page-index a").click(function(e){
                e.preventDefault();
//                $(".page-index li").css("font-weight","normal");
//                $(this).children("li").css("font-weight","bold");
                var idx = $(this).index();
                idx = ".p"+(idx+2);
                $("html, body").stop().animate({
                    scrollTop :$(idx).offset().top-20
                },500);
//                pageidx = $(this).index();
            });
//           
            $(".page-index a").hover(function(){
                $(this).children("li").css("font-weight","bold");
            },function(){
                    $(".page-index li").css("font-weight","normal");
                if($("html, body").scrollTop()>=$(".p6").offset().top-20){
                    $(".page-index li").eq(4).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p5").offset().top-20){
                    $(".page-index li").eq(3).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p4").offset().top-20){
                    $(".page-index li").eq(2).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p3").offset().top-20){
                    $(".page-index li").eq(1).css("font-weight","bold");
                }else  if($("html, body").scrollTop()>=$(".p2").offset().top-20){
                    $(".page-index li").eq(0).css("font-weight","bold");
                }
            });
            
            
            
        });
    </script>
</body>
</html>