package fr.vikingGameJam.tacheKiller2000;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MessageScore
{
	private HashMap<Integer, String> message;
	private int messageActuel;
	private int count;
	
	private BitmapFont font;
	
	public MessageScore(Game game)
	{
		count = 0;
		font = new BitmapFont();
		font.scale(5.0F);
		messageActuel = 0;
		this.message = new HashMap<Integer, String>();

		this.message.put(3000, "Félicitations !");
		this.message.put(2000, "GG");
		this.message.put(1500, "No comments ...");
		this.message.put(1250, "Fffuuu ! Vantard ...");
		this.message.put(1000, "Non mais sérieux ?!");
		this.message.put(750, "Arrête !");
		this.message.put(500, "Tu vas me battre !");
		this.message.put(500, "Tu te prends pour qui ?");
		this.message.put(300, "Tu te la pète ?");
		this.message.put(200, "Cool :)");
		this.message.put(150, "Mouais ...");
		this.message.put(100, "Pas mal ...");
		this.message.put(50, "Bon début ;)");
		this.message.put(-50, "Tu chutes là !!!");
		this.message.put(-100, "Tu te chies dessus !");
		this.message.put(-150, "Ça craint non ?");
		this.message.put(-200, "Sérieux ?");
		this.message.put(-300, "Tu peux arrêter ...");
		this.message.put(-500, "Honte à toi !");
		this.message.put(-750, "Arrête le massacre !");
		this.message.put(-1000, "Je me casse !");
	}
	
	public void setMessageActuel(long score)
	{
		if(messageActuel == 0 && this.message.containsKey(new Integer((int) score)))
			messageActuel = (int)score;
		System.out.println(messageActuel);
		System.out.println(score);
		System.out.println((int)score);
	}
	
	public void draw(SpriteBatch batch)
	{
		if(messageActuel != 0 && this.message.containsKey(messageActuel))
		{
			font.setColor((int)(Math.random() * 255),
					(int)(Math.random() * 255),
					(int)(Math.random() * 255), 255);
			font.drawWrapped(batch, this.message.get(messageActuel) , 20, Game.HEIGHT / 2, Game.WIDTH - 40 ); 
			
			if(++count == 150)
			{
				messageActuel = 0;
				count = 0;
			}
		}
	}
}
