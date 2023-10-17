package session;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import basesql.Connect;

public class SessionManager {

    public static void insertSession(String sessionId, ArrayList<String> key ,ArrayList<String> value,PrintWriter out) throws SQLException , ClassNotFoundException{
        try {
            Connect c = new Connect();
            Connection connection = c.getConnectionPostgresql();
            String insertQuery = "";
            Statement stmt =  null;
            int i = 0;

            for(i=0;i<key.size();i++){
                insertQuery = "INSERT INTO sessions (idSession,key,value) VALUES ("+"'"+sessionId+"',"+"'"+key.get(i)+"',"+"'"+value.get(i)+"')";
                out.println(insertQuery);
                stmt = connection.createStatement();
                stmt.execute(insertQuery);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static HttpSession retrieveSession(HttpSession httpsession, String sessionId,PrintWriter out) throws SQLException , ClassNotFoundException{

        try {
            Connect c = new Connect();
            Connection connection = c.getConnectionPostgresql();
            String query = "select * from sessions where idSession="+"'"+sessionId+"'";
            out.println(query);
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            
            while(res.next()){
                httpsession.setAttribute(res.getString(2),res.getString(3));
            }

            res.close();
            connection.close();
            return httpsession;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void storeSession(HttpSession httpsession,PrintWriter out) throws SQLException , ClassNotFoundException{

            Enumeration keys_enum = httpsession.getAttributeNames();
            ArrayList<String> keys = new ArrayList<String>();
            while (keys_enum.hasMoreElements()){
                keys.add((String) keys_enum.nextElement());
                out.println(keys.get(keys.size()-1));
            }
            ArrayList<String> values = new ArrayList<String>();
            int i = 0;
            for(i=0;i<keys.size();i++){
                values.add((String) httpsession.getAttribute(keys.get(i)));
            }
            SessionManager.insertSession(httpsession.getId(), keys, values,out);
    }  
}
