/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.panels;

import Speech.WavePanel.CutAudioWave;
import Speech.WavePanel.PlotWave;
import Speech.WavePanel.SelectedAudioWave;
import Speech.annotations.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import Speech.common.*;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.Stack;
import Speech.panels.NewPlotWavePanel;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Tatapower SED
 *
 */
public class PanelRightClickEvent {

    private NewPlotWavePanel pWave;
    private NewPlotWavePanel pWaveSda;
    private int startSam, endSam, startPix, endPix;
    private JMenu paste;
    private JMenu zoom;
    private JMenu play;
    private JMenu pds;
    private JMenu phoneme;
    private JMenu keywordMenu;
    private JMenu speakeridMenu;
    private JMenu annotation;
    private JMenu keywordAssam;
    private JMenu keywordHindi;
    private JMenuItem item;
    private JMenu manualProcess;
    private JMenu dummy;
    //private JMenu sendTo;
    private int copy_from_ms = 0, copy_to_ms = 0, ann_fLength, ann_oldfLength;
    private String ann_fName, ann_oldfName;
//:)    
    Stack<String> undoStack;
    Stack<String> redoStack;
    int _MAX_UNDO_REDO_SIZE = 5;
//(:

    public PanelRightClickEvent(NewPlotWavePanel pWave) {

        this.pWave = pWave;
        paste = new JMenu(" Paste ");
        paste.setFont(new Font("Courier New", Font.PLAIN, 15));
        paste.setBackground(Color.white);
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
        keywordMenu = new JMenu(" Keyword spotting ");
        keywordMenu.setFont(new Font("Courier New", Font.PLAIN, 15));
        keywordMenu.setBackground(Color.white);
        speakeridMenu = new JMenu(" Speaker identification ");
        speakeridMenu.setFont(new Font("Courier New", Font.PLAIN, 15));
        speakeridMenu.setBackground(Color.white);
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
        dummy = new JMenu(" Dummy ");
        dummy.setFont(new Font("Courier New", Font.PLAIN, 15));
        dummy.setBackground(Color.white);


//:)        
        undoStack = new Stack<String>();
        redoStack = new Stack<String>();
//(:



    }

    public void addMenuItems() {
        pWave.menu.removeAll();
        paste.removeAll();
        zoom.removeAll();
        play.removeAll();
        pds.removeAll();
        phoneme.removeAll();
        keywordMenu.removeAll();
        speakeridMenu.removeAll();
        annotation.removeAll();
        keywordAssam.removeAll();
        keywordHindi.removeAll();
        manualProcess.removeAll();
        dummy.removeAll();

        try {



            if (pWave.audioInputStream == null) {
                item = new JMenuItem(" Open ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        pWave.fileOpenMethod();
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                pWave.menu.add(item);

            }

            //Play Manu
            if (pWave.playback.line == null && pWave.audioInputStream != null) {
                //  pWave.menu.add(play);
            }

            item = new JMenuItem(" Play ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (pWave.playback.line == null && pWave.audioInputStream != null) {
                        if (sourceAvailValidation()) {
                            playSoundAll();
                        }
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            manualProcess.add(item);





            if (pWave.playback.line != null) {
                if (pWave.playback.line.isRunning() && pWave.playback.line.isOpen()) {
                    item = new JMenuItem(" Pause ");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (sourceAvailValidation()) {
                                pWave.pauseSound();
                            }
                        }
                    });


                    item.setFont(new Font("Courier New", Font.PLAIN, 15));
                    item.setBackground(Color.white);
                    manualProcess.add(item);
                }
            }

            if (pWave.playback.line != null) {
                if (!pWave.playback.line.isRunning() && pWave.playback.line.isOpen()) {
                    item = new JMenuItem(" Resume ");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (sourceAvailValidation()) {
                                pWave.resumeSound();
                            }
                        }
                    });


                    item.setFont(new Font("Courier New", Font.PLAIN, 15));
                    item.setBackground(Color.white);
                    manualProcess.add(item);
                }
            }


            if (pWave.playback.line != null) {
                if (pWave.playback.line.isRunning() || pWave.playback.line.isOpen() || pWave.playback.line.isActive()) {

                    item = new JMenuItem(" Stop ");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (sourceAvailValidation()) {
                                pWave.stopSound();
                            }
                        }
                    });


                    item.setFont(new Font("Courier New", Font.PLAIN, 15));
                    item.setBackground(Color.white);
                    manualProcess.add(item);
                }
            }

            //End playing portion

            //undo and redo

            if (pWave.audioInputStream != null && !undoStack.isEmpty()) {
                item = new JMenuItem(" Undo ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
//:)                            
                        saveFileAsSelected("REDOC", pWave.streamBytes.getCurrent());
                        String uFileName = undoStack.pop();
                        File undoFile = new File(uFileName);
//(:
                        if (!undoFile.exists()) {
                            return;
                        }
                        byte[] audioBytesArray = StreamConverter.wavefileToBytes(undoFile);
                        if (audioBytesArray == null) {
                            return;
                        }

                        pWave.setfileNameColor(Color.red);

                        pWave.streamBytes.setCurrent(audioBytesArray);
                        audioBytesArray = null;
                        pWave.setStreamDrawGraph();
//:)

                    }
                });

                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }

            if (pWave.audioInputStream != null && !redoStack.isEmpty()) {
                item = new JMenuItem(" Redo ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (sourceAvailValidation()) {
//:)    
                            saveFileAsSelected("UNDOC", pWave.streamBytes.getCurrent());
                            String rFileName = redoStack.pop();
                            File redoFile = new File(rFileName);
//(:
                            if (!redoFile.exists()) {
                                return;
                            }
                            byte[] audioBytesArray = StreamConverter.wavefileToBytes(redoFile);
                            if (audioBytesArray == null) {
                                return;
                            }

                            pWave.setfileNameColor(Color.red);

                            pWave.streamBytes.setCurrent(audioBytesArray);
                            audioBytesArray = null;
                            pWave.setStreamDrawGraph();

                        }
                    }
                });


                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);


            }

            //End undo and redo



            if (pWave.mousePosX1 != 0 && pWave.mousePosX2 != 0 && pWave.audioInputStream != null) {

                item = new JMenuItem(" Cut ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        cutWaveFile(true);
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }

            //End cut


            //Copy

            if (pWave.mousePosX1 != 0 && pWave.mousePosX2 != 0 && pWave.audioInputStream != null) {
                item = new JMenuItem(" Copy ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //  cutWave();
                            if (pWave.mousePosX1 == 0 && pWave.mousePosX2 == 0) {
                                return;
                            }
                            copy_from_ms = 0;
                            copy_to_ms = 0;
                            getSamplingPositions();
                            int startSample = getStartSamples();
                            int endSample = getEndSamples();

                            System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWave.frames_per_pixel);

                            CutAudioWave cutW = new CutAudioWave();
                            cutW.cutPortion(pWave.streamBytes.getCurrent(), startSample, endSample);

                            calculatePixcel();
                            PixcelConversion pixConversion = new PixcelConversion();
                            copy_from_ms = pixConversion.pixcelToMillisecond(getStartPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                            copy_to_ms = pixConversion.pixcelToMillisecond(getendPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());


                            if (cutW.getresultByteArray() == null) {
                                return;
                            }



                        } catch (Exception ex) {
                            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }
            //End copy

            //[paste]
            if (CutAudioInputStream.getCutWave() != null && pWave.audioInputStream != null) {
                item = new JMenuItem(" Paste ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (CutAudioInputStream.getCutWave() != null) {
                            AudioInputStream newInputStream = StreamConverter.byteTostream(CutAudioInputStream.getCutWave(), pWave.audioInputStream);

                            // FindAnnotated findAnnotation = new FindAnnotated(pWave.mainFrame.getConn(), pWave.fileName, copy_from_ms, copy_to_ms);
                           // ArrayList ann_id = new FindAnnotated(pWave.mainFrame.getConn(), Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), copy_from_ms, copy_to_ms).getAnnotationID();

                            String newAnn_file = Hash.getHashValue(newInputStream);
                            newInputStream = StreamConverter.byteTostream(CutAudioInputStream.getCutWave(), pWave.audioInputStream);
                    //        new InsertAnnotation(pWave.mainFrame.getConn()).insertCopySAnnotation(newAnn_file, copy_from_ms, 0, ann_id);
                    //        pWave.mainFrame.createCopyPanel("Copy/Paste", newInputStream);

                            CutAudioInputStream.setCutWave(null);
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }
            // [End paste]

            //Delete
            if (pWave.mousePosX1 != 0 && pWave.mousePosX2 != 0 && pWave.audioInputStream != null) {

                item = new JMenuItem(" Delete ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (sourceAvailValidation()) {
                            if (pWave.mousePosX1 == 0 && pWave.mousePosX2 == 0) {
                                return;
                            }
                            cutWaveFile(false);
                            /* 
                             getSamplingPositions();
                             int startSample = getStartSamples();
                             int endSample = getEndSamples();
                             waveDelete(startSample, endSample);*/
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }


            //Delete

            //Crop

            if (pWave.mousePosX1 != 0 && pWave.mousePosX2 != 0 && pWave.audioInputStream != null) {

                item = new JMenuItem(" Crop ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cropWaveFile();
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }


            //End crop


            if (pWave.audioInputStream != null && (pWave.mousePosX1 == 0 || pWave.mousePosX2 == 0)) {

                item = new JMenuItem(" Save (Workspace) ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (sourceAvailValidation()) {
                            saveFile("saved");
                        }

                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }


            //Save As...

            if (pWave.audioInputStream != null) {

                item = new JMenuItem(" Save As... ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (sourceAvailValidation()) {
                            if (pWave.mousePosX1 != 0 && pWave.mousePosX2 != 0) {
                                getSamplingPositions();
                                int startSample = getStartSamples();
                                int endSample = getEndSamples();

                                CutAudioWave cutW = new CutAudioWave();
                                cutW.cutPortion(pWave.streamBytes.getCurrent(), startSample, endSample);

                                if (cutW.getresultByteArray() == null) {
                                    return;
                                }
                                if (CutAudioInputStream.getCutWave() == null) {
                                    return;
                                }
                                String filelocName = saveLocation();
                                StreamConverter.byteTowavefile(CutAudioInputStream.getCutWave(), pWave.audioInputStream, filelocName);

                                CutAudioInputStream.setCutWave(null);

                            } else {

                                saveFile("saveAs");
                            }
                        }

                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }



            if (pWave.audioInputStream != null && (pWave.mousePosX1 == 0 || pWave.mousePosX2 == 0)) {

                item = new JMenuItem(" Save to server ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (sourceAvailValidation()) {
                            sendToServer();
                        }

                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }


            //Zoom Menu

            manualProcess.add(zoom);



            //End

            //[Select all] Added by Lok

            if (pWave.audioInputStream != null && (pWave.mousePosX1 == 0 || pWave.mousePosX2 == 0)) {

                item = new JMenuItem(" Select All ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // action performed
                        try {

                            int inPos = (int) pWave.mousePosX1, enPos = (int) ((pWave.samplingGraph.getSize().width) - 10);

                            if (inPos < 1) {
                                inPos = 1;
                            }

                            if (enPos < 1) {
                                enPos = 1;
                            }
                            if (inPos > (enPos - 10)) {
                                inPos = enPos - 10;
                            }
                            //double bytesPerSecond = pWave.audioInputStream.getFormat().getFrameSize() * (double) pWave.audioInputStream.getFormat().getFrameRate();
                            int startTime = ((inPos * pWave.frames_per_pixel) * pWave.audioInputStream.getFormat().getFrameSize() - 118);
                            int endTime = ((enPos * pWave.frames_per_pixel) * pWave.audioInputStream.getFormat().getFrameSize());
                            System.out.println("CK " + ((inPos * pWave.frames_per_pixel) / pWave.audioInputStream.getFormat().getFrameRate()) * 1000 + " " + ((enPos * pWave.frames_per_pixel) / pWave.audioInputStream.getFormat().getFrameRate()) * 1000);


                            pWave.mousePosX1 = startTime;
                            pWave.mousePosX2 = endTime;

                            pWave.samplingGraph.repaint();
                        } catch (Exception er) {
                            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, er.toString() + "20");
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }

            if (pWave.audioInputStream != null && (pWave.mousePosX1 == 0 || pWave.mousePosX2 == 0)) {
                item = new JMenuItem(" Merge here ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        mergeFileMathod();
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);

            }
            //Merger file



            //[End Select All]

            pWave.menu.add(manualProcess);

            //Annotation
       /* if (pWave.audioInputStream != null) {
             pWave.menu.add(annotation);
             }*/

            item = new JMenuItem(" Add to dummy ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        //-- modified by Lok
                        if (pWave.mousePosX1 == 0 && pWave.mousePosX2 == 0) {
                            return;
                        }
                        getSamplingPositions();
                        int startSample = getStartSamples();
                        int endSample = getEndSamples();

                        if (pWave.dummyList.size() < 1) {
                            ann_fName = null;
                            ann_oldfName = null;
                            ann_fLength = 0;
                            ann_oldfLength = 0;
                        }

                        pWave.dummyList.add(Arrays.asList(startSample, endSample));
                        //---
                        if (!pWave.dummyList.isEmpty()) {
                            try {
                                CutAudioWave cutW = new CutAudioWave();
                                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                for (int dum = 0; dum < 1; dum++) {
                                    try {

                                        cutW.cutPortion(pWave.streamBytes.getCurrent(), startSample, endSample);

                                        if (cutW.getresultByteArray() == null) {
                                            return;
                                        }
                                        if (pWaveSda != null) {
                                            if (pWaveSda.streamBytes != null) {
                                                if (pWaveSda.streamBytes.getCurrent() != null) {
                                                    outputStream.write(pWaveSda.streamBytes.getCurrent());
                                                }
                                            }
                                        }
                                        outputStream.write(CutAudioInputStream.getCutWave());

                                    } catch (IOException ex) {
                                        Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, null, ex + "2");
                                    }

                                }
                                CutAudioInputStream.setCutWave(null);
                                AudioInputStream newInputStream = StreamConverter.byteTostream(outputStream.toByteArray(), pWave.audioInputStream);
                                //pWave.mainFrame.createCopyPanel("Dummy Panel", newInputStream);

                                //create Annotation

                                ann_fName = Hash.getHashValue(newInputStream);
                                newInputStream = StreamConverter.byteTostream(outputStream.toByteArray(), pWave.audioInputStream);
                                ann_fLength = (int) ((newInputStream.getFrameLength() * 1000) / newInputStream.getFormat().getFrameRate());

                                if (ann_oldfName != null && ann_oldfLength > 0) {
                               //     new UpdateAnnotation(pWave.mainFrame.getConn()).updateAnnotationFileName(ann_oldfName, ann_fName);
                                }
                                calculatePixcel();
                                PixcelConversion pixConversion = new PixcelConversion();
                                int from_ms = pixConversion.pixcelToMillisecond(getStartPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                                int to_ms = pixConversion.pixcelToMillisecond(getendPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                                String ann_fileName = Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
                             //   ArrayList ann_id = new FindAnnotated(pWave.mainFrame.getConn(), ann_fileName, from_ms, to_ms).getAnnotationID();
                             //   System.out.println("\n\n\n*********************\n" + ann_id);


                             //   new InsertAnnotation(pWave.mainFrame.getConn()).insertCopySAnnotation(ann_fName, from_ms, ann_oldfLength, ann_id);

                                ann_oldfName = ann_fName;
                                ann_oldfLength = ann_fLength;

                                if (pWave.dummyList.size() == 1) {
                                    try {
                                        pWaveSda = new NewPlotWavePanel(pWave.mainFrame, newInputStream, "Dummy Panel");
                                     //   InternalFrame soundImprove = new InternalFrame(false, true, true, pWave.mainFrame);
                                    //    soundImprove.setDummyList((ArrayList) pWave.dummyList);
                                    //    soundImprove.setTitle("Dummy Panel");
                                   //     soundImprove.setVisible(true);
                                    //    soundImprove.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn2.png")));
                                    //    pWave.mainFrame.screenproperty.addSoundImprovePanel(soundImprove);
                                    //    soundImprove.add(pWaveSda);
                                    //    pWave.mainFrame.jDesktopPane1.add(soundImprove);
                                        pWave.mainFrame.screenproperty.resizeScreen(pWave.mainFrame);
                                    } catch (Exception er) {
                                        Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, er.toString() + "21");
                                    }
                                } else {
                                    //ByteArrayOutputStream dummyoutputStream = new ByteArrayOutputStream();
                                    //dummyoutputStream.write(pWaveSda.streamBytes.getCurrent());
                                    pWaveSda.streamBytes.setCurrent(outputStream.toByteArray());
                                    pWaveSda.createAudioInputStream(null, newInputStream, true);
                                    pWaveSda.scrollWaveFromRemote();
                                    // pWaveSda.samplingGraph.createWaveForm(outputStream.toByteArray());

                                }


                            } catch (Exception er) {
                                Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, er.toString() + "22");
                            }
                        }
                        //--



                        //----------------
                    } catch (Exception ex) {
                        Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, null, ex + "3");
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            manualProcess.add(item);

            /*   if (pWave.dummyList != null) {
             if (pWave.dummyList.size() > 0) {
             item = new JMenuItem(" Clear dummy ");
             item.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             try {
             pWave.dummyList.clear();
             } catch (Exception ex) {
             Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
             }
             }
             });
             item.setFont(new Font("Courier New", Font.PLAIN, 15));
             item.setBackground(Color.white);
             pWave.menu.add(item);
             }
             }*/

            item = new JMenuItem(" Create label ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (pWave.audioInputStream != null) {
                        createAnnotation();
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            pWave.menu.add(item);






            //PDS

            pWave.menu.add(pds);


            item = new JMenuItem(" Remove silence ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
//                        pWave.mainFrame.createSoundImprovePanel("Remove silence", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
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
                  //      pWave.mainFrame.createSoundImprovePanel("Remove noise", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
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
                      //  pWave.mainFrame.createSoundImprovePanel("Enhance speech", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
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
                      //  pWave.mainFrame.createSoundImprovePanel("Combined", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            pds.add(item);





            //Phoneme

            pWave.menu.add(phoneme);


            //KWS
            pWave.menu.add(keywordMenu);

            item = new JMenuItem(" Speaker Identification ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                  //  pWave.mainFrame.createIvectorInternalFrame("Speaker Identification", "word/Assamese/part2");

                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            pWave.menu.add(item);

            //[end speaker Identification]

            // pWave.menu.add(speakeridMenu);

            //pWave.menu.add(speakeridMenu);



            //Phoneme

            item = new JMenuItem(" Assamese ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                    //    pWave.mainFrame.createPhenomeInternalFrame("Phenome analysis (Assamese)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Assamese", null);
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
                 //       pWave.mainFrame.createPhenomeInternalFrame("Phenome analysis (Hindi)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Hindi", null);
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
                   //     pWave.mainFrame.createPhenomeInternalFrame("Phenome analysis (Telugu)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Telugu", null);
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
                   //     pWave.mainFrame.createPhenomeInternalFrame("Phenome analysis (English)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "English", null);
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
                  //      pWave.mainFrame.createKwsInternalFrame("Keyword spotting (Telugu)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Telugu");
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
                  //      pWave.mainFrame.createKwsInternalFrame("Keyword spotting (English)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "English");
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            keywordMenu.add(item);

            //Assames

            item = new JMenuItem(" Phoneme Based  ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                  //      pWave.mainFrame.createKwsInternalFrame("Keyword spotting (Assamese Part-1)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Assamese_part1");
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            keywordAssam.add(item);


            item = new JMenuItem(" Word Based ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                  //      pWave.mainFrame.createKwsInternalFrame("Keyword spotting (Assamese Part-2)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Assamese_part2");
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
                 //       pWave.mainFrame.createKwsInternalFrame("Keyword spotting (Hindi Part-1)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Hindi_part1");
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
                //        pWave.mainFrame.createKwsInternalFrame("Keyword spotting (Hindi Part-2)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Hindi_part1");
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            keywordHindi.add(item);


            //[speaker Identification]

            item = new JMenuItem(" i - vector ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

              //      pWave.mainFrame.createIvectorInternalFrame("Speaker Identification", "word/Assamese/part2");

                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            speakeridMenu.add(item);

            //[paste]

            item = new JMenuItem(" Paste ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    if (CutAudioInputStream.getCutWave() != null) {
                        AudioInputStream newInputStream = StreamConverter.byteTostream(CutAudioInputStream.getCutWave(), pWave.audioInputStream);
                   //     pWave.mainFrame.createCopyPanel("Copy Panel", newInputStream);
                        CutAudioInputStream.setCutWave(null);
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            paste.add(item);


            item = new JMenuItem(" Normal ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        pWave.setStreamDrawGraph();
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
                        pWave.setZoomIn();
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
                        pWave.setZoomOut();
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            zoom.add(item);



        } catch (NullPointerException er) {
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, er.getMessage().toString() + "28");
        }
    }

    private void getSamplingPositions() {
        try {
            int width = pWave.samplingGraph.getSize().width;
            int inPos = (int) pWave.mousePosX1, enPos = (int) pWave.mousePosX2;

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

            int startTime = ((inPos * pWave.frames_per_pixel) * pWave.audioInputStream.getFormat().getFrameSize());
            int endTime = ((enPos * pWave.frames_per_pixel) * pWave.audioInputStream.getFormat().getFrameSize());
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
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, er.toString());
        }

    }

    public void playSoundAll() {
        try {

            if (pWave.playback.line != null) {
                if (pWave.playback.line.isRunning() || pWave.playback.line.isOpen()) {
                    return;
                }
            }

            if (pWave.mousePosX1 != 0 && pWave.mousePosX2 != 0) {
                getSamplingPositions();
                int startSample = getStartSamples();
                int endSample = getEndSamples();

                System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWave.frames_per_pixel);

                SelectedAudioWave selectW = new SelectedAudioWave();
                selectW.selectdPortion(pWave.streamBytes.getCurrent(), startSample, endSample);

                if (selectW.getSelectedByteArray() == null) {
                    return;
                }
                pWave.streamBytes.setSelectedPlay(selectW.getSelectedByteArray());
                pWave.selectedPlay = true;
                pWave.streamVariable.setSecondStarts(startSample / pWave.audioInputStream.getFormat().getFrameRate());
                pWave.playSound();
            } else if (pWave.mousePosX1 != 0) {

                int inPos = (int) pWave.mousePosX1, enPos = (int) ((pWave.samplingGraph.getSize().width) - 10);

                if (inPos < 1) {
                    inPos = 1;
                }

                if (enPos < 1) {
                    enPos = 1;
                }
                if (inPos > (enPos - 10)) {
                    inPos = enPos - 10;
                }

                double bytesPerSecond = pWave.audioInputStream.getFormat().getFrameSize() * (double) pWave.audioInputStream.getFormat().getFrameRate();
                int startTime = ((inPos * pWave.frames_per_pixel) * pWave.audioInputStream.getFormat().getFrameSize());
                int endTime = ((enPos * pWave.frames_per_pixel) * pWave.audioInputStream.getFormat().getFrameSize());
                System.out.println("CK " + ((inPos * pWave.frames_per_pixel) / pWave.audioInputStream.getFormat().getFrameRate()) * 1000 + " " + ((enPos * pWave.frames_per_pixel) / pWave.audioInputStream.getFormat().getFrameRate()) * 1000);
                int startSample, endSample;
                if (startTime > endTime) {
                    startSample = endTime;
                    endSample = startTime;
                } else {
                    startSample = startTime;
                    endSample = endTime;
                }

                System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWave.frames_per_pixel);

                SelectedAudioWave selectW = new SelectedAudioWave();
                selectW.selectdPortion(pWave.streamBytes.getCurrent(), startSample, endSample);

                if (selectW.getSelectedByteArray() == null) {
                    return;
                }
                pWave.streamBytes.setSelectedPlay(selectW.getSelectedByteArray());
                pWave.selectedPlay = true;
                pWave.streamVariable.setSecondStarts(startSample / pWave.audioInputStream.getFormat().getFrameRate());
                pWave.playSound();
            } else {
                pWave.selectedPlay = false;
                pWave.playSound();
            }
        } catch (Exception er) {
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, er.toString() + "11");
        }
    }

    public void createAnnotation() {
        try {
            if (pWave.mousePosX1 != 0 && pWave.mousePosX2 != 0) {
                PixcelConversion pixConversion = new PixcelConversion();
                int anStPos = 0;
                int anEnPos = 0;
                if (pWave.mousePosX1 > pWave.mousePosX2) {
                    anStPos = (int) pWave.mousePosX2;
                    anEnPos = (int) pWave.mousePosX1;
                } else {
                    anStPos = (int) pWave.mousePosX1;
                    anEnPos = (int) pWave.mousePosX2;
                }
                System.out.println("TY " + anStPos);
                anStPos = pixConversion.pixcelToMillisecond(anStPos, pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                anEnPos = pixConversion.pixcelToMillisecond(anEnPos, pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                System.out.println("TY " + anStPos);
                String ann_fileName = Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
            //    FindAnnotated findAnn = new FindAnnotated(pWave.mainFrame.getConn(), ann_fileName, anStPos, anEnPos);

              //  if (findAnn.findAnnationAll()) {
               //     javax.swing.JOptionPane.showMessageDialog(pWave, "Label already created :" + findAnn.findAnnationAll(), "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
              //      return;
             //   }

             //   pWave.mainFrame.createAnnotationInternalFrame("Annotation", anStPos, anEnPos, ann_fileName, pWave);
            }
        } catch (Exception er) {
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, er.toString() + "12");
        }

    }

    public void resizeandAddannotation() {
        try {
            if (pWave.mousePosX1 != 0 && pWave.mousePosX2 != 0 && pWave.mousePosY1 > 160) {
                try {
                    PixcelConversion pixConversion = new PixcelConversion();
                    int anStPos = 0;
                    int anEnPos = 0;
                    if (pWave.mousePosX1 > pWave.mousePosX2) {
                        anStPos = (int) pWave.mousePosX2;
                        anEnPos = (int) pWave.mousePosX1;
                    } else {
                        anStPos = (int) pWave.mousePosX1;
                        anEnPos = (int) pWave.mousePosX2;
                    }
                    anStPos = pixConversion.pixcelToMillisecond(anStPos, pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                    anEnPos = pixConversion.pixcelToMillisecond(anEnPos, pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());

                    String ann_fileName = Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
                   // FindAnnotated findAnn = new FindAnnotated(pWave.mainFrame.getConn(), ann_fileName, anStPos, anEnPos);

                    /*if (findAnn.isAnnotatedBothPosiotion() == true) {
                        javax.swing.JOptionPane.showMessageDialog(pWave, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);

                        return;
                    }

                    if (!findAnn.findResizeAnnation()) {
                        javax.swing.JOptionPane.showMessageDialog(pWave, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }
                    if (findAnn.findNumberOfMatch() > 1) {
                        javax.swing.JOptionPane.showMessageDialog(pWave, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }
                    if (findAnn.getAnnotationStartPos() == 0 || findAnn.getAnnotationEndPos() == 0) {
                        javax.swing.JOptionPane.showMessageDialog(pWave, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }

                    int ann_actualStPos = findAnn.getAnnotationStartPos();
                    int ann_actualEnPos = findAnn.getAnnotationEndPos();
                    String ann_allow = findAnn.getAllow();
                    String ann_level = findAnn.getLevel();*/

                    int dragStPos = (int) pWave.mousePosX1;
                    int dragEnPos = (int) pWave.mousePosX2;

                 //   int ann_actualStPixPos = pixConversion.milliSecondToPixcel(Integer.toString(ann_actualStPos), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                 //   int ann_actualEnPixPos = pixConversion.milliSecondToPixcel(Integer.toString(ann_actualEnPos), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());

                 /*   if (!(Math.abs(dragStPos - ann_actualStPixPos) < 15 || Math.abs(dragStPos - ann_actualEnPixPos) < 15)) {
                        javax.swing.JOptionPane.showMessageDialog(pWave, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }
                    if ((Math.abs(dragStPos - ann_actualStPixPos)) < 15) {
                        if (dragEnPos > ann_actualEnPixPos) {
                            javax.swing.JOptionPane.showMessageDialog(pWave, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            allAnnReturnSetUp();
                            return;
                        }


                    } else {
                        if (dragEnPos < ann_actualStPixPos) {
                            javax.swing.JOptionPane.showMessageDialog(pWave, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            allAnnReturnSetUp();
                            return;
                        }
                    }*/
                 /*   if (ann_allow != null) {
                        ann_allow = ann_allow.trim();
                        try {
                            if (Integer.parseInt(ann_allow) > pWave.mainFrame.getUserRoll()) {
                                javax.swing.JOptionPane.showMessageDialog(pWave, "Senior has verified this region", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                                allAnnReturnSetUp();
                                return;
                            }
                        } catch (NumberFormatException er) {
                            System.err.println(er);
                        }
                    }*/

                  /*  if ((Math.abs(dragStPos - ann_actualStPixPos)) < 15) {
                        int changeStarPos = pixConversion.pixcelToMillisecond(dragEnPos, pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                        int changeEndPos = ann_actualEnPos;
                        int option = JOptionPane.showConfirmDialog(pWave, "Are you sure you want to update ", "Update", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {

                            boolean updated = new UpdateAnnotation(pWave.mainFrame.getConn()).resizeUpdate(ann_actualStPos, ann_actualEnPos, changeStarPos, changeEndPos, ann_fileName, pWave.mainFrame.getUserID(), ann_level);
                            if (!updated) {
                                javax.swing.JOptionPane.showMessageDialog(pWave, "Unable to update", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            } else {
                                pWave.samplingGraph.createWaveForm(null);
                                pWave.mainFrame.pWave.loadWaveFromRemote();
                                int changeStrarPixPos = pixConversion.milliSecondToPixcel(Integer.toString(changeStarPos), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                                pWave.mainFrame.pWave.scrollWaveFromRemote(changeStrarPixPos);
                            }
                        }
                    } else {
                        int changeStarPos = ann_actualStPos;
                        int changeEndPos = pixConversion.pixcelToMillisecond(dragEnPos, pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                        int option = JOptionPane.showConfirmDialog(pWave, "Are you sure you want to update ", "Update", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            boolean updated = new UpdateAnnotation(pWave.mainFrame.getConn()).resizeUpdate(ann_actualStPos, ann_actualEnPos, changeStarPos, changeEndPos, ann_fileName, pWave.mainFrame.getUserID(), ann_level);
                            if (!updated) {
                                javax.swing.JOptionPane.showMessageDialog(pWave, "Unable to update", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            } else {
                                pWave.samplingGraph.createWaveForm(null);
                                pWave.mainFrame.pWave.loadWaveFromRemote();
                                int changeStrarPixPos = pixConversion.milliSecondToPixcel(Integer.toString(changeStarPos), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                                pWave.mainFrame.pWave.scrollWaveFromRemote(changeStrarPixPos);
                            }
                        }
                    }*/

                } catch (Exception er) {
                    System.err.println(er);
                }


                // pWave.mainFrame.createAnnotationInternalFrame("Annotation", anStPos, anEnPos);


            }

            if (pWave.mousePosX1 != 0 && pWave.mousePosX2 == 0) {

                int ms = new PixcelConversion().pixcelToMillisecond((int) pWave.mousePosX1, pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                if (ms == 0) {
                    return;
                }
                String ann_fileName = Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
             //   int[] ann_pos = new GetAnnotation(pWave.mainFrame.getConn()).getUniqueSelect(ms, ann_fileName);
             //   if (ann_pos == null) {
              //      return;
              //  }
             //   if (ann_pos.length != 2) {
             //       return;
             //   }
            //    if (ann_pos[0] == 0 || ann_pos[1] == 1) {
             //       return;
            //    }

               // pWave.mainFrame.createAnnotationInternalFrame("Annotation", ann_pos[0], ann_pos[1], ann_fileName, pWave);
                pWave.mousePosX1 = 0;
            }
            allAnnReturnSetUp();
        } catch (Exception er) {
            System.err.println(er);
        }
    }

    private void calculatePixcel() {

        if (pWave.mousePosX1 > pWave.mousePosX2) {
            startPix = (int) pWave.mousePosX2;
            endPix = (int) pWave.mousePosX1;
        } else {
            startPix = (int) pWave.mousePosX1;
            endPix = (int) pWave.mousePosX2;
        }
    }

    private int getStartPixel() {
        return this.startPix;
    }

    private int getendPixel() {
        return this.endPix;
    }

    private void allAnnReturnSetUp() {
        pWave.mousePosX1 = 0;
        pWave.mousePosX2 = 0;
        pWave.samplingGraph.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        pWave.samplingGraph.repaint();
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
        if (pWave.audioInputStream == null) {
            return false;
        }
        if (pWave.streamBytes.getCurrent() == null) {
            return false;
        }
        if (pWave.streamBytes.getCurrent().length < 10) {
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
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return filePath;

    }

    public void deleteWaVeFiles() {
        if (sourceAvailValidation()) {
            if (pWave.mousePosX1 == 0 && pWave.mousePosX2 == 0) {
                return;
            }
            getSamplingPositions();
            int startSample = getStartSamples();
            int endSample = getEndSamples();
            
        }
    }

  

    public void saveFile() {
        try {
            /*  if (pWave.audioInputStream != null) {
             // String fileName = pWave.abbfilePath;
             if (pWave.fileName.endsWith("*")) {
             pWave.fileName = pWave.fileName.substring(0, pWave.fileName.length() - 1);
             }
             pWave.setfileNameColor(Color.black);
             if (pWave.streamBytes.getCurrent() != null && fileName != null) {
             StreamConverter.byteTowavefile(pWave.streamBytes.getCurrent(), pWave.audioInputStream, fileName);
             }
             }*/
        } catch (Exception er) {
            System.err.println(er.getMessage());
        }
    }

    private void saveFileAsSelected(String task, byte[] audiobytes) {
        System.out.println(" us " + undoStack.size() + " rs " + redoStack.size());
        if (task.startsWith("UNDOC")) {
            if (undoStack.size() >= _MAX_UNDO_REDO_SIZE) {
                undoStack.remove(0);
            }
            String fileName = "conf/temp/undo" + undoStack.size() + ".wav";
            undoStack.push(fileName);
            try {
                if (audiobytes != null && fileName != null) {
                    StreamConverter.byteTowavefile(audiobytes, pWave.audioInputStream, fileName);
                    audiobytes = null;
                }
            } catch (Exception er) {
                System.err.println(er.getMessage());
            }

        } else if (task.startsWith("REDOC")) {
            String fileName = "conf/temp/redo" + redoStack.size() + ".wav";
            redoStack.push(fileName);
            try {
                if (audiobytes != null && fileName != null) {
                    StreamConverter.byteTowavefile(audiobytes, pWave.audioInputStream, fileName);
                    audiobytes = null;
                }
            } catch (Exception er) {
                System.err.println(er.getMessage());
            }

        } else {
            try {
                if (audiobytes != null && task != null) {
                    StreamConverter.byteTowavefile(audiobytes, pWave.audioInputStream, task);
                    audiobytes = null;
                }
            } catch (Exception er) {
                System.err.println(er.getMessage());
            }

        }
    }

    public void cutWaveFile(boolean method) {
        try {


            if (pWave.mousePosX1 != 0 && pWave.mousePosX2 != 0 && pWave.audioInputStream != null) {



                //  cutWave();
                if (pWave.mousePosX1 == 0 && pWave.mousePosX2 == 0) {
                    return;
                }

                //Get selection region Pixcel and milli Seconds

                getSamplingPositions();
                int startSample = getStartSamples();
                int endSample = getEndSamples();

                calculatePixcel();
                PixcelConversion pixConversion = new PixcelConversion();
                int startMs = pixConversion.pixcelToMillisecond(getStartPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                int endMs = pixConversion.pixcelToMillisecond(getendPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());


                //Find annotations are available in the selection region 

            //    FindAnnotated findAnnotation = new FindAnnotated(pWave.mainFrame.getConn(), Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), startMs, endMs);


                //if annotation is occuring in the selection region do not allow to cut this region
             //   if (findAnnotation.findAnnationAll()) {
             //       javax.swing.JOptionPane.showMessageDialog(pWave, "Can not be cut/delete this region");
             //       return;
            //    }

                // Do process of selection cutting
                //saveFileAsSelected("conf/temp/undo.wav", pWave.streamBytes.getCurrent());
                CutAudioWave cutW = new CutAudioWave();
                cutW.cutPortion(pWave.streamBytes.getCurrent(), startSample, endSample);

                if (cutW.getresultByteArray() == null) {
                    return;
                }

                // Hash function for current file and newly created file

                String hash_current_name = Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
                String hash_new_name = Hash.getHashValue(StreamConverter.byteTostream(cutW.getresultByteArray(), pWave.audioInputStream));

                // Get All annotation of before selection and after selection regions

              //  ArrayList ann_id_beforeselection = new FindAnnotated(pWave.mainFrame.getConn(), hash_current_name, 0, endMs).getAnnotationID();

              //  ArrayList ann_id_afterselection = new FindAnnotated(pWave.mainFrame.getConn(), hash_current_name, endMs, endMs).getStartPointAnnotationID();

                // Insert Annotation to New file 
                //insert from 0 pos to start selection pos in new file

            //    boolean inserted = new InsertAnnotation(pWave.mainFrame.getConn()).insertCopySAnnotation(hash_new_name, 0, 0, ann_id_beforeselection);
                //insert from endpart of selection to file length (startpos - selection)+length
            //    inserted = new InsertAnnotation(pWave.mainFrame.getConn()).insertCopySAnnotation(hash_new_name, endMs, startMs, ann_id_afterselection);

                //Display portion

                saveFileAsSelected("UNDOC", pWave.streamBytes.getCurrent());

                pWave.setfileNameColor(Color.red);

                pWave.streamBytes.setCurrent(cutW.getresultByteArray());

                if (!method) {
                    CutAudioInputStream.setCutWave(null);
                }
                copy_from_ms = 0;
                copy_to_ms = 0;

                pWave.setStreamDrawGraph();
                pWave.mousePosX1 = 0;
                pWave.mousePosX2 = 0;
                // saveFile();

            }
        } catch (Exception ex) {
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cropWaveFile() {
        try {


            if (pWave.mousePosX1 != 0 && pWave.mousePosX2 != 0 && pWave.audioInputStream != null) {



                //  cutWave();
                if (pWave.mousePosX1 == 0 && pWave.mousePosX2 == 0) {
                    return;
                }

                //Get selection region Pixcel and milli Seconds

                getSamplingPositions();
                int startSample = getStartSamples();
                int endSample = getEndSamples();

                calculatePixcel();
                PixcelConversion pixConversion = new PixcelConversion();
                int startMs = pixConversion.pixcelToMillisecond(getStartPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                int endMs = pixConversion.pixcelToMillisecond(getendPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());



                // Do process of selection cutting
                //saveFileAsSelected("conf/temp/undo.wav", pWave.streamBytes.getCurrent());
                CutAudioWave cutW = new CutAudioWave();
                cutW.cutPortion(pWave.streamBytes.getCurrent(), startSample, endSample);

                if (cutW.getresultByteArray() == null) {
                    return;
                }

                // Hash function for current file and newly created file

                String hash_current_name = Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
                String hash_new_name = Hash.getHashValue(StreamConverter.byteTostream(CutAudioInputStream.getCutWave(), pWave.audioInputStream));

                // Get All annotation of before selection and after selection regions

       //         ArrayList ann_id_selection = new FindAnnotated(pWave.mainFrame.getConn(), hash_current_name, startMs, endMs).getAnnotationID();


                // Insert Annotation to New file 
                //insert from 0 pos to start selection pos in new file

          //      boolean inserted = new InsertAnnotation(pWave.mainFrame.getConn()).insertCopySAnnotation(hash_new_name, startMs, 0, ann_id_selection);
                //insert from endpart of selection to file length (startpos - selection)+length

                //Display portion
                saveFileAsSelected("UNDOC", pWave.streamBytes.getCurrent());

                pWave.setfileNameColor(Color.red);

                pWave.streamBytes.setCurrent(CutAudioInputStream.getCutWave());


                CutAudioInputStream.setCutWave(null);


                pWave.setStreamDrawGraph();
                pWave.mousePosX1 = 0;
                pWave.mousePosX2 = 0;
                //saveFile();



            }
        } catch (Exception ex) {
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveFile(String method) {
        try {
            if (pWave.audioInputStream != null) {


                if (method.startsWith("saved")) {


                    pWave.setfileNameColor(Color.black);

                    StreamConverter.byteTowavefile(pWave.streamBytes.getCurrent(), pWave.audioInputStream, "conf/workspace/" + pWave.fileName);


                } else {

                    String fileName = saveLocation();
                    StreamConverter.byteTowavefile(pWave.streamBytes.getCurrent(), pWave.audioInputStream, fileName);
                    File nFile = new File(fileName);
                    if (nFile.exists()) {
                        pWave.fileName = nFile.getName();
                        pWave.setfileNameColor(Color.black);

                    }

                }


            }
        } catch (Exception er) {
            System.err.println(er.getMessage());
        }
    }

    private void sendToServer() {
        try {
            // pWave.setfileNameColor(Color.black);

            StreamConverter.byteTowavefile(pWave.streamBytes.getCurrent(), pWave.audioInputStream, "conf/workspace/" + pWave.fileName);
            File workspacefile = new File("conf/workspace/" + pWave.fileName);
            if (workspacefile.exists()) {
                String current_hash_name = Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
            //    pWave.mainFrame.createSendServerFile("conf/workspace/" + pWave.fileName, current_hash_name, false);

            }

        } catch (Exception er) {
            System.err.println(er.getMessage());
        }
    }

    private void deleteFile1(String fileName) {
        try {
            File deletedName = new File(fileName);
            if (deletedName.exists()) {
                deletedName.delete();
            }
        } catch (Exception er) {
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, er.toString() + "19");
        }
    }

    public void mergeFileMathod() {
        try {
            File wavefile = null;
            File fileDir = new File(System.getProperty("user.dir"));
            JFileChooser fc = new JFileChooser(fileDir);


            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                long start = System.currentTimeMillis();

                String source_file_name = (fc.getSelectedFile().getName()).toLowerCase();//eg. test.mp3
                if (!((source_file_name.toLowerCase()).endsWith(".wav"))) {
                    int idx = source_file_name.lastIndexOf('.');
                    String wav_file_name = source_file_name.substring(0, idx) + ".wav";

                    File convertedFile = new File("conf/converted");
                    convertedFile.mkdir();
                    File soxExefile = new File("sox/sox.exe");
                    File allToWavExefile = new File("sox/AlltowavCmd.exe");
                    Process pr;
                    String sox_path = soxExefile.getAbsolutePath();
                    String allToWav_path = allToWavExefile.getAbsolutePath();

                    String source_file_path = fc.getSelectedFile().getAbsolutePath();
                    String wav_file_path = convertedFile.getAbsolutePath() + "\\" + wav_file_name;

                    String sox_command = sox_path + " " + source_file_path + " -r 8k " + convertedFile.getAbsolutePath() + "\\" + wav_file_name;
                    //String mystring1 = sox_path+" -r 8k -e signed -b 8 -c 1 "+source_file_path+" d:\\converted\\"+newFileName;
                    String AllToWav_command = allToWav_path + " " + source_file_path + " -O" + convertedFile.getAbsolutePath() + "\\" + wav_file_name + " -S8000";

                    if ((source_file_name).endsWith(".mp3")) {


                        Process t = Runtime.getRuntime().exec(sox_command);
                        t.waitFor();


                        System.out.println("sox command: " + sox_command);
                        wavefile = new File(wav_file_path);

                        if (!wavefile.exists()) {
                            System.out.println("Error : File not found !");
                            return;
                        }
                        //lblloading.setText("Done");
                    }

                    if ((source_file_name).endsWith(".wma")) {
                        Process t = Runtime.getRuntime().exec(AllToWav_command);
                        t.waitFor();


                        System.out.println("All to wave command: " + AllToWav_command);
                        wavefile = new File(wav_file_path);

                        if (!wavefile.exists()) {
                            System.out.println("Error: File not found !");
                            return;
                        }

                    }
                } else {
                    wavefile = fc.getSelectedFile();
                }

                if (wavefile != null) {

                    //Get Bytes from current wave  files & selected new wave file
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    AudioInputStream newaudioInputStream = AudioSystem.getAudioInputStream(wavefile);
                    byte[] newBytes = StreamConverter.streamTobyte(newaudioInputStream);
                    outputStream.write(pWave.streamBytes.getCurrent());
                    outputStream.write(newBytes);


                    // Create InputSteam for Current file , New file & Mergerd file

                    newaudioInputStream = StreamConverter.byteTostream(newBytes, pWave.audioInputStream);
                    AudioInputStream currentaudioInputStream = StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream);
                    AudioInputStream mergedaudioInputStream = StreamConverter.byteTostream(outputStream.toByteArray(), pWave.audioInputStream);


                    //Find hash function for inputstream

                    String current_hash_name = Hash.getHashValue(currentaudioInputStream);
                    String merged_hash_name = Hash.getHashValue(mergedaudioInputStream);
                    String new_hash_name = Hash.getHashValue(newaudioInputStream);


                    //Find length of current wave file

                    int current_length = (int) ((currentaudioInputStream.getFrameLength() * 1000) / currentaudioInputStream.getFormat().getFrameRate());

                    //get annotation from current and selected new file

               //     ArrayList current_ann_id = new FindAnnotated(pWave.mainFrame.getConn(), Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), 0, 0).getAnnotationIDAll();

              //      ArrayList new_ann_id = new FindAnnotated(pWave.mainFrame.getConn(), new_hash_name, 0, 0).getAnnotationIDAll();

                    // Insert Annotation to Merged file 

                 //   boolean inserted = new InsertAnnotation(pWave.mainFrame.getConn()).insertCopySAnnotation(merged_hash_name, 0, 0, current_ann_id);

                 //   if (inserted) {
                //        inserted = new InsertAnnotation(pWave.mainFrame.getConn()).insertCopySAnnotation(merged_hash_name, 0, current_length, new_ann_id);
                    //    if (!inserted) {
                    //        javax.swing.JOptionPane.showMessageDialog(null, "Unable to copy the annotations 2");
                    //    }
                   // } else {
                   //     javax.swing.JOptionPane.showMessageDialog(null, "Unable to copy the annotations");
                   // }

                    //Create new panel and display Merged file with annotation 
                    saveFileAsSelected("UNDOC", pWave.streamBytes.getCurrent());
                    pWave.streamBytes.setCurrent(outputStream.toByteArray());
                    pWave.audioInputStream = StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream);
                    pWave.samplingGraph.createWaveForm(null);
                    newBytes = null;
                }


            }
        } catch (SecurityException ex) {
            // JavaSound.showInfoDialog();
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex1) {
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//End F

    public void mergewithFileMathod() {
        try {
            File wavefile = null;
            File fileDir = new File(System.getProperty("user.dir"));
            JFileChooser fc = new JFileChooser(fileDir);


            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                long start = System.currentTimeMillis();

                String source_file_name = (fc.getSelectedFile().getName()).toLowerCase();//eg. test.mp3
                if (!((source_file_name.toLowerCase()).endsWith(".wav"))) {
                    int idx = source_file_name.lastIndexOf('.');
                    String wav_file_name = source_file_name.substring(0, idx) + ".wav";

                    File convertedFile = new File("conf/converted");
                    convertedFile.mkdir();
                    File soxExefile = new File("sox/sox.exe");
                    File allToWavExefile = new File("sox/AlltowavCmd.exe");
                    Process pr;
                    String sox_path = soxExefile.getAbsolutePath();
                    String allToWav_path = allToWavExefile.getAbsolutePath();

                    String source_file_path = fc.getSelectedFile().getAbsolutePath();
                    String wav_file_path = convertedFile.getAbsolutePath() + "\\" + wav_file_name;

                    String sox_command = sox_path + " " + source_file_path + " -r 8k " + convertedFile.getAbsolutePath() + "\\" + wav_file_name;
                    //String mystring1 = sox_path+" -r 8k -e signed -b 8 -c 1 "+source_file_path+" d:\\converted\\"+newFileName;
                    String AllToWav_command = allToWav_path + " " + source_file_path + " -O" + convertedFile.getAbsolutePath() + "\\" + wav_file_name + " -S8000";

                    if ((source_file_name).endsWith(".mp3")) {


                        Process t = Runtime.getRuntime().exec(sox_command);
                        t.waitFor();


                        System.out.println("sox command: " + sox_command);
                        wavefile = new File(wav_file_path);

                        if (!wavefile.exists()) {
                            System.out.println("Error : File not found !");
                            return;
                        }
                        //lblloading.setText("Done");
                    }

                    if ((source_file_name).endsWith(".wma")) {
                        Process t = Runtime.getRuntime().exec(AllToWav_command);
                        t.waitFor();


                        System.out.println("All to wave command: " + AllToWav_command);
                        wavefile = new File(wav_file_path);

                        if (!wavefile.exists()) {
                            System.out.println("Error: File not found !");
                            return;
                        }

                    }
                } else {
                    wavefile = fc.getSelectedFile();
                }

                if (wavefile != null) {

                    //Get Bytes from current wave  files & selected new wave file
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    AudioInputStream newaudioInputStream = AudioSystem.getAudioInputStream(wavefile);
                    byte[] newBytes = StreamConverter.streamTobyte(newaudioInputStream);
                    outputStream.write(pWave.streamBytes.getCurrent());
                    outputStream.write(newBytes);


                    // Create InputSteam for Current file , New file & Mergerd file

                    newaudioInputStream = StreamConverter.byteTostream(newBytes, pWave.audioInputStream);
                    AudioInputStream currentaudioInputStream = StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream);
                    AudioInputStream mergedaudioInputStream = StreamConverter.byteTostream(outputStream.toByteArray(), pWave.audioInputStream);


                    //Find hash function for inputstream

                    String current_hash_name = Hash.getHashValue(currentaudioInputStream);
                    String merged_hash_name = Hash.getHashValue(mergedaudioInputStream);
                    String new_hash_name = Hash.getHashValue(newaudioInputStream);


                    //Find length of current wave file

                    int current_length = (int) ((currentaudioInputStream.getFrameLength() * 1000) / currentaudioInputStream.getFormat().getFrameRate());

                    //get annotation from current and selected new file

            //        ArrayList current_ann_id = new FindAnnotated(pWave.mainFrame.getConn(), Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), 0, 0).getAnnotationIDAll();

            //        ArrayList new_ann_id = new FindAnnotated(pWave.mainFrame.getConn(), new_hash_name, 0, 0).getAnnotationIDAll();

                    // Insert Annotation to Merged file 

           //         boolean inserted = new InsertAnnotation(pWave.mainFrame.getConn()).insertCopySAnnotation(merged_hash_name, 0, 0, current_ann_id);

           //         if (inserted) {
           //             inserted = new InsertAnnotation(pWave.mainFrame.getConn()).insertCopySAnnotation(merged_hash_name, 0, current_length, new_ann_id);
          //              if (!inserted) {
           //                 javax.swing.JOptionPane.showMessageDialog(null, "Unable to copy the annotations 2");
           //             }
           //         } else {
          //              javax.swing.JOptionPane.showMessageDialog(null, "Unable to copy the annotations");
          //          }

                    //Create new panel and display Merged file with annotation 
                    mergedaudioInputStream = StreamConverter.byteTostream(outputStream.toByteArray(), pWave.audioInputStream);
              //      pWave.mainFrame.createCopyPanel("Copy Panel", mergedaudioInputStream);
                    newBytes = null;
                }


            }
        } catch (SecurityException ex) {
            // JavaSound.showInfoDialog();
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex1) {
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PanelRightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//End File Load
}
