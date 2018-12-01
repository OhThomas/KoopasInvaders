package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityD;
import com.game.src.main.libs.Animation;

public class ItemBall extends GameObject implements EntityD{
	
	private Textures tex;
	private Game game;
	protected double velX, velY;
	private String itemName = "chainChompItem";
	private boolean itemSpawnLocation = false;
	
	Animation anim;
	Animation animBackwards;

	SoundLoops itemSoundLoop;
	
	public ItemBall(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		velX = 0;
		velY = 0;
		int randomIPlus = 0;
		int randomItem = 0;
		String itemFile;
		SoundLoops itemSoundLoop;
		itemFile = "res/Sounds/SFX/Items/sm64_chain_chomp.wav";
		itemSoundLoop = new SoundLoops(itemFile);
		if(this.getX() < Game.WIDTH)
			itemSpawnLocation = false;
		else
			itemSpawnLocation = true;
		if(Game.item4Unlocked)
			randomIPlus++;
		if(Game.item5Unlocked)
			randomIPlus++;
		if(Game.item6Unlocked)
			randomIPlus++;
		Random rand = new Random();
		randomItem = rand.nextInt(4) + randomIPlus;
		switch(randomItem) {
		case 0:
			anim = new Animation(1, tex.chainChompItemBall[0], tex.chainChompItemBall[1], tex.chainChompItemBall[2], tex.chainChompItemBall[3],
					tex.chainChompItemBall[4], tex.chainChompItemBall[5], tex.chainChompItemBall[6], tex.chainChompItemBall[7],
					tex.chainChompItemBall[8], tex.chainChompItemBall[9], tex.chainChompItemBall[10], tex.chainChompItemBall[11],
					tex.chainChompItemBall[12], tex.chainChompItemBall[13], tex.chainChompItemBall[14], tex.chainChompItemBall[15],
					tex.chainChompItemBall[16], tex.chainChompItemBall[17], tex.chainChompItemBall[18], tex.chainChompItemBall[19],
					tex.chainChompItemBall[20], tex.chainChompItemBall[21], tex.chainChompItemBall[22], tex.chainChompItemBall[23],
					tex.chainChompItemBall[24]);
			animBackwards = new Animation(1, tex.chainChompItemBall[24], tex.chainChompItemBall[23], tex.chainChompItemBall[22], tex.chainChompItemBall[21],
					tex.chainChompItemBall[20], tex.chainChompItemBall[19], tex.chainChompItemBall[18], tex.chainChompItemBall[17],
					tex.chainChompItemBall[16], tex.chainChompItemBall[15], tex.chainChompItemBall[14], tex.chainChompItemBall[13],
					tex.chainChompItemBall[12], tex.chainChompItemBall[11], tex.chainChompItemBall[10], tex.chainChompItemBall[9],
					tex.chainChompItemBall[8], tex.chainChompItemBall[7], tex.chainChompItemBall[6], tex.chainChompItemBall[5],
					tex.chainChompItemBall[4], tex.chainChompItemBall[3], tex.chainChompItemBall[2], tex.chainChompItemBall[1],
					tex.chainChompItemBall[0]);
			
			itemFile = "res/Sounds/SFX/Items/sm64_chain_chomp.wav";
			itemSoundLoop = new SoundLoops(itemFile);
			itemSoundLoop.reduceSound(10f);
			VolumeSlider.adjustSFX(itemSoundLoop);
			this.itemName = "chainChompItem";
			break;
		case 1:
			anim = new Animation(1, tex.bulletBillItemBall[0], tex.bulletBillItemBall[1], tex.bulletBillItemBall[2], tex.bulletBillItemBall[3],
					tex.bulletBillItemBall[4], tex.bulletBillItemBall[5], tex.bulletBillItemBall[6], tex.bulletBillItemBall[7],
					tex.bulletBillItemBall[8], tex.bulletBillItemBall[9], tex.bulletBillItemBall[10], tex.bulletBillItemBall[11],
					tex.bulletBillItemBall[12], tex.bulletBillItemBall[13], tex.bulletBillItemBall[14], tex.bulletBillItemBall[15],
					tex.bulletBillItemBall[16], tex.bulletBillItemBall[17], tex.bulletBillItemBall[18], tex.bulletBillItemBall[19],
					tex.bulletBillItemBall[20], tex.bulletBillItemBall[21], tex.bulletBillItemBall[22], tex.bulletBillItemBall[23]);
			animBackwards = new Animation(1, tex.bulletBillItemBall[23], tex.bulletBillItemBall[22], tex.chainChompItemBall[21],
					tex.bulletBillItemBall[20], tex.bulletBillItemBall[19], tex.bulletBillItemBall[18], tex.bulletBillItemBall[17],
					tex.bulletBillItemBall[16], tex.bulletBillItemBall[15], tex.bulletBillItemBall[14], tex.bulletBillItemBall[13],
					tex.bulletBillItemBall[12], tex.bulletBillItemBall[11], tex.bulletBillItemBall[10], tex.bulletBillItemBall[9],
					tex.bulletBillItemBall[8], tex.bulletBillItemBall[7], tex.bulletBillItemBall[6], tex.bulletBillItemBall[5],
					tex.bulletBillItemBall[4], tex.bulletBillItemBall[3], tex.bulletBillItemBall[2], tex.bulletBillItemBall[1],
					tex.bulletBillItemBall[0]);
			
			itemFile = "res/Sounds/SFX/Items/sm64_scuttlebug_hop.wav";
			itemSoundLoop = new SoundLoops(itemFile);
			itemSoundLoop.reduceSound(10f);
			VolumeSlider.adjustSFX(itemSoundLoop);
			this.itemName = "bulletBillItem";
			break;
		case 2:
			anim = new Animation(1, tex.bombOmbItemBall[0], tex.bombOmbItemBall[1], tex.bombOmbItemBall[2], tex.bombOmbItemBall[3],
					tex.bombOmbItemBall[4], tex.bombOmbItemBall[5], tex.bombOmbItemBall[6], tex.bombOmbItemBall[7],
					tex.bombOmbItemBall[8], tex.bombOmbItemBall[9], tex.bombOmbItemBall[10], tex.bombOmbItemBall[11],
					tex.bombOmbItemBall[12], tex.bombOmbItemBall[13], tex.bombOmbItemBall[14], tex.bombOmbItemBall[15],
					tex.bombOmbItemBall[16], tex.bombOmbItemBall[17], tex.bombOmbItemBall[18], tex.bombOmbItemBall[19],
					tex.bombOmbItemBall[20], tex.bombOmbItemBall[21], tex.bombOmbItemBall[22], tex.bombOmbItemBall[23]);
			animBackwards = new Animation(1, tex.bombOmbItemBall[23], tex.bombOmbItemBall[22], tex.bombOmbItemBall[21],
					tex.bombOmbItemBall[20], tex.bombOmbItemBall[19], tex.bombOmbItemBall[18], tex.bombOmbItemBall[17],
					tex.bombOmbItemBall[16], tex.bombOmbItemBall[15], tex.bombOmbItemBall[14], tex.bombOmbItemBall[13],
					tex.bombOmbItemBall[12], tex.bombOmbItemBall[11], tex.bombOmbItemBall[10], tex.bombOmbItemBall[9],
					tex.bombOmbItemBall[8], tex.bombOmbItemBall[7], tex.bombOmbItemBall[6], tex.bombOmbItemBall[5],
					tex.bombOmbItemBall[4], tex.bombOmbItemBall[3], tex.bombOmbItemBall[2], tex.bombOmbItemBall[1],
					tex.bombOmbItemBall[0]);
			
			itemFile = "res/Sounds/SFX/Items/sm64_scuttlebug_hop.wav";
			itemSoundLoop = new SoundLoops(itemFile);
			itemSoundLoop.reduceSound(10f);
			VolumeSlider.adjustSFX(itemSoundLoop);
			this.itemName = "bombOmbItem";
			break;
		case 3:
			anim = new Animation(1, tex.cheepCheepsItemBall[0], tex.cheepCheepsItemBall[1], tex.cheepCheepsItemBall[2], tex.cheepCheepsItemBall[3],
					tex.cheepCheepsItemBall[4], tex.cheepCheepsItemBall[5], tex.cheepCheepsItemBall[6], tex.cheepCheepsItemBall[7],
					tex.cheepCheepsItemBall[8], tex.cheepCheepsItemBall[9], tex.cheepCheepsItemBall[10], tex.cheepCheepsItemBall[11],
					tex.cheepCheepsItemBall[12], tex.cheepCheepsItemBall[13], tex.cheepCheepsItemBall[14], tex.cheepCheepsItemBall[15],
					tex.cheepCheepsItemBall[16], tex.cheepCheepsItemBall[17], tex.cheepCheepsItemBall[18], tex.cheepCheepsItemBall[19],
					tex.cheepCheepsItemBall[20], tex.cheepCheepsItemBall[21], tex.cheepCheepsItemBall[22], tex.cheepCheepsItemBall[23]);
			animBackwards = new Animation(1, tex.cheepCheepsItemBall[23], tex.cheepCheepsItemBall[22], tex.cheepCheepsItemBall[21],
					tex.cheepCheepsItemBall[20], tex.cheepCheepsItemBall[19], tex.cheepCheepsItemBall[18], tex.cheepCheepsItemBall[17],
					tex.cheepCheepsItemBall[16], tex.cheepCheepsItemBall[15], tex.cheepCheepsItemBall[14], tex.cheepCheepsItemBall[13],
					tex.cheepCheepsItemBall[12], tex.cheepCheepsItemBall[11], tex.cheepCheepsItemBall[10], tex.cheepCheepsItemBall[9],
					tex.cheepCheepsItemBall[8], tex.cheepCheepsItemBall[7], tex.cheepCheepsItemBall[6], tex.cheepCheepsItemBall[5],
					tex.cheepCheepsItemBall[4], tex.cheepCheepsItemBall[3], tex.cheepCheepsItemBall[2], tex.cheepCheepsItemBall[1],
					tex.cheepCheepsItemBall[0]);
			
			itemFile = "res/Sounds/SFX/Items/sm64_scuttlebug_hop.wav";
			itemSoundLoop = new SoundLoops(itemFile);
			itemSoundLoop.reduceSound(10f);
			VolumeSlider.adjustSFX(itemSoundLoop);
			this.itemName = "cheepCheepsItem";
			break;
		case 4:
			if(Game.item4Unlocked) {
				anim = new Animation(1, tex.ampItemBall[0], tex.ampItemBall[1], tex.ampItemBall[2], tex.ampItemBall[3],
						tex.ampItemBall[4], tex.ampItemBall[5], tex.ampItemBall[6], tex.ampItemBall[7],
						tex.ampItemBall[8], tex.ampItemBall[9], tex.ampItemBall[10], tex.ampItemBall[11],
						tex.ampItemBall[12], tex.ampItemBall[13], tex.ampItemBall[14], tex.ampItemBall[15],
						tex.ampItemBall[16], tex.ampItemBall[17], tex.ampItemBall[18], tex.ampItemBall[19],
						tex.ampItemBall[20], tex.ampItemBall[21], tex.ampItemBall[22], tex.ampItemBall[23]);
				animBackwards = new Animation(1, tex.ampItemBall[23], tex.ampItemBall[22], tex.ampItemBall[21],
						tex.ampItemBall[20], tex.ampItemBall[19], tex.ampItemBall[18], tex.ampItemBall[17],
						tex.ampItemBall[16], tex.ampItemBall[15], tex.ampItemBall[14], tex.ampItemBall[13],
						tex.ampItemBall[12], tex.ampItemBall[11], tex.ampItemBall[10], tex.ampItemBall[9],
						tex.ampItemBall[8], tex.ampItemBall[7], tex.ampItemBall[6], tex.ampItemBall[5],
						tex.ampItemBall[4], tex.ampItemBall[3], tex.ampItemBall[2], tex.ampItemBall[1],
						tex.ampItemBall[0]);
				
				itemFile = "res/Sounds/SFX/Items/sm64_scuttlebug_hop.wav";
				itemSoundLoop = new SoundLoops(itemFile);
				itemSoundLoop.reduceSound(10f);
				VolumeSlider.adjustSFX(itemSoundLoop);
				this.itemName = "ampItem";
				break;
			}
			else if(Game.item5Unlocked) {
				anim = new Animation(1, tex.wigglerItemBall[0], tex.wigglerItemBall[1], tex.wigglerItemBall[2], tex.wigglerItemBall[3],
						tex.wigglerItemBall[4], tex.wigglerItemBall[5], tex.wigglerItemBall[6], tex.wigglerItemBall[7],
						tex.wigglerItemBall[8], tex.wigglerItemBall[9], tex.wigglerItemBall[10], tex.wigglerItemBall[11],
						tex.wigglerItemBall[12], tex.wigglerItemBall[13], tex.wigglerItemBall[14], tex.wigglerItemBall[15],
						tex.wigglerItemBall[16], tex.wigglerItemBall[17], tex.wigglerItemBall[18], tex.wigglerItemBall[19],
						tex.wigglerItemBall[20], tex.wigglerItemBall[21], tex.wigglerItemBall[22], tex.wigglerItemBall[23]);
				animBackwards = new Animation(1, tex.wigglerItemBall[23], tex.wigglerItemBall[22], tex.wigglerItemBall[21],
						tex.wigglerItemBall[20], tex.wigglerItemBall[19], tex.wigglerItemBall[18], tex.wigglerItemBall[17],
						tex.wigglerItemBall[16], tex.wigglerItemBall[15], tex.wigglerItemBall[14], tex.wigglerItemBall[13],
						tex.wigglerItemBall[12], tex.wigglerItemBall[11], tex.wigglerItemBall[10], tex.wigglerItemBall[9],
						tex.wigglerItemBall[8], tex.wigglerItemBall[7], tex.wigglerItemBall[6], tex.wigglerItemBall[5],
						tex.wigglerItemBall[4], tex.wigglerItemBall[3], tex.wigglerItemBall[2], tex.wigglerItemBall[1],
						tex.wigglerItemBall[0]);
				
				itemFile = "res/Sounds/SFX/Items/sm64_scuttlebug_hop.wav";
				itemSoundLoop = new SoundLoops(itemFile);
				itemSoundLoop.reduceSound(10f);
				VolumeSlider.adjustSFX(itemSoundLoop);
				this.itemName = "wigglerItem";
				break;
			}
			else if(Game.item6Unlocked) {
				anim = new Animation(1, tex.lakituItemBall[0], tex.lakituItemBall[1], tex.lakituItemBall[2], tex.lakituItemBall[3],
						tex.lakituItemBall[4], tex.lakituItemBall[5], tex.lakituItemBall[6], tex.lakituItemBall[7],
						tex.lakituItemBall[8], tex.lakituItemBall[9], tex.lakituItemBall[10], tex.lakituItemBall[11],
						tex.lakituItemBall[12], tex.lakituItemBall[13], tex.lakituItemBall[14], tex.lakituItemBall[15],
						tex.lakituItemBall[16], tex.lakituItemBall[17], tex.lakituItemBall[18], tex.lakituItemBall[19],
						tex.lakituItemBall[20], tex.lakituItemBall[21], tex.lakituItemBall[22], tex.lakituItemBall[23]);
				animBackwards = new Animation(1, tex.lakituItemBall[23], tex.lakituItemBall[22], tex.lakituItemBall[21],
						tex.lakituItemBall[20], tex.lakituItemBall[19], tex.lakituItemBall[18], tex.lakituItemBall[17],
						tex.lakituItemBall[16], tex.lakituItemBall[15], tex.lakituItemBall[14], tex.lakituItemBall[13],
						tex.lakituItemBall[12], tex.lakituItemBall[11], tex.lakituItemBall[10], tex.lakituItemBall[9],
						tex.lakituItemBall[8], tex.lakituItemBall[7], tex.lakituItemBall[6], tex.lakituItemBall[5],
						tex.lakituItemBall[4], tex.lakituItemBall[3], tex.lakituItemBall[2], tex.lakituItemBall[1],
						tex.lakituItemBall[0]);
				
				itemFile = "res/Sounds/SFX/Items/sm64_scuttlebug_hop.wav";
				itemSoundLoop = new SoundLoops(itemFile);
				itemSoundLoop.reduceSound(10f);
				VolumeSlider.adjustSFX(itemSoundLoop);
				this.itemName = "lakituItem";
				break;
			}
			break;
		case 5:
			if(Game.item5Unlocked) {
				anim = new Animation(1, tex.wigglerItemBall[0], tex.wigglerItemBall[1], tex.wigglerItemBall[2], tex.wigglerItemBall[3],
						tex.wigglerItemBall[4], tex.wigglerItemBall[5], tex.wigglerItemBall[6], tex.wigglerItemBall[7],
						tex.wigglerItemBall[8], tex.wigglerItemBall[9], tex.wigglerItemBall[10], tex.wigglerItemBall[11],
						tex.wigglerItemBall[12], tex.wigglerItemBall[13], tex.wigglerItemBall[14], tex.wigglerItemBall[15],
						tex.wigglerItemBall[16], tex.wigglerItemBall[17], tex.wigglerItemBall[18], tex.wigglerItemBall[19],
						tex.wigglerItemBall[20], tex.wigglerItemBall[21], tex.wigglerItemBall[22], tex.wigglerItemBall[23]);
				animBackwards = new Animation(1, tex.wigglerItemBall[23], tex.wigglerItemBall[22], tex.wigglerItemBall[21],
						tex.wigglerItemBall[20], tex.wigglerItemBall[19], tex.wigglerItemBall[18], tex.wigglerItemBall[17],
						tex.wigglerItemBall[16], tex.wigglerItemBall[15], tex.wigglerItemBall[14], tex.wigglerItemBall[13],
						tex.wigglerItemBall[12], tex.wigglerItemBall[11], tex.wigglerItemBall[10], tex.wigglerItemBall[9],
						tex.wigglerItemBall[8], tex.wigglerItemBall[7], tex.wigglerItemBall[6], tex.wigglerItemBall[5],
						tex.wigglerItemBall[4], tex.wigglerItemBall[3], tex.wigglerItemBall[2], tex.wigglerItemBall[1],
						tex.wigglerItemBall[0]);
				
				itemFile = "res/Sounds/SFX/Items/sm64_scuttlebug_hop.wav";
				itemSoundLoop = new SoundLoops(itemFile);
				itemSoundLoop.reduceSound(10f);
				VolumeSlider.adjustSFX(itemSoundLoop);
				this.itemName = "wigglerItem";
				break;
			}
			else if(Game.item6Unlocked) {
				anim = new Animation(1, tex.lakituItemBall[0], tex.lakituItemBall[1], tex.lakituItemBall[2], tex.lakituItemBall[3],
						tex.lakituItemBall[4], tex.lakituItemBall[5], tex.lakituItemBall[6], tex.lakituItemBall[7],
						tex.lakituItemBall[8], tex.lakituItemBall[9], tex.lakituItemBall[10], tex.lakituItemBall[11],
						tex.lakituItemBall[12], tex.lakituItemBall[13], tex.lakituItemBall[14], tex.lakituItemBall[15],
						tex.lakituItemBall[16], tex.lakituItemBall[17], tex.lakituItemBall[18], tex.lakituItemBall[19],
						tex.lakituItemBall[20], tex.lakituItemBall[21], tex.lakituItemBall[22], tex.lakituItemBall[23]);
				animBackwards = new Animation(1, tex.lakituItemBall[23], tex.lakituItemBall[22], tex.lakituItemBall[21],
						tex.lakituItemBall[20], tex.lakituItemBall[19], tex.lakituItemBall[18], tex.lakituItemBall[17],
						tex.lakituItemBall[16], tex.lakituItemBall[15], tex.lakituItemBall[14], tex.lakituItemBall[13],
						tex.lakituItemBall[12], tex.lakituItemBall[11], tex.lakituItemBall[10], tex.lakituItemBall[9],
						tex.lakituItemBall[8], tex.lakituItemBall[7], tex.lakituItemBall[6], tex.lakituItemBall[5],
						tex.lakituItemBall[4], tex.lakituItemBall[3], tex.lakituItemBall[2], tex.lakituItemBall[1],
						tex.lakituItemBall[0]);
				
				itemFile = "res/Sounds/SFX/Items/sm64_scuttlebug_hop.wav";
				itemSoundLoop = new SoundLoops(itemFile);
				itemSoundLoop.reduceSound(10f);
				VolumeSlider.adjustSFX(itemSoundLoop);
				this.itemName = "lakituItem";
				break;
			}
			break;
		case 6:
			anim = new Animation(1, tex.lakituItemBall[0], tex.lakituItemBall[1], tex.lakituItemBall[2], tex.lakituItemBall[3],
					tex.lakituItemBall[4], tex.lakituItemBall[5], tex.lakituItemBall[6], tex.lakituItemBall[7],
					tex.lakituItemBall[8], tex.lakituItemBall[9], tex.lakituItemBall[10], tex.lakituItemBall[11],
					tex.lakituItemBall[12], tex.lakituItemBall[13], tex.lakituItemBall[14], tex.lakituItemBall[15],
					tex.lakituItemBall[16], tex.lakituItemBall[17], tex.lakituItemBall[18], tex.lakituItemBall[19],
					tex.lakituItemBall[20], tex.lakituItemBall[21], tex.lakituItemBall[22], tex.lakituItemBall[23]);
			animBackwards = new Animation(1, tex.lakituItemBall[23], tex.lakituItemBall[22], tex.lakituItemBall[21],
					tex.lakituItemBall[20], tex.lakituItemBall[19], tex.lakituItemBall[18], tex.lakituItemBall[17],
					tex.lakituItemBall[16], tex.lakituItemBall[15], tex.lakituItemBall[14], tex.lakituItemBall[13],
					tex.lakituItemBall[12], tex.lakituItemBall[11], tex.lakituItemBall[10], tex.lakituItemBall[9],
					tex.lakituItemBall[8], tex.lakituItemBall[7], tex.lakituItemBall[6], tex.lakituItemBall[5],
					tex.lakituItemBall[4], tex.lakituItemBall[3], tex.lakituItemBall[2], tex.lakituItemBall[1],
					tex.lakituItemBall[0]);
			
			itemFile = "res/Sounds/SFX/Items/sm64_scuttlebug_hop.wav";
			itemSoundLoop = new SoundLoops(itemFile);
			itemSoundLoop.reduceSound(10f);
			VolumeSlider.adjustSFX(itemSoundLoop);
			this.itemName = "lakituItem";
			break;
		default:
			anim = new Animation(1, tex.chainChompItemBall[0], tex.chainChompItemBall[1], tex.chainChompItemBall[2], tex.chainChompItemBall[3],
					tex.chainChompItemBall[4], tex.chainChompItemBall[5], tex.chainChompItemBall[6], tex.chainChompItemBall[7],
					tex.chainChompItemBall[8], tex.chainChompItemBall[9], tex.chainChompItemBall[10], tex.chainChompItemBall[11],
					tex.chainChompItemBall[12], tex.chainChompItemBall[13], tex.chainChompItemBall[14], tex.chainChompItemBall[15],
					tex.chainChompItemBall[16], tex.chainChompItemBall[17], tex.chainChompItemBall[18], tex.chainChompItemBall[19],
					tex.chainChompItemBall[20], tex.chainChompItemBall[21], tex.chainChompItemBall[22], tex.chainChompItemBall[23],
					tex.chainChompItemBall[24]);
			animBackwards = new Animation(1, tex.chainChompItemBall[24], tex.chainChompItemBall[23], tex.chainChompItemBall[22], tex.chainChompItemBall[21],
					tex.chainChompItemBall[20], tex.chainChompItemBall[19], tex.chainChompItemBall[18], tex.chainChompItemBall[17],
					tex.chainChompItemBall[16], tex.chainChompItemBall[15], tex.chainChompItemBall[14], tex.chainChompItemBall[13],
					tex.chainChompItemBall[12], tex.chainChompItemBall[11], tex.chainChompItemBall[10], tex.chainChompItemBall[9],
					tex.chainChompItemBall[8], tex.chainChompItemBall[7], tex.chainChompItemBall[6], tex.chainChompItemBall[5],
					tex.chainChompItemBall[4], tex.chainChompItemBall[3], tex.chainChompItemBall[2], tex.chainChompItemBall[1],
					tex.chainChompItemBall[0]);
			
			itemFile = "res/Sounds/SFX/Items/sm64_chain_chomp.wav";
			itemSoundLoop = new SoundLoops(itemFile);
			itemSoundLoop.reduceSound(10f);
			VolumeSlider.adjustSFX(itemSoundLoop);
			break;
		}
		this.itemSoundLoop = itemSoundLoop;
	}

