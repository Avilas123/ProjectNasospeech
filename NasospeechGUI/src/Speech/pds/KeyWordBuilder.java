/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.pds;

import Speech.rmi.VrasiClientPDS;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Tatapower SED
 *
 */
public class KeyWordBuilder extends KeyList {

    List startTimeList;
    List endTimeList;

    public KeyWordBuilder() {

        endTimeList = new ArrayList();
        startTimeList = new ArrayList();
    }

    public boolean setSource(String inputWave, String language, String userName, ArrayList annotation, String threshold) {
        VrasiClientPDS vClient;
        List keyRes = null;
        try {
            vClient = new VrasiClientPDS();
            if (!vClient.setRemoteConnection(userName, inputWave)) {
                javax.swing.JOptionPane.showMessageDialog(null, "Server could not find due to missing some resource", "Network error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return false;
            }

            keyRes = vClient.processPDS(language, annotation, threshold);


            //new PhoneExe(inputWave,language);
        } catch (Exception er) {
            System.out.println(er);
        }





        if (keyRes == null) {
            return false;
        }
        /*
         for(int i = 0; i < keyRes.size(); i++){
         System.out.println(keyRes.get(i));
         }
         */

        setFileContent(keyRes);
        extractKeyword(keyRes);


        if (!endTimeList.isEmpty()) {
            setEndTimeList(endTimeList);
        }
        if (!startTimeList.isEmpty()) {
            setStartTimeList(startTimeList);
        }




        return true;
    }

    private void extractKeyword(List source) {

        String keyStart = null, keyEnd = null, status = null;
        int statusValue;
        StringTokenizer st;


        startTimeList.clear();
        endTimeList.clear();

        try {
            for (int i = 0; i < source.size(); i++) {
                st = new StringTokenizer(source.get(i).toString(), " ");

                if (st.hasMoreElements()) {
                    status = st.nextElement().toString();
                }


                if (st.hasMoreElements()) {
                    keyStart = st.nextElement().toString();
                }
                if (st.hasMoreElements()) {
                    keyEnd = st.nextElement().toString();
                }

                //System.out.println(keyStart+" + "+keyEnd+" + "+keyList);
                if (keyStart != null && keyEnd != null && status != null) {
                    try {
                        statusValue = Integer.parseInt(status);
                    } catch (NumberFormatException er) {
                        continue;
                    }
                    if (statusValue != 1) {
                        continue;
                    }

                    keyStart = keyStart.trim();
                    keyEnd = keyEnd.trim();
                    if (keyStart.length() > 0 && keyEnd.length() > 0) {

                        startTimeList.add(keyStart);
                        endTimeList.add(keyEnd);
                    }
                }

            }
        } catch (Exception er) {
            System.err.println(er);
        }
    }

    public static void main(String args[]) {
        new KeyWordBuilder().setSource("", "", "", null, "");
    }
}
