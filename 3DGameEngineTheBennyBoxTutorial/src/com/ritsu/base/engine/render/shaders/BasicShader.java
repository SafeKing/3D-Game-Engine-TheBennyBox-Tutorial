package com.ritsu.base.engine.render.shaders;

import com.ritsu.base.engine.core.math.Matrix4f;
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

	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
		material.getTexture().bind();

		setUniform("transform", projectedMatrix);
		setUniform("color", material.getColor());
	}
}
