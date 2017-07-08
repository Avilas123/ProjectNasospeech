/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.sqlconnection;

/**
 *
 * @author SpeecHWareNet
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MysqlConnect {

    private static Connection conn = null;
    private static String driver = "com.mysql.jdbc.Driver";

    public static synchronized Connection getDBConnection() {


        try {
            if (conn == null) {
                ConnectionProperties connPro = new ConnectionProperties();
                if (!connPro.getDbProperties()) {
                    System.err.print("Database configuration fail");
                    javax.swing.JOptionPane.showMessageDialog(null, "Database configuration fail", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
                Class.forName(driver).newInstance();
                /*?characterEncoding=utf8 added by atin on 1-1-2015 @ 5"05 to support hindi characters in mysql*/
                conn = DriverManager.getConnection("jdbc:mysql://" + connPro.getHostName() + "/" + connPro.getDbName()+"?characterEncoding=utf8", connPro.getUserName(), connPro.getPassword());
            }
        } catch (ClassNotFoundException e) {
            System.err.println(e.toString());
            javax.swing.JOptionPane.showMessageDialog(null, "Error : " + e, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            conn = null;
        } catch (Exception e) {
            System.err.println(e.toString());
            javax.swing.JOptionPane.showMessageDialog(null, "Error : " + e, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            conn = null;
        }
        if (conn == null) {
            System.err.print("Database configuration fail");
            //System.exit(0);
        }

        return conn;
    }

    public static Connection getTestDBConnection() {


        try {

            ConnectionProperties connPro = new ConnectionProperties();
            if (!connPro.getDbProperties()) {
                System.err.print("Database configuration fail");
                javax.swing.JOptionPane.showMessageDialog(null, "Database configuration fail", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);

            }
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://" + connPro.getHostName() + "/" + connPro.getDbName(), connPro.getUserName(), connPro.getPassword());

        } catch (ClassNotFoundException e) {
            System.err.println(e.toString());
            javax.swing.JOptionPane.showMessageDialog(null, "Error : " + e, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            conn = null;
        } catch (Exception e) {
            System.err.println(e.toString());
            javax.swing.JOptionPane.showMessageDialog(null, "Error : " + e, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            conn = null;
        }


        return conn;
    }

    public static void main(String args[]) {
        try {
            new MysqlConnect().getDBConnection();
        } catch (Exception ex) {
            Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
