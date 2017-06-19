package com.mygdx.gamethirteen.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.gamethirteen.GameThirteenMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GameThirteenMain(), config);
		config.width = 1366;
		config.height = 768;
		//config.samples = 2;  // antialiasing

	}
}
