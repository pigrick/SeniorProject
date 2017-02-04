/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function () {
    "use strict";
    $(function () {
        if ($("#likestatus").val() === "false") {
            $("#likebutton").html("Like");
            $("#likebutton").click(function () {
                likebe();
            });
        } else {
            $("#likebutton").html("Unlike");
            $("#likebutton").click(function () {
                unlikebe();
            });
        }
        ;

        $(".commentclass").each(function () {
            var t = $(this);
            var id = t.find("input[name=commentid]").val();
            var status = t.find("input[name=likecommentstatus]").val();
            var btn = t.find("button");
            if (status === "false") {
                btn.text("Like");
                t.find("button").click(function () {
                    likecomment(id, t);
                });
            } else {
                btn.text("Unlike");
                t.find("button").click(function () {
                    unlikecomment(id, t);
                });

            }
        });
    });

    function likebe() {
        var beid = $("#beid").val();
        $.ajax("likeblogentry", {
            "type": "post",
            "data": {
                "beid": beid,
                "likey": "like"
            },
            "success": function () {
                $("#likebutton").unbind("click");
                $("#likebutton").html("Unlike");
                var likesnumber = $("#likesnumber").html();
                likesnumber++;
                $("#likesnumber").html(likesnumber);
                $("#likebutton").click(function () {
                    unlikebe();
                });
            }
        });
    }

    function unlikebe() {
        var beid = $("#beid").val();
        $.ajax("likeblogentry", {
            "type": "post",
            "data": {
                "beid": beid,
                "likey": "unlike"
            },
            "success": function () {
                $("#likebutton").unbind("click");
                $("#likebutton").html("Like");
                var likesnumber = $("#likesnumber").html();
                likesnumber--;
                $("#likesnumber").html(likesnumber);
                $("#likebutton").click(function () {
                    likebe();
                });
            }
        });
    }

    function likecomment(commentid, elem) {
        $.ajax("likecomment", {
            "type": "post",
            "data": {
                "commentid": commentid,
                "likey": "like"
            },
            "success": function () {
                var btn = elem.find("button");
                var num = elem.find("span");

                btn.unbind("click");
                btn.html("Unlike");
                var likesnumber = num.html();
                likesnumber++;
                num.html(likesnumber);
                btn.click(function () {
                    unlikecomment(commentid, elem);
                });
            }
        });
    }

    function unlikecomment(commentid, elem) {
        $.ajax("likecomment", {
            "type": "post",
            "data": {
                "commentid": commentid,
                "likey": "unlike"
            },
            "success": function () {
                var btn = elem.find("button");
                var num = elem.find("span");

                btn.unbind("click");
                btn.html("Like");
                var likesnumber = num.html();
                likesnumber--;
                num.html(likesnumber);
                btn.click(function () {
                    likecomment(commentid, elem);
                });
            }
        });
    }

})();
/*
 (function () {
 $(document).ready(function () {
 
 $("#like").click(function () {
 alert("hello");
 });
 
 $("#unlike").click(function () {
 alert("hello");
 });*/
/*
 function like() {
 var beid = $("#beid").val();
 $.ajax("likeblogentry", {
 "type": "post",
 "data": {
 "beid": beid,
 "likey": "like"
 }
 }).done(function () {
 alert(beid);
 })
 }*/