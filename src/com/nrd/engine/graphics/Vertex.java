package com.nrd.engine.graphics;

import com.nrd.engine.maths.Vector2f;
import com.nrd.engine.maths.Vector3f;

public class Vertex {
    private Vector3f position, color;
    private Vector2f textureCoord;

    public Vector3f getPosition() {
        return position;
    }

    public Vector2f getTextureCoord() {
        return textureCoord;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vertex(Vector3f position, Vector2f textureCoord) {
        this.position = position;
        this.color = new Vector3f(0f, 0f, 0f);
        this.textureCoord = textureCoord;
    }

    public Vector3f getColor() {
        return this.color;
    }

}
