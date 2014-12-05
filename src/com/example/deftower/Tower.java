package com.example.deftower;

import java.util.ArrayList;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public class Tower {

	private TiledTextureRegion tTextureRegion;
	private TiledTextureRegion bTextureRegion;
	private AnimatedSprite spTower;
	private float area;
	private float pTowerX;
	private float pTowerY;
	private int price;
	private int speed;
	private ArrayList<Bullet> arrBullet;
	private ArrayList<Enemy> enemyInArea;
	
	public Tower(TiledTextureRegion TTR, TiledTextureRegion BTR, float PTX, float PTY){
		tTextureRegion = TTR;
		bTextureRegion = BTR;
		pTowerX = PTX;
		pTowerY = PTY;
		spTower = new AnimatedSprite(pTowerX, pTowerY, tTextureRegion);
		arrBullet = new ArrayList<Bullet>();
		enemyInArea = new ArrayList<Enemy>();
		area = 200.0f;
		speed = 50;
		price = 100;
	}
	
	
	
	public AnimatedSprite getSprite() {
		return spTower;
	}

	public void checkEnemy(Enemy enemy){
		if (Math.sqrt(Math.pow((enemy.getSprite().getX() + enemy.getSprite().getWidth()/2 - spTower.getX() -  spTower.getWidth()/2), 2) + Math.pow((enemy.getSprite().getY() + enemy.getSprite().getHeight()/2 - spTower.getY() - spTower.getHeight()/2), 2)) <= area){
			if (enemyInArea.contains(enemy) == false)
				enemyInArea.add(enemy);
			//neu enemy nam trong list va nam trong tam ban thi ke no
    	}else{
    		if (enemyInArea.contains(enemy) == true)
    			enemyInArea.remove(enemy);
    		//neu enemy ko nam trong list va nam ngoai tam ban thi ke no
    	}
	}
	
	public float getPositionTowerX() {
		return pTowerX;
	}

	public float getPositionTowerY() {
		return pTowerY;
	}


	public void runToFire(int timer, Scene scene){
		if (timer%speed == 0 && enemyInArea.size() != 0){
			Bullet blt = new Bullet(bTextureRegion, pTowerX, pTowerY, enemyInArea.get(0));
			blt.setFire();
			scene.attachChild(blt.getSprite());
			arrBullet.add(blt);
		}
		if (enemyInArea.size() != 0){
			for (int i=0; i<arrBullet.size();){
				if (arrBullet.get(i).isFire() == 0){
					scene.detachChild(arrBullet.get(i).getSprite());
					arrBullet.remove(arrBullet.get(i));
				}else{
					i++;
				}
			}
			for (Bullet bullet: arrBullet){
				bullet.runToKill();
			}
		}
	}
	
}
