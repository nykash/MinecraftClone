package com.nrd.engine.objects;

import com.nrd.engine.graphics.Mesh;
import com.nrd.engine.maths.Vector3f;

public class GameObject {
    private Vector3f position, rotation, scale;
    private Mesh mesh;

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public GameObject(Mesh mesh, Vector3f position, Vector3f rotation, Vector3f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.mesh = mesh;
    }

    public void render() {

    }

    public void update() {

    }
}
