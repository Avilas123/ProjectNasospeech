/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.kws;

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
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import Speech.common.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Tatapower SED
 *
 */
public class RightClickEventKws {

    private PlotWaveKws pWaveKws;
    private int startSam, endSam;
    private JMenu zoom;
    private JMenu keyword;
    private JMenu play;
    private JMenuItem item;

    public RightClickEventKws(PlotWaveKws pWaveKwsKws) {
        this.pWaveKws = pWaveKwsKws;

        zoom = new JMenu(" Zoom ");
        zoom.setFont(new Font("Courier New", Font.PLAIN, 15));
        zoom.setBackground(Color.white);
        play = new JMenu(" Play ");
        play.setFont(new Font("Courier New", Font.PLAIN, 15));
        play.setBackground(Color.white);
        keyword = new JMenu(" Keyword analysis ");
        keyword.setFont(new Font("Courier New", Font.PLAIN, 15));
        keyword.setBackground(Color.white);
    }

    public void addMenuItems() {
        pWaveKws.menu.removeAll();

        zoom.removeAll();
        keyword.removeAll();
        play.removeAll();

        try {
            //Play Manu
            //pWaveKws.menu.add(play);
            if (pWaveKws.playback.line == null && pWaveKws.audioInputStream != null) {
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
                pWaveKws.menu.add(item);

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


            if (pWaveKws.playback.line != null) {
                if (pWaveKws.playback.line.isRunning() && pWaveKws.playback.line.isOpen()) {
                    item = new JMenuItem(" Pause ");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (sourceAvailValidation()) {
                                pWaveKws.pauseSound();
                            }
                        }
                    });


                    item.setFont(new Font("Courier New", Font.PLAIN, 15));
                    item.setBackground(Color.white);
                    pWaveKws.menu.add(item);
                }
            }

            if (pWaveKws.playback.line != null) {
                if (!pWaveKws.playback.line.isRunning() && pWaveKws.playback.line.isOpen()) {
                    item = new JMenuItem(" Resume ");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (sourceAvailValidation()) {
                                pWaveKws.resumeSound();
                            }
                        }
                    });


                    item.setFont(new Font("Courier New", Font.PLAIN, 15));
                    item.setBackground(Color.white);
                    pWaveKws.menu.add(item);
                }
            }


            if (pWaveKws.playback.line != null) {
                if (pWaveKws.playback.line.isRunning() || pWaveKws.playback.line.isOpen() || pWaveKws.playback.line.isActive()) {

                    item = new JMenuItem(" Stop ");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (sourceAvailValidation()) {
                                pWaveKws.stopSound();
                            }
                        }
                    });


                    item.setFont(new Font("Courier New", Font.PLAIN, 15));
                    item.setBackground(Color.white);
                    pWaveKws.menu.add(item);
                }
            }

            if (pWaveKws.mousePosX1 != 0 && pWaveKws.mousePosX2 != 0 && pWaveKws.audioInputStream != null) {

                item = new JMenuItem(" Cut ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //  cutWave();
                            if (pWaveKws.mousePosX1 == 0 && pWaveKws.mousePosX2 == 0) {
                                return;
                            }
                            getSamplingPositions();
                            int startSample = getStartSamples();
                            int endSample = getEndSamples();

                            System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWaveKws.frames_per_pixel);

                            CutAudioWave cutW = new CutAudioWave();
                            cutW.cutPortion(pWaveKws.streamBytes.getCurrent(), startSample, endSample);

                            if (cutW.getresultByteArray() == null) {
                                return;
                            }
                            pWaveKws.streamBytes.setCurrent(cutW.getresultByteArray());
                            pWaveKws.setSreemDrawGraph();

                        } catch (Exception ex) {
                            Logger.getLogger(RightClickEventKws.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                //pWaveKws.menu.add(item);
            }

            if (pWaveKws.mousePosX1 != 0 && pWaveKws.mousePosX2 != 0 && pWaveKws.audioInputStream != null) {
                item = new JMenuItem(" Copy ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //  cutWave();
                            if (pWaveKws.mousePosX1 == 0 && pWaveKws.mousePosX2 == 0) {
                                return;
                            }
                            getSamplingPositions();
                            int startSample = getStartSamples();
                            int endSample = getEndSamples();

                            System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWaveKws.frames_per_pixel);

                            CutAudioWave cutW = new CutAudioWave();
                            cutW.cutPortion(pWaveKws.streamBytes.getCurrent(), startSample, endSample);

                            if (cutW.getresultByteArray() == null) {
                                return;
                            }


                        } catch (Exception ex) {
                            Logger.getLogger(RightClickEventKws.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                // pWaveKws.menu.add(item);
            }


            if (pWaveKws.audioInputStream != null) {
                item = new JMenuItem(" Save As... ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String fileName = saveLocation();
                        if (pWaveKws.mousePosX1 != 0 && pWaveKws.mousePosX2 != 0) {
                            getSamplingPositions();
                            int startSample = getStartSamples();
                            int endSample = getEndSamples();

                            System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWaveKws.frames_per_pixel);

                            SelectedAudioWave selectW = new SelectedAudioWave();
                            selectW.selectdPortion(pWaveKws.streamBytes.getCurrent(), startSample, endSample);

                            if (selectW.getSelectedByteArray() == null) {
                                return;
                            }
                            StreamConverter.byteTowavefile(selectW.getSelectedByteArray(), pWaveKws.audioInputStream, fileName);
                        } else {
                            if (pWaveKws.streamBytes.getCurrent() != null && fileName != null) {
                                StreamConverter.byteTowavefile(pWaveKws.streamBytes.getCurrent(), pWaveKws.audioInputStream, fileName);
                            }
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                pWaveKws.menu.add(item);
            }

            if (pWaveKws.audioInputStream != null) {
                item = new JMenuItem(" Export ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String fileName = saveLocation();

                        List lines = pWaveKws.keyBuilder.getFileContent();
                        if (lines == null) {
                            return;
                        }
                        String backPhone = fileName.replace(".wav", ".kws");
                        new ExportKws().startProcess(lines, backPhone, pWaveKws.fileHashValue, pWaveKws.language);

                        if (pWaveKws.streamBytes.getCurrent() != null && fileName != null) {
                            StreamConverter.byteTowavefile(pWaveKws.streamBytes.getCurrent(), pWaveKws.audioInputStream, fileName);
                        }
                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                pWaveKws.menu.add(item);
            }

            //Zoom Menu
            if (pWaveKws.audioInputStream != null) {
                pWaveKws.menu.add(zoom);
            }
            //End
            if (pWaveKws.audioInputStream != null) {
                item = new JMenuItem(" Original Wave ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (pWaveKws.streamBytes.getOriginal() == null) {
                            return;
                        }

                        pWaveKws.streamBytes.setCurrent(pWaveKws.streamBytes.getOriginal());
                        pWaveKws.setSreemDrawGraph();

                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                //  pWaveKws.menu.add(item);
            }

            //Annotation
       /* if (pWaveKws.audioInputStream != null) {
             pWaveKws.menu.add(annotation);
             }*/
            if (pWaveKws.audioInputStream != null) {
                item = new JMenuItem(" Create Lable ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        createAnnotation();

                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                pWaveKws.menu.add(item);
            }

            //Transcription
            if (pWaveKws.audioInputStream != null) {
                item = new JMenuItem(" Transcription ");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (pWaveKws.streamBytes.getCurrent() == null) {
                            return;
                        }

                        pWaveKws.mainFrame.createKwsTranscrption(pWaveKws);

                    }
                });
                item.setFont(new Font("Courier New", Font.PLAIN, 15));
                item.setBackground(Color.white);
                pWaveKws.menu.add(item);
            }
            //Add key words
            if (pWaveKws.audioInputStream != null) {
                pWaveKws.menu.add(keyword);
            }


            item = new JMenuItem(" Set keywords Color ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        // new KeywordColor(pWaveKws).setVisible(true);
                        if (pWaveKws.keyBuilder.getKeywordsList() != null) {
                            if (pWaveKws.keyBuilder.getKeywordsList().size() > 0) {
                                KeywordsColor.createAndShowGUI(Filters.uniqueValueFromList(pWaveKws.keyBuilder.getKeywordsList()), pWaveKws);
                            }
                        }
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            keyword.add(item);

            item = new JMenuItem(" Set default Color ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        // new KeywordColor(pWaveKws).setVisible(true);
                        setDefaultKeywordColor();
                    }
                }
            });
            item.setFont(new Font("Courier New", Font.PLAIN, 15));
            item.setBackground(Color.white);
            keyword.add(item);




            item = new JMenuItem(" Normal ");
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sourceAvailValidation()) {
                        pWaveKws.setSreemDrawGraph();
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
                        pWaveKws.setZoomIn();
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
                        pWaveKws.setZoomOut();
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
            int width = pWaveKws.samplingGraph.getSize().width;
            int inPos = (int) pWaveKws.mousePosX1, enPos = (int) pWaveKws.mousePosX2;

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

            double bytesPerSecond = pWaveKws.audioInputStream.getFormat().getFrameSize() * (double) pWaveKws.audioInputStream.getFormat().getFrameRate();
            int startTime = ((inPos * pWaveKws.frames_per_pixel) * pWaveKws.audioInputStream.getFormat().getFrameSize());
            int endTime = ((enPos * pWaveKws.frames_per_pixel) * pWaveKws.audioInputStream.getFormat().getFrameSize());
            // System.out.println("CK " + ((inPos * pWaveKws.frames_per_pixel) / pWaveKws.audioInputStream.getFormat().getFrameRate()) * 1000 + " " + ((enPos * pWaveKws.frames_per_pixel) / pWaveKws.audioInputStream.getFormat().getFrameRate()) * 1000);
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

    public synchronized void playSoundAll() {
        try {
            if (pWaveKws.playback.line != null) {
                if (pWaveKws.playback.line.isRunning() || pWaveKws.playback.line.isOpen()) {
                    return;
                }
            }
            if (pWaveKws.mousePosX1 != 0 && pWaveKws.mousePosX2 != 0) {
                getSamplingPositions();
                int startSample = getStartSamples();
                int endSample = getEndSamples();

                System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWaveKws.frames_per_pixel);

                SelectedAudioWave selectW = new SelectedAudioWave();
                selectW.selectdPortion(pWaveKws.streamBytes.getCurrent(), startSample, endSample);

                if (selectW.getSelectedByteArray() == null) {
                    return;
                }
                pWaveKws.streamBytes.setSelectedPlay(selectW.getSelectedByteArray());
                pWaveKws.selectedPlay = true;
                pWaveKws.streamVariable.setSecondStarts(startSample / pWaveKws.audioInputStream.getFormat().getFrameRate());
                pWaveKws.playSound();
            } else if (pWaveKws.mousePosX1 != 0) {

                int inPos = (int) pWaveKws.mousePosX1, enPos = (int) ((pWaveKws.samplingGraph.getSize().width) - 10);

                if (inPos < 1) {
                    inPos = 1;
                }

                if (enPos < 1) {
                    enPos = 1;
                }
                if (inPos > (enPos - 10)) {
                    inPos = enPos - 10;
                }

                double bytesPerSecond = pWaveKws.audioInputStream.getFormat().getFrameSize() * (double) pWaveKws.audioInputStream.getFormat().getFrameRate();
                int startTime = ((inPos * pWaveKws.frames_per_pixel) * pWaveKws.audioInputStream.getFormat().getFrameSize());
                int endTime = ((enPos * pWaveKws.frames_per_pixel) * pWaveKws.audioInputStream.getFormat().getFrameSize());
                System.out.println("CK " + ((inPos * pWaveKws.frames_per_pixel) / pWaveKws.audioInputStream.getFormat().getFrameRate()) * 1000 + " " + ((enPos * pWaveKws.frames_per_pixel) / pWaveKws.audioInputStream.getFormat().getFrameRate()) * 1000);
                int startSample, endSample;
                if (startTime > endTime) {
                    startSample = endTime;
                    endSample = startTime;
                } else {
                    startSample = startTime;
                    endSample = endTime;
                }

                System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWaveKws.frames_per_pixel);

                SelectedAudioWave selectW = new SelectedAudioWave();
                selectW.selectdPortion(pWaveKws.streamBytes.getCurrent(), startSample, endSample);

                if (selectW.getSelectedByteArray() == null) {
                    return;
                }
                pWaveKws.streamBytes.setSelectedPlay(selectW.getSelectedByteArray());
                pWaveKws.selectedPlay = true;
                pWaveKws.streamVariable.setSecondStarts(startSample / pWaveKws.audioInputStream.getFormat().getFrameRate());
                pWaveKws.playSound();
            } else {
                pWaveKws.selectedPlay = false;
                pWaveKws.playSound();
            }
        } catch (Exception er) {
            System.err.println(er);
        }
    }

    public void playFromSoundAll() {
        try {
            if (pWaveKws.mousePosX1 != 0) {

                int inPos = (int) pWaveKws.mousePosX1, enPos = (int) ((pWaveKws.samplingGraph.getSize().width) - 10);

                if (inPos < 1) {
                    inPos = 1;
                }

                if (enPos < 1) {
                    enPos = 1;
                }
                if (inPos > (enPos - 10)) {
                    inPos = enPos - 10;
                }

                double bytesPerSecond = pWaveKws.audioInputStream.getFormat().getFrameSize() * (double) pWaveKws.audioInputStream.getFormat().getFrameRate();
                int startTime = ((inPos * pWaveKws.frames_per_pixel) * pWaveKws.audioInputStream.getFormat().getFrameSize());
                int endTime = ((enPos * pWaveKws.frames_per_pixel) * pWaveKws.audioInputStream.getFormat().getFrameSize());
                System.out.println("CK " + ((inPos * pWaveKws.frames_per_pixel) / pWaveKws.audioInputStream.getFormat().getFrameRate()) * 1000 + " " + ((enPos * pWaveKws.frames_per_pixel) / pWaveKws.audioInputStream.getFormat().getFrameRate()) * 1000);
                int startSample, endSample;
                if (startTime > endTime) {
                    startSample = endTime;
                    endSample = startTime;
                } else {
                    startSample = startTime;
                    endSample = endTime;
                }

                System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramePix " + pWaveKws.frames_per_pixel);

                SelectedAudioWave selectW = new SelectedAudioWave();
                selectW.selectdPortion(pWaveKws.streamBytes.getCurrent(), startSample, endSample);

                if (selectW.getSelectedByteArray() == null) {
                    return;
                }
                pWaveKws.streamBytes.setSelectedPlay(selectW.getSelectedByteArray());
                pWaveKws.selectedPlay = true;
                pWaveKws.streamVariable.setSecondStarts(startSample / pWaveKws.audioInputStream.getFormat().getFrameRate());
                pWaveKws.playSound();
            } else {
                pWaveKws.selectedPlay = false;
                pWaveKws.playSound();
            }
        } catch (Exception er) {
            System.err.println(er);
        }
    }

    public void createAnnotation() {
        try {
            if (pWaveKws.mousePosX1 != 0 && pWaveKws.mousePosX2 != 0) {
                PixcelConversion pixConversion = new PixcelConversion();
                int anStPos = 0;
                int anEnPos = 0;
                if (pWaveKws.mousePosX1 > pWaveKws.mousePosX2) {
                    anStPos = (int) pWaveKws.mousePosX2;
                    anEnPos = (int) pWaveKws.mousePosX1;
                } else {
                    anStPos = (int) pWaveKws.mousePosX1;
                    anEnPos = (int) pWaveKws.mousePosX2;
                }
                anStPos = pixConversion.pixcelToMillisecond(anStPos, pWaveKws.frames_per_pixel, (int) pWaveKws.audioInputStream.getFormat().getFrameRate());
                anEnPos = pixConversion.pixcelToMillisecond(anEnPos, pWaveKws.frames_per_pixel, (int) pWaveKws.audioInputStream.getFormat().getFrameRate());

                FindAnnotated findAnn = new FindAnnotated(pWaveKws.mainFrame.getConn(), pWaveKws.fileHashValue, anStPos, anEnPos);

                if (findAnn.findAnnationAll()) {
                    javax.swing.JOptionPane.showMessageDialog(pWaveKws, "Lable already created :" + findAnn.findAnnationAll(), "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                pWaveKws.mainFrame.createAnnotationInternalFrame("Annotation", anStPos, anEnPos, pWaveKws.fileHashValue, pWaveKws);

            }
        } catch (Exception er) {
            System.err.println(er);
        }

    }

    public void resizeandAddannotation() {
        try {
            if (pWaveKws.mousePosX1 != 0 && pWaveKws.mousePosX2 != 0 && pWaveKws.mousePosY1 > 170) {
                try {
                    PixcelConversion pixConversion = new PixcelConversion();
                    int anStPos = 0;
                    int anEnPos = 0;
                    if (pWaveKws.mousePosX1 > pWaveKws.mousePosX2) {
                        anStPos = (int) pWaveKws.mousePosX2;
                        anEnPos = (int) pWaveKws.mousePosX1;
                    } else {
                        anStPos = (int) pWaveKws.mousePosX1;
                        anEnPos = (int) pWaveKws.mousePosX2;
                    }
                    anStPos = pixConversion.pixcelToMillisecond(anStPos, pWaveKws.frames_per_pixel, (int) pWaveKws.audioInputStream.getFormat().getFrameRate());
                    anEnPos = pixConversion.pixcelToMillisecond(anEnPos, pWaveKws.frames_per_pixel, (int) pWaveKws.audioInputStream.getFormat().getFrameRate());


                    FindAnnotated findAnn = new FindAnnotated(pWaveKws.mainFrame.getConn(), pWaveKws.fileHashValue, anStPos, anEnPos);

                    if (findAnn.isAnnotatedBothPosiotion() == true) {
                        javax.swing.JOptionPane.showMessageDialog(pWaveKws, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);

                        return;
                    }

                    if (!findAnn.findResizeAnnation()) {
                        javax.swing.JOptionPane.showMessageDialog(pWaveKws, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }
                    if (findAnn.findNumberOfMatch() > 1) {
                        javax.swing.JOptionPane.showMessageDialog(pWaveKws, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }
                    if (findAnn.getAnnotationStartPos() == 0 || findAnn.getAnnotationEndPos() == 0) {
                        javax.swing.JOptionPane.showMessageDialog(pWaveKws, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }

                    int ann_actualStPos = findAnn.getAnnotationStartPos();
                    int ann_actualEnPos = findAnn.getAnnotationEndPos();
                    String ann_allow = findAnn.getAllow();
                    String ann_level = findAnn.getLevel();

                    int dragStPos = (int) pWaveKws.mousePosX1;
                    int dragEnPos = (int) pWaveKws.mousePosX2;

                    int ann_actualStPixPos = pixConversion.milliSecondToPixcel(Integer.toString(ann_actualStPos), pWaveKws.frames_per_pixel, (int) pWaveKws.audioInputStream.getFormat().getFrameRate());
                    int ann_actualEnPixPos = pixConversion.milliSecondToPixcel(Integer.toString(ann_actualEnPos), pWaveKws.frames_per_pixel, (int) pWaveKws.audioInputStream.getFormat().getFrameRate());

                    if (!(Math.abs(dragStPos - ann_actualStPixPos) < 15 || Math.abs(dragStPos - ann_actualEnPixPos) < 15)) {
                        javax.swing.JOptionPane.showMessageDialog(pWaveKws, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                        allAnnReturnSetUp();
                        return;
                    }
                    if ((Math.abs(dragStPos - ann_actualStPixPos)) < 15) {
                        if (dragEnPos > ann_actualEnPixPos) {
                            javax.swing.JOptionPane.showMessageDialog(pWaveKws, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            allAnnReturnSetUp();
                            return;
                        }


                    } else {
                        if (dragEnPos < ann_actualStPixPos) {
                            javax.swing.JOptionPane.showMessageDialog(pWaveKws, "Invalid position", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            allAnnReturnSetUp();
                            return;
                        }
                    }
                    if (ann_allow != null) {
                        ann_allow = ann_allow.trim();
                        try {
                            if (Integer.parseInt(ann_allow) > pWaveKws.mainFrame.getUserRoll()) {
                                javax.swing.JOptionPane.showMessageDialog(pWaveKws, "Senior has verified this region", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                                allAnnReturnSetUp();
                                return;
                            }
                        } catch (NumberFormatException er) {
                            System.err.println(er);
                        }
                    }

                    if ((Math.abs(dragStPos - ann_actualStPixPos)) < 15) {
                        int changeStarPos = pixConversion.pixcelToMillisecond(dragEnPos, pWaveKws.frames_per_pixel, (int) pWaveKws.audioInputStream.getFormat().getFrameRate());
                        int changeEndPos = ann_actualEnPos;
                        int option = JOptionPane.showConfirmDialog(pWaveKws, "Are you sure you want to update ", "Update", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            boolean updated = new UpdateAnnotation(pWaveKws.mainFrame.getConn()).resizeUpdate(ann_actualStPos, ann_actualEnPos, changeStarPos, changeEndPos, pWaveKws.fileHashValue, pWaveKws.mainFrame.getUserID(), ann_level);
                            if (!updated) {
                                javax.swing.JOptionPane.showMessageDialog(pWaveKws, "Unable to update", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            } else {
                                pWaveKws.samplingGraph.createWaveForm(null);
                                pWaveKws.mainFrame.pWave.loadWaveFromRemote();
                                int changeStrarPixPos = pixConversion.milliSecondToPixcel(Integer.toString(changeStarPos), pWaveKws.frames_per_pixel, (int) pWaveKws.audioInputStream.getFormat().getFrameRate());
                                pWaveKws.mainFrame.pWave.scrollWaveFromRemote(changeStrarPixPos);
                            }
                        }
                    } else {
                        int changeStarPos = ann_actualStPos;
                        int changeEndPos = pixConversion.pixcelToMillisecond(dragEnPos, pWaveKws.frames_per_pixel, (int) pWaveKws.audioInputStream.getFormat().getFrameRate());
                        int option = JOptionPane.showConfirmDialog(pWaveKws, "Are you sure you want to update ", "Update", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            boolean updated = new UpdateAnnotation(pWaveKws.mainFrame.getConn()).resizeUpdate(ann_actualStPos, ann_actualEnPos, changeStarPos, changeEndPos, pWaveKws.fileHashValue, pWaveKws.mainFrame.getUserID(), ann_level);
                            if (!updated) {
                                javax.swing.JOptionPane.showMessageDialog(pWaveKws, "Unable to update", "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                            } else {
                                pWaveKws.samplingGraph.createWaveForm(null);
                                pWaveKws.mainFrame.pWave.loadWaveFromRemote();
                                int changeStrarPixPos = pixConversion.milliSecondToPixcel(Integer.toString(changeStarPos), pWaveKws.frames_per_pixel, (int) pWaveKws.audioInputStream.getFormat().getFrameRate());
                                pWaveKws.mainFrame.pWave.scrollWaveFromRemote(changeStrarPixPos);
                            }
                        }
                    }

                } catch (Exception er) {
                    System.err.println(er);
                }


                // pWaveKws.mainFrame.createAnnotationInternalFrame("Annotation", anStPos, anEnPos);


            }

            if (pWaveKws.mousePosX1 != 0 && pWaveKws.mousePosX2 == 0) {

                int ms = new PixcelConversion().pixcelToMillisecond((int) pWaveKws.mousePosX1, pWaveKws.frames_per_pixel, (int) pWaveKws.audioInputStream.getFormat().getFrameRate());
                if (ms == 0) {
                    return;
                }

                int[] ann_pos = new GetAnnotation(pWaveKws.mainFrame.getConn()).getUniqueSelect(ms, pWaveKws.fileHashValue);
                if (ann_pos == null) {
                    return;
                }
                if (ann_pos.length != 2) {
                    return;
                }
                if (ann_pos[0] == 0 || ann_pos[1] == 1) {
                    return;
                }

                pWaveKws.mainFrame.createAnnotationInternalFrame("Annotation", ann_pos[0], ann_pos[1], pWaveKws.fileHashValue, pWaveKws);
                pWaveKws.mousePosX1 = 0;
            }
            allAnnReturnSetUp();
        } catch (Exception er) {
            System.err.println(er);
        }
    }

    private void allAnnReturnSetUp() {
        pWaveKws.mousePosX1 = 0;
        pWaveKws.mousePosX2 = 0;
        pWaveKws.samplingGraph.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        pWaveKws.samplingGraph.repaint();
    }

    public void setDefaultKeywordColor() {
        try {
            if (pWaveKws.keyBuilder.getKeywordsList() != null) {
                if (pWaveKws.keyBuilder.getKeywordsList().size() > 0) {
                    List keyWord = pWaveKws.keyBuilder.getKeywordsList();
                    for (int i = 0; i < keyWord.size(); i++) {
                        if (keyWord.get(i) == null) {
                            return;
                        }

                        if (pWaveKws.keyBuilder.getKeywordsColor() == null) {
                            return;
                        } else {
                            pWaveKws.keyBuilder.getKeywordsColor().put(keyWord.get(i), new Color(165, 42, 42));
                            pWaveKws.samplingGraph.repaint();
                        }
                    }
                }
            }
        } catch (Exception er) {
            System.out.println(er);
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
        if (pWaveKws.audioInputStream == null) {
            return false;
        }
        if (pWaveKws.streamBytes.getCurrent() == null) {
            return false;
        }
        if (pWaveKws.streamBytes.getCurrent().length < 10) {
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
}
