/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.phoneme;

import Speech.rmi.VrasiClientKWS;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Tatapower SED
 *
 */
public class KeyWordBuilder extends KeyList {

  public  List keyExtract;
    List startTimeList;
    List endTimeList;

    public KeyWordBuilder() {
        keyExtract = new ArrayList();
        endTimeList = new ArrayList();
        startTimeList = new ArrayList();
    }

    public boolean setSource(String inputWave, String language, String userName, List processedList) {
        VrasiClientKWS vClient;
        List keyRes = null;

        if (processedList == null) {
            try {
                vClient = new VrasiClientKWS();

                if (!vClient.setRemoteConnection(userName, inputWave)) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Server could not find due to missing some resource","Network error",javax.swing.JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                keyRes = vClient.processPhoneReg(language);
                System.out.println("\nLANGUAGE testing: "+language);

                //new PhoneExe(inputWave,language);
            } catch (Exception er) {
                System.out.println("Error - "+er);
            }

        } else {
            keyRes = processedList;
        }



        if (keyRes == null) {
            return false;
        }



        setFileContent(keyRes);
        extractKeyword(keyRes);

        if (!keyExtract.isEmpty()) {
            setKeywordsList(keyExtract);
        }
        if (!endTimeList.isEmpty()) {
            setEndTimeList(endTimeList);
        }
        if (!startTimeList.isEmpty()) {
            setStartTimeList(startTimeList);
        }


        System.out.println(getKeywordsList().size() + " " + getStartTimeList().size() + " " + getEndTimeList().size());

        return true;
    }

    private void extractKeyword(List source) {

        String keyList = null, keyStart = null, keyEnd = null;
        StringTokenizer st;

        keyExtract.clear();
        startTimeList.clear();
        endTimeList.clear();

        try {
            for (int i = 0; i < source.size(); i++) {
                st = new StringTokenizer(source.get(i).toString(), " ");
                if (st.hasMoreElements()) {
                    keyStart = st.nextElement().toString();
                }
                if (st.hasMoreElements()) {
                    keyEnd = st.nextElement().toString();
                }
                if (st.hasMoreElements()) {
                    keyList = st.nextElement().toString();
                }
                //System.out.println(keyStart+" + "+keyEnd+" + "+keyList);
                if (keyList != null && keyStart != null && keyEnd != null) {
                    keyList = keyList.trim();
                    keyStart = keyStart.trim();
                    keyEnd = keyEnd.trim();
                    if (keyList.length() > 0 && keyStart.length() > 0 && keyEnd.length() > 0) {
                        keyExtract.add(keyList);
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
        new KeyWordBuilder().setSource("", "", "", null);
    }
}
