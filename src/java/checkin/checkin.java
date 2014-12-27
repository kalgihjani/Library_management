/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class checkin extends HttpServlet {

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
            String book_id=request.getParameter("Book_id");
            String card_no=request.getParameter("Card_no");
            String fname=request.getParameter("Fname");
            String lname=request.getParameter("Lname");
            
            if(book_id.equals(""))
            book_id="%";
            if(card_no.equals(""))
            card_no="%";
            if(fname.equals(""))
            fname="%";
            if(lname.equals(""))
            lname="%";
            
            ArrayList<Integer> loan_ids=new ArrayList<Integer>();
            ArrayList book_ids=new ArrayList();
            ArrayList<Integer> branches=new ArrayList<Integer>();
            ArrayList<Integer> cards=new ArrayList<Integer>();
            ArrayList<java.sql.Date> dateout=new ArrayList<java.sql.Date>();
            ArrayList<java.sql.Date> duedate=new ArrayList<java.sql.Date>();
            ArrayList<java.sql.Date> datein=new ArrayList<java.sql.Date>();
            ArrayList total=new ArrayList();
            
            String sql;
          //  ResultSet rs;
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/library?zeroDateTimeBehavior=convertToNull","root","");
            Statement s=c.createStatement();
            
            sql="select * from BOOK_LOANS as bl,BORROWER as bw where bl.Book_id like '"+book_id+"' and bl.Card_no like '"+card_no+"' and bw.Fname like '"+fname+"' and bw.Lname like '"+lname+"' and bw.Card_no=bl.Card_no and bl.Date_in is NULL;";
            //JOptionPane.showMessageDialog(null, sql);
            ResultSet rs=s.executeQuery(sql);
            
            while(rs.next())
                        {
                            int a=rs.getInt("Loan_id");
                            String b=rs.getString("Book_id");
                            int c1=rs.getInt("Branch_id");
                            int d=rs.getInt("Card_no");
                            java.sql.Date dtout=rs.getDate("Date_out");
                             java.sql.Date duedt=rs.getDate("Due_date");
                              java.sql.Date dtin=rs.getDate("Date_in");
                            //JOptionPane.showMessageDialog(null, b);
                            
                            //JOptionPane.showMessageDialog(null,a);
                            //System.out.println(a);
                            loan_ids.add(a);
                            book_ids.add(b);
                            branches.add(c1);
                            cards.add(d);
                            dateout.add(dtout);
                            duedate.add(duedt);
                            datein.add(dtin);
                            //JOptionPane.showMessageDialog(null,book_ids.get(0));
                        }
                //System.out.println(a);
                total.add(loan_ids);
                total.add(book_ids);
                total.add(branches);
                total.add(cards);
                total.add(dateout);
                total.add(duedate);
                total.add(datein);
                
                session.setAttribute("checkinrs",total);
                
                
                response.sendRedirect("checkin_result.jsp");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(checkin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(checkin.class.getName()).log(Level.SEVERE, null, ex);
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
