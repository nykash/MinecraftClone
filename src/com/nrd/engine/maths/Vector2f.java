package com.nrd.engine.maths;

public class Vector2f {
    private float x, y;

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

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Vector3f add(Vector3f vector1, Vector3f vector2) {
        return new Vector3f(vector1.getX() + vector2.getX(),
                vector1.getY() + vector2.getY(),
                vector1.getZ() + vector2.getZ());
    }

    public static Vector2f subtract(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() - vector2.getX(),
                vector1.getY() - vector2.getY());
    }

    public static Vector2f multiply(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() * vector2.getX(),
                vector1.getY() * vector2.getY());
    }

    public static Vector2f divide(Vector2f vector1, Vector2f vector2) {
        return new Vector2f(vector1.getX() / vector2.getX(),
                vector1.getY() / vector2.getY());
    }

    public static float length(Vector2f vector) {
        return (float) Math.sqrt(vector.getX() * vector.getY() +
                vector.getY() * vector.getY());
    }
    public static Vector2f normalize(Vector2f vector) {
        float len = length(vector);
        return Vector2f.divide(vector, new Vector2f(len, len));
    }

    public static float dot(Vector2f vector1, Vector2f vector2) {
        return vector1.getX() * vector2.getX() +
                vector1.getY() * vector2.getY();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2f vector2f = (Vector2f) o;

        if (Float.compare(vector2f.x, x) != 0) return false;
        return Float.compare(vector2f.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }


}
