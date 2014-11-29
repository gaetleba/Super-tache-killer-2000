package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Moustache extends Animation
{
	private int coordY;
	private final int WIDTH = 174;
	
	public Moustache(float frameDuration, TextureRegion[] keyFrames)
	{
		super(frameDuration, keyFrames);
		coordY = 0;
		setPlayMode(Animation.PlayMode.LOOP);
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY)
	{
		if(-WIDTH/2 <= coordY && coordY < Game.WIDTH - WIDTH/2)
			this.coordY = coordY;
	}
	
	public void moveLeft()
	{
		setCoordY(getCoordY() - 10);
	}
	
	public void moveRight()
	{
		setCoordY(getCoordY() + 10);
	}

}
