package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Missile extends Animation
{
	private int coordX;
	private int coordY;
	private static int SIZE = 64;
	
	private int compteurVitesse;
	private int vitesse;
	
	public Missile(int coordX,TextureRegion[] keyFrames)
	{
		super(8.0F, keyFrames);
		setPlayMode(PlayMode.NORMAL);
		this.coordY = 32;
		this.coordX = coordX + 32;
		vitesse = 1;
		compteurVitesse = 0;
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
		coordY += vitesse;
		if(compteurVitesse++ % vitesse == 0)
			vitesse++;
	}

	public boolean isOut()
	{
		return coordY < - SIZE;
	}
}
