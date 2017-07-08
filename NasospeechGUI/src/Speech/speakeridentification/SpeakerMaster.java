/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.speakeridentification;

import Speech.kws.CreateKeywordModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Tatapower SED
 *
 */
public class SpeakerMaster {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private ResultSet rs = null;
    private Statement st = null;

    public SpeakerMaster(Connection connect) {
        this.connect = connect;
    }

    public String getWaveFile(String userName, String groupName) {
        String result = null;
        try {
            statement = connect.createStatement();
            String query = "select wavefile from sidmaster where TRIM(speakername) = '" + userName.trim() + "' and TRIM(groupname) = '" + groupName.trim() + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                result = resultSet.getString("wavefile");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateKeywordModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception er) {
            Logger.getLogger(CreateKeywordModel.class.getName()).log(Level.SEVERE, null, er);
        }
        return result;
    }

    public List getSpeakerInformation(String fileName, String groupName) {
        fileName = fileName.replace(".x", "");
        System.out.println(fileName);
        if (fileName.startsWith("train_")) {
            fileName = fileName.replace("train_", "test_");
        } else {
            if (fileName.indexOf("_") > 0) {
                fileName = fileName.substring(0, fileName.indexOf("_"));
            }
            if (fileName.indexOf("-") > 0) {
                fileName = fileName.substring(0, fileName.indexOf("-"));
            }
        }

        List resutlList = new ArrayList();
        try {
            statement = connect.createStatement();
            String query = "select speakername,gender from sidmaster where TRIM(wavefile) like '" + fileName.trim() + "%' and TRIM(groupname) = '" + groupName.trim() + "'";

            System.out.println(query);
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                if ((resultSet.getString("speakername") != null)) {
                    resutlList.add(resultSet.getString("speakername"));
                    if ((resultSet.getString("gender") != null)) {
                        resutlList.add(resultSet.getString("gender"));
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CreateKeywordModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception er) {
            Logger.getLogger(CreateKeywordModel.class.getName()).log(Level.SEVERE, null, er);
        }
        return resutlList;
    }

    public boolean trainNewSID(HashMap mapedValue) {
        try {


            String fieldValue = "";
            String fieldName = "";
            for (Object f : mapedValue.keySet()) {

                if (fieldName.length() == 0) {
                    fieldName = f.toString();
                } else {
                    fieldName = fieldName + "," + f;
                }
                String fvalue = mapedValue.get(f).toString();
                if (fieldValue.length() == 0) {
                    fieldValue = "'" + fvalue + "'";
                } else {
                    fieldValue = fieldValue + "," + "'" + fvalue + "'";
                }
            }
            fieldName = "insert into sidmaster(" + fieldName + ") values (";
            fieldValue = fieldValue + ")";
            String query = fieldName + fieldValue;
            preparedStatement = connect.prepareStatement(query);
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();

            if (rows == 1) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SpeakerMaster.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception er) {
            Logger.getLogger(SpeakerMaster.class.getName()).log(Level.SEVERE, null, er);
        }

        return false;
    }

    public boolean updateSIDfile(HashMap mapedValue, String userDir, String filename) {
        try {



            String fieldName = "";
            for (Object f : mapedValue.keySet()) {

                if (fieldName.length() == 0) {
                    fieldName = f.toString() + " = '" + mapedValue.get(f).toString() + "'";
                } else {
                    fieldName = fieldName + ", " + f.toString() + " = '" + mapedValue.get(f).toString() + "'";
                }

            }
            String fieldquery = "update sidmaster set ";
            String query = fieldquery + fieldName + " where filename = '" + userDir + "' and wavefile = '" + filename + "'";

            System.out.println("Query is " + query);
            preparedStatement = connect.prepareStatement(query);
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();

            if (rows == 1) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SpeakerMaster.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception er) {
            Logger.getLogger(SpeakerMaster.class.getName()).log(Level.SEVERE, null, er);
        }

        return false;
    }

    public boolean updateSpeakerGroup(String speaker, String group, String previous) {
        try {
            // Creating Statement for query execution
            statement = connect.createStatement();
            // creating Query String
            String query = "UPDATE sidmaster SET groupname = '" + group + "' where groupname ='" + previous + "' and speakername = '" + speaker + "'";
            // Updating Table
            //System.out.println(query);
            int rows = statement.executeUpdate(query);
            statement.close();
            if (rows == 1) {
                return true;
            }

        } catch (Exception er) {
        }
        return false;
    }

    public ArrayList getAllTypeNames(String name) {
        ArrayList groupName = new ArrayList();
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query

            String query = "select DISTINCT(" + name + ") from sidmaster";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if (resultSet.getString(name) != null) {
                    if (resultSet.getString(name).trim().length() > 0) {

                        groupName.add(resultSet.getString(name).trim());
                    }
                }

            }
            statement.close();
            resultSet.close();
            //End Getting Group Name
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return groupName;

    }

    public String getTypeName(String filedname, String whereAs, String fileName) {
        String typeValue = null;
        try {
            if (fileName == null) {
                return null;
            }
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query

            String query = "select DISTINCT(" + filedname + ") from sidmaster where TRIM(LOWER(" + whereAs + ")) = '" + (fileName.toLowerCase()).trim() + "'";
            //  System.out.println(query);
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                if (resultSet.getString(filedname) != null) {
                    if (resultSet.getString(filedname).trim().length() > 0) {
                        typeValue = resultSet.getString(filedname).trim();

                        // System.err.println(typeValue);

                    }
                }

            }
            statement.close();
            resultSet.close();
            //End Getting Group Name
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }



        return typeValue;

    }

