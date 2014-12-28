package com.ritsu.base.engine.core;

import java.util.ArrayList;

import com.ritsu.base.engine.components.GameComponent;
import com.ritsu.base.engine.core.math.Transform;
import com.ritsu.base.engine.render.RenderingEngine;
import com.ritsu.base.engine.render.lightning.shaders.Shader;

public class GameObject {

	private ArrayList<GameObject> children;
	private ArrayList<GameComponent> components;
	private Transform transform;

	public GameObject() {
		children = new ArrayList<GameObject>();
		components = new ArrayList<GameComponent>();
		transform = new Transform();
	}

	public void addChild(GameObject child) {
		children.add(child);
	}

	public GameObject addComponent(GameComponent component) {
		components.add(component);
		component.setParent(this);

		return this;
	}

	public void input(float delta) {

		for (GameComponent component : components) {
			component.input(delta);
		}

		for (GameObject child : children) {
			child.input(delta);
		}
	}

	public void update(float delta) {

		for (GameComponent component : components) {
			component.update(delta);
		}
		for (GameObject child : children) {
			child.update(delta);
		}
	}

	public void render(Shader shader) {

		for (GameComponent component : components) {
			component.render(shader);
		}
		for (GameObject child : children) {
			child.render(shader);
		}
	}

	public void addToRenderingEngine(RenderingEngine renderingEngine) {
		for (GameComponent component : components) {
			component.addToRenderingEngine(renderingEngine);
		}
		for (GameObject child : children) {
			child.addToRenderingEngine(renderingEngine);
		}
	}

	public Transform getTransform() {
		return transform;
	}

}
