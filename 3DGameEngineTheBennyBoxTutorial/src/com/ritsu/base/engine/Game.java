package com.ritsu.base.engine;

import org.lwjgl.input.Keyboard;

import com.ritsu.base.engine.math.Vector3f;
import com.ritsu.base.engine.render.Mesh;
import com.ritsu.base.engine.render.Vertex;

public class Game {

	private Mesh mesh;

	public Game() {
		mesh = new Mesh();

		Vertex[] data = new Vertex[] { new Vertex(new Vector3f(-1, -1, 0)), new Vertex(new Vector3f(0, 1, 0)), new Vertex(new Vector3f(1, -1, 0)) };

		mesh.addVertices(data);
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

	public void update() {

	}

	public void render() {
		mesh.draw();
	}

}