    public ArrayList getTypeNameAll(String filedname, String whereAs, String fileName) {
        ArrayList typeValue = new ArrayList();
        try {
            if (fileName == null) {
                return null;
            }
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query

            String query = "select DISTINCT(" + filedname + ") from sidmaster where TRIM(LOWER(" + whereAs + ")) = '" + (fileName.toLowerCase()).trim() + "'";
            //  System.out.println(query);
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if (resultSet.getString(filedname) != null) {
                    if (resultSet.getString(filedname).trim().length() > 0) {
                        typeValue.add(resultSet.getString(filedname).trim());

                        // System.err.println(typeValue);

                    }
                }

            }
            statement.close();
            resultSet.close();
            //End Getting Group Name
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }



        return typeValue;

    }

    public String getTypeName(String filedname, String whereAs, String userDir, String fileName) {
        String typeValue = null;
        try {
            if (fileName == null) {
                return null;
            }
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query

            String query = "select DISTINCT(" + filedname + ") from sidmaster where TRIM(LOWER(" + whereAs + ")) = '" + (userDir.toLowerCase()).trim() + "' and TRIM(LOWER(wavefile)) = '" + (fileName.toLowerCase()).trim() + "'";
            //  System.out.println(query);
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                if (resultSet.getString(filedname) != null) {
                    if (resultSet.getString(filedname).trim().length() > 0) {
                        typeValue = resultSet.getString(filedname).trim();

                        // System.err.println(typeValue);

                    }
                }

            }
            statement.close();
            resultSet.close();
            //End Getting Group Name
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }


        return typeValue;

    }

    public String[][] getAllFields() {
        String[][] fieldsArr = null;
        try {

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query

            String query = "select * from sidgroupdetails";
            //  System.out.println(query);
            resultSet = statement.executeQuery(query);

            int rowcount = 0, arrayRow = 0;
            if (resultSet.last()) {
                rowcount = resultSet.getRow();
                resultSet.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
            fieldsArr = new String[rowcount][2];

            while (resultSet.next()) {
                fieldsArr[arrayRow][0] = resultSet.getString("typename");
                fieldsArr[arrayRow++][1] = resultSet.getString("typevalue");
            }
            statement.close();
            resultSet.close();
            //End Getting Group Name
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }


        return fieldsArr;

    }

    public String[] getFields(String typeName) {
        String[] fieldsArr = null;
        try {
            if (typeName == null) {
                return null;
            }

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query

            String query = "select * from sidgroupdetails where typename = '" + typeName + "'";
            //  System.out.println(query);
            resultSet = statement.executeQuery(query);

            int rowcount = 0, arrayRow = 0;
            if (resultSet.last()) {
                rowcount = resultSet.getRow();
                resultSet.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
            fieldsArr = new String[rowcount];

            while (resultSet.next()) {

                fieldsArr[arrayRow++] = resultSet.getString("typevalue");
            }
            statement.close();
            resultSet.close();
            //End Getting Group Name
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }


        return fieldsArr;

    }

    public ArrayList getAllUserRecords(String query) {
        ArrayList groupName = new ArrayList();
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query


            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getString("filename") != null) {
                    if (resultSet.getString("filename").trim().length() > 0) {

                        groupName.add(resultSet.getString("filename").trim());
                    }
                }

            }
            statement.close();
            resultSet.close();
            //End Getting Group Name
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return groupName;

    }

    public String[][] getAllSpkInfo(String fileName) {
        String[][] spkInfo = null;
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            String query = "select filename, gender, language, network, groupname from sidmaster where filename = '" + fileName.trim() + "'";
            // System.out.println(query);
            resultSet = statement.executeQuery(query);

            spkInfo = new String[5][2];
            if (resultSet.next()) {
                if (resultSet.getString("filename") != null) {
                    if (resultSet.getString("filename").trim().length() > 0) {

                        spkInfo[0][0] = "filename";
                        spkInfo[0][1] = resultSet.getString("filename");

                        spkInfo[1][0] = "gender";
                        spkInfo[1][1] = ((resultSet.getString("gender") != null) ? resultSet.getString("gender") : "");

                        spkInfo[2][0] = "language";
                        spkInfo[2][1] = ((resultSet.getString("language") != null) ? resultSet.getString("language") : "");

                        spkInfo[3][0] = "network";
                        spkInfo[3][1] = ((resultSet.getString("network") != null) ? resultSet.getString("network") : "");

                        spkInfo[4][0] = "groupname";
                        spkInfo[4][1] = ((resultSet.getString("groupname") != null) ? resultSet.getString("groupname") : "");

                    }
                }

            }


            statement.close();
            resultSet.close();
            //End Getting Group Name
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return spkInfo;

    }

    public boolean getInsertFileInfo(String fileName, String exstFileName) {
        String spkInfo = null;
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            String query = "select * from sidmaster where filename = '" + fileName.trim() + "' and wavefile = '" + exstFileName + "'";
            System.out.println(query);
            resultSet = statement.executeQuery(query);


            return resultSet.next();


            //End Getting Group Name
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(SpeakerMaster.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return false;

    }

    //Inset annotations
    public boolean insertNewTestfile(String userDir, String newWave) {
        try {


            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            String query = "select * from sidmaster where filename = '" + userDir.trim() + "'";
            resultSet = statement.executeQuery(query);
            String groupname = "", network = "", channel = "", language = "", gender = "", nationality = "", date = "";
            if (resultSet.next()) {
                groupname = resultSet.getString("groupname");
                network = resultSet.getString("network");
                channel = resultSet.getString("channel");
                language = resultSet.getString("language");
                gender = resultSet.getString("gender");
                nationality = resultSet.getString("nationality");

            }


            statement.close();
            resultSet.close();



            query = "INSERT into sidmaster(mode,wavefile,groupname,network,channel,language,gender,nationality,filename) values('test','" + newWave + "','" + groupname + "','" + network + "','" + channel + "','" + language + "','" + gender + "','" + nationality + "','" + userDir + "')";
            // System.out.println(query);
            preparedStatement = connect.prepareStatement(query);
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rows > 0) {
                return true;
            }
        } catch (SQLException er) {
            Logger.getLogger(SpeakerMaster.class.getName()).log(Level.SEVERE, er.toString());

        } catch (Exception ex) {
            Logger.getLogger(SpeakerMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean updateTrainedStatus(String userDir, ArrayList<String> fileslist) {
        try {

            String query = "update sidmaster set mode = 'test' where filename = '" + userDir + "'";
            // System.out.println(query);
            preparedStatement = connect.prepareStatement(query);
            int rows = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rows == 0) {
                return false;
            }
            String whereAs = "";
            for (String files : fileslist) {
                if (whereAs.isEmpty()) {
                    whereAs = "wavefile = '" + files + "'";
                } else {
                    whereAs = whereAs + " or wavefile = '" + files + "'";
                }

            }

            query = "update sidmaster set mode = 'train' where filename = '" + userDir + "' and " + whereAs;
            //System.out.println(query);
            preparedStatement = connect.prepareStatement(query);
            rows = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (rows > 0) {
                return true;
            }

        } catch (SQLException er) {
            Logger.getLogger(SpeakerMaster.class.getName()).log(Level.SEVERE, er.toString());

        } catch (Exception ex) {
            Logger.getLogger(SpeakerMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean deleteSidFile(String query) {
        try {
            // Creating Statement for query execution
            statement = connect.createStatement();
            // creating Query String          
            // Updating Table
            int rows = statement.executeUpdate(query);
            statement.close();
            if (rows > 0) {
                return true;
            }
        } catch (SQLException er) {
            System.err.println(er);
        }
        return false;
    }

    public ArrayList getAllSpkInfo(String fileName, String userDir) {
        ArrayList<String> spkInfo = new ArrayList();
        try {
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            String query = "select * from sidmaster where filename = '" + userDir.trim() + "' and wavefile = '" + fileName.trim() + "'";
            // System.out.println(query);
            resultSet = statement.executeQuery(query);


            if (resultSet.next()) {
                if (resultSet.getString("filename") != null) {
                    if (resultSet.getString("filename").trim().length() > 0) {
                        spkInfo.add(resultSet.getString("nationality"));
                        spkInfo.add(resultSet.getString("date"));
                        spkInfo.add(resultSet.getString("gender"));
                        spkInfo.add(resultSet.getString("language"));
                        spkInfo.add(resultSet.getString("channel"));
                        spkInfo.add(resultSet.getString("network"));
                        spkInfo.add(resultSet.getString("groupname"));
                    }
                }

            }
            for (int i = 0; i < spkInfo.size(); i++) {

                if (spkInfo.get(i) == null) {
                    spkInfo.set(i, "Default");
                    continue;
                }

                if ((spkInfo.get(i).trim()).isEmpty()) {
                    spkInfo.set(i, "Default");
                    continue;
                }

                if ((spkInfo.get(i).trim().toLowerCase()).startsWith("null")) {
                    spkInfo.set(i, "Default");
                    continue;
                }


            }



            statement.close();
            resultSet.close();
            //End Getting Group Name
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return spkInfo;

    }

    public static void main(String args[]) {
        new SpeakerMaster(null);
    }
}
/*
 * filenumber automatically increasing
 * 
 * 
 *  public String getInsertFileInfo(String fileName, String exstFileName) {
 String spkInfo = null;
 try {
 // Statements allow to issue SQL queries to the database
 statement = connect.createStatement();
 // Result set get the result of the SQL query
 String query = "select COUNT(*) As counts, wavefile from sidmaster where filename = '" + fileName.trim() + "'";
 System.out.println(query);
 resultSet = statement.executeQuery(query);


 if (resultSet.next()) {
 if (resultSet.getString("wavefile") != null) {
 if (resultSet.getString("wavefile").trim().length() > 7) {
 String spkfileName = resultSet.getString("wavefile").trim();
 spkfileName = spkfileName.replaceAll(".wav", "");
 spkfileName = spkfileName.substring(0, spkfileName.length() - 2);

 spkfileName = (resultSet.getInt("counts") < 10) ? (spkfileName + "0" + (resultSet.getInt("counts") + 1)) : (spkfileName + (resultSet.getInt("counts") + 1));
 spkInfo = spkfileName + ".wav";

 }
 }
 }


 statement.close();
 resultSet.close();
 //End Getting Group Name
 } catch (SQLException ex) {
 System.err.println(ex.getMessage());
 }
 return spkInfo;

 }
 * 
 * 
 * 
 * 
 */
