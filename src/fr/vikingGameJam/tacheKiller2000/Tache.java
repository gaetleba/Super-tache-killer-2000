package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tache extends Animation
{
	private int coordX;
	private int coordY;
	
	private int valueForMove;
	private int valueForMoveActual;
	
	private int iaNumber;
	
	private Moustache moustache;
	
	public Tache(TextureRegion[] keyFrames, Moustache moustache)
	{
		super(250, keyFrames);
		setPlayMode(Animation.PlayMode.LOOP);
		coordX = Gdx.graphics.getHeight();
		coordY = (int)(Math.random() * Gdx.graphics.getWidth());
		valueForMove = (int)(Math.random() * 10);
		valueForMoveActual = 0;
		this.moustache = moustache;
		
		iaNumber = (int)(Math.random() * 3);
		//On corse un peu :)
		if(iaNumber == 2)
			setFrameDuration(125);
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
		coordX -= 10;
		
		switch (iaNumber)
		{
			case 1:
				if(++valueForMoveActual < 0)
					moveLeft();
				else
					moveRight();
				
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
					moveLeft();
				else
					moveRight();
			break;
		}
		
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
