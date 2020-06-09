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

public class PlayerSpinning{
	private Textures tex;
	private Game game;
	private Animation playerSpinning;
	private double x = 0;
	private double y = 0;
	private double velX = 0;
	private double velY = 0;
	private long waitToDie = 0;
	public PlayerSpinning(Textures tex, Game game, double x, double y) {
		this.tex = tex;
		this.game = game;
		this.x = x;
		this.y = y;
		this.velX = velX;
		switch(Game.currentlySelectedCharacterSkin) {
		case 0:
			playerSpinning = new Animation(6,tex.marioSpinning[0],tex.marioSpinning[1],tex.marioSpinning[2],tex.marioSpinning[3]
					,tex.marioSpinning[4],tex.marioSpinning[5],tex.marioSpinning[6],tex.marioSpinning[7],tex.marioSpinning[8]
					,tex.marioSpinning[9],tex.marioSpinning[10],tex.marioSpinning[11],tex.marioSpinning[12],tex.marioSpinning[13]
					,tex.marioSpinning[14],tex.marioSpinning[15],tex.marioSpinning[16],tex.marioSpinning[17],tex.marioSpinning[18]
					,tex.marioSpinning[19],tex.marioSpinning[20],tex.marioSpinning[21],tex.marioSpinning[22],tex.marioSpinning[23]);
			break;
		case 1:
			playerSpinning = new Animation(6,tex.marioSpinningNES[0],tex.marioSpinningNES[1],tex.marioSpinningNES[2],tex.marioSpinningNES[3]
					,tex.marioSpinningNES[4],tex.marioSpinningNES[5],tex.marioSpinningNES[6],tex.marioSpinningNES[7],tex.marioSpinningNES[8]
					,tex.marioSpinningNES[9],tex.marioSpinningNES[10],tex.marioSpinningNES[11],tex.marioSpinningNES[12],tex.marioSpinningNES[13]
					,tex.marioSpinningNES[14],tex.marioSpinningNES[15],tex.marioSpinningNES[16],tex.marioSpinningNES[17],tex.marioSpinningNES[18]
					,tex.marioSpinningNES[19],tex.marioSpinningNES[20],tex.marioSpinningNES[21],tex.marioSpinningNES[22],tex.marioSpinningNES[23]);
			break;
		case 2:
			playerSpinning = new Animation(6,tex.marioSpinningNES3[0],tex.marioSpinningNES3[1],tex.marioSpinningNES3[2],tex.marioSpinningNES3[3]
					,tex.marioSpinningNES3[4],tex.marioSpinningNES3[5],tex.marioSpinningNES3[6],tex.marioSpinningNES3[7],tex.marioSpinningNES3[8]
					,tex.marioSpinningNES3[9],tex.marioSpinningNES3[10],tex.marioSpinningNES3[11],tex.marioSpinningNES3[12],tex.marioSpinningNES3[13]
					,tex.marioSpinningNES3[14],tex.marioSpinningNES3[15],tex.marioSpinningNES3[16],tex.marioSpinningNES3[17],tex.marioSpinningNES3[18]
					,tex.marioSpinningNES3[19],tex.marioSpinningNES3[20],tex.marioSpinningNES3[21],tex.marioSpinningNES3[22],tex.marioSpinningNES3[23]);
			break;
		case 3:
			playerSpinning = new Animation(6,tex.marioSpinningSNESFireLuigi[0],tex.marioSpinningSNESFireLuigi[1],tex.marioSpinningSNESFireLuigi[2],tex.marioSpinningSNESFireLuigi[3]
					,tex.marioSpinningSNESFireLuigi[4],tex.marioSpinningSNESFireLuigi[5],tex.marioSpinningSNESFireLuigi[6],tex.marioSpinningSNESFireLuigi[7],tex.marioSpinningSNESFireLuigi[8]
					,tex.marioSpinningSNESFireLuigi[9],tex.marioSpinningSNESFireLuigi[10],tex.marioSpinningSNESFireLuigi[11],tex.marioSpinningSNESFireLuigi[12],tex.marioSpinningSNESFireLuigi[13]
					,tex.marioSpinningSNESFireLuigi[14],tex.marioSpinningSNESFireLuigi[15],tex.marioSpinningSNESFireLuigi[16],tex.marioSpinningSNESFireLuigi[17],tex.marioSpinningSNESFireLuigi[18]
					,tex.marioSpinningSNESFireLuigi[19],tex.marioSpinningSNESFireLuigi[20],tex.marioSpinningSNESFireLuigi[21],tex.marioSpinningSNESFireLuigi[22],tex.marioSpinningSNESFireLuigi[23]);
			break;
		case 4:
			playerSpinning = new Animation(6,tex.marioSpinningNESMikeTyson[0],tex.marioSpinningNESMikeTyson[1],tex.marioSpinningNESMikeTyson[2],tex.marioSpinningNESMikeTyson[3]
					,tex.marioSpinningNESMikeTyson[4],tex.marioSpinningNESMikeTyson[5],tex.marioSpinningNESMikeTyson[6],tex.marioSpinningNESMikeTyson[7],tex.marioSpinningNESMikeTyson[8]
					,tex.marioSpinningNESMikeTyson[9],tex.marioSpinningNESMikeTyson[10],tex.marioSpinningNESMikeTyson[11],tex.marioSpinningNESMikeTyson[12],tex.marioSpinningNESMikeTyson[13]
					,tex.marioSpinningNESMikeTyson[14],tex.marioSpinningNESMikeTyson[15],tex.marioSpinningNESMikeTyson[16],tex.marioSpinningNESMikeTyson[17],tex.marioSpinningNESMikeTyson[18]
					,tex.marioSpinningNESMikeTyson[19],tex.marioSpinningNESMikeTyson[20],tex.marioSpinningNESMikeTyson[21],tex.marioSpinningNESMikeTyson[22],tex.marioSpinningNESMikeTyson[23]);
			break;
		case 5:
			playerSpinning = new Animation(6,tex.marioSpinningNESContra[0],tex.marioSpinningNESContra[1],tex.marioSpinningNESContra[2],tex.marioSpinningNESContra[3]
					,tex.marioSpinningNESContra[4],tex.marioSpinningNESContra[5],tex.marioSpinningNESContra[6],tex.marioSpinningNESContra[7],tex.marioSpinningNESContra[8]
					,tex.marioSpinningNESContra[9],tex.marioSpinningNESContra[10],tex.marioSpinningNESContra[11],tex.marioSpinningNESContra[12],tex.marioSpinningNESContra[13]
					,tex.marioSpinningNESContra[14],tex.marioSpinningNESContra[15],tex.marioSpinningNESContra[16],tex.marioSpinningNESContra[17],tex.marioSpinningNESContra[18]
					,tex.marioSpinningNESContra[19],tex.marioSpinningNESContra[20],tex.marioSpinningNESContra[21],tex.marioSpinningNESContra[22],tex.marioSpinningNESContra[23]);
			break;
		default:
			playerSpinning = new Animation(6,tex.marioSpinning[0],tex.marioSpinning[1],tex.marioSpinning[2],tex.marioSpinning[3]
					,tex.marioSpinning[4],tex.marioSpinning[5],tex.marioSpinning[6],tex.marioSpinning[7],tex.marioSpinning[8]
					,tex.marioSpinning[9],tex.marioSpinning[10],tex.marioSpinning[11],tex.marioSpinning[12],tex.marioSpinning[13]
					,tex.marioSpinning[14],tex.marioSpinning[15],tex.marioSpinning[16],tex.marioSpinning[17],tex.marioSpinning[18]
					,tex.marioSpinning[19],tex.marioSpinning[20],tex.marioSpinning[21],tex.marioSpinning[22],tex.marioSpinning[23]);
			break;
		}
		playerSpinning.nextFrame();
		playerSpinning.setCount(0);
	}

	public void tick() {
		if(-20 < velY)
			velY-=0.15;
		
		playerSpinning.runAnimation();
		y+=velY;
//		if (y+40 < 0){
//			game.getController().removeEntity(this);
//		}
	}

	public void render(Graphics g) {
		playerSpinning.drawAnimation(g, x, y, 0);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 28);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String entityName() {
		return "playerSpinningFX";
	}

	public void close() {
		//g2d.dispose();
	}

}
