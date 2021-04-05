package com.nrd.engine.objects;

import com.nrd.engine.graphics.Renderer;
import com.nrd.engine.io.Input;
import com.nrd.engine.maths.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera {
    private Vector3f position, rotation;
    private float moveSpeed = 0.08f, mouseSensitivity = 0.05f;

    private double oldMouseX=0, oldMouseY=0, newMouseX=0, newMouseY=0;

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void update() {
        newMouseX = Input.getMouseX();
        newMouseY = Input.getMouseY();

        float x = (float) Math.sin(Math.toRadians(rotation.getY())) * moveSpeed;
        float z = (float) Math.cos(Math.toRadians(rotation.getY())) * moveSpeed;

        if(Input.isKeyDown(GLFW.GLFW_KEY_A)) {
            position = Vector3f.add(position, new Vector3f(-z, 0, x));
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_D)) {
            position = Vector3f.add(position, new Vector3f(z, 0, -x));
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_W)) {
            position = Vector3f.add(position, new Vector3f(-x, 0, -z));
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_S)) {
            position = Vector3f.add(position, new Vector3f(x, 0, z));
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_UP)) {
            position = Vector3f.add(position, new Vector3f(0, moveSpeed, 0));
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
            position = Vector3f.add(position, new Vector3f(0, -moveSpeed, 0));
        }

        float dx = (float) (newMouseX - oldMouseX);
        float dy = (float) (newMouseY - oldMouseY);

        rotation = Vector3f.add(rotation, new Vector3f(-dy * mouseSensitivity, -dx * mouseSensitivity, 0));

        oldMouseX = newMouseX;
        oldMouseY = newMouseY;
    }

    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }
}
