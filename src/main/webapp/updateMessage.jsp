<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Message</title>
</head>

<body onload="myFunction()">
	<div>
		<h4>${requestScope["error"]}<h4>
		<%String emess=request.getAttribute("existingMessage").toString();
		if(null==emess)
			emess="";
		%>
		<h3>Enter the Message</h3>
		<form action="upload" method="post">
			<textarea id="mySelect" name=message rows="15" cols="50" oninput="myFunction()"><%out.print(emess);%></textarea>
			<input type="hidden" name ="flow" value="updateMessage">
			<input type="submit" value="updateMessage" />
		</form>
		<p id="demo" style="font-size:90%"></p>
		<script>
		function myFunction() {
		    var x = document.getElementById("mySelect").value;
		    document.getElementById("demo").innerHTML = "characters: " + x.length;
		}
		</script>
	</div>
</body>
</html>