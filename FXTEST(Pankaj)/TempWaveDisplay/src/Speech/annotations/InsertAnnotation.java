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
public class InsertAnnotation {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public InsertAnnotation(Connection conn) {
        this.connect = conn;
    }

    public boolean insertNewAnnotate(int startpos, int endpos, String user, String fileName, String message, String level) {
        try {
            String userRoll;
            message = message.replaceAll("'", "");
            message = message.replaceAll("\"", "");
            fileName = fileName.replaceAll("'", "");
            fileName = fileName.replaceAll("\"", "");
            //Get User Roll

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select roll from authentication where LOWER(user_id) = '" + user.toLowerCase() + "'");
            if (resultSet.next()) {
                userRoll = resultSet.getString("roll");
            } else {
                userRoll = "0";
            }
            if (userRoll == null) {
                userRoll = "0";
            }


            statement.close();
            resultSet.close();

            //End Getting User Roll

            //Insert into annotation_master table


            //Get check already avail or not 
            boolean find = false;
            int annotationID = 0;
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select annotate_id from annotation_master where LOWER(filename) = '" + fileName.toLowerCase() + "' and startpos = " + startpos + "");
            if (resultSet.next()) {
                find = true;
                annotationID = resultSet.getInt("annotate_id");
            } else {
                find = false;
            }
            statement.close();
            resultSet.close();
            //End Check


            if (!find) {

                //if value is not insert

                statement = connect.createStatement();
                statement.executeUpdate("insert into  annotation_master (filename,startpos,endpos,allow,level) values ('" + fileName + "'," + startpos + "," + endpos + ",'" + userRoll + "','" + level + "')");
                ResultSet rs = statement.getGeneratedKeys();

                if (rs.next()) {
                    if (rs.getInt(1) == 0) {
                        return false;
                    }
                    annotationID = rs.getInt(1);

                } else {
                    return false;
                }
                statement.close();
                rs.close();
                //End Insert
            } else {
                //if value is already avail update

                // Creating Statement for query execution
                statement = connect.createStatement();
                // creating Query String
                String query = "UPDATE annotation_master SET allow = '" + userRoll + "', level='" + level + "' where LOWER(filename) = '" + fileName.toLowerCase() + "' and startpos = " + startpos + " ";
                // Updating Table
                int rows = statement.executeUpdate(query);
                statement.close();
                if (rows == 0) {
                    return false;
                }


            }

            //insert into annotation_message table

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("insert into  annotation_message (annotate_id,user_id,message,level,ann_date) values ('" + annotationID + "','" + user + "','" + message + "','" + level + "',now())");
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rows == 1) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    //Inset annotations
    public boolean insertCopySAnnotation(String filename, int start_selection_point, int previous_file_length, ArrayList ann_id) {
        int annotationID, startPos = 0, endPos = 0;

        try {
            for (int i = 0; i < ann_id.size(); i++) {
                try {
                    statement = connect.createStatement();
                    // Result set get the result of the SQL query
                    resultSet = statement.executeQuery("select startpos, endpos from annotation_master where annotate_id = " + ann_id.get(i) + "");
                    if (resultSet.next()) {
                        startPos = resultSet.getInt("startpos");
                        endPos = resultSet.getInt("endpos");
                        startPos = previous_file_length + (startPos - start_selection_point);
                        endPos = previous_file_length + (endPos - start_selection_point);
                    }
                    statement.close();
                    resultSet.close();



                    statement = connect.createStatement();
                    String query = "INSERT into annotation_master(filename,startpos,endpos,allow,level) (select '" + filename + "'," + startPos + "," + endPos + ",allow,level from annotation_master where annotate_id = " + ann_id.get(i) + ")";
                    // System.out.println(query);
                    statement.executeUpdate(query);
                    ResultSet rs = statement.getGeneratedKeys();

                    if (rs.next()) {

                        annotationID = rs.getInt(1);

                    } else {

                        continue;
                    }
                    statement.close();
                    rs.close();
                    //End Insert

                    query = "INSERT into annotation_message(annotate_id,user_id,message,level,ann_date) (select " + annotationID + ",user_id,message,level,ann_date from annotation_message where annotate_id in (select annotate_id from annotation_master where annotate_id = " + ann_id.get(i) + "))";
                    //System.out.println(query);
                    preparedStatement = connect.prepareStatement(query);
                    int rows = preparedStatement.executeUpdate();
                    preparedStatement.close();

                } catch (SQLException er) {
                    Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, er.toString());

                } catch (Exception ex) {
                    Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;

    }

    //Inset annotations
    public boolean insertSaveASAnnotation(String newFileName, String filename) {
        try {

            ArrayList<Integer> ann_id = new ArrayList<Integer>();
            try {
                statement = connect.createStatement();
                // Result set get the result of the SQL query
                resultSet = statement.executeQuery("select annotate_id from annotation_master where LOWER(filename) = '" + filename.toLowerCase() + "'");
                while (resultSet.next()) {
                    ann_id.add(resultSet.getInt("annotate_id"));
                }

            } catch (SQLException er) {
                Logger.getLogger(FindAnnotated.class.getName()).log(Level.SEVERE, er.toString());

            } finally {
                try {
                    statement.close();
                    resultSet.close();
                } catch (Exception er) {
                }
            }

            for (int i = 0; i < ann_id.size(); i++) {
                try {
                    int annotationID;

                    statement = connect.createStatement();
                    String query = "INSERT into annotation_master(filename,startpos,endpos,allow,level) (select '" + newFileName + "',startpos,endpos,allow,level from annotation_master where annotate_id = " + ann_id.get(i) + ")";
                    // System.out.println(query);
                    statement.executeUpdate(query);
                    ResultSet rs = statement.getGeneratedKeys();

                    if (rs.next()) {

                        annotationID = rs.getInt(1);

                    } else {

                        continue;
                    }
                    statement.close();
                    rs.close();
                    //End Insert

                    query = "INSERT into annotation_message(annotate_id,user_id,message,level,ann_date) (select " + annotationID + ",user_id,message,level,ann_date from annotation_message where annotate_id in (select annotate_id from annotation_master where annotate_id = " + ann_id.get(i) + "))";
                    //System.out.println(query);
                    preparedStatement = connect.prepareStatement(query);
                    int rows = preparedStatement.executeUpdate();
                    preparedStatement.close();

                } catch (SQLException ex) {

                    Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
                }

            }


        } catch (Exception ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;

    }

    public boolean insertAnnotaionFilePostion(String fileName, String postion) {
        try {
            String query = "INSERT into annotation_filepostion (filename,file_pos) values('" + fileName + "','" + postion + "')  ON DUPLICATE KEY UPDATE filename = '" + fileName + "'";
            //System.out.println(query);
            preparedStatement = connect.prepareStatement(query);
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rows > 0) {
                return true;
            }
        } catch (SQLException er) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, er);
        }
        return false;
    }

    public static void main(String args[]) {
        try {
            // int a = 2000000000;
         //   Speech.sqlconnection.MysqlConnect tr = new Speech.sqlconnection.MysqlConnect();
           // Connection conn = tr.getDBConnection();
            //new InsertAnnotation(conn).insertNewAnnotate(234567891, 234567891, "senthil", "test.wav1", "They are moving", "danger");
        } catch (Exception ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
