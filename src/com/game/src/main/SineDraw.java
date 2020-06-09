package com.game.src.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.Random;

import static java.lang.Math.*;

public class SineDraw{
	private Game game;
	private int amplitude;
	private int amplitudeVel;
	private long amplitudeZeroTimer = 0;
//	private long colorChangeTimer = 0;
//	private Color randColor1 = new Color(9,239,109);
//	private Color randColor2 = new Color(150,9,9);
//	private Color randColor3 = new Color(240,32,22);
	private Color thisColor = new Color(248,192,0);
	private SoundLoops song;
    public SineDraw(Game game) {
    	this.game = game;
    }
    public int randColorRandomifier(int i) {
    	Random rand = new Random();
//    	if(rand.nextInt(6) == 0) {
//    		int j = rand.nextInt(3);
//        	if(i-j >= 0)
//        		i= i-j;
//        	else if(i-1 >= 0)
//        		i=233;
//        	return i;
//        }
//    	else {
    		int j = rand.nextInt(3);
        	if(i+j <= 255)
        		i = i+j;
        	else
        		i=0;
        	return i;
//    	}
    }
    public void colorToWhite(int r, int g, int b) {
    	if(g < 248)
    		g++;
    	else if(b < 248)
    		b++;
    	else {
    		g = 192;
    		b = 0;
    	}
    	Color color = new Color(r,g,b);
    	thisColor = color;
    }
    public void tick() {
    	if(amplitudeVel < 30) {
    		amplitudeZeroTimer = System.currentTimeMillis() + 300;
    	}
    	if(amplitudeZeroTimer < System.currentTimeMillis() && amplitudeVel >= 30 && !game.gameSoundLoops.get(game.getSoundRandomizer()).endsSoon(245)) {
	    	if(amplitude < amplitudeVel)
	    		amplitude += amplitudeVel/100;
	    	else
	    		amplitude -= amplitudeVel;
    	}
    	else  {
        	//System.out.println(amplitude);
    		if(amplitude - 300 <= 0)
    			amplitude = 0;
    		else
    			amplitude -= 300;
    	}
    }
    public void draw(Graphics g, int amplitudeVel, int xPosition, int yPosition) {
    	this.amplitudeVel = amplitudeVel;
    	//g.drawLine(0,350,900,350); // x-axis
        //g.drawLine(450,0,450,900); // y-axis
//		if(colorChangeTimer < System.currentTimeMillis()) {
//			Color orange = new Color(248,192,0);
//			Color yellow = new Color(248,248,0);
//			Color white = new Color(248,248,248);
//			if(amplitude < 10000 || amplitude >= 62000)
//		        thisColor = orange;
//			else if(amplitude >= 10000 && amplitude < 40000 || amplitude >= 55000 && amplitude < 62000)
//				thisColor = yellow;
//			else
//				thisColor = white;
//			colorChangeTimer = colorChangeTimer = System.currentTimeMillis() + 100;
//		}
//		Color orange = thisColor;
//		int r = orange.getRed();
//		int gg = orange.getGreen();
//		int b = orange.getBlue();
//		if(amplitude < 10000 || amplitude >= 62000) {
//			if(gg>192)
//				gg-=1;
//			if(b > 0)
//				b-=1;
//		}
//		else if(amplitude >= 10000 && amplitude < 40000 || amplitude >= 55000 && amplitude < 62000) {
//			if(gg < 248)
//				gg+=1;
//		}
//		else {
//			if(gg < 248)
//				gg+=1;
//			if(b < 248)
//				b+=1;
//		}
//		orange = new Color(r,gg,b);
//		thisColor = orange;
		
//        g.setColor(thisColor);
        Random rand = new Random();
		if(System.currentTimeMillis() % 60 == 0) {
//        int r1 = randColor1.getRed();
//        int g1 = randColor1.getGreen();
//        int b1 = randColor1.getBlue();
//        int r2 = randColor2.getRed();
//        int g2 = randColor2.getGreen();
//        int b2 = randColor2.getBlue();
//        int r3 = randColor3.getRed();
//        int g3 = randColor3.getGreen();
//        int b3 = randColor3.getBlue();
//        r1 = randColorRandomifier(r1);
//        g1 = randColorRandomifier(g1);
//        b1 = randColorRandomifier(b1);
//        r2 = randColorRandomifier(r2);
//        g2 = randColorRandomifier(g2);
//        b2 = randColorRandomifier(b2);
//        r3 = randColorRandomifier(r3);
//        g3 = randColorRandomifier(g3);
//        b3 = randColorRandomifier(b3);
//        Color color1 = new Color(r1,g1,b1);
//        Color color2 = new Color(r2,g2,b2);
//        Color color3 = new Color(r3,g3,b3);
//        randColor1 = color1;
//        randColor2 = color2;
//        randColor3 = color3;
        colorToWhite(thisColor.getRed(),thisColor.getGreen(),thisColor.getBlue());
		}
//		Color colorr = randColor1;
//        int colorRed = randColor1.getRed();
//        int colorGreen = randColor1.getGreen();
//        int colorBlue = randColor1.getBlue();
        for(double x=-7;x<=25;x=x+0.05){
//        	if(x >= -7 && x < 0)
//        		g.setColor(randColor1);
//        	else if(x >= 0 && x < 7 || x >= 12 && x < 19)
//        		g.setColor(randColor2);
//        	else
//        		g.setColor(randColor3);
//        	if(x >=5 && x < 12) {
//	        	colorRed = randColorRandomifier(colorRed);
//	        	colorGreen = randColorRandomifier(colorGreen);
//	        	colorBlue = randColorRandomifier(colorBlue);
//	        	colorr = new Color(colorRed,colorGreen,colorBlue);
//        	}
        	g.setColor(thisColor);
        	double y = 0;
        	if(amplitude > 1000)
        		y = 15 * sin(x*(3.1415926/18));
        	else
        		y = 0;
        		//y = 15 * sin(x*(3.1415926/18));
            int Y = (int)y;
            int X = (int)x;
//            if(amplitude > 1000)
//            	g.drawLine(150+X+rand.nextInt(2),150-Y-(amplitude/100)+rand.nextInt(2),150+X+rand.nextInt(2),150-Y-(amplitude/100)+rand.nextInt(2));
//            else {
//            	x-=0.04;
//                y = 55 * sin(x*(3.1415926/180));
//                Y = (int)y;
//                X = (int)x;
//            	g.drawLine(150+X+rand.nextInt(2),150-Y+rand.nextInt(2),(150+X+rand.nextInt(2)/2),150-Y+rand.nextInt(2));
//            }
            /*
            if(amplitude > 1000) {
	            if(x > 11 && x < 16) {
	            	y-=0.6;
	                Y = (int)y;
	            }
	            else if(x >= 16 && x < 21) {
	            	y+=0.6;
	                Y = (int)y;
	            }
            }
            else {
            	x-=0.04;
                X = (int)x;
            }
            g.drawLine(150+X+rand.nextInt(2),150-Y-(amplitude/100)+rand.nextInt(2),150+X+rand.nextInt(2),150-Y-(amplitude/100)+rand.nextInt(2));
            */
            if(amplitude > 10 && amplitude <= 500) {
        		y = (1) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),yPosition-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),yPosition-Y+rand.nextInt(2));
        	}
            else if(amplitude > 500 && amplitude <= 800) {
        		y = (2) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-1)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-1)-Y+rand.nextInt(2));
        	}
        	else if(amplitude > 800 && amplitude <= 1000) {
        		y = (3) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-2)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-2)-Y+rand.nextInt(2));
        	}
        	else if(amplitude > 1000 && amplitude <= 3000) {
        		y = (4) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-3)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-3)-Y+rand.nextInt(2));
        	}
        	else if(amplitude > 3000 && amplitude <= 8000) {
        		y = (5) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-4)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-4)-Y+rand.nextInt(2));
        	}
        	else if(amplitude > 8000 && amplitude <= 18000) {
        		y = (6) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-5)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-5)-Y+rand.nextInt(2));
        	}
        	else if(amplitude > 18000 && amplitude <= 26000) {
        		y = (7) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-6)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-6)-Y+rand.nextInt(2));
        	}
        	else if(amplitude > 26000 && amplitude <= 33000) {
        		y = (8) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-7)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-7)-Y+rand.nextInt(2));
        	}
        	else if(amplitude > 33000 && amplitude <= 36000) {
        		y = (9) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-8)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-8)-Y+rand.nextInt(2));
        	}
        	else if(amplitude > 36000 && amplitude <= 42000) {
        		y = (10) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-9)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-9)-Y+rand.nextInt(2));
        	}
        	else if(amplitude > 42000 && amplitude <= 48000) {
        		y = (11) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-10)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-10)-Y+rand.nextInt(2));
        	}
        	else if(amplitude > 48000 && amplitude <= 52000) {
        		y = (12) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-11)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-11)-Y+rand.nextInt(2));
        	}
        	else if(amplitude > 52000 && amplitude <= 55000) {
        		y = (13) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-12)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-12)-Y+rand.nextInt(2));
        	}
        	else if(amplitude > 55000 && amplitude <= 60000) {
        		y = (14) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-13)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-13)-Y+rand.nextInt(2));
        	}
        	else if(amplitude > 60000 && amplitude <= 61000) {
        		y = (15) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-14)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-14)-Y+rand.nextInt(2));
        	}else if(amplitude > 61000 && amplitude <= 62000) {
        		y = (16) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-15)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-15)-Y+rand.nextInt(2));
        	}else if(amplitude > 62000 && amplitude <= 63000) {
        		y = (17) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-16)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-16)-Y+rand.nextInt(2));
        	}else if(amplitude > 63000 && amplitude <= 64000) {
        		y = (18) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-17)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-17)-Y+rand.nextInt(2));
        	}else if(amplitude > 64000 && amplitude <= 65000) {
        		y = (19) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-18)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-18)-Y+rand.nextInt(2));
        	}else if(amplitude > 65000 && amplitude <= 66000) {
        		y = (20) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-19)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-19)-Y+rand.nextInt(2));
        	}else if(amplitude > 66000 && amplitude <= 67000) {
        		y = (21) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-20)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-20)-Y+rand.nextInt(2));
        	}else if(amplitude > 67000 && amplitude <= 68000) {
        		y = (22) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-22)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-22)-Y+rand.nextInt(2));
        	}else if(amplitude > 68000 && amplitude <= 69000) {
        		y = (23) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-22)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-22)-Y+rand.nextInt(2));
        	}else if(amplitude > 69000) {
        		y = (24) * sin(x*(3.1415926/18));
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),(yPosition-23)-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),(yPosition-23)-Y+rand.nextInt(2));
        	}
        	else {
        		y = (1);
        		Y = (int)y;
        		g.drawLine(xPosition+X+rand.nextInt(2),yPosition+1-Y+rand.nextInt(2),xPosition+X+rand.nextInt(2),yPosition-Y+rand.nextInt(2));
        	}
        	//else
        		//g.drawLine(150+X+rand.nextInt(2),165-Y+rand.nextInt(2),150+X+rand.nextInt(2),165-Y+rand.nextInt(2));
        		
            
        }
    }
    public void setAmplitude(int amplitude) {
    	this.amplitude = amplitude;
    }
    public void setSong(SoundLoops sound) {
    	this.song = sound;
    }
}
