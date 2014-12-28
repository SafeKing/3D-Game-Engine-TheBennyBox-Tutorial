package com.ritsu.base.engine.render.lightning;

import com.ritsu.base.engine.components.BaseLight;
import com.ritsu.base.engine.components.DirectionalLight;
import com.ritsu.base.engine.core.math.Matrix4f;
import com.ritsu.base.engine.core.math.Transform;
import com.ritsu.base.engine.render.Material;
import com.ritsu.base.engine.render.lightning.shaders.Shader;

public class ForwardDirectional extends Shader {

	private static final ForwardDirectional instance = new ForwardDirectional();

	public static ForwardDirectional getInstance() {
		return instance;
	}

	private ForwardDirectional() {
		super();

		addVertexShaderFromFile("forward-directional.vs");
		addFragmentShaderFromFile("forward-directional.fs");

		setAttribLocation("position", 0);
		setAttribLocation("texCoord", 1);
		setAttribLocation("normal", 2);

		compileShader();

		addUniform("model");
		addUniform("MVP");

		addUniform("specularIntensity");
		addUniform("specularPower");
		addUniform("eyePos");

		addUniform("directionalLight.base.color");
		addUniform("directionalLight.base.intensity");
		addUniform("directionalLight.direction");
	}

	public void updateUniforms(Transform transform, Material material) {
		Matrix4f worldMatrix = transform.getTransformation();
		Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().mul(worldMatrix);
		material.getTexture().bind();

		setUniform("model", worldMatrix);
		setUniform("MVP", projectedMatrix);

		setUniformf("specularIntensity", material.getSpecularIntensity());
		setUniformf("specularPower", material.getSpecularPower());

		setUniform("eyePos", getRenderingEngine().getMainCamera().getPos());
		setUniformDirectionalLight("directionalLight", (DirectionalLight) getRenderingEngine().getActiveLight());
	}

	public void setUniformBaseLight(String uniformName, BaseLight baseLight) {
		setUniform(uniformName + ".color", baseLight.getColor());
		setUniformf(uniformName + ".intensity", baseLight.getIntensity());
	}

	public void setUniformDirectionalLight(String uniformName, DirectionalLight directionalLight) {
		setUniformBaseLight(uniformName + ".base", (BaseLight) directionalLight);
		setUniform(uniformName + ".direction", directionalLight.getDirection());
	}
}
