function iconEffect(i) {

    if (i == 1) {
        document.getElementById("footerItem1").style.fontSize = '21px'
        document.getElementById("footerItem1").style.marginRight = '11px'
        document.getElementById("footerItem1").style.marginLeft = '22px'
        setTimeout(iconReset, 150, 1);
        location.href='/main/main'
    } else if (i == 2) {
        document.getElementById("footerItem2").style.marginLeft = '22px'
        document.getElementById("footerItem2").style.fontSize = '21px'
        document.getElementById("footerItem2").style.marginRight = '11px'
        setTimeout(iconReset, 150, 2);
    } else if (i == 3) {
        document.getElementById("footerItem3").style.fontSize = '21px'
        document.getElementById("footerItem3").style.marginLeft = '22px'
        document.getElementById("footerItem3").style.marginRight = '11px'
        setTimeout(iconReset, 150, 3);
    } else if (i == 4) {
        document.getElementById("footerItem4").className = "fi fa-solid fa-door-open"
        setTimeout(iconReset, 150, 4);
    } else if (i == 5) {
        document.getElementById("headerItem1").className = "fi fa-solid fa-square-plus"
        setTimeout(iconReset, 150, 5);
    } else if (i == 6) {

        document.getElementById("headerItem2").className = "fi fa-solid fa-heart"
        setTimeout(iconReset, 150, 6);
    } else if (i == 7) {

        document.getElementById("headerItem3").className = "fi fa-solid fa-paper-plane"
        setTimeout(iconReset, 150, 7);
    }


    function iconReset(i) {
        if (i == 1) {
            document.getElementById("footerItem1").style.marginLeft = '20px'
            document.getElementById("footerItem1").style.fontSize = "25px"
            document.getElementById("footerItem1").style.marginRight = '9px'
        } else if (i == 2) {
            document.getElementById("footerItem2").style.marginLeft = '20px'
            document.getElementById("footerItem2").style.fontSize = "25px"
            document.getElementById("footerItem2").style.marginRight = '9px'
        } else if (i == 3) {
            document.getElementById("footerItem3").style.marginLeft = '20px'
            document.getElementById("footerItem3").style.fontSize = "25px"
            document.getElementById("footerItem3").style.marginRight = '9px'
        } else if (i == 4) {

            document.getElementById("footerItem4").className = "fi fa-solid fa-door-closed"
        } else if (i == 5) {
            document.getElementById("headerItem1").className = "fi fa-regular fa-square-plus"
        } else if (i == 6) {
            document.getElementById("headerItem2").className = "fi fa-regular fa-heart"
        } else if (i == 7) {
            document.getElementById("headerItem3").className = "fi fa-regular fa-paper-plane"
        }
    }
}

function logoutChk() {
    let header = $("meta[name='_csrf_header']").attr('content');
    let token = $("meta[name='_csrf']").attr('content');
    alert("logout chk 호출")
    $.ajax({
        url: "/member/logoutCheck",
        type: "get",
        beforeSend:function (result){
            result.setRequestHeader(header,token);
        },
        success: function (e) {
            console.log("logout chk suc")
            location.href = "/member/logout"
        },
        error: function (e) {
            console.log("logout chk err")

        }

    })
}

function loginChk() {
    let header = $("meta[name='_csrf_header']").attr('content');
    let token = $("meta[name='_csrf']").attr('content');
    console.log("login chk 호출")
    $.ajax({
        url: "/member/loginCheck",
        beforeSend:function(result){
            result.setRequestHeader(header,token);
        },
        success: function (e) {
            console.log("login chk suc")
        },
        error: function (e) {
            console.log("login chk err")
        }

    })
}

function followBTN() {
    let header = $("meta[name='_csrf_header']").attr('content');
    let token = $("meta[name='_csrf']").attr('content');
    const memberName = document.getElementById("memberName").value;
    const yourId = "memberName";
    const yourName = "memberName";
    const yourProfileName = "memberProfileName2";
    const loginStatus = 0;

    $.ajax({
        url: "/follow/request",
        type: "post",
        dataType: "json",
        data: {
            "myId": memberName,
            "yourId": yourId,
            "yourName": yourName,
            "yourProfileName": yourProfileName,
            "loginStatus": loginStatus
        },
        beforeSend:function(result){
            result.setRequestHeader(header,token);
        },
        success: function (result) {
            console.log("suc");
        },
        error: function (result) {
            console.log("err");
        }
    })
};

function followList() {
    let header = $("meta[name='_csrf_header']").attr('content');
    let token = $("meta[name='_csrf']").attr('content');
    const myId = "yourId";
    $.ajax({
        url: "/follow/myList",
        type: "get",
        data: {
            "myId": myId
        },
        beforeSend:function (result){
            result.setRequestHeader(header,token);
        },
        success: function (result) {

        }
    })
};

