package com.example.deftower;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.content.Context;
import android.util.Log;

public class Enemy {

	public TiledTextureRegion mXeTangTiledTextureRegion;
	
	private AnimatedSprite face;
	private float positionBeginX;
	private float positionBeginY;
	final PhysicsHandler physicsHandler;
	private float speed; // toc do
	private int direction; // huong di
	private int tempD =0;
	private int landmark; // danh dau cac trang duong
	private boolean isDead;
	private int HP;

	private Rectangle[] ways = {
			new Rectangle(75, 85, 239, 241, 2, new int[] { Rectangle.UP,
					Rectangle.DOWN }),
					
			new Rectangle(75, 85, 203, 213, 1, new int[] { Rectangle.RIGHT }),
			new Rectangle(107, 117, 203, 213, 1, new int[] { Rectangle.UP}),
			new Rectangle(107, 117, 171, 181, 1, new int[] { Rectangle.RIGHT }),
			new Rectangle(139, 149, 171, 181, 1, new int[] { Rectangle.UP}),
			new Rectangle(139, 149, 139, 149, 1, new int[] { Rectangle.RIGHT }),
			new Rectangle(619, 629, 139, 149, 1, new int[] { Rectangle.DOWN }),
			new Rectangle(619, 629, 171, 181, 1, new int[] { Rectangle.RIGHT }),
			new Rectangle(651, 661, 171, 181, 1, new int[] { Rectangle.DOWN }),
			new Rectangle(651, 661, 203, 213, 1, new int[] { Rectangle.RIGHT }),
			new Rectangle(683, 693, 203, 213, 1, new int[] { Rectangle.DOWN }),
			new Rectangle(683, 693, 235, 245, 1, new int[] { Rectangle.RIGHT }),
			
			new Rectangle(75, 85, 267, 277, 1, new int[] { Rectangle.RIGHT }),
			new Rectangle(107, 117, 267, 277, 1, new int[] { Rectangle.DOWN}),
			new Rectangle(107, 117, 299, 309, 1, new int[] { Rectangle.RIGHT }),
			new Rectangle(139, 149, 299, 309, 1, new int[] { Rectangle.DOWN}),
			new Rectangle(139, 149, 331, 341, 1, new int[] { Rectangle.RIGHT }),
			new Rectangle(619, 629, 331, 341, 1, new int[] { Rectangle.UP }),
			new Rectangle(619, 629, 299, 309, 1, new int[] { Rectangle.RIGHT }),
			new Rectangle(651, 661, 299, 309, 1, new int[] { Rectangle.UP }),
			new Rectangle(651, 661, 267, 277, 1, new int[] { Rectangle.RIGHT }),
			new Rectangle(683, 693, 267, 277, 1, new int[] { Rectangle.UP }),
 };

	public Enemy(TiledTextureRegion TTR, float pbx, float pby) {
		
		positionBeginX = pbx;
		positionBeginY = pby;
		mXeTangTiledTextureRegion = TTR;
		face = new AnimatedSprite(positionBeginX,
				positionBeginY, this.mXeTangTiledTextureRegion);
		physicsHandler = new PhysicsHandler(face);
		face.registerUpdateHandler(physicsHandler);
		landmark = -1;
		direction = Rectangle.RIGHT;
		isDead = false;
		speed = 50;
	}

	public AnimatedSprite getSprite() {
		return face;
	}

	public void afterRun(){
		//face.animate(new long[]{200, 200, 200, 200, 200, 200, 200}, 1, 7, true);
		//face.animate(new long[]{200, 200, 200}, 6, 8, true);
		//face.setScale(0.5f);
	}
	
	public void runrunrun() {
		float pX, pY, pVX = 0, pVY = 0;
		pX = face.getX() + face.getWidth() / 2;
		pY = face.getY() + face.getHeight() / 2;
		if (direction == Rectangle.RIGHT) {
			if (tempD != direction){
				face.animate(new long[]{200, 200, 200}, 6, 8, true);
				tempD = direction;
			}
			//face.setRotation(0);
			pVX = 1;
			pVY = 0;
		} else if (direction == Rectangle.LEFT) {
			if (tempD != direction){
				face.animate(new long[]{200, 200, 200}, 3, 5, true);
				tempD = direction;
			}
			//face.setRotation(180);
			pVX = -1;
			pVY = 0;
		} else if (direction == Rectangle.DOWN) {
			if (tempD != direction){
				face.animate(new long[]{200, 200, 200}, 0, 2, true);
				tempD = direction;
			}
			//face.setRotation(90);
			pVX = 0;
			pVY = 1;
		} else if (direction == Rectangle.UP) {
			if (tempD != direction){
				face.animate(new long[]{200, 200, 200}, 9, 11, true);
				tempD = direction;
			}
			//face.setRotation(270);
			pVX = 0;
			pVY = -1;
		}
		
		for (int i=0; i<ways.length; i++){
			if (ways[i].isInside(pX,  pY) == true && landmark != i){
				direction = ways[i].getway();
				//Log.v("Coin", direction + " - " + landmark +" - " + ways[i].x1 +" - "+ ways[i].x2 + " - " + ways[i].y1 + " - " + ways[i].y2);
				landmark = i;
			}
		}
		
		physicsHandler.setVelocity(pVX * speed, pVY * speed);
	}

}
