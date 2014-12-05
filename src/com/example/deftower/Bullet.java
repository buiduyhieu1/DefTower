package com.example.deftower;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.util.Log;

public class Bullet {
	private TiledTextureRegion bTextureRegion;
	private AnimatedSprite spBullet;
    private PhysicsHandler physicsHandler;
    private int dandaban = 0;
    private float pTowerX;
    private float pTowerY;
    private int speed;
    private int damage;
    private Enemy target;
    
    public Bullet(TiledTextureRegion bTextureRegion2, float PTX, float PTY, Enemy enemyTarget){
    	bTextureRegion = bTextureRegion2;
    	pTowerX = PTX;
    	pTowerY = PTY;
    	spBullet = new AnimatedSprite(pTowerX, pTowerY, bTextureRegion);
    	physicsHandler = new PhysicsHandler(spBullet);
    	spBullet.registerUpdateHandler(physicsHandler);
    	dandaban = 0;
    	speed = 100;
    	damage = 100;
    	target = enemyTarget;
    }
    
    public AnimatedSprite getSprite() {
		return spBullet;
	}
    
    public void runToKill(){
    	if (dandaban == 1){
    		float distantX = (spBullet.getX() + spBullet.getWidth()/2 - target.getSprite().getX() -  target.getSprite().getWidth()/2);
        	float distantY = (spBullet.getY() + spBullet.getHeight()/2 - target.getSprite().getY() -  target.getSprite().getHeight()/2);
    		float max = 0.0f;
    		if (Math.abs(distantX) > Math.abs(distantY))
    			max = Math.abs(distantX);
    		else
    			max = Math.abs(distantY);
    		
    		distantX /= max;
    		distantY /= max;
    		
    		Log.v("distant", distantX + " ; " + distantY);
    		physicsHandler.setVelocity( -distantX * speed, -distantY * speed);
    		
    		if (spBullet.getX() + spBullet.getWidth()/2 > target.getSprite().getX() && 
    			spBullet.getX() + spBullet.getWidth()/2 < target.getSprite().getX() + target.getSprite().getWidth() &&
    			spBullet.getY() + spBullet.getHeight()/2 > target.getSprite().getY() && 
    			spBullet.getY() + spBullet.getHeight()/2 < target.getSprite().getY() + target.getSprite().getHeight()){
    			killTarget();
    		}
    		
    	}
    }
    
    //xu li va cham muc tieu o day, khi ke thua lop nay thi viet lai phuong thuc nay
    public void killTarget(){
    	dandaban = 0;
		//scene.detachChild(spBullet);
    }

	public int isFire() {
		return dandaban;
	}

	public void setFire() {
		this.dandaban = 1;
	}
	
}
