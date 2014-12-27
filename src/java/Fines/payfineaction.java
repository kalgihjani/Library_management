/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fines;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class payfineaction extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            String card_no=request.getParameter("tupels");
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/library?zeroDateTimeBehavior=convertToNull","root","");
            
            String sql="select fines.Loan_id from book_loans,fines where book_loans.Card_no='"+card_no+"' and book_loans.Loan_id=fines.Loan_id and book_loans.Date_in is null;";
            Statement s=c.createStatement();
             ResultSet rs=s.executeQuery(sql);
             if(rs != null)
             
             
             {
                 JOptionPane.showMessageDialog(null,"the borrower has yet not returned the book!");
                 response.sendRedirect("searchfines.jsp");
             }
             String sql1="update fines,book_loans set paid=1 where book_loans.Card_no='"+card_no+"' and book_loans.Loan_id=fines.Loan_id;";
             PreparedStatement p=c.prepareStatement(sql1);
            p.executeUpdate();
            JOptionPane.showMessageDialog(null,"Fine is paid!");
            response.sendRedirect("index.jsp");
            
            
        
        } 
        catch (SQLException ex) {            
            Logger.getLogger(payfineaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(payfineaction.class.getName()).log(Level.SEVERE, null, ex);
        }        finally {            
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
