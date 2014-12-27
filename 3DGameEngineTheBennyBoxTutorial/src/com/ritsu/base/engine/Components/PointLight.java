package com.ritsu.base.engine.Components;

import com.ritsu.base.engine.core.RenderingEngine;
import com.ritsu.base.engine.core.math.Vector3f;
import com.ritsu.base.engine.render.lightning.Attenuation;
import com.ritsu.base.engine.render.lightning.BaseLight;

public class PointLight extends GameComponent {

	private BaseLight baseLight;
	private Attenuation atten;
	private Vector3f position;
	private float range;

	public PointLight(BaseLight baseLight, Attenuation atten, Vector3f position, float range) {
		this.baseLight = baseLight;
		this.atten = atten;
		this.position = position;
		this.range = range;
	}

	@Override
	public void addToRenderingEngine(RenderingEngine renderingEngine) {
		renderingEngine.addPointLight(this);
	}

	public BaseLight getBaseLight() {
		return baseLight;
	}

	public void setBaseLight(BaseLight baseLight) {
		this.baseLight = baseLight;
	}

	public Attenuation getAtten() {
		return atten;
	}

	public void setAtten(Attenuation atten) {
		this.atten = atten;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

}
