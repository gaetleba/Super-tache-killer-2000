package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Moustache extends Animation
{
	private int coordX;
	private final int WIDTH = 128;
	
	public Moustache(float frameDuration, TextureRegion[] keyFrames)
	{
		super(frameDuration, keyFrames);
		coordX = 0;
		setPlayMode(Animation.PlayMode.LOOP_RANDOM);
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX)
	{
		if(-WIDTH/2 <= coordX && coordX < Game.WIDTH - WIDTH/2)
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
}
