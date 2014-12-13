package com.ritsu.base.engine.render;

import com.ritsu.base.engine.math.Vector2f;
import com.ritsu.base.engine.math.Vector3f;

public class Vertex {

	public static final int SIZE = 5;

	private Vector3f pos;
	private Vector2f textCoord;

	public Vertex(Vector3f pos) {
		this(pos, new Vector2f(0, 0));
	}

	public Vertex(Vector3f pos, Vector2f textCoords) {
		this.pos = pos;
		this.textCoord = textCoords;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector2f getTextCoord() {
		return textCoord;
	}

	public void setTextCoord(Vector2f textCoord) {
		this.textCoord = textCoord;
	}

}
