/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.sqlconnection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionProperties {

    private String dbName;
    private String userName;
    private String password;
    private String hostName;

    public String getDbName() {
        return dbName;
    }

    private void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getHostName() {
        return hostName;
    }

    private void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public boolean getDbProperties() {
        Properties prop = new Properties();
        String db_name = null, db_host = null, db_user = null, db_pws = null;
        try {
            //load a properties file
            FileInputStream fStream = new FileInputStream("conf/db/dbconfig.properties");
            prop.load(fStream);
            if ((prop.getProperty("host") == null) || (prop.getProperty("database") == null) || (prop.getProperty("dbuser") == null) || (prop.getProperty("dbpassword") == null)) {
                return false;
            }
            db_host = prop.getProperty("host");
            db_name = prop.getProperty("database");
            db_user = prop.getProperty("dbuser");
            db_pws = prop.getProperty("dbpassword");

            if ((db_host.length() < 1) || (db_name.length() < 1) || (db_user.length() < 1) || (db_pws.length() < 1)) {
                return false;
            }

            setHostName(db_host);
            setDbName(db_name);
            setUserName(db_user);
            setPassword(db_pws);
            fStream.close();

        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return true;
    }

    public static void main(String args[]) {

        new ConnectionProperties().getDbProperties();
    }
}
