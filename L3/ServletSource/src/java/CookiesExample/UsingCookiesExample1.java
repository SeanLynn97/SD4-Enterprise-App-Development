package CookiesExample;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UsingCookiesExample1 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String usersName = request.getParameter( "NameField" );
            Cookie c = new Cookie(  "theUsersName", usersName );
            c.setMaxAge( 120 );  // seconds until cookie removed
            response.addCookie( c );  // must precede getWriter

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Using Sessions</title>");
            out.println("</head>");

            out.println("<body>");
            out.println("Hello " + c.getValue());
            out.println("<br>");
            out.println("Click <a href=UsingCookiesExample2>here</a> for another page" );
            out.println("</body>");
            out.println("</html>");

        } finally {
            out.close();
        }
    }

}
