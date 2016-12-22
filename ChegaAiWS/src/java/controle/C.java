package controle;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class C {
    private static final String URL = "jdbc:mysql://localhost/chegaAiDB";   
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String SENHA = "gabriel";
    
    private static Connection con;
    
    public static Connection cb() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        con = (Connection) DriverManager.getConnection(URL, USER, SENHA);
        return con;
    }
    
    public static void db(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(C.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
