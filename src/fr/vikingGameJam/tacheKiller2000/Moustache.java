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
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY)
	{
		if(-1024*0.1F <= coordY && coordY < Gdx.graphics.getWidth() + 1024*0.1F)
			this.coordY = coordY;
	}

}
