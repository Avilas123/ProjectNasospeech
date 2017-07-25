/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.annotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tatapower SED
 *
 */
public class GetAnnotation {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public GetAnnotation(Connection connect) {
        this.connect = connect;
    }

    public String[][] getAll(String filename) {
        String[][] pos = null;
        int size = 0, index = 0;
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select startpos,endpos,level from annotation_master where LOWER(filename) = '" + filename.toLowerCase() + "'");

            resultSet.last();
            size = resultSet.getRow();
            resultSet.beforeFirst();
            pos = new String[size][3];


            while (resultSet.next()) {
                if (index < size) {
                    if (resultSet.getInt("startpos") == 0 || resultSet.getInt("endpos") == 0 || resultSet.getString("level") == null) {
                        continue;
                    }
                    if (resultSet.getString("level").trim().length() == 0) {
                        continue;
                    }
                    pos[index][0] = Integer.toString(resultSet.getInt("startpos"));
                    pos[index][1] = Integer.toString(resultSet.getInt("endpos"));
                    pos[index][2] = (resultSet.getString("level")).trim();
                    index++;
                }

            }


            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pos;
    }

    public ResultSet getSelected(String filename, int startPos, int endPos, int orderby) {
        String[][] pos = null;
        int size = 0, index = 0;
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            // System.err.println("select * from annotation_message where annotate_id in (select annotate_id from annotation_master where LOWER(filename) = '" + filename.toLowerCase() + "' and startpos = " + startPos + " and endpos = " + endPos + ") order by user_id ASC");



            if (orderby == 0) {
                resultSet = statement.executeQuery("select * from annotation_message where annotate_id in (select annotate_id from annotation_master where LOWER(filename) = '" + filename.toLowerCase() + "' and startpos = " + startPos + " and endpos = " + endPos + ") order by level ASC");
                // System.err.println("select * from annotation_message where annotate_id in (select annotate_id from annotation_master where LOWER(filename) = '" + filename.toLowerCase() + "' and startpos = " + startPos + " and endpos = " + endPos + ") order by level ASC");
            } else if (orderby == 3) {
                resultSet = statement.executeQuery("select * from annotation_message where annotate_id in (select annotate_id from annotation_master where LOWER(filename) = '" + filename.toLowerCase() + "' and startpos = " + startPos + " and endpos = " + endPos + ") order by user_id ASC");
                // System.err.println("select * from annotation_message where annotate_id in (select annotate_id from annotation_master where LOWER(filename) = '" + filename.toLowerCase() + "' and startpos = " + startPos + " and endpos = " + endPos + ") order by user_id ASC");
            } else if (orderby == 1) {
                resultSet = statement.executeQuery("select * from authentication a, annotation_message b where b.annotate_id in (select annotate_id from annotation_master where LOWER(filename) = '" + filename.toLowerCase() + "' and startpos = " + startPos + " and endpos = " + endPos + ") and a.user_id = b.user_id order by a.roll DESC");
            } else {
                resultSet = statement.executeQuery("select * from annotation_message where annotate_id in (select annotate_id from annotation_master where LOWER(filename) = '" + filename.toLowerCase() + "' and startpos = " + startPos + " and endpos = " + endPos + ") order by ann_date ASC");
            }

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
    }

    public int[] getUniqueSelect(int ms, String filename) {
        int[] result = null;
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            result = new int[2];
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select startpos,endpos from annotation_master where startpos < " + ms + " and endpos > " + ms + " and LOWER(filename) = '" + filename.toLowerCase() + "'");
            if (resultSet.next()) {

                if (resultSet.getInt("startpos") != 0 && resultSet.getInt("endpos") != 0) {
                    result[0] = resultSet.getInt("startpos");
                    result[1] = resultSet.getInt("endpos");
                }
            }


            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList getAllTimes(String filename) {
        ArrayList pos = null;
        int size = 0, index = 0;
        try {
            pos = new ArrayList();
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select startpos,endpos from annotation_master where LOWER(filename) = '" + filename.toLowerCase() + "'");
            while (resultSet.next()) {
                pos.add(resultSet.getInt("startpos") + " " + resultSet.getInt("endpos"));
            }
            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pos;
    }

    public String getUserLevel(String userID) {
        String pos = null;
        int size = 0, index = 0;
        try {

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select roll from authentication where LOWER(user_id) = '" + userID.toLowerCase() + "'");
            if (resultSet.next()) {
                pos = resultSet.getString("roll");
            }
            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pos;
    }

    public static void main(String args[]) {
        try {
            // int a = 2000000000;
         //   Speech.sqlconnection.MysqlConnect tr = new Speech.sqlconnection.MysqlConnect();
            //Connection conn = tr.getDBConnection();
          //  new GetAnnotation(conn).getAll("test.wav1");
        } catch (Exception ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
