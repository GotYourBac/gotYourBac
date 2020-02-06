var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected){
        $("#conversation").show();
    }
    else{
        $("#conversation").hide();
    }
    $("#happyhour").html("");
}

function connect() {
    var socket = new SockJS('/hhchat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/happyhour', function (happyhour) {
            showHappyHour(JSON.parse(happyhour.body).name);
        });

    });

}

function disconnect() {
    if(stompClient != null){
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/topic/happyhour", {}, JSON.stringify({'name': $("#name").val() + " has RSVP'd"}))
}

function showHappyHour(message) {
    $("#happyhour").append("<tr><td>" + message + "</td></tr>")
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

