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
import java.sql.SQLException;
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
public class fineupdate extends HttpServlet {

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
            Class.forName("com.mysql.jdbc.Driver");
            Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/library?zeroDateTimeBehavior=convertToNull","root","");
            
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String currentTime = sdf.format(dt);
            java.sql.Date today = java.sql.Date.valueOf(currentTime);
            
            String sql="insert into fines(Loan_id,Fine_Amount) SELECT Loan_id,(DATEDIFF(Date_in,Due_date)* .25) FROM Book_loans WHERE (Date_in > Due_date AND Loan_id NOT IN (SELECT Loan_id FROM Fines)) UNION (SELECT Loan_id, (DATEDIFF('2014-11-10',Due_date)*.25) FROM Book_loans WHERE ('"+today+"' > Due_date AND Date_in IS NULL AND Loan_id NOT IN (SELECT Loan_id FROM Fines)));";
            PreparedStatement p=c.prepareStatement(sql);
            p.executeUpdate();
            
            String sql1="update fines,book_loans set Fine_Amount=DATEDIFF('"+today+"',Due_date)*0.25 where Paid=0 and Due_date in (select Due_date from Book_loans where fines.Loan_id=Book_loans.Loan_id and Date_in is null);";
            PreparedStatement p1=c.prepareStatement(sql1);
            p1.executeUpdate();
            JOptionPane.showMessageDialog(null,"Fines are updateD!");
            response.sendRedirect("searchfines.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(fineupdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(fineupdate.class.getName()).log(Level.SEVERE, null, ex);
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