function dropdown(){
    $("#dropBtn").trigger("click")
}
let rollInt=0;
let timer;
function roll(e){
    if(rollInt==0){
        document.getElementById("searchX").className="spinner-border spinner-border-sm"
        document.getElementById("spinZone").removeAttribute("hidden");
        document.getElementById("spinDisplay").style.display="none";
        rollInt=1
        clearTimeout(timer)
    }else if(rollInt==1){
        clearTimeout(timer)
    }
    if(e==2){
        function rollBack(){
            document.getElementById("spinZone").setAttribute("hidden","hidden");
            document.getElementById("spinDisplay").style.display="block"

            document.getElementById("searchX").className="fa-regular fa-circle-xmark"
            rollInt=0;
        }
        timer= setTimeout(rollBack,500)
    }
}

let popInt=0
function popoverCss(e){
    if(e=="a"){
        if(popInt==0){
            popInt=1
            console.log("클릭이벤트 실행1")

            document.getElementById("searchDrop").style.position="relative";
            document.getElementById("searchDrop").style.inset="0px auto auto 0px";
            document.getElementById("searchDrop").style.transform="translate(0px, 2px)";
            document.getElementById("searchDrop").style.top="10px";
            document.getElementById("searchDrop").style.left="315px";
            document.getElementById("searchDrop").style.zIndex="-1";
            document.getElementById("dropUl1").style.boxShadow="4px 5px 12px -5px grey";
            $("#searchDrop").trigger("click");


        }}else{
        popInt=0;
        console.log("클릭이벤트 실행2")
        $("#searchDrop").trigger("click")
    }

}

// popover 메뉴가 보이기 직전에 호출되는 이벤트
$('.searchDrop').on('show.bs.dropdown', function () {
    console.log("메뉴가 열리기 전 이벤트!2");

    $("#searchInput").removeAttr('readonly');
    $("#searchZone").show();
    $("#searchIcon").hide();
    $("#searchZone").removeAttr("hidden");
    $(".searchGroup").removeClass("input-group")
    document.getElementById("searchInput").style.border='1px solid #ced4da';
    document.getElementById("searchInput").style.padding='0.375rem 0.75rem';
    document.getElementById("searchInput").style.color='black';
});
let body;
// popover 메뉴가 보이기 직후에 호출되는 이벤트
$('.searchDrop').on('shown.bs.dropdown', function () {
    console.log("메뉴가 열린 후 이벤트!2");
    body=document.querySelectorAll('body')
    $(body).click(function(e){

        if(!$(e.target).hasClass('drop-item')){
            console.log('레이어팝업 외의 영역입니다')
            popoverCss('b');
        }

    });
});


// popover 메뉴가 사라지기 직전에 호출되는 이벤트
$('.searchDrop').on('hide.bs.dropdown', function () {
    console.log("메뉴가 닫히기 전 이벤트!2");

    $("#searchInput").attr('readonly');

    $("#searchIcon").show();
    $(".searchGroup").addClass("input-group")
    $("#searchZone").hide();
    document.getElementById("searchInput").style.borderLeft=0;
    document.getElementById("searchInput").style.paddingLeft=0;
    document.getElementById("searchInput").style.color='gray';
    popInt=0

});
// popover 메뉴가 사라진 직후에 호출되는 이벤트
$('.searchDrop').on('hidden.bs.dropdown', function () {
    console.log("메뉴가 닫힌 후 이벤트!2");
    $(body).unbind( "click" );
    $("#searchX").unbind( "click" );
});




function ulSet(){
    document.getElementById("dropUl").style.margin="11px -9px 0 0"
}
let circle=0
function circleReset(){
    console.log("circle : "+circle)
    if(circle==0){
        document.getElementById("headerFrofile").style.border="1px solid gray"
        circle=1
    }
    else if(circle==1){
        console.log("fhfkdk")
        document.getElementById("headerFrofile").style.border="1px solid transparent"
        circle=0
    }

}

// dropdown 메뉴가 보이기 직전에 호출되는 이벤트
$('.event-dropdown').on('show.bs.dropdown', function () {
    console.log("메뉴가 열리기 전 이벤트!");
    circleReset()
    document.getElementById("dropUl").style.boxShadow="4px 5px 12px -5px grey";
});

// dropdown 메뉴가 보이기 직후에 호출되는 이벤트
// $('.event-dropdown').on('shown.bs.dropdown', function () {
//     console.log("메뉴가 열린 후 이벤트!");  });
// dropdown 메뉴가 사라지기 직전에 호출되는 이벤트
$('.event-dropdown').on('hide.bs.dropdown', function () {
    console.log("메뉴가 닫히기 전 이벤트!");
    circleReset()
});
// dropdown 메뉴가 사라진 직후에 호출되는 이벤트
// $('.event-dropdown').on('hidden.bs.dropdown	', function () {
//         console.log("메뉴가 닫힌 후 이벤트!");  });



