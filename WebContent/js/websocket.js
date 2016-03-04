var socket = new WebSocket("ws://localhost:8080/Mancala/play");
var player = 1
var gameStarted = false;
var turn = false;
var activeSession = -1;
socket.onmessage = onMessage;
socket.onopen = onOpen;

function onOpen(event) {
	if (isGameStarted) {
		player = 2;
		turn = false;
		gameStarted = true;
		var msg = {};
		msg.event = "start";
		msg.sessionId = sessionId;
		socket.send(JSON.stringify(msg));
		console.log(JSON.stringify(msg));
		
	} else {
		turn = true;
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
	console.log(msg);
	switch (msg.event) {
	case 'ack':
		console.log(msg.message);
		alert(msg.message);
		gameStarted = true;
		activeSession = msg.sessionId;
		$("#sessionConnector").fadeOut();
		break;
		
	case 'play':
		console.log(msg);
		var gamestate = JSON.parse(msg["gamestate"]);
		var deck = gamestate["deck"];
		console.log(deck);
		for(var i=0; i<deck.length; i++) {
			$("#hole"+i).html(deck[i]);
		}
		break;
	}
}