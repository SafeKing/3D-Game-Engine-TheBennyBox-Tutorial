package com.ritsu.base.engine.core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.*;

import com.ritsu.base.engine.core.math.Vector3f;
import com.ritsu.base.engine.render.Camera;
import com.ritsu.base.engine.render.shaders.BasicShader;
import com.ritsu.base.engine.render.shaders.Shader;
import com.ritsu.base.engine.render.window.Window;

public class RenderingEngine {

	private Camera mainCamera;

	public RenderingEngine() {

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);

		glEnable(GL_DEPTH_CLAMP);

		glEnable(GL_TEXTURE_2D);

		mainCamera = new Camera((float) Math.toRadians(70.0f), (float) Window.getWidth() / Window.getHeight(), 0.01f, 1000.0f);;
	}
	public void input(){
		mainCamera.input();
	}

	public void render(GameObject object) {

		clearScreen();

		Shader shader = BasicShader.getInstance();
		shader.setRenderingEngine(this);

		object.render(BasicShader.getInstance());
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

	public Camera getMainCamera() {
		return mainCamera;
	}

	public void setMainCamera(Camera mainCamera) {
		this.mainCamera = mainCamera;
	}

}
