var socket = new WebSocket("ws://10.0.0.24:8080/Mancala/play");
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
		var gamestate = JSON.parse(msg["gamestate"]);
		if (gamestate.winner >= 0) {
			if (gamestate.winner == 0) {
				$("#msg").html("Game draw!").fadeIn();
			} else if (gamestate.winner == 1) {
				$("#msg").html("Player 1 won!").fadeIn();
			} else if (gamestate.winner == 2) {
				$("#msg").html("Player 2 won!").fadeIn();
			}
		}

		var deck = gamestate["deck"];
		for (var i = 0; i < deck.length; i++) {
			$("#hole" + i).html(deck[i]);
		}
		$("#player1hole").html(gamestate.player1Score);
		$("#player2hole").html(gamestate.player2Score);
		turn = (gamestate.playerTurn == player);
		$(".highlight").removeClass("highlight");
		$("#hole" + gamestate.lastMove).addClass("highlight");

		if (turn) {
			$("#turn").html("Your turn!");
		} else {
			$("#turn").html("Waiting for other player..");
		}
		break;
	}
}

function onClose(event) {
	var msg = {};
	msg.event = "close";
	msg.activeSession = activeSession;
	msg.selfId = selfId;
	socket.send(JSON.stringify(msg));
}