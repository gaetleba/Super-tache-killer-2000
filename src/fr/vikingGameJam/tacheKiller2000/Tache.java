package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tache extends Animation
{
	private int coordX;
	private int coordY;
	
	public Tache(TextureRegion[] keyFrames)
	{
		super(250, keyFrames);
		setPlayMode(Animation.PlayMode.LOOP);
		coordX = Gdx.graphics.getHeight();
		coordY = (int)(Math.random() * Gdx.graphics.getWidth());
	}
	
	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public void move()
	{
		coordX -= 10;
		
		if((int)(Math.random() * 2) == 0)
			moveLeft();
		else
			moveRight();
		
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
