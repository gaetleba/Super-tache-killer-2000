package fr.vikingGameJam.tacheKiller2000;

import java.util.LinkedList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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
	private LinkedList<Missile> missiles;
	
	private long lastMissile;
	private GameOverAnimation gameOver = null;

	@Override
	public void create()
	{
		batch = new SpriteBatch();
		stateTime = 0;
		taches = new LinkedList<Tache>();
		missiles = new LinkedList<Missile>();
		difficulty = 1;
		lastMissile = System.currentTimeMillis();

		moustache = Moustache.getMoustache();
	}

	@Override
	public void render()
	{
		stateTime++;
		
		if (gameOver != null)
		{
			drawGameOver();
			return;
		}
		
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
			taches.add(Tache.getTache(moustache));
		batch.end();

		removeOutTaches();
		removeOutMissiles();
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
		LinkedList<Missile> toRemoveMissile = new LinkedList<Missile>();
		LinkedList<Tache> toRemoveTache = new LinkedList<Tache>();
		for (Tache tache : taches)
		{
			if (tache.getCoordY() < moustache.getHeight()
					&& tache.getCoordX() < moustache.getRightCorner()
					&& tache.getRightCorner() > moustache.getCoordX())
				gameOver();
			
			for(Missile missile : missiles)
			{
				if(missile.getCoordX() < tache.getCoordX() &&
					tache.getCoordX() < (missile.getCoordX() + Missile.SIZE) &&
					missile.getCoordY() < tache.getCoordY() &&
					tache.getCoordY() < missile.getCoordY() + Missile.SIZE)
				{
					toRemoveMissile.add(missile);
					toRemoveTache.add(tache);
				}
			}
		}
		
		taches.removeAll(toRemoveTache);
		missiles.removeAll(toRemoveMissile);
		Gdx.gl.glClearColor(1, 1, 1, 1);
	}

	private void gameOver()
	{
		if (gameOver != null)
			return;
		gameOver = GameOverAnimation.getInstance();

	}

	private void drawGameOver()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(gameOver.getKeyFrame(stateTime), (WIDTH-GameOverAnimation.WIDTH)/2, (HEIGHT-GameOverAnimation.HEIGHT)/2);
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

	private void createMissile()
	{
		int nbFrames = 1;
		Sprite[] missileFrames = new Sprite[nbFrames];
		int width = 64;
		int height = 64;
		for (int i = 0; i < nbFrames; i++)
			missileFrames[i] = new Sprite(new Texture("assets/mousse.png"), i
					* width, 0, width, height);
		missiles.add(Missile.getMissile(moustache));
	}

	public static float getDifficulty()
	{
		return difficulty;
	}
}
