package com.readonlydev.space.trappist1.d;

import com.readonlydev.api.world.weather.ExoRainRenderer;
import com.readonlydev.client.Assets;

public class Trappist1DRainRenderer extends ExoRainRenderer {
	
	public Trappist1DRainRenderer() {
		this.RAIN_TEXTURES = Assets.getTexture("heavyrain");
	}

}
