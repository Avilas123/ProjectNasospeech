/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.phoneme;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tatapower SED
 *
 */
public class KyeColorHandler {

    private int frame_per_pixel;
    private List startPixel;
    private List endPixel;
    private PlotWavePhoneme pWave;
    private List keyWordList;

    public KyeColorHandler(PlotWavePhoneme pWave) {

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
        BigInteger startMicroSecond, endMicroSecond;
        int keyStartSamples = 0, keyStartPixel = 0, keyEndSamples = 0, keyEndPixel = 0;
        for (int i = 0; i < pWave.keyBuilder.getStartTimeList().size(); i++) {
            try {
                if (pWave.keyBuilder.getStartTimeList().get(i) == null) {
                    continue;
                }

                keyStartSamples = microSecondToSamples(pWave.keyBuilder.getStartTimeList().get(i).toString());

                keyStartPixel = samplesToPixels(keyStartSamples);
                if (keyStartPixel < 1) {
                    continue;
                }
                keyEndSamples = microSecondToSamples(pWave.keyBuilder.getEndTimeList().get(i).toString());
                ;

                keyEndPixel = samplesToPixels(keyEndSamples);

                if (keyEndPixel < 1) {
                    continue;
                }

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
                System.err.println("Number Formate Error " + e);
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
        BigInteger microSecond = new BigInteger(ms);
        int formate = (int) pWave.audioInputStream.getFormat().getFrameRate();
        BigInteger sampleRate = new BigInteger(Integer.toString(formate));
        BigInteger devided = new BigInteger("10000000");
        samples = Integer.parseInt(((sampleRate.multiply(microSecond)).divide((devided)).toString()));
//System.out.println(samples);
        return samples;
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
