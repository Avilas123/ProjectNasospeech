/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface_1;

import Speech.rmi.VrasiClientKWS;
import Speech.rmi.VrasiClientPDS;
import Speech.rmi.VrasiClientSID;

/**
 *
 * @author Tatapower SED
 *
 */
public class UIControls {

    private MainGuI mainFrame;
    private DoUIControlProcess controlPro;

    public UIControls(MainGuI mainFrame) {
        this.mainFrame = mainFrame;
        controlPro = new DoUIControlProcess();
    }

    public void startControlProcess() {
        controlPro.start();
    }

    public void stopControlProcess() {
        controlPro.stop();
    }

    public class DoUIControlProcess implements Runnable {

        private Thread thread = null;
        private boolean oldserverkwsStatus = false;
        private boolean oldserverpdsStatus = false;
        private boolean oldserversidStatus = false;

        public void start() {
            thread = new Thread(this);
            thread.setName("uicontrols");
            thread.start();
        }

        public void stop() {
            if (thread != null) {
                thread.interrupt();
            }
            thread = null;
        }

        public void run() {

            try {
                thread.sleep(30000);
                oldserverkwsStatus = new VrasiClientKWS().getConnectionStatus();
                oldserverpdsStatus = new VrasiClientPDS().getConnectionStatus();
                oldserversidStatus = new VrasiClientSID().getConnectionStatus();
                System.out.println("Checking network status..");
                while (thread != null) {
                    try {
                        thread.sleep(5000);
                        boolean serverkwsStatus = new VrasiClientKWS().getConnectionStatus();
                        boolean serverpdsStatus = new VrasiClientPDS().getConnectionStatus();
                        boolean serversidStatus = new VrasiClientSID().getConnectionStatus();
                        try {
                            if (serverkwsStatus != oldserverkwsStatus || serverpdsStatus != oldserverpdsStatus || serversidStatus != oldserversidStatus) {
                                mainFrame.pWave.serverStatus.start();
                            }
                            oldserverkwsStatus = serverkwsStatus;
                            oldserverpdsStatus = serverpdsStatus;
                            oldserversidStatus = serversidStatus;

                        } catch (Exception er) {
                        }


                    } catch (Exception er) {
                    }

                }
            } catch (Exception er) {
                System.err.println(er);
            }
        }
    }
}
