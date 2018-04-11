/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing_phase;
import javax.sound.sampled.*;
import java.io.*;

/**
 *
 * @author nazibur
 */

public class JavaSoundRecorder {
	
	AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

	// the line from which audio data is captured
	public static TargetDataLine line;
        public static ByteArrayOutputStream recordBytes;
        public static Boolean isRunning;

	/**
	 * Defines an audio format
	 */
	AudioFormat getAudioFormat() {
		float sampleRate = 8000;
		int sampleSizeInBits = 16;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = false;
		AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,channels, signed, bigEndian);
		return format;
	}

	/**
	 * Captures the sound and record into a WAV file
	 */
	void start(String filePath) {
		try {
			File fileName=new File (filePath);
			AudioFormat format = getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

			// checks if system supports the data line
			if (!AudioSystem.isLineSupported(info)) {
				System.out.println("Line not supported");
				System.exit(0);
			}
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
                        
                        
                        
                        recordBytes = new ByteArrayOutputStream();
                        int frameSizeInBytes = format.getFrameSize();
                        int bufferLengthInFrames = line.getBufferSize() / 8;
                        int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
                        byte[] data = new byte[bufferLengthInBytes];
                        int numBytesRead, noofwrites = 1;
                        
                        
                        
			line.start();	// start capturing
                        System.out.println("Start capturing...");
                        
                        isRunning=true;
                        while (isRunning)
                        {
                        numBytesRead = line.read(data, 0, bufferLengthInBytes);
                        recordBytes.write(data, 0, numBytesRead);
                        }
                        
                        byte audioBytes[] = recordBytes.toByteArray();
                        ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
                        AudioInputStream audioInputStream = new AudioInputStream(bais, format,audioBytes.length / format.getFrameSize());
                        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, fileName);
                        audioInputStream.close();
                        recordBytes.close();
                        
			

			//AudioInputStream ais = new AudioInputStream(line);

			System.out.println("Done recording...");

			// start recording
			//AudioSystem.write(ais, fileType, fileName);

		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Closes the target data line to finish capturing and recording
	 */
	void finish() 
        {
           isRunning=false;
           if (line != null) 
           {
            line.drain();
            line.close();
        }
           //line.drain();
           //line.close();
           System.out.println("Finished");
	}
}
