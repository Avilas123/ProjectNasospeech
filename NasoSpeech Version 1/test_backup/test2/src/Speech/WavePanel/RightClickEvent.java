/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.WavePanel;


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
 *  @author Tatapower SED
 *  
 */

/*
 * Change Log:
 * Date 10 Apr 2013
 * Modifier : Lokesh Makode (TP SED)
 * Task : Undo and Redo Funtion changed to be Used for multiple levels
 * Funtions Effected : saveFileAsSelected
 * New Variable's :
 *          undoStack
 *          redoStack
 * 
 * chages Made are marked in quoted of :) and (:
 * 
 * 
 */
public class RightClickEvent {

    private PlotWave pWave;
    private NewPlotWavePanel npWave;
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
    private JMenu importProcessed;
    private int copy_from_ms = 0, copy_to_ms = 0, ann_fLength, ann_oldfLength;
    private NewPlotWavePanel pWaveSda;
    private String ann_fName, ann_oldfName;
    
    private JMenu addKeywordMenu;
   
//:)    
    Stack<String> undoStack;
    Stack<String> redoStack;
    int _MAX_UNDO_REDO_SIZE = 5;
//(:

    public RightClickEvent(PlotWave pWave) {

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
        importProcessed = new JMenu(" Import ");
        importProcessed.setFont(new Font("Courier New", Font.PLAIN, 15));
        importProcessed.setBackground(Color.white);

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
        importProcessed.removeAll();
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
            /*if (pWave.playback.line == null && pWave.audioInputStream != null) {
                //  pWave.menu.add(play);
            }
            if (pWave.playback.line == null && pWave.audioInputStream != null) {
                item = new JMenuItem(" Hypernasality ");
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
            }*/




            /*if (pWave.playback.line != null) {
                if (pWave.playback.line.isRunning() && pWave.playback.line.isOpen()) {
                    item = new JMenuItem("End Point Detection");
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
            }*/

            /*if (pWave.playback.line != null) {
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
            }*/


            /*if (pWave.playback.line != null) {
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
            }*/

            //End playing portion

            //undo and redo

           /*if (pWave.audioInputStream != null && !undoStack.isEmpty()) {
                item = new JMenuItem(" Undo ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
//:)                            
                        saveFileAsSelected("REDO", pWave.streamBytes.getCurrent());
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
            }*/

           /* if (pWave.audioInputStream != null && !redoStack.isEmpty()) {
                item = new JMenuItem(" Redo ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (sourceAvailValidation()) {
//:)    
                            saveFileAsSelected("UNDO", pWave.streamBytes.getCurrent());
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
            }*/

            //End undo and redo



            /*if (pWave.mousePosX1 != 0 && pWave.mousePosX2 != 0 && pWave.audioInputStream != null) {

                item = new JMenuItem(" Cut ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        cutWaveFile(true);
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }*/

            //End cut


            //Copy

            /*if (pWave.mousePosX1 != 0 && pWave.mousePosX2 != 0 && pWave.audioInputStream != null) {
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
                            Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }*/
            //End copy

            //[paste]
            /*if (CutAudioInputStream.getCutWave() != null && pWave.audioInputStream != null) {
                item = new JMenuItem(" Paste ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (CutAudioInputStream.getCutWave() != null) {
                            AudioInputStream newInputStream = StreamConverter.byteTostream(CutAudioInputStream.getCutWave(), pWave.audioInputStream);

                            // FindAnnotated findAnnotation = new FindAnnotated(pWave.mainFrame.getConn(), pWave.fileName, copy_from_ms, copy_to_ms);
                            ArrayList ann_id = new FindAnnotated(pWave.mainFrame.getConn(), Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), copy_from_ms, copy_to_ms).getAnnotationID();

                            String newAnn_file = Hash.getHashValue(newInputStream);
                            newInputStream = StreamConverter.byteTostream(CutAudioInputStream.getCutWave(), pWave.audioInputStream);
                            new InsertAnnotation(pWave.mainFrame.getConn()).insertCopySAnnotation(newAnn_file, copy_from_ms, 0, ann_id);
                            pWave.mainFrame.createCopyPanel("Copy/Paste", newInputStream);

                            CutAudioInputStream.setCutWave(null);
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
            }*/
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

            //manualProcess.add(dummy);

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
                            System.err.println(er);
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                manualProcess.add(item);
                
                
                
            }