function detailGo(object) {
    console.log("detailgo 호출")
    let header=$("meta[name='_csrf_header']").attr('content');
    let token=$("meta[name='_csrf']").attr('content');
    let hssIndex=0;
    let result=0;
    $("#detailProfile").attr("src", /upload/ + object.memberProfileName);
    $("#deTailProfileId").html(object.boardWriter);
    $("#detailModalBtn").trigger("click");
    showBtn(0)
    function showBtn(board){
        result=board*1;
        function showRight(){
            $("#detailRightBtn").show()
        }
        function hideRight(){
            $("#detailRightBtn").hide()
        }
        function showLeft(){
            $("#detailLeftBtn").show()
        }
        function hideLeft(){
            $("#detailLeftBtn").hide()
        }
        if(board<=1){
            hideLeft(),hideRight()
        }

        else{
            if(hssIndex==0){
                hideLeft(),showRight()
            }else{
                showLeft(),showRight()
                if(hssIndex == (result-1)){
                    hideRight(),showLeft()
                }}}}
    $.ajax({
        type: "post",
        url: "/board/detail",
        data: {"boardId": object.id},
        dataType: "json",
        beforeSend:function (result){
            result.setRequestHeader(header,token);
        },
        success: function (data) {

            console.log("detailGo data: "+data.length)
            let detail = document.getElementById("contentsBody_detail");
            let m = "";
            showBtn(data.length)
            addImg()
            likeList(object.id)
            function addImg(){
                if (data[hssIndex].boardImgName != null) {
                    m = "<div><img src='" + '/upload/' + data[hssIndex].boardImgName + '' + "'" + " class='contentsItem_detail contentsItem_img detail' id='contentsItem_detail' alt=''></div>";
                } else if (data[hssIndex].boardVideoName != null) {
                    m = "<div><video src='" + '/upload/' + data[hssIndex].boardVideoName + '' + "'" + " class='contentsItem_detail detail contentsItem_video' id='contentsItem_detail' alt='' autoplay='autoplay' loop='loop'>" + "</video></div>";
                }
                detail.style.filter="";
                detail.innerHTML = m;
                detail.style.filter=data[hssIndex].boardFilter;
            }

            $('#detailRightBtn').click(function () {
                if(hssIndex<result && ((data.length)*1)-1 != hssIndex ){
                    hssIndex += 1;
                    addImg()
                }
                showBtn(data.length)

                console.log("right index: "+hssIndex)
                console.log("right length: "+data.length)

            });
            $('#detailLeftBtn').click(function () {
                if(hssIndex>0){
                    hssIndex += -1;
                    showBtn(data.length)
                    addImg()
                }

                console.log("left : "+hssIndex)

            });
            $('#detailClose').click(function () {

                hssIndex=0;
                $("#detailClose").unbind( "click" );
                $("#detailLeftBtn").unbind( "click" );
                $("#detailRightBtn").unbind( "click" );
            });
        } }); }

$("#feedCommentZone1").onclick=commentList();
function commentList(){
    let header=$("meta[name='_csrf_header']").attr('content');
    let token=$("meta[name='_csrf']").attr('content');
    $.ajax({
        type: "post",
        url: "/comment/list",
        data: {},
        dataType: "json",
        beforeSend:function (result){
            result.setRequestHeader(header,token);
        },
        success: function (data) {
            let commentIndex = [];
            const board = [[${boardList}]];
            let commentNum = board.length;
            let stop;
            for (let i = 0; i < board.length; i++) {
                commentIndex[i]=0;
                for (let j = 0; j < data.length; j++) {
                    commentIndex[i]++;
                }
            }
            for (let i = 0; i < board.length; i++) {
                const comment = document.getElementById("feedCommentZone"+commentNum);
                stop = 0;
                for (let j = 0; j < data.length; j++) {
                    if (data[j].boardId == board[i].id && stop <= 1){
                        comment.innerHTML += "<br><span>" + data[j].commentContents + "</span>";
                        stop++;
                    }
                }
                commentNum--;
            }
            // comment.innerHTML = data[0].commentContents;
        }
    });
}
$("#detailGoBtn2").click(function () {
    $("#detailGoBtn").trigger("click")
})

