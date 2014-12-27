/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Checkout;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
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
public class checkout extends HttpServlet {

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
        
        String book_id=request.getParameter("Book_id");
        String branch_id=request.getParameter("Branch_id");
        String card_no=request.getParameter("Card_no");
          ResultSet rs;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/library?zeroDateTimeBehavior=convertToNull","root","");
            Statement s=c.createStatement();
            if(book_id.equals("") || branch_id.equals("") || card_no.equals(""))
            { //System.out.print(book_id+branch_id+card_no);
                JOptionPane.showMessageDialog(null,"please enter ALL the details!");
                response.sendRedirect("Checkout.jsp");
                
            }
            else
            {   String sql8="select COUNT(*) from borrower where Card_no='"+card_no+"'";
                ResultSet rs8=s.executeQuery(sql8);
                rs8.next();
               int brw=rs8.getInt(1);
               if(brw==0)
               {
                   JOptionPane.showMessageDialog(null, "the borrower does not exist!");
                   response.sendRedirect("Checkout.jsp");
               }
                
                
                String sql="select COUNT(*) from book_loans where Card_no='"+card_no+"'";
               rs=s.executeQuery(sql);
                rs.next();
                int loans=rs.getInt(1);
                sql="select COUNT(*) from BOOK_LOANS as bl,FINES as f where Card_no='"+card_no+"' and bl.Loan_id=f.loan_id and Paid=0";
                ResultSet rs4=s.executeQuery(sql);
                rs4.next();
                int dueloans=rs4.getInt(1);
                if(loans>=3)
                {
                    JOptionPane.showMessageDialog(null, "The borrower has already three loans!");
                    response.sendRedirect("Checkout.jsp");
                }
                else if(dueloans>=1)
                {
                    JOptionPane.showMessageDialog(null, "The borrower has unpaid loans!");
                    response.sendRedirect("Checkout.jsp");
                }
                
                else{
                sql="select No_of_copies from BOOK_COPIES where Book_id='"+book_id+"' and Branch_id='"+branch_id+"'";
                ResultSet rs1=s.executeQuery(sql);
                rs1.next();
                int x=rs1.getInt(1);
                sql="select Count(*) from BOOK_LOANS where Book_id='"+book_id+"' and Branch_id='"+branch_id+"'";
                ResultSet rs2=s.executeQuery(sql);
                rs2.next();
                int y=rs2.getInt(1);
                int z=x-y;
                if(z<=0)
                {
                    JOptionPane.showMessageDialog(null, "There are no more copies available at your library branch!");
                    response.sendRedirect("Checkout.jsp");
                }
                
                else{
                sql="select max(Loan_id) from BOOK_LOANS";
                ResultSet rs5=s.executeQuery(sql);
                rs5.next();
                int loanid=rs5.getInt(1);
                loanid=loanid+1;
                java.util.Date dt = new java.util.Date();
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    String currentTime = sdf.format(dt);
            java.sql.Date dateout = java.sql.Date.valueOf(currentTime);
            dt = addDays(dt, 14);
                    currentTime = sdf.format(dt);
                java.sql.Date duedate = java.sql.Date.valueOf(currentTime);
                sql="insert into book_loans values('"+loanid+"','"+book_id+"','"+branch_id+"','"+card_no+"','"+dateout+"','"+duedate+"',NULL);";                
                 PreparedStatement p=c.prepareStatement(sql);
                 p.executeUpdate();
                  JOptionPane.showMessageDialog(null, "checkout successful!");
                 response.sendRedirect("index.jsp");
                }
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(checkout.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(checkout.class.getName()).log(Level.SEVERE, null, ex);
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

    private static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}
