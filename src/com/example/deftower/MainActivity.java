package com.example.deftower;

import java.util.ArrayList;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.BoundCamera;
import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLayer;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXProperties;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXProperty;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTileProperty;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTiledMap;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.util.Log;

public class MainActivity extends BaseGameActivity {

	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;

	private BoundCamera mCamera;
	private Texture[] mTexture;
	private Texture tTexture;
	private Texture bTexture;

	private TiledTextureRegion[] mXeTangTiledTextureRegion;
	private TextureRegion tTextureRegion;
	private TiledTextureRegion bTextureRegion;

	private Texture textureAnh1;
	private TiledTextureRegion textureReAnh1;

	private Texture texturetest;
	private TextureRegion test;

	private Texture textureAnh2;
	private TiledTextureRegion textureReAnh2;

	private Texture towerOneTeture;
	private TiledTextureRegion towerOneTetureRegion;

	private Texture textureAnh2re;
	private TiledTextureRegion textureReAnh2re;

	private Texture textureAnh3re;
	private TiledTextureRegion textureReAnh3re;

	private Texture textureAnh4re;
	private TiledTextureRegion textureReAnh4re;

	private Texture textureAnh3;
	private TiledTextureRegion textureReAnh3;

	private Texture textureAnh4;
	private TiledTextureRegion textureReAnh4;

	private Texture textlocation;
	private TiledTextureRegion textRelocation;

	private TMXTiledMap mTmxTiledMap;
	private TMXLayer VatCanTMXLayer;
	private String tenBanDo = "maptest.tmx";

	private Sprite spTower;
	private Sprite spBullet;
	private PhysicsHandler physicsHandler;

	private Tower towerTest;
	private Tower tower2;
	private ArrayList<Tower> arrTower;

	int timeCount = 1;
	int dem = 0;
	int dandaban = 0;
	int click, i, j;

	// Enemy enyTest;
	Enemy[] someTest;

