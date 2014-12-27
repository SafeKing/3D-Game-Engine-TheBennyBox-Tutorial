package com.ritsu.base.engine.core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.*;

import java.util.ArrayList;

import com.ritsu.base.engine.Components.DirectionalLight;
import com.ritsu.base.engine.Components.PointLight;
import com.ritsu.base.engine.core.math.Vector3f;
import com.ritsu.base.engine.render.Camera;
import com.ritsu.base.engine.render.lightning.ForwardAmbient;
import com.ritsu.base.engine.render.lightning.ForwardDirectional;
import com.ritsu.base.engine.render.lightning.ForwardPoint;
import com.ritsu.base.engine.render.lightning.ForwardSpot;
import com.ritsu.base.engine.render.lightning.SpotLight;
import com.ritsu.base.engine.render.lightning.shaders.Shader;
import com.ritsu.base.engine.render.window.Window;

public class RenderingEngine {

	private Camera mainCamera;
	private Vector3f ambientLight;
	private DirectionalLight activeDirectionalLight;
	private PointLight activePointLight;
	private SpotLight spotLight;

	private ArrayList<DirectionalLight> directionalLights;
	private ArrayList<PointLight> pointLights;

	public RenderingEngine() {

		directionalLights = new ArrayList<DirectionalLight>();
		pointLights = new ArrayList<PointLight>();
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);

		glEnable(GL_DEPTH_CLAMP);

		glEnable(GL_TEXTURE_2D);

		mainCamera = new Camera((float) Math.toRadians(70.0f), (float) Window.getWidth() / (float) Window.getHeight(), 0.01f, 1000.0f);

		ambientLight = new Vector3f(0.1f, 0.1f, 0.1f);
		// activeDirectionalLight = new DirectionalLight(new BaseLight(new Vector3f(0, 0, 1), 0.4f), new Vector3f(1, 1, 1));
		// directionalLight2 = new DirectionalLight(new BaseLight(new Vector3f(1, 0, 0), 0.4f), new Vector3f(-1, 1, -1));
		//
		// activePointLight = new PointLight(new BaseLight(new Vector3f(0, 1, 0), 4f), new Attenuation(0, 0, 1), new Vector3f(5, 0, 5), 100);
		//
		// spotLight = new SpotLight(new PointLight(new BaseLight(new Vector3f(0, 1, 1), 0.4f), new Attenuation(0, 0, 0.1f), new Vector3f(0, 0, 0), 100), new Vector3f(1, 0, 0), 0.7f);
	}

	public Vector3f getAmbientLight() {
		return ambientLight;
	}

	public DirectionalLight getDirectionalLight() {
		return activeDirectionalLight;
	}

	public PointLight getPointLight() {
		return activePointLight;
	}

	public SpotLight getSpotLight() {
		return spotLight;
	}

	public void input(float delta) {
		mainCamera.input(delta);
	}

	public void render(GameObject object) {
		clearScreen();

		clearLightList();
		object.addToRenderingEngine(this);

		Shader forwardAmbient = ForwardAmbient.getInstance();
		Shader forwardPoint = ForwardPoint.getInstance();
		Shader forwardSpot = ForwardSpot.getInstance();
		Shader forwardDirectional = ForwardDirectional.getInstance();
		forwardAmbient.setRenderingEngine(this);
		forwardDirectional.setRenderingEngine(this);
		forwardPoint.setRenderingEngine(this);
		forwardSpot.setRenderingEngine(this);

		object.render(forwardAmbient);

		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE);
		glDepthMask(false);
		glDepthFunc(GL_EQUAL);

		for (DirectionalLight light : directionalLights) {
			activeDirectionalLight = light;
			object.render(forwardDirectional);
		}
		for (PointLight light : pointLights) {
			activePointLight = light;
			object.render(forwardPoint);
		}

		glDepthFunc(GL_LESS);
		glDepthMask(true);
		glDisable(GL_BLEND);
	}

	private void clearLightList() {
		directionalLights.clear();
		pointLights.clear();
	}

	private static void clearScreen() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	private static void setTextures(boolean enabled) {
		if (enabled)
			glEnable(GL_TEXTURE_2D);
		else
			glDisable(GL_TEXTURE_2D);
	}

	private static void unbindTextures() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	private static void setClearColor(Vector3f color) {
		glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
	}

	public static String getOpenGLVersion() {
		return glGetString(GL_VERSION);
	}

	public void addDirectionalLight(DirectionalLight directionalLight) {
		directionalLights.add(directionalLight);
	}

	public void addPointLight(PointLight pointLight) {
		pointLights.add(pointLight);
	}

	public Camera getMainCamera() {
		return mainCamera;
	}

	public void setMainCamera(Camera mainCamera) {
		this.mainCamera = mainCamera;
	}
}