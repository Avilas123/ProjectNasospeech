/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.common;

import java.math.BigInteger;

/**
 *
 * @author Tatapower SED
 *
 */
public class SampleConversion {

    public int milliSecondToSamples(String ms, int formate) {

        int samples = 0;
        try {

            BigInteger microSecond = new BigInteger(ms.trim());
            BigInteger sampleRate = new BigInteger(Integer.toString(formate));
            BigInteger devided = new BigInteger("1000");
            samples = Integer.parseInt(((sampleRate.multiply(microSecond)).divide((devided)).toString()));

        } catch (Exception er) {
            System.err.println(er);
        }
        return samples;
    }

    public int samplesToMillisecond(int samples, int formate, int frameSize) {

        int milliSecond = 0;
        try {

            milliSecond = (int) ((samples * 1000) / (formate * frameSize));

        } catch (Exception er) {
            System.err.println(er);
        }
        return milliSecond;
    }
}
