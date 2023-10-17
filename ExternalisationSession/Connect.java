package basesql;           
import java.sql.*; 

public class Connect {

    String user;
    String password;

    public Connect()
    {
        
    }
    

    public String getUser() {
        return user;
    }


    public void setUser(String user) {
        this.user = user;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Connection getConnectionPostgresql() throws ClassNotFoundException, SQLException
    {
         
            //step1 load the driver class  
            Class.forName("org.postgresql.Driver");  
              
            //step2 create  the connection object  
            Connection con=DriverManager.getConnection(  
            "jdbc:postgresql://192.168.109.130:5432/haproxy","postgres","");  
            System.out.println(con);
            return con;
    }
}
        
          
    
   
          
      

 
    

