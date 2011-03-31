/*
* vertex shader template
*/
//void main() {
    // Vertex transformation 
//    gl_Position = ftransform();
//}

void main()
{
    vec3 position = vec3(gl_ModelViewMatrix * gl_Vertex);
    vec3 normal = normalize(gl_NormalMatrix * gl_Normal);
    vec3 light = normalize(gl_LightSource[0].position.xyz - position);
    float diffuse = dot(light, normal);

    gl_FrontColor = gl_FrontLightProduct[0].ambient;
    vec4 lighting = vec4(0, 0, 0, 1);
    if (diffuse > 0.0) {
        //vec3 view = normalize(position);
        //vec3 halfway = normalize(light - view);
        //float specular = pow(max(dot(normal, halfway), 0.0), gl_FrontMaterial.shininess);
        lighting += gl_FrontLightProduct[0].diffuse * diffuse;
        //+ gl_FrontLightProduct[0].specular * specular;
    }

    vec3 light1 = normalize(gl_LightSource[1].position.xyz - position);
    float diffuse1 = dot(light1, normal);
    if (diffuse1 > 0.0) {
        lighting += gl_FrontLightProduct[1].diffuse * diffuse1;
    }
    vec3 light2 = normalize(gl_LightSource[2].position.xyz - position);
    float diffuse2 = dot(light2, normal);
    if (diffuse2 > 0.0) {
        lighting += gl_FrontLightProduct[2].diffuse * diffuse2;
    }
    vec3 light3 = normalize(gl_LightSource[3].position.xyz - position);
    float diffuse3 = dot(light3, normal);
    if (diffuse3 > 0.0) {
        lighting += gl_FrontLightProduct[3].diffuse * diffuse3;
    }
    vec3 light4 = normalize(gl_LightSource[4].position.xyz - position);
    float diffuse4 = dot(light4, normal);
    if (diffuse4 > 0.0) {
        lighting += gl_FrontLightProduct[4].diffuse * diffuse4;
    }
    gl_FrontColor += lighting * gl_Color;
    gl_FrontColor.a = gl_Color.a;

    gl_Position = ftransform();
}
