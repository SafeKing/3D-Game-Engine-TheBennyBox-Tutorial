#version 330

layout (location = 0) in vec3 position;

out vec4 colour;

uniform mat4 transform;

void main() {
	colour = vec4(clamp(position, 0.0, 1.0), 1.0);
	gl_Position = transform * vec4(position, 1.0);
	}