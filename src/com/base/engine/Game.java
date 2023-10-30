package com.base.engine;

public class Game {
    private Mesh mesh;

    public Game () {
        mesh = new Mesh();

        Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0)),
                                      new Vertex(new Vector3f(0, 1, 0)),
                                      new Vertex(new Vector3f(1, -1, 0)),};

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
