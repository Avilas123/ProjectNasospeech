

 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlitejdbcconnection;
import java.sql.*;
import javax.swing.JOptionPane;
        /*
/**
 *
 * @author IITG
 */
public class SqliteJDBCconnection {

     /**
     * Connect to a sample database
     */
    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:\\sqlite\\Nasospeech.sqlite";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            //System.out.println("Connection to the Database has been established.");
            //JOptionPane.showMessageDialog(null,"Connection Successfull");
            return conn;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } 
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        connect();
    }
}