$("#like1").onclick=likeList(0);
function likeList (id) {
    let header = $("meta[name='_csrf_header']").attr('content');
    let token = $("meta[name='_csrf']").attr('content');
    $.ajax({
        type: "post",
        url: "/board/find",
        dataType: "json",
        beforeSend: function (result) {
            result.setRequestHeader(header, token);
        },
        success: function (data) {
            $.ajax({
                type: "post",
                url: "/board/count",
                dataType: "json",
                beforeSend: function (result) {
                    result.setRequestHeader(header, token);
                },
                success: function (like) {
                    let likeIndex = [];
                    for (let i = 0; i < data.length; i++) {
                        likeIndex[i] = 0;
                        for (let j = 0; j < like.length; j++) {
                            if (like[j].boardId == data[i].id) {
                                likeIndex[i]++;
                            }
                        }
                    }

                    let likeNum = 1;
                    for (let i = 0; i < data.length; i++) {
                        const likeMain = document.getElementById("like" + likeNum);
                        const likeDetail = document.getElementById("likeBtn_detail");
                        let detailIcon = "";
                        let icon = "";
                        if (data[i].likes == 1) {
                            icon += "<i class='fa-solid fa-heart feedBtn' style='color: red' id='i" + data[i].id + "' onclick='likes(" + data[i].id + ")'></i>"
                            icon += "<i class='fa-regular fa-comment feedBtn'></i>";
                            icon += "<span id='k" + likeNum + "'>" + "좋아요" + likeIndex[i] + "개" + "</span>";
                            likeMain.innerHTML = icon;
                            if (likeNum == id){
                                detailIcon += "<i class='fa-solid fa-heart commentListItem detail' style='color: red' id='like_detail' onclick='likes(" + data[i].id + ',' + 1 + ")'></i>"
                                detailIcon += "<span id='like_count'>" + "좋아요" + likeIndex[i] + "개" + "</span>";
                                likeDetail.innerHTML = detailIcon;
                            }
                        } else {
                            icon += "<i class='fa-regular fa-heart feedBtn' style='color: black' id='i" + data[i].id + "' onclick='likes(" + data[i].id + ")'></i>"
                            icon += "<i class='fa-regular fa-comment feedBtn'></i>";
                            icon += "<span id='k" + likeNum + "'>" + "좋아요" + likeIndex[i] + "개" + "</span>";
                            likeMain.innerHTML = icon;
                            if (likeNum == id) {
                                detailIcon += "<i class='fa-regular fa-heart commentListItem detail' style='color: black' id='like_detail' onclick='likes(" + data[i].id + ',' + 1 + ")'></i>"
                                detailIcon += "<span id='like_count'>" + "좋아요" + likeIndex[i] + "개" + "</span>";
                                likeDetail.innerHTML = detailIcon;
                            }
                        }
                        likeNum++;
                    }
                }
            });
        }
    });
}

const d = [[${boardFile}]];
const s = [[${boardList}]];
let sliderNum = [];
for (let i = 1; i <= s.length; i++) {
    sliderNum[i] = 0;
    // 한 게시물당 슬라이드 num 구하기
    document.getElementById("l"+i).style.visibility = "hidden";
}
for (let i = 1; i < s.length+1; i++){
    let c = 0;
    for (let j = 0; j < d.length; j++){
        if (d[j].boardId == s[s.length-i].id){
            c++
        }
    }
    if (c == 1) {
        document.getElementById("r"+i).style.visibility = "hidden";
    }
}
function feedRightSlider(a) {
    // a = id 값 벨류 받아오기
    document.getElementById("l"+a).style.visibility = "visible";
    let y=0;
    let zz = -498;
    for (let i = 0; i < d.length; i++){
        if (d[i].boardId == s[s.length-a].id){
            y++;
            // 한게시물당 슬라이드 length 구하기
        }
    }
    if (sliderNum[a]+1 < y){
        sliderNum[a]++;
    }
    if (sliderNum[a] == y-1) {
        document.getElementById("r"+a).style.visibility = "hidden";
    }
    for (let i =0; i <= y; i++) {
        if (sliderNum[a] == i) {
            document.getElementById(a).style.transform = "translate(" + (zz*i) + "px)";
        }
    }
}
function feedLeftSlider(a) {
    let zz = -498;
    if(sliderNum[a] > 0) {
        sliderNum[a]--;
    }
    if (sliderNum[a] == 0){
        document.getElementById("l"+a).style.visibility = "hidden";
    }
    document.getElementById("r"+a).style.visibility = "visible";
    for (let i =0; i <= 10; i++) {
        if (sliderNum[a] == i) {
            document.getElementById(a).style.transform = "translate(" + (zz*i) + "px)";
        }
    }
}
function likes (id,id2) {
    console.log(id2);
    let header=$("meta[name='_csrf_header']").attr('content');
    let token=$("meta[name='_csrf']").attr('content');
    console.log(id)
    const loginId = [[${memberDTO.memberName}]];
    $.ajax({
        type: "post",
        url: "/board/likes",
        data: {"memberName":loginId, "boardId": id},
        dataType: "text",
        beforeSend:function (result){
            result.setRequestHeader(header,token);
        },
        success: function (data) {
            $.ajax({
                type: "post",
                url: "/board/count",
                dataType: "json",
                beforeSend:function (result){
                    result.setRequestHeader(header,token);
                },
                success: function (likes) {
                    let like = [];
                    const board = [[${boardList}]];
                    for (let i = 0; i < board.length; i++) {
                        like[i]=0;
                        for (let j =0; j < likes.length; j++) {
                            if (likes[j].boardId == board[i].id){
                                like[i]++;
                                console.log(like[i])
                            }
                        }
                    }
                    const likeDetail = document.getElementById("like_detail");
                    const like_count = document.getElementById("like_count");
                    const likeTest2 = document.getElementById("i"+id);
                    const likeIndex = document.getElementById("k"+id);
                    if (data == "ok") {
                        likeTest2.className="fa-solid fa-heart feedBtn";
                        likeTest2.style.color ="red";
                        likeIndex.innerHTML = "좋아요"+ like[board.length-id] + "개";
                        if (id2 == 1){
                            likeDetail.className = 'fa-solid fa-heart commentListItem detail';
                            likeDetail.style.color = "red";
                            like_count.innerHTML = "좋아요"+ like[board.length-id] + "개";
                        }
                    }else {
                        likeTest2.className="fa-regular fa-heart feedBtn";
                        likeTest2.style.color ="black";
                        likeIndex.innerHTML = "좋아요"+ like[board.length-id] + "개";
                        if (id2 == 1){
                            likeDetail.className = 'fa-regular fa-heart commentListItem detail';
                            likeDetail.style.color = "black";
                            like_count.innerHTML = "좋아요"+ like[board.length-id] + "개";
                        }
                    }
                }
            });
        }
    });
}

function sizeTest(i) {

    let zone = document.getElementById("modal-content")
    if (i == 0) {
        console.log("test : 0")

        // document.getElementById("modalSize").className = "modal-dialog modal-lg"
        zone.style.width='800px'
    } else {
        console.log("test : 1")

        function sizeS() {
            // document.getElementById("modalSize").className = "modal-dialog"
            zone.style.width='500px'
            document.getElementById("nextBtn").setAttribute("data-bs-toggle", "collapse")
        }

        setTimeout(sizeS, 180)
        count = 0
    }
}

let classNum = 1;
let sliderNum2 = 0;
let test = 0;

function nextBtnEffect() {
    document.getElementById('nextBtn').style.color = 'gray'

    function reset() {
        document.getElementById('nextBtn').style.color = 'black'
    }

    setTimeout(reset, 150)

    nextPage()

}

function nextBtn2() {
    $('#nextBtn2').trigger("click")
}

function nextBtn() {
    $('#nextBtn').trigger("click")
}

let page = 0

function nextPage() {
    sizeTest(0)
    console.log("nextPage 호출 : " + page)
    if (page == 0) {
        document.getElementById("beforeBtn").removeAttribute("hidden")
        page = 1
    } else if (page == 1) {
        document.getElementById("beforeBtn").removeAttribute("hidden")
        document.getElementById("nextBtn").innerHTML = '<b>Save<b>'
        // document.getElementById("nextBtn").removeAttribute("onclick")
        document.getElementById("nextBtn").setAttribute("onclick", "submitBtn()")
        setTimeout(nextBtn2, 200)
        page = 2
        document.getElementById("nextBtn").removeAttribute("data-bs-toggle")
        // document.getElementById("nextBtn2").removeAttribute("data-bs-toggle")
    } else if (page == 2) {
        alert("save 만들기")
        // document.getElementById("nextBtn").setAttribute("data-bs-toggle", "collapse")
        // document.getElementById("nextBtn2").setAttribute("data-bs-toggle","collapse")
    }
}

function submitBtn() {
    // $('#submitBtn').trigger("click")

    document.getElementById("saveForm").submit();
    // location.href='/main/main'
}

let beforeCount = 0

function beforeBtnEffect() {
    if (page == 2) {
        nextBtn2();
        document.getElementById("nextBtn").innerHTML = '<b>Next<b>'
        document.getElementById("nextBtn").removeAttribute("onclick")
        document.getElementById("nextBtn").setAttribute("onclick", "nextBtnEffect()")


        page = 1
        // setTimeout(nextBtn,200)
    } else if (page == 1) {
        sizeTest(1)
        document.getElementById("beforeBtn").setAttribute("hidden", "hidden")

        page = 0
    } else if (page == 0) {

    }

    // document.getElementById("beforeBtn").setAttribute("hidden","hidden")
    // $('#beforeBtn').click()

}

function rightSlider() {
    const boardFile = document.getElementById("boardFile").files;
    if (document.getElementById("boardFile").value != "") {
        if (sliderNum2 + 1 < boardFile.length) {
            sliderNum2 ++;
        }
        if (classNum < boardFile.length) {
            classNum++;
        }

        let zz = -420;
        for (let i = 0; i <= boardFile.length; i++) {
            if (sliderNum2 == i) {
                document.querySelector('.contentsBody').style.transform = "translate(" + (zz * i) + "px)";
            }
        }
    }
}

function leftSlider() {
    if (document.getElementById("boardFile").value != "") {
        if (sliderNum2 > 0) {
            sliderNum2 --;
        }
        if (classNum > 1) {
            classNum--;
        }
        let zz = -420;
        for (let i = 0; i <= 10; i++) {
            if (sliderNum2 == i) {
                document.querySelector('.contentsBody').style.transform = "translate(" + (zz * i) + "px)";

            }
        }
    }
}

