/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package borrower;

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
public class addborrower extends HttpServlet {

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
            String fname=request.getParameter("Fname");
            String lname=request.getParameter("Lname");
            String address=request.getParameter("Address");
            String phoneno=request.getParameter("Phone_no");
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/library?zeroDateTimeBehavior=convertToNull","root","");
            Statement s=c.createStatement();
             
            if(fname.equals("") || lname.equals("") || address.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Name and address are compulsory!");
                response.sendRedirect("Addborrower.jsp");
            }
            else
            {
                String sql="select Count(*) from borrower where Fname='"+fname+"' and Lname='"+lname+"' and Address='"+address+"';";
                ResultSet rs=s.executeQuery(sql);
                rs.next();
                int check=rs.getInt(1);
                if(check>=1)
                {
                    JOptionPane.showMessageDialog(null,"The borrower already exist!");
                    response.sendRedirect("Addborrower.jsp");
                }
                else{
                    String sql1="select MAX(Card_no) from borrower;"; 
                ResultSet rs1=s.executeQuery(sql1);
                rs1.next();
                int newcardno=rs1.getInt(1);
                newcardno=newcardno+1;
                String sql2="insert into borrower values('"+newcardno+"','"+fname+"','"+lname+"','"+address+"','"+phoneno+"');";
                PreparedStatement p=c.prepareStatement(sql2);
            p.executeUpdate();
            JOptionPane.showMessageDialog(null,"The borrower has been added!");
                    response.sendRedirect("index.jsp");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(addborrower.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addborrower.class.getName()).log(Level.SEVERE, null, ex);
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
