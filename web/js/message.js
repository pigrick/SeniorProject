/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function () {
    $(function () {
        $(".chatbox").scrollTop($('.chatbox')[0].scrollHeight);
        $("#sendmessage").keypress(presseddown);
        $("#sendmessage").keyup(pressedup);
        getMessage();
        setInterval(getMessage, 5000);

    });

    function pressedup(e) {
        if (e.keyCode === 13) {
            $("#sendmessage").val("");
        }
    }


    function presseddown(e) {
        if (e.keyCode === 13) {
            if ($("#sendmessage").val() !== "") {
                var message = $("#sendmessage").val();
                sendmessage(message);
            }
        }
    }

    function sendmessage(message) {
        var targetname = $("#targetname").val();
        var sendname = $("#sendname").val();
        var lastid = $(".currentid").last().val();

        $.ajax("message", {
            "type": "post",
            "data": {
                "targetname": targetname,
                "details": message,
                "lastid": lastid
            },
            "success": getMessage
            

        });
    }

    function getMessage() {
        var targetname = $("#targetname").val();
        var username = $("#sendname").val();
        var lastid = $(".currentid").last().val();
        $.ajax("getmessage", {
            "type": "post",
            "data": {
                "targetname": targetname,
                "lastid": lastid
            },
            "success": getresult

                    //$(".chatbox").append("<div class='textright'><span class='messagename'>"+ username + "</span><br/>"+ result +"</div><br/>")
                    //$(".chatbox").scrollTop($('.chatbox')[0].scrollHeight);


        });

        function getresult(result) {
            var results = result;
            var username = $("#sendname").val();
            var newmessages = results.split("@@@");
            for (var i = 1; i < newmessages.length; i = i + 3) {
                $(".chatbox").append("<input class='currentid' type='hidden' name='currentid' value='" + newmessages[i] + "'/>");
                if (newmessages[i + 1] === username) {                   
                    $(".chatbox").append("<div class='textright'><span class='messagename'>" + username + "</span><br/>" + newmessages[i + 2] + "</div><br/>");
                } else {
                    $(".chatbox").append("<div class='textleft'><span class='messagename'>" + targetname + "</span><br/>" + newmessages[i + 2] + "</div><br/>");
                }
                $(".currentid").last().val(newmessages[i]);
                $(".chatbox").scrollTop($('.chatbox')[0].scrollHeight);

            }

        }
    }

})();
