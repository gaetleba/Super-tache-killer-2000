package fr.vikingGameJam.tacheKiller2000;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LevelUpAnimation extends Animation
{
	public static final int WIDTH = 512;
	public static final int HEIGHT = 256;
	private int stateTime;

	public static LevelUpAnimation getLevelUp()
	{
		LevelUpAnimation animation;
		int nbFrames = 2;
		Sprite[] frames = new Sprite[nbFrames];
		for (int i = 0; i < nbFrames; i++)
			frames[i] = new Sprite(new Texture(Gdx.files.internal("img/levelup.png")), i
					* WIDTH, 0, WIDTH, HEIGHT);
		animation = new LevelUpAnimation(2.0F, frames);
		animation.setPlayMode(PlayMode.LOOP_RANDOM);
		return animation;
	}

	private LevelUpAnimation(float frameDuration, TextureRegion[] keyFrames)
	{
		super(frameDuration, keyFrames);
		stateTime = 50;
	}

	public TextureRegion getKeyFrame() throws LevelUpAnimationEnded
	{
		stateTime--;
		if (stateTime == 0)
			throw new LevelUpAnimationEnded();
		return super.getKeyFrame(stateTime);
	}
}
