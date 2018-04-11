/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author Naso
 */
import java.io.*;
import javax.sound.sampled.*;

class AudioFileProcessor {

  public static void main(String[] args) {
    copyAudio("C:\\Users\\Naso\\Desktop\\pankaj.wav", "C:\\Users\\Naso\\Desktop\\BHemanthKumar_s5.wav", 17, 24);
  }

  public static void copyAudio(String sourceFileName, String destinationFileName, int startSecond, int secondsToCopy) {
    AudioInputStream inputStream = null;
    AudioInputStream shortenedStream = null;
    try {
      File file = new File(sourceFileName);
      AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
      AudioFormat format = fileFormat.getFormat();
      inputStream = AudioSystem.getAudioInputStream(file);
      int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
      int name = startSecond * bytesPerSecond;
        
      inputStream.skip(name);
      long framesOfAudioToCopy = secondsToCopy * (int)format.getFrameRate();
      shortenedStream = new AudioInputStream(inputStream, format, framesOfAudioToCopy);
      File destinationFile = new File(destinationFileName);
      AudioSystem.write(shortenedStream, fileFormat.getType(), destinationFile);
        System.out.println("completed");
    } catch (Exception e) {
      println(e);
    } finally {
      if (inputStream != null) try { inputStream.close(); } catch (Exception e) { println(e); }
      if (shortenedStream != null) try { shortenedStream.close(); } catch (Exception e) { println(e); }
    }
  }

  public static void println(Object o) {
    System.out.println(o);
  }

  public static void print(Object o) {
    System.out.print(o);
  }

}