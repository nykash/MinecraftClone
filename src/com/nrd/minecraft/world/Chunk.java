package com.nrd.minecraft.world;

import com.nrd.engine.graphics.ChunkMesh;
import com.nrd.engine.graphics.Mesh;
import com.nrd.engine.graphics.Renderer;
import com.nrd.engine.graphics.TextureAtlas;
import com.nrd.engine.io.Window;
import com.nrd.engine.maths.Matrix4f;
import com.nrd.engine.maths.Vector2f;
import com.nrd.engine.maths.Vector3f;
import com.nrd.engine.objects.Camera;
import com.nrd.minecraft.blocks.Block;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class Chunk {
    public static final int CHUNK_HEIGHT = 16;
    public static final int CHUNK_WIDTH = 16;
    public static final int CHUNK_LENGTH = 16;
    public static final float BLOCK_SIZE = 1;

    public Block[][][] chunk;
    private ChunkMesh mesh;
    private Renderer renderer;
    private Vector3f position;

    public ChunkMesh getMesh() {
        return mesh;
    }


    public Chunk(Renderer renderer, Vector3f position) {
        this.renderer = renderer;
        this.position = position;
        chunk = new Block[CHUNK_LENGTH][CHUNK_HEIGHT][CHUNK_WIDTH];
        for(int i = 0; i < CHUNK_WIDTH; i++) {
            for(int j = 0; j < CHUNK_HEIGHT; j++) {
                for(int k = 0; k < CHUNK_LENGTH; k++) {
                    if(j == CHUNK_HEIGHT-1) {
                        chunk[i][j][k] = Block.GRASS;
                    } else if (j >= 253){
                        chunk[i][j][k] = Block.DIRT;
                    } else {
                        chunk[i][j][k] = Block.STONE;
                    }
                }
            }
        }

        mesh = new ChunkMesh(this);
        mesh.create();
    }

    public void render(Camera camera) {
        renderer.renderChunk(mesh, camera, position);
    }
}
