package com.base.engine;

public class MainComponent {
    // Constants for the window dimensions and title.
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final String TITLE = "3D Game Engine";
    public static final double FRAME_CAP = 10000.0; // Maximum frame rate

    private boolean isRunning; // Flag to track if the game is running
    private final Game game;

    // Constructor initializes the game and sets isRunning to false
    public MainComponent() {
        isRunning = false;
        game = new Game();
    }

    // Method to start the game
    public void start() {
        if (isRunning)
            return;

        run(); // Start the game loop
    }

    // Method to stop the game
    public void stop() {
        if(!isRunning)
            return;

        isRunning = false; // Stop the game loop
    }

    private void run() {
        isRunning = true; // Set the game as running

        // Initialize frame count and frame time tracking variables
        int frames = 0;
        long frameCounter = 0;

        // Define the time allocated for each frame (inverse of FRAME_CAP)
        final double frameTime = 1.0 / FRAME_CAP;

        // Initialize the last recorded time.
        long lastTime = Time.getTime();
        // Initialize unprocessed time for frame time calculation.
        double unprocessedTime = 0;

        // Game loop: continues as long as the game is running
        while (isRunning) {
            boolean render = false; // Flag to determine whether to render

            long startTime = Time.getTime(); // Record the current time.
            long pastTime = startTime - lastTime; // Calculate time passed since the last frame.
            lastTime = startTime; // Update last recorded time.

            // Accumulate the time passed since the last frame.
            unprocessedTime += pastTime / (double) Time.SECOND;
            // Accumulate the past time in the frame counter.
            frameCounter += pastTime;

            // While there is enough unprocessed time for a frame
            while (unprocessedTime > frameTime) {
                render = true;

                // Subtract one frame worth of time from unprocessed time
                unprocessedTime -= frameTime;

                // Check if the game window should be closed and stop the game if so
                if (Window.isCloseRequested())
                    stop();

                // Update the time delta for frame time-based calculations
                Time.setDelta(frameTime);
                Input.update();

                game.input();
                game.update();

                // If a second has passed (used for frame rate monitoring)
                if (frameCounter >= Time.SECOND) {
                    // Print the frame count for monitoring purposes
                    System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                }
            }

            // If rendering is required for this frame
            if (render) {
                // Call the render method of the Game and Window classes and update the window
                render();
                frames++;
            }
            else {
                try {
                    // If rendering is not required, sleep briefly to control frame rate
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // When the game loop ends, perform cleanup tasks
        cleanUp();
    }

    // Method to render the game
    private void render() {
        game.render();
        Window.render();
    }

    // Method to clean up and close the game
    private void cleanUp() {
        Window.dispose();
    }

    public static void main(String[] args) {
        Window.createWindow(WIDTH, HEIGHT, TITLE);

        MainComponent game = new MainComponent();

        game.start();
    }
}
