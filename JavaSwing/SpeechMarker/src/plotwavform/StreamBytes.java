/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




package plotwavform;

/**
 *
 * @author nazibur
 */

public class StreamBytes {

    private byte[] original = null;
    private byte[] current = null;
    private byte[] playSelected = null;

    public StreamBytes() {
    }

    public void setOriginal(byte[] audioBytes) {
        this.original = audioBytes;
    }

    public byte[] getOriginal() {
        return this.original;
    }

    public void setCurrent(byte[] audioBytes) {
        this.current = audioBytes;
    }

    public byte[] getCurrent() {
        return this.current;
    }

    public void setSelectedPlay(byte[] audioBytes) {
        this.playSelected = audioBytes;
    }

    public byte[] getSelectedPlay() {
        return this.playSelected;
    }
}
