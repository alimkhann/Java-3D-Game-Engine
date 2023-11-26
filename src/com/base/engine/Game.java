package com.base.engine;

public class Game {
    private final Mesh mesh;
    private final Shader shader;
    private final Transform transform;

    public Game () {
        mesh = new Mesh();
        shader = new Shader();

        Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0)),
                                      new Vertex(new Vector3f(0, 1, 0)),
                                      new Vertex(new Vector3f(1, -1, 0)),
                                      new Vertex(new Vector3f(0, -1, 1))};

        int[] indices = new int[] {0, 1, 3,
                                   3, 1, 2,
                                   2, 1, 0,
                                   0, 2, 3};

        mesh.addVertices(vertices, indices);

        transform = new Transform();

        shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vsh"));
        shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fsh"));
        shader.compileShader();

        shader.addUniform("transform");
    }

    public void input() {
        if(Input.getKey(Input.KEY_UP))
            System.out.println("UP");

        if(Input.getMouse(1))
            System.out.println("Clicked at " + Input.getMousePosition());
    }

    float temp = 0.0f;
    // float tempAmount = 0.0f;

    public void update() {
        temp += (float) Time.getDelta();

        float sinTemp = (float) Math.sin(temp);

        // transform.setScale(sinTemp, sinTemp, sinTemp);
        transform.setTranslation(sinTemp, 0, 0);
        transform.setRotation(0, sinTemp * 180, 0);
        // shader.setUniformf("uniformFloat", (float) Math.abs(Math.sin(temp)));
    }

    public void render() {
        shader.bind();
        shader.setUniform("transform", transform.getTransformation());
        mesh.draw();
    }
}
