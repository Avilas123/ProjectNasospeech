/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.filebrowser;

/**
 *
 * @author Tatapower SED
 *
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileEnumerator {

    /**
     * @param args
     * @throws IOException
     */
    public ArrayList getListOfRippedFiles(String filePath) {

        // Prepare the List of files
        String path = filePath;
        ArrayList<String> Files = new ArrayList<String>();
        LinkedList<String> Dir = new LinkedList<String>();
        try {
            File f = new File(path);
            Dir.add(f.getAbsolutePath());
            while (!Dir.isEmpty()) {
                f = new File(Dir.pop());
                if (f.isFile()) {
                    Files.add(f.getAbsolutePath());
                } else {
                    String arr[] = f.list();
                    try {
                        for (int i = 0; i < arr.length; i++) {
                            Dir.add(f.getAbsolutePath() + "/" + arr[i]);
                        }
                    } catch (NullPointerException exp) {
                        Dir.remove(f.getAbsoluteFile());
                    }
                }
            }

        } catch (Exception er) {
        }
        //Print the files
        return Files;
    }
}
