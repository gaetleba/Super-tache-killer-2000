package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TacheDeathAnimation extends Animation
{
	private int coordX;
	private int coordY;

	public static int SIZE = 128;

	private int stateTime;

	public static int frameNumber = 4;
	public static int frameNumberCpt = 0;

	private TacheDeathAnimation(TextureRegion[] keyFrames, int x, int y)
	{
		super(4.0F, keyFrames);

		setPlayMode(Animation.PlayMode.NORMAL);
		coordY = y;
		coordX = x;
		stateTime = 0;
	}

	public static TacheDeathAnimation getTacheDeathAnimation(int x, int y,
			int iaNumber)
	{
		int nbFrames = 4;
		Sprite[] tacheFrames = new Sprite[nbFrames];
		int width = SIZE;
		int height = SIZE;
		Texture texture = new Texture(Gdx.files.internal("img/tache_mort_"
				+ iaNumber + ".png"));
		for (int i = 0; i < nbFrames; i++)
		{
			tacheFrames[i] = new Sprite(texture, i * width, 0, width, height);
		}
		return new TacheDeathAnimation(tacheFrames, x - 32, y - 32);

	}

	public int getStateTime()
	{
		return stateTime;
	}

	public TextureRegion getKeyFrame()
	{
		stateTime++;
		return super.getKeyFrame(stateTime);
	}

	public int getCoorX()
	{
		return coordX;
	}

	public int getCoorY()
	{
		return coordY;
	}

	public int getCenterX()
	{
		return coordX + SIZE / 2;
	}

	public int getRightCorner()
	{
		return coordX + SIZE;
	}
}
