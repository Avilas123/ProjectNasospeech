package Speech.rmi;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;

/**
 *
 * @author Tatapower SED
 *
 */
public class CreateSession {

    public CreateSession() {
    }

    public boolean setUserSessionDir(String userName, String rootDir) {

        if (!createSessionRootDir(rootDir)) {
            return false;
        }

        if (isAvailable(rootDir + "/" + userName)) {
            File userDir = new File(rootDir + "/" + userName);
            deleteDir(userDir);

        }
        if (!createSessionDir(rootDir + "/" + userName)) {
            return false;
        }
        return true;
    }

    private boolean createSessionRootDir(String dirName) {
        if (!isAvailable(dirName)) {
            if (!createSessionDir(dirName)) {
                return false;
            }
        } else {

            if (!isDirctory(dirName)) {
                deleteSessionFile(dirName);
                if (!createSessionDir(dirName)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean createSessionDir(String dirName) {
        try {
            File dir = new File(dirName);
            return dir.mkdir();
        } catch (Exception er) {
            System.err.println(er);
        }
        return false;
    }

    private boolean isAvailable(String dirName) {
        try {
            File dir = new File(dirName);
            if (dir.exists()) {
                return true;
            }
        } catch (Exception er) {
            System.err.println(er);
        }

        return false;
    }

    private boolean isDirctory(String dirName) {
        try {
            File dir = new File(dirName);
            if (dir.isDirectory()) {
                return true;
            }
        } catch (Exception er) {
            System.err.println(er);
        }

        return false;
    }

    private boolean deleteSessionFile(String dirName) {
        try {
            File dir = new File(dirName);
            if (dir.exists()) {
                return dir.delete();
            }
        } catch (Exception er) {
            System.err.println(er);
        }

        return false;
    }

    private void deleteDir(File file) {

        if (file.isDirectory()) {

            //directory is empty, then delete it
            if (file.list().length == 0) {

                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());

            } else {

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    deleteDir(fileDelete);
                }

                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        } else {
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }
}
