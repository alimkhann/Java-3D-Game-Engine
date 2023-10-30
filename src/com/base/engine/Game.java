package com.base.engine;

public class Game {
    private Mesh mesh;

    public Game () {
        mesh = new Mesh();

        Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-0.25f, -0.25f, 0)),
                                      new Vertex(new Vector3f(-0.25f, 0.25f, 0)),
                                      new Vertex(new Vector3f(0, 0.25f, 0)),};

        mesh.addVertices(data);
    }

    public void input() {

    }

    public void update() {

    }

    public void render() {
        mesh.draw();
    }
}
