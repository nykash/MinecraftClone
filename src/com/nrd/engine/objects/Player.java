package com.nrd.engine.objects;

import com.nrd.engine.maths.Vector3f;

public class Player extends Camera {
    public static float height=2f, width=0.2f, length=0.2f;
    public static float eye_height=1.8f, eye_width=0.1f, eye_length=0.1f;

    public Player(Vector3f position) {
        super(Vector3f.add(position, new Vector3f(eye_width, eye_height, eye_length)),
                new Vector3f(0,0,0));

    }
    
}
