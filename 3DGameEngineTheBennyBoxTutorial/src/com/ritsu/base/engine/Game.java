package com.ritsu.base.engine;

import org.lwjgl.input.Keyboard;

import com.ritsu.base.engine.math.Time;
import com.ritsu.base.engine.math.Vector3f;
import com.ritsu.base.engine.render.Mesh;
import com.ritsu.base.engine.render.Vertex;

public class Game {

	private Mesh mesh;
	private Shader shader;

	public Game() {
		mesh = new Mesh();
		shader = new Shader();

		Vertex[] data = new Vertex[] { new Vertex(new Vector3f(-1, -1, 0)), new Vertex(new Vector3f(0, 1, 0)), new Vertex(new Vector3f(1, -1, 0)) };

		mesh.addVertices(data);

		shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
		shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
		shader.compileShader();

		shader.addUniform("uniformFloat");
	}

	public void input() {
		if (Input.getKeyDown(Keyboard.KEY_UP)) {
			System.out.println("We've just pressed up!");
		}
		if (Input.getKeyUp(Keyboard.KEY_UP)) {
			System.out.println("We've just released up!");
		}
		if (Input.getMouseDown(1)) {
			System.out.println("We've just right clocked at" + Input.getMousePosition().toString());
		}
		if (Input.getMouseUp(1)) {
			System.out.println("We've just released right mouse button!");
		}
	}

	float temp = 0.0f;

	public void update() {
		temp += Time.getDelta();

		shader.setUniformf("uniformFloat", (float) Math.abs(Math.sin(temp)));
	}

	public void render() {
		shader.bind();
		mesh.draw();
	}

}
