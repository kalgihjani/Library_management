/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
public class Search_result extends HttpServlet {

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
            String Book_id=request.getParameter("Book_id");
            String Title=request.getParameter("Title");
            String Author=request.getParameter("Author");
            //java.util.Date dt = new java.util.Date();
              //      java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                //    String currentTime = sdf.format(dt);
            //java.sql.Date a11 = java.sql.Date.valueOf(currentTime);
            //Date date=new Date();
           // JOptionPane.showMessageDialog(null,a11);
            //String check=null;
            if(Book_id== null || Book_id.equals(""))
            Book_id="%";
            if(Title==null || Title.equals(""))
            Title="\"%\"";
            else
             Title="\"%"+Title+"%\"";
            if(Author==null || Author.equals(""))
                Author="\"%\"";
            else
                Author="\"%"+Author+"%\"";
            ArrayList book_ids=new ArrayList();
            ArrayList titles=new ArrayList();
            ArrayList branches=new ArrayList();
            ArrayList<Integer> copies=new ArrayList<Integer>();
            ArrayList<Integer> avail_copies=new ArrayList<Integer>();
            ArrayList total=new ArrayList();
            //String a=null;
            
           
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/library?zeroDateTimeBehavior=convertToNull","root","");
            Statement s=c.createStatement();
            String sql="select b.Book_id,b.Title,bc.Branch_id,bc.No_of_copies from book as b,book_copies as bc,book_authors as ba where b.Book_id like '"+ Book_id + "' and b.Title like " + Title+" and b.Book_id=bc.Book_id and ba.Author_name like "+Author+" and b.Book_id=ba.Book_id;";
            //JOptionPane.showMessageDialog(null,sql);
            //System.out.println(sql);
            ResultSet rs=s.executeQuery(sql);
                //System.out.println("In servlet:"+rs.getString("Book_id"));
               //JOptionPane.showMessageDialog(null,"query executed!");
                //JOptionPane.showMessageDialog(null,rs.getString("Book_id"));
                //rs.next();
                while(rs.next())
                        {
                            String a=rs.getString("Book_id");
                            String b=rs.getString("Title");
                            String c1=rs.getString("Branch_id");
                            int d=rs.getInt("No_of_copies");
                            
                            //JOptionPane.showMessageDialog(null,a);
                            //System.out.println(a);
                            book_ids.add(a);
                            titles.add(b);
                            branches.add(c1);
                            copies.add(d);
                            //JOptionPane.showMessageDialog(null,book_ids.get(0));
                        }
                //System.out.println(a);
                total.add(book_ids);
                total.add(titles);
                total.add(branches);
                total.add(copies);
                //total.add(book_ids);
                for(int i=0;i<book_ids.size();i++)
                {
                String sql1="select COUNT(*) from book_loans as bl where bl.Book_id='"+book_ids.get(i)+"' and bl.Branch_id='"+branches.get(i)+"' and bl.Date_in is NULL";
                ResultSet rs1=s.executeQuery(sql1);
                rs1.next();
                int temp=rs1.getInt(1);
                int avail;
                int qwe=(int)copies.get(i);
                
                avail = qwe-temp;
                avail_copies.add(avail);
                }
                total.add(avail_copies);
                session.setAttribute("sr",total);
                
                
                response.sendRedirect("Search_result1.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(Search_result.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Search_result.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
        }
    }
    //select b.Book_id,b.Title,bc.Branch_id,bc.No_of_copies from book as b,book_copies as bc,book_authors as ba where b.Book_id like "+Book_id+" and b.Title like "+Title+" and b.Book_id=bc.Book_id and ba.Author_name like "+Author+" and b.Book_id=ba.Book_id;

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
