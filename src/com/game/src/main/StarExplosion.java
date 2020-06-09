package com.game.src.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class StarExplosion {
	private int x;
	private int y;
	private float straightLine = 1;
	private float curvedLine = 1;
	public static boolean setup = false;
	private float imageTranslucent = 0;
	private double imageTranslucentVelocity = 0;
	private boolean imageIsGone = false;
	private boolean iStart = false;
	public static ArrayList<Polygon> p = new ArrayList<Polygon>();
	public ArrayList<Double> pImageTransVel = new ArrayList<Double>();
	public ArrayList<Double> pImageTrans = new ArrayList<Double>();
	public ArrayList<ArrayList<Integer>> randomI = new ArrayList<ArrayList<Integer>>();
	public ArrayList<ArrayList<Integer>> pInts = new ArrayList<ArrayList<Integer>>();
	public ArrayList<ArrayList<Float>> pTrajectory = new ArrayList<ArrayList<Float>>();
	public ArrayList<ArrayList<Boolean>> pStraightorCurved = new ArrayList<ArrayList<Boolean>>();
	public StarExplosion() {
		//this.game = game
	}
	public void StarExplosionSetup(int x, int y) {
		this.x = x;
		this.y = y;
		straightLine = 1;
		curvedLine = 1;
		imageTranslucentVelocity = 0;
		imageTranslucent = 1;
		/*
		if(!p.isEmpty()) {//JUST REMOVE WHEN THE TRANSPARENCY REACHES 100
			for(int i = 0; i <= p.size()-1; i++) {
				p.remove(i);
				pInts.remove(i);
				pTrajectory.remove(i);
				pStraightorCurved.remove(i);
				this.randomI.remove(i);
				i--;
			}
		}*/
		int ii = 20;
		for(int i = 0; i <= ii; i++) {
			if(!iStart && !p.isEmpty()) {
				i = p.size();
				ii = p.size() + 20;
			}
			iStart = true;
			//if(i == 0 && !this.p.isEmpty())
				//i = p.size()-1;
			Polygon p = new Polygon();
			Random randomPoints = new Random();
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x-5,this.y-3);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(x-5, y);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(x-3, y+2);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(x, y+5);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(x+3, y+2);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(x+5, y);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(x+3, y-2);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(x, y-5);
			Random randle = new Random();
			Random randle2 = new Random();
			p.translate(randle.nextInt(70)-35, randle2.nextInt(70)-35);
			Rectangle r = p.getBounds();
			this.pInts.add(new ArrayList<Integer>(2));
			this.pTrajectory.add(new ArrayList<Float>(2));
			this.pStraightorCurved.add(new ArrayList<Boolean>(2));
			this.randomI.add(new ArrayList<Integer>(2));
			pInts.get(i).add(r.x);
			pInts.get(i).add(r.y);
			if(r.x < Game.mx)//r.y < Game.my SPIRAL EFFECT
				pTrajectory.get(i).add((float) -1);//1
			else
				pTrajectory.get(i).add((float) 1);//-1
			if(r.y < Game.my)//r.x < Game.mx SPIRAL EFFECT
				pTrajectory.get(i).add((float) -1);
			else
				pTrajectory.get(i).add((float) 1);
			if(r.x < Game.mx && r.y < Game.my) {
				//pTrajectory.get(i).add(-1 * straightLine);
				pStraightorCurved.get(i).add(true);
			}
			else if(r.x < Game.mx && r.y > Game.my) {
				//pTrajectory.get(i).add(curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			else if(r.x > Game.mx && r.y < Game.my) {
				//pTrajectory.get(i).add(straightLine);
				pStraightorCurved.get(i).add(true);
			}
			else {
				//pTrajectory.get(i).add(-1 * curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			if(r.x < Game.mx && r.y < Game.my) {
				//pTrajectory.get(i).add(-1 * curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			else if(r.x < Game.mx && r.y > Game.my) {
				//pTrajectory.get(i).add(-1 * straightLine);
				pStraightorCurved.get(i).add(true);
			}
			else if(r.x > Game.mx && r.y < Game.my) {
				//pTrajectory.get(i).add(curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			else {
				//pTrajectory.get(i).add(straightLine);
				pStraightorCurved.get(i).add(true);
			}
			this.p.add(p);
			this.randomI.get(i).add(randomPoints.nextInt(9)+1);
			this.randomI.get(i).add(randomPoints.nextInt(9)+1);
			this.pImageTrans.add((double)1);
			double dd =(1); //- (randomPoints.nextDouble()/100));
			//System.out.println(dd);
			this.pImageTransVel.add(dd);
			//g.drawPolygon(p);
		}
		setup = true;
		iStart = false;
	}

	public void StarExplosionSetup(int x, int y, int x2, int y2) {
		this.x = x;
		this.y = y;
		straightLine = 1;
		curvedLine = 1;
		imageTranslucentVelocity = 0;
		imageTranslucent = 1;
		/*
		if(!p.isEmpty()) {//JUST REMOVE WHEN THE TRANSPARENCY REACHES 100
			for(int i = 0; i <= p.size()-1; i++) {
				p.remove(i);
				pInts.remove(i);
				pTrajectory.remove(i);
				pStraightorCurved.remove(i);
				this.randomI.remove(i);
				i--;
			}
		}*/
		int ii = 20;
		for(int i = 0; i <= ii; i++) {
			if(!iStart && !p.isEmpty()) {
				i = p.size();
				ii = p.size() + 20;
			}
			iStart = true;
			//if(i == 0 && !this.p.isEmpty())
				//i = p.size()-1;
			Polygon p = new Polygon();
			Random randomPoints = new Random();
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x-5,this.y-3);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x-5, this.y);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x-3, this.y+2);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x, this.y+5);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x+3, this.y+2);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x+5, this.y);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x+3, this.y-2);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x, this.y-5);
			Random randle = new Random();
			Random randle2 = new Random();
			p.translate(randle.nextInt(70)-35, randle2.nextInt(70)-35);
			Rectangle r = p.getBounds();
			this.pInts.add(new ArrayList<Integer>(2));
			this.pTrajectory.add(new ArrayList<Float>(2));
			this.pStraightorCurved.add(new ArrayList<Boolean>(2));
			this.randomI.add(new ArrayList<Integer>(2));
			pInts.get(i).add(r.x);
			pInts.get(i).add(r.y);
			if(r.x < this.x)//r.y < Game.my SPIRAL EFFECT
				pTrajectory.get(i).add((float) -1);//1
			else
				pTrajectory.get(i).add((float) 1);//-1
			if(r.y < this.y)//r.x < Game.mx SPIRAL EFFECT
				pTrajectory.get(i).add((float) -1);
			else
				pTrajectory.get(i).add((float) 1);
			if(r.x < this.x && r.y < this.y) {
				//pTrajectory.get(i).add(-1 * straightLine);
				pStraightorCurved.get(i).add(true);
			}
			else if(r.x < this.x && r.y > this.y) {
				//pTrajectory.get(i).add(curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			else if(r.x > this.x && r.y < this.y) {
				//pTrajectory.get(i).add(straightLine);
				pStraightorCurved.get(i).add(true);
			}
			else {
				//pTrajectory.get(i).add(-1 * curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			if(r.x < this.x && r.y < this.y) {
				//pTrajectory.get(i).add(-1 * curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			else if(r.x < this.x && r.y > this.y) {
				//pTrajectory.get(i).add(-1 * straightLine);
				pStraightorCurved.get(i).add(true);
			}
			else if(r.x > this.x && r.y < this.y) {
				//pTrajectory.get(i).add(curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			else {
				//pTrajectory.get(i).add(straightLine);
				pStraightorCurved.get(i).add(true);
			}
			this.p.add(p);
			this.randomI.get(i).add(randomPoints.nextInt(9)+1);
			this.randomI.get(i).add(randomPoints.nextInt(9)+1);
			this.pImageTrans.add((double)1);
			double dd =(1); //- (randomPoints.nextDouble()/100));
			//System.out.println(dd);
			this.pImageTransVel.add(dd);
			//g.drawPolygon(p);
		}
		iStart = false;
		
		this.x = x2;
		this.y = y2;
		straightLine = 1;
		curvedLine = 1;
		imageTranslucentVelocity = 0;
		imageTranslucent = 1;
		/*
		if(!p.isEmpty()) {//JUST REMOVE WHEN THE TRANSPARENCY REACHES 100
			for(int i = 0; i <= p.size()-1; i++) {
				p.remove(i);
				pInts.remove(i);
				pTrajectory.remove(i);
				pStraightorCurved.remove(i);
				this.randomI.remove(i);
				i--;
			}
		}*/
		ii = 20;
		for(int i = 0; i <= ii; i++) {
			if(!iStart && !p.isEmpty()) {
				i = p.size();
				ii = p.size() + 20;
			}
			iStart = true;
			//if(i == 0 && !this.p.isEmpty())
				//i = p.size()-1;
			Polygon p = new Polygon();
			Random randomPoints = new Random();
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x-5,this.y-3);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x-5, this.y);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x-3, this.y+2);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x, this.y+5);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x+3, this.y+2);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x+5, this.y);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x+3, this.y-2);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x, this.y-5);
			Random randle = new Random();
			Random randle2 = new Random();
			p.translate(randle.nextInt(70)-35, randle2.nextInt(70)-35);
			Rectangle r = p.getBounds();
			this.pInts.add(new ArrayList<Integer>(2));
			this.pTrajectory.add(new ArrayList<Float>(2));
			this.pStraightorCurved.add(new ArrayList<Boolean>(2));
			this.randomI.add(new ArrayList<Integer>(2));
			pInts.get(i).add(r.x);
			pInts.get(i).add(r.y);
			if(r.x < this.x)//r.y < Game.my SPIRAL EFFECT
				pTrajectory.get(i).add((float) -1);//1
			else
				pTrajectory.get(i).add((float) 1);//-1
			if(r.y < this.y)//r.x < Game.mx SPIRAL EFFECT
				pTrajectory.get(i).add((float) -1);
			else
				pTrajectory.get(i).add((float) 1);
			if(r.x < this.x && r.y < this.y) {
				//pTrajectory.get(i).add(-1 * straightLine);
				pStraightorCurved.get(i).add(true);
			}
			else if(r.x < this.x && r.y > this.y) {
				//pTrajectory.get(i).add(curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			else if(r.x > this.x && r.y < this.y) {
				//pTrajectory.get(i).add(straightLine);
				pStraightorCurved.get(i).add(true);
			}
			else {
				//pTrajectory.get(i).add(-1 * curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			if(r.x < this.x && r.y < this.y) {
				//pTrajectory.get(i).add(-1 * curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			else if(r.x < this.x && r.y > this.y) {
				//pTrajectory.get(i).add(-1 * straightLine);
				pStraightorCurved.get(i).add(true);
			}
			else if(r.x > this.x && r.y < this.y) {
				//pTrajectory.get(i).add(curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			else {
				//pTrajectory.get(i).add(straightLine);
				pStraightorCurved.get(i).add(true);
			}
			this.p.add(p);
			this.randomI.get(i).add(randomPoints.nextInt(9)+1);
			this.randomI.get(i).add(randomPoints.nextInt(9)+1);
			this.pImageTrans.add((double)1);
			double dd =(1); //- (randomPoints.nextDouble()/100));
			//System.out.println(dd);
			this.pImageTransVel.add(dd);
			//g.drawPolygon(p);
		}
		iStart = false;
		setup = true;
	}
	public void StarExplosionSmallSetup(int x, int y) {
		this.x = x;
		this.y = y;
		straightLine = 1;
		curvedLine = 1;
		imageTranslucentVelocity = 0;
		imageTranslucent = 1;
		/*
		if(!p.isEmpty()) {//JUST REMOVE WHEN THE TRANSPARENCY REACHES 100
			for(int i = 0; i <= p.size()-1; i++) {
				p.remove(i);
				pInts.remove(i);
				pTrajectory.remove(i);
				pStraightorCurved.remove(i);
				this.randomI.remove(i);
				i--;
			}
		}*/
		int ii = 7;
		for(int i = 0; i <= ii; i++) {
			if(!iStart && !p.isEmpty()) {
				i = p.size();
				ii = p.size() + 20;
			}
			iStart = true;
			//if(i == 0 && !this.p.isEmpty())
				//i = p.size()-1;
			Polygon p = new Polygon();
			Random randomPoints = new Random();
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(this.x-3,this.y-2);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(x-3, y);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(x-2, y+1);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(x, y+3);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(x+2, y+1);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(x+3, y);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(x+2, y-1);
			if(randomPoints.nextInt(5) != 1)
				p.addPoint(x, y-3);
			Random randle = new Random();
			Random randle2 = new Random();
			p.translate(randle.nextInt(70)-35, randle2.nextInt(70)-35);
			Rectangle r = p.getBounds();
			this.pInts.add(new ArrayList<Integer>(2));
			this.pTrajectory.add(new ArrayList<Float>(2));
			this.pStraightorCurved.add(new ArrayList<Boolean>(2));
			this.randomI.add(new ArrayList<Integer>(2));
			pInts.get(i).add(r.x);
			pInts.get(i).add(r.y);
			if(r.x < this.x)//r.y < Game.my SPIRAL EFFECT
				pTrajectory.get(i).add((float) -1);//1
			else
				pTrajectory.get(i).add((float) 1);//-1
			if(r.y < this.y)//r.x < Game.mx SPIRAL EFFECT
				pTrajectory.get(i).add((float) -1);
			else
				pTrajectory.get(i).add((float) 1);
			if(r.x < this.x && r.y < this.y) {
				//pTrajectory.get(i).add(-1 * straightLine);
				pStraightorCurved.get(i).add(true);
			}
			else if(r.x < this.x && r.y > this.y) {
				//pTrajectory.get(i).add(curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			else if(r.x > this.x && r.y < this.y) {
				//pTrajectory.get(i).add(straightLine);
				pStraightorCurved.get(i).add(true);
			}
			else {
				//pTrajectory.get(i).add(-1 * curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			if(r.x < this.x && r.y < this.y) {
				//pTrajectory.get(i).add(-1 * curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			else if(r.x < this.x && r.y > this.y) {
				//pTrajectory.get(i).add(-1 * straightLine);
				pStraightorCurved.get(i).add(true);
			}
			else if(r.x > this.x && r.y < this.y) {
				//pTrajectory.get(i).add(curvedLine);
				pStraightorCurved.get(i).add(false);
			}
			else {
				//pTrajectory.get(i).add(straightLine);
				pStraightorCurved.get(i).add(true);
			}
			this.p.add(p);
			this.randomI.get(i).add(randomPoints.nextInt(9)+1);
			this.randomI.get(i).add(randomPoints.nextInt(9)+1);
			this.pImageTrans.add((double)1);
			double dd =(1); //- (randomPoints.nextDouble()/100));
			//System.out.println(dd);
			this.pImageTransVel.add(dd);
			//g.drawPolygon(p);
		}
		setup = true;
		iStart = false;
	}
	public void Explosion(Graphics g) {
		for(int i = 0; i <= p.size()-1; i++) {
			
			Random rand = new Random();
			Random rand2 = new Random();
			Random rand3 = new Random();
			//g.setColor(new Color(rand.nextInt(255),rand2.nextInt(255),rand3.nextInt(255)));
			//g.fillPolygon(p.get(i));
			//g.drawPolygon(p.get(i));
			Graphics2D g2d = (Graphics2D)g.create();
			g2d.setColor(new Color(rand.nextInt(255),rand2.nextInt(255),rand3.nextInt(255)));
			g2d.setComposite(makeComposite((float)pImageTrans.get(i).doubleValue()));
			g2d.fillPolygon(p.get(i));
			g2d.drawPolygon(p.get(i));
		}
	}
	public void tick() {
		curvedLine = (float) (curvedLine);
		straightLine = (float) (straightLine);
		for(int i = 0; i <= p.size() -1; i++) {
			if(p.isEmpty())
				break;
			float xTrajectory;
			float yTrajectory;
			Rectangle r = p.get(i).getBounds();
			if(pImageTrans.get(i).doubleValue() <= 0.015 || r.x + r.getWidth() < 0 || r.x > Game.WIDTH * Game.SCALE ||
					r.y + r.getHeight() < 0 || r.y > Game.HEIGHT * Game.SCALE) {
				//DELETE
				p.remove(i);
				pInts.remove(i);
				pTrajectory.remove(i);
				pStraightorCurved.remove(i);
				this.randomI.remove(i);
				pImageTrans.remove(i);
				pImageTransVel.remove(i);
				i--;
				//System.out.println(i + "in for");
				continue;
			}
			//System.out.println(pImageTrans.get(i).longValue());
			//System.out.println(i + "before p");
			if(pStraightorCurved.get(i).get(0)) {
				xTrajectory = pTrajectory.get(i).get(0) * straightLine;
				pTrajectory.get(i).set(0, pTrajectory.get(i).get(0) * straightLine);
			}
			else {
				xTrajectory = pTrajectory.get(i).get(0) * curvedLine;
				pTrajectory.get(i).set(0, pTrajectory.get(i).get(0) * curvedLine);
			}
			if(pStraightorCurved.get(i).get(1)) {
				yTrajectory = pTrajectory.get(i).get(1) * straightLine;
				pTrajectory.get(i).set(1, pTrajectory.get(i).get(1) * straightLine);
			}
			else {
				yTrajectory = pTrajectory.get(i).get(1) * curvedLine;
				pTrajectory.get(i).set(1, pTrajectory.get(i).get(1) * curvedLine);
			}
			//System.out.println(pInts.get(i).get(0));
			//System.out.println(pInts.get(i).get(1));
			pInts.get(i).set(0, pInts.get(i).get(0) + (int)xTrajectory/100);
			pInts.get(i).set(1, pInts.get(i).get(1) + (int)yTrajectory/100);
			if(xTrajectory<0 && yTrajectory<0)
				p.get(i).translate(-2*this.randomI.get(i).get(0), -2*this.randomI.get(i).get(1));
			else if(xTrajectory<0 && yTrajectory>0)
				p.get(i).translate(-2*this.randomI.get(i).get(0),2*this.randomI.get(i).get(1));
			else if(xTrajectory>0 && yTrajectory<0)
				p.get(i).translate(2*this.randomI.get(i).get(0), -2*this.randomI.get(i).get(1));
			else
				p.get(i).translate(2*this.randomI.get(i).get(0),2*this.randomI.get(i).get(1));
			if(pImageTrans.get(i).doubleValue() >= 0.99) {
				double s = pImageTransVel.get(i).doubleValue();
				if(s < 0.3)
					pImageTransVel.set(i,s += -(0.08));
				else
					pImageTransVel.set(i,s += -(0.05));
			}
			if(pImageTrans.get(i).doubleValue() + pImageTransVel.get(i).doubleValue() <= 1 &&
					pImageTrans.get(i).doubleValue() + pImageTransVel.get(i).doubleValue() >= 0) {
				double ss = pImageTrans.get(i).doubleValue();
				pImageTrans.set(i, (ss + pImageTransVel.get(i).doubleValue()));
			}
			else if(!(pImageTrans.get(i).doubleValue() + pImageTransVel.get(i).doubleValue() >= 0)){
				//pImageTrans.set(i, (0.015));
				double ss = pImageTrans.get(i).doubleValue();
				if(ss - (0.005) < 0 )
					pImageTrans.set(i, (0.015));
				else
					pImageTrans.set(i, ss - (0.005));
			}
		}
		/*
		if(imageTranslucent >= 0.99){
			imageTranslucentVelocity += -0.01;
			imageIsGone = false;
		}
		if(imageTranslucent <= 0.01 && imageIsGone == false){
			imageIsGone = true;
		}
		if(imageTranslucent + imageTranslucentVelocity <= 1 &&
				imageTranslucent + imageTranslucentVelocity >= 0)
			imageTranslucent += imageTranslucentVelocity;
		*/
	}
	
	private AlphaComposite makeComposite(float alpha) {
		  int type = AlphaComposite.SRC_OVER;
		  return(AlphaComposite.getInstance(type, alpha));
	}
	
}