	@Override
	public Engine onLoadEngine() {

		this.mCamera = new BoundCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
				this.mCamera));
	}

	@Override
	public void onLoadResources() {
		// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
		// }
		TextureRegionFactory.setAssetBasePath("gfx/");

		// this.mTexture = new Texture(256, 32,
		// TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		// this.mXeTangTiledTextureRegion =
		// TextureRegionFactory.createTiledFromAsset(mTexture, this,
		// "XeTang.png", 0, 0, 8, 1);

		mTexture = new Texture[20];
		mXeTangTiledTextureRegion = new TiledTextureRegion[20];
		for (int i = 0; i < 20; i++) {
			this.mTexture[i] = new Texture(256, 256,
					TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			this.mXeTangTiledTextureRegion[i] = TextureRegionFactory
					.createTiledFromAsset(mTexture[i], this, "red_dragon.png",
							0, 0, 3, 4);
			this.mEngine.getTextureManager().loadTextures(mTexture[i]);
		}

		this.tTexture = new Texture(64, 64,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.tTextureRegion = TextureRegionFactory.createFromAsset(tTexture,
				this, "anh2_fixed.png", 0, 0);
		this.mEngine.getTextureManager().loadTexture(tTexture);

		this.bTexture = new Texture(16, 16,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.bTextureRegion = TextureRegionFactory.createTiledFromAsset(
				bTexture, this, "bullet.png", 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(bTexture);

		this.textureAnh1 = new Texture(2048, 2048,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.textureReAnh1 = TextureRegionFactory.createTiledFromAsset(
				this.textureAnh1, this, "concussion_turret_1.png", 0, 0, 30, 1);

		this.texturetest = new Texture(32, 32,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.test = TextureRegionFactory.createFromAsset(this.texturetest,
				this, "anh4re.png", 0, 0);

		//teture button ao de xac dinh vi tri dat tower
		this.textlocation = new Texture(32, 32,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.textRelocation = TextureRegionFactory.createTiledFromAsset(
				this.textlocation, this, "hiden.png", 0, 0, 1, 1);

		this.textureAnh2 = new Texture(128, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.textureReAnh2 = TextureRegionFactory.createTiledFromAsset(
				this.textureAnh2, this, "anh2.png", 0, 0, 1, 1);

		//khoi tao texture cho tower 1
		this.towerOneTeture = new Texture(2048, 2048,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.towerOneTetureRegion = TextureRegionFactory.createTiledFromAsset(
				this.towerOneTeture, this, "concussion_turret_1.png", 0, 0, 30, 1);

		this.textureAnh2re = new Texture(32, 32,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.textureReAnh2re = TextureRegionFactory.createTiledFromAsset(
				this.textureAnh2re, this, "anh2re.png", 0, 0, 1, 1);

		this.textureAnh3re = new Texture(32, 32,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.textureReAnh3re = TextureRegionFactory.createTiledFromAsset(
				this.textureAnh3re, this, "anh3re.png", 0, 0, 1, 1);

		this.textureAnh4re = new Texture(32, 32,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.textureReAnh4re = TextureRegionFactory.createTiledFromAsset(
				this.textureAnh4re, this, "anh4re.png", 0, 0, 1, 1);

		this.textureAnh3 = new Texture(128, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.textureReAnh3 = TextureRegionFactory.createTiledFromAsset(
				this.textureAnh3, this, "anh3.png", 0, 0, 1, 1);
		//
		this.textureAnh4 = new Texture(128, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.textureReAnh4 = TextureRegionFactory.createTiledFromAsset(
				this.textureAnh4, this, "anh4.png", 0, 0, 1, 1);

		this.mEngine.getTextureManager().loadTextures(this.textureAnh1,
				this.textureAnh2, this.textureAnh3, this.textureAnh4,
				this.textlocation, this.towerOneTeture, this.textureAnh2re,
				this.textureAnh3re, this.textureAnh4re, this.texturetest);
	}

	@Override
	public Scene onLoadScene() {

		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene scene = new Scene();
		scene.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));

		arrTower = new ArrayList<Tower>();

		// khoi tao map
		mTmxTiledMap = TaiBanDo.getTMXTiledMap(scene, mEngine, this, tenBanDo,
				this);

		ArrayList<TMXLayer> mapLayers = mTmxTiledMap.getTMXLayers();
		for (TMXLayer layer : mapLayers) {
			if (layer.getName().equals("vatcan")) {
				VatCanTMXLayer = layer;
			}
			scene.attachChild(layer);
		}
		
		//430 398
		final AnimatedSprite towerOne = new AnimatedSprite(450, 370,
				textureReAnh1) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					click = 1;
				}
				return true;
			};
		};
		towerOne.setScale(0.6f);
		towerOne.animate(200, true);
		scene.registerTouchArea(towerOne);
		scene.attachChild(towerOne);

		final AnimatedSprite towerTwo = new AnimatedSprite(495, 398,
				textureReAnh2) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					click = 2;
				}
				return true;
			};
		};
		towerTwo.setScale(0.5f);
		scene.registerTouchArea(towerTwo);
		scene.attachChild(towerTwo);
		
		final AnimatedSprite towerThree = new AnimatedSprite(558, 398,
				textureReAnh3) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					click = 3;
				}
				return true;
			};
		};
		towerThree.setScale(0.5f);
		scene.registerTouchArea(towerThree);
		scene.attachChild(towerThree);

		final AnimatedSprite towerFour = new AnimatedSprite(623, 398,
				textureReAnh4) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					click = 4;
				} else {
				}
				return true;
			};
		};
		towerFour.setScale(0.5f);
		scene.registerTouchArea(towerFour);
		scene.attachChild(towerFour);

		for (i = 0; i < CAMERA_WIDTH; i += 32)
			for (j = 0; j < CAMERA_HEIGHT; j += 32) {
				TMXTile mTMXTile = VatCanTMXLayer.getTMXTileAt(i, j);
				try {
					if (mTMXTile != null) {
						TMXProperties<TMXTileProperty> mTMXProperties = mTMXTile
								.getTMXTileProperties(mTmxTiledMap);
						TMXProperty mTmxProperty = mTMXProperties.get(0);
						if (mTmxProperty.getName().equals("vatcan")) {

						}
					}
				} catch (Exception e) {

					final AnimatedSprite buildTower = new AnimatedSprite(i,
							j, textRelocation) {

						public boolean onAreaTouched(
								TouchEvent pSceneTouchEvent,
								float pTouchAreaLocalX, float pTouchAreaLocalY) {
							if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
								if (click == 1) {
									for (Tower tw : arrTower) {
										if (tw.getPositionTowerX() == this.mX
												&& tw.getPositionTowerY() == this.mY) {
											// xu li xu kien click vao tower da
											// co san

											return true;
										}
									}
									Tower tw = new Tower(towerOneTetureRegion,
											bTextureRegion, this.mX, this.mY);
									arrTower.add(tw);
									scene.attachChild(tw.getSprite());
									tw.getSprite().animate(200, true);
									return true;

								}
								if (click == 2) {
									for (Tower tw : arrTower) {
										if (tw.getPositionTowerX() == this.mX
												&& tw.getPositionTowerY() == this.mY) {
											// xu li xu kien click vao tower da
											// co san

											return true;
										}
									}
									Tower tw = new Tower(textureReAnh2re,
											bTextureRegion, this.mX, this.mY);
									arrTower.add(tw);
									scene.attachChild(tw.getSprite());
									return true;
								}

								if (click == 3) {
									for (Tower tw : arrTower) {
										if (tw.getPositionTowerX() == this.mX
												&& tw.getPositionTowerY() == this.mY) {
											// xu li xu kien click vao tower da
											// co san

											return true;
										}
									}
									Tower tw = new Tower(textureReAnh3re,
											bTextureRegion, this.mX, this.mY);
									arrTower.add(tw);
									scene.attachChild(tw.getSprite());
									return true;

								}

								if (click == 4) {
									for (Tower tw : arrTower) {
										if (tw.getPositionTowerX() == this.mX
												&& tw.getPositionTowerY() == this.mY) {
											// xu li xu kien click vao tower da
											// co san

											return true;
										}
									}
									Tower tw = new Tower(textureReAnh4re,
											bTextureRegion, this.mX, this.mY);
									arrTower.add(tw);
									scene.attachChild(tw.getSprite());
									return true;

								}

							} else {

							}
							return true;
						};
					};
					scene.registerTouchArea(buildTower);
					scene.attachChild(buildTower);
				}
			}

		// enyTest = new Enemy(mXeTangTiledTextureRegion[0], 0, 224);
		someTest = new Enemy[20];

		// scene.attachChild(enyTest.getSprite());

		// enyTest.afterRun();

		// spTower = new Sprite(32*7, 32*5, tTextureRegion);
		// scene.attachChild(spTower);

		towerTest = new Tower(towerOneTetureRegion, bTextureRegion, 32 * 7, 32 * 5);
		tower2 = new Tower(textureReAnh2re, bTextureRegion, 32 * 10, 32 * 5);
		scene.attachChild(towerTest.getSprite());
		scene.attachChild(tower2.getSprite());

		scene.registerUpdateHandler(new TimerHandler(0.02f, true,
				new ITimerCallback() {
					@Override
					public void onTimePassed(TimerHandler pTimerHandler) {

						timeCount++;

						if (timeCount % 50 == 0 && dem < 20) {
							someTest[dem] = new Enemy(
									mXeTangTiledTextureRegion[dem], 0, 224);
							scene.attachChild(someTest[dem].getSprite());
							someTest[dem].afterRun();
							dem++;
						}

						for (int k = 0; k < dem; k++) {
							someTest[k].runrunrun();
							towerTest.checkEnemy(someTest[k]);
							tower2.checkEnemy(someTest[k]);
							for (Tower tw : arrTower) {
								tw.checkEnemy(someTest[k]);
							}
						}

						towerTest.runToFire(timeCount, scene);
						tower2.runToFire(timeCount, scene);
						for (Tower tw : arrTower) {
							tw.runToFire(timeCount, scene);
						}

						// enyTest.runrunrun();

						// tower kiem tra ban dan

						// if (Math.sqrt(Math.pow((enyTest.getSprite().getX() +
						// enyTest.getSprite().getWidth()/2 - spTower.getX() -
						// spTower.getWidth()/2), 2) +
						// Math.pow((enyTest.getSprite().getY() +
						// enyTest.getSprite().getHeight()/2 - spTower.getY() -
						// spTower.getHeight()/2), 2)) <= 2000 && dandaban != 1
						// && timeCount%250 == 0){
						// spBullet = new Sprite(spTower.getX() +
						// spTower.getWidth()/2, spTower.getY() +
						// spTower.getHeight()/2, bTextureRegion);
						// physicsHandler = new PhysicsHandler(spBullet);
						// spBullet.registerUpdateHandler(physicsHandler);
						// scene.attachChild(spBullet);
						// dandaban = 1;
						// }
						//
						// if (dandaban == 1){
						// float distantX = (spBullet.getX() +
						// spBullet.getWidth()/2 - enyTest.getSprite().getX() -
						// enyTest.getSprite().getWidth()/2);
						// float distantY = (spBullet.getY() +
						// spBullet.getHeight()/2 - enyTest.getSprite().getY() -
						// enyTest.getSprite().getHeight()/2);
						// float max = 0.0f;
						// if (Math.abs(distantX) > Math.abs(distantY))
						// max = Math.abs(distantX);
						// else
						// max = Math.abs(distantY);
						//
						// distantX /= max;
						// distantY /= max;
						//
						// Log.v("distant", distantX + " ; " + distantY);
						// physicsHandler.setVelocity( -distantX * 80, -distantY
						// * 80);
						//
						// if (spBullet.getX() + spBullet.getWidth()/2 >
						// enyTest.getSprite().getX() &&
						// spBullet.getX() + spBullet.getWidth()/2 <
						// enyTest.getSprite().getX() +
						// enyTest.getSprite().getWidth() &&
						// spBullet.getY() + spBullet.getHeight()/2 >
						// enyTest.getSprite().getY() &&
						// spBullet.getY() + spBullet.getHeight()/2 <
						// enyTest.getSprite().getY() +
						// enyTest.getSprite().getHeight()){
						// dandaban = 0;
						// scene.detachChild(spBullet);
						// }
						//
						// }

					}
				}));

		final TMXLayer tmxLayer = this.mTmxTiledMap.getTMXLayers().get(0);
		mCamera.setBounds(0, tmxLayer.getWidth(), 0, tmxLayer.getHeight());
		mCamera.setBoundsEnabled(true);
		// mCamera.setChaseEntity(enyTest.getSprite());
		return scene;
	}

	@Override
	public void onLoadComplete() {
	}
}