package com.nrd.engine.io;

import com.nrd.engine.maths.Matrix4f;
import com.nrd.engine.maths.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Window {
    private int width, height;
    private String title;
    private long window;
    public Input input;
    private Vector3f background;
    private GLFWWindowSizeCallback sizeCallback;
    private boolean isResized, isFullScreened;
    private int[] windowPosX = new int[1], windowPosY = new int[1];
    private Matrix4f projection;

    public static float FOV = 70f;
    public static float NEAR = 0.2f;
    public static float FAR = 256;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFullScreened() {
        return isFullScreened;
    }

    public Matrix4f getProjectionMatrix() {
        return projection;
    }

    public void setFullScreened(boolean fullScreened) {
        isFullScreened = fullScreened;
        isResized = true;

    }

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;

        projection = Matrix4f.projection(FOV, (float) width/ (float) height, NEAR, FAR);
    }

    public void create() {
        if(!GLFW.glfwInit()) {
            System.err.println("ERROR: GLFW WAS NOT INITIALIZED");
            return;
        }

        window = GLFW.glfwCreateWindow(width, height, title,
                isFullScreened? GLFW.glfwGetPrimaryMonitor() : 0,0);
        input = new Input();

        if (window == 0) {
            System.err.println("ERROR: GLFW WAS NOT INITIALIZED");
            return;
        }

        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

        windowPosX[0] = (videoMode.width() - width)/2;
        windowPosY[0] = (videoMode.height() - height)/2;

        GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        createCallbacks();

        GLFW.glfwShowWindow(window);

        GLFW.glfwSwapInterval(1);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void destroy() {
        input.destroy();
        sizeCallback.free();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();

    }

    public void update() {
        if(isResized) {
            GL11.glViewport(0, 0, width, height);
            isResized = false;

            if(isFullScreened) {
                GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
                GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, width, height, 0);
            } else {
                GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], width, height, 0);
            }
        }

        GL11.glClearColor(background.getX(), background.getY(), background.getZ(), 1f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GLFW.glfwPollEvents();
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }

    private void createCallbacks() {
        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        GLFW.glfwSetScrollCallback(window, input.getMouseScrollCallback());

        sizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int w, int h) {
                width = width;
                height = height;
                isResized = true;
            }
        };

        GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
    }

    public void mouseState(boolean lock) {
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, lock? GLFW.GLFW_CURSOR_DISABLED:GLFW.GLFW_CURSOR_NORMAL);
    }

    public void setBackgroundColor(Vector3f color) {
        background = color;
    }

}
