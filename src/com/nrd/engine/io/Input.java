package com.nrd.engine.io;

import org.lwjgl.glfw.*;

public class Input {
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static double mouseX, mouseY;
    private static double scrollX, scrollY;

    private GLFWCursorPosCallback mouseMove;
    private GLFWMouseButtonCallback mouseButtons;
    private GLFWKeyCallback keyboard;
    private GLFWScrollCallback mouseScroll;

    public static double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

    public static double getScrollX() {
        return scrollX;
    }

    public static  double getScrollY() {
        return scrollY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    public GLFWKeyCallback getKeyboardCallback() {
        return keyboard;
    }

    public void setKeyboardCallback(GLFWKeyCallback keyboard) {
        this.keyboard = keyboard;
    }

    public GLFWCursorPosCallback getMouseMoveCallback() {
        return mouseMove;
    }

    public void setMouseMove(GLFWCursorPosCallback mouseMove) {
        this.mouseMove = mouseMove;
    }

    public GLFWMouseButtonCallback getMouseButtonsCallback() {
        return mouseButtons;
    }

    public void setMouseButtonsCallback(GLFWMouseButtonCallback mouseButtons) {
        this.mouseButtons = mouseButtons;
    }

    public GLFWScrollCallback getMouseScrollCallback() {
        return mouseScroll;
    }

    public Input() {
        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW.GLFW_RELEASE);
            }
        };

        mouseMove = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        };

        mouseButtons = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action != GLFW.GLFW_RELEASE);
            }
        };

        mouseScroll = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double xoffset, double yoffset) {
                scrollX += xoffset;
                scrollY += yoffset;
            }
        };

    }

    public void destroy() {
        keyboard.free();
        mouseMove.free();
        mouseButtons.free();
        mouseScroll.free();
    }

    public static boolean isKeyDown(int key) {
        return keys[key];
    }

    public static boolean isButtonDown(int button) {
        return buttons[button];
    }

}
