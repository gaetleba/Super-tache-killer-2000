package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter
{
	SpriteBatch batch;
	Texture moustacheTexture;
	private float stateTime;

	@Override
	public void create()
	{
		batch = new SpriteBatch();
		moustacheTexture = new Texture("assets/moustache.png");
		stateTime = 0;

		{
			Sprite[] moustacheFrames = new Sprite[6];
			int width = 1024;
			int height = 64;
			int nbFrames = 6;
			for (int i = 0; i < nbFrames; i++)
				moustacheFrames[i] = new Sprite(moustacheTexture, i * width/nbFrames, 0,
						width/nbFrames, height);
			moustache = new Animation(0.2F, moustacheFrames);
			moustache.setPlayMode(Animation.PlayMode.LOOP);
		}
	}

	private Animation moustache;

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(moustache.getKeyFrame(stateTime/100), 0, 64);
		batch.end();
		stateTime ++;
	}
}
