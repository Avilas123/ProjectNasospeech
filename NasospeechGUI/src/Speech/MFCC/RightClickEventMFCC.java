/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.MFCC;

import Speech.phoneme.*;
import Speech.addLangugeKeywords.AddKeywordMenu;
import Speech.WavePanel.CutAudioWave;
import Speech.WavePanel.SelectedAudioWave;
import Speech.annotations.FindAnnotated;
import Speech.annotations.GetAnnotation;
import Speech.annotations.UpdateAnnotation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import Speech.common.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;


/**
 *
 * @author Tatapower SED
 *
 */
public class RightClickEventMFCC {

    private PlotWaveMFCC pWavePhone;
    private int startSam, endSam;
    private JMenu zoom;
    private JMenu keyword;
    private JMenu play;
    private JMenuItem item;

    public RightClickEventMFCC(PlotWaveMFCC pWavePhonePhone) {
        this.pWavePhone = pWavePhonePhone;

        zoom = new JMenu(" Zoom ");
        zoom.setFont(new Font("Courier New", Font.PLAIN, 15));
        zoom.setBackground(Color.white);
        play = new JMenu(" Play ");
        play.setFont(new Font("Courier New", Font.PLAIN, 15));
        play.setBackground(Color.white);
        keyword = new JMenu(" Add Keyword ");
        keyword.setFont(new Font("Courier New", Font.PLAIN, 15));
        keyword.setBackground(Color.white);
        
    }

    public void addMenuItems() {
        pWavePhone.menu.removeAll();

        zoom.removeAll();
        keyword.removeAll();
        play.removeAll();
        try {

            //Play Manu
            // pWavePhone.menu.add(play);
            if (pWavePhone.playback.line == null && pWavePhone.audioInputStream != null) {
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
                pWavePhone.menu.add(item);
            }

            item = new JMenuItem(" Play from here ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        playFromSoundAll();
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            play.add(item);


            if (pWavePhone.playback.line != null) {
                if (pWavePhone.playback.line.isRunning() && pWavePhone.playback.line.isOpen()) {
                    item = new JMenuItem(" Pause ");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (sourceAvailValidation()) {
                                pWavePhone.pauseSound();
                            }
                        }
                    });


                    item.setFont(new Font("Courier New", Font.PLAIN, 15));
                    item.setBackground(Color.white);
                    pWavePhone.menu.add(item);
                }
            }

