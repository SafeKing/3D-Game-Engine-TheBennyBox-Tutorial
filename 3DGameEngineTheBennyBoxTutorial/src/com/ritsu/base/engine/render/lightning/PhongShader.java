package com.ritsu.base.engine.render.lightning;

import com.ritsu.base.engine.render.Material;
import com.ritsu.base.engine.render.RenderUtil;
import com.ritsu.base.engine.render.shaders.ResourceLoader;
import com.ritsu.base.engine.render.shaders.Shader;
import com.ritsu.base.engine.resources.math.Matrix4f;
import com.ritsu.base.engine.resources.math.Vector3f;

public class PhongShader extends Shader {

	private static final PhongShader instance = new PhongShader();

	public static PhongShader getInstance() {
		return instance;
	}

	private static Vector3f ambientLight;

	private PhongShader() {
		super();

		addVertexShader(ResourceLoader.loadShader("phongVertex.vs"));
		addFragmentShader(ResourceLoader.loadShader("phongFragment.fs"));
		compileShader();

		addUniform("transform");
		addUniform("baseColor");
		addUniform("ambientLight");
	}

	public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
		if (material.getTexture() != null)
			material.getTexture().bind();
		else
			RenderUtil.unbindTextures();

		setUniform("transform", projectedMatrix);
		setUniform("baseColor", material.getColor());
		setUniform("ambientLight", ambientLight);
	}

	public static Vector3f getAmbientLight() {
		return ambientLight;
	}

	public static void setAmbientLight(Vector3f ambientLight) {
		PhongShader.ambientLight = ambientLight;
	}
}
