package Speech.filebrowser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Tatapower SED
 *
 */
public class FileTransferMaster {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public FileTransferMaster(Connection connect) {
        this.connect = connect;
    }

    public boolean isFileExists(String filename, String fileLocation) {
        boolean findRecord = false;

        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select * from fileinfomaster where LOWER(filename) like '" + filename.toLowerCase() + "' and LOWER(filelocation) like '" + fileLocation.toLowerCase() + "'");

            if (resultSet.next()) {
                findRecord = true;
            }
            resultSet.close();
            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(FileTransferMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
        return findRecord;
    }

    public boolean insertfileInfo(String fileName, String fileLocation, String userID) {
        try {  // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("insert into  fileinfomaster (filename,filelocation,userid,filedate) values ('" + fileName + "','" + fileLocation + "','" + userID + "',now())");
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rows == 1) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(FileTransferMaster.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public DefaultMutableTreeNode getAllServerFiles() {

        DefaultMutableTreeNode server = new DefaultMutableTreeNode("Server");
        DefaultMutableTreeNode pds = new DefaultMutableTreeNode("PDS");
        DefaultMutableTreeNode kws = new DefaultMutableTreeNode("KWS");
        DefaultMutableTreeNode kwsassames = new DefaultMutableTreeNode("Assamese");
        DefaultMutableTreeNode kwshindi = new DefaultMutableTreeNode("Hindi");
        DefaultMutableTreeNode kwstelugu = new DefaultMutableTreeNode("Telugu");
        DefaultMutableTreeNode kwsenglish = new DefaultMutableTreeNode("English");


        try {


            DefaultMutableTreeNode fileNode;
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select distinct(filename) from fileinfomaster where LOWER(filelocation) like 'pds'");

            while (resultSet.next()) {
                fileNode = new DefaultMutableTreeNode(resultSet.getString("filename"));
                pds.add(fileNode);
            }
            resultSet.close();



            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select distinct(filename) from fileinfomaster where LOWER(filelocation) like 'kws - assamese'");

            while (resultSet.next()) {
                fileNode = new DefaultMutableTreeNode(resultSet.getString("filename"));
                kwsassames.add(fileNode);
            }
            resultSet.close();



            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select distinct(filename) from fileinfomaster where LOWER(filelocation) like 'kws - hindi'");

            while (resultSet.next()) {
                fileNode = new DefaultMutableTreeNode(resultSet.getString("filename"));
                kwshindi.add(fileNode);
            }
            resultSet.close();




            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select distinct(filename) from fileinfomaster where LOWER(filelocation) like 'kws - telugu'");

            while (resultSet.next()) {
                fileNode = new DefaultMutableTreeNode(resultSet.getString("filename"));
                kwstelugu.add(fileNode);
            }
            resultSet.close();




            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("select distinct(filename) from fileinfomaster where LOWER(filelocation) like 'kws - english'");

            while (resultSet.next()) {
                fileNode = new DefaultMutableTreeNode(resultSet.getString("filename"));
                kwsenglish.add(fileNode);
            }
            resultSet.close();

            kws.add(kwshindi);
            kws.add(kwsassames);
            kws.add(kwstelugu);
            kws.add(kwsenglish);
            server.add(pds);
            server.add(kws);




            //End Getting User Roll
        } catch (SQLException ex) {
            Logger.getLogger(FileTransferMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
        return server;
    }
}
