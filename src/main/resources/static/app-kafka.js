var stompClient = null;

function connect() {
    var socket = new SockJS('/live-temperature');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/temperature', function (greeting) {
            showGreeting(greeting.body);
        });
    });
}

function sendName() {
    $.ajax("/message/producer?message="+$("#name").val());
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendName(); });

    connect();
});

