package com.game.src.main;import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
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
	private float volume;
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
    public void loopSegment(int x, int y){
    	clip.setLoopPoints(x, y);
    	//clip.loop(x);
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
    public double getVolume(){
    	return (double)this.gainControl.getValue();
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
    	if((int)clip.getLongFramePosition() >= clip.getFrameLength()-(441*4))
    		return true;
    	else
    		return false;
    }
    public boolean endsSoon(int i){
    	if((int)clip.getLongFramePosition() >= clip.getFrameLength()-(441*i))
    		return true;
    	else
    		return false;
    }
    }