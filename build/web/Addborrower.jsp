<%-- 
    Document   : Addborrower
    Created on : Nov 3, 2014, 2:25:34 AM
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
        <center><form action="addborrower" method="get">
    
    First Name:  <input name="Fname" type="text" size="50"><br><br>
    Last Name: <input name="Lname" type="text" size="50"><br><br>
    Address:  <input name="Address" type="text" size="50"><br><br>
    Phone No:: <input name="Phone_no" type="text" size="50"><br><br>
      <input name="add_borrower" type="submit" value="Add Borrower">
      </form></center>
    </body>
   
</html>
