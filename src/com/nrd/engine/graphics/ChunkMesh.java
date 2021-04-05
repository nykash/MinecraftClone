package com.nrd.engine.graphics;

import com.nrd.engine.maths.Face;
import com.nrd.engine.maths.Vector2f;
import com.nrd.engine.maths.Vector3f;
import com.nrd.minecraft.blocks.Block;
import com.nrd.minecraft.blocks.Textures;
import com.nrd.minecraft.render.Atlas;
import com.nrd.minecraft.world.Chunk;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;
import org.lwjglx.Sys;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class ChunkMesh {
    private ArrayList<Vertex> vertices;
    private ArrayList<Integer> indices;
    private int vao, pbo, ibo, tbo;
    private Chunk chunk;

    public int getVAO() {
        return vao;
    }

    public int getPBO() {
        return pbo;
    }

    public int getIBO() {
        return ibo;
    }

    public int getTBO() {
        return tbo;
    }
    public int[] getIndices() {
        int[] indices_array = new int[indices.size()];
        for(int i = 0; i < indices.size(); i++) {
            indices_array[i] = indices.get(i);
        }
        return indices_array;
    }

    public ChunkMesh(Chunk chunk) {
        this.chunk = chunk;

        vertices = new ArrayList<>();
        indices = new ArrayList<>();

        update();
    }

    public void update() {
        int c = 0;
        float s = Chunk.BLOCK_SIZE;
        for(float x = 0; x < Chunk.CHUNK_WIDTH; x++) {
            for(float y = 0; y < Chunk.CHUNK_HEIGHT; y++) {
                for(float z = 0; z < Chunk.CHUNK_LENGTH; z++) {
                    //Back face
                    int row=0, col=0;
                    if(z == 0 || chunk.chunk[(int)x][(int)y][(int)z-1] == Block.AIR) {
                        col=(int)Textures.textureMap.get(chunk.chunk[(int)x][(int)y][(int)z]).get(Face.BACK).getX();
                        row=(int)Textures.textureMap.get(chunk.chunk[(int)x][(int)y][(int)z]).get(Face.BACK).getY();
                        vertices.add(new Vertex(new Vector3f(x*s-s/2,  y*s+s/2, z*s-s/2), Atlas.atlas.trans(new Vector2f(0.0f, 0.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f(x*s-s/2, y*s-s/2, z*s-s/2), Atlas.atlas.trans(new Vector2f(0.0f, 1.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f( x*s+s/2, y*s-s/2, z*s-s/2), Atlas.atlas.trans(new Vector2f(1.0f, 1.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f( x*s+s/2,  y*s+s/2, z*s-s/2), Atlas.atlas.trans(new Vector2f(1.0f, 0.0f), col, row)));

                    }

                    //Front face
                    if(z == Chunk.CHUNK_LENGTH-1 || chunk.chunk[(int)x][(int)y][(int)z+1] == Block.AIR) {
                        col=(int)Textures.textureMap.get(chunk.chunk[(int)x][(int)y][(int)z]).get(Face.FRONT).getX();
                        row=(int)Textures.textureMap.get(chunk.chunk[(int)x][(int)y][(int)z]).get(Face.FRONT).getY();
                        vertices.add(new Vertex(new Vector3f(x*s-s/2,  y*s+s/2,  z*s+s/2), Atlas.atlas.trans(new Vector2f(0.0f, 0.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f(x*s-s/2, y*s-s/2,  z*s+s/2), Atlas.atlas.trans(new Vector2f(0.0f, 1.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f( x*s+s/2, y*s-s/2,  z*s+s/2), Atlas.atlas.trans(new Vector2f(1.0f, 1.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f( x*s+s/2,  y*s+s/2,  z*s+s/2), Atlas.atlas.trans(new Vector2f(1.0f, 0.0f), col, row)));
                    }


                    //Right face
                    if(x == Chunk.CHUNK_WIDTH-1 || chunk.chunk[(int)x+1][(int)y][(int)z] == Block.AIR) {
                        col=(int)Textures.textureMap.get(chunk.chunk[(int)x][(int)y][(int)z]).get(Face.RIGHT).getX();
                        row=(int)Textures.textureMap.get(chunk.chunk[(int)x][(int)y][(int)z]).get(Face.RIGHT).getY();
                        vertices.add(new Vertex(new Vector3f( x*s+s/2,  y*s+s/2, z*s-s/2), Atlas.atlas.trans(new Vector2f(0.0f, 0.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f( x*s+s/2, y*s-s/2, z*s-s/2), Atlas.atlas.trans(new Vector2f(0.0f, 1.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f( x*s+s/2, y*s-s/2,  z*s+s/2), Atlas.atlas.trans(new Vector2f(1.0f, 1.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f( x*s+s/2,  y*s+s/2,  z*s+s/2), Atlas.atlas.trans(new Vector2f(1.0f, 0.0f), col, row)));

                    }

                    //Left face
                    if(x == 0 || chunk.chunk[(int)x-1][(int)y][(int)z] == Block.AIR) {
                        col=(int)Textures.textureMap.get(chunk.chunk[(int)x][(int)y][(int)z]).get(Face.LEFT).getX();
                        row=(int)Textures.textureMap.get(chunk.chunk[(int)x][(int)y][(int)z]).get(Face.LEFT).getY();
                        vertices.add(new Vertex(new Vector3f(x*s-s/2,  y*s+s/2, z*s-s/2), Atlas.atlas.trans(new Vector2f(0.0f, 0.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f(x*s-s/2, y*s-s/2, z*s-s/2), Atlas.atlas.trans(new Vector2f(0.0f, 1.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f(x*s-s/2, y*s-s/2,  z*s+s/2), Atlas.atlas.trans(new Vector2f(1.0f, 1.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f(x*s-s/2,  y*s+s/2,  z*s+s/2), Atlas.atlas.trans(new Vector2f(1.0f, 0.0f), col, row)));

                    }

                    //Top face
                    if(y == Chunk.CHUNK_HEIGHT-1 || chunk.chunk[(int)x][(int)y+1][(int)z] == Block.AIR) {
                        col=(int)Textures.textureMap.get(chunk.chunk[(int)x][(int)y][(int)z]).get(Face.TOP).getX();
                        row=(int)Textures.textureMap.get(chunk.chunk[(int)x][(int)y][(int)z]).get(Face.TOP).getY();
                        vertices.add(new Vertex(new Vector3f(x*s-s/2,  y*s+s/2,  z*s+s/2), Atlas.atlas.trans(new Vector2f(0.0f, 0.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f(x*s-s/2,  y*s+s/2, z*s-s/2), Atlas.atlas.trans(new Vector2f(0.0f, 1.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f( x*s+s/2,  y*s+s/2, z*s-s/2), Atlas.atlas.trans(new Vector2f(1.0f, 1.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f( x*s+s/2,  y*s+s/2,  z*s+s/2), Atlas.atlas.trans(new Vector2f(1.0f, 0.0f), col, row)));

                    }

                    //Bottom face
                    if(y == 0 || chunk.chunk[(int)x][(int)y-1][(int)z] == Block.AIR) {
                        col=(int)Textures.textureMap.get(chunk.chunk[(int)x][(int)y][(int)z]).get(Face.BOTTOM).getX();
                        row=(int)Textures.textureMap.get(chunk.chunk[(int)x][(int)y][(int)z]).get(Face.BOTTOM).getY();
                        vertices.add(new Vertex(new Vector3f(x*s-s/2, y*s-s/2,  z*s+s/2), Atlas.atlas.trans(new Vector2f(0.0f, 0.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f(x*s-s/2, y*s-s/2, z*s-s/2), Atlas.atlas.trans(new Vector2f(0.0f, 1.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f( x*s+s/2, y*s-s/2, z*s-s/2), Atlas.atlas.trans(new Vector2f(1.0f, 1.0f), col, row)));
                        vertices.add(new Vertex(new Vector3f( x*s+s/2, y*s-s/2,  z*s+s/2), Atlas.atlas.trans(new Vector2f(1.0f, 0.0f), col, row)));
                    }

                    indices.addAll(Arrays.asList(0+c,1+c,3+c,
                            3+c,1+c,2+c,
                            4+c,5+c,7+c,
                            7+c,5+c,6+c,
                            8+c,9+c,11+c,
                            11+c,9+c,10+c,
                            12+c,13+c,15+c,
                            15+c,13+c,14+c,
                            16+c,17+c,19+c,
                            19+c,17+c,18+c,
                            20+c,21+c,23+c,
                            23+c,21+c,22+c));
                    c+=24;
                }
            }
        }
    }

    public void create() {
        vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.size() * 3);
        float[] positionData = new float[vertices.size() * 3];

        for(int i = 0; i < vertices.size(); i++) {
            positionData[i*3] = vertices.get(i).getPosition().getX();
            positionData[i*3+1] = vertices.get(i).getPosition().getY();
            positionData[i*3+2] = vertices.get(i).getPosition().getZ();
        }

        positionBuffer.put(positionData).flip();

        positionBuffer.put(positionData).flip();

        pbo = storeData(positionBuffer, 0, 3);

        FloatBuffer textureBuffer = MemoryUtil.memAllocFloat(vertices.size() * 2);
        float[] textureData = new float[vertices.size() * 2];

        for(int i = 0; i < vertices.size(); i++) {
            textureData[i*2] = vertices.get(i).getTextureCoord().getX();
            textureData[i*2+1] = vertices.get(i).getTextureCoord().getY();
        }

        textureBuffer.put(textureData).flip();

        tbo = storeData(textureBuffer, 1, 2);

        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.size());
        int[] indices_array = new int[indices.size()];
        for(int i = 0; i < indices.size(); i++) {
            indices_array[i] = indices.get(i);
        }

        indicesBuffer.put(indices_array).flip();
        ibo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    private int storeData(FloatBuffer buffer, int index, int size) {
        int bufferID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        return bufferID;
    }
}
