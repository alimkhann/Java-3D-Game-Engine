package com.base.engine;

public class Vertex {
    private Vector3f pos;

    public Vertex(Vector3f pos) {
        this.pos = pos;
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }
}
