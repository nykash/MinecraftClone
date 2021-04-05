package com.nrd.engine.graphics;

import com.nrd.engine.maths.Vector2f;
import org.lwjgl.system.CallbackI;
import org.lwjglx.Sys;

public class TextureAtlas extends Material {

    private int rows, columns;
    private float cellWidth, cellHeight;

    public TextureAtlas(String path, int rows, int columns) {
        super(path);
        this.rows = rows;
        this.columns = columns;

        this.create();

        cellWidth = texture.getWidth()/columns;
        cellHeight = texture.getHeight()/rows;
    }

    public Vector2f trans(Vector2f coord, int col, int row) {
        Vector2f res = new Vector2f(0, 0);
        float x_off = col/(float)(columns);
        float y_off = row/(float)(rows);

        res.setX(coord.getX()/columns+x_off);
        res.setY(coord.getY()/rows+y_off);

        return res;
    }

}
