package com.ritsu.base.engine.components;

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

	public void render(Shader shader) {

		shader.bind();
		shader.updateUniforms(getTransform(), material);
		mesh.draw();
	}

}
