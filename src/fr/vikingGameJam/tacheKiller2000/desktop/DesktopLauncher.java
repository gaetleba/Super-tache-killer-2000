package fr.vikingGameJam.tacheKiller2000.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.vikingGameJam.tacheKiller2000.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.height = 860;
		config.width = 600;
		
		new LwjglApplication(new Game(), config);
	}
}
