/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function () {
    "use strict";
    $(function () {
        notification();
        messageNotification();
        setInterval(notification, 5000);
        setInterval(messageNotification, 5000);
    });

    function notification() {
        $.ajax("/SeniorProject/notification", {
            "type": "post"
        }).done(getNotification);
        
    }
    
    function messageNotification(){
        $.ajax("/SeniorProject/messagenotification",{
            "type": "post"
        }).done(getMessageNotification);
    }

    function getNotification(result) {
  
        $("#notification").empty();
        var results = result;
        var beids = results.split("@@@");
        var notificationno = 0;
        for (var i = 1; i < beids.length; i=i+2) {
            $("#notification").append("<a href='/SeniorProject/BlogEntryList/view?id=" + beids[i] + "'>" + beids[i+1] + " writes a comment!</a>");
            notificationno++;
        }
        $("#notificationno").text(notificationno);

    }
    
    function getMessageNotification(result){
        $("#messages").empty();
        var results = result;
        var messages = results.split("@@@");
        var messageno = 0;
        for (var i = 1; i < messages.length; i++) {
            $("#messages").append("<a href='/SeniorProject/message?user=" + messages[i] + "'>" + messages[i] + " sent you some messages!</a>");
            messageno++;
        }
        
         $("#messageno").text(messageno);
    }
})();
