<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result</title>
</head>

<body>
	<div id="result">
		<h3>${requestScope["message"]}</h3><br>
		<table>
		<tr> <td><h4> Missed List</h4></td></tr>
			<%
				ArrayList<String> list = (ArrayList<String>) request
						.getAttribute("missedlist");
				for (String call : list) {
					out.println("<tr><td>" + call + "</td></tr>");
				}
			%>
		</table>
		<table>
			<c:forEach items="${list}" var="item">
				<tr>
					<td><c:out value="${item}"/></td>
				</tr>
			</c:forEach>
		</table>
		<table>
		<tr> <td><h4> Dialled and Recieved List</h4></td></tr>
			<%
				ArrayList<String> list = (ArrayList<String>) request
						.getAttribute("dialledlist");
				for (String call : list) {
					out.println("<tr><td>" + call + "</td></tr>");
				}
			%>
		</table>
		<table>
			<c:forEach items="${list}" var="item">
				<tr>
					<td><c:out value="${item}"/></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>
