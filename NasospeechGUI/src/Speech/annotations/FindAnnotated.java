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
public class FindAnnotated {

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private String filename = null;
    int startPos = 0, endPos = 0;
    private int annotationStartPos = 0;
    private int annotationEndPos = 0;
    private String allow = null;
    private String level = null;
    private PreparedStatement preparedStatement = null;

    public FindAnnotated(Connection conn, String filename, int startPos, int endPos) {
        this.connect = conn;
        this.filename = filename;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public boolean isAnnotatedBothPosiotion() {
        boolean find = true;
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select startpos,endpos from annotation_master where startpos > " + startPos + " and endpos < " + endPos + " and LOWER(filename) = '" + filename.toLowerCase() + "'");
            if (!resultSet.next()) {
                find = false;
            }
            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }

    public boolean isAnnotatedInPosiotion() {
        boolean find = true;
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select startpos,endpos,allow,level from annotation_master where startpos < " + startPos + " and endpos > " + endPos + " and LOWER(filename) = '" + filename.toLowerCase() + "'");
            if (resultSet.next()) {
                annotationStartPos = resultSet.getInt("startpos");
                annotationEndPos = resultSet.getInt("endpos");
                allow = resultSet.getString("allow");
                level = resultSet.getString("level");
                find = true;
            } else {
                find = false;
            }

            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }

    public boolean isAnnotatedSameStartPosiotion() {
        boolean find = true;
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select startpos,endpos,allow,level from annotation_master where startpos = " + startPos + " and LOWER(filename) = '" + filename.toLowerCase() + "'");
            if (resultSet.next()) {
                annotationStartPos = resultSet.getInt("startpos");
                annotationEndPos = resultSet.getInt("endpos");
                allow = resultSet.getString("allow");
                level = resultSet.getString("level");
                find = true;
            } else {
                find = false;
            }
            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }

    public boolean isAnnotatedSameEndPosiotion() {
        boolean find = true;
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select startpos,endpos,allow,level from annotation_master where endpos = " + endPos + " and LOWER(filename) = '" + filename.toLowerCase() + "'");
            if (resultSet.next()) {
                annotationStartPos = resultSet.getInt("startpos");
                annotationEndPos = resultSet.getInt("endpos");
                allow = resultSet.getString("allow");
                level = resultSet.getString("level");
                find = true;
            } else {
                find = false;
            }
            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }

    public boolean isAnnotatedEndPostion() {
        boolean find = true;
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            resultSet = statement.executeQuery("select startpos,endpos,allow,level from annotation_master where endpos > " + startPos + " and endpos < " + endPos + " and LOWER(filename) = '" + filename.toLowerCase() + "'");
            if (resultSet.next()) {
                annotationStartPos = resultSet.getInt("startpos");
                annotationEndPos = resultSet.getInt("endpos");
                allow = resultSet.getString("allow");
                level = resultSet.getString("level");
                find = true;
            } else {
                find = false;
            }
            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }

    public boolean isAnnotatedStartPostion() {
        boolean find = true;
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select startpos,endpos,allow,level from annotation_master where startpos > " + startPos + " and startpos < " + endPos + " and LOWER(filename) = '" + filename.toLowerCase() + "'");
            if (resultSet.next()) {
                find = true;
            } else {
                find = false;
            }
            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }

    public boolean isAnnotationAtfront() {
        boolean find = true;
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            resultSet = statement.executeQuery("select * from annotation_master where endpos < " + startPos + " and LOWER(filename) = '" + filename.toLowerCase() + "'");
            if (resultSet.next()) {
                find = true;
            } else {
                find = false;
            }
            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }

    public boolean isAnnotationAtback() {
        boolean find = true;
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            resultSet = statement.executeQuery("select * from annotation_master where startpos > " + endPos + " and LOWER(filename) = '" + filename.toLowerCase() + "'");
            if (resultSet.next()) {
                find = true;
            } else {
                find = false;
            }
            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }

    public boolean isFileAnnotation() {
        boolean find = true;
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            resultSet = statement.executeQuery("select * from annotation_master where LOWER(filename) = '" + filename.toLowerCase() + "'");
            if (resultSet.next()) {
                find = true;
            } else {
                find = false;
            }
            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }

    public boolean findAnnationAll() {
        if (isAnnotatedBothPosiotion() == true || isAnnotatedEndPostion() == true || isAnnotatedStartPostion() == true || isAnnotatedInPosiotion() == true || isAnnotatedSameStartPosiotion() == true || isAnnotatedSameEndPosiotion() == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean findResizeAnnation() {
        if (isAnnotatedEndPostion() == true || isAnnotatedStartPostion() == true || isAnnotatedInPosiotion() == true || isAnnotatedSameStartPosiotion() == true || isAnnotatedSameEndPosiotion() == true) {
            return true;
        } else {
            return false;
        }
    }

    public int findNumberOfMatch() {
        int numberOfTrue = 0;
        if (isAnnotatedEndPostion() == true) {
            numberOfTrue++;
        }
        if (isAnnotatedStartPostion() == true) {
            numberOfTrue++;
        }
        if (isAnnotatedInPosiotion() == true) {
            numberOfTrue++;
        }
        if (isAnnotatedSameStartPosiotion() == true) {
            numberOfTrue++;
        }
        if (isAnnotatedSameEndPosiotion() == true) {
            numberOfTrue++;
        }
        return numberOfTrue;
    }

    public int getAnnotationStartPos() {
        return annotationStartPos;
    }

    public int getAnnotationEndPos() {
        return annotationEndPos;
    }

    public String getAllow() {
        return allow;
    }

    public String getLevel() {
        if (level == null) {
            level = "Regular";
        }
        if ((level.trim()).length() == 0) {
            level = "Regular";
        }
        return level;
    }

    //update Annotations
    public boolean changeRemovePostionAnnotation(int endChange, int length) {
        try {
            // Creating Statement for query execution
            statement = connect.createStatement();
            // creating Query String
            String query = "UPDATE annotation_master SET startpos = startpos - " + length + ", endpos= endpos - " + length + " where LOWER(filename) = '" + filename.toLowerCase() + "' and startpos >= " + endChange + " ";
            // Updating Table

            int rows = statement.executeUpdate(query);
            statement.close();

            if (rows != 0) {
                return true;
            }
        } catch (SQLException er) {
            System.err.println(er);
        }


        return false;
    }

    public ArrayList getAnnotationID() {
        boolean find = true;
        ArrayList<Integer> ann_id = new ArrayList<Integer>();
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select annotate_id from annotation_master where startpos > " + startPos + " and endpos < " + endPos + " and LOWER(filename) = '" + filename.toLowerCase() + "'");
            while (resultSet.next()) {
                ann_id.add(resultSet.getInt("annotate_id"));
            }
            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ann_id;
    }

    public ArrayList getStartPointAnnotationID() {
        boolean find = true;
        ArrayList<Integer> ann_id = new ArrayList<Integer>();
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select annotate_id from annotation_master where startpos > " + startPos + " and LOWER(filename) = '" + filename.toLowerCase() + "'");
            while (resultSet.next()) {
                ann_id.add(resultSet.getInt("annotate_id"));
            }
            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ann_id;
    }

    public ArrayList getAnnotationIDAll() {
        boolean find = true;
        ArrayList<Integer> ann_id = new ArrayList<Integer>();
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select annotate_id from annotation_master where LOWER(filename) = '" + filename.toLowerCase() + "'");
            while (resultSet.next()) {
                ann_id.add(resultSet.getInt("annotate_id"));
            }
            statement.close();
            resultSet.close();

            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ann_id;
    }

    public boolean isAnnotationFileinServer() {
        boolean find = false;
        boolean ann_master = false;
        boolean ann_file = false;

        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            if (filename == null) {
                return false;
            }
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select * from annotation_master where LOWER(filename) = '" + filename.toLowerCase() + "'");
            if (resultSet.next()) {
                ann_master = true;
            }
            statement.close();
            resultSet.close();

            System.out.println("annotation_master " + ann_master);
            statement = connect.createStatement();

            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select * from annotation_filepostion where LOWER(filename) = '" + filename.toLowerCase() + "'");
            if (resultSet.next()) {
                ann_file = true;
            }
            statement.close();
            resultSet.close();

            System.out.println("annotation_filepostion " + ann_file);

            if (ann_master == ann_file) {
                find = true;
            }
            System.out.println("Final  " + find);
            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return find;
    }
}
