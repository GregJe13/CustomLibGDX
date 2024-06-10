package com.snakegame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;
import java.util.Iterator;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private OrthographicCamera camera;
	private Texture headDown;
	private Texture headUp;
	private Texture headLeft;
	private Texture headRight;
	private Rectangle up;
	private boolean isUp=true;
	private boolean isDown=false;
	private boolean isLeft=false;
	private boolean isRight=false;
	private final float speed=10;
	

	
	@Override
	public void create () {
		headDown =new Texture(Gdx.files.internal("head_down.png"));
		headUp =new Texture(Gdx.files.internal("head_up.png"));
		headLeft =new Texture(Gdx.files.internal("head_left.png"));
		headRight =new Texture(Gdx.files.internal("head_right.png"));
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,1280,720);
		up = new Rectangle();
		up.x = 20;
		up.y = 20;
		up.width = 64;
		up.height = 64;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		float delta=Gdx.graphics.getDeltaTime();
		up.y+= speed * delta;
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if (isUp){
			batch.draw(headUp,up.x,up.y);
		} else if (isDown) {
			batch.draw(headDown,up.x,up.y);
		} else if (isRight) {
			batch.draw(headRight,up.x,up.y);
		} else if (isLeft) {
			batch.draw(headLeft,up.x,up.y);
		}


		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			if (!isLeft) {
				isRight = true;
				isLeft = false;
				isUp = false;
				isDown = false;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			if (!isRight) {
				isRight = false;
				isLeft = true;
				isUp = false;
				isDown = false;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			if (!isDown) {
				isRight = false;
				isLeft = false;
				isUp = true;
				isDown = false;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			if (!isUp) {
				isRight = false;
				isLeft = false;
				isUp = false;
				isDown = true;
			}
		}


		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
