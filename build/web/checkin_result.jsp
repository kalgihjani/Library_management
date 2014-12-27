<%-- 
    Document   : checkin_result
    Created on : Nov 2, 2014, 1:12:44 AM
    Author     : DELL
--%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <form action="Checkinaction" method="get">
        <br><br>
        <table style="border: 1px solid; ">
            <tr>
                <td>  </td>
            <td style="border: 1px solid;">Loan ID</td>
            <td style="border: 1px solid;">Book ID</td>
            <td style="border: 1px solid;">Branch ID</td>
            <td style="border: 1px solid;">Card No</td>
            <td style="border: 1px solid;">Check Out Date</td>
            <td style="border: 1px solid;">Due Date</td>
            <td style="border: 1px solid;">Check in Date</td>
            </tr>
      <%
           
            ArrayList abc=(ArrayList)session.getAttribute("checkinrs"); 
            
            ArrayList<Integer> loan_ids=(ArrayList) abc.get(0);
            ArrayList book_ids=(ArrayList) abc.get(1);
            ArrayList<Integer> branches=(ArrayList) abc.get(2);
            ArrayList<Integer> cards=(ArrayList) abc.get(3);
            ArrayList<java.sql.Date> dateout=(ArrayList) abc.get(4);
            ArrayList<java.sql.Date> duedate=(ArrayList) abc.get(5);
            ArrayList<java.sql.Date> datein=(ArrayList) abc.get(6);
            
            for(int i=0;i<book_ids.size();i++)
            {%> 
            <tr>
            <td style="border: 1px solid;"> <input type="radio" name="tupels" value="<%= loan_ids.get(i)%>" id="tupels_0"></td>
          

             <td style="border: 1px solid;"><%out.print((int)loan_ids.get(i)); %></td>
             <td style="border: 1px solid;"><%out.print("\t\t"+(String)book_ids.get(i)); %></td>
             <td style="border: 1px solid;"><%out.print("\t\t"+(int)branches.get(i)); %></td>
             <td style="border: 1px solid;"><%out.print("\t\t"+(int)cards.get(i)); %></td>
             <td style="border: 1px solid;"><%out.print("\t\t"+(java.sql.Date)dateout.get(i)); %></td>
             <td style="border: 1px solid;"><%out.print("\t\t"+(java.sql.Date)duedate.get(i)); %></td>
             <td style="border: 1px solid;"><%out.print("\t\t"+(java.sql.Date)datein.get(i)); %></td>
            </tr>
            <%
            }
        %> </table>
        <input name="Check in" type="submit" value="Check in">
       
        </form>
    </body>
</html>
