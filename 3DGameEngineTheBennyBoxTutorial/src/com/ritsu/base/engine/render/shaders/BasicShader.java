package com.ritsu.base.engine.render.shaders;

import com.ritsu.base.engine.core.math.Matrix4f;
import com.ritsu.base.engine.core.math.Transform;
import com.ritsu.base.engine.render.Material;

public class BasicShader extends Shader {

	private static final BasicShader instance = new BasicShader();

	public static BasicShader getInstance() {
		return instance;
	}

	private BasicShader() {
		super();

		addVertexShaderFromFile("basicVertex.vs");
		addFragmentShaderFromFile("basicFragment.fs");
		compileShader();

		addUniform("transform");
		addUniform("color");
	}

	public void updateUniforms(Transform transform, Material material) {

		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().mul(worldMatrix);
		material.getTexture().bind();

		setUniform("transform", projectedMatrix);
		setUniform("color", material.getColor());
	}
}
