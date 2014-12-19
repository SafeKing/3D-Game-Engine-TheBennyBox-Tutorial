package com.ritsu.base.engine.core;

import com.ritsu.base.engine.core.math.Transform;

public interface GameComponent {

	public void input(Transform transform);

	public void update(Transform transform);

	public void render(Transform transform);
}
