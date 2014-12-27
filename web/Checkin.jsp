<%-- 
    Document   : Checkin
    Created on : Nov 2, 2014, 1:14:28 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <center><form action="checkin" method="get">
    Book ID:	 <input name="Book_id" type="text" size="50"><br><br>
    Card No: <input name="Card_no" type="text" size="50"><br><br>
    Fname:  <input name="Fname" type="text" size="50"><br><br>
    Lname:  <input name="Lname" type="text" size="50"><br><br>
      <input name="Checkin" type="submit" value="Checkin">
      </form></center>
    </body>
</html>
