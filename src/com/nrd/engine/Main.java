package com.nrd.engine;

import com.nrd.engine.graphics.*;
import com.nrd.engine.io.Input;
import com.nrd.engine.io.Window;
import com.nrd.engine.maths.Vector2f;
import com.nrd.engine.maths.Vector3f;
import com.nrd.engine.objects.Camera;
import com.nrd.engine.objects.GameObject;
import com.nrd.engine.objects.Player;
import com.nrd.minecraft.blocks.Textures;
import com.nrd.minecraft.world.Chunk;
import com.nrd.minecraft.world.World;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.CallbackI;

public class Main implements Runnable {
    public Thread game;
    public static Window window;
    public final int WIDTH=1280, HEIGHT=760;

    public Renderer renderer;
    public Shader shader;

    public World world;

    public Camera player = new Player(new Vector3f(0f, 0f, 0f));

    public void start() {
        game = new Thread(this, "game");
        game.start();
    }

    private void close() {
        window.destroy();
        shader.destroy();
    }

    public void init() {
        System.out.println("starting game");
        window = new Window(WIDTH, HEIGHT, "Minecreate");

        window.setBackgroundColor(new Vector3f(0.529f, 0.808f, 0.922f));
        window.create();
        window.mouseState(true);

        shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
        shader.create();

        Textures.load();

        renderer = new Renderer(window, shader);
        world = new World(renderer);

        GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MAG_FILTER, GL30.GL_NEAREST);
        GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MIN_FILTER, GL30.GL_NEAREST);
    }

    public void run() {
        init();

        while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE) ) {

            update();
            render();

        }
        close();
    }

    private void render() {
        world.render(player);
        window.swapBuffers();
    }

    private void update() {
        window.update();
        player.update();
    }

    public static void main(String[] args) {

        new Main().start();

    }
}
