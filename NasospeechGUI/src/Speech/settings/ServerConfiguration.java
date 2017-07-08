/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BasicSettings.java
 *
 * Created on Apr 6, 2013, 4:50:12 PM
 */
package Speech.settings;

import Speech.rmi.VrasiClientKWS;
import Speech.rmi.VrasiClientPDS;
import Speech.rmi.VrasiClientSID;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Tatapower SED
 *
 */
public class ServerConfiguration extends javax.swing.JPanel {

    /**
     * Creates new form BasicSettings
     */
    public ServerConfiguration() {
        initComponents();
        this.setSize(new Dimension(270, 200));
        readProperties();
        serverStatus();
    }

    //write properties to file
    public void writeProperties() {
        // The name of the file to open.
        String fileName = "conf//server//serverconfig.properties";

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("PDSServerHost=" + txtPdsIp.getText());
            bufferedWriter.newLine();
            bufferedWriter.write("KWSServerHost=" + txtKwsIp.getText());
            bufferedWriter.newLine();
            bufferedWriter.write("SIDServerHost=" + txtSidIp.getText());
            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '"
                    + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

    }

    //read properties from file and display in text fields
    public void readProperties() {
        // The name of the file to open.
        String fileName = "conf//server//serverconfig.properties";
        String line = null;
        // declares an array of strings
        String[] ipArray;
        ipArray = new String[3];

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split("=");
                ipArray[i] = array[1];
                i++;
            }
            txtPdsIp.setText(ipArray[0]);
            txtKwsIp.setText(ipArray[1]);
            txtSidIp.setText(ipArray[2]);

            // close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '"
                    + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                    + fileName + "'");
            // Or we could just do this: 
            // ex.printStackTrace();
        }

    }

    private void serverStatus() {
        Thread thread = new Thread() {
            public void run() {
                boolean serverkwsStatus = new VrasiClientKWS().getConnectionStatus();
                boolean serverpdsStatus = new VrasiClientPDS().getConnectionStatus();
                boolean serversidStatus = new VrasiClientSID().getConnectionStatus();
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(500);
                        labKWS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/blank.png")));
                        labPDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/blank.png")));
                        labSID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/blank.png")));
                        Thread.sleep(500);

                        if (serverkwsStatus) {
                            labKWS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/serveron.png")));
                        } else {
                            labKWS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/serveroff.png")));
                        }
                        if (serverpdsStatus) {
                            labPDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/serveron.png")));
                        } else {
                            labPDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/serveroff.png")));
                        }
                        if (serversidStatus) {
                            labSID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/serveron.png")));
                        } else {
                            labSID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/serveroff.png")));
                        }
                    } catch (Exception er) {
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     *
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmdSave = new javax.swing.JButton();
        txtPdsIp = new javax.swing.JTextField();
        txtKwsIp = new javax.swing.JTextField();
        txtSidIp = new javax.swing.JTextField();
        labKWS = new javax.swing.JLabel();
        labPDS = new javax.swing.JLabel();
        labSID = new javax.swing.JLabel();

        jLabel4.setText("PDS Server");

        jLabel5.setText("KWS Server");

        jLabel6.setText("SID Server");

        cmdSave.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        cmdSave.setText("Connect");
        cmdSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveActionPerformed(evt);
            }
        });

        labKWS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/serveroff.png"))); // NOI18N

        labPDS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/serveroff.png"))); // NOI18N

        labSID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/serveroff.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdSave, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtKwsIp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(txtPdsIp, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSidIp))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labKWS)
                    .addComponent(labPDS)
                    .addComponent(labSID))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPdsIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labPDS))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labKWS)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtKwsIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labSID)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtSidIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdSave)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        // TODO add your handling code here:
        writeProperties();
        serverStatus();
    }//GEN-LAST:event_cmdSaveActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdSave;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel labKWS;
    private javax.swing.JLabel labPDS;
    private javax.swing.JLabel labSID;
    private javax.swing.JTextField txtKwsIp;
    private javax.swing.JTextField txtPdsIp;
    private javax.swing.JTextField txtSidIp;
    // End of variables declaration//GEN-END:variables
}
