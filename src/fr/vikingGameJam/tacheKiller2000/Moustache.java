package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Moustache extends Animation
{
	private int coordX;
	private final int WIDTH = 174;
	
	public Moustache(float frameDuration, TextureRegion[] keyFrames)
	{
		super(frameDuration, keyFrames);
		coordX = 0;
		setPlayMode(Animation.PlayMode.LOOP_RANDOM);
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordY)
	{
		if(-WIDTH/2 <= coordY && coordY < Game.WIDTH - WIDTH/2)
			this.coordX = coordY;
	}
	
	public void moveLeft()
	{
		setCoordX(coordX - 10 * Game.getDifficulty());
	}
	
	public void moveRight()
	{
		setCoordX(coordX + 10 * Game.getDifficulty());
	}

}
