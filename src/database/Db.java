
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Db {
    
    private static Connection conn=null;
    
    private Db(){}
    
    public static Connection get_connection() throws SQLException{
        
        if(conn==null){
         conn=DriverManager.getConnection("jdbc:mysql://localhost/fast_truck","root","sasa");
        } 
        return conn;
    }
    
}