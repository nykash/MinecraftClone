package com.nrd.engine.maths;

import org.lwjgl.system.CallbackI;

import java.util.Objects;

public class Vector3f {
    private float x, y, z;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3f add(Vector3f vector1, Vector3f vector2) {
        return new Vector3f(vector1.getX() + vector2.getX(),
                vector1.getY() + vector2.getY(),
                vector1.getZ() + vector2.getZ());
    }

    public static Vector3f subtract(Vector3f vector1, Vector3f vector2) {
        return new Vector3f(vector1.getX() - vector2.getX(),
                vector1.getY() - vector2.getY(),
                vector1.getZ() - vector2.getZ());
    }

    public static Vector3f multiply(Vector3f vector1, Vector3f vector2) {
        return new Vector3f(vector1.getX() * vector2.getX(),
                vector1.getY() * vector2.getY(),
                vector1.getZ() * vector2.getZ());
    }

    public static Vector3f divide(Vector3f vector1, Vector3f vector2) {
        return new Vector3f(vector1.getX() / vector2.getX(),
                vector1.getY() / vector2.getY(),
                vector1.getZ() / vector2.getZ());
    }

    public static float length(Vector3f vector) {
        return (float) Math.sqrt(vector.getX() * vector.getY() +
                vector.getY() * vector.getY() +
                vector.getZ() * vector.getZ());
    }

    public static Vector3f normalize(Vector3f vector) {
        float len = length(vector);
        return Vector3f.divide(vector, new Vector3f(len, len, len));
    }

    public static float dot(Vector3f vector1, Vector3f vector2) {
        return vector1.getX() * vector2.getX() +
                vector1.getY() * vector2.getY() +
                vector1.getZ() * vector2.getZ();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector3f vector3f = (Vector3f) o;

        if (Float.compare(vector3f.x, x) != 0) return false;
        if (Float.compare(vector3f.y, y) != 0) return false;
        return Float.compare(vector3f.z, z) == 0;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (z != +0.0f ? Float.floatToIntBits(z) : 0);
        return result;
    }
}
