package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Moustache extends Animation
{
	private int coordX;
	private final int WIDTH = 128;
	private static Moustache instance;

	public static Moustache getMoustache()
	{
		if (instance == null)
		{
			Texture moustacheTexture = new Texture("assets/moustache.png");
			int nbFrames = 16;
			Sprite[] moustacheFrames = new Sprite[nbFrames];
			int width = 128;
			int height = 64;
			for (int i = 0; i < nbFrames; i++)
				moustacheFrames[i] = new Sprite(moustacheTexture, i * width, 0,
						width, height);
			instance = new Moustache(2.0F, moustacheFrames);
			instance.setPlayMode(PlayMode.LOOP);
		}
		return instance;
	}

	private Moustache(float frameDuration, TextureRegion[] keyFrames)
	{
		super(frameDuration, keyFrames);
		coordX = 0;
		setPlayMode(Animation.PlayMode.LOOP_RANDOM);
	}

	public int getCoordX()
	{
		return coordX;
	}

	public void setCoordX(int coordX)
	{
		if (-WIDTH / 2 <= coordX && coordX < Game.WIDTH - WIDTH / 2)
			this.coordX = coordX;
	}

	public void moveLeft()
	{
		setCoordX(coordX - 15);
	}

	public void moveRight()
	{
		setCoordX(coordX + 15);
	}

	public int getHeight()
	{
		return 64;
	}

	public int getRightCorner()
	{
		return coordX + WIDTH;
	}

	public int getWidth()
	{
		return WIDTH;
	}
}
