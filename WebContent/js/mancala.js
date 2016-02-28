/**
 * Created by Saurabh on 17-Feb-16.
 */

$(document).ready(function() {
	$("#msg").hide(1000);
	$(".holearea").click(function(event) {
		if (!gameStarted) {
			$("#msg").html("Waiting for player2..");
			$("#msg").fadeIn(1000).delay(1000);
			$("#msg").fadeOut(1000);
		}
		var pebbleCount = event.target.innerHTML;
		if (pebbleCount == 0) {
			console.log("No pebbles present!")
		} else {
			console.log(event.target.id);
			console.log("Make ajax call!");
			var msg = {};
			msg.event = "play";
			msg.hole = ("" + event.target.id).substring(4);
			socket.send(JSON.stringify(msg));
			for (var i = 1; i < 13; i++) {
				$("#hole" + i).html(Math.floor(Math.random() * 10));
			}
		}
	});

});