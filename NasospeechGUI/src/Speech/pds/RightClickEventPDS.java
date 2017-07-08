/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.pds;

import Speech.WavePanel.CutAudioWave;
import Speech.WavePanel.SelectedAudioWave;
import Speech.annotations.FindAnnotated;
import Speech.annotations.GetAnnotation;
import Speech.annotations.UpdateAnnotation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import Speech.common.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Tatapower SED
 *
 */
public class RightClickEventPDS {

    private PlotWavePDS pWavePDS;
    private int startSam, endSam, startPix, endPix;
    private JMenu zoom;
    private JMenu play;
    private JMenu pds;
    private JMenu phoneme;
    private JMenu keywordMenu;
    private JMenu annotation;
    private JMenu keywordAssam;
    private JMenu keywordHindi;
    private JMenuItem item;
    private JMenu manualProcess;

    public RightClickEventPDS(PlotWavePDS pWavePDS) {
        this.pWavePDS = pWavePDS;

        zoom = new JMenu(" Zoom ");
        zoom.setFont(new Font("Courier New", Font.PLAIN, 15));
        zoom.setBackground(Color.white);
        play = new JMenu(" Play ");
        play.setFont(new Font("Courier New", Font.PLAIN, 15));
        play.setBackground(Color.white);
        pds = new JMenu(" Processing degraded speech ");
        pds.setFont(new Font("Courier New", Font.PLAIN, 15));
        pds.setBackground(Color.white);
        phoneme = new JMenu(" Phoneme recognition ");
        phoneme.setFont(new Font("Courier New", Font.PLAIN, 15));
        phoneme.setBackground(Color.white);
        keywordMenu = new JMenu(" Keword spotting ");
        keywordMenu.setFont(new Font("Courier New", Font.PLAIN, 15));
        keywordMenu.setBackground(Color.white);

        annotation = new JMenu(" Annotation ");
        annotation.setFont(new Font("Courier New", Font.PLAIN, 15));
        annotation.setBackground(Color.white);
        keywordAssam = new JMenu(" Assamese ");
        keywordAssam.setFont(new Font("Courier New", Font.PLAIN, 15));
        keywordAssam.setBackground(Color.white);
        keywordHindi = new JMenu(" Hindi ");
        keywordHindi.setFont(new Font("Courier New", Font.PLAIN, 15));
        keywordHindi.setBackground(Color.white);
        manualProcess = new JMenu(" Manual process ");
        manualProcess.setFont(new Font("Courier New", Font.PLAIN, 15));
        manualProcess.setBackground(Color.white);


    }

    public void addMenuItems() {
        pWavePDS.menu.removeAll();
        zoom.removeAll();
        play.removeAll();
        pds.removeAll();
        phoneme.removeAll();
        keywordMenu.removeAll();

        annotation.removeAll();
        keywordAssam.removeAll();
        keywordHindi.removeAll();
        manualProcess.removeAll();
        try {


            //Play Manu
            if (pWavePDS.playback.line == null && pWavePDS.audioInputStream != null) {
                //  pWavePDS.menu.add(play);
            }
            if (pWavePDS.partialPlay.thread == null) {
                item = new JMenuItem(" Play ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (sourceAvailValidation()) {
                            playSoundAll();
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }

            item = new JMenuItem(" Play from here ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        // playFromSoundAll();
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            play.add(item);

            if (pWavePDS.pausB.getToolTipText().equals("pause") && pWavePDS.partialPlay.thread != null) {
                item = new JMenuItem(" Pause ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (sourceAvailValidation()) {
                            pWavePDS.pauseSound();
                        }
                    }
                });


                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }



            if ((!pWavePDS.pausB.getToolTipText().equals("pause")) && pWavePDS.partialPlay.thread != null) {
                item = new JMenuItem(" Resume ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (sourceAvailValidation()) {
                            pWavePDS.resumeSound();
                        }
                    }
                });


                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }




            if (pWavePDS.partialPlay.thread != null) {

                item = new JMenuItem(" Stop ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (sourceAvailValidation()) {
                            pWavePDS.stopSound();
                        }
                    }
                });


                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }


            //Save As...

            if (pWavePDS.audioInputStream != null) {

                item = new JMenuItem(" Save As... ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        pdsFileSaveMethod();
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }


            //Zoom Menu
            if (pWavePDS.audioInputStream != null) {
                manualProcess.add(zoom);
            }
            //End
            if (pWavePDS.audioInputStream != null) {
                pWavePDS.menu.add(manualProcess);
            }
            //Annotation
       /* if (pWavePDS.audioInputStream != null) {
             pWavePDS.menu.add(annotation);
             }*/
            if (pWavePDS.audioInputStream != null) {
                item = new JMenuItem(" Create label ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        createAnnotation();

                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                pWavePDS.menu.add(item);
            }

            //PDS
            if (pWavePDS.audioInputStream != null) {
                pWavePDS.menu.add(pds);
            }

            item = new JMenuItem(" Remove silence ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        if (pWavePDS.processMethod.startsWith("Enhance")) {
                            pWavePDS.mainFrame.createSoundImprovePanel("Remove silence", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream));
                        } else if (pWavePDS.processMethod.startsWith("Combined")) {
                            pWavePDS.mainFrame.createSoundImprovePanel("Remove silence", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream));
                        } else {
                            AudioInputStream audioStream = getSilenceFileStream();
                            if (audioStream == null) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Server is busy... try after some time");
                                return;
                            }
                            pWavePDS.mainFrame.createSoundImprovePanel("Remove silence", audioStream);
                        }

                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            pds.add(item);



            item = new JMenuItem(" Remove noise ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        if (pWavePDS.processMethod.startsWith("Enhance")) {
                            pWavePDS.mainFrame.createSoundImprovePanel("Remove noise", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream));
                        } else if (pWavePDS.processMethod.startsWith("Combined")) {
                            pWavePDS.mainFrame.createSoundImprovePanel("Remove noise", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream));
                        } else {
                            AudioInputStream audioStream = getSilenceFileStream();
                            if (audioStream == null) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Server is busy... try after some time");
                                return;
                            }
                            pWavePDS.mainFrame.createSoundImprovePanel("Remove noise", audioStream);
                        }

                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            pds.add(item);

            item = new JMenuItem(" Enhance speech ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        if (pWavePDS.processMethod.startsWith("Enhance")) {
                            pWavePDS.mainFrame.createSoundImprovePanel("Enhance speech", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream));
                        } else if (pWavePDS.processMethod.startsWith("Combined")) {
                            pWavePDS.mainFrame.createSoundImprovePanel("Enhance speech", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream));
                        } else {
                            AudioInputStream audioStream = getSilenceFileStream();
                            if (audioStream == null) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Server is busy... try after some time");
                                return;
                            }
                            pWavePDS.mainFrame.createSoundImprovePanel("Enhance speech", audioStream);
                        }
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            pds.add(item);

            item = new JMenuItem(" Combined ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        if (pWavePDS.processMethod.startsWith("Enhance")) {
                            pWavePDS.mainFrame.createSoundImprovePanel("Combined", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream));
                        } else if (pWavePDS.processMethod.startsWith("Combined")) {
                            pWavePDS.mainFrame.createSoundImprovePanel("Combined", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream));
                        } else {
                            AudioInputStream audioStream = getSilenceFileStream();
                            if (audioStream == null) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Server is busy... try after some time");
                                return;
                            }
                            pWavePDS.mainFrame.createSoundImprovePanel("Combined", audioStream);
                        }
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            pds.add(item);




            if (pWavePDS.audioInputStream != null) {
                //Phoneme

                pWavePDS.menu.add(phoneme);


                //KWS
                pWavePDS.menu.add(keywordMenu);

                //Speaker identification
            }





            //Phoneme

            item = new JMenuItem(" Assamese ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        if (pWavePDS.processMethod.startsWith("Enhance")) {
                            pWavePDS.mainFrame.createPhenomeInternalFrame("Phenome analysis", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Assamese", null);
                        } else if (pWavePDS.processMethod.startsWith("Combined")) {
                            pWavePDS.mainFrame.createPhenomeInternalFrame("Phenome analysis", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Assamese", null);
                        } else {
                            AudioInputStream audioStream = getSilenceFileStream();
                            if (audioStream == null) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Server is busy... try after some time");
                                return;
                            }
                            pWavePDS.mainFrame.createPhenomeInternalFrame("Phenome analysis", audioStream, "Assamese", null);
                        }
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            phoneme.add(item);

            item = new JMenuItem(" Hindi ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        if (pWavePDS.processMethod.startsWith("Enhance")) {
                            pWavePDS.mainFrame.createPhenomeInternalFrame("Phenome analysis", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Hindi", null);
                        } else if (pWavePDS.processMethod.startsWith("Combined")) {
                            pWavePDS.mainFrame.createPhenomeInternalFrame("Phenome analysis", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Hindi", null);
                        } else {
                            AudioInputStream audioStream = getSilenceFileStream();
                            if (audioStream == null) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Server is busy... try after some time");
                                return;
                            }
                            pWavePDS.mainFrame.createPhenomeInternalFrame("Phenome analysis", audioStream, "Hindi", null);
                        }

                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            phoneme.add(item);

            item = new JMenuItem(" Telugu ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        if (pWavePDS.processMethod.startsWith("Enhance")) {
                            pWavePDS.mainFrame.createPhenomeInternalFrame("Phenome analysis", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Telugu", null);
                        } else if (pWavePDS.processMethod.startsWith("Combined")) {
                            pWavePDS.mainFrame.createPhenomeInternalFrame("Phenome analysis", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Telugu", null);
                        } else {
                            AudioInputStream audioStream = getSilenceFileStream();
                            if (audioStream == null) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Server is busy... try after some time");
                                return;
                            }
                            pWavePDS.mainFrame.createPhenomeInternalFrame("Phenome analysis", audioStream, "Telugu", null);
                        }
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            phoneme.add(item);


            item = new JMenuItem(" English ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        if (pWavePDS.processMethod.startsWith("Enhance")) {
                            pWavePDS.mainFrame.createPhenomeInternalFrame("Phenome analysis", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "English", null);
                        } else if (pWavePDS.processMethod.startsWith("Combined")) {
                            pWavePDS.mainFrame.createPhenomeInternalFrame("Phenome analysis", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "English", null);
                        } else {
                            AudioInputStream audioStream = getSilenceFileStream();
                            if (audioStream == null) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Server is busy... try after some time");
                                return;
                            }
                            pWavePDS.mainFrame.createPhenomeInternalFrame("Phenome analysis", audioStream, "English", null);
                        }
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            phoneme.add(item);

            //Keyword spotting

            keywordMenu.add(keywordAssam);
            keywordMenu.add(keywordHindi);

            item = new JMenuItem(" Telugu ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        if (pWavePDS.processMethod.startsWith("Enhance")) {
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Telugu");
                        } else if (pWavePDS.processMethod.startsWith("Combined")) {
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Telugu");
                        } else {
                            AudioInputStream audioStream = getSilenceFileStream();
                            if (audioStream == null) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Server is busy... try after some time");
                                return;
                            }
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", audioStream, "Telugu");
                        }
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            keywordMenu.add(item);


            item = new JMenuItem(" English ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        if (pWavePDS.processMethod.startsWith("Enhance")) {
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "English");
                        } else if (pWavePDS.processMethod.startsWith("Combined")) {
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "English");
                        } else {
                            AudioInputStream audioStream = getSilenceFileStream();
                            if (audioStream == null) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Server is busy... try after some time");
                                return;
                            }
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", audioStream, "English");
                        }
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            keywordMenu.add(item);

            //Assames

            item = new JMenuItem(" Phoneme Based ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        if (pWavePDS.processMethod.startsWith("Enhance")) {
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Assamese_part1");
                        } else if (pWavePDS.processMethod.startsWith("Combined")) {
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Assamese_part1");
                        } else {
                            AudioInputStream audioStream = getSilenceFileStream();
                            if (audioStream == null) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Server is busy... try after some time");
                                return;
                            }
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", audioStream, "Assamese_part1");
                        }
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            keywordAssam.add(item);


            item = new JMenuItem(" Word based ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        if (pWavePDS.processMethod.startsWith("Enhance")) {
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Assamese_part2");
                        } else if (pWavePDS.processMethod.startsWith("Combined")) {
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Assamese_part2");
                        } else {
                            AudioInputStream audioStream = getSilenceFileStream();
                            if (audioStream == null) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Server is busy... try after some time");
                                return;
                            }
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", audioStream, "Assamese_part2");
                        }
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            keywordAssam.add(item);

            //Hindi
            item = new JMenuItem(" Phoneme Based ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        if (pWavePDS.processMethod.startsWith("Enhance")) {
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Hindi_part1");
                        } else if (pWavePDS.processMethod.startsWith("Combined")) {
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Hindi_part1");
                        } else {
                            AudioInputStream audioStream = getSilenceFileStream();
                            if (audioStream == null) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Server is busy... try after some time");
                                return;
                            }
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", audioStream, "Hindi_part1");
                        }
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            keywordHindi.add(item);


            item = new JMenuItem(" Word Based ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        if (pWavePDS.processMethod.startsWith("Enhance")) {
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Hindi_part2");
                        } else if (pWavePDS.processMethod.startsWith("Combined")) {
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", StreamConverter.byteTostream(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream), "Hindi_part2");
                        } else {
                            AudioInputStream audioStream = getSilenceFileStream();
                            if (audioStream == null) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Server is busy... try after some time");
                                return;
                            }
                            pWavePDS.mainFrame.createKwsInternalFrame("Keyword spotting", audioStream, "Hindi_part2");
                        }

                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            keywordHindi.add(item);

            //[paste]



            item = new JMenuItem(" Normal ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        pWavePDS.setSreemDrawGraph();
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            zoom.add(item);


            item = new JMenuItem(" Zoom in ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        pWavePDS.setZoomIn();
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            zoom.add(item);

            item = new JMenuItem(" Zoom out ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        pWavePDS.setZoomOut();
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            zoom.add(item);


            //annotation Menu item
            item = new JMenuItem(" Command ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // annotationMethod();
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            annotation.add(item);

            item = new JMenuItem(" Resize ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // pWavePDS.mainFrame.createAnnotationInternalFrame("sdfsdf", null, null);
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            annotation.add(item);
        } catch (Exception er) {
            System.err.println(er);
        }

    }

    private void getSamplingPositions() {
        try {
            int width = pWavePDS.samplingGraph.getSize().width;
            int inPos = (int) pWavePDS.mousePosX1, enPos = (int) pWavePDS.mousePosX2;

            if (inPos < 1) {
                inPos = 1;
            }
            if (enPos > (width - 10)) {
                enPos = width - 10;
            }
            if (enPos < 1) {
                enPos = 1;
            }
            if (inPos > (width - 10)) {
                inPos = width - 10;
            }

            double bytesPerSecond = pWavePDS.audioInputStream.getFormat().getFrameSize() * (double) pWavePDS.audioInputStream.getFormat().getFrameRate();
            int startTime = ((inPos * pWavePDS.frames_per_pixel) * pWavePDS.audioInputStream.getFormat().getFrameSize());
            int endTime = ((enPos * pWavePDS.frames_per_pixel) * pWavePDS.audioInputStream.getFormat().getFrameSize());
            System.out.println("CK " + ((inPos * pWavePDS.frames_per_pixel) / pWavePDS.audioInputStream.getFormat().getFrameRate()) * 1000 + " " + ((enPos * pWavePDS.frames_per_pixel) / pWavePDS.audioInputStream.getFormat().getFrameRate()) * 1000);
            int startSample, endSample;
            if (startTime > endTime) {
                startSample = endTime;
                endSample = startTime;
            } else {
                startSample = startTime;
                endSample = endTime;
            }

            setStartSamples(startSample);
            setEndSamples(endSample);
        } catch (Exception er) {
            System.err.println(er);
        }

    }

    public void playSoundAll() {
        try {

            if (pWavePDS.playback.line != null) {
                if (pWavePDS.playback.line.isRunning() || pWavePDS.playback.line.isOpen()) {
                    return;
                }
            }

            if (pWavePDS.mousePosX1 != 0 && pWavePDS.mousePosX2 != 0) {
                getSamplingPositions();
                int startSample = getStartSamples();
                int endSample = getEndSamples();

                System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWavePDS.frames_per_pixel);

                SelectedAudioWave selectW = new SelectedAudioWave();
                selectW.selectdPortion(pWavePDS.streamBytes.getCurrent(), startSample, endSample);

                if (selectW.getSelectedByteArray() == null) {
                    return;
                }
                pWavePDS.streamBytes.setSelectedPlay(selectW.getSelectedByteArray());
                pWavePDS.selectedPlay = true;
                pWavePDS.streamVariable.setSecondStarts(startSample / pWavePDS.audioInputStream.getFormat().getFrameRate());
                //javax.swing.JOptionPane.showMessageDialog(null, "Comes here");
                pWavePDS.playSound();
                //javax.swing.JOptionPane.showMessageDialog(null, "After ");
            } else if (pWavePDS.mousePosX1 != 0) {
                if (pWavePDS.processMethod.startsWith("Remove silence") || pWavePDS.processMethod.startsWith("Combined")) {
                    pWavePDS.selectedPlay = true;
                    pWavePDS.partialPlay.start();
                } else {
                    int inPos = (int) pWavePDS.mousePosX1, enPos = (int) ((pWavePDS.samplingGraph.getSize().width) - 10);

                    if (inPos < 1) {
                        inPos = 1;
                    }

                    if (enPos < 1) {
                        enPos = 1;
                    }
                    if (inPos > (enPos - 10)) {
                        inPos = enPos - 10;
                    }

                    double bytesPerSecond = pWavePDS.audioInputStream.getFormat().getFrameSize() * (double) pWavePDS.audioInputStream.getFormat().getFrameRate();
                    int startTime = ((inPos * pWavePDS.frames_per_pixel) * pWavePDS.audioInputStream.getFormat().getFrameSize());
                    int endTime = ((enPos * pWavePDS.frames_per_pixel) * pWavePDS.audioInputStream.getFormat().getFrameSize());
                    System.out.println("CK " + ((inPos * pWavePDS.frames_per_pixel) / pWavePDS.audioInputStream.getFormat().getFrameRate()) * 1000 + " " + ((enPos * pWavePDS.frames_per_pixel) / pWavePDS.audioInputStream.getFormat().getFrameRate()) * 1000);
                    int startSample, endSample;
                    if (startTime > endTime) {
                        startSample = endTime;
                        endSample = startTime;
                    } else {
                        startSample = startTime;
                        endSample = endTime;
                    }

                    System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWavePDS.frames_per_pixel);

                    SelectedAudioWave selectW = new SelectedAudioWave();
                    selectW.selectdPortion(pWavePDS.streamBytes.getCurrent(), startSample, endSample);

                    if (selectW.getSelectedByteArray() == null) {
                        return;
                    }
                    pWavePDS.streamBytes.setSelectedPlay(selectW.getSelectedByteArray());
                    pWavePDS.selectedPlay = true;
                    pWavePDS.streamVariable.setSecondStarts(startSample / pWavePDS.audioInputStream.getFormat().getFrameRate());
                    pWavePDS.playSound();
                }
            } else {
                if (pWavePDS.processMethod.startsWith("Remove silence") || pWavePDS.processMethod.startsWith("Combined")) {
                    pWavePDS.selectedPlay = false;
                    pWavePDS.partialPlay.start();

                } else {
                    pWavePDS.selectedPlay = false;
                    pWavePDS.playSound();
                }
            }
        } catch (Exception er) {
            System.err.println(er);
        }
    }

    public void createAnnotation() {
        try {
            if (pWavePDS.mousePosX1 != 0 && pWavePDS.mousePosX2 != 0) {
                PixcelConversion pixConversion = new PixcelConversion();
                int anStPos = 0;
                int anEnPos = 0;
                if (pWavePDS.mousePosX1 > pWavePDS.mousePosX2) {
                    anStPos = (int) pWavePDS.mousePosX2;
                    anEnPos = (int) pWavePDS.mousePosX1;
                } else {
                    anStPos = (int) pWavePDS.mousePosX1;
                    anEnPos = (int) pWavePDS.mousePosX2;
                }
                anStPos = pixConversion.pixcelToMillisecond(anStPos, pWavePDS.frames_per_pixel, (int) pWavePDS.audioInputStream.getFormat().getFrameRate());
                anEnPos = pixConversion.pixcelToMillisecond(anEnPos, pWavePDS.frames_per_pixel, (int) pWavePDS.audioInputStream.getFormat().getFrameRate());

                FindAnnotated findAnn = new FindAnnotated(pWavePDS.mainFrame.getConn(), pWavePDS.fileHashValue, anStPos, anEnPos);

                if (findAnn.findAnnationAll()) {
                    javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Lable already created :" + findAnn.findAnnationAll(), "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                pWavePDS.mainFrame.createAnnotationInternalFrame("Annotation", anStPos, anEnPos, pWavePDS.fileHashValue, pWavePDS);
            }

        } catch (Exception er) {
            System.err.println(er);
        }
    }

    public void resizeandAddannotation() {
        try {


            if (pWavePDS.mousePosX1 != 0 && pWavePDS.mousePosX2 != 0 && pWavePDS.mousePosY1 > 195) {
                try {
                    PixcelConversion pixConversion = new PixcelConversion();
                    int anStPos = 0;
                    int anEnPos = 0;
                    if (pWavePDS.mousePosX1 > pWavePDS.mousePosX2) {
                        anStPos = (int) pWavePDS.mousePosX2;
                        anEnPos = (int) pWavePDS.mousePosX1;
                    } else {
                        anStPos = (int) pWavePDS.mousePosX1;
                        anEnPos = (int) pWavePDS.mousePosX2;
                    }
                    anStPos = pixConversion.pixcelToMillisecond(anStPos, pWavePDS.frames_per_pixel, (int) pWavePDS.audioInputStream.getFormat().getFrameRate());
                    anEnPos = pixConversion.pixcelToMillisecond(anEnPos, pWavePDS.frames_per_pixel, (int) pWavePDS.audioInputStream.getFormat().getFrameRate());


                    FindAnnotated findAnn = new FindAnnotated(pWavePDS.mainFrame.getConn(), pWavePDS.fileHashValue, anStPos, anEnPos);

                    if (findAnn.isAnnotatedBothPosiotion() == true) {
                        javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);

                        return;
                    }

                    if (!findAnn.findResizeAnnation()) {
                        javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }
                    if (findAnn.findNumberOfMatch() > 1) {
                        javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }
                    if (findAnn.getAnnotationStartPos() == 0 || findAnn.getAnnotationEndPos() == 0) {
                        javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }

                    int ann_actualStPos = findAnn.getAnnotationStartPos();
                    int ann_actualEnPos = findAnn.getAnnotationEndPos();
                    String ann_allow = findAnn.getAllow();
                    String ann_level = findAnn.getLevel();

                    int dragStPos = (int) pWavePDS.mousePosX1;
                    int dragEnPos = (int) pWavePDS.mousePosX2;

                    int ann_actualStPixPos = pixConversion.milliSecondToPixcel(Integer.toString(ann_actualStPos), pWavePDS.frames_per_pixel, (int) pWavePDS.audioInputStream.getFormat().getFrameRate());
                    int ann_actualEnPixPos = pixConversion.milliSecondToPixcel(Integer.toString(ann_actualEnPos), pWavePDS.frames_per_pixel, (int) pWavePDS.audioInputStream.getFormat().getFrameRate());

                    if (!(Math.abs(dragStPos - ann_actualStPixPos) < 15 || Math.abs(dragStPos - ann_actualEnPixPos) < 15)) {
                        javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }
                    if ((Math.abs(dragStPos - ann_actualStPixPos)) < 15) {
                        if (dragEnPos > ann_actualEnPixPos) {
                            javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            allAnnReturnSetUp();
                            return;
                        }


                    } else {
                        if (dragEnPos < ann_actualStPixPos) {
                            javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            allAnnReturnSetUp();
                            return;
                        }
                    }
                    if (ann_allow != null) {
                        ann_allow = ann_allow.trim();
                        try {
                            if (Integer.parseInt(ann_allow) > pWavePDS.mainFrame.getUserRoll()) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Senior has verified this region", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                                allAnnReturnSetUp();
                                return;
                            }
                        } catch (NumberFormatException er) {
                            System.err.println(er);
                        }
                    }

                    if ((Math.abs(dragStPos - ann_actualStPixPos)) < 15) {
                        int changeStarPos = pixConversion.pixcelToMillisecond(dragEnPos, pWavePDS.frames_per_pixel, (int) pWavePDS.audioInputStream.getFormat().getFrameRate());
                        int changeEndPos = ann_actualEnPos;
                        int option = JOptionPane.showConfirmDialog(pWavePDS, "Are you sure you want to update ", "Update", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            boolean updated = new UpdateAnnotation(pWavePDS.mainFrame.getConn()).resizeUpdate(ann_actualStPos, ann_actualEnPos, changeStarPos, changeEndPos, pWavePDS.fileHashValue, pWavePDS.mainFrame.getUserID(), ann_level);
                            if (!updated) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Unable to update", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            } else {
                                pWavePDS.samplingGraph.createWaveForm(null);
                            }
                        }
                    } else {
                        int changeStarPos = ann_actualStPos;
                        int changeEndPos = pixConversion.pixcelToMillisecond(dragEnPos, pWavePDS.frames_per_pixel, (int) pWavePDS.audioInputStream.getFormat().getFrameRate());
                        int option = JOptionPane.showConfirmDialog(pWavePDS, "Are you sure you want to update ", "Update", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            boolean updated = new UpdateAnnotation(pWavePDS.mainFrame.getConn()).resizeUpdate(ann_actualStPos, ann_actualEnPos, changeStarPos, changeEndPos, pWavePDS.fileHashValue, pWavePDS.mainFrame.getUserID(), ann_level);
                            if (!updated) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Unable to update", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            } else {
                                pWavePDS.samplingGraph.createWaveForm(null);
                            }
                        }
                    }

                } catch (Exception er) {
                    System.err.println(er);
                }


                // pWavePDS.mainFrame.createAnnotationInternalFrame("Annotation", anStPos, anEnPos);


            }


            if (pWavePDS.mousePosX1 != 0 && pWavePDS.mousePosX2 == 0) {

                int ms = new PixcelConversion().pixcelToMillisecond((int) pWavePDS.mousePosX1, pWavePDS.frames_per_pixel, (int) pWavePDS.audioInputStream.getFormat().getFrameRate());
                if (ms == 0) {
                    return;
                }

                int[] ann_pos = new GetAnnotation(pWavePDS.mainFrame.getConn()).getUniqueSelect(ms, pWavePDS.fileHashValue);
                if (ann_pos == null) {
                    return;
                }
                if (ann_pos.length != 2) {
                    return;
                }
                if (ann_pos[0] == 0 || ann_pos[1] == 1) {
                    return;
                }

                pWavePDS.mainFrame.createAnnotationInternalFrame("Annotation", ann_pos[0], ann_pos[1], pWavePDS.fileHashValue, pWavePDS);
                pWavePDS.mousePosX1 = 0;
            }
            allAnnReturnSetUp();
        } catch (Exception er) {
            System.err.println(er);
        }
    }

    private void calculatePixcel() {

        if (pWavePDS.mousePosX1 > pWavePDS.mousePosX2) {
            startPix = (int) pWavePDS.mousePosX2;
            endPix = (int) pWavePDS.mousePosX1;
        } else {
            startPix = (int) pWavePDS.mousePosX1;
            endPix = (int) pWavePDS.mousePosX2;
        }
    }

    private int getStartPixel() {
        return this.startPix;
    }

    private int getendPixel() {
        return this.endPix;
    }

    private void allAnnReturnSetUp() {
        pWavePDS.mousePosX1 = 0;
        pWavePDS.mousePosX2 = 0;
        pWavePDS.samplingGraph.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        pWavePDS.samplingGraph.repaint();
    }

    public void playFromSoundAll1() {
    }

    private void setStartSamples(int start) {
        this.startSam = start;
    }

    private void setEndSamples(int end) {
        this.endSam = end;
    }

    private int getStartSamples() {
        return this.startSam;
    }

    private int getEndSamples() {
        return this.endSam;
    }

    public boolean sourceAvailValidation() {
        if (pWavePDS.audioInputStream == null) {
            return false;
        }
        if (pWavePDS.streamBytes.getCurrent() == null) {
            return false;
        }
        if (pWavePDS.streamBytes.getCurrent().length < 10) {
            return false;
        }
        return true;
    }

    public String saveLocation() {
        String filePath = null;
        try {
            File file = new File(System.getProperty("user.dir"));
            JFileChooser fc = new JFileChooser(file);
            fc.setFileFilter(new javax.swing.filechooser.FileFilter() {
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    String name = f.getName();
                    if (name.endsWith(".wav")) {
                        return true;
                    }
                    return false;
                }

                public String getDescription() {
                    return ".wav";
                }
            });


            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                filePath = fc.getSelectedFile().toString();
            }
            if (filePath == null) {
                return null;
            }
            filePath = filePath.replace(".wav", "");
            filePath = filePath.replace(".WAV", "");
            filePath = filePath.replace(".", "");
            filePath = filePath + ".wav";

        } catch (SecurityException ex) {
            // JavaSound.showInfoDialog();
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return filePath;

    }

    private void saveFileAsSelected(String fileName, byte[] audiobytes) {
        try {
            if (audiobytes != null && fileName != null) {
                StreamConverter.byteTowavefile(audiobytes, pWavePDS.audioInputStream, fileName);
                audiobytes = null;
            }
        } catch (Exception er) {
            System.err.println(er.getMessage());
        }
    }

    private void deleteFile(String fileName) {
        try {
            File deletedName = new File(fileName);
            if (deletedName.exists()) {
                deletedName.delete();
            }
        } catch (Exception er) {
            System.err.println(er.getMessage());
        }
    }

    private AudioInputStream getSilenceFileStream() {
        AudioInputStream audioStreamArray = null;
        try {
            File sePds = new File("PDS/pds.wav");
            if (sePds.exists()) {
                audioStreamArray = AudioSystem.getAudioInputStream(sePds);
            }
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(PlotWavePDS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlotWavePDS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return audioStreamArray;
    }

    private void silenceFileSaveAs(String filepaths) {
        try {

            if (filepaths == null) {
                return;
            }


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PixcelConversion pixcelConvert = new PixcelConversion();
            int startMilliTime = 0, endMilliTime = 0, startRemove = 0, endRemove = 0, oldEndRemove = 10;
            if (pWavePDS.keyBuilder.getStartTimeList() != null) {
                for (int i = 0; i <= pWavePDS.keyBuilder.getStartTimeList().size(); i++) {


                    try {

                        try {
                            startRemove = pixcelConvert.milliSecondToPixcel(pWavePDS.keyBuilder.getStartTimeList().get(i).toString(), pWavePDS.frames_per_pixel, (int) pWavePDS.audioInputStream.getFormat().getFrameRate());
                            endRemove = pixcelConvert.milliSecondToPixcel(pWavePDS.keyBuilder.getEndTimeList().get(i).toString(), pWavePDS.frames_per_pixel, (int) pWavePDS.audioInputStream.getFormat().getFrameRate());
                            startMilliTime = oldEndRemove;
                            endMilliTime = startRemove;
                            oldEndRemove = endRemove;
                        } catch (NumberFormatException er) {
                            continue;
                        } catch (Exception er) {
                            continue;
                        }

                        pWavePDS.mousePosX1 = startMilliTime;
                        pWavePDS.mousePosX2 = endMilliTime;
                        if (((int) (pWavePDS.mousePosX1)) == 0) {

                            pWavePDS.mousePosX1 = 15;
                            continue;
                        }
                        if (((int) (pWavePDS.mousePosX2)) == 0) {
                            pWavePDS.mousePosX2 = 15;
                        }


                        if (sourceAvailValidation()) {
                            System.out.println("Test Play " + pWavePDS.mousePosX1 + "," + pWavePDS.mousePosX2);

                            try {
                                if (pWavePDS.mousePosX1 == 0 && pWavePDS.mousePosX2 == 0) {
                                    return;
                                }

                                getSamplingPositions();
                                int startSample = getStartSamples();
                                int endSample = getEndSamples();



                                CutAudioWave cutW = new CutAudioWave();
                                cutW.cutPortion(pWavePDS.streamBytes.getCurrent(), startSample, endSample);




                                if (cutW.getresultByteArray() == null) {
                                    return;
                                }


                                if (CutAudioInputStream.getCutWave() != null) {
                                    // AudioInputStream newInputStream = StreamConverter.byteTostream(CutAudioInputStream.getCutWave(), pWavePDS.audioInputStream);
                                    outputStream.write(CutAudioInputStream.getCutWave());
                                    CutAudioInputStream.setCutWave(null);
                                }


                            } catch (Exception ex) {
                                Logger.getLogger(RightClickEventPDS.class.getName()).log(Level.SEVERE, null, ex);
                            }


                        }



                    } catch (Exception ex) {
                        Logger.getLogger(PlotWavePDS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                StreamConverter.byteTowavefile(outputStream.toByteArray(), pWavePDS.audioInputStream, filepaths);

            }

        } catch (Exception ex) {
            Logger.getLogger(RightClickEventPDS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pdsFileSaveMethod() {

        try {

            if (pWavePDS.processMethod.startsWith("Combined")) {
                silenceFileSaveAs(saveLocation());
            } else {
                String fileName = saveLocation();
                AudioInputStream audioStream = getSilenceFileStream();
                if (audioStream == null) {
                    StreamConverter.byteTowavefile(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream, fileName);
                } else {
                    StreamConverter.streamTowavefile(fileName, audioStream);
                }
            }
        } catch (Exception er) {
        }


        /*    
        
         try {
         if (pWavePDS.processMethod.startsWith("Remove silence") || pWavePDS.processMethod.startsWith("Combined")) {
         silenceFileSaveAs(saveLocation());
         } else {

         String fileName = saveLocation();
         try {
         if (pWavePDS.mousePosX1 != 0 && pWavePDS.mousePosX2 != 0) {
         getSamplingPositions();
         int startSample = getStartSamples();
         int endSample = getEndSamples();

         System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWavePDS.frames_per_pixel);

         SelectedAudioWave selectW = new SelectedAudioWave();

         selectW.selectdPortion(pWavePDS.streamBytes.getCurrent(), startSample, endSample);

         if (selectW.getSelectedByteArray() == null) {
         return;
         }
         StreamConverter.byteTowavefile(selectW.getSelectedByteArray(), pWavePDS.audioInputStream, fileName);
         } else {
         if (pWavePDS.streamBytes.getCurrent() != null && fileName != null) {
         //Annotation
         getSamplingPositions();
         int startSample = getStartSamples();
         int endSample = getEndSamples();


         calculatePixcel();
         PixcelConversion pixConversion = new PixcelConversion();
         int startMs = pixConversion.pixcelToMillisecond(getStartPixel(), pWavePDS.frames_per_pixel, (int) pWavePDS.audioInputStream.getFormat().getFrameRate());
         int endMs = pixConversion.pixcelToMillisecond(getendPixel(), pWavePDS.frames_per_pixel, (int) pWavePDS.audioInputStream.getFormat().getFrameRate());


         FindAnnotated findAnnotation = new FindAnnotated(pWavePDS.mainFrame.getConn(), pWavePDS.fileHashValue, startMs, endMs);



         if (findAnnotation.isFileAnnotation()) {
         int option = javax.swing.JOptionPane.showConfirmDialog(pWavePDS.mainFrame, "Do you want to save annotation to " + fileName, "Save", JOptionPane.YES_NO_OPTION);
         if (option == JOptionPane.YES_OPTION) {
         File getFileName = new File(fileName);

         InsertAnnotation insertAnn = new InsertAnnotation(pWavePDS.mainFrame.getConn());
         if (!insertAnn.insertSaveASAnnotation(getFileName.getName(), pWavePDS.fileHashValue)) {
         javax.swing.JOptionPane.showMessageDialog(pWavePDS, "Unable to create label file", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
         }
         }
         }

         if (pWavePDS.processMethod.startsWith("Enhance") || pWavePDS.processMethod.startsWith("Combined")) {
         StreamConverter.byteTowavefile(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream, fileName);
         } else {
         AudioInputStream audioStream = getSilenceFileStream();
         if (audioStream == null) {
         StreamConverter.byteTowavefile(pWavePDS.streamBytes.getCurrent(), pWavePDS.audioInputStream, fileName);
         } else {
         StreamConverter.streamTowavefile(fileName, audioStream);
         }
         }


         }
         }
         } catch (Exception er) {
         Logger.getLogger(RightClickEventPDS.class.getName()).log(Level.SEVERE, null, er);
         }
         }
         } catch (Exception er) {
         Logger.getLogger(RightClickEventPDS.class.getName()).log(Level.SEVERE, null, er);
         } */

    }
}
