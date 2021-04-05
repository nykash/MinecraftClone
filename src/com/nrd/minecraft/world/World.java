package com.nrd.minecraft.world;

import com.nrd.engine.graphics.Renderer;
import com.nrd.engine.maths.Vector3f;
import com.nrd.engine.objects.Camera;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class World {
    public ArrayList<Chunk> chunks;
    private Renderer renderer;
    public static final int CHUNK_RADIUS = 1;

    public World(Renderer renderer) {
        this.renderer = renderer;
        chunks = new ArrayList<>();
        for(int i = -CHUNK_RADIUS/2; i < CHUNK_RADIUS; i++) {
            for(int j = -CHUNK_RADIUS/2; j < CHUNK_RADIUS; j++) {
                chunks.add(new Chunk(renderer, new Vector3f(i*Chunk.CHUNK_WIDTH, -Chunk.CHUNK_HEIGHT, j*Chunk.CHUNK_LENGTH)));
            }
        }
    }

    public void render(Camera player) {
        for(int i = 0; i < chunks.size(); i++) {
            chunks.get(i).render(player);
        }
    }
}
