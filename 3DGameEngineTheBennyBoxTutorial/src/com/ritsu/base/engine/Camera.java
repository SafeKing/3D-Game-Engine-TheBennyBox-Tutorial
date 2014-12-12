package com.ritsu.base.engine;

import com.ritsu.base.engine.math.Vector3f;

public class Camera {

	public static final Vector3f yAxis = new Vector3f(0, 1, 0);

	private Vector3f pos;
	private Vector3f forward;
	private Vector3f up;

	public Camera() {
		this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
	}

	public Camera(Vector3f pos, Vector3f forward, Vector3f up) {
		super();
		this.pos = pos;
		this.forward = forward;
		this.up = up;

		up.normalize();
		forward.normalize();
	}

	public void move(Vector3f dir, float amt) {
		pos = pos.add(dir.mul(amt));
	}

	public void rotateY(float angle) {
		Vector3f Haxis = yAxis.cross(forward);
		Haxis.normalize();

		forward.rotate(angle, yAxis);
		forward.normalize();

		up = forward.cross(Haxis);
		up.normalize();
	}

	public void rotateX(float angle) {
		Vector3f Haxis = yAxis.cross(forward);
		Haxis.normalize();

		forward.rotate(angle, Haxis);
		forward.normalize();

		up = forward.cross(Haxis);
		up.normalize();
	}

	public Vector3f getLeft() {
		Vector3f left = up.cross(forward);
		left.normalize();
		return left;
	}

	public Vector3f getRight() {
		Vector3f right = forward.cross(up);
		right.normalize();
		return right;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector3f getForward() {
		return forward;
	}

	public void setForward(Vector3f forward) {
		this.forward = forward;
	}

	public Vector3f getUp() {
		return up;
	}

	public void setUp(Vector3f up) {
		this.up = up;
	}

}