function filter(i) {
    const boardFile = document.getElementById("boardFile").files;
    console.log(boardFile);
    if (boardFile.length == 0) {
        alert("파일이 없습니다.");
    } else {
        let contentsItem = document.getElementById("contentsItem" + classNum);
        let boardFilter0 = document.getElementById("boardFilter0");
        let boardFilter = document.getElementById("boardFilter" + classNum);
        let boardFilter1 = document.getElementById("boardFilter1");
        let boardFilter2 = document.getElementById("boardFilter2");
        let boardFilter3 = document.getElementById("boardFilter3");
        let boardFilter4 = document.getElementById("boardFilter4");
        let boardFilter5 = document.getElementById("boardFilter5");
        let boardFilter6 = document.getElementById("boardFilter6");
        let boardFilter7 = document.getElementById("boardFilter7");
        let boardFilter8 = document.getElementById("boardFilter8");
        let boardFilter9 = document.getElementById("boardFilter9");
        let boardFilter10 = document.getElementById("boardFilter10");
        console.log(boardFilter);
        let a;
        for (let i = 1; i <= classNum; i++) {
            a = boardFilter;
        }
        if (i == "reset") {
            contentsItem.style.filter = ""
            if (classNum == classNum) {
                a.value = "" + ",";
            }
        } else if (i == "gray") {
            contentsItem.style.filter = "grayscale(50)"
            if (classNum == classNum) {
                a.value = "grayscale(50)" + ",";
            }
        } else if (i == "sepia") {
            contentsItem.style.filter = "sepia(1)";
            if (classNum == classNum) {
                a.value = "sepia(1)" + ",";
            }
        } else if (i == "contrast") {
            contentsItem.style.filter = "contrast(1.5)";
            if (classNum == classNum) {
                a.value = "contrast(1.5)" + ",";
            }
        } else if (i == "brightness") {
            contentsItem.style.filter = "brightness(1.3)"
            if (classNum == classNum) {
                a.value = "brightness(1.3)" + ",";
            }
        } else if (i == "invert") {
            contentsItem.style.filter = "invert(1)"
            if (classNum == classNum) {
                a.value = "invert(1)" + ",";
            }
        }
        boardFilter0.value = boardFilter1.value + boardFilter2.value + boardFilter3.value + boardFilter4.value + boardFilter5.value + boardFilter6.value + boardFilter7.value + boardFilter8.value + boardFilter9.value + boardFilter10.value;
    }
}


function range(i) {
    let val1 = document.getElementById("Range1").value
    let val2 = document.getElementById("Range2").value
    let val3 = document.getElementById("Range3").value
    let item = document.getElementById("contentsItem" + classNum)
    let boardFilter0 = document.getElementById("boardFilter0");
    let boardFilter = document.getElementById("boardFilter" + classNum);
    let boardFilter1 = document.getElementById("boardFilter1");
    let boardFilter2 = document.getElementById("boardFilter2");
    let boardFilter3 = document.getElementById("boardFilter3");
    let boardFilter4 = document.getElementById("boardFilter4");
    let boardFilter5 = document.getElementById("boardFilter5");
    let boardFilter6 = document.getElementById("boardFilter6");
    let boardFilter7 = document.getElementById("boardFilter7");
    let boardFilter8 = document.getElementById("boardFilter8");
    let boardFilter9 = document.getElementById("boardFilter9");
    let boardFilter10 = document.getElementById("boardFilter10");
    console.log(boardFilter);
    let a;
    for (let i = 1; i <= classNum; i++) {
        a = boardFilter;
    }
    if (i == "contrast") {
        item.style.filter = "contrast(" + val1 + "%)";
        if (classNum == classNum) {
            a.value = "contrast(" + val1 + "%)" + ",";
        }
    } else if (i == "saturate") {
        item.style.filter = "saturate(" + val2 + "%)";
        if (classNum == classNum) {
            a.value = "saturate(" + val2 + "%)" + ",";
        }
    } else if (i == "Brightness") {
        item.style.filter = "Brightness(" + val3 + "%)";
        if (classNum == classNum) {
            a.value = "Brightness(" + val3 + "%)" + ",";
        }
    }
    boardFilter0.value = boardFilter1.value + boardFilter2.value + boardFilter3.value + boardFilter4.value + boardFilter5.value + boardFilter6.value + boardFilter7.value + boardFilter8.value + boardFilter9.value + boardFilter10.value;
}

function valuetest() {
    let val = document.getElementById("boardFile").value
    console.log("val : " + val)
    if (val == "") {
        alert("파일 없음")
    } else {
        document.getElementById("nextBtn").removeAttribute("hidden")
        document.getElementById("addBtn").removeAttribute("hidden")
        document.getElementById("imgBtnZone").setAttribute("hidden", "hidden")

    }
}

