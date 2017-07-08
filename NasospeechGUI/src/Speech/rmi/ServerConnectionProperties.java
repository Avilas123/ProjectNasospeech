/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.rmi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Tatapower SED
 *
 */
public class ServerConnectionProperties {

    private String pdsServer;
    private String sidServer;
    private String kswServer;

    public String getPdsServer() {
        return pdsServer;
    }

    public void setPdsServer(String pdsServer) {
        this.pdsServer = pdsServer;
    }

    public String getSidServer() {
        return sidServer;
    }

    public void setSidServer(String sidServer) {
        this.sidServer = sidServer;
    }

    public String getKswServer() {
        return kswServer;
    }

    public void setKswServer(String kswServer) {
        this.kswServer = kswServer;
    }

    public boolean getServerProperties() {
        Properties prop = new Properties();
        String kws_server = null, pds_server = null, sid_server = null;
        try {
            //load a properties file
            FileInputStream fStream = new FileInputStream("conf/server/serverconfig.properties");
            prop.load(fStream);
            if ((prop.getProperty("PDSServerHost") == null) || (prop.getProperty("KWSServerHost") == null) || (prop.getProperty("SIDServerHost") == null)) {
                return false;
            }
            pds_server = prop.getProperty("PDSServerHost");
            kws_server = prop.getProperty("KWSServerHost");
            sid_server = prop.getProperty("SIDServerHost");


            if ((pds_server.length() < 1) || (kws_server.length() < 1) || (sid_server.length() < 1)) {
                return false;
            }

            setPdsServer(pds_server);
            setKswServer(kws_server);
            setSidServer(sid_server);
            fStream.close();


        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return true;
    }
}
