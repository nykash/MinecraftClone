package com.nrd.engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.IOException;

public class Material {
    Texture texture;
    private float width, height;
    private int textureID;
    private String path;

    public Texture getTexture() {
        return texture;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getTextureID() {
        return textureID;
    }

    public Material(String path) {
        this.path = path;
    }

    public void create() {
        try {
            texture = TextureLoader.getTexture(path.split("[.]")[1], Material.class.getResourceAsStream(path), GL11.GL_LINEAR);
            GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -5);
        } catch(IOException e) {
            System.err.println("can't find texture at " + path);
        }

        width = texture.getWidth();
        height = texture.getHeight();
        textureID = texture.getTextureID();
    }

    public void destroy() {
        GL13.glDeleteTextures(textureID);
    }

}
