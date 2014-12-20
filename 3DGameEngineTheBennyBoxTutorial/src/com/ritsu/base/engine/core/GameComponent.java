package com.ritsu.base.engine.core;

import com.ritsu.base.engine.core.math.Transform;
import com.ritsu.base.engine.render.shaders.Shader;

public interface GameComponent {

	public void input(Transform transform, float delta);

	public void update(Transform transform, float delta);

	public void render(Transform transform, Shader shader);
}
