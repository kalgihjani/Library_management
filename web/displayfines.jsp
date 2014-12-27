<%-- 
    Document   : displayfines
    Created on : Nov 3, 2014, 1:51:46 AM
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
        <form action="payfineaction" method="get">
             <br><br>
        <table style="border: 1px solid; ">
            <tr>
                <td>  </td>
            <td style="border: 1px solid;">Fname</td>
            <td style="border: 1px solid;">Lname</td>
            
            <td style="border: 1px solid;">Card No</td>
            <td style="border: 1px solid;">Fine</td>
            
            </tr>
      <%
           
            ArrayList abc=(ArrayList)session.getAttribute("searchrs"); 
            //out.print("HARSHAL");
            ArrayList fnames=(ArrayList) abc.get(0);
            ArrayList lnames=(ArrayList) abc.get(1);
            ArrayList<Integer> cards=(ArrayList) abc.get(2);
            ArrayList<Double> totalfine=(ArrayList) abc.get(3);
            
            
            for(int i=0;i<fnames.size();i++)
            {%> 
            <tr>
          <td style="border: 1px solid;"><input type="radio" name="tupels" value="<%= cards.get(i)%>" id="tupels_0"></td>
          

           
            <td style="border: 1px solid;"><% out.print("\t\t"+(String)fnames.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("\t\t"+(String)lnames.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("\t\t"+(int)cards.get(i)); %></td>
            <td style="border: 1px solid;"><% out.print("\t\t"+(Double)totalfine.get(i)); %></td>
            
            </tr><%
            
            }
            %></table>
            <br><br><input name="Pay_Fine" type="submit" value="Pay Fine">
    </form>
    </body>
</html>
