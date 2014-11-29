package fr.vikingGameJam.tacheKiller2000;

import java.util.LinkedList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter
{
	public static final int WIDTH = 600;
	public static final int HEIGHT = 860;
	
	SpriteBatch batch;
	Texture moustacheTexture;
	private float stateTime;
	private Moustache moustache;
	private LinkedList<Tache> taches;

	@Override
	public void create()
	{
		batch = new SpriteBatch();
		moustacheTexture = new Texture("assets/moustache.png");
		stateTime = 0;
		taches = new LinkedList<Tache>();

		{// Creation moustache
			int nbFrames = 6;
			Sprite[] moustacheFrames = new Sprite[nbFrames];
			int width = 1024;
			int height = 64;
			for (int i = 0; i < nbFrames; i++)
				moustacheFrames[i] = new Sprite(moustacheTexture, i * width
						/ nbFrames, 0, width / nbFrames, height);
			moustache = new Moustache(0.2F, moustacheFrames);
		}
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			moustache.moveLeft();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			moustache.moveRight();
		for (Tache tache : taches)
			tache.move();
		
		
		batch.begin();
		batch.draw(moustache.getKeyFrame(stateTime/20), moustache.getCoordY(), 0);
		for (Tache tache : taches)
			batch.draw(tache.getKeyFrame(stateTime/20), tache.getCoordX(), tache.getCoordY());
		if (stateTime % 10 == 0)
			taches.add(createTache());
		batch.end();
		
		stateTime ++;
	}

	private Tache createTache()
	{
		int nbFrames = 1;
		Sprite[] tacheFrames = new Sprite[nbFrames];
		int width = 64;
		int height = 64;
		for (int i = 0; i < nbFrames; i++)
			tacheFrames[i] = new Sprite(new Texture("assets/tache_0.png"), i * width, 0, width, height);
		Tache tache = new Tache(0.2F, tacheFrames, moustache);
		return tache;
	}
}
