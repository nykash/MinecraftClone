package com.nrd.minecraft.blocks;

import com.nrd.engine.graphics.Material;
import com.nrd.engine.maths.Face;
import com.nrd.engine.maths.Vector2f;
import org.newdawn.slick.opengl.Texture;

import java.util.HashMap;

public class Textures {
    public static HashMap<Block, HashMap<Face, Vector2f>> textureMap = new HashMap<>();


    public static void load() {
        Vector2f dirt_idx = new Vector2f(2, 5);
        HashMap<Face, Vector2f> dirt_map = new HashMap<>();
        dirt_map.put(Face.BACK, dirt_idx);
        dirt_map.put(Face.FRONT, dirt_idx);
        dirt_map.put(Face.TOP, dirt_idx);
        dirt_map.put(Face.BOTTOM, dirt_idx);
        dirt_map.put(Face.LEFT, dirt_idx);
        dirt_map.put(Face.RIGHT, dirt_idx);
        textureMap.put(Block.DIRT, dirt_map);

        Vector2f side_grass_idx = new Vector2f(2, 15);
        Vector2f top_grass_idx = new Vector2f(3, 15);
        Vector2f bottom_grass_idx = new Vector2f(2, 5);
        HashMap<Face, Vector2f> grass_map = new HashMap<>();
        grass_map.put(Face.BACK, side_grass_idx);
        grass_map.put(Face.FRONT, side_grass_idx);
        grass_map.put(Face.TOP, top_grass_idx);
        grass_map.put(Face.BOTTOM, bottom_grass_idx);
        grass_map.put(Face.LEFT, side_grass_idx);
        grass_map.put(Face.RIGHT, side_grass_idx);
        textureMap.put(Block.GRASS, grass_map);

        Vector2f stone_idx = new Vector2f(3, 5);
        HashMap<Face, Vector2f> stone_map = new HashMap<>();
        stone_map.put(Face.BACK, stone_idx);
        stone_map.put(Face.FRONT, stone_idx);
        stone_map.put(Face.TOP, stone_idx);
        stone_map.put(Face.BOTTOM, stone_idx);
        stone_map.put(Face.LEFT, stone_idx);
        stone_map.put(Face.RIGHT, stone_idx);
        textureMap.put(Block.STONE, stone_map);
    }

}
