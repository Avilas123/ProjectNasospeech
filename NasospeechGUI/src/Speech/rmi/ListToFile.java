/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tatapower SED
 *
 */
public class ListToFile {

    public ListToFile() {
    }

    public void writeFile(String fileName, ArrayList list) {
        try {

            if (list == null) {
                return;
            }

            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (int j = 0; j < list.size(); j++) {
                bw.write((list.get(j).toString()).trim());
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
