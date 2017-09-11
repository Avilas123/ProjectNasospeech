/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.common;

import java.io.File;

/**
 *
 * @author Lok Bahadur Chetri<lok.b.chetri@gmail.com>
 */
public class DeleteFile {

    public static void delete(File file)
    {

        // Check if file is directory/folder
        if(file.isDirectory())
        {
            // Get all files in the folder
            File[] files=file.listFiles();

            for(int i=0;i<files.length;i++)
            {

            // Delete each file in the folder
            delete(files[i]);

            }

            // Delete the folder
            file.delete();

        }
        else
        {
            // Delete the file if it is not a folder
            file.delete();

        }
    }   
}
