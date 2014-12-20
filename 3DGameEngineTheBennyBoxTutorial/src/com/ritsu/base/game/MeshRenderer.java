package com.ritsu.base.game;

import com.ritsu.base.engine.core.GameComponent;
import com.ritsu.base.engine.core.math.Transform;
import com.ritsu.base.engine.render.Material;
import com.ritsu.base.engine.render.Mesh;
import com.ritsu.base.engine.render.shaders.Shader;

public class MeshRenderer implements GameComponent {

	private Mesh mesh;
	private Material material;

	public MeshRenderer(Mesh mesh, Material material) {
		this.mesh = mesh;
		this.material = material;
	}

	public void input(Transform transform) {

	}

	public void update(Transform transform) {

	}

	public void render(Transform transform, Shader shader) {

		shader.bind();
		shader.updateUniforms(transform, material);
		mesh.draw();
	}

}
