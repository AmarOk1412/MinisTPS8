package net.tuto2.ws.webclient;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.ws.WebServiceRef;
import javax.servlet.annotation.WebServlet;

@WebServlet(name="HelloServlet", urlPatterns={"/WSConcatServlet"})
public class WSConcatServlet extends HttpServlet {

  @WebServiceRef(wsdlLocation="http://localhost:8080/services/concat?wsdl")
  static MyConcatAgent service;

  /**
  * Handles the HTTP <code>GET</code> method.
  * @param request servlet request
  * @param response servlet response
  * @throws ServletException if a servlet-specific error occurs
  * @throws IOException if an I/O error occurs
  */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse
  response)
  throws ServletException, IOException {
    processRequest(request, response);
  }
  /**
  * Handles the HTTP <code>POST</code> method.
  * @param request servlet request
  * @param response servlet response
  * @throws ServletException if a servlet-specific error occurs
  * @throws IOException if an I/O error occurs
  */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse
  response)
  throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
  * Processes requests for both HTTP <code>GET</code>
  * and <code>POST</code> methods.
  * @param request servlet request
  * @param response servlet response
  * @throws ServletException if a servlet-specific error occurs
  * @throws IOException if an I/O error occurs
  */
  protected void processRequest(HttpServletRequest request,
  HttpServletResponse response)
  throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html lang=\"en\">");
    out.println("<head>");
    out.println("<title>Servlet HelloServlet</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Servlet HelloServlet at " +
    request.getContextPath () + "</h1>");
    out.println("<p>" + concat("hello ","david") + "</p>");
    out.println("</body>");
    out.println("</html>");
  }

  private String concat(String arg0, String arg1) {
    service = new MyConcatAgent();
    WSConcat port = service.getWSConcatImplPort();
    return port.concat(arg0,arg1);
  }
}