function imgBtnEffect() {
    let imgBtn = document.getElementById("imgBtn")
    imgBtn.style.color = "gray"
    imgBtn.style.fontSize = "65px"

    setTimeout(reset, 150)

    function reset() {
        imgBtn.style.color = "#0d6efd"
        imgBtn.style.fontSize = "70px"
    }
}


// false 일때 댓글 기능을 해제 하도록 개발 해주세요
function replyChk() {
    let val = document.getElementById("flexSwitchCheckDefault").value
    console.log("cktest : " + val)
}


// false 일때 기능 해제 하도록 개발 해주세요
function likeChk() {
    let val = document.getElementById("flexSwitchCheckDefault1").value
    console.log("cktest1 : " + val)
}


//    textarea 이모지 및 키이벤트 함수
$("#text1").emojioneArea({
    pickerPosition: "right",
    searchPosition: "bottom",
    filtersPosition: "bottom",

    events: {
        keypress: function () {
            console.log("key events")

            window.addEventListener("keydown", keysPressed, false);
            window.addEventListener("keyup", keysReleased, false);

            var keys = [];
            let zone = document.querySelector(".emojionearea .emojionearea-editor")

            function keysPressed(e) {
                // store an entry for every key pressed
                keys[e.keyCode] = true;

                // Ctrl + Shift + 5
                // if (keys[17] && keys[16] && keys[53]) {
                if (keys[16] && keys[51]) {
                    // do something
                    console.log("test성공")
                    zone.style.color = "red"
                }

                if (keys[32]) {
                    console.log("test성공")
                    zone.style.color = "#555"
                }
                // Ctrl + f
                if (keys[17] && keys[70]) {
                    // do something
                    // console.log("test성공")

                    // prevent default browser behavior
                    e.preventDefault();
                }
            }

            function keysReleased(e) {
                // mark keys that were released
                keys[e.keyCode] = false;
            }


        }
    }

});

// 체크박스 토글 밸류 변경 함수

$(function () {
    let val = document.getElementById("flexSwitchCheckDefault").value
    $('#flexSwitchCheckDefault').change(function () {
        $('#flexSwitchCheckDefault').val($(this).prop('checked'))
    })

})

$(function () {
    let val = document.getElementById("flexSwitchCheckDefault1").value
    $('#flexSwitchCheckDefault1').change(function () {
        $('#flexSwitchCheckDefault1').val($(this).prop('checked'))
    })

})


let num = 0;

function uploadImgPreview(obj) {
    console.log(obj);
    var maxFileCnt = 10;   // 첨부파일 최대 개수
    var attFileCnt = document.querySelectorAll('.filebox').length;    // 기존 추가된 첨부파일 개수
    var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
    var curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수


    document.querySelector("[id=contentsBody]").innerHTML = null;
    if (curFileCnt > remainFileCnt) {
        alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
        document.querySelector("input[type=file]").value = "";
    } else {
        const boardFile = document.getElementById("boardFile").files;
        if (boardFile[0] == null) {
            alert("파일이 없습니다.");
        } else {
            for (const f of obj.files) {
                validation(f)
            }

        }
        num = 0
    }
}

function uploadImgPreview1(obj) {
    const boardFile = document.getElementById("boardFile");
    var maxFileCnt = 10;   // 첨부파일 최대 개수
    var attFileCnt = document.querySelectorAll('.filebox').length;    // 기존 추가된 첨부파일 개수
    var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
    var curFileCnt = boardFile.files.length;  // 현재 선택된 첨부파일 개수

    if (curFileCnt > remainFileCnt) {
        alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
        document.querySelector("input[type=file]").value = "";
    } else {
        const boardFile = document.getElementById("boardFile").files;
        if (boardFile[0] == null) {
            alert("파일이 없습니다.");
        } else {
            for (const f of obj.files) {
                validation(f)
            }

        }

    }
}

