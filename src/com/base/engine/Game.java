package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Game {
    public Game () {

    }

    public void input() {
        if (Input.getKeyDown(Keyboard.KEY_W))
            System.out.println("Pressed 'W'");
        if (Input.getKeyUp(Keyboard.KEY_W))
            System.out.println("Released 'W'");

        if (Input.getMouseDown(1))
            System.out.println("Pressed 'RMB' at " + Input.getMousePosition().toString());
        if (Input.getMouseUp(1))
            System.out.println("Released 'RMB'");
    }

    public void update() {

    }

    public void render() {

    }
}
