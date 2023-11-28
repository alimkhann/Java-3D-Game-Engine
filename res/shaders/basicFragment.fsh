#version 330

in vec2 texCoord0;

uniform sampler2D sampler_;

out vec4 fragColor;

void main() {
    fragColor = texture(sampler_, texCoord0.xy);
}