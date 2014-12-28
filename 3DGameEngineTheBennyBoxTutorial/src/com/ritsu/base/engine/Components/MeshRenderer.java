package com.ritsu.base.engine.components;

import com.ritsu.base.engine.core.math.Transform;
import com.ritsu.base.engine.render.Material;
import com.ritsu.base.engine.render.Mesh;
import com.ritsu.base.engine.render.lightning.shaders.Shader;

public class MeshRenderer extends GameComponent {

	private Mesh mesh;
	private Material material;

	public MeshRenderer(Mesh mesh, Material material) {
		this.mesh = mesh;
		this.material = material;
	}

	public void input(Transform transform, float delta) {

	}

	public void update(Transform transform, float delta) {

	}

	public void render(Transform transform, Shader shader) {

		shader.bind();
		shader.updateUniforms(transform, material);
		mesh.draw();
	}

}
