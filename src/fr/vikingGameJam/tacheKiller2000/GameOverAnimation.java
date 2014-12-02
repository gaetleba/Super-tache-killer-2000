package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameOverAnimation extends Animation
{
	private static GameOverAnimation instance = null;
	public static final int WIDTH = 512;
	public static final int HEIGHT = 128;

	public static GameOverAnimation getInstance()
	{
		if (instance == null)
		{
			int nbFrames = 2;
			Sprite[] frames = new Sprite[nbFrames];
			for (int i = 0; i < nbFrames; i++)
				frames[i] = new Sprite(
						new Texture(Gdx.files.internal("img/game_over.png")), i * WIDTH, 0, WIDTH,
						HEIGHT);
			instance = new GameOverAnimation(2.0F, frames);
			instance.setPlayMode(PlayMode.LOOP_RANDOM);
		}
		return instance;
	}
	
	private GameOverAnimation(float frameDuration, TextureRegion[] keyFrames)
	{
		super(frameDuration, keyFrames);
	}
}
