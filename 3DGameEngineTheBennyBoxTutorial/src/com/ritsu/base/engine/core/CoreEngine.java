package com.ritsu.base.engine.core;

import com.ritsu.base.engine.core.input.Input;
import com.ritsu.base.engine.core.math.Time;
import com.ritsu.base.engine.render.window.Window;

public class CoreEngine {

	private boolean running;
	private Game game;
	private RenderingEngine renderingEngine;
	private int width;
	private int height;
	private double frameTime;

	public CoreEngine(int width, int height, double framerate, Game game) {
		running = false;
		this.game = game;
		this.width = width;
		this.height = height;
		this.frameTime = 1.0 / framerate;
	}

	private void initializeRenderingSystem() {
		System.out.println(RenderingEngine.getOpenGLVersion());
		this.renderingEngine = new RenderingEngine();
	}

	public void createWindow(String title) {
		Window.createWindow(width, height, title);
		initializeRenderingSystem();
	}

	public void start() {
		if (running) return;

		run();
	}

	public void stop() {
		if (!running) return;

		running = false;
	}

	private void run() {
		running = true;

		int frames = 0;
		long frameCounter = 0;

		game.init();

		long lastTime = Time.getTime();
		double unprocessedTime = 0;

		while (running) {
			boolean render = false;

			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;

			unprocessedTime += passedTime / (double) Time.SECOND;
			frameCounter += passedTime;

			while (unprocessedTime > frameTime) {
				render = true;

				unprocessedTime -= frameTime;

				if (Window.isCloseRequested()) stop();

				Time.setDelta(frameTime);

				game.input();
				Input.update();

				game.update();

				if (frameCounter >= Time.SECOND) {
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			if (render) {
				renderingEngine.render(game.getRootObject());
				Window.render();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		cleanUp();
	}

	private void cleanUp() {
		Window.dispose();
	}

}
