package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Missile extends Animation implements Outable
{
	private int coordX;
	private int coordY;
	public static int SIZE = 64;
	
	private int compteurVitesse;
	private int vitesse;
	
	public static Missile getMissile(Moustache moustache)
	{
		int nbFrames = 1;
		Sprite[] tacheFrames = new Sprite[nbFrames];
		int width = 64;
		int height = 64;
		for (int i = 0; i < nbFrames; i++)
			tacheFrames[i] = new Sprite(new Texture("assets/mousse.png"), i
					* width, 0, width, height);
		return new Missile(moustache.getCoordX(), tacheFrames);
	}
	
	private Missile(int coordX,TextureRegion[] keyFrames)
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
