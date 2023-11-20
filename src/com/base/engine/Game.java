package com.base.engine;

public class Game {
    private Mesh mesh;
    private Shader shader;
    private Transform transform;

    public Game () {
        mesh = new Mesh();
        shader = new Shader();

        Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0)),
                                      new Vertex(new Vector3f(0, 1, 0)),
                                      new Vertex(new Vector3f(1, -1, 0)),};

        mesh.addVertices(data);

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
            System.out.println("Clicked at " + Input.getMousePosition().toString());
    }

    float temp = 0.0f;
    // float tempAmount = 0.0f;

    public void update() {
        temp += Time.getDelta();

        transform.setTranslation((float) Math.sin(temp), (float) Math.tan(temp), (float) Math.cos(temp));

        // tempAmount = (float)(Math.sin(temp));
        // shader.setUniformf("uniformFloat", (float) Math.abs(Math.sin(temp)));
    }

    public void render() {
        shader.bind();
        shader.setUniform("transform", transform.getTransformation());
        mesh.draw();
    }
}
