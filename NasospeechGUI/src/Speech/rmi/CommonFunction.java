/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 *
 * @author Tatapower SED
 *
 */
public class CommonFunction {

    public void copyFile(File sourceFile, File destFile) throws IOException {


        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    public void deleteDir(File file) {
        try {
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
                // System.out.println("File is deleted : " + file.getAbsolutePath());
            }

        } catch (Exception er) {
        }

    }
}
