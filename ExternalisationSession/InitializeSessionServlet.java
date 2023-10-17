package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.*; 
import javax.servlet.http.*;
import session.SessionManager;
import javax.servlet.annotation.WebServlet;

@WebServlet("/InsertSession")
public class InitializeSessionServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    
        try{
            //Raha mistocker session de stockena anaty cookie le session_id raha tsy mbola miexiste le cookie
            //Raha ampiasa anle session efa nisy le serveur/servlet vo maka cookie ijerena anle session_id
            //eto zao tsy mampiasa

            /*jerena aloha raha efa misy cookie anle session_id */
            String idSession = req.getSession().getId();
            PrintWriter out = res.getWriter();
            out.println(idSession);
            Cookie cookie = new Cookie("session_id",idSession);
            Cookie[] cookies =  req.getCookies();

            int i = 0;
            int target = 0;
            for(i=0;i<cookies.length;i++){
                if(cookies[i].getName().equals("session_id")){
                    target = 1;
                    out.println("efa misy");
                    break;
                }
            }
            //raha tsisy de mcreer cookie misy anle session_id
            if(target == 0){
                out.println("mbola tsisy");
                HttpSession session =  req.getSession();
                session.setAttribute("name","Riana");
                SessionManager.storeSession(session,out);
            }
            /*jerena aloha raha efa misy cookie anle session_id */
            res.addCookie(cookie);

            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
