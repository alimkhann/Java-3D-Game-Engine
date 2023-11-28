package com.base.engine;

public class Game {
    private final Mesh mesh;
    private final Shader shader;
    private final Transform transform;
    private Camera camera;

    public Game () {
        mesh = ResourceLoader.loadMesh("suzanne.obj"); //new Mesh();
        shader = new Shader();
        camera = new Camera();

//        Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0)),
//                                      new Vertex(new Vector3f(0, 1, 0)),
//                                      new Vertex(new Vector3f(1, -1, 0)),
//                                      new Vertex(new Vector3f(0, -1, 1))};
//
//        int[] indices = new int[] {0, 1, 3,
//                                   3, 1, 2,
//                                   2, 1, 0,
//                                   0, 2, 3};
//
//        mesh.addVertices(vertices, indices);

        transform = new Transform();
        Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
        Transform.setCamera(camera);

        shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vsh"));
        shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fsh"));
        shader.compileShader();

        shader.addUniform("transform");
    }

    public void input() {
        camera.input();
    }

    float temp = 0.0f;
    // float tempAmount = 0.0f;

    public void update() {
        temp += (float) Time.getDelta();

        float sinTemp = (float) Math.sin(temp);

        //transform.setScale(0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp);
        //transform.setTranslation(sinTemp, 0, 3);
        //transform.setRotation(0, sinTemp * 180, 0);
        // shader.setUniformf("uniformFloat", (float) Math.abs(Math.sin(temp)));
    }

    public void render() {
        shader.bind();
        shader.setUniform("transform", transform.getProjectedTransformation());
        mesh.draw();
    }
}
