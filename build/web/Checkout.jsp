<%-- 
    Document   : Checkout
    Created on : Nov 1, 2014, 9:17:47 PM
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
        <center><form action="checkout" method="get">
    Book ID:	 <input name="Book_id" type="text" size="50"><br><br>
    Branch ID:  <input name="Branch_id" type="text" size="50"><br><br>
    Card No: <input name="Card_no" type="text" size="50"><br><br>
      <input name="Checkout" type="submit" value="Checkout">
      </form></center>
    </body>
</html>
