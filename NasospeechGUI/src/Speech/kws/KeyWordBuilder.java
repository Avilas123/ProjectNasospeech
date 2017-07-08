/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.kws;

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

    List keyExtract;
    List startTimeList;
    List endTimeList;

    public KeyWordBuilder() {
        keyExtract = new ArrayList();
        endTimeList = new ArrayList();
        startTimeList = new ArrayList();
    }

    public boolean setSource(String inputWave, String language, String userName, ArrayList selectedKeyword, List processedList) {
        VrasiClientKWS vClient;
        List keyRes = null;
        if (processedList == null) {
            try {
                //new KeywordExe(inputWave, language);

                vClient = new VrasiClientKWS();

                if (!vClient.setRemoteConnection(userName, inputWave)) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Server could not find due to missing some resource");
                    return false;
                }
                keyRes = vClient.processKwsReg(language, selectedKeyword);



            } catch (Exception er) {
                System.out.println(er);
            }
        } else {
            keyRes = processedList;
        }

        if (keyRes == null) {
            return false;
        }

//        for (int i = 0; i < keyRes.size(); i++) {
//            System.out.println(keyRes.get(i));
//        }

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


        System.out.println(getKeywordsList() + getStartTimeList().toString() + getEndTimeList());

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
                        if ((keyList.toLowerCase()).startsWith("garb") || (keyList.toLowerCase()).startsWith("sil")) {
                            continue;
                        }

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
        new KeyWordBuilder().setSource("", "", "", null, null);
    }
}
