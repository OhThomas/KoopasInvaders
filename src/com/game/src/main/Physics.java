package com.game.src.main;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityD;

public class Physics {

	public static boolean Collision(EntityA enta, EntityB entb){
		
		if(enta.getBounds().intersects(entb.getBounds())){
			return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityB entb, EntityA enta){
		
		if(entb.getBounds().intersects(enta.getBounds())){
			return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityA enta, EntityC entc){
		
		if(enta.getBounds().intersects(entc.getBounds())){
			return true;
		}
		return false;
	}

	public static boolean Collision(EntityC entc, EntityA enta){
	
		if(entc.getBounds().intersects(enta.getBounds())){
		return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityA enta, EntityD entd){
		
		if(enta.getBounds().intersects(entd.getBounds())){
			return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityD entd, EntityA enta){
		
		if(entd.getBounds().intersects(enta.getBounds())){
			return true;
		}
		return false;
	}
}
