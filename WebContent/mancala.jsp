<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Mancala</title>
<link rel="stylesheet" type="text/css"
	href="/Mancala/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/Mancala/css/mancala.css">
</head>

<body>
	
	<div id="sessionConnector">
	<%
		if (request.getAttribute("started") == null) {
	%>

	Welcome to Mancala!
	<br> Use this id to connect with your partner:
	<%
		out.print(request.getAttribute("selfid"));
	%>

	<br> If you have id of your partner, enter it here:
	<form method="post" action="/Mancala/app/start">
		<input type="text" value="" name="id" /> <input type="hidden"
			value=<%=request.getAttribute("selfid")%> name="playerSession" /> <input
			type="submit" value="Play!" />
	</form>
	<%
		}
	%>
	</div>
	<div align="center" class="container">
		<div class="white-space"></div>
		<div align="center" id="turn">Waiting for player to join!</div>
		<table class="table-bordered">
			<tr>
				<td rowspan="2" id="player2area"><div class="goalholes"
						id="player2hole">0</div></td>
				<td class="holearea"><div class="holes" id="hole11">4</div></td>
				<td class="holearea"><div class="holes" id="hole10">4</div></td>
				<td class="holearea"><div class="holes" id="hole9">4</div></td>
				<td class="holearea"><div class="holes" id="hole8">4</div></td>
				<td class="holearea"><div class="holes" id="hole7">4</div></td>
				<td class="holearea"><div class="holes" id="hole6">4</div></td>
				<td rowspan="2" id="player1area"><div class="goalholes"
						id="player1hole">0</div></td>
			</tr>
			<tr>
				<td class="holearea"><div class="holes" id="hole0">4</div></td>
				<td class="holearea"><div class="holes" id="hole1">4</div></td>
				<td class="holearea"><div class="holes" id="hole2">4</div></td>
				<td class="holearea"><div class="holes" id="hole3">4</div></td>
				<td class="holearea"><div class="holes" id="hole4">4</div></td>
				<td class="holearea"><div class="holes" id="hole5">4</div></td>
			</tr>
		</table>
	</div>
	<div align="center" id="msg"><p></p></div>
	<script src="https://code.jquery.com/jquery-2.2.0.min.js"
		type="text/javascript"></script>
	<script>
		var sessionId = <%=request.getAttribute("sessionid")%>
		var selfId = <%=request.getAttribute("selfid")%>
		<%boolean b = request.getAttribute("started") != null;%>
		var isGameStarted = <%= b %>
	</script>
	<script src="/Mancala/js/mancala.js" type="text/javascript"></script>
	<script src="/Mancala/js/websocket.js" type="text/javascript"></script>
</body>
</html>