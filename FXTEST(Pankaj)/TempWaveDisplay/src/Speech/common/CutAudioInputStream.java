/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.common;

/**
 *
 * @author Tatapower SED
 *
 */
public class CutAudioInputStream {

    public static byte[] cutWave = null;

    public static void setCutWave(byte[] cutStream) {
        CutAudioInputStream.cutWave = cutStream;
    }

    public static byte[] getCutWave() {
        return CutAudioInputStream.cutWave;
    }
}
