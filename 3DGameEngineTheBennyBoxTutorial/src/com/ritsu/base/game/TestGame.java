package com.ritsu.base.game;

import com.ritsu.base.engine.core.Game;
import com.ritsu.base.engine.core.GameObject;
import com.ritsu.base.engine.core.math.Vector2f;
import com.ritsu.base.engine.core.math.Vector3f;
import com.ritsu.base.engine.render.Material;
import com.ritsu.base.engine.render.Mesh;
import com.ritsu.base.engine.render.Texture;
import com.ritsu.base.engine.render.Vertex;

public class TestGame extends Game {

	public void init() {

		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;

		Vertex[] vertices = new Vertex[] { new Vertex(new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)), new Vertex(new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)), new Vertex(new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)), new Vertex(new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f)) };

		int indices[] = { 0, 1, 2, 2, 1, 3 };

		Mesh mesh = new Mesh(vertices, indices, true);
		Material material = new Material(new Texture("test.png"), new Vector3f(1, 1, 1), 1, 8);

		MeshRenderer meshRenderer = new MeshRenderer(mesh, material);

		GameObject planeObject = new GameObject();
		planeObject.addComponent(meshRenderer);
		planeObject.getTransform().setPos(0, -1, 5);

		getRootObject().addChild(planeObject);

	}
}
