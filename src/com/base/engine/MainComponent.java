package com.base.engine;

public class MainComponent {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final String TITLE = "3D Graphics Engine";
    public static final double FRAME_CAP = 10000.0;

    private boolean isRunning;
    private final Game game;

    public MainComponent() {
        System.out.println(RenderUtil.getOpenGLVersion());
        RenderUtil.initGraphics();
        isRunning = false;
        game = new Game();
    }

    public void start() {
        if (isRunning)
            return;

        run();
    }

    public void stop() {
        if(!isRunning)
            return;

        isRunning = false;
    }

    private void run() {
        isRunning = true;

        int frames = 0;
        long frameCounter = 0;

        final double frameTime = 1.0 / FRAME_CAP;

        long lastTime = Time.getTime();
        double unprocessedTime = 0;

        while (isRunning) {
            boolean render = false;

            long startTime = Time.getTime();
            long pastTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += pastTime / (double) Time.SECOND;
            frameCounter += pastTime;

            while (unprocessedTime > frameTime) {
                render = true;

                unprocessedTime -= frameTime;

                if (Window.isCloseRequested())
                    stop();

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
                render();
                frames++;
            }
            else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        cleanUp();
    }

    private void render() {
        RenderUtil.clearScreen();
        game.render();
        Window.render();
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
