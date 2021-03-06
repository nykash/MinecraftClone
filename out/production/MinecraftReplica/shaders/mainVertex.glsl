#version 460 core

in vec3 position;
in vec2 textureCoord;

out vec2 passTextureCoord;

uniform mat4 model;
uniform mat4 projection;
uniform mat4 view;

void main() {
    gl_Position = projection * view * model * vec4(position, 1.0);
    passTextureCoord = textureCoord;
}