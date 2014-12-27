<%--
    Document   : Search_result1
    Created on : Nov 1, 2014, 2:59:42 PM
    Author     : DELL
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <br><br>
        <table style="border: 1px solid; ">
            <tr>
                <td style="border: 1px solid;">Book ID</td>
            <td style="border: 1px solid;">Book Title</td>
            <td style="border: 1px solid;">Branch ID</td>
            <td style="border: 1px solid;">Total copies at branch</td>
            <td style="border: 1px solid;">Available Copies at branch</td>
            </tr>
        <%
            ArrayList abc=(ArrayList)session.getAttribute("sr");
            //out.print("HARSHAL");
            ArrayList book_ids=(ArrayList) abc.get(0);
            ArrayList titles=(ArrayList) abc.get(1);
            ArrayList branches=(ArrayList) abc.get(2);
            ArrayList<Integer> copies=(ArrayList) abc.get(3);
            ArrayList<Integer> avail_copies=(ArrayList) abc.get(4);
            for(int i=0;i<book_ids.size();i++)
            {%> 
            <tr>
            <td style="border: 1px solid;"><% out.print((String)book_ids.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("     "+(String)titles.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("\t\t"+(String)branches.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("\t\t"+(int)copies.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("\t\t"+(int)avail_copies.get(i)); %></td>
            </tr><%
            }
        %>
        </table>   <br>
        <form action="index.jsp" method="get">
        <input name="home" type="submit" value="Home">
        </form>
    </body>
</html>
