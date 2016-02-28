var socket = new WebSocket("ws://localhost:8080/Mancala/play");
var player = 1
var gameStarted = false;
var turn = false;
socket.onmessage = onMessage;
socket.onopen = onOpen;

function onOpen(event) {
	if (isGameStarted) {
		var msg = {};
		msg.event = "start";
		msg.sessionId = sessionId;
		socket.send(JSON.stringify(msg));
		console.log(JSON.stringify(msg));
		player = 2;
		gameStarted = true;
	} else {
		var msg = {};
		msg.event = "registration";
		msg.selfId = selfId;
		socket.send(JSON.stringify(msg));
		console.log(JSON.stringify(msg));
	}
}

function onMessage(event) {
	console.log(event.data);
	var msg = JSON.parse(event.data);
	switch (msg.event) {
	case 'ack':
		console.log(msg.message);
		alert(msg.message);
		gameStarted = true;
		$("#sessionConnector").fadeOut();
		break;
	}
}