<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Send Message</title>
    </head>
 
    <body> 
        <div>
        	<h4>${requestScope["error"]}<h4>
            <h3> Choose File to send Message </h3>
            <form action="upload" method="post" enctype="multipart/form-data">
                <input name ="ExcelFile1" type="file" name="file1" /><br>
                <input name ="ExcelFile2" type="file" name="file2" />
                <input type="submit" value="sendMessage" />
            </form>          
        </div>
    </body>
</html>