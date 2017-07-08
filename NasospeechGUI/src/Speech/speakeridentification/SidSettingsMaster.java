/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.speakeridentification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tatapower SED
 *
 */
public class SidSettingsMaster {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;
    private Statement st = null;
    String query = "insert  into sidgroupdetails(typename,typevalue) values(\"Gender\",\"Male\") ON DUPLICATE KEY update typename = 'Gender',typevalue = 'Male';";

    public SidSettingsMaster(Connection connect) {
        this.connect = connect;
    }

    public boolean insertFieldValue(String fieldName, String fieldValue) {
        try {
            String query = "insert  into sidgroupdetails(typename,typevalue) values('" + fieldName + "','" + fieldValue + "') ON DUPLICATE KEY update typename = '" + fieldName + "',typevalue = '" + fieldValue + "';";
            //System.out.println(query);
            preparedStatement = connect.prepareStatement(query);
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rows > 0) {
                return true;
            }
        } catch (SQLException er) {
            Logger.getLogger(SidSettingsMaster.class.getName()).log(Level.SEVERE, null, er);
        }
        return false;
    }

    public boolean updateFieldValue(int id, String fieldValue) {
        try {
            String query = "update sidgroupdetails set typevalue = '" + fieldValue + "' where uid = " + id;
            //System.out.println(query);
            preparedStatement = connect.prepareStatement(query);
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rows > 0) {
                return true;
            }
        } catch (SQLException er) {
            Logger.getLogger(SidSettingsMaster.class.getName()).log(Level.SEVERE, null, er);
        }
        return false;
    }

    public boolean deleteFieldValue(int id) {
        try {
            String query = "delete from sidgroupdetails where uid = " + id;
            //System.out.println(query);
            preparedStatement = connect.prepareStatement(query);
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rows > 0) {
                return true;
            }
        } catch (SQLException er) {
            Logger.getLogger(SidSettingsMaster.class.getName()).log(Level.SEVERE, null, er);
        }
        return false;
    }

    public Object[][] getAllTypeNames(String name) {
        Object[][] typevaluearr = null;
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query

            String query = "select uid,typevalue from sidgroupdetails where typename = '" + name + "'";
            rs = statement.executeQuery(query);
            int rowcount = 0, arrayRow = 0;
            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
            typevaluearr = new Object[rowcount + 1][2];
            while (rs.next()) {
                if (rs.getString("typevalue") != null) {
                    if (rs.getString("typevalue").trim().length() > 0) {

                        typevaluearr[arrayRow][0] = rs.getString("typevalue").trim();
                        typevaluearr[arrayRow++][1] = rs.getInt("uid");
                    }
                }

            }
            statement.close();
            rs.close();
            //End Getting Group Name
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }


        return typevaluearr;

    }
}
