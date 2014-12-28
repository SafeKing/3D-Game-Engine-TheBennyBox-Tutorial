package com.ritsu.base.engine.components;

import com.ritsu.base.engine.core.GameObject;
import com.ritsu.base.engine.core.math.Transform;
import com.ritsu.base.engine.render.RenderingEngine;
import com.ritsu.base.engine.render.lightning.shaders.Shader;

public abstract class GameComponent {

	private GameObject parent;

	public void input(float delta) {

	}

	public void update(float delta) {

	}

	public void render(Shader shader) {

	}

	public void setParent(GameObject parent) {
		this.parent = parent;
	}

	public Transform getTransform() {
		return parent.getTransform();
	}

	public void addToRenderingEngine(RenderingEngine renderingEngine) {

	}
}
