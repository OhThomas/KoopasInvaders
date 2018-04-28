/*
 * 
 */
package com.game.src.main;import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// TODO: Auto-generated Javadoc
/**
 * Handles playing, stopping, and looping of sounds for the game.
 * @author Tyler Thomas @edited by Thomas Rader
 *
 */
public class SoundLoops {
    
    /** The clip. */
    private Clip clip;
    
    /** The Sound loop boolean. */
    private boolean SoundLoopBoolean = false;
	
	/** The gain control. */
	private FloatControl gainControl;
	
	/** The volume. */
	private float volume;
    
    /**
     * Instantiates a new sound loops.
     *
     * @param fileName the file name
     */
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
    
    /**
     * Play.
     */
    public void play(){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }
    
    /**
     * Continue playing.
     */
    public void continuePlaying(){
        clip.start();
    }
    
    /**
     * Loop.
     */
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    /**
     * Stop.
     */
    public void stop(){
            clip.stop();
        }
    
    /**
     * Loop segment.
     *
     * @param x the x
     * @param y the y
     */
    public void loopSegment(int x, int y){
    	clip.setLoopPoints(x, y);
    	//clip.loop(x);
    }
    
    /**
     * Reduce sound.
     */
    public void reduceSound(){
    	this.volume -= 5.0f;
    	this.gainControl.setValue(volume);
    }
    
    /**
     * Increase sound.
     */
    public void increaseSound(){
    	this.volume += 5.0f;
    	this.gainControl.setValue(volume);
    }
    
    /**
     * Reduce sound.
     *
     * @param v the v
     */
    public void reduceSound(float v){
    	this.volume -= v * 1.0f;
    	this.gainControl.setValue(volume);
    }
    
    /**
     * Increase sound.
     *
     * @param v the v
     */
    public void increaseSound(float v){
    	this.volume += v * 1.0f;
    	this.gainControl.setValue(volume);
    }
    
    /**
     * Gets the volume.
     *
     * @return the volume
     */
    public float getVolume(){
    	return this.gainControl.getValue();
    }
    
    /**
     * Sets the volume.
     *
     * @param v the new volume
     */
    public void setVolume(float v){
    	this.volume = v;
    	this.gainControl.setValue(volume);
    }
    
    /**
     * Gets the long frame position.
     *
     * @return the long frame position
     */
    public long getLongFramePosition(){
    	long framePosition = clip.getLongFramePosition();
    	return framePosition;
    }
    
    /**
     * Sets the frame position.
     *
     * @param frames the new frame position
     */
    public void setFramePosition(int frames){
    	clip.setFramePosition(frames);
    }
    
    /**
     * Gets the frame length.
     *
     * @return the frame length
     */
    public int getFrameLength(){
    	int frameLength = clip.getFrameLength();
    	return frameLength;
    }
    
    /**
     * Sets the sound loop boolean.
     *
     * @param SoundLoopBoolean the new sound loop boolean
     */
    public void setSoundLoopBoolean(boolean SoundLoopBoolean){
    	this.SoundLoopBoolean = SoundLoopBoolean;
    }
    
    /**
     * Gets the sound loop boolean.
     *
     * @return the sound loop boolean
     */
    public boolean getSoundLoopBoolean(){
    	return SoundLoopBoolean;
    }
    
    /**
     * Clip is active.
     *
     * @return true, if successful
     */
    public boolean clipIsActive(){
    	return clip.isActive();
    }
    
    /**
     * Sound playing.
     *
     * @return true, if successful
     */
    public boolean soundPlaying(){//SET SOUNDLOOPBOOLEAN TO FALSE AFTER USING THIS
    	if(clip.getFramePosition() != clip.getFrameLength() && this.SoundLoopBoolean == true)
    		return true;
    	else
    		return false;
    }
    
    /**
     * Ends soon.
     *
     * @return true, if successful
     */
    public boolean endsSoon(){
    	if((int)clip.getLongFramePosition() >= clip.getFrameLength()-(441*4))
    		return true;
    	else
    		return false;
    }
    
    /**
     * Ends soon.
     *
     * @param i the i
     * @return true, if successful
     */
    public boolean endsSoon(int i){
    	if((int)clip.getLongFramePosition() >= clip.getFrameLength()-(441*i))
    		return true;
    	else
    		return false;
    }
    }