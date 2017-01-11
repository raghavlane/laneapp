<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Raghav Application</title>
</head>
<body>
	<div>
		<h4>${requestScope["error"]}<h4>
		<h2>Steps: 	1. click on update message to view/edit the messages</h2>
		<h2>		2. to get the list of calls just click on get list and upload the excel sheets</h2>
		<h2>		3. to send message click on send message and upload the excel sheets </h2>
				<table>
					<tr>
						<td>
							<form action="getList.jsp">
								<input type="submit" value="getList">
							</form>
						</td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td>
							<form action="sendMessage.jsp">
								<input type="submit" value="sendMessage">
							</form>
						</td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td>
							<form action="upload" method="get">
								<input type="submit" value="updateMessage">
							</form>
						</td>
					</tr>
				</table>
	</div>
</body>
</html>