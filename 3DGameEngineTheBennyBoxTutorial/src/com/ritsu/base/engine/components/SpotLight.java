package com.ritsu.base.engine.components;

import com.ritsu.base.engine.core.math.Vector3f;
import com.ritsu.base.engine.render.lightning.ForwardSpot;

public class SpotLight extends PointLight {

	private Vector3f direction;
	private float constant, linear, exponent;
	private float cutoff;

	public SpotLight(Vector3f color, float intensity, float constant, float linear, float exponent, Vector3f position, float range, Vector3f direction, float cutoff) {
		super(color, intensity, constant, linear, exponent, position, range);
		this.direction = direction.normalized();
		this.cutoff = cutoff;

		setShader(ForwardSpot.getInstance());
	}

	public Vector3f getDirection() {
		return direction;
	}

	public void setDirection(Vector3f direction) {
		this.direction = direction.normalized();
	}

	public float getCutoff() {
		return cutoff;
	}

	public void setCutoff(float cutoff) {
		this.cutoff = cutoff;
	}

}
