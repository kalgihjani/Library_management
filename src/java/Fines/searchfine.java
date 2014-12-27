/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fines;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class searchfine extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        PrintWriter out = response.getWriter();
        try {
            String card_no=request.getParameter("Card_no");
            String fname=request.getParameter("Fname");
            String lname=request.getParameter("Lname");
            
             if(card_no.equals(""))
            card_no="%";
            if(fname.equals(""))
            fname="%";
            if(lname.equals(""))
            lname="%";
            
            ArrayList fnames=new ArrayList();
            ArrayList lnames=new ArrayList();
            ArrayList<Integer> cards=new ArrayList<Integer>();
            ArrayList<Double> totalfine=new ArrayList<Double>();
            ArrayList total=new ArrayList();
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/library?zeroDateTimeBehavior=convertToNull","root","");
            
            String sql="select borrower.Fname,borrower.Lname,borrower.Card_no,SUM(Fine_Amount) from borrower,book_loans,fines where borrower.Card_no like '"+card_no+"' and Fname like '"+fname+"' and Lname like '"+lname+"' and borrower.Card_no=book_loans.Card_no and book_loans.Loan_id=Fines.Loan_id and Fines.paid is null group by borrower.Card_no;";
             Statement s=c.createStatement();
             ResultSet rs=s.executeQuery(sql);
            
            
             while(rs.next())
                        {
                            
                            String a=rs.getString(1);
                            String b=rs.getString(2);
                            int c1=rs.getInt(3);
                            double d=rs.getDouble(4);
                            
                            fnames.add(a);
                            lnames.add(b);
                            cards.add(c1);
                            totalfine.add(d);
                         }
             if(fnames.size()==0)
             {
                 JOptionPane.showMessageDialog(null, "no fines found!");
                 response.sendRedirect("searchfines.jsp");
             }
             else{
             total.add(fnames);
             total.add(lnames);
             total.add(cards);
             total.add(totalfine);
             
             session.setAttribute("searchrs",total);
             response.sendRedirect("displayfines.jsp");
            
             } 
            
        } catch (SQLException ex) {
            Logger.getLogger(searchfine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(searchfine.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