            if (pWavePhone.playback.line != null) {
                if (!pWavePhone.playback.line.isRunning() && pWavePhone.playback.line.isOpen()) {
                    item = new JMenuItem(" Resume ");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (sourceAvailValidation()) {
                                pWavePhone.resumeSound();
                            }
                        }
                    });


                    item.setFont(new Font("Courier New", Font.PLAIN, 15));
                    item.setBackground(Color.white);
                    pWavePhone.menu.add(item);
                }
            }


            if (pWavePhone.playback.line != null) {
                if (pWavePhone.playback.line.isRunning() || pWavePhone.playback.line.isOpen() || pWavePhone.playback.line.isActive()) {

                    item = new JMenuItem(" Stop ");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (sourceAvailValidation()) {
                                pWavePhone.stopSound();
                            }
                        }
                    });


                    item.setFont(new Font("Courier New", Font.PLAIN, 15));
                    item.setBackground(Color.white);
                    pWavePhone.menu.add(item);
                }
            }








            //End
            //Save As
            if (pWavePhone.audioInputStream != null) {
                item = new JMenuItem(" Save As... ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String fileName = saveLocation();
                        if (pWavePhone.mousePosX1 != 0 && pWavePhone.mousePosX2 != 0) {
                            getSamplingPositions();
                            int startSample = getStartSamples();
                            int endSample = getEndSamples();

                            //System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWavePhone.frames_per_pixel);

                            SelectedAudioWave selectW = new SelectedAudioWave();
                            selectW.selectdPortion(pWavePhone.streamBytes.getCurrent(), startSample, endSample);

                            if (selectW.getSelectedByteArray() == null) {
                                return;
                            }
                            StreamConverter.byteTowavefile(selectW.getSelectedByteArray(), pWavePhone.audioInputStream, fileName);
                        } else {
                            if (pWavePhone.streamBytes.getCurrent() != null && fileName != null) {
                                StreamConverter.byteTowavefile(pWavePhone.streamBytes.getCurrent(), pWavePhone.audioInputStream, fileName);
                            }
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                pWavePhone.menu.add(item);
            }

            //Export
            /*if (pWavePhone.audioInputStream != null) {
                item = new JMenuItem(" Export ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String fileName = saveLocation();

                        List lines = pWavePhone.keyBuilder.getFileContent();
                        if (lines == null) {
                            return;
                        }
                        String backPhone = fileName.replace(".wav", ".pho");
                        new ExportPhoneme().startProcess(lines, backPhone, pWavePhone.fileHashValue, pWavePhone.language);

                        if (pWavePhone.streamBytes.getCurrent() != null && fileName != null) {
                            StreamConverter.byteTowavefile(pWavePhone.streamBytes.getCurrent(), pWavePhone.audioInputStream, fileName);
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                pWavePhone.menu.add(item);
            }*/


            //Zoom Menu
            /*if (pWavePhone.audioInputStream != null) {
                pWavePhone.menu.add(zoom);
            }*/
            //End


            //Annotation
            /* if (pWavePhone.audioInputStream != null) {
             pWavePhone.menu.add(annotation);
             }*/
            /*if (pWavePhone.audioInputStream != null) {
                item = new JMenuItem(" Create Lable ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        createAnnotation();

                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                pWavePhone.menu.add(item);
            }*/


            //Transcription
            /*if (pWavePhone.audioInputStream != null) {
                item = new JMenuItem(" Transcription ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (pWavePhone.streamBytes.getCurrent() == null) {
                            return;
                        }

                        pWavePhone.mainFrame.createPhonemeTranscrption(pWavePhone);

                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                pWavePhone.menu.add(item);
            }*/
            
            
            
            
            //Add Keyword added on 29/4/14                  
//            
//            if (pWavePhone.audioInputStream != null) {
//                pWavePhone.menu.add(keyword);                         
//            }
//
//            item = new JMenuItem(" Assamese ");
//            item.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    addKeywordNew("Assamese", pWavePhone.audioInputStream);
//                }
//            });
//            item.setFont(new Font("Courier New", Font.PLAIN, 15));
//            item.setBackground(Color.white);
//            keyword.add(item);                    
//            
//            item = new JMenuItem(" Hindi ");
//            item.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    addKeywordNew("Hindi", pWavePhone.audioInputStream);
//                }
//            });
//            item.setFont(new Font("Courier New", Font.PLAIN, 15));
//            item.setBackground(Color.white);
//            keyword.add(item);            
//            
//            item = new JMenuItem(" Telugu  ");
//            item.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    addKeywordNew("Telugu", pWavePhone.audioInputStream);
//                }
//            });
//            item.setFont(new Font("Courier New", Font.PLAIN, 15));
//            item.setBackground(Color.white);
//            keyword.add(item);
//            
//            item = new JMenuItem(" English ");
//            item.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {                             
//                    addKeywordNew("English", pWavePhone.audioInputStream);
//               }
//            });
//            item.setFont(new Font("Courier New", Font.PLAIN, 15));
//            item.setBackground(Color.white);
//            keyword.add(item);
            
           // addKeywordMenu(pWavePhone.menu);
           //new AddKeywordMenu(pWavePhone.menu,pWavePhone.mainFrame);           
            /*Code to 'Add Keyword' ends here*/
            

            item = new JMenuItem(" Normal ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        pWavePhone.setSreemDrawGraph();
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
                        pWavePhone.setZoomIn();
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
                        pWavePhone.setZoomOut();
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
            int width = pWavePhone.samplingGraph.getSize().width;
            int inPos = (int) pWavePhone.mousePosX1, enPos = (int) pWavePhone.mousePosX2;

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

            double bytesPerSecond = pWavePhone.audioInputStream.getFormat().getFrameSize() * (double) pWavePhone.audioInputStream.getFormat().getFrameRate();
            int startTime = ((inPos * pWavePhone.frames_per_pixel) * pWavePhone.audioInputStream.getFormat().getFrameSize());
            int endTime = ((enPos * pWavePhone.frames_per_pixel) * pWavePhone.audioInputStream.getFormat().getFrameSize());
            System.out.println("CK " + ((inPos * pWavePhone.frames_per_pixel) / pWavePhone.audioInputStream.getFormat().getFrameRate()) * 1000 + " " + ((enPos * pWavePhone.frames_per_pixel) / pWavePhone.audioInputStream.getFormat().getFrameRate()) * 1000);
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

            if (pWavePhone.playback.line != null) {
                if (pWavePhone.playback.line.isRunning() || pWavePhone.playback.line.isOpen()) {
                    return;
                }
            }


            if (pWavePhone.mousePosX1 != 0 && pWavePhone.mousePosX2 != 0) {
                getSamplingPositions();
                int startSample = getStartSamples();
                int endSample = getEndSamples();

                System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWavePhone.frames_per_pixel);

                SelectedAudioWave selectW = new SelectedAudioWave();
                selectW.selectdPortion(pWavePhone.streamBytes.getCurrent(), startSample, endSample);

                if (selectW.getSelectedByteArray() == null) {
                    return;
                }
                pWavePhone.streamBytes.setSelectedPlay(selectW.getSelectedByteArray());
                pWavePhone.selectedPlay = true;
                pWavePhone.streamVariable.setSecondStarts(startSample / pWavePhone.audioInputStream.getFormat().getFrameRate());
                pWavePhone.playSound();
            } else if (pWavePhone.mousePosX1 != 0) {

                int inPos = (int) pWavePhone.mousePosX1, enPos = (int) ((pWavePhone.samplingGraph.getSize().width) - 10);

                if (inPos < 1) {
                    inPos = 1;
                }

                if (enPos < 1) {
                    enPos = 1;
                }
                if (inPos > (enPos - 10)) {
                    inPos = enPos - 10;
                }

                double bytesPerSecond = pWavePhone.audioInputStream.getFormat().getFrameSize() * (double) pWavePhone.audioInputStream.getFormat().getFrameRate();
                int startTime = ((inPos * pWavePhone.frames_per_pixel) * pWavePhone.audioInputStream.getFormat().getFrameSize());
                int endTime = ((enPos * pWavePhone.frames_per_pixel) * pWavePhone.audioInputStream.getFormat().getFrameSize());
                System.out.println("CK " + ((inPos * pWavePhone.frames_per_pixel) / pWavePhone.audioInputStream.getFormat().getFrameRate()) * 1000 + " " + ((enPos * pWavePhone.frames_per_pixel) / pWavePhone.audioInputStream.getFormat().getFrameRate()) * 1000);
                int startSample, endSample;
                if (startTime > endTime) {
                    startSample = endTime;
                    endSample = startTime;
                } else {
                    startSample = startTime;
                    endSample = endTime;
                }

                System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWavePhone.frames_per_pixel);

                SelectedAudioWave selectW = new SelectedAudioWave();
                selectW.selectdPortion(pWavePhone.streamBytes.getCurrent(), startSample, endSample);

                if (selectW.getSelectedByteArray() == null) {
                    return;
                }
                pWavePhone.streamBytes.setSelectedPlay(selectW.getSelectedByteArray());
                pWavePhone.selectedPlay = true;
                pWavePhone.streamVariable.setSecondStarts(startSample / pWavePhone.audioInputStream.getFormat().getFrameRate());
                pWavePhone.playSound();
            } else {
                pWavePhone.selectedPlay = false;
                pWavePhone.playSound();
            }
        } catch (Exception er) {
            System.err.println(er);
        }
    }

    public void playFromSoundAll() {
        try {
            if (pWavePhone.mousePosX1 != 0) {

                int inPos = (int) pWavePhone.mousePosX1, enPos = (int) ((pWavePhone.samplingGraph.getSize().width) - 10);

                if (inPos < 1) {
                    inPos = 1;
                }

                if (enPos < 1) {
                    enPos = 1;
                }
                if (inPos > (enPos - 10)) {
                    inPos = enPos - 10;
                }

                double bytesPerSecond = pWavePhone.audioInputStream.getFormat().getFrameSize() * (double) pWavePhone.audioInputStream.getFormat().getFrameRate();
                int startTime = ((inPos * pWavePhone.frames_per_pixel) * pWavePhone.audioInputStream.getFormat().getFrameSize());
                int endTime = ((enPos * pWavePhone.frames_per_pixel) * pWavePhone.audioInputStream.getFormat().getFrameSize());
                System.out.println("CK " + ((inPos * pWavePhone.frames_per_pixel) / pWavePhone.audioInputStream.getFormat().getFrameRate()) * 1000 + " " + ((enPos * pWavePhone.frames_per_pixel) / pWavePhone.audioInputStream.getFormat().getFrameRate()) * 1000);
                int startSample, endSample;
                if (startTime > endTime) {
                    startSample = endTime;
                    endSample = startTime;
                } else {
                    startSample = startTime;
                    endSample = endTime;
                }

                System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWavePhone.frames_per_pixel);

                SelectedAudioWave selectW = new SelectedAudioWave();
                selectW.selectdPortion(pWavePhone.streamBytes.getCurrent(), startSample, endSample);

                if (selectW.getSelectedByteArray() == null) {
                    return;
                }
                pWavePhone.streamBytes.setSelectedPlay(selectW.getSelectedByteArray());
                pWavePhone.selectedPlay = true;
                pWavePhone.streamVariable.setSecondStarts(startSample / pWavePhone.audioInputStream.getFormat().getFrameRate());
                pWavePhone.playSound();
            } else {
                pWavePhone.selectedPlay = false;
                pWavePhone.playSound();
            }
        } catch (Exception er) {
            System.err.println(er);
        }
    }

    public void createAnnotation() {
        try {
            if (pWavePhone.mousePosX1 != 0 && pWavePhone.mousePosX2 != 0) {
                PixcelConversion pixConversion = new PixcelConversion();
                int anStPos = 0;
                int anEnPos = 0;
                if (pWavePhone.mousePosX1 > pWavePhone.mousePosX2) {
                    anStPos = (int) pWavePhone.mousePosX2;
                    anEnPos = (int) pWavePhone.mousePosX1;
                } else {
                    anStPos = (int) pWavePhone.mousePosX1;
                    anEnPos = (int) pWavePhone.mousePosX2;
                }
                anStPos = pixConversion.pixcelToMillisecond(anStPos, pWavePhone.frames_per_pixel, (int) pWavePhone.audioInputStream.getFormat().getFrameRate());
                anEnPos = pixConversion.pixcelToMillisecond(anEnPos, pWavePhone.frames_per_pixel, (int) pWavePhone.audioInputStream.getFormat().getFrameRate());

                FindAnnotated findAnn = new FindAnnotated(pWavePhone.mainFrame.getConn(), pWavePhone.fileHashValue, anStPos, anEnPos);

                if (findAnn.findAnnationAll()) {
                    javax.swing.JOptionPane.showMessageDialog(pWavePhone, "Lable already created :" + findAnn.findAnnationAll(), "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                pWavePhone.mainFrame.createAnnotationInternalFrame("Annotation", anStPos, anEnPos, pWavePhone.fileHashValue, pWavePhone);

            }
        } catch (Exception er) {
            System.err.println(er);
        }

    }

    public void resizeandAddannotation() {
        try {
            if (pWavePhone.mousePosX1 != 0 && pWavePhone.mousePosX2 != 0 && pWavePhone.mousePosY1 > 170) {
                try {
                    PixcelConversion pixConversion = new PixcelConversion();
                    int anStPos = 0;
                    int anEnPos = 0;
                    if (pWavePhone.mousePosX1 > pWavePhone.mousePosX2) {
                        anStPos = (int) pWavePhone.mousePosX2;
                        anEnPos = (int) pWavePhone.mousePosX1;
                    } else {
                        anStPos = (int) pWavePhone.mousePosX1;
                        anEnPos = (int) pWavePhone.mousePosX2;
                    }
                    anStPos = pixConversion.pixcelToMillisecond(anStPos, pWavePhone.frames_per_pixel, (int) pWavePhone.audioInputStream.getFormat().getFrameRate());
                    anEnPos = pixConversion.pixcelToMillisecond(anEnPos, pWavePhone.frames_per_pixel, (int) pWavePhone.audioInputStream.getFormat().getFrameRate());


                    FindAnnotated findAnn = new FindAnnotated(pWavePhone.mainFrame.getConn(), pWavePhone.fileHashValue, anStPos, anEnPos);

                    if (findAnn.isAnnotatedBothPosiotion() == true) {
                        javax.swing.JOptionPane.showMessageDialog(pWavePhone, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);

                        return;
                    }

                    if (!findAnn.findResizeAnnation()) {
                        javax.swing.JOptionPane.showMessageDialog(pWavePhone, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }
                    if (findAnn.findNumberOfMatch() > 1) {
                        javax.swing.JOptionPane.showMessageDialog(pWavePhone, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }
                    if (findAnn.getAnnotationStartPos() == 0 || findAnn.getAnnotationEndPos() == 0) {
                        javax.swing.JOptionPane.showMessageDialog(pWavePhone, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }

                    int ann_actualStPos = findAnn.getAnnotationStartPos();
                    int ann_actualEnPos = findAnn.getAnnotationEndPos();
                    String ann_allow = findAnn.getAllow();
                    String ann_level = findAnn.getLevel();

                    int dragStPos = (int) pWavePhone.mousePosX1;
                    int dragEnPos = (int) pWavePhone.mousePosX2;

                    int ann_actualStPixPos = pixConversion.milliSecondToPixcel(Integer.toString(ann_actualStPos), pWavePhone.frames_per_pixel, (int) pWavePhone.audioInputStream.getFormat().getFrameRate());
                    int ann_actualEnPixPos = pixConversion.milliSecondToPixcel(Integer.toString(ann_actualEnPos), pWavePhone.frames_per_pixel, (int) pWavePhone.audioInputStream.getFormat().getFrameRate());

                    if (!(Math.abs(dragStPos - ann_actualStPixPos) < 15 || Math.abs(dragStPos - ann_actualEnPixPos) < 15)) {
                        javax.swing.JOptionPane.showMessageDialog(pWavePhone, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }
                    if ((Math.abs(dragStPos - ann_actualStPixPos)) < 15) {
                        if (dragEnPos > ann_actualEnPixPos) {
                            javax.swing.JOptionPane.showMessageDialog(pWavePhone, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            allAnnReturnSetUp();
                            return;
                        }


                    } else {
                        if (dragEnPos < ann_actualStPixPos) {
                            javax.swing.JOptionPane.showMessageDialog(pWavePhone, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            allAnnReturnSetUp();
                            return;
                        }
                    }
                    if (ann_allow != null) {
                        ann_allow = ann_allow.trim();
                        try {
                            if (Integer.parseInt(ann_allow) > pWavePhone.mainFrame.getUserRoll()) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePhone, "Senior has verified this region", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                                allAnnReturnSetUp();
                                return;
                            }
                        } catch (NumberFormatException er) {
                            System.err.println(er);
                        }
                    }

                    if ((Math.abs(dragStPos - ann_actualStPixPos)) < 15) {
                        int changeStarPos = pixConversion.pixcelToMillisecond(dragEnPos, pWavePhone.frames_per_pixel, (int) pWavePhone.audioInputStream.getFormat().getFrameRate());
                        int changeEndPos = ann_actualEnPos;
                        int option = JOptionPane.showConfirmDialog(pWavePhone, "Are you sure you want to update ", "Update", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            boolean updated = new UpdateAnnotation(pWavePhone.mainFrame.getConn()).resizeUpdate(ann_actualStPos, ann_actualEnPos, changeStarPos, changeEndPos, pWavePhone.fileHashValue, pWavePhone.mainFrame.getUserID(), ann_level);
                            if (!updated) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePhone, "Unable to update", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            } else {
                                pWavePhone.samplingGraph.createWaveForm(null);
                                pWavePhone.mainFrame.pWave.loadWaveFromRemote();
                                int changeStrarPixPos = pixConversion.milliSecondToPixcel(Integer.toString(changeStarPos), pWavePhone.frames_per_pixel, (int) pWavePhone.audioInputStream.getFormat().getFrameRate());
                                pWavePhone.mainFrame.pWave.scrollWaveFromRemote(changeStrarPixPos);
                            }
                        }
                    } else {
                        int changeStarPos = ann_actualStPos;
                        int changeEndPos = pixConversion.pixcelToMillisecond(dragEnPos, pWavePhone.frames_per_pixel, (int) pWavePhone.audioInputStream.getFormat().getFrameRate());
                        int option = JOptionPane.showConfirmDialog(pWavePhone, "Are you sure you want to update ", "Update", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            boolean updated = new UpdateAnnotation(pWavePhone.mainFrame.getConn()).resizeUpdate(ann_actualStPos, ann_actualEnPos, changeStarPos, changeEndPos, pWavePhone.fileHashValue, pWavePhone.mainFrame.getUserID(), ann_level);
                            if (!updated) {
                                javax.swing.JOptionPane.showMessageDialog(pWavePhone, "Unable to update", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            } else {
                                pWavePhone.samplingGraph.createWaveForm(null);
                                pWavePhone.mainFrame.pWave.loadWaveFromRemote();
                                int changeStrarPixPos = pixConversion.milliSecondToPixcel(Integer.toString(changeStarPos), pWavePhone.frames_per_pixel, (int) pWavePhone.audioInputStream.getFormat().getFrameRate());
                                pWavePhone.mainFrame.pWave.scrollWaveFromRemote(changeStrarPixPos);
                            }
                        }
                    }

                } catch (Exception er) {
                    System.err.println(er);
                }


                // pWavePhone.mainFrame.createAnnotationInternalFrame("Annotation", anStPos, anEnPos);


            }

            if (pWavePhone.mousePosX1 != 0 && pWavePhone.mousePosX2 == 0) {

                int ms = new PixcelConversion().pixcelToMillisecond((int) pWavePhone.mousePosX1, pWavePhone.frames_per_pixel, (int) pWavePhone.audioInputStream.getFormat().getFrameRate());
                if (ms == 0) {
                    return;
                }

                int[] ann_pos = new GetAnnotation(pWavePhone.mainFrame.getConn()).getUniqueSelect(ms, pWavePhone.fileHashValue);
                if (ann_pos == null) {
                    return;
                }
                if (ann_pos.length != 2) {
                    return;
                }
                if (ann_pos[0] == 0 || ann_pos[1] == 1) {
                    return;
                }

                pWavePhone.mainFrame.createAnnotationInternalFrame("Annotation", ann_pos[0], ann_pos[1], pWavePhone.fileHashValue, pWavePhone);
                pWavePhone.mousePosX1 = 0;
            }
            allAnnReturnSetUp();
        } catch (Exception er) {
            System.err.println(er);
        }
    }

    private void allAnnReturnSetUp() {
        try {
            pWavePhone.mousePosX1 = 0;
            pWavePhone.mousePosX2 = 0;
            pWavePhone.samplingGraph.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            pWavePhone.samplingGraph.repaint();
        } catch (Exception er) {
            System.err.println(er);
        }
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
        if (pWavePhone.audioInputStream == null) {
            return false;
        }
        if (pWavePhone.streamBytes.getCurrent() == null) {
            return false;
        }
        if (pWavePhone.streamBytes.getCurrent().length < 10) {
            return false;
        }
        return true;
    }

    private String saveLocation() {
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
     public void addKeywordNew(String lang, AudioInputStream audioStream) {
      
         /*
          *  
          * This was changed on on 22/5/14
         */
         
         try {
            if (pWavePhone.mousePosX1 != 0 && pWavePhone.mousePosX2 != 0) {
                PixcelConversion pixConversion = new PixcelConversion();
                int anStPos = 0;
                int anEnPos = 0;
                if (pWavePhone.mousePosX1 > pWavePhone.mousePosX2) {
                    anStPos = (int) pWavePhone.mousePosX2;
                    anEnPos = (int) pWavePhone.mousePosX1;
                } else {
                    anStPos = (int) pWavePhone.mousePosX1;
                    anEnPos = (int) pWavePhone.mousePosX2;
                }

                anStPos = pixConversion.pixcelToMillisecond(anStPos, pWavePhone.frames_per_pixel, (int) pWavePhone.audioInputStream.getFormat().getFrameRate());
                anEnPos = pixConversion.pixcelToMillisecond(anEnPos, pWavePhone.frames_per_pixel, (int) pWavePhone.audioInputStream.getFormat().getFrameRate());
                System.out.println(lang+" : "+anStPos + "-" + anEnPos);
               // Empty conf/username folder
                File f=new File("conf/workspace/"+pWavePhone.mainFrame.getUserID());
                new DeleteFile().delete(f);
                //----
                File directory = new File("conf/workspace/"+pWavePhone.mainFrame.getUserID());
                directory.mkdirs();

                // cut the selected audio and save in temp folder
               
                          if (sourceAvailValidation()) {
                            if (pWavePhone.mousePosX1 != 0 && pWavePhone.mousePosX2 != 0) {
                                getSamplingPositions();
                                int startSample = getStartSamples();
                                int endSample = getEndSamples();
                                String ss=String.valueOf(anStPos);
                                String es=String.valueOf(anEnPos);
                                String fname=ss+"_"+es+".wav";
                                CutAudioWave cutW = new CutAudioWave();
                                cutW.cutPortion(pWavePhone.streamBytes.getCurrent(), startSample, endSample);

                                if (cutW.getresultByteArray() == null) {
                                    return;
                                }
                                if (CutAudioInputStream.getCutWave() == null) {
                                    return;
                                }
                                String filelocName = "conf/workspace/"+pWavePhone.mainFrame.getUserID()+"/"+fname;
                                System.out.println("\nFileloc: "+filelocName+"\n");
                                StreamConverter.byteTowavefile(CutAudioInputStream.getCutWave(), pWavePhone.audioInputStream, filelocName);
                                                              
                                //-- send to server
                                StreamConverter.byteTowavefile(CutAudioInputStream.getCutWave(), pWavePhone.audioInputStream, pWavePhone.mainFrame.getUserID() + "phoneme" + ".wav");               
                                // -- invoke pr
                                //keyBuilder=new KeyWordBuilder();
                                //boolean result = keyBuilder.setSource(pWave.mainFrame.getUserID() + "phoneme" + ".wav", lang, pWave.mainFrame.getUserID() + "phoneme", null);

                                //--
                                CutAudioInputStream.setCutWave(null);                                 
                               // pWavePhone.mainFrame.createAddkwInternalFrame("Add New Keyword",lang, anStPos, anEnPos, fname, pWavePhone.audioInputStream);
                                pWavePhone.mainFrame.createAddNewInternalFrame("Add New Keyword",lang);
                            }
                          }
                
            }
        } catch (Exception er) {
            System.err.println(er);
        }

    }
}