            //[End Select All]

            //pWave.menu.add(manualProcess);

            //Annotation
       /* if (pWave.audioInputStream != null) {
             pWave.menu.add(annotation);
             }*/

           //item = new JMenuItem(" Add to dummy ");
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
                            pWaveSda = null;
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
                                        Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
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
                                //    new UpdateAnnotation(pWave.mainFrame.getConn()).updateAnnotationFileName(ann_oldfName, ann_fName);
                                }
                                calculatePixcel();
                                PixcelConversion pixConversion = new PixcelConversion();
                                int from_ms = pixConversion.pixcelToMillisecond(getStartPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                                int to_ms = pixConversion.pixcelToMillisecond(getendPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());

                         //       ArrayList ann_id = new FindAnnotated(pWave.mainFrame.getConn(), Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), from_ms, to_ms).getAnnotationID();



                            //    new InsertAnnotation(pWave.mainFrame.getConn()).insertCopySAnnotation(ann_fName, from_ms, ann_oldfLength, ann_id);

                                ann_oldfName = ann_fName;
                                ann_oldfLength = ann_fLength;

                                if (pWave.dummyList.size() == 1) {
                                    try {
                                        pWaveSda = new NewPlotWavePanel(pWave.mainFrame, newInputStream, "Dummy Panel");
                               //         InternalFrame soundImprove = new InternalFrame(false, true, true, pWave.mainFrame);
                                 //       soundImprove.setDummyList((ArrayList) pWave.dummyList);
                                //        soundImprove.setTitle("Dummy");
                               //         soundImprove.setVisible(true);
                              //          soundImprove.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Speech/Icons/swn2.png")));
                              //          pWave.mainFrame.screenproperty.addSoundImprovePanel(soundImprove);
                              //          soundImprove.add(pWaveSda);
                              //          pWave.mainFrame.jDesktopPane1.add(soundImprove);
                                        pWave.mainFrame.screenproperty.resizeScreen(pWave.mainFrame);
                                    } catch (Exception er) {
                                        System.out.println(er);
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
                                System.out.println(er);
                            }
                        }
                        //--



                        //----------------
                    } catch (Exception ex) {
                        Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
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

            /*item = new JMenuItem(" Create label ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (pWave.audioInputStream != null) {
                        createAnnotation();
                    }
                }
            });*/
           item.setFont(new Font("Courier New", Font.PLAIN, 15));
           item.setBackground(Color.white);
           pWave.menu.add(item);


            /*item = new JMenuItem(" Refresh ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        pWave.mainFrame.fileBrowser("");
                    } catch (Exception er) {
                        System.err.println(er);
                    }
                }
            });*/
            //item.setFont(new Font("Courier New", Font.PLAIN, 15));
            //item.setBackground(Color.white);
            //pWave.menu.add(item);


            //item = new JMenuItem(" Server ");
            //item.addActionListener(new ActionListener() {
                //public void actionPerformed(ActionEvent e) {
                    //if (sourceAvailValidation()) {
                        //try {
                          //  if (pWave.fileName != null) {
                            //    if (pWave.abbfilePath != null) {
                              //      File getFile = new File(pWave.abbfilePath);
                                //    if (getFile.exists()) {
                                  //      ArrayList fileList = new ArrayList();
                                    //    fileList.add(getFile.getAbsoluteFile());
                                      //  pWave.mainFrame.createFileBrowser(fileList);
                                    //}
                               // }
                            //}
                        //} catch (Exception er) {
                          //  System.err.println(er);
                        //}
                   // }
                //}
            //});
            //item.setFont(new Font("Courier New", Font.PLAIN, 15));
           // item.setBackground(Color.white);
            //sendTo.add(item);

            //PDS
            // pWave.menu.add(sendTo);
            //pWave.menu.add(pds);


            //item = new JMenuItem(" Remove silence ");
            //item.addActionListener(new ActionListener() {
              //  public void actionPerformed(ActionEvent e) {
                  //  if (sourceAvailValidation()) {
                //        pWave.mainFrame.createSoundImprovePanel("Remove silence", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
                    //}
                //}
           // });
            //item.setFont(new Font("Courier New", Font.PLAIN, 15));
            //item.setBackground(Color.white);
            //pds.add(item);

            //item = new JMenuItem(" Remove noise ");
            //item.addActionListener(new ActionListener() {
              //  public void actionPerformed(ActionEvent e) {
                //    if (sourceAvailValidation()) {
                  //      pWave.mainFrame.createSoundImprovePanel("Remove noise", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
                    //}
                //}
            //});
           // item.setFont(new Font("Courier New", Font.PLAIN, 15));
            //item.setBackground(Color.white);
            //pds.add(item);


           // item = new JMenuItem(" Enhance speech ");
           // item.addActionListener(new ActionListener() {
               // public void actionPerformed(ActionEvent e) {
                   // if (sourceAvailValidation()) {
                      //  pWave.mainFrame.createSoundImprovePanel("Enhance speech", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
                 //   }
               // }
           // });
           // item.setFont(new Font("Courier New", Font.PLAIN, 15));
           // item.setBackground(Color.white);
           // pds.add(item);


//            item = new JMenuItem(" Combined ");
          //  item.addActionListener(new ActionListener() {
           //     public void actionPerformed(ActionEvent e) {
               //     if (sourceAvailValidation()) {
              //          pWave.mainFrame.createSoundImprovePanel("Combined", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
              //      }
             //   }
         //   });
          //  item.setFont(new Font("Courier New", Font.PLAIN, 15));
          //  item.setBackground(Color.white);
          //  pds.add(item);





            //Phoneme

           // pWave.menu.add(phoneme);


            //KWS
           // pWave.menu.add(keywordMenu);

           ///// put the hypernasality code here and end point detection code here  .///////////
           
            item = new JMenuItem(" Energy detection ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    //pWave.mainFrame.createIvectorInternalFrame("Speaker Identification", "word/Assamese/part2");
              //      pWave.mainFrame.createKwsInternalFrame("Keyword spotting )", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), imFile.getLanguage(), processList);
                  //  System.out.println("fdfadf jdfajdf adfjadfkja ");
                
                    
               
                      
                       
                        //new KeyWordSpottingFrame(pWave.mainFrame, "Keyword spotting (" + imFile.getLanguage() + ")", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), imFile.getLanguage()).processedSelectedKeywordSpotting(processList);
                  //       pWave.mainFrame.createPhenomeInternalFrame("Energy Contours ", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Nasospeech", null);
                    
                    
                    
                    
                    
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            pWave.menu.add(item);
           
            
            
            item = new JMenuItem(" End point Detection");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    //pWave.mainFrame.createIvectorInternalFrame("Speaker Identification", "word/Assamese/part2");

                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            pWave.menu.add(item);
            
            
            ///// for MFCC extraction 
            item = new JMenuItem(" MFCC extraction");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    //pWave.mainFrame.createIvectorInternalFrame("Speaker Identification", "word/Assamese/part2");
                  //  pWave.mainFrame.createMFCCInternalFrame("MFCC Output ", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Nasospeech", null);

                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            pWave.menu.add(item);
            /////////////////////////  MFCC extraction end
            
            
            item = new JMenuItem(" Hypernasality");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    //pWave.mainFrame.createIvectorInternalFrame("Speaker Identification", "word/Assamese/part2");

                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            pWave.menu.add(item);
            
            
             ///// END OF  the hypernasality code here and end point detection code here  .///////////
            //[end speaker Identification]

            // pWave.menu.add(speakeridMenu);

            //pWave.menu.add(speakeridMenu);

          //  item = new JMenuItem(" Ripping ");
          //  item.addActionListener(new ActionListener() {
            //    public void actionPerformed(ActionEvent e) {
             //       pWave.mainFrame.startRipping();
             //   }
           // });
          //  item.setFont(new Font("Courier New", Font.PLAIN, 15));
          //  item.setBackground(Color.white);
           // pWave.menu.add(item);
           // pWave.menu.add(importProcessed);

          //  item = new JMenuItem(" Phoneme recognition ");
          //  item.addActionListener(new ActionListener() {
           //     public void actionPerformed(ActionEvent e) {
                  //  try {
                      //  ImportFile imFile = new ImportFile();
                     //   ArrayList processList = imFile.phonemeReg(pWave);
                     //   if (processList == null) {
                     //       return;
                     //   }
                     //   if (imFile.getHashvalue() == null || imFile.getLanguage() == null) {
                     //       javax.swing.JOptionPane.showMessageDialog(pWave.mainFrame, "File has corrupted", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                      //      return;
                      //  }
                       // if (!(imFile.getHashvalue().equals(pWave.fileHashValue))) {
                       //     javax.swing.JOptionPane.showMessageDialog(pWave.mainFrame, "you are importing wrong file", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                      //      return;
                      //  }
                     //   pWave.mainFrame.createPhenomeInternalFrame("Phenome analysis (" + imFile.getLanguage() + ")", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "English", processList);
                  //  } catch (Exception er) {
                  //      System.err.println(er);
                  //  }
               // }
           // });
           // item.setFont(new Font("Courier New", Font.PLAIN, 15));
           // item.setBackground(Color.white);
           // importProcessed.add(item);


           // item = new JMenuItem(" Keyword spotting ");
           // item.addActionListener(new ActionListener() {
             //   public void actionPerformed(ActionEvent e) {
                   // try {
                    //    ImportFile imFile = new ImportFile();
                      //  ArrayList processList = imFile.keywordSpotting(pWave);
                       // if (processList == null) {
                       //     return;
                       // }
                       // if (imFile.getHashvalue() == null || imFile.getLanguage() == null) {
                       //     javax.swing.JOptionPane.showMessageDialog(pWave.mainFrame, "File has corrupted", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                       //     return;
                       // }
                       // if (!(imFile.getHashvalue().equals(pWave.fileHashValue))) {
                       //     javax.swing.JOptionPane.showMessageDialog(pWave.mainFrame, "you are importing wrong file", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                           // return;
                       // }
                       // new KeyWordSpottingFrame(pWave.mainFrame, "Keyword spotting (" + imFile.getLanguage() + ")", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), imFile.getLanguage()).processedSelectedKeywordSpotting(processList);
                        // pWave.mainFrame.createKwsInternalFrame("Keyword spotting ("+imFile.getLanguage()+")", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), imFile.getLanguage(), processList);
                   // } catch (Exception er) {
                        //System.err.println(er);
                   // }
              //  }
           // });
           // item.setFont(new Font("Courier New", Font.PLAIN, 15));
           // item.setBackground(Color.white);
           // importProcessed.add(item);


          //  if (pWave.audioInputStream != null && (pWave.mousePosX1 == 0 || pWave.mousePosX2 == 0)) {
                //Merger file
             //   item = new JMenuItem(" Merge with ");
              //  item.addActionListener(new ActionListener() {
                  //  public void actionPerformed(ActionEvent e) {
                  //      mergeFileMathod();
                   // }
               // });
               // item.setFont(new Font("Courier New", Font.PLAIN, 15));
               // item.setBackground(Color.white);
               // manualProcess.add(item);
          //  }
            
            //add keyword
            /*item = new JMenuItem(" Add Keyword (English) ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (pWave.audioInputStream != null) {
                        addKeyword("English",null);
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            manualProcess.add(item);
            */
            
            //----------------ADD KEYWORD MENU STARTS----------------------------//            
    // new AddKeywordMenu(pWave.menu,pWave.mainFrame);      
            
            //-----------------ADD KEYWORD MWNU ENDS-----------------------//
            
            
            item = new JMenuItem(" Close");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {

                        if (sourceAvailValidation()) {

                            //Save Exsiting 
                            if (pWave.getfileNameColor().equals(Color.red)) {
                                int option = javax.swing.JOptionPane.showConfirmDialog(pWave.mainFrame, "Do you want to save changes to " + pWave.fileName, "Save", JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.YES_OPTION) {
                                    saveFile("saveAS");
                                }

                            }
                            //End Save
                            String curr_hash_name = Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream));
                      //      boolean find_ann = new FindAnnotated(pWave.mainFrame.getConn(), curr_hash_name, 0, 0).isAnnotationFileinServer();

                      //      if (!find_ann) {
                       //         int option = javax.swing.JOptionPane.showConfirmDialog(pWave.mainFrame, "" + pWave.fileName + " is annotated, if not saved in server changes will be removed, Do you want to send to server", "Conform", JOptionPane.YES_NO_OPTION);
                      //          if (option == JOptionPane.YES_OPTION) {
                       //             sendToServer();
                     //           }
                    //        }
                            if (pWave.audioInputStream != null) {
                                if (pWave.playback.line != null) {
                                    if (pWave.playback.line.isRunning() && pWave.playback.line.isOpen()) {
                                        pWave.stopSound();
                                    }
                                }
                            }
                            pWave.audioInputStream = null;
                            pWave.clearInfoLabel();
                            pWave.streamBytes.setCurrent(null);
                            pWave.createAudioInputStream(null, null, true);
                            pWave.setGrphSizeinScreen(0);
                            pWave.samplingGraph.repaint();
                            undoStack.clear();
                            redoStack.clear();
                            pWave.setfileNameColor(Color.black);
                          //  pWave.loadB.setEnabled(true);
                        } else {
                            pWave.clearInfoLabel();
                            if (pWave.audioInputStream != null) {
                                if (pWave.playback.line != null) {
                                    if (pWave.playback.line.isRunning() && pWave.playback.line.isOpen()) {
                                        pWave.stopSound();
                                    }
                                }
                            }
                            pWave.audioInputStream = null;
                            pWave.streamBytes.setCurrent(null);
                            pWave.createAudioInputStream(null, null, true);
                            pWave.setGrphSizeinScreen(0);
                            pWave.samplingGraph.repaint();
                            pWave.setfileNameColor(Color.black);
                        //    pWave.loadB.setEnabled(true);

                        }


                    } catch (Exception er) {
                        System.err.println(er);
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            pWave.menu.add(item);


            //End Menu

            //Phoneme

            item = new JMenuItem(" Assamese ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
               //         pWave.mainFrame.createPhenomeInternalFrame("Phenome analysis (Assamese)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Assamese", null);
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            phoneme.add(item);

            //item = new JMenuItem(" Hindi ");
            //item.addActionListener(new ActionListener() {
                //public void actionPerformed(ActionEvent e) {
                    //if (sourceAvailValidation()) {
                        //pWave.mainFrame.createPhenomeInternalFrame("Phenome analysis (Hindi)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Hindi", null);
                    //}
                //}
            //});
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            phoneme.add(item);

            item = new JMenuItem(" Telugu ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                    //    pWave.mainFrame.createPhenomeInternalFrame("Phenome analysis (Telugu)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Telugu", null);
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            phoneme.add(item);


            //item = new JMenuItem(" English ");
            //item.addActionListener(new ActionListener() {
               // public void actionPerformed(ActionEvent e) {
                   // if (sourceAvailValidation()) {
                       // pWave.mainFrame.createPhenomeInternalFrame("Phenome analysis (English)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "English", null);
                   // }
               // }
            //});
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
                   //     pWave.mainFrame.createKwsInternalFrame("Keyword spotting (English)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "English");
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
                    //    pWave.mainFrame.createKwsInternalFrame("Keyword spotting (Assamese Part-1)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Assamese_part1");
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
                   //     pWave.mainFrame.createKwsInternalFrame("Keyword spotting (Assamese Part-2)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Assamese_part2");
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
                  //      pWave.mainFrame.createKwsInternalFrame("Keyword spotting (Hindi Part-1)", StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream), "Hindi_part1");
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
               //         pWave.mainFrame.createCopyPanel("Copy Panel", newInputStream);
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



        } catch (Exception er) {
            System.err.println(er);
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
            System.err.println(er);
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
            System.err.println(er);
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

                anStPos = pixConversion.pixcelToMillisecond(anStPos, pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                anEnPos = pixConversion.pixcelToMillisecond(anEnPos, pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());

//                FindAnnotated findAnn = new FindAnnotated(pWave.mainFrame.getConn(), Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), anStPos, anEnPos);

            //    if (findAnn.findAnnationAll()) {
            //        javax.swing.JOptionPane.showMessageDialog(pWave, "Label already created :" + findAnn.findAnnationAll(), "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
          //          return;
          //      }

             //   pWave.mainFrame.createAnnotationInternalFrame("Annotation", anStPos, anEnPos, Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), pWave);
            }
        } catch (Exception er) {
            System.err.println(er);
        }

    }

    // added on 9-12-2013 by lok
        public void addKeyword(String lang, AudioInputStream audioStream) {
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

                anStPos = pixConversion.pixcelToMillisecond(anStPos, pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                anEnPos = pixConversion.pixcelToMillisecond(anEnPos, pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                System.out.println(lang+" : "+anStPos + "-" + anEnPos);
               // Empty conf/username folder
              //  File f=new File("conf/workspace/"+pWave.mainFrame.getUserID());
            //    new DeleteFile().delete(f);
                //----
           //     File directory = new File("conf/workspace/"+pWave.mainFrame.getUserID());
             //   directory.mkdirs();

                // cut the selected audio and save in temp folder
               
                          if (sourceAvailValidation()) {
                            if (pWave.mousePosX1 != 0 && pWave.mousePosX2 != 0) {
                                getSamplingPositions();
                                int startSample = getStartSamples();
                                int endSample = getEndSamples();
                                String ss=String.valueOf(anStPos);
                                String es=String.valueOf(anEnPos);
                                String fname=ss+"_"+es+".wav";
                                CutAudioWave cutW = new CutAudioWave();
                                cutW.cutPortion(pWave.streamBytes.getCurrent(), startSample, endSample);

                                if (cutW.getresultByteArray() == null) {
                                    return;
                                }
                                if (CutAudioInputStream.getCutWave() == null) {
                                    return;
                                }
                          //      String filelocName = "conf/workspace/"+pWave.mainFrame.getUserID()+"/"+fname;
                        //        StreamConverter.byteTowavefile(CutAudioInputStream.getCutWave(), pWave.audioInputStream, filelocName);
                                                              
                                //-- send to server
                       //         StreamConverter.byteTowavefile(CutAudioInputStream.getCutWave(), pWave.audioInputStream, pWave.mainFrame.getUserID() + "phoneme" + ".wav");               
                                // -- invoke pr
                                //keyBuilder=new KeyWordBuilder();
                                //boolean result = keyBuilder.setSource(pWave.mainFrame.getUserID() + "phoneme" + ".wav", lang, pWave.mainFrame.getUserID() + "phoneme", null);

                                //--
                                CutAudioInputStream.setCutWave(null);                                 
                              //  pWave.mainFrame.createAddkwInternalFrame("Add New Keyword",lang, anStPos, anEnPos, fname, pWave.audioInputStream);
                            }
                          }
                 // Run Phoneme Recognizer to get the phone sequences of the selected audio
                          
                 // Display phone sequences and let user enter Keyword and other details
                
            }
        } catch (Exception er) {
            System.err.println(er);
        }

    }
    //----------
    public void resizeandAddannotation() {
        try {
            if (pWave.mousePosX1 != 0 && pWave.mousePosX2 != 0 && pWave.mousePosY1 > 195) {
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


                  //  FindAnnotated findAnn = new FindAnnotated(pWave.mainFrame.getConn(), Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), anStPos, anEnPos);

                   
                    int dragStPos = (int) pWave.mousePosX1;
                    int dragEnPos = (int) pWave.mousePosX2;

           //         int ann_actualStPixPos = pixConversion.milliSecondToPixcel(Integer.toString(ann_actualStPos), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
           //         int ann_actualEnPixPos = pixConversion.milliSecondToPixcel(Integer.toString(ann_actualEnPos), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());

              
                   
                   
                   

                    

                } catch (Exception er) {
                    System.err.println(er);
                }


            }


            if (pWave.mousePosX1 != 0 && pWave.mousePosX2 == 0) {

                int ms = new PixcelConversion().pixcelToMillisecond((int) pWave.mousePosX1, pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                if (ms == 0) {
                    return;
                }

               // int[] ann_pos = new GetAnnotation(pWave.mainFrame.getConn()).getUniqueSelect(ms, Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)));
              //  if (ann_pos == null) {
              //      return;
              //  }
              //  if (ann_pos.length != 2) {
              //      return;
             //   }
             //   if (ann_pos[0] == 0 || ann_pos[1] == 1) {
             //       return;
            //    }

              //  pWave.mainFrame.createAnnotationInternalFrame("Annotation", ann_pos[0], ann_pos[1], Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), pWave);
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

    public void waveDelete(int startSample, int endSample) {
        try {
            //  cutWave();           

            System.out.println(" u " + undoStack.size()
                    + " r " + redoStack.size());

            calculatePixcel();
            PixcelConversion pixConversion = new PixcelConversion();
            int startMs = pixConversion.pixcelToMillisecond(getStartPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
            int endMs = pixConversion.pixcelToMillisecond(getendPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());


    //        FindAnnotated findAnnotation = new FindAnnotated(pWave.mainFrame.getConn(), Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), startMs, endMs);



          //  if (findAnnotation.findAnnationAll()) {
          ///      javax.swing.JOptionPane.showMessageDialog(pWave, "Can not be Delete the region");
          //      return;
          //  }

            //Find any annotation at back side (after selection area)
            
        } catch (Exception ex) {
            Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
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
               // pWave.mainFrame.createSendServerFile("conf/workspace/" + pWave.fileName, current_hash_name, false);

            }

        } catch (Exception er) {
            System.err.println(er.getMessage());
        }
    }

    private void fileSaveAs1() {
        String fileName = saveLocation();
        try {
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
                StreamConverter.byteTowavefile(selectW.getSelectedByteArray(), pWave.audioInputStream, fileName);
            } else {
                if (pWave.streamBytes.getCurrent() != null && fileName != null) {
                    //Annotation
                    getSamplingPositions();
                    int startSample = getStartSamples();
                    int endSample = getEndSamples();


                    calculatePixcel();
                    PixcelConversion pixConversion = new PixcelConversion();
                    int startMs = pixConversion.pixcelToMillisecond(getStartPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());
                    int endMs = pixConversion.pixcelToMillisecond(getendPixel(), pWave.frames_per_pixel, (int) pWave.audioInputStream.getFormat().getFrameRate());


              //      FindAnnotated findAnnotation = new FindAnnotated(pWave.mainFrame.getConn(), Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), startMs, endMs);



                    

                    StreamConverter.byteTowavefile(pWave.streamBytes.getCurrent(), pWave.audioInputStream, fileName);
                }
            }
        } catch (Exception er) {
            System.err.println(er.getMessage());
        }
    }

    private void saveFileAsSelected(String task, byte[] audiobytes) {
        System.out.println(" us " + undoStack.size() + " rs " + redoStack.size());
        if (task == "UNDO") {
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

        } else if (task == "REDO") {
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

        //        FindAnnotated findAnnotation = new FindAnnotated(pWave.mainFrame.getConn(), Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), startMs, endMs);


              

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

                //insert from 0 pos to start selection pos in new file

              
                //Display portion

                saveFileAsSelected("UNDO", pWave.streamBytes.getCurrent());

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
            Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
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

      //          ArrayList ann_id_selection = new FindAnnotated(pWave.mainFrame.getConn(), hash_current_name, startMs, endMs).getAnnotationID();


                // Insert Annotation to New file 
                //insert from 0 pos to start selection pos in new file

           //     boolean inserted = new InsertAnnotation(pWave.mainFrame.getConn()).insertCopySAnnotation(hash_new_name, startMs, 0, ann_id_selection);
                //insert from endpart of selection to file length (startpos - selection)+length

                //Display portion
                saveFileAsSelected("UNDO", pWave.streamBytes.getCurrent());

                pWave.setfileNameColor(Color.red);

                pWave.streamBytes.setCurrent(CutAudioInputStream.getCutWave());


                CutAudioInputStream.setCutWave(null);


                pWave.setStreamDrawGraph();
                pWave.mousePosX1 = 0;
                pWave.mousePosX2 = 0;
                //saveFile();



            }
        } catch (Exception ex) {
            Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
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

          //          ArrayList current_ann_id = new FindAnnotated(pWave.mainFrame.getConn(), Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), 0, 0).getAnnotationIDAll();

         //           ArrayList new_ann_id = new FindAnnotated(pWave.mainFrame.getConn(), new_hash_name, 0, 0).getAnnotationIDAll();

                    // Insert Annotation to Merged file 

                 //   boolean inserted = new InsertAnnotation(pWave.mainFrame.getConn()).insertCopySAnnotation(merged_hash_name, 0, 0, current_ann_id);

                    

                    //Create new panel and display Merged file with annotation 
                    mergedaudioInputStream = StreamConverter.byteTostream(outputStream.toByteArray(), pWave.audioInputStream);
               //     pWave.mainFrame.createCopyPanel("Merge", mergedaudioInputStream);
                    newBytes = null;
                }


            }
        } catch (SecurityException ex) {
            // JavaSound.showInfoDialog();
            Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex1) {
            Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex1);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//End File Load
}
