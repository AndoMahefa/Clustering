package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.*; 
import javax.servlet.http.*;
import session.SessionManager;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ShowName")
public class UseSessionServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    
        try{
            PrintWriter out = res.getWriter();
            /*maka anle session_id ao @ cookies */
            Cookie[] cookies = req.getCookies();
            Cookie cookie = null;
            int i = 0;
            for(i=0;i<cookies.length;i++){
                if(cookies[i].getName().equals("session_id")){
                    out.println("misy");
                    cookie = cookies[i];
                    break;
                }
            }
            /*maka anle session_id ao @ cookies */

            out.println(cookie.getValue());
            /*maka ny session  */
            HttpSession session = req.getSession();
            session = SessionManager.retrieveSession(session, cookie.getValue(),out);
            /*maka ny session */

            
            out.println(session.getAttribute("name"));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
