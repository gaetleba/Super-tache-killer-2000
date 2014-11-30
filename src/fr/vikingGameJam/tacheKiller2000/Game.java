package fr.vikingGameJam.tacheKiller2000;

import java.util.LinkedList;

import org.lwjgl.Sys;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter
{
	public static final int WIDTH = 600;
	public static final int HEIGHT = 860;

	private static float difficulty;

	SpriteBatch batch;
	Texture moustacheTexture;
	private float stateTime;
	private Moustache moustache;
	private LinkedList<Tache> taches;
	private LinkedList<Missile> missiles;
	private boolean gameOver;
	
	private long lastMissile;

	@Override
	public void create()
	{
		batch = new SpriteBatch();
		moustacheTexture = new Texture("assets/moustache.png");
		stateTime = 0;
		taches = new LinkedList<Tache>();
		missiles = new LinkedList<>();
		difficulty = 1;
		gameOver = false;
		lastMissile = System.currentTimeMillis();

		{// Creation moustache
			int nbFrames = 16;
			Sprite[] moustacheFrames = new Sprite[nbFrames];
			int width = 128;
			int height = 64;
			for (int i = 0; i < nbFrames; i++)
				moustacheFrames[i] = new Sprite(moustacheTexture, i * width, 0,
						width, height);
			moustache = new Moustache(2.0F, moustacheFrames);
			moustache.setPlayMode(PlayMode.LOOP);
		}
	}

	@Override
	public void render()
	{
		if (gameOver)
			return;

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		play();

		batch.begin();
		for(Missile missile : missiles)
			batch.draw(missile.getKeyFrame(stateTime), missile.getCoordX(), missile.getCoordY());
		batch.draw(moustache.getKeyFrame(stateTime), moustache.getCoordX(), 0);
		for (Tache tache : taches)
			batch.draw(tache.getKeyFrame(stateTime), tache.getCoordX(),
					tache.getCoordY());
		if (stateTime % 100 == 0)
			taches.add(createTache());
		batch.end();

		removeOutTaches();
		removeOutMissiles();
		checkCollisions();

		stateTime++;
		if (stateTime % (50 / difficulty) == 0 && difficulty <= 10)
			difficulty += 0.1;
	}

	private void removeOutTaches()
	{
		LinkedList<Tache> toRemove = new LinkedList<Tache>();
		for (Tache tache : taches)
			if (tache.isOut())
				toRemove.add(tache);
		taches.removeAll(toRemove);
	}

	private void removeOutMissiles()
	{
		LinkedList<Missile> toRemove = new LinkedList<Missile>();
		for (Missile missile : missiles)
			if (missile.isOut())
				toRemove.add(missile);
		missiles.removeAll(toRemove);
	}

	private void checkCollisions()
	{
		LinkedList<Missile> toRemove = new LinkedList<>();
		for (Tache tache : taches)
		{
			if (tache.getCoordY() < moustache.getHeight()
					&& tache.getCoordX() < moustache.getRightCorner()
					&& tache.getRightCorner() > moustache.getCoordX())
				gameOver();
		}
		Gdx.gl.glClearColor(1, 1, 1, 1);
	}

	private void gameOver()
	{
		if (gameOver)
		{
			drawGameOver();
			return;
		}
		int nbFrames = 1;
		Sprite[] moustacheFrames = new Sprite[nbFrames];
		int width = 400;
		int height = 200;
		for (int i = 0; i < nbFrames; i++)
			moustacheFrames[i] = new Sprite(
					new Texture("assets/game_over.png"), i * width, 0, width,
					height);
		moustache = new Moustache(2.0F, moustacheFrames);
		moustache.setPlayMode(PlayMode.LOOP);
		gameOver = true;

	}

	private void drawGameOver()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(moustache.getKeyFrame(stateTime), moustache.getCoordX(), 0);
		batch.end();
	}

	private void play()
	{
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			moustache.moveLeft();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			moustache.moveRight();
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) &&
				lastMissile < System.currentTimeMillis())
		{
			createMissile();
			lastMissile = System.currentTimeMillis() + 200;
		}
		for (Tache tache : taches)
			tache.move();
		for (Missile missile : missiles)
			missile.move();
	}

	private Tache createTache()
	{
		int nbFrames = 4;
		Sprite[] tacheFrames = new Sprite[nbFrames];
		int width = 64;
		int height = 64;
		for (int i = 0; i < nbFrames; i++)
			tacheFrames[i] = new Sprite(new Texture("assets/tache_4.png"), i
					* width, 0, width, height);
		Tache tache = new Tache(8.0F, tacheFrames, moustache);
		return tache;
	}
	
	private void createMissile()
	{
		int nbFrames = 4;
		Sprite[] missileFrames = new Sprite[nbFrames];
		int width = 64;
		int height = 64;
		for (int i = 0; i < nbFrames; i++)
			missileFrames[i] = new Sprite(new Texture("assets/tache_4.png"), i
					* width, 0, width, height);
		missiles.add(new Missile(moustache.getCoordX(), missileFrames));
	}

	public static float getDifficulty()
	{
		return difficulty;
	}
}
