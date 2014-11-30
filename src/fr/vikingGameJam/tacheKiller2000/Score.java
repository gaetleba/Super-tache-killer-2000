package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Score extends BitmapFont
{
	private long score = 0;
	
	private int translate = 0;
	private boolean[] scoreCouleur;
	private int positionHorizontal = 10;
	private float positionSize = 0.1F;
	private int positionVertical = Game.HEIGHT - 120;
	private boolean goToRigth = true;
	private boolean goToDown = true;
	private boolean goToSize = true;
	private MessageScore message;
	
	
	public Score(MessageScore message)
	{
		super();
		setColor((int)(Math.random() * 255),
				(int)(Math.random() * 255),
				(int)(Math.random() * 255), 255);
		setScale(3.0F);
		scoreCouleur = new boolean[3];
		scoreCouleur[0] = false;
		scoreCouleur[1] = true;
		scoreCouleur[2] = false;
		this.message = message;
	}
	
	public void add(int val)
	{
		score += val;
	}
	
	public void sub(int val)
	{
		score -= val;
	}
	
	public long getValue()
	{
		return score;
	}
	
	public void draw(SpriteBatch batch)
	{
		if(++translate >= 100 && (int)(Math.random() * 5) == 0)
		{
			positionVertical = 120 + (int)(Math.random() * Game.HEIGHT - 140);
			positionHorizontal = 10 + (int)(Math.random() * Game.WIDTH - 190);
			translate = 0;
		}

		
		if(goToSize)
		{
			positionSize += 0.1;
			if(positionSize >= 1.5)
				goToSize = false;
		}
		else
		{
			positionSize -= 0.1;
			if(positionSize <= 0.2)
				goToSize = true;
		}
		setScale(3.0F + positionSize);
		
		setColor((int)(Math.random() * 255),
				(int)(Math.random() * 255),
				(int)(Math.random() * 255), 255);
		
		if(goToDown)
		{
			positionVertical += 5;
			if(positionVertical >= Game.HEIGHT - 20)
				goToDown = false;
		}
		else
		{
			positionVertical -= 5;
			if(positionVertical <= 120)
				goToDown = true;
		}
		
		if(goToRigth)
		{
			positionHorizontal += 5;
			if(positionHorizontal >= Game.WIDTH - 180)
				goToRigth = false;
		}
		else
		{
			positionHorizontal -= 5;
			if(positionHorizontal <= 10)
				goToRigth = true;
		}
		
		message.setMessageActuel(score);
		draw(batch, "Score : " + score, positionHorizontal , positionVertical); 
	}
}
