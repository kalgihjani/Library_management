package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;

public final class displayfines_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <form action=\"payfineaction\" method=\"get\">\n");
      out.write("             <br><br>\n");
      out.write("        <table style=\"border: 1px solid; \">\n");
      out.write("            <tr>\n");
      out.write("                <td>  </td>\n");
      out.write("            <td style=\"border: 1px solid;\">Fname</td>\n");
      out.write("            <td style=\"border: 1px solid;\">Lname</td>\n");
      out.write("            \n");
      out.write("            <td style=\"border: 1px solid;\">Card No</td>\n");
      out.write("            <td style=\"border: 1px solid;\">Fine</td>\n");
      out.write("            \n");
      out.write("            </tr>\n");
      out.write("      ");

           
            ArrayList abc=(ArrayList)session.getAttribute("searchrs"); 
            //out.print("HARSHAL");
            ArrayList fnames=(ArrayList) abc.get(0);
            ArrayList lnames=(ArrayList) abc.get(1);
            ArrayList<Integer> cards=(ArrayList) abc.get(2);
            ArrayList<Double> totalfine=(ArrayList) abc.get(3);
            
            
            for(int i=0;i<fnames.size();i++)
            {
      out.write(" \n");
      out.write("            <tr>\n");
      out.write("          <td style=\"border: 1px solid;\"><input type=\"radio\" name=\"tupels\" value=\"");
      out.print( cards.get(i));
      out.write("\" id=\"tupels_0\"></td>\n");
      out.write("          \n");
      out.write("\n");
      out.write("           \n");
      out.write("            <td style=\"border: 1px solid;\">");
 out.print("\t\t"+(String)fnames.get(i)); 
      out.write("</td>\n");
      out.write("            <td style=\"border: 1px solid;\">");
 out.print("\t\t"+(String)lnames.get(i)); 
      out.write("</td>\n");
      out.write("            <td style=\"border: 1px solid;\">");
 out.print("\t\t"+(int)cards.get(i)); 
      out.write("</td>\n");
      out.write("            <td style=\"border: 1px solid;\">");
 out.print("\t\t"+(Double)totalfine.get(i)); 
      out.write("</td>\n");
      out.write("            \n");
      out.write("            </tr>");

            
            }
            
      out.write("</table>\n");
      out.write("        <input name=\"Pay_Fine\" type=\"submit\" value=\"Pay Fine\">\n");
      out.write("    </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
