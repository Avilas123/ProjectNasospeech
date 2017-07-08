/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.kws;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tatapower SED
 *
 */
public class KeyColorHandler {

    private int frame_per_pixel;
    private List startPixel;
    private List endPixel;
    private PlotWaveKws pWave;
    private List keyWordList;
    private int colorStart = 0, colorEnd = 0;

    public KeyColorHandler(PlotWaveKws pWave) {

        this.pWave = pWave;
    }

    public boolean isKeywordFind() {

        setStartPixel(null);
        setEndPixel(null);
        setKeyWordName(null);
        if (!isStartingPoint()) {
            return false;
        }
        if (getStartPixel() == null || getEndPixel() == null) {
            return false;
        }

        return true;
    }

    public boolean sourceValidation(int frame_per_pixel) {

        if (frame_per_pixel < 1) {
            return false;
        }
        if (pWave.keyBuilder == null) {
            return false;
        }
        if (pWave.keyBuilder.getStartTimeList() == null) {
            return false;
        }
        if (pWave.keyBuilder.getEndTimeList() == null) {
            return false;
        }
        if (pWave.keyBuilder.getKeywordsList() == null) {
            return false;
        }

        if (!((pWave.keyBuilder.getStartTimeList().size() == pWave.keyBuilder.getEndTimeList().size()) && (pWave.keyBuilder.getEndTimeList().size() == pWave.keyBuilder.getKeywordsList().size()))) {
            return false;
        }
        this.frame_per_pixel = frame_per_pixel;
        return true;
    }

    private boolean isStartingPoint() {
        List stratSampleList = new ArrayList();
        List endSampleList = new ArrayList();
        List keywordNameList = new ArrayList();
        String keyWordStr = null;

        int keyStartSamples = 0, keyStartPixel = 0, keyEndSamples = 0, keyEndPixel = 0;
        for (int i = 0; i < pWave.keyBuilder.getStartTimeList().size(); i++) {
            try {
                if (pWave.keyBuilder.getStartTimeList().get(i) == null) {
                    continue;
                }

                keyStartSamples = microSecondToSamples(pWave.keyBuilder.getStartTimeList().get(i).toString());

                keyStartPixel = samplesToPixels(keyStartSamples);

                keyEndSamples = microSecondToSamples(pWave.keyBuilder.getEndTimeList().get(i).toString());


                keyEndPixel = samplesToPixels(keyEndSamples);



                keyWordStr = pWave.keyBuilder.getKeywordsList().get(i).toString();

                if (keyWordStr == null) {
                    continue;
                }
                keyWordStr = keyWordStr.trim();
                if (keyWordStr.length() < 1) {
                    continue;
                }



                stratSampleList.add(keyStartPixel);
                endSampleList.add(keyEndPixel);
                keywordNameList.add(keyWordStr);
            } catch (NumberFormatException e) {
                System.err.println("Number Formate Error");
            }
        }
        if (stratSampleList == null || endSampleList == null || keywordNameList == null) {
            return false;
        }
        if (stratSampleList.size() < 1 || endSampleList.size() < 1 || keywordNameList.size() < 1) {
            return false;
        }
        setStartPixel(stratSampleList);
        setEndPixel(endSampleList);
        setKeyWordName(keywordNameList);
        return true;
    }

    private int samplesToPixels(int pixel) {
        int sample = 0;
        sample = (int) (pixel / frame_per_pixel);
        return sample;
    }

    private int microSecondToSamples(String ms) {


        int samples = 0;
        try {
            BigInteger microSecond = new BigInteger(ms);
            int formate = (int) pWave.audioInputStream.getFormat().getFrameRate();
            BigInteger sampleRate = new BigInteger(Integer.toString(formate));
            BigInteger devided = new BigInteger("10000000");
            samples = Integer.parseInt(((sampleRate.multiply(microSecond)).divide((devided)).toString()));
        } catch (NumberFormatException e) {
            System.err.println("Number Formate Error");

        } catch (Exception er) {
            System.err.println(er);
        }
        return samples;

    }

    public void setColorIndex(int startPosPix, int endPosPix) {
        try {
            startPosPix = startPosPix - 1000;
            endPosPix = endPosPix + 1000;
            if (startPosPix < 0) {
                startPosPix = 0;
            }
            colorStart = 0;
            colorEnd = 0;

            if (getStartPixel().size() < 25) {
                colorStart = 0;
                colorEnd = getStartPixel().size();
            } else {

                for (int in = 0; in < getStartPixel().size(); in++) {
                    int keyStPos = Integer.parseInt((getStartPixel().get(in)).toString());
                    int keyEnPos = Integer.parseInt((getEndPixel().get(in)).toString());
                    if ((startPosPix <= keyStPos && endPosPix >= keyStPos) || (startPosPix <= keyEnPos && endPosPix >= keyEnPos)) {


                        if (colorStart == 0 && colorEnd == 0) {
                            colorEnd = in;
                            colorStart = in;
                        }

                        if (colorStart > in) {
                            colorStart = in;
                        } else {
                            if (colorEnd <= in) {
                                colorEnd = in;
                            }
                        }

                    }

                }


                if (colorStart > 5) {
                    colorStart = colorStart - 5;

                } else {
                    colorStart = 0;
                }

                if ((colorEnd + 5) < getStartPixel().size()) {
                    colorEnd = colorEnd + 5;
                } else {
                    colorEnd = getStartPixel().size();
                }

                if (colorStart == 0 && colorEnd == 0) {
                    colorStart = 0;
                    colorEnd = getStartPixel().size();
                }

                if (colorStart < 0) {
                    colorStart = 0;
                }
                if (colorEnd > getStartPixel().size()) {
                    colorEnd = ((getStartPixel().size()));
                }
            }
        } catch (Exception er) {
            System.out.println(er);
        }


    }

    public int getColorStart() {
        return colorStart;
    }

    public int getColorEnd() {
        return colorEnd;
    }

    public List getEndPixel() {
        return endPixel;
    }

    public void setEndPixel(List endPixel) {
        this.endPixel = endPixel;
    }

    public List getKeyWordName() {
        return keyWordList;
    }

    public void setKeyWordName(List keyWordName) {
        this.keyWordList = keyWordName;
    }

    public List getStartPixel() {
        return startPixel;
    }

    public void setStartPixel(List startPixel) {
        this.startPixel = startPixel;
    }
}
