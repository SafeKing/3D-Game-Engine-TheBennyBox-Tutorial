package com.ritsu.base.engine;

import com.ritsu.base.engine.math.Time;
import com.ritsu.base.engine.render.RenderUtil;

public class MainComponent {

	public static final int WIDTH = 800, HEIGHT = 600;
	public static final String TITLE = "3D Engine (TheBennyBox) v0.0.01a";
	public static final double FRAME_CAP = 5000.0;

	private boolean running;
	private Game game;

	public MainComponent() {
		System.out.println(RenderUtil.getOpenGLVersion());
		RenderUtil.initGraphics();
		running = false;
		game = new Game();
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

		final double frameTime = 1.0 / FRAME_CAP;

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
				Input.update();

				game.input();
				game.update();

				if (frameCounter >= Time.SECOND) {
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			if (render) {
				render();
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

	private void render() {
		RenderUtil.clearScreen();
		Window.render();
		game.render();
	}

	private void cleanUp() {
		Window.dispose();
	}

	public static void main(String[] args) {
		Window.createWindow(WIDTH, HEIGHT, TITLE);

		MainComponent game = new MainComponent();

		game.start();
	}

}