	@Override
	public void tick() {
		if(itemSpawnLocation == false){
			x += 2.5; //1.5
			y += velY;
			if(y > Game.HEIGHT * Game.SCALE - 33){
				velY = velY - 0.2; //0.5
			}
			else if(y < Game.HEIGHT * Game.SCALE){
				velY = velY + 0.2; //0.5
			}
			if (this.getX() >= (Game.WIDTH * Game.SCALE)){
				game.ed.remove(this);
			}
		}
		else{
			x -= 2.5; //1.5
			y += velY;
			if(y > Game.HEIGHT * Game.SCALE - 33){
				velY = velY - 0.2; //0.5
			}
			else if(y < Game.HEIGHT * Game.SCALE){
				velY = velY + 0.2; //0.5
			}
			if (this.getX() +16 <= 0){
				game.ed.remove(this);
			}
		}
		
		for(int i = 0; i < game.ea.size()-1; i++){
			EntityA tempEnt = game.ea.get(i);
			if(Physics.Collision(this, tempEnt)){
				game.ed.remove(this);
				if(!game.ea.isEmpty())
					game.ea.remove(game.ea.getLast());
			}
		}
		/*
		if(Physics.Collision(this, game.ea.getFirst())){
			//MAKE PLAYER INVULNERABLE THROUGH BOOLEAN OR SOMETHIN(HEALTH + EXTRA INT MAYBE)
			game.ec.remove(this);
		}*/
		if(itemSpawnLocation == false)
			anim.runAnimation();
		else
			animBackwards.runAnimation();
	}

	@Override
	public void render(Graphics g) {
		if(itemSpawnLocation == false)
			anim.drawAnimation(g, x, y, 0);
		else
			animBackwards.drawAnimation(g, x, y, 0);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 14, 16);
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public String getItemName() {
		return itemName;
	}

	public SoundLoops getItemSoundLoop() {
		return itemSoundLoop;
	}

	public void setItemSoundLoop(SoundLoops itemSoundLoop) {
		this.itemSoundLoop = itemSoundLoop;
	}
	
}
