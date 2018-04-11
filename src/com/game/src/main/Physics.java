package com.game.src.main;

import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.classes.EntityD;
import com.game.src.main.classes.EntityE;

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
	
	public static boolean Collision(EntityE ente, EntityB entb){
		
		if(ente.getBounds().intersects(entb.getBounds())){
			return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityB entb, EntityE ente){
		
		if(entb.getBounds().intersects(ente.getBounds())){
			return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityE ente, EntityC entc){
		
		if(ente.getBounds().intersects(entc.getBounds())){
			return true;
		}
		return false;
	}

	public static boolean Collision(EntityC entc, EntityE ente){
	
		if(entc.getBounds().intersects(ente.getBounds())){
		return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityE ente, EntityD entd){
		
		if(ente.getBounds().intersects(entd.getBounds())){
			return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityD entd, EntityE ente){
		
		if(entd.getBounds().intersects(ente.getBounds())){
			return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityA enta, BasicBlocks bb){
		for(int i = 0; i < bb.wall.size(); i++){
			if(enta.getBounds().intersects(bb.wall.get(i).getBounds2D()))
				return true;
		}
		return false;
	}
	
	public static boolean Collision(BasicBlocks bb, EntityA enta){
		for(int i = bb.wall.size(); i > 0; i--){
			if(bb.wall.get(i-1).intersects(enta.getBounds()))
				return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityB entb, BasicBlocks bb){
		for(int i = 0; i < bb.wall.size(); i++){
			if(entb.getBounds().intersects(bb.wall.get(i).getBounds2D()))
				return true;
		}
		return false;
	}
	public static boolean Collision(BasicBlocks bb, EntityB entb){
		for(int i = bb.wall.size(); i > 0; i--){
			if(bb.wall.get(i-1).intersects(entb.getBounds()))
				return true;
		}
		return false;
	}

	public static boolean Collision(EntityC entc, BasicBlocks bb){
		for(int i = 0; i < bb.wall.size(); i++){
			if(entc.getBounds().intersects(bb.wall.get(i).getBounds2D()))
				return true;
		}
		return false;
	}
	public static boolean Collision(BasicBlocks bb, EntityC entc){
		for(int i = 0; i < bb.wall.size(); i++){
			if(bb.wall.get(i).intersects(entc.getBounds()))
				return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityE ente, BasicBlocks bb){
		for(int i = 0; i < bb.wall.size(); i++){
			if(ente.getBounds().intersects(bb.wall.get(i).getBounds2D()))
				return true;
		}
		return false;
	}
	
	public static boolean Collision(BasicBlocks bb, EntityE ente){
		for(int i = bb.wall.size(); i > 0; i--){
			if(bb.wall.get(i-1).intersects(ente.getBounds()))
				return true;
		}
		return false;
	}
	
	public static int BlockCollision(BasicBlocks bb, EntityA enta){
		for(int i = bb.wall.size(); i > 0; i--){
			if(bb.wall.get(i-1).intersects(enta.getBounds()))
				return i-1;
		}
		return -1;
	}
	
	public static int BlockCollision(BasicBlocks bb, EntityB entb){
		for(int i = 0; i < bb.wall.size(); i++){
			if(bb.wall.get(i).intersects(entb.getBounds()))
				return i;
		}
		return -1;
	}
	
	public static int BlockCollision(BasicBlocks bb, EntityC entc){
		for(int i = bb.wall.size(); i > 0; i--){
			if(bb.wall.get(i-1).intersects(entc.getBounds()))
				return i-1;
		}
		return -1;
	}
	
	public static int BlockCollision(BasicBlocks bb, EntityE ente){
		for(int i = bb.wall.size(); i > 0; i--){
			if(bb.wall.get(i-1).intersects(ente.getBounds()))
				return i-1;
		}
		return -1;
	}
	
	public static int BlockCollision(BasicBlocks bb, Rectangle rect){
		for(int i = bb.wall.size(); i > 0; i--){
			if(bb.wall.get(i-1).intersects(rect.getBounds2D()))
				return i-1;
		}
		return -1;
	}
}
