package com.ritsu.base.engine.components;

import com.ritsu.base.engine.core.math.Vector3f;
import com.ritsu.base.engine.render.lightning.ForwardDirectional;

public class DirectionalLight extends BaseLight {

	private BaseLight base;
	private Vector3f direction;

	public DirectionalLight(Vector3f color, float intensity, Vector3f direction) {
		super(color, intensity);
		this.direction = direction.normalized();

		setShader(ForwardDirectional.getInstance());
	}

	

	public BaseLight getBase() {
		return base;
	}

	public void setBase(BaseLight base) {
		this.base = base;
	}

	public Vector3f getDirection() {
		return direction;
	}

	public void setDirection(Vector3f direction) {
		this.direction = direction.normalized();
	}

}
