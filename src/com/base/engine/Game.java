package com.base.engine;

public class Game {
    private Mesh mesh;
    private Shader shader;

    public Game () {
        mesh = new Mesh();
        shader = new Shader();

        Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0)),
                                      new Vertex(new Vector3f(0, 1, 0)),
                                      new Vertex(new Vector3f(1, -1, 0)),};

        mesh.addVertices(data);

        shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vsh"));
        shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fsh"));
        shader.compileShader();
    }

    public void input() {
        if(Input.getKey(Input.KEY_UP))
            System.out.println("UP");

        if(Input.getMouse(1))
            System.out.println("Clicked at " + Input.getMousePosition().toString());
    }

    public void update() {

    }

    public void render() {
        shader.bind();
        mesh.draw();
    }
}
