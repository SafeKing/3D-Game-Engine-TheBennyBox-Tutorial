package com.ritsu.base.engine.components;

import com.ritsu.base.engine.core.math.Vector3f;
import com.ritsu.base.engine.render.lightning.ForwardSpot;

public class SpotLight extends PointLight {

	private float cutoff;

	public SpotLight(Vector3f color, float intensity, Vector3f attenuation, float cutoff) {
		super(color, intensity, attenuation);
		this.cutoff = cutoff;

		setShader(ForwardSpot.getInstance());
	}

	public Vector3f getDirection() {
		return getTransform().getRot().getForward();
	}

	public float getCutoff() {
		return cutoff;
	}

	public void setCutoff(float cutoff) {
		this.cutoff = cutoff;
	}

}
