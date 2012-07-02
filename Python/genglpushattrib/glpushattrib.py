
# input = """
#      //glDisable(GL_DEPTH_TEST);
#      glDisable(GL_LIGHTING);
#      glEnable(GL_BLEND);
#      glDepthMask(GL_FALSE);
#      glPolygonOffset( 0.6f, 0.6f );
#      glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
#      glColor4ub(255, 255, 255, 128);
# """

def splitToTokens(l):
    l = l.replace('(', ' ')
    l = l.replace(')', ' ')
    l = l.replace(',', ' ')
    l = l.replace(';', ' ')
    tokens = [t for t in l.split(' ') if len(t) > 0]
    return tokens

flagMap = {
    "glEnable": "GL_ENABLE_BIT",
    "glDisable": "GL_ENABLE_BIT",
    "glClearAccum": "GL_ACCUM_BUFFER_BIT",
    "glAlphaFunc": "GL_COLOR_BUFFER_BIT",
    "glBlendFunc": "GL_COLOR_BUFFER_BIT",
    "glClearColor": "GL_COLOR_BUFFER_BIT",
    "glDrawBuffer": "GL_COLOR_BUFFER_BIT",
    "glLogicOp": "GL_COLOR_BUFFER_BIT",
    "glClearDepth": "GL_DEPTH_BUFFER_BIT",
    "glDepthFunc": "GL_DEPTH_BUFFER_BIT",
    "glDepthMask": "GL_DEPTH_BUFFER_BIT",
    "glShadeModel": "GL_LIGHTING_BIT",
    "glLineWidth": "GL_LINE_BIT",
    "glPointSize": "GL_POINT_BIT",
    "glPolygonOffset": "GL_POLYGON_BIT",
    "glCullFace": "GL_POLYGON_BIT",
    "glClipPlane": "GL_TRANSFORM_BIT",
    "glMatrixMode": "GL_TRANSFORM_BIT",
    "glDepthRange": "GL_VIEWPORT_BIT",
    "glViewport": "GL_VIEWPORT_BIT",
}

enableMap = {
   "GL_ALPHA_TEST": "GL_COLOR_BUFFER_BIT",
   "GL_BLEND": "GL_COLOR_BUFFER_BIT",
   "GL_DITHER": "GL_COLOR_BUFFER_BIT",
   "GL_LOGIC_OP": "GL_COLOR_BUFFER_BIT",
   "GL_LIGHTING": "GL_LIGHTING_BIT",
}


def generateGLPushAttrib(input):
    glArgs = set()
    for line in input.split(';'):
        l = line.strip(' \t\r\n')
        if not l.startswith('//') and len(l) > 0:
            tokens = splitToTokens(l)
            funcName = tokens[0]
            if (funcName == "glEnable" or funcName == "glDisable") and tokens[1] in enableMap:
                glArgs.add(enableMap[tokens[1]])
            elif funcName in flagMap:
                glArgs.add(flagMap[funcName])
            elif funcName.startswith("glColor"):
                glArgs.add("GL_CURRENT_BIT")
            elif funcName.startswith("glNormal"):
                glArgs.add("GL_CURRENT_BIT")
            elif funcName.startswith("glTexCoord"):
                glArgs.add("GL_CURRENT_BIT")
            elif funcName.startswith("glRasterPos"):
                glArgs.add("GL_CURRENT_BIT")
            elif funcName.startswith("glMaterial") or funcName.startswith("glLight"):
                glArgs.add("GL_LIGHTING_BIT")
            else:
                print "not supported: " + l

    output = ""
    for arg in glArgs:
        if len(output) == 0:
            output += "glPushAttrib(" + arg
        else:
            output += ' | ' + arg
    output += ")"
    #print output
    return output

# glArgs = set()
# for line in input.split(';'):
#     l = line.strip(' \t\r\n')
#     if not l.startswith('//') and len(l) > 0:
#         tokens = splitToTokens(l)
#         funcName = tokens[0]
#         if (funcName == "glEnable" or funcName == "glDisable") and tokens[1] in enableMap:
#             glArgs.add(enableMap[tokens[1]])
#         elif funcName in flagMap:
#             glArgs.add(flagMap[funcName])
#         elif funcName.startswith("glColor"):
#             glArgs.add("GL_CURRENT_BIT")
#         elif funcName.startswith("glNormal"):
#             glArgs.add("GL_CURRENT_BIT")
#         elif funcName.startswith("glTexCoord"):
#             glArgs.add("GL_CURRENT_BIT")
#         elif funcName.startswith("glRasterPos"):
#             glArgs.add("GL_CURRENT_BIT")
#         elif funcName.startswith("glMaterial") or funcName.startswith("glLight"):
#             glArgs.add("GL_LIGHTING_BIT")
#         else:
#             print "not supported: " + l

# output = ""
# for arg in glArgs:
#     if len(output) == 0:
#         output += "glPushAttrib(" + arg
#     else:
#         output += ' | ' + arg
# output += ")"
# print output

