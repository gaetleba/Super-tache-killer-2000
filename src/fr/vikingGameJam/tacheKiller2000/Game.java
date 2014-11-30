package fr.vikingGameJam.tacheKiller2000;

import java.util.LinkedList;

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
	private float stateTime;
	private Moustache moustache;
	private LinkedList<Tache> taches;
	private boolean gameOver;

	@Override
	public void create()
	{
		batch = new SpriteBatch();
		stateTime = 0;
		taches = new LinkedList<Tache>();
		difficulty = 1;
		gameOver = false;

		moustache = Moustache.getMoustache();
	}

	@Override
	public void render()
	{
		stateTime++;
		
		if (gameOver)
		{
			drawGameOver();
			return;
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		play();

		batch.begin();
		batch.draw(moustache.getKeyFrame(stateTime), moustache.getCoordX(), 0);
		for (Tache tache : taches)
			batch.draw(tache.getKeyFrame(stateTime), tache.getCoordX(),
					tache.getCoordY());
		if (stateTime % 100 == 0)
			taches.add(Tache.getTache(moustache));
		batch.end();

		removeOutTaches();
		checkCollisions();

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

	private void checkCollisions()
	{
		for (Tache tache : taches)
			if (tache.getCoordY() < moustache.getHeight()
					&& tache.getCoordX() < moustache.getRightCorner()
					&& tache.getRightCorner() > moustache.getCoordX())
				gameOver();
		Gdx.gl.glClearColor(1, 1, 1, 1);
	}

	private void gameOver()
	{
		if (gameOver)
			return;
		int nbFrames = 2;
		Sprite[] moustacheFrames = new Sprite[nbFrames];
		int width = 128;
		int height = 64;
		for (int i = 0; i < nbFrames; i++)
			moustacheFrames[i] = new Sprite(
					new Texture("assets/game_over.png"), i * width, 0, width,
					height);
		moustache = new Moustache(2.0F, moustacheFrames);
		moustache.setPlayMode(PlayMode.LOOP_RANDOM);
		gameOver = true;

	}

	private void drawGameOver()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(moustache.getKeyFrame(stateTime), (WIDTH-128)/2, (HEIGHT-64)/2);
		batch.end();
	}

	private void play()
	{
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			moustache.moveLeft();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			moustache.moveRight();
		for (Tache tache : taches)
			tache.move();
	}

	public static float getDifficulty()
	{
		return difficulty;
	}
}
