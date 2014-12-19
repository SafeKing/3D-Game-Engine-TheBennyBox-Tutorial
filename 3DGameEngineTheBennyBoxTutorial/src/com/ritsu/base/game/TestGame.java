package com.ritsu.base.game;

import com.ritsu.base.engine.core.Game;
import com.ritsu.base.engine.core.GameObject;
import com.ritsu.base.engine.core.math.Transform;
import com.ritsu.base.engine.core.math.Vector2f;
import com.ritsu.base.engine.core.math.Vector3f;
import com.ritsu.base.engine.render.Camera;
import com.ritsu.base.engine.render.Material;
import com.ritsu.base.engine.render.Mesh;
import com.ritsu.base.engine.render.Texture;
import com.ritsu.base.engine.render.Vertex;
import com.ritsu.base.engine.render.window.Window;

public class TestGame implements Game {

	private Camera camera;

	private GameObject root;

	public void init() {
		root = new GameObject();
		camera = new Camera();

		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;

		Vertex[] vertices = new Vertex[] { new Vertex(new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)), new Vertex(new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)), new Vertex(new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)), new Vertex(new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f)) };

		int indices[] = { 0, 1, 2, 2, 1, 3 };

		Mesh mesh = new Mesh(vertices, indices, true);
		Material material = new Material(new Texture("test.png"), new Vector3f(1, 1, 1), 1, 8);

		MeshRenderer meshRenderer = new MeshRenderer(mesh, material);
		root.addComponent(meshRenderer);

		Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
		Transform.setCamera(camera);
	}

	public void input() {
		camera.input();
		root.input();
	}

	public void update() {
		root.getTransform().setTranslation(0, -1, 5);
		root.update();
	}

	public void render() {
		root.render();

	}
}
