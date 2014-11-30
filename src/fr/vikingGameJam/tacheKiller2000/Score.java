package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Score extends BitmapFont
{
	private long score = 0;
	private boolean[] scoreCouleur;
	private int positionHorizontal = 10;
	private int positionVertical = Game.HEIGHT - 120;
	private boolean goToRigth = true;
	private boolean goToDown = true;
	public Score()
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
	}
	
	public void add(int val)
	{
		score += val;
	}
	
	public void sub(int val)
	{
		score -= val;
	}
	
	public void draw(SpriteBatch batch)
	{
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
			
		draw(batch, "Score : " + score, positionHorizontal , positionVertical); 
	}
}
