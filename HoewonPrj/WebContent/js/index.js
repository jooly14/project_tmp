$(function () {
    ////////////////////////////////////////// 마우스 올렸을때 및 클릭했을때 이벤트
    $(".p2 td").hover(function () {
        $(this).css("font-weight", "bold");
        $(this).css("cursor", "pointer");
        if ($(this).index() == 0) {
            $(this).next().css("font-weight", "bold");
        } else if ($(this).index() == 1) {
            $(this).prev().css("font-weight", "bold");
        }
    }, function () {
        $(this).css("font-weight", "normal");
        $(this).css("cursor", "normal");
        if ($(this).index() == 0) {
            $(this).next().css("font-weight", "normal");
        } else if ($(this).index() == 1) {
            $(this).prev().css("font-weight", "normal");
        }
    });
    
    $(".p-quickmenu img").hover(function () {
        $(".menu-wrapin2 .qm1").eq($(this).index()).css("color", "#295E52");
        $(".menu-wrapin2 .qm1").eq($(this).index()).css("text-decoration", "underline");
        $(this).css("cursor", "pointer");
    }, function () {
        $(".menu-wrapin2 .qm1").eq($(this).index()).css("color", "black");
        $(".menu-wrapin2 .qm1").eq($(this).index()).css("text-decoration", "none");
    });
    
    $(".p-quickmenu .qm1").hover(function () {
        $(this).css("color", "#295E52");
        $(this).css("text-decoration", "underline");
        $(this).css("cursor", "pointer");
    }, function () {
        $(this).css("color", "black");
        $(this).css("text-decoration", "none");
    });
    
    $(".p5 .category ul li label").click(function () {
        var idx = $(this).text();
        $(".p5 .category ul li label").css("color", "black");
        $(this).css("color", "#295E52");
        $(".new").css("display", "none");
        $(".best").css("display", "none");
        $(".recommend").css("display", "none");
        $(".new").css("opacity", "0");
        $(".best").css("opacity", "0");
        $(".recommend").css("opacity", "0");
        if (idx == "신착도서") {
            $(".new").css("display", "flex");
            $(".new").animate({
                opacity: "1",
            }, 500);
        } else if (idx == "인기도서") {
            $(".best").css("display", "flex");
            $(".best").animate({
                opacity: "1",
            }, 500);
        } else if (idx == "권장도서") {
            $(".recommend").css("display", "flex");
            $(".recommend").animate({
                opacity: "1",
            }, 500);
        }
    });
    
    $(".p5 .category ul li label").hover(function () {
        $(this).css("color", "#295E52");
    }, function () {
        var arr = [".new", ".best", ".recommend"];
        for (var i = 0; i < 3; i++) {
            if ($(arr[i]).css("display") == "flex") {
                arr = i;
                break;
            }
        }
        if ($(".p5 li label").eq(i).text() != $(this).text()) {
            $(this).css("color", "black");
        }
    });
    $(".p5 .category ul li label").eq(0).css("color", "#295E52");
    /////////////////////////////////////////////////////////////////////////////
    
    
    //천천히 스크롤이 올라가도록 애니메이션
    $(".link-top a").attr("href", "#");
    $(".link-top a").click(function (e) {
        e.preventDefault();
        $("html, body").animate({
            scrollTop: "0"
        }, 500);
    });


    ////////////////////////////////달력////////////////////////////////////
    var today = new Date();
    var month = today.getMonth() + 1;
    curday = new Date(today.getFullYear(), month - 1, 1);
    var endday;
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) { //31일까지
        var d = 1;
        endday = 31;

    } else if (month == 2) { //28일까지
        var d = 1;
        endday = 28;
        if (today.getFullYear() % 400 == 0 || (today.getFullYear() % 100 != 0 && today.getFullYear() % 4 == 0)) {
            endday++;
        }
    } else {
        var d = 1;
        endday = 30;
    }
    for (; d < endday + 1;) {
        var tr1 = $("<tr></tr>");
        if (d == 1) {
            for (var i = 0; i < 7; i++) {
                var elm = $("<td></td>");
                if (i >= curday.getDay()) {
                    if (i == 1) {
                        elm.addClass("holiday");
                        elm.css("position", "relative");
                    }
                    if (d == today.getDate()) {
                        elm.addClass("today");
                    }
                    elm.text(d++);
                }
                tr1.append(elm);
            }
        } else {
            for (var i = 0; i < 7; i++) {
                var elm = $("<td></td>");
                if (d <= endday) {
                    if (i == 1) {
                        elm.addClass("holiday");
                        elm.css("position", "relative");
                    }
                    if (d == today.getDate()) {
                        elm.addClass("today");
                    }
                    elm.text(d++);
                }
                tr1.append(elm);
            }
        }
        $(".sec1 table").append(tr1);
    }
    $(".sec1 table tr td").mouseenter(function () {
        $(this).css("cursor", "pointer");
    });

    //////// 첫페이지 개관 여부 알림///////////////////
    if (today.getDay() == 1) {
        $(".p1 h1").html("<i class='fas fa-door-closed'></i> 오늘은 도서관 정기 휴관일입니다");
    } else {
        $(".p1 h1").html("<i class='fas fa-door-open'></i> 오늘은 도서관 개관일입니다");
    }
    //////////////////////////////////배너/////////////////////////////////
    var myInterval1;

    function startInterval() {
        myInterval1 = setInterval(function () {
            $(".movebox").stop().animate({
                left: "-300"
            }, 500, function () {
                var elm = $(this).children().eq(0).detach();
                $(".movebox").append(elm);
                $(".movebox").css("left", "0");
            });
        }, 2000);

    }
    startInterval();
    var elm = $("<div></div");
    elm.text("STOP");
    elm.addClass("layer1");
    elm.css("background-color", "rgba(2,59,72,.8)");
    elm.css("font-size", "50px");
    elm.css("font-weight", "bold");
    elm.css("color", "white");
    elm.css("z-index", "500");
    elm.css("width", "300px");
    elm.css("height", "180px");
    elm.css("position", "absolute");
    elm.css("top", "0");
    elm.css("left", "0");
    elm.css("opacity", "0");
    elm.css("display", "none");
    elm.css("justify-content", "center");
    elm.css("align-items", "center");
    elm.css("cursor", "pointer");
    $(".sec2 .banner").append(elm);
    $(".sec2").hover(function () {
        $(".layer1").css("display", "flex");
        $(".layer1").stop().animate({
            opacity: "1"
        }, 500);
    }, function () {
        $(".layer1").stop().animate({
            opacity: "0"
        }, 500, function () {
            $(".layer1").css("display", "none");
        });
    });
    $(".layer1").click(function () {
        if ($(this).text() == "STOP") {
            clearInterval(myInterval1);
            $(this).text("START");
        } else {
            startInterval();
            $(this).text("STOP");
        }
    });
    // 모달창 - 쿠키를 사용
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
