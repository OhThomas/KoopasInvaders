package com.game.src.main;import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Handles playing, stopping, and looping of sounds for the game.
 * @author Tyler Thomas @edited by Thomas Rader
 *
 */
public class SoundLoops {
    private Clip clip;
    private boolean SoundLoopBoolean = false;
	private FloatControl gainControl;
	private BooleanControl booleanControl;
	private float volume;
	private float defaultVolume;


    public final int NOT_SPECIFIED = AudioSystem.NOT_SPECIFIED; // -1
    public final int INT_SIZE = 4;
    private int sampleSize = NOT_SPECIFIED;
    private long framesCount = NOT_SPECIFIED;
    private int sampleRate = NOT_SPECIFIED;
    private int channelsNum;
    private byte[] data;      // wav bytes
    private AudioInputStream ais;
    private AudioFormat af;
    public SoundLoops(String fileName) {
        // specify the sound to play
        // (assuming the sound can be played by the audio system)
        // from a wave File
        try {
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
             // load the sound into memory (a Clip)
                clip = AudioSystem.getClip();
                clip.open(sound);
        		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        		booleanControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
        		booleanControl.setValue(false);
        		defaultVolume = gainControl.getValue();
        		
        		
                ais = AudioSystem.getAudioInputStream(file);
                af = ais.getFormat();
                framesCount = ais.getFrameLength();
                sampleRate = (int) af.getSampleRate();
                sampleSize = af.getSampleSizeInBits() / 8;
                channelsNum = af.getChannels();
                long dataLength = framesCount * af.getSampleSizeInBits() * af.getChannels() / 8;
                data = new byte[(int) dataLength];
                ais.read(data);

            }
            else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            }
            
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }

    // play, stop, loop the sound clip
    }
    public void play(){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }
    public void continuePlaying(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
            clip.stop();
    }
    public void loopSegmentFromStart(int x, int y){
    	clip.setLoopPoints(x, y);
    	clip.setFramePosition(0);
    	clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void loopSegment(int x, int y){
    	clip.setLoopPoints(x, y);
    	clip.setFramePosition(x);
    	clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void reduceSound(){
    	this.volume -= 5.0f;
    	this.gainControl.setValue(volume);
    }
    public void increaseSound(){
    	this.volume += 5.0f;
    	this.gainControl.setValue(volume);
    }
    public void reduceSound(float v){
    	this.volume -= v * 1.0f;
    	this.gainControl.setValue(volume);
    }
    public void increaseSound(float v){
    	this.volume += v * 1.0f;
    	this.gainControl.setValue(volume);
    }
    public float getVolume(){
    	return this.gainControl.getValue();
    }
    public void setVolume(float v){
    	this.volume = v;
    	this.gainControl.setValue(volume);
    }
    public void resetVolume() {
    	this.gainControl.setValue(defaultVolume);
    }
    public void shiftVolume(float from, float to, int microseconds) {
    	this.gainControl.shift(from, to, microseconds);
    }
    public float minimumVolume() {
    	return this.gainControl.getMinimum();
    }
    public void mute() {
    	this.booleanControl.setValue(true);
    }
    public void unMute() {
    	this.booleanControl.setValue(false);
    }
    public long getLongFramePosition(){
    	long framePosition = clip.getLongFramePosition();
    	return framePosition;
    }
    public void setFramePosition(int frames){
    	clip.setFramePosition(frames);
    }
    public int getFrameLength(){
    	int frameLength = clip.getFrameLength();
    	return frameLength;
    }
    public void setSoundLoopBoolean(boolean SoundLoopBoolean){
    	this.SoundLoopBoolean = SoundLoopBoolean;
    }
    public boolean getSoundLoopBoolean(){
    	return SoundLoopBoolean;
    }
    public boolean clipIsActive(){
    	return clip.isActive();
    }
    public boolean soundPlaying(){//SET SOUNDLOOPBOOLEAN TO FALSE AFTER USING THIS
    	if(clip.getFramePosition() != clip.getFrameLength() && this.SoundLoopBoolean == true)
    		return true;
    	else
    		return false;
    }
    public boolean endsSoon(){
    	if((int)clip.getLongFramePosition() >= clip.getFrameLength()-(441*2/*4*/))//was 4, now 2 because bitrate split in half
    		return true;														  //for koopas invaders wav songs
    	else
    		return false;
    }
    public boolean endsSoon(int i){
    	if((int)clip.getLongFramePosition() >= clip.getFrameLength()-(441*i))
    		return true;
    	else
    		return false;
    }
    
    
    public AudioFormat getAudioFormat() {
        return af;
    }
    public int getSampleSize() {
        return sampleSize;
    }
    public double getDurationTime() {
        return getFramesCount() / getAudioFormat().getFrameRate();
    }
    public long getFramesCount() {
        return framesCount;
    }
    /**
     * Returns sample (amplitude value). Note that in case of stereo samples
     * go one after another. I.e. 0 - first sample of left channel, 1 - first
     * sample of the right channel, 2 - second sample of the left channel, 3 -
     * second sample of the right channel, etc.
     */
    public int getSampleInt(int sampleNumber) {

        if (sampleNumber < 0 || sampleNumber >= data.length / sampleSize) {
            throw new IllegalArgumentException(
                    "sample number can't be < 0 or >= data.length/"
                            + sampleSize);
        }

        byte[] sampleBytes = new byte[4]; //4byte = int

        for (int i = 0; i < sampleSize; i++) {
            sampleBytes[i] = data[sampleNumber * sampleSize * channelsNum + i];
        }

        int sample = ByteBuffer.wrap(sampleBytes)
                .order(ByteOrder.LITTLE_ENDIAN).getInt();
        return sample;
    }
    public int getSampleRate() {
        return sampleRate;
    }
    public float getFrameRate() {
    	return af.getFrameRate();
    }
    public void close() {
    	//if(clip.isOpen() && !clip.isActive())
    	if(clip.isRunning())
    		clip.close();
    	//else if(clip.isOpen())
    		//Game.clipGarbageCollection.add(clip);
    }
    }