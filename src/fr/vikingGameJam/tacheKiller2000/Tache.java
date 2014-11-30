package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tache extends Animation implements Outable
{
	private int coordX;
	private int coordY;

	public static int SIZE = 64;

	private int valueForMove;
	private int valueForMoveActual;

	private int iaNumber;

	public static int frameNumber = 250;
	public static int frameNumberCpt = 0;
	private Moustache moustache;

	public static Tache getTache(Moustache moustache)
	{
		int iaNumber = (int) (Math.random() * 4);
		int nbFrames = 4;
		Sprite[] tacheFrames = new Sprite[nbFrames];
		int width = 64;
		int height = 64;
		for (int i = 0; i < nbFrames; i++)
			tacheFrames[i] = new Sprite(new Texture("assets/tache_" + iaNumber
					+ ".png"), i * width, 0, width, height);
		Tache tache = new Tache(8.0F, tacheFrames, moustache, iaNumber);
		return tache;
	}

	private Tache(float frameDuration, TextureRegion[] keyFrames,
			Moustache moustache, int iaNumber)
	{
		super(frameDuration, keyFrames);

		if (++frameNumberCpt == 10)
		{
			if (frameNumber > 2)
				frameNumber--;
			frameNumberCpt = 0;
		}

		setPlayMode(Animation.PlayMode.LOOP);
		coordY = Game.HEIGHT - SIZE;
		coordX = (int) (Math.random() * Game.WIDTH);
		valueForMove = (int) (Math.random() * 15) + 20;
		valueForMoveActual = 0;
		this.moustache = moustache;
		this.iaNumber = iaNumber;
	}

	public int getCoordX()
	{
		return coordX;
	}

	public void setCoordX(int coordX)
	{
		this.coordX = coordX;
	}

	public int getCoordY()
	{
		return coordY;
	}

	public void setCoordY(int coordY)
	{
		this.coordY = coordY;
	}

	/**
	 * Permet de faire descendre la tache
	 * 
	 * @param ia
	 *            Entre 0 et 1 0 : Random 1 : Descend droite x fois et gauche x
	 *            fois 2 : Se dirigera vers la moustache
	 */
	public void move()
	{
		coordY -= 2 * Game.getDifficulty();

		switch (iaNumber)
		{
		case 1: // blue
			if (++valueForMoveActual < 0)
			{
				if (!moveLeft())
					valueForMoveActual = 0;
			} else
			{
				if (!moveRight())
					valueForMoveActual = -valueForMove;
			}

			if (valueForMoveActual >= valueForMove)
				valueForMoveActual = -valueForMove;
			break;

		case 2: // Rouge, suiveuse
			if (moustache.getCenterX() < getCenterX())
				moveLeft();
			else
				moveRight();
			break;

		case 3: //vert  TP
			if (++valueForMoveActual == 100)
			{
				valueForMoveActual = 0;
				if (coordY > 128)
					coordY += 10 + (int) (Math.random() * 20);

				coordX = (int) (Math.random() * (Game.WIDTH - SIZE));
			}
			if ((int) (Math.random() * 2) == 0)
			{
				if (!moveLeft())
					moveRight();
			} else if (!moveRight())
			{
				moveLeft();
			}
			break;
		default: //jaune
			coordY -= (int)(Math.random() * 10);
			break;
		}

	}

	public int getCenterX()
	{
		return coordX + SIZE / 2;
	}

	public boolean moveLeft()
	{
		if (0 < coordX - 10)
		{
			coordX -= 2 * Game.getDifficulty();
			return true;
		}
		return false;
	}

	public boolean moveRight()
	{
		if (Game.WIDTH - SIZE > coordX + 10)
		{
			coordX += 2 * Game.getDifficulty();
			return true;
		}
		return false;
	}

	public boolean isOut()
	{
		return coordY < -SIZE;
	}

	public int getRightCorner()
	{
		return coordX + SIZE;
	}
}