function validation(obj) {
    console.log(obj);
    let fileList = document.getElementById("boardFile").files;
    const fileTypes = ['application/pdf', 'image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif', 'video/mp4', 'application/haansofthwp', 'application/x-hwp'];
    for (let i = 0; i < fileList.length; i++) {
        if (fileList[i].name.length > 100) {
            alert("파일명이 100자 이상인" + fileList[i].name + "파일이 있습니다.");
            document.querySelector("input[id=boardFile]").value = "";
            return false;
        } else if (fileList[i].size > (100 * 1024 * 1024)) {
            alert("최대 파일 용량인 100MB를 초과한" + fileList[i].name + "파일이 있습니다.");
            document.querySelector("input[id=boardFile]").value = "";
            return false;
        } else if (fileList[i].name.lastIndexOf('.') == -1) {
            alert("확장자가 없는" + fileList[i].name + "파일이 있습니다.");
            document.querySelector("input[id=boardFile]").value = "";
            return false;
        } else if (!fileTypes.includes(fileList[i].type)) {
            alert("첨부가 불가능한" + fileList[i].name + "파일이 있습니다.");
            document.querySelector("input[id=boardFile]").value = "";
            return false;
        }
    }

    setTimeout(function () {
        if (fileList[0] == obj) {
            uploadFile(obj);
        }
    },);
    setTimeout(function () {
        if (fileList[1] == obj) {
            uploadFile(obj);
        }
    }, 350);
    setTimeout(function () {
        if (fileList[2] == obj) {
            uploadFile(obj);
        }
    }, 700);
    setTimeout(function () {
        if (fileList[3] == obj) {
            uploadFile(obj);
        }
    }, 1050);
    setTimeout(function () {
        if (fileList[4] == obj) {
            uploadFile(obj);
        }
    }, 1400);
    setTimeout(function () {
        if (fileList[5] == obj) {
            uploadFile(obj);
        }
    }, 1750);
    setTimeout(function () {
        if (fileList[6] == obj) {
            uploadFile(obj);
        }
    }, 2100);
    setTimeout(function () {
        if (fileList[7] == obj) {
            uploadFile(obj);
        }
    }, 2450);
    setTimeout(function () {
        if (fileList[8] == obj) {
            uploadFile(obj);
        }
    }, 2800);
    setTimeout(function () {
        if (fileList[9] == obj) {
            uploadFile(obj);
        }
    }, 3150);
    return true;
}


function uploadFile(obj) {
    const fileTypes2 = ['image/gif', 'image/jpeg', 'image/png'];
    const fileTypes3 = ['video/mp4'];
    const fileTypes4 = ['image/gif', 'image/jpeg', 'image/png', 'video/mp4'];
    if (fileTypes4.includes(obj.type)) {
        let reader = new FileReader();
        reader.addEventListener("load", function () {
            if (fileTypes2.includes(obj.type)) {
                let image = new Image();

                num += 1;

                image.src = this.result;


                image.id = "contentsItem" + num;

                image.className = "contentsItem";


                document.getElementById("contentsBody").appendChild(image);
            } else if (fileTypes3.includes(obj.type)) {
                let video = document.createElement("video");
                num += 1;
                video.setAttribute("src", this.result);
                // video.setAttribute("controls", "controls");
                video.setAttribute("autoplay", "autoplay");
                video.setAttribute("id", "contentsItem" + num);
                video.setAttribute("class", "contentsItem");
                video.setAttribute("loop", "loop");
                document.getElementById("contentsBody").appendChild(video);
            }

        });
        if (obj) {
            valuetest()
            if (document.getElementById("boardFile").files.length >= 2) {
                document.getElementById("slideBtnZone").removeAttribute("hidden")
            }
            reader.readAsDataURL(obj);
        }


    }
}


function plus(obj) {
    const files1 = document.querySelector('#filePlus').files;
    const files = document.querySelector('#boardFile').files;
    const dataTranster = new DataTransfer();
    const fileTypes = ['application/pdf', 'image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif', 'video/mp4', 'application/haansofthwp', 'application/x-hwp'];
    for (let i = 0; i < files1.length; i++) {
        if (files1[i].name.length > 100) {
            alert("파일명이 100자 이상인" + files1[i].name + "파일이 있습니다.");
            document.querySelector("input[id=filePlus]").value = "";
            return false;
        } else if (files1[i].size > (100 * 1024 * 1024)) {
            alert("최대 파일 용량인 100MB를 초과한" + files1[i].name + "파일이 있습니다.");
            document.querySelector("input[id=filePlus]").value = "";
            return false;
        } else if (files1[i].name.lastIndexOf('.') == -1) {
            alert("확장자가 없는" + files1[i].name + "파일이 있습니다.");
            document.querySelector("input[id=filePlus]").value = "";
            return false;
        } else if (!fileTypes.includes(files1[i].type)) {
            alert("첨부가 불가능한" + files1[i].name + "파일이 있습니다.");
            document.querySelector("input[id=filePlus]").value = "";
            return false;
        }
    }
    Array.from(files)
        .forEach(file => {
            dataTranster.items.add(file);
        });
    Array.from(files1)
        .forEach(file => {
            dataTranster.items.add(file);
        });
    document.querySelector('#boardFile').files = dataTranster.files;
    uploadImgPreview1(obj);

}


function addBtnEffect() {
    let btn = document.getElementById("addBtn")
    btn.className = "fa-solid fa-clone"

    function reset() {
        btn.className = "fa-regular fa-clone"
    }

    setTimeout(reset, 150)

}

function black1(){
    document.getElementById("saveCommentBtn_detail").style.color="black"
}
function gray1(){
    document.getElementById("saveCommentBtn_detail").style.color="gray"
}


function focusComment(){
    $(".emojionearea-editor").focus();
}


$(".text1_detail").emojioneArea({
    pickerPosition:"top",
    searchPosition: "bottom",
    filtersPosition: "bottom",
});