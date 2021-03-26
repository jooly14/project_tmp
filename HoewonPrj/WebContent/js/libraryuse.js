$(function () {
    // 스크롤 천천히 올라가는 애니메이션 
    $(".link-top a").attr("href", "#");
    $(".link-top a").click(function (e) {
        e.preventDefault();
        $("html, body").stop().animate({
            scrollTop: "0"
        }, 500);
    });
    
    // 스크롤이 특정 위치로 가면 네비게이션 바가 상단에 fixed됨
    // 도서관소개에 팝업창 버튼 위치 변경
    // 스크롤이 특정 위치에 있으면 해당 네비게이션 링크가 bold하게 표시됨
    $(window).on("scroll resize", function () {
        console.log($("html, body").scrollTop());
        if ($(window).width() > 783) {
            if ($("html, body").scrollTop() >= 200) {
                $(".page-index").css("position", "fixed");
                $(".page-index").css("top", "80px");
            } else {
                $(".page-index").css("position", "inherit");
                $(".page-index").css("top", "0px");
            }
            $(".page-index").css("height", "80px");
            $(".page-index li").css({
                "font-size": "15px",
                "height": "30px",
                "padding-top": "10px"
            })
        } else {

            $(".page-index").css("position", "fixed");
            $(".page-index").css("top", "80px");
            $(".page-index").css("height", "60px");
            $(".page-index li").css({
                "font-size": "13px",
                "height": "25px",
                "padding-top": "10px"
            })
        }
        $(".page-index li").css("font-weight", "normal");
        if ($("html, body").scrollTop() >= $(".p6").offset().top - 20) {
            $(".page-index li").eq(4).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p5").offset().top - 20) {
            $(".page-index li").eq(3).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p4").offset().top - 20) {
            $(".page-index li").eq(2).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p3").offset().top - 20) {
            $(".page-index li").eq(1).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p2").offset().top - 20) {
            $(".page-index li").eq(0).css("font-weight", "bold");
        }
    });
    
    //화면 새로고침 시에 적용되도록
    if ($(window).width() > 783) {
        if ($("html, body").scrollTop() >= 200) {
            $(".page-index").css("position", "fixed");
            $(".page-index").css("top", "80px");

        } else {
            $(".page-index").css("position", "inherit");
            $(".page-index").css("top", "0px");
        }
        $(".page-index").css("height", "80px");
        $(".page-index li").css({
            "font-size": "15px",
            "height": "30px",
            "padding-top": "10px"
        })
    } else {
        $(".page-index").css("position", "fixed");
        $(".page-index").css("top", "80px");
        $(".page-index").css("height", "60px");
        $(".page-index li").css({
            "font-size": "13px",
            "height": "25px",
            "padding-top": "10px"
        })
    }
    //             var pageidx = -1;
    // 네비게이션 링크 클릭시 스크롤 이벤트
    $(".page-index a").click(function (e) {
        e.preventDefault();
        //                $(".page-index li").css("font-weight","normal");
        //                $(this).children("li").css("font-weight","bold");
        var idx = $(this).index();
        idx = ".p" + (idx + 2);
        $("html, body").stop().animate({
            scrollTop: $(idx).offset().top
        }, 500);
        //                pageidx = $(this).index();
    });
    
    // 네비게이션 링크 호버 이벤트       
    $(".page-index a").hover(function () {
        $(this).children("li").css("font-weight", "bold");
    }, function () {
        $(".page-index li").css("font-weight", "normal");
        if ($("html, body").scrollTop() >= $(".p6").offset().top - 20) {
            $(".page-index li").eq(4).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p5").offset().top - 20) {
            $(".page-index li").eq(3).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p4").offset().top - 20) {
            $(".page-index li").eq(2).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p3").offset().top - 20) {
            $(".page-index li").eq(1).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p2").offset().top - 20) {
            $(".page-index li").eq(0).css("font-weight", "bold");
        }
    });

    //모바일 네비게이션에서 같은 페이지 내에 있는 링크 클릭시 네비게이션 화면이 안 닫히는 문제 해결
    $(".div2 a").click(function () {
        if ($(this).attr("href").indexOf("libraryuse") != -1) {
            $("#navg-box").prop("checked", false);
        }
    });


});
