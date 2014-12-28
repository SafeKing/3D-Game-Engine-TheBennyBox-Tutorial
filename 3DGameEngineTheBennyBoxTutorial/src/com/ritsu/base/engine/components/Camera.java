package com.ritsu.base.engine.components;

import com.ritsu.base.engine.core.input.Input;
import com.ritsu.base.engine.core.math.Matrix4f;
import com.ritsu.base.engine.core.math.Quaternion;
import com.ritsu.base.engine.core.math.Vector2f;
import com.ritsu.base.engine.core.math.Vector3f;
import com.ritsu.base.engine.render.RenderingEngine;
import com.ritsu.base.engine.render.window.Window;

public class Camera extends GameComponent {

	public static final Vector3f yAxis = new Vector3f(0, 1, 0);

	private Matrix4f projection;

	public Camera(float fov, float aspect, float zNear, float zFar) {
		this.projection = new Matrix4f().initPerspective(fov, aspect, zNear, zFar);
	}

	public Matrix4f getViewProjection() {
		Matrix4f cameraRotation = getTransform().getRot().toRotationMatrix();
		Matrix4f cameraTranslation = new Matrix4f().initTranslation(-getTransform().getPos().getX(), -getTransform().getPos().getY(), -getTransform().getPos().getZ());

		return projection.mul(cameraRotation.mul(cameraTranslation));
	}

	@Override
	public void addToRenderingEngine(RenderingEngine renderingEngine) {
		renderingEngine.addCamera(this);
	}

	boolean mouseLocked = false;
	Vector2f centerPosition = new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2);

	@Override
	public void input(float delta) {
		float sensitivity = -0.5f;
		float movAmt = (float) (10 * delta);
		// float rotAmt = (float) (100 * Time.getDelta());

		if (Input.getKey(Input.KEY_ESCAPE)) {
			Input.setCursor(true);
			mouseLocked = false;
		}
		if (Input.getMouseDown(0)) {
			Input.setMousePosition(centerPosition);
			Input.setCursor(false);
			mouseLocked = true;
		}

		if (Input.getKey(Input.KEY_W)) move(getTransform().getRot().getForward(), movAmt);
		if (Input.getKey(Input.KEY_S)) move(getTransform().getRot().getForward(), -movAmt);
		if (Input.getKey(Input.KEY_A)) move(getTransform().getRot().getLeft(), movAmt);
		if (Input.getKey(Input.KEY_D)) move(getTransform().getRot().getRight(), movAmt);

		if (mouseLocked) {
			Vector2f deltaPos = Input.getMousePosition().sub(centerPosition);

			boolean rotY = deltaPos.getX() != 0;
			boolean rotX = deltaPos.getY() != 0;

			if (rotY) getTransform().setRot(getTransform().getRot().mul(new Quaternion().initRotation(yAxis, (float) Math.toRadians(deltaPos.getX() * sensitivity))).normalized());
			if (rotX) getTransform().setRot(getTransform().getRot().mul(new Quaternion().initRotation(getTransform().getRot().getRight(), ((float) Math.toRadians(-deltaPos.getY() * sensitivity)))).normalized());

			if (rotY || rotX) Input.setMousePosition(new Vector2f(Window.getWidth() / 2, Window.getHeight() / 2));
		}

		// if(Input.getKey(Input.KEY_UP))
		// rotateX(-rotAmt);
		// if(Input.getKey(Input.KEY_DOWN))
		// rotateX(rotAmt);
		// if(Input.getKey(Input.KEY_LEFT))
		// rotateY(-rotAmt);
		// if(Input.getKey(Input.KEY_RIGHT))
		// rotateY(rotAmt);
	}

	public void move(Vector3f dir, float amt) {
		getTransform().setPos(getTransform().getPos().add(dir.mul(amt)));
	}

}
