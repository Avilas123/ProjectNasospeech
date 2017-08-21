/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.annotations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tatapower SED
 *
 */
public class UpdateAnnotation {

    private Connection connect;
    private Statement statement = null;

    public UpdateAnnotation(Connection connect) {
        this.connect = connect;
    }

    public boolean resizeUpdate(int actual_StPos, int actual_EnPos, int change_StPos, int change_EnPos, String filename, String userID, String level) {

        try {
            // Creating Statement for query execution
            statement = connect.createStatement();
            // creating Query String
            String query = "UPDATE annotation_master SET startpos = " + change_StPos + ", endpos= " + change_EnPos + " where LOWER(filename) = '" + filename.toLowerCase() + "' and startpos = " + actual_StPos + " ";
            // Updating Table
            int rows = statement.executeUpdate(query);
            statement.close();
            if (rows == 0) {
                return false;
            } else {
                String message = "He changed position from " + actual_StPos / 1000 + ", " + actual_EnPos / 1000 + " to " + change_StPos / 1000 + ", " + change_EnPos / 1000;
             //   new InsertAnnotation(connect).insertNewAnnotate(change_StPos, change_EnPos, userID, filename, message, level);
            }
        } catch (SQLException er) {
            System.err.println(er);
        }


        return true;
    }

    public boolean updateAnnotation(int annotationid, String message, String originalMessage, String userID, String level) {

        try {
            // Creating Statement for query execution
            statement = connect.createStatement();
            // creating Query String
            message = message + "  (Your message Update by " + userID + ")";
            String query = "UPDATE annotation_message SET message = '" + message + "', level = '" + level + "' where annotate_message_id = " + annotationid;
            System.out.println(query);
            // Updating Table
            int rows = statement.executeUpdate(query);
            statement.close();
            if (rows == 0) {
                return false;
            }
        } catch (SQLException er) {
            System.err.println(er);
        }

        return true;
    }

    public boolean deleteAnnotation(int message_id) {

        try {
            // Creating Statement for query execution
            statement = connect.createStatement();
            // creating Query String
            String query = "delete from annotation_message where annotate_message_id = " + message_id;
            // Updating Table
            int rows = statement.executeUpdate(query);
            statement.close();
            if (rows == 0) {
                return false;
            }
        } catch (SQLException er) {
            System.err.println(er);
        }


        return true;
    }

    public boolean deleteTempFileAnnotation() {

        try {
            // Creating Statement for query execution
            statement = connect.createStatement();
            // creating Query String
            String query = "delete from annotation_master where filename NOT IN (select filename from annotation_filepostion)";            // Updating Table
            int rows = statement.executeUpdate(query);
            statement.close();
            if (rows == 0) {
                return false;
            }
        } catch (SQLException er) {
            System.err.println(er);
            System.exit(0);
        }


        return true;
    }

    public boolean deleteServerFileAnnotation(String fileName) {

        try {
            // Creating Statement for query execution
            statement = connect.createStatement();
            // creating Query String
            String query = "delete from annotation_master where filename = '" + fileName + "'";            // Updating Table
            int rows = statement.executeUpdate(query);
            statement.close();

            statement = connect.createStatement();
            query = "delete from annotation_filepostion where filename = '" + fileName + "'";            // Updating Table
            rows = statement.executeUpdate(query);
            statement.close();
            if (rows > 0) {
                return true;
            }
        } catch (SQLException er) {
            System.err.println(er);
        }


        return false;
    }

    public boolean deleteALLAnnotation(String fileName) {

        try {
            // Creating Statement for query execution
            statement = connect.createStatement();
            // creating Query String
            String query = "delete from annotation_master where filename = '" + fileName + "'";
            // Updating Table
            int rows = statement.executeUpdate(query);
            statement.close();
            if (rows == 0) {
                return false;
            }
        } catch (SQLException er) {
            System.err.println(er);
        }


        return true;
    }

    public boolean updateAnnotationFileName(String oldfileName, String newfileName) {

        try {
            // Creating Statement for query execution
            statement = connect.createStatement();
            // creating Query String

            String query = "UPDATE annotation_master SET filename = '" + newfileName + "' where filename = '" + oldfileName + "'";
            //   System.out.println(query);
            // Updating Table
            int rows = statement.executeUpdate(query);
            statement.close();
            if (rows == 0) {
                return false;
            }
        } catch (SQLException er) {
            System.err.println(er);
        }

        return true;
    }
}
