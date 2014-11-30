package fr.vikingGameJam.tacheKiller2000;

import java.util.LinkedList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter
{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 860;

	private static float difficulty;

	SpriteBatch batch;
	private float stateTime;
	private Moustache moustache;
	private LinkedList<Tache> taches;
	private LinkedList<Missile> missiles;
	
	private long lastMissile;
	private GameOverAnimation gameOver = null;

	private long score;
	
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
		
		score = 0;
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
			batch.draw(missile.getKeyFrame(), missile.getCoordX(), missile.getCoordY());
		batch.draw(moustache.getKeyFrame(stateTime), moustache.getCoordX(), 0);
		for (Tache tache : taches)
			batch.draw(tache.getKeyFrame(stateTime), tache.getCoordX(),
					tache.getCoordY());
		if (stateTime % 100/difficulty == 0)
			taches.add(Tache.getTache(moustache));
		
		new BitmapFont().draw(batch, "Score : " + score, 10, 30);   
		
		batch.end();

		score -= 20 * removeOut(taches);
		score -= 15 * removeOut(missiles);
		checkCollisions();

		if (stateTime % (10 / difficulty) == 0 && difficulty <= 10)
			difficulty += 0.5;
	}

	private int removeOut(LinkedList<? extends Outable> list)
	{
		LinkedList<Outable> toRemove = new LinkedList<Outable>();
		for (Outable outable : list)
			if (outable.isOut())
				toRemove.add(outable);
		list.removeAll(toRemove);
		return toRemove.size();
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
				if(((missile.getCoordX() < tache.getCoordX() &&
					tache.getCoordX() < (missile.getCoordX() + Missile.SIZE)) ||
					(missile.getCoordX() < tache.getCoordX() + Tache.SIZE &&
					tache.getCoordX() + Tache.SIZE < (missile.getCoordX() + Missile.SIZE))) &&
					missile.getCoordY() < tache.getCoordY() &&
					tache.getCoordY() < missile.getCoordY() + Missile.SIZE)
				{
					toRemoveMissile.add(missile);
					toRemoveTache.add(tache);
					score += 10;
				}
			}
		}
		
		taches.removeAll(toRemoveTache);
		missiles.removeAll(toRemoveMissile);
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
			missiles.add(Missile.getMissile(moustache));
			lastMissile = System.currentTimeMillis() + 200;
		}
		for (Tache tache : taches)
			tache.move();
		for (Missile missile : missiles)
			missile.move();
	}

	public static float getDifficulty()
	{
		return difficulty;
	}
}
