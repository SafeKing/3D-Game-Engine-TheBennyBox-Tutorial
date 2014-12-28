package com.ritsu.base.game;

import com.ritsu.base.engine.components.Camera;
import com.ritsu.base.engine.components.DirectionalLight;
import com.ritsu.base.engine.components.MeshRenderer;
import com.ritsu.base.engine.components.PointLight;
import com.ritsu.base.engine.components.SpotLight;
import com.ritsu.base.engine.core.Game;
import com.ritsu.base.engine.core.GameObject;
import com.ritsu.base.engine.core.math.Quaternion;
import com.ritsu.base.engine.core.math.Vector2f;
import com.ritsu.base.engine.core.math.Vector3f;
import com.ritsu.base.engine.render.Material;
import com.ritsu.base.engine.render.Mesh;
import com.ritsu.base.engine.render.Texture;
import com.ritsu.base.engine.render.Vertex;
import com.ritsu.base.engine.render.window.Window;

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
		planeObject.getTransform().getPos().set(0, -1, 5);

		GameObject directionalLightObject = new GameObject();
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0, 0, 1), 0.4f, new Vector3f(1, 1, 1));
		directionalLightObject.addComponent(directionalLight);

		GameObject pointLightObject = new GameObject();
		directionalLightObject.addComponent(new PointLight(new Vector3f(0, 1, 0), 4f, new Vector3f(0, 0, 1)));

		SpotLight spotLight = new SpotLight(new Vector3f(0, 1, 1), 0.4f, new Vector3f(0, 0, 0.1f), 0.7f);

		GameObject spotLightObject = new GameObject();
		spotLightObject.addComponent(spotLight);

		spotLight.getTransform().getPos().set(5, 0, 5);
		spotLight.getTransform().setRot(new Quaternion().initRotation(new Vector3f(0, 1, 0), (float) Math.toRadians(-90.0f)));

		getRootObject().addChild(planeObject);
		getRootObject().addChild(directionalLightObject);
		getRootObject().addChild(pointLightObject);
		getRootObject().addChild(spotLightObject);

		getRootObject().addChild(new GameObject().addComponent(new Camera((float) Math.toRadians(70.0f), (float) Window.getWidth() / (float) Window.getHeight(), 0.01f, 1000.0f)));

	}
}
