package com.nrd.engine.graphics;

import com.nrd.engine.io.Window;
import com.nrd.engine.maths.Face;
import com.nrd.engine.maths.Matrix4f;
import com.nrd.engine.maths.Vector3f;
import com.nrd.engine.objects.Camera;
import com.nrd.engine.objects.GameObject;
import com.nrd.minecraft.blocks.Block;
import com.nrd.minecraft.blocks.Textures;
import com.nrd.minecraft.render.Atlas;
import org.lwjgl.opengl.*;

public class Renderer {

    private Shader shader;
    private Window window;

    public Renderer(Window window, Shader shader) {
        this.shader = shader;
        this.window = window;

    }


    public void renderMesh(GameObject object, Camera camera) {
        Mesh mesh = object.getMesh();
        GL30.glBindVertexArray(mesh.getVAO());
        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL11.GL_TEXTURE_2D, mesh.getMaterial().getTextureID());
        shader.bind();
        shader.setUniform("model", Matrix4f.transform(object.getPosition(), object.getRotation(), object.getScale()));
        shader.setUniform("view", Matrix4f.view(camera.getPosition(), camera.getRotation()));
        shader.setUniform("projection", window.getProjectionMatrix());
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
        shader.unbind();
        GL30.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

    public void renderChunk(ChunkMesh mesh, Camera camera, Vector3f position) {
        Vector3f rotation = new Vector3f(0f, 0f, 0f);
        Vector3f scale = new Vector3f(1f, 1f, 1f);

        GL30.glBindVertexArray(mesh.getVAO());
        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL11.GL_TEXTURE_2D, Atlas.atlas.getTextureID());
        shader.bind();
        shader.setUniform("model", Matrix4f.transform(position, rotation, scale));
        shader.setUniform("view", Matrix4f.view(camera.getPosition(), camera.getRotation()));
        shader.setUniform("projection", window.getProjectionMatrix());
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
        shader.unbind();
        GL30.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }
}
