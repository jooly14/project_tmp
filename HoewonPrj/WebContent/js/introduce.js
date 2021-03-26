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
        if ($(window).width() > 1200) {
            if ($("html, body").scrollTop() >= 200) {
                $(".page-index").css("position", "fixed");
                $(".page-index").css("top", "80px");
            } else {
                $(".page-index").css("position", "inherit");
                $(".page-index").css("top", "0px");
            }
            $(".page-index").css("height", "70px");
            $(".page-index li").css({
                "font-size": "15px",
                "height": "30px",
                "padding-top": "10px"
            })
            $(".p1-2 .imgbox span").css("transform", "translate(" + ($(window).innerWidth() * 0.344 - $(".p1-2 .imgbox span").innerWidth() / 2) + "px,-" + ($(window).innerHeight() * 0.36 - $(".p1-2 .imgbox span").innerHeight() / 2) + "px)");
        } else if ($(window).width() > 783) {
            if ($("html, body").scrollTop() >= 200) {
                $(".page-index").css("position", "fixed");
                $(".page-index").css("top", "80px");
            } else {
                $(".page-index").css("position", "inherit");
                $(".page-index").css("top", "0px");
            }
            $(".page-index").css("height", "100px");
            $(".page-index li").css({
                "font-size": "15px",
                "height": "30px",
                "padding-top": "10px"
            })
            $(".p1-2 .imgbox span").css("transform", "translate(" + ($(window).innerWidth() * 0.338 - $(".p1-2 .imgbox span").innerWidth() / 2) + "px,-" + ($(window).innerHeight() * 0.36 - $(".p1-2 .imgbox span").innerHeight() / 2) + "px)");
        } else {

            $(".page-index").css("position", "fixed");
            $(".page-index").css("top", "80px");
            $(".page-index").css("height", "80px");
            $(".page-index li").css({
                "font-size": "13px",
                "height": "25px",
                "padding-top": "5px"
            })
            $(".p1-2 .imgbox span").css("transform", "none");
        }
        $(".page-index li").css("font-weight", "normal");
        if ($("html, body").scrollTop() >= $(".p8").offset().top - 50) {
            $(".page-index li").eq(7).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p7").offset().top - 50) {
            $(".page-index li").eq(6).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p6").offset().top - 50) {
            $(".page-index li").eq(5).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p5").offset().top - 50) {
            $(".page-index li").eq(4).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p4").offset().top - 50) {
            $(".page-index li").eq(3).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p3").offset().top - 50) {
            $(".page-index li").eq(2).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p2").offset().top - 50) {
            $(".page-index li").eq(1).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p1-2").offset().top - 50) {
            $(".page-index li").eq(0).css("font-weight", "bold");
        }

    });
    
    //화면 새로고침 시에 적용되도록
    if ($(window).width() > 1200) {
        if ($("html, body").scrollTop() >= 200) {
            $(".page-index").css("position", "fixed");
            $(".page-index").css("top", "80px");

        } else {
            $(".page-index").css("position", "inherit");
            $(".page-index").css("top", "0px");
        }
        $(".page-index").css("height", "70px");
        $(".page-index li").css({
            "font-size": "15px",
            "height": "30px",
            "padding-top": "10px"
        });
    } else if ($(window).width() > 783) {

        if ($("html, body").scrollTop() >= 200) {
            $(".page-index").css("position", "fixed");
            $(".page-index").css("top", "80px");
        } else {
            $(".page-index").css("position", "inherit");
            $(".page-index").css("top", "0px");
        }
        $(".page-index").css("height", "100px");
        $(".page-index li").css({
            "font-size": "15px",
            "height": "30px",
            "padding-top": "10px"
        });
    } else {
        $(".page-index").css("position", "fixed");
        $(".page-index").css("top", "80px");
        $(".page-index").css("height", "80px");
        $(".page-index li").css({
            "font-size": "13px",
            "height": "25px",
            "padding-top": "5px"
        });
    }
    
    // 네비게이션 링크 클릭시 스크롤 이벤트
    $(".page-index a").click(function (e) {
        e.preventDefault();
        //                $(".page-index li").css("font-weight","normal");
        //                $(this).children("li").css("font-weight","bold");
        var idx = $(this).index();
        if (idx == 0) {
            idx = ".p1-2";
        } else {
            idx = ".p" + (idx + 1);
        }
        $("html, body").stop().animate({
            scrollTop: $(idx).offset().top
        }, 500);
    });
    // 네비게이션 링크 호버 이벤트           
    $(".page-index a").hover(function () {
        $(this).children("li").css("font-weight", "bold");
    }, function () {
        $(".page-index li").css("font-weight", "normal");
        if ($("html, body").scrollTop() >= $(".p8").offset().top - 50) {
            $(".page-index li").eq(7).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p7").offset().top - 50) {
            $(".page-index li").eq(6).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p6").offset().top - 50) {
            $(".page-index li").eq(5).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p5").offset().top - 50) {
            $(".page-index li").eq(4).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p4").offset().top - 50) {
            $(".page-index li").eq(3).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p3").offset().top - 50) {
            $(".page-index li").eq(2).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p2").offset().top - 50) {
            $(".page-index li").eq(1).css("font-weight", "bold");
        } else if ($("html, body").scrollTop() >= $(".p1-2").offset().top - 50) {
            $(".page-index li").eq(0).css("font-weight", "bold");
        }
    });

    /////////////////층별 안내도 클릭시 보여주는 화면////////////////
    var imgboxidx;
    $(".p1-2 .b2 p").not($(".p1-2 .b2 p:nth-child(7)")).click(function () {

        imgboxidx = $(this).index();
        $(".imgbox").css("display", "flex");
        $(".imgbox").animate({
            height: "100vh",
        }, 500, function () {
            $(".imgbox img").eq(imgboxidx - 2).css("display", "block");
            $(".imgbox img").eq(imgboxidx - 2).animate({
                opacity: "1"
            }, 500);
        });
    });
    
    // 층별 안내도 팝업창에서 닫기 클릭시 이벤트
    $(".imgbox span").click(function () {
        $(".imgbox img").eq(imgboxidx - 2).animate({
            opacity: "0"
        }, 500, function () {
            $(".imgbox img").eq(imgboxidx - 2).css("display", "none");
            $(".imgbox").animate({
                height: "0"
            }, 500, function () {
                $(".imgbox").css("display", "none");
            });
        });
    });

    $(".imgbox span").css("cursor", "pointer");
    $(".p1-2 .imgbox span").css("z-index", "30");


    // 모바일 네비게이션 버튼 클릭시 화면이 닫히지 않는 현상 보정 
    // 현재 선택된 페이지 카테고리가 먼저 보이게
    $("#navg-box").change(function () {
        if ($(this).is(":checked")) {
            $("#nav4-r2").prop("checked", true);
        }
    });
    $(".div2 a").click(function () {
        if ($(this).attr("href").indexOf("introduce") != -1) {
            $("#navg-box").prop("checked", false);
        }
    });
});
