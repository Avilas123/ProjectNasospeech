/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.kws;

import Speech.annotations.InsertAnnotation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Tatapower SED
 *
 */
public class CreateKeywordModel {

    private Connection connect;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Vector groupName = null;
    private Vector groupTree = null;
    private String KWList;
    private String query;

    public Vector getGroupTree() {
        return groupTree;
    }

    public CreateKeywordModel(Connection connect) {
        this.connect = connect;
    }

    public DefaultMutableTreeNode getKeywordModel(String language, String list) {//this function has been edited ,parameter list newly added on 20/5/14 
        DefaultMutableTreeNode keyModel = null;
        KWList=list;  
        
        try {
            groupTree = new Vector();

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            
            groupName = new Vector();
           
            if(list.equals("all")){        
                query = "select DISTINCT(groupname) from keyword_master where LOWER(language) = '" + language.toLowerCase() + "'";
            }
            
            else{                
                query = "select DISTINCT(groupname) from keyword_master where LOWER(language) = '" + language.toLowerCase() + "' and listname ='"+list+"'";                
            }
            
            resultSet = statement.executeQuery(query);
            keyModel = new DefaultMutableTreeNode("List of Keywords", true);
            while (resultSet.next()) {
                if (resultSet.getString("groupname") != null) {
                    if (resultSet.getString("groupname").trim().length() > 0) {
                        groupName.add(resultSet.getString("groupname").trim());
                    }
                }

            }
            statement.close();
            resultSet.close();
            //End Getting Group Name

            for (int gN = 0; gN < groupName.size(); gN++) {
                //Getting all keywords    
                if (groupName.get(gN) == null) {
                    continue;
                }
                statement = connect.createStatement();
                // Result set get the result of the SQL 
                //this code edited on 21/5/14 and added the list parameter to select keywords from each list per priority
                if(list.equals("all")){
                   query = "select keyword from keyword_master where LOWER(language) = '" + language.toLowerCase() + "' and TRIM(groupname) = '" + groupName.get(gN) + "'";
                }
                
                else{
                   query = "select keyword from keyword_master where LOWER(language) = '" + language.toLowerCase() + "' and TRIM(listname) = '" + list + "' and TRIM(groupname) = '" + groupName.get(gN) + "'";                    
                }
                
                resultSet = statement.executeQuery(query);

                resultSet.last();
                int size = resultSet.getRow();
                resultSet.beforeFirst();
                Vector treeNodes = new Vector();
                treeNodes.add(groupName.get(gN) + " (" + size + ") ");
                DefaultMutableTreeNode parent = new DefaultMutableTreeNode(groupName.get(gN) + " (" + size + ") ");
                while (resultSet.next()) {
                    if (resultSet.getString("keyword") != null) {
                        if (resultSet.getString("keyword").trim().length() > 0) {
                            DefaultMutableTreeNode child = new DefaultMutableTreeNode(resultSet.getString("keyword").trim());
                            parent.add(child);
                            treeNodes.add(resultSet.getString("keyword").trim());
                        }
                    }

                }
                statement.close();
                resultSet.close();
                //End Getting Keyword
                groupTree.add(treeNodes);
                keyModel.add(parent);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CreateKeywordModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception er) {
            Logger.getLogger(CreateKeywordModel.class.getName()).log(Level.SEVERE, null, er);
        }

        return keyModel;
    }

    public boolean updateKeywordList(String language, String keyword, String group) {
        try {
            // Creating Statement for query execution
            statement = connect.createStatement();
            // creating Query String
            String query = "UPDATE keyword_master SET groupname = '" + group + "' where LOWER(language) = '" + language.toLowerCase() + "' and keyword = '" + keyword + "' ";
            // Updating Table
            //System.out.println(query);
            int rows = statement.executeUpdate(query);
            statement.close();
            if (rows > 0) {
                return true;
            }

        } catch (Exception er) {
        }
        return false;
    }

    public Vector getGroupName() {
       return groupName;
    } 

    public boolean addNewKeyword(String language, String keyword, String list, String group) {

        //if value is not insert
        try {

            //Check the keyword alread exist or not
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            String query = "select keyword from keyword_master where LOWER(language) = '" + language.toLowerCase() + "' and LOWER(keyword) = '" + keyword.toLowerCase() + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Keyword already available");
                return false;
            }


            query = "insert into  keyword_master (language,keyword,listname, groupname) values ('" + language + "','" + keyword.trim() + "','" + list + "','" + group + "')";
            preparedStatement = connect.prepareStatement(query);
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rows == 1) {
                return true;
            }
            //End Insert
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public int getPriority(String keyword, String language) {

        int priorityVal = 10;

        try {

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            groupName = new Vector();
            String query = "select DISTINCT(groupname) from keyword_master where LOWER(language) = '" + language.toLowerCase() + "' and LOWER(keyword) = '" + keyword.toLowerCase() + "'";
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                if (resultSet.getString("groupname") != null) {
                    String gpName = resultSet.getString("groupname").trim();
                    if (gpName.length() > 0) {

                        StringTokenizer st = new StringTokenizer(gpName, " ");
                        if (st.hasMoreElements()) {
                            st.nextElement();
                            if (st.hasMoreElements()) {
                                try {
                                    priorityVal = Integer.parseInt(st.nextElement().toString());
                                    System.err.println("Number is " + priorityVal);
                                } catch (NumberFormatException er) {
                                    System.err.println(er);
                                }
                            }
                        }
                    }
                }

            }
            statement.close();
            resultSet.close();
            //End Insert
        } catch (SQLException ex) {
            Logger.getLogger(InsertAnnotation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return priorityVal;
    }
}
