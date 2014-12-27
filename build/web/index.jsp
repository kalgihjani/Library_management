<%-- 
    Document   : index
    Created on : Nov 1, 2014, 12:02:52 PM
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
    	<br>
        <form action="Search.jsp" method="get">
        <center><input name="Search" type="submit" value="Search"></center>
        </form><br>
        
        <form action="Checkout.jsp" method="get">
        <center><input name="Check Out" type="submit" value="Check Out"></center>
        </form><br>
         <form action="Checkin.jsp" method="get">
        <center><input name="Check In" type="submit" value="Check In"></center>
        </form><br>
         <form action="Addborrower.jsp" method="get">
        <center><input name="Add new Borrower" type="submit" value="Add new Borrower"></center>
        </form><br>
         <form action="fineupdate" method="get">
        <center><input name="Check Fines" type="submit" value="Check Fines"></center>
        </form>
    </body>
</html>
