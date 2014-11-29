package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tache extends Animation
{
	private int coordX;
	private int coordY;
	
	private int size = 64;
	
	private int valueForMove;
	private int valueForMoveActual;
	
	private int iaNumber;
	
	public static int frameNumber = 250;
	public static int frameNumberCpt = 0;
	private Moustache moustache;
	
	public Tache(float frameDuration, TextureRegion[] keyFrames, Moustache moustache)
	{
		super(250, keyFrames);
		
		if(++frameNumberCpt == 10)
		{
			if(frameNumber > 2)
				frameNumber--;
			frameNumberCpt = 0;
		}
		
		setPlayMode(Animation.PlayMode.LOOP);
		coordY = Game.HEIGHT - size;
		coordX = (int)(Math.random() * Game.WIDTH);
		valueForMove = (int)(Math.random() * 10) + 10;
		valueForMoveActual = 0;
		this.moustache = moustache;
		
		iaNumber = (int)(Math.random() * 3);
		//On corse un peu :)
		if(iaNumber == 2)
			setFrameDuration(250);
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

	/**
	 * Permet de faire descendre la tache
	 * @param ia Entre 0 et 1
	 * 0 : Random
	 * 1 : Descend droite x fois et gauche x fois
	 * 2 : Se dirigera vers la moustache
	 */
	public void move()
	{
		coordY -= 10;
		
		switch (iaNumber)
		{
			case 1:
				if(++valueForMoveActual < 0)
				{
					if( ! moveLeft())
						valueForMoveActual = 0;
				}
				else
				{
					if( ! moveRight())
						valueForMoveActual = -valueForMove;
				}
				
				if(valueForMoveActual >= valueForMove)
					valueForMoveActual = -valueForMove;
			break;
			
			case 2:
				if(moustache.getCoordY() < getCoordY())
					moveLeft();
				else
					moveRight();
			break;
	
			default:
				if((int)(Math.random() * 2) == 0)
				{
					if( ! moveLeft())
						moveRight();
				}
				else
					if( ! moveRight())
					{
						moveLeft();
					}
			break;
		}
		
	}
	
	public boolean moveLeft()
	{
		if(0 < coordX - 10)
		{
			coordX -= 10;
			return true;
		}
		return false;
	}
	
	public boolean moveRight()
	{
		if(Game.WIDTH - size > coordX + 10)
		{
			coordX += 10;
			return true;
		}
		return false;
	}
}
