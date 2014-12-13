package com.ritsu.base.engine;

import com.ritsu.base.engine.math.Time;
import com.ritsu.base.engine.math.Transform;
import com.ritsu.base.engine.math.Vector2f;
import com.ritsu.base.engine.math.Vector3f;
import com.ritsu.base.engine.render.Mesh;
import com.ritsu.base.engine.render.Vertex;

public class Game {

	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Texture texture;
	private Camera camera;

	public Game() {
		mesh = new Mesh();// ResourceLoader.loadMesh("box.obj");
		texture = ResourceLoader.loadTexture("test.png");
		shader = new Shader();
		camera = new Camera();

		Vertex[] vertices = new Vertex[] { new Vertex(new Vector3f(-1, -1, 0), new Vector2f(0, 0)), new Vertex(new Vector3f(0, 1, 0), new Vector2f(0.5f, 0)), new Vertex(new Vector3f(1, -1, 0), new Vector2f(1.0f, 0)), new Vertex(new Vector3f(0, -1, 1), new Vector2f(0.5f, 1.0f)) };

		int[] indices = new int[] { 3, 1, 0, 2, 1, 3, 0, 1, 2, 0, 2, 3 };

		mesh.addVertices(vertices, indices);

		Transform.setCamera(camera);
		Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);

		transform = new Transform();

		shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
		shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
		shader.compileShader();

		shader.addUniform("transform");
	}

	public void input() {

		camera.input();

		// if (Input.getKeyDown(Keyboard.KEY_UP)) {
		// System.out.println("We've just pressed up!");
		// }
		// if (Input.getKeyUp(Keyboard.KEY_UP)) {
		// System.out.println("We've just released up!");
		// }
		// if (Input.getMouseDown(1)) {
		// System.out.println("We've just right clocked at" + Input.getMousePosition().toString());
		// }
		// if (Input.getMouseUp(1)) {
		// System.out.println("We've just released right mouse button!");
		// }
	}

	float temp = 0.0f;

	public void update() {
		temp += Time.getDelta();

		float sinTemp = (float) Math.sin(temp);

		transform.setTranslation(sinTemp, 0, 5);
		transform.setRotation(0, sinTemp * 180, 0);
		// transform.setScale(0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp);
	}

	public void render() {
		shader.bind();
		shader.setUniform("transform", transform.getProjectedTransformation());
		texture.bind();
		mesh.draw();
	}

}
