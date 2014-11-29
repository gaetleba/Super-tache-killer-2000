package fr.vikingGameJam.tacheKiller2000;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Moustache extends Animation
{
	private int coordY;
	
	public Moustache(float frameDuration, TextureRegion[] keyFrames)
	{
		super(frameDuration, keyFrames);
		coordY = 0;
		setPlayMode(Animation.PlayMode.LOOP_RANDOM);
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY)
	{
		if(-1024*0.1F <= coordY && coordY < Gdx.graphics.getWidth() - 1024*0.1F)
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
