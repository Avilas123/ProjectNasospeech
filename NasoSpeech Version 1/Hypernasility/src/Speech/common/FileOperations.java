/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.common;

import java.io.File;

/**
 *
 * @author Tatapower SED
 *
 */
public class FileOperations {

    public static void deleteDir(File file) {

        if (file.isDirectory()) {

            //directory is empty, then delete it
            if (file.list().length == 0) {
                //  file.delete();
                // System.out.println("Directory is deleted : "
                // + file.getAbsolutePath());
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
                    // file.delete();
                    // System.out.println("Directory is deleted : "
                    //  + file.getAbsolutePath());
                }
            }

        } else {
            //if file, then delete it
            file.delete();
            // System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }
}
