package com.ritsu.base.engine.components;

import com.ritsu.base.engine.core.math.Transform;
import com.ritsu.base.engine.render.RenderingEngine;
import com.ritsu.base.engine.render.lightning.shaders.Shader;

public abstract class GameComponent {

	public void input(Transform transform, float delta) {

	}

	public void update(Transform transform, float delta) {

	}

	public void render(Transform transform, Shader shader) {

	}

	public void addToRenderingEngine(RenderingEngine renderingEngine) {

	}
}
