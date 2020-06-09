package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityE;
import com.game.src.main.classes.EntityF;
import com.game.src.main.libs.Animation;

public class NakedLakituFX  extends GameObject implements EntityF{
	private Textures tex;
	private Game game;
	private Animation lakitu;
	private Animation lakituDeath;
	private double velX = 0;
	private double velY = 0;
	private int width = 16;
	private int height = 30;
	private long popOffTimer = 0;
	private long waitToDie = 0;
	private long pausedTimerAddition = 0;
	private long pausedTimerAddition2 = 0;
	private boolean velXBoolean = false;
	public NakedLakituFX(double x, double y,Textures tex, Game game, double velX) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.velX = velX;
		if(velX < 0) {
			lakituDeath = new Animation(6,tex.lakituItemDeath[0],tex.lakituItemDeath[1], tex.lakituItemDeath[2],
					tex.lakituItemDeath[3],tex.lakituItemDeath[4],tex.lakituItemDeath[5],tex.lakituItemDeath[6],
					tex.lakituItemDeath[7],tex.lakituItemDeath[8],tex.lakituItemDeath[9],tex.lakituItemDeath[10],
					tex.lakituItemDeath[11],tex.lakituItemDeath[12],tex.lakituItemDeath[13],tex.lakituItemDeath[24],
					tex.lakituItemDeath[23],tex.lakituItemDeath[22],tex.lakituItemDeath[21],tex.lakituItemDeath[20],
					tex.lakituItemDeath[19],tex.lakituItemDeath[18],tex.lakituItemDeath[17],tex.lakituItemDeath[16],
					tex.lakituItemDeath[15],tex.lakituItemDeath[14],tex.lakituItemDeath[13]);
					
		}
		else {
			lakituDeath = new Animation(6,tex.lakituItemDeath[13],tex.lakituItemDeath[14], tex.lakituItemDeath[15],
					tex.lakituItemDeath[16],tex.lakituItemDeath[17],tex.lakituItemDeath[18],tex.lakituItemDeath[19],
					tex.lakituItemDeath[20],tex.lakituItemDeath[21],tex.lakituItemDeath[22],tex.lakituItemDeath[23],
					tex.lakituItemDeath[24],tex.lakituItemDeath[25],tex.lakituItemDeath[12],tex.lakituItemDeath[11],
					tex.lakituItemDeath[10],tex.lakituItemDeath[9],tex.lakituItemDeath[8],tex.lakituItemDeath[7],
					tex.lakituItemDeath[6],tex.lakituItemDeath[5],tex.lakituItemDeath[4],tex.lakituItemDeath[3],
					tex.lakituItemDeath[2],tex.lakituItemDeath[1],tex.lakituItemDeath[0]);
		}
		lakitu = new Animation(6,tex.lakituItemDeath[12],tex.lakituItemDeath[25]);
		popOffTimer = System.currentTimeMillis() + 200;
		waitToDie = System.currentTimeMillis() + 600;
		lakitu.nextFrame();
		lakitu.setCount(0);
		lakituDeath.nextFrame();
		lakituDeath.setCount(0);
	}

	public void tick() {
		if(pausedTimerAddition != 0) {
			pausedTimerAddition2 = System.currentTimeMillis();
			pausedTimerAddition2 = pausedTimerAddition2 - pausedTimerAddition;
			if(pausedTimerAddition2 > 0) {
				if(waitToDie != 0)
					waitToDie+=pausedTimerAddition2;
				if(popOffTimer != 0)
					popOffTimer+=pausedTimerAddition2;
			}
			pausedTimerAddition = 0;
		}
		if(System.currentTimeMillis() < popOffTimer) {
			if(velY > -2.5) {
				velY-=0.15;
			}
		}
		else {
			if(velY < 20)
				velY+=0.1;
		}
		
//		if(velXBoolean == false) {
//			velX-=0.05;
//			if(velX <= -0.4)
//				velXBoolean = true;
//		}
//		else {
//			velX+=0.05;
//			if(velX >= 0.4)
//				velXBoolean = false;
//		}
		if(game.getPlayer().getBounds().intersects(this.getBounds())) {
			//ADD SFX
			Game.soundPop();
			game.getController().addEntity(new ChompFX(game,x+4,y,"Lakitu"));
			game.getController().removeEntity(this);
		}
		for(int i = 0; i < game.ea.size(); i++){
			EntityA tempEnt = game.ea.get(i);
			if(game.ea.get(i).getBounds().intersects(this.getBounds())) {
				//ADD SFX
				Game.soundPop();
				game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Lakitu"));
				game.getController().removeEntity(this);
			}
		}
		for(int i = 0; i < game.ec.size(); i++){
			EntityC tempEnt = game.ec.get(i);
			if(game.ec.get(i).getBounds().intersects(this.getBounds()) && waitToDie < System.currentTimeMillis()) {
				//ADD SFX
				Game.soundPop();
				game.getController().addEntity(new ChompFX(game,tempEnt.getX()+4,tempEnt.getY()+4,"Lakitu"));
				game.getController().removeEntity(this);
			}
		}
		for(int i = 0; i < game.ee.size(); i++) {
			EntityE tempEnt = game.ee.get(i);
			if(game.ee.get(i).getBounds().intersects(this.getBounds()) && !tempEnt.getEntityEDead() && !tempEnt.entityName().equals("lakitu")){
				Game.soundPop();
				game.getController().addEntity(new ChompFX(game,x+4,y,"Lakitu"));
				game.getController().removeEntity(this);
			}
		}
//		if(lakituDeath.getCount() != 12) {
			lakituDeath.runAnimation();
//		}
		lakitu.runAnimation();
		y+=velY;
		x+=velX;
		if (y-getBounds().height >= Game.HEIGHT * Game.SCALE){
			game.getController().removeEntity(this);
		}
	}

	public void render(Graphics g) {
		if((Game.isPaused() || System.currentTimeMillis() < game.getEnemyHitPauseTimer()) && pausedTimerAddition == 0 && (System.currentTimeMillis() < popOffTimer || System.currentTimeMillis() < waitToDie)){
			this.pausedTimerAddition = System.currentTimeMillis();
		}
//		if(lakituDeath.getCount() < 12) 
			lakituDeath.drawAnimation(g, x, y, 0);
//		else
//			lakitu.drawAnimation(g, x, y, 0);
	}

	public Rectangle getBounds() {
		if(velX < 0) {
			switch(lakituDeath.getCount()) {
			case 0:
				return new Rectangle((int)x, (int)y, 16, 30);
			case 1:
				return new Rectangle((int)x, (int)y, 19, 30);
			case 2:
				return new Rectangle((int)x, (int)y, 22, 29);
			case 3:
				return new Rectangle((int)x, (int)y, 25, 26);
			case 4:
				return new Rectangle((int)x, (int)y, 28, 23);
			case 5:
				return new Rectangle((int)x, (int)y, 29, 20);
			case 6:
				return new Rectangle((int)x, (int)y, 30, 16);
			case 7:
				return new Rectangle((int)x, (int)y, 29, 20);
			case 8:
				return new Rectangle((int)x, (int)y, 28, 23);
			case 9:
				return new Rectangle((int)x, (int)y, 25, 26);
			case 10:
				return new Rectangle((int)x, (int)y, 22, 29);
			case 11:
				return new Rectangle((int)x, (int)y, 19, 30);
			case 12:
				return new Rectangle((int)x, (int)y, 16, 30);
			case 13:
				return new Rectangle((int)x, (int)y, 16, 30);
			case 14://24
				return new Rectangle((int)x, (int)y, 19, 30);
			case 15://23
				return new Rectangle((int)x, (int)y, 22, 29);
			case 16://22
				return new Rectangle((int)x, (int)y, 25, 26);
			case 17://21
				return new Rectangle((int)x, (int)y, 28, 23);
			case 18://20
				return new Rectangle((int)x, (int)y, 29, 20);
			case 19://19
				return new Rectangle((int)x, (int)y, 30, 16);
			case 20://18
				return new Rectangle((int)x, (int)y, 29, 20);
			case 21://17
				return new Rectangle((int)x, (int)y, 28, 23);
			case 22://16
				return new Rectangle((int)x, (int)y, 25, 26);
			case 23://15
				return new Rectangle((int)x, (int)y, 22, 29);
			case 24://14
				return new Rectangle((int)x, (int)y, 19, 30);
			case 25://13
				return new Rectangle((int)x, (int)y, 16, 30);
			default:
				return new Rectangle((int)x, (int)y, width, height);
			}
		}
		else {
			switch(lakituDeath.getCount()) {
			case 0://13
				return new Rectangle((int)x, (int)y, 16, 30);
			case 1://14
				return new Rectangle((int)x, (int)y, 19, 30);
			case 2://15
				return new Rectangle((int)x, (int)y, 22, 29);
			case 3://16
				return new Rectangle((int)x, (int)y, 25, 26);
			case 4://17
				return new Rectangle((int)x, (int)y, 28, 23);
			case 5://18
				return new Rectangle((int)x, (int)y, 29, 20);
			case 6://19
				return new Rectangle((int)x, (int)y, 30, 16);
			case 7://20
				return new Rectangle((int)x, (int)y, 29, 20);
			case 8://21
				return new Rectangle((int)x, (int)y, 28, 23);
			case 9://22
				return new Rectangle((int)x, (int)y, 25, 26);
			case 10://23
				return new Rectangle((int)x, (int)y, 22, 29);
			case 11://24
				return new Rectangle((int)x, (int)y, 19, 30);
			case 12://25
				return new Rectangle((int)x, (int)y, 16, 30);
			case 13://12
				return new Rectangle((int)x, (int)y, 16, 30);
			case 14://11
				return new Rectangle((int)x, (int)y, 19, 30);
			case 15://10
				return new Rectangle((int)x, (int)y, 22, 29);
			case 16://9
				return new Rectangle((int)x, (int)y, 25, 26);
			case 17://8
				return new Rectangle((int)x, (int)y, 28, 23);
			case 18://7
				return new Rectangle((int)x, (int)y, 29, 20);
			case 19://6
				return new Rectangle((int)x, (int)y, 30, 16);
			case 20://5
				return new Rectangle((int)x, (int)y, 29, 20);
			case 21://4
				return new Rectangle((int)x, (int)y, 28, 23);
			case 22://3
				return new Rectangle((int)x, (int)y, 25, 26);
			case 23://2
				return new Rectangle((int)x, (int)y, 22, 29);
			case 24://1
				return new Rectangle((int)x, (int)y, 19, 30);
			case 25://0
				return new Rectangle((int)x, (int)y, 16, 30);
			default:
				return new Rectangle((int)x, (int)y, width, height);
			}
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public double getVelX() {
		return 0;
	}

	public double getVelY() {
		return 0;
	}

	public String entityName() {
		return "nakedLakituFX";
	}

	public void close() {
		//g2d.dispose();
	}

}
