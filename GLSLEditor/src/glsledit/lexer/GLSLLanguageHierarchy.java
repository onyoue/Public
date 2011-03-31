/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package glsledit.lexer;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;
import glsledit.jcclexer.JavaParserConstants;

/**
 *
 * @author onoue
 */
public class GLSLLanguageHierarchy extends LanguageHierarchy<GLSLTokenId> {

    private static List<GLSLTokenId> tokens;
    private static Map<Integer, GLSLTokenId> idToToken;

    private static void init() {
        tokens = Arrays.<GLSLTokenId>asList(new GLSLTokenId[]{
                    //[PENDING]
                    new GLSLTokenId("EOF", "whitespace", JavaParserConstants.EOF),
                    new GLSLTokenId("WHITESPACE", "whitespace", JavaParserConstants.WHITESPACE),
                    new GLSLTokenId("SINGLE_LINE_COMMENT", "comment", JavaParserConstants.SINGLE_LINE_COMMENT),
                    new GLSLTokenId("FORMAL_COMMENT", "comment", JavaParserConstants.FORMAL_COMMENT),
                    new GLSLTokenId("MULTI_LINE_COMMENT", "comment", JavaParserConstants.MULTI_LINE_COMMENT),
                    new GLSLTokenId("ABSTRACT", "keyword", JavaParserConstants.ABSTRACT),
                    new GLSLTokenId("ASSERT", "keyword", JavaParserConstants.ASSERT),
                    new GLSLTokenId("ATTRIBUTE", "keyword", JavaParserConstants.ATTRIBUTE),
                    new GLSLTokenId("AUTO", "keyword", JavaParserConstants.AUTO),
                    new GLSLTokenId("BOOLEAN", "keyword", JavaParserConstants.BOOLEAN),
                    new GLSLTokenId("BREAK", "keyword", JavaParserConstants.BREAK),
                    new GLSLTokenId("BYTE", "keyword", JavaParserConstants.BYTE),
                    new GLSLTokenId("CASE", "keyword", JavaParserConstants.CASE),
                    new GLSLTokenId("CATCH", "keyword", JavaParserConstants.CATCH),
                    new GLSLTokenId("CENTROID", "keyword", JavaParserConstants.CENTROID),
                    new GLSLTokenId("CHAR", "keyword", JavaParserConstants.CHAR),
                    new GLSLTokenId("CLASS", "keyword", JavaParserConstants.CLASS),
                    new GLSLTokenId("CONST", "keyword", JavaParserConstants.CONST),
                    new GLSLTokenId("CONTINUE", "keyword", JavaParserConstants.CONTINUE),
                    new GLSLTokenId("_DEFAULT", "keyword", JavaParserConstants._DEFAULT),
                    new GLSLTokenId("DO", "keyword", JavaParserConstants.DO),
                    new GLSLTokenId("DOUBLE", "keyword", JavaParserConstants.DOUBLE),
                    new GLSLTokenId("ELSE", "keyword", JavaParserConstants.ELSE),
                    new GLSLTokenId("ENUM", "keyword", JavaParserConstants.ENUM),
                    new GLSLTokenId("EXTENDS", "keyword", JavaParserConstants.EXTENDS),
                    new GLSLTokenId("FALSE", "keyword", JavaParserConstants.FALSE),
                    new GLSLTokenId("FINAL", "keyword", JavaParserConstants.FINAL),
                    new GLSLTokenId("FINALLY", "keyword", JavaParserConstants.FINALLY),
                    new GLSLTokenId("FLAT", "keyword", JavaParserConstants.FLAT),
                    new GLSLTokenId("FOR", "keyword", JavaParserConstants.FOR),
                    new GLSLTokenId("GOTO", "keyword", JavaParserConstants.GOTO),
                    new GLSLTokenId("IF", "keyword", JavaParserConstants.IF),
                    new GLSLTokenId("IMPLEMENTS", "keyword", JavaParserConstants.IMPLEMENTS),
                    new GLSLTokenId("IMPORT", "keyword", JavaParserConstants.IMPORT),
                    new GLSLTokenId("IN", "keyword", JavaParserConstants.IN),
                    new GLSLTokenId("INOUT", "keyword", JavaParserConstants.INOUT),
                    new GLSLTokenId("INSTANCEOF", "keyword", JavaParserConstants.INSTANCEOF),
                    new GLSLTokenId("INTERFACE", "keyword", JavaParserConstants.INTERFACE),
                    new GLSLTokenId("INVARIANT", "keyword", JavaParserConstants.INVARIANT),
                    new GLSLTokenId("LONG", "keyword", JavaParserConstants.LONG),
                    new GLSLTokenId("NATIVE", "keyword", JavaParserConstants.NATIVE),
                    new GLSLTokenId("NEW", "keyword", JavaParserConstants.NEW),
                    new GLSLTokenId("NONPERSPECTIVE", "keyword", JavaParserConstants.NONPERSPECTIVE),
                    new GLSLTokenId("NULL", "keyword", JavaParserConstants.NULL),
                    new GLSLTokenId("OUT", "keyword", JavaParserConstants.OUT),
                    new GLSLTokenId("PACKAGE", "keyword", JavaParserConstants.PACKAGE),
                    new GLSLTokenId("PRIVATE", "keyword", JavaParserConstants.PRIVATE),
                    new GLSLTokenId("PROTECTED", "keyword", JavaParserConstants.PROTECTED),
                    new GLSLTokenId("PUBLIC", "keyword", JavaParserConstants.PUBLIC),
                    new GLSLTokenId("RETURN", "keyword", JavaParserConstants.RETURN),
                    new GLSLTokenId("SHORT", "keyword", JavaParserConstants.SHORT),
                    new GLSLTokenId("SMOOTH", "keyword", JavaParserConstants.SMOOTH),
                    new GLSLTokenId("STATIC", "keyword", JavaParserConstants.STATIC),
                    new GLSLTokenId("STRICTFP", "keyword", JavaParserConstants.STRICTFP),
                    new GLSLTokenId("SUPER", "keyword", JavaParserConstants.SUPER),
                    new GLSLTokenId("SWITCH", "keyword", JavaParserConstants.SWITCH),
                    new GLSLTokenId("SYNCHRONIZED", "keyword", JavaParserConstants.SYNCHRONIZED),
                    new GLSLTokenId("THIS", "keyword", JavaParserConstants.THIS),
                    new GLSLTokenId("THROW", "keyword", JavaParserConstants.THROW),
                    new GLSLTokenId("THROWS", "keyword", JavaParserConstants.THROWS),
                    new GLSLTokenId("TRANSIENT", "keyword", JavaParserConstants.TRANSIENT),
                    new GLSLTokenId("TRUE", "keyword", JavaParserConstants.TRUE),
                    new GLSLTokenId("TRY", "keyword", JavaParserConstants.TRY),
                    new GLSLTokenId("UNIFORM", "keyword", JavaParserConstants.UNIFORM),
                    new GLSLTokenId("VARYING", "keyword", JavaParserConstants.VARYING),
                    new GLSLTokenId("VOID", "keyword", JavaParserConstants.VOID),
                    new GLSLTokenId("VOLATILE", "keyword", JavaParserConstants.VOLATILE),
                    new GLSLTokenId("WHILE", "keyword", JavaParserConstants.WHILE),
                    new GLSLTokenId("BOOL", "keyword", JavaParserConstants.BOOL),
                    new GLSLTokenId("BVEC2", "type", JavaParserConstants.BVEC2),
                    new GLSLTokenId("BVEC3", "type", JavaParserConstants.BVEC3),
                    new GLSLTokenId("BVEC4", "type", JavaParserConstants.BVEC4),
                    new GLSLTokenId("INT", "type", JavaParserConstants.INT),
                    new GLSLTokenId("IVEC2", "type", JavaParserConstants.IVEC2),
                    new GLSLTokenId("IVEC3", "type", JavaParserConstants.IVEC3),
                    new GLSLTokenId("IVEC4", "type", JavaParserConstants.IVEC4),
                    new GLSLTokenId("FLOAT", "type", JavaParserConstants.FLOAT),
                    new GLSLTokenId("VEC2", "type", JavaParserConstants.VEC2),
                    new GLSLTokenId("VEC3", "type", JavaParserConstants.VEC3),
                    new GLSLTokenId("VEC4", "type", JavaParserConstants.VEC4),
                    new GLSLTokenId("MAT2", "type", JavaParserConstants.MAT2),
                    new GLSLTokenId("MAT3", "type", JavaParserConstants.MAT3),
                    new GLSLTokenId("MAT4", "type", JavaParserConstants.MAT4),
                    new GLSLTokenId("SAMPLER2D", "type", JavaParserConstants.SAMPLER2D),
                    new GLSLTokenId("SAMPLERCUBE", "type", JavaParserConstants.SAMPLERCUBE),
                    new GLSLTokenId("SAMPLER1D", "type", JavaParserConstants.SAMPLER1D),
                    new GLSLTokenId("SAMPLER1DSHADOW", "type", JavaParserConstants.SAMPLER1DSHADOW),
                    new GLSLTokenId("SAMPLER2DSHADOW", "type", JavaParserConstants.SAMPLER2DSHADOW),
                    new GLSLTokenId("SAMPLER3D", "type", JavaParserConstants.SAMPLER3D),
                    new GLSLTokenId("MAT2X3", "type", JavaParserConstants.MAT2X3),
                    new GLSLTokenId("MAT2X4", "type", JavaParserConstants.MAT2X4),
                    new GLSLTokenId("MAT3X2", "type", JavaParserConstants.MAT3X2),
                    new GLSLTokenId("MAT3X4", "type", JavaParserConstants.MAT3X4),
                    new GLSLTokenId("MAT4X2", "type", JavaParserConstants.MAT4X2),
                    new GLSLTokenId("MAT4X3", "type", JavaParserConstants.MAT4X3),
                    new GLSLTokenId("UINT", "type", JavaParserConstants.UINT),
                    new GLSLTokenId("UVEC2", "type", JavaParserConstants.UVEC2),
                    new GLSLTokenId("UVEC3", "type", JavaParserConstants.UVEC3),
                    new GLSLTokenId("UVEC4", "type", JavaParserConstants.UVEC4),
                    new GLSLTokenId("ISAMPLER1DARRAY", "type", JavaParserConstants.ISAMPLER1DARRAY),
                    new GLSLTokenId("USAMPLER1DARRAY", "type", JavaParserConstants.USAMPLER1DARRAY),
                    new GLSLTokenId("ISAMPLER2DARRAY", "type", JavaParserConstants.ISAMPLER2DARRAY),
                    new GLSLTokenId("USAMPLER2DARRAY", "type", JavaParserConstants.USAMPLER2DARRAY),
                    new GLSLTokenId("SAMPLERCUBESHADOW", "type", JavaParserConstants.SAMPLERCUBESHADOW),
                    new GLSLTokenId("ISAMPLER1D", "type", JavaParserConstants.ISAMPLER1D),
                    new GLSLTokenId("USAMPLER1D", "type", JavaParserConstants.USAMPLER1D),
                    new GLSLTokenId("ISAMPLER2D", "type", JavaParserConstants.ISAMPLER2D),
                    new GLSLTokenId("USAMPLER2D", "type", JavaParserConstants.USAMPLER2D),
                    new GLSLTokenId("ISAMPLER3D", "type", JavaParserConstants.ISAMPLER3D),
                    new GLSLTokenId("USAMPLER3D", "type", JavaParserConstants.USAMPLER3D),
                    new GLSLTokenId("ISAMPLERCUBE", "type", JavaParserConstants.ISAMPLERCUBE),
                    new GLSLTokenId("USAMPLERCUBE", "type", JavaParserConstants.USAMPLERCUBE),
                    new GLSLTokenId("SAMPLER2DRECT", "type", JavaParserConstants.SAMPLER2DRECT),
                    new GLSLTokenId("SAMPLER2DRECTSHADOW", "type", JavaParserConstants.SAMPLER2DRECTSHADOW),
                    new GLSLTokenId("SAMPLER1DARRAY", "type", JavaParserConstants.SAMPLER1DARRAY),
                    new GLSLTokenId("SAMPLER2DARRAY", "type", JavaParserConstants.SAMPLER2DARRAY),
                    new GLSLTokenId("SAMPLER1DARRAYSHADOW", "type", JavaParserConstants.SAMPLER1DARRAYSHADOW),
                    new GLSLTokenId("SAMPLER2DARRAYSHADOW", "type", JavaParserConstants.SAMPLER2DARRAYSHADOW),
                    new GLSLTokenId("SAMPLERBUFFER", "type", JavaParserConstants.SAMPLERBUFFER),
                    new GLSLTokenId("ISAMPLERBUFFER", "type", JavaParserConstants.ISAMPLERBUFFER),
                    new GLSLTokenId("USAMPLERBUFFER", "type", JavaParserConstants.USAMPLERBUFFER),
                    new GLSLTokenId("GL_POSITION", "builtin_variable", JavaParserConstants.GL_POSITION),
                    new GLSLTokenId("GL_POINTSIZE", "builtin_variable", JavaParserConstants.GL_POINTSIZE),
                    new GLSLTokenId("GL_FRAGCOORD", "builtin_variable", JavaParserConstants.GL_FRAGCOORD),
                    new GLSLTokenId("GL_FRONTFACING", "builtin_variable", JavaParserConstants.GL_FRONTFACING),
                    new GLSLTokenId("GL_FRAGCOLOR", "builtin_variable", JavaParserConstants.GL_FRAGCOLOR),
                    new GLSLTokenId("GL_FRAGDEPTH", "builtin_variable", JavaParserConstants.GL_FRAGDEPTH),
                    new GLSLTokenId("GL_VERTEX", "builtin_variable", JavaParserConstants.GL_VERTEX),
                    new GLSLTokenId("GL_NORMAL", "builtin_variable", JavaParserConstants.GL_NORMAL),
                    new GLSLTokenId("GL_COLOR", "builtin_variable", JavaParserConstants.GL_COLOR),
                    new GLSLTokenId("GL_SECONDARYCOLOR", "builtin_variable", JavaParserConstants.GL_SECONDARYCOLOR),
                    new GLSLTokenId("GL_MULTITEXCOORD0", "builtin_variable", JavaParserConstants.GL_MULTITEXCOORD0),
                    new GLSLTokenId("GL_MULTITEXCOORD1", "builtin_variable", JavaParserConstants.GL_MULTITEXCOORD1),
                    new GLSLTokenId("GL_MULTITEXCOORD2", "builtin_variable", JavaParserConstants.GL_MULTITEXCOORD2),
                    new GLSLTokenId("GL_MULTITEXCOORD3", "builtin_variable", JavaParserConstants.GL_MULTITEXCOORD3),
                    new GLSLTokenId("GL_MULTITEXCOORD4", "builtin_variable", JavaParserConstants.GL_MULTITEXCOORD4),
                    new GLSLTokenId("GL_MULTITEXCOORD5", "builtin_variable", JavaParserConstants.GL_MULTITEXCOORD5),
                    new GLSLTokenId("GL_MULTITEXCOORD6", "builtin_variable", JavaParserConstants.GL_MULTITEXCOORD6),
                    new GLSLTokenId("GL_MULTITEXCOORD7", "builtin_variable", JavaParserConstants.GL_MULTITEXCOORD7),
                    new GLSLTokenId("GL_FOGCOORD", "builtin_variable", JavaParserConstants.GL_FOGCOORD),
                    new GLSLTokenId("GL_CLIPVERTEX", "builtin_variable", JavaParserConstants.GL_CLIPVERTEX),
                    new GLSLTokenId("GL_FRONTCOLOR", "builtin_variable", JavaParserConstants.GL_FRONTCOLOR),
                    new GLSLTokenId("GL_BACKCOLOR", "builtin_variable", JavaParserConstants.GL_BACKCOLOR),
                    new GLSLTokenId("GL_FRONTSECONDARYCOLOR", "builtin_variable", JavaParserConstants.GL_FRONTSECONDARYCOLOR),
                    new GLSLTokenId("GL_BACKSECONDARYCOLOR", "builtin_variable", JavaParserConstants.GL_BACKSECONDARYCOLOR),
                    new GLSLTokenId("GL_FOGFRAGCOORD", "builtin_variable", JavaParserConstants.GL_FOGFRAGCOORD),
                    new GLSLTokenId("GL_POINTCOORD", "builtin_variable", JavaParserConstants.GL_POINTCOORD),
                    new GLSLTokenId("GL_VERTEXID", "builtin_variable", JavaParserConstants.GL_VERTEXID),
                    new GLSLTokenId("GL_MODELVIEWMATRIX", "builtin_variable", JavaParserConstants.GL_MODELVIEWMATRIX),
                    new GLSLTokenId("GL_PROJECTIONMATRIX", "builtin_variable", JavaParserConstants.GL_PROJECTIONMATRIX),
                    new GLSLTokenId("GL_MODELVIEWPROJECTIONMATRIX", "builtin_variable", JavaParserConstants.GL_MODELVIEWPROJECTIONMATRIX),
                    new GLSLTokenId("GL_NORMALMATRIX", "builtin_variable", JavaParserConstants.GL_NORMALMATRIX),
                    new GLSLTokenId("GL_MODELVIEWMATRIXINVERSE", "builtin_variable", JavaParserConstants.GL_MODELVIEWMATRIXINVERSE),
                    new GLSLTokenId("GL_PROJECTIONMATRIXINVERSE", "builtin_variable", JavaParserConstants.GL_PROJECTIONMATRIXINVERSE),
                    new GLSLTokenId("GL_MODELVIEWPROJECTIONMATRIXINVERSE", "builtin_variable", JavaParserConstants.GL_MODELVIEWPROJECTIONMATRIXINVERSE),
                    new GLSLTokenId("GL_MODELVIEWMATRIXTRANSPOSE", "builtin_variable", JavaParserConstants.GL_MODELVIEWMATRIXTRANSPOSE),
                    new GLSLTokenId("GL_PROJECTIONMATRIXTRANSPOSE", "builtin_variable", JavaParserConstants.GL_PROJECTIONMATRIXTRANSPOSE),
                    new GLSLTokenId("GL_MODELVIEWPROJECTIONMATRIXTRANSPOSE", "builtin_variable", JavaParserConstants.GL_MODELVIEWPROJECTIONMATRIXTRANSPOSE),
                    new GLSLTokenId("GL_MODELVIEWMATRIXINVERSETRANSPOSE", "builtin_variable", JavaParserConstants.GL_MODELVIEWMATRIXINVERSETRANSPOSE),
                    new GLSLTokenId("GL_PROJECTIONMATRIXINVERSETRANSPOSE", "builtin_variable", JavaParserConstants.GL_PROJECTIONMATRIXINVERSETRANSPOSE),
                    new GLSLTokenId("GL_MODELVIEWPROJECTIONMATRIXINVERSETRANSPOSE", "builtin_variable", JavaParserConstants.GL_MODELVIEWPROJECTIONMATRIXINVERSETRANSPOSE),
                    new GLSLTokenId("GL_NORMALSCALE", "builtin_variable", JavaParserConstants.GL_NORMALSCALE),
                    new GLSLTokenId("GL_LIGHTMODEL", "builtin_variable", JavaParserConstants.GL_LIGHTMODEL),
                    new GLSLTokenId("INTEGER_LITERAL", "literal", JavaParserConstants.INTEGER_LITERAL),
                    new GLSLTokenId("DECIMAL_LITERAL", "literal", JavaParserConstants.DECIMAL_LITERAL),
                    new GLSLTokenId("HEX_LITERAL", "literal", JavaParserConstants.HEX_LITERAL),
                    new GLSLTokenId("OCTAL_LITERAL", "literal", JavaParserConstants.OCTAL_LITERAL),
                    new GLSLTokenId("FLOATING_POINT_LITERAL", "literal", JavaParserConstants.FLOATING_POINT_LITERAL),
                    new GLSLTokenId("DECIMAL_FLOATING_POINT_LITERAL", "literal", JavaParserConstants.DECIMAL_FLOATING_POINT_LITERAL),
                    new GLSLTokenId("DECIMAL_EXPONENT", "number", JavaParserConstants.DECIMAL_EXPONENT),
                    new GLSLTokenId("HEXADECIMAL_FLOATING_POINT_LITERAL", "literal", JavaParserConstants.HEXADECIMAL_FLOATING_POINT_LITERAL),
                    new GLSLTokenId("HEXADECIMAL_EXPONENT", "number", JavaParserConstants.HEXADECIMAL_EXPONENT),
                    new GLSLTokenId("CHARACTER_LITERAL", "literal", JavaParserConstants.CHARACTER_LITERAL),
                    new GLSLTokenId("STRING_LITERAL", "literal", JavaParserConstants.STRING_LITERAL),
                    new GLSLTokenId("IDENTIFIER", "identifier", JavaParserConstants.IDENTIFIER),
                    new GLSLTokenId("LETTER", "literal", JavaParserConstants.LETTER),
                    new GLSLTokenId("PART_LETTER", "literal", JavaParserConstants.PART_LETTER),
                    new GLSLTokenId("LPAREN", "operator", JavaParserConstants.LPAREN),
                    new GLSLTokenId("RPAREN", "operator", JavaParserConstants.RPAREN),
                    new GLSLTokenId("LBRACE", "operator", JavaParserConstants.LBRACE),
                    new GLSLTokenId("RBRACE", "operator", JavaParserConstants.RBRACE),
                    new GLSLTokenId("LBRACKET", "operator", JavaParserConstants.LBRACKET),
                    new GLSLTokenId("RBRACKET", "operator", JavaParserConstants.RBRACKET),
                    new GLSLTokenId("SEMICOLON", "operator", JavaParserConstants.SEMICOLON),
                    new GLSLTokenId("COMMA", "operator", JavaParserConstants.COMMA),
                    new GLSLTokenId("DOT", "operator", JavaParserConstants.DOT),
                    new GLSLTokenId("AT", "operator", JavaParserConstants.AT),
                    new GLSLTokenId("ASSIGN", "operator", JavaParserConstants.ASSIGN),
                    new GLSLTokenId("LT", "operator", JavaParserConstants.LT),
                    new GLSLTokenId("BANG", "operator", JavaParserConstants.BANG),
                    new GLSLTokenId("TILDE", "operator", JavaParserConstants.TILDE),
                    new GLSLTokenId("HOOK", "operator", JavaParserConstants.HOOK),
                    new GLSLTokenId("COLON", "operator", JavaParserConstants.COLON),
                    new GLSLTokenId("EQ", "operator", JavaParserConstants.EQ),
                    new GLSLTokenId("LE", "operator", JavaParserConstants.LE),
                    new GLSLTokenId("GE", "operator", JavaParserConstants.GE),
                    new GLSLTokenId("NE", "operator", JavaParserConstants.NE),
                    new GLSLTokenId("SC_OR", "operator", JavaParserConstants.SC_OR),
                    new GLSLTokenId("SC_AND", "operator", JavaParserConstants.SC_AND),
                    new GLSLTokenId("INCR", "operator", JavaParserConstants.INCR),
                    new GLSLTokenId("DECR", "operator", JavaParserConstants.DECR),
                    new GLSLTokenId("PLUS", "operator", JavaParserConstants.PLUS),
                    new GLSLTokenId("MINUS", "operator", JavaParserConstants.MINUS),
                    new GLSLTokenId("STAR", "operator", JavaParserConstants.STAR),
                    new GLSLTokenId("SLASH", "operator", JavaParserConstants.SLASH),
                    new GLSLTokenId("BIT_AND", "operator", JavaParserConstants.BIT_AND),
                    new GLSLTokenId("BIT_OR", "operator", JavaParserConstants.BIT_OR),
                    new GLSLTokenId("XOR", "operator", JavaParserConstants.XOR),
                    new GLSLTokenId("REM", "operator", JavaParserConstants.REM),
                    new GLSLTokenId("LSHIFT", "operator", JavaParserConstants.LSHIFT),
                    new GLSLTokenId("PLUSASSIGN", "operator", JavaParserConstants.PLUSASSIGN),
                    new GLSLTokenId("MINUSASSIGN", "operator", JavaParserConstants.MINUSASSIGN),
                    new GLSLTokenId("STARASSIGN", "operator", JavaParserConstants.STARASSIGN),
                    new GLSLTokenId("SLASHASSIGN", "operator", JavaParserConstants.SLASHASSIGN),
                    new GLSLTokenId("ANDASSIGN", "operator", JavaParserConstants.ANDASSIGN),
                    new GLSLTokenId("ORASSIGN", "operator", JavaParserConstants.ORASSIGN),
                    new GLSLTokenId("XORASSIGN", "operator", JavaParserConstants.XORASSIGN),
                    new GLSLTokenId("REMASSIGN", "operator", JavaParserConstants.REMASSIGN),
                    new GLSLTokenId("LSHIFTASSIGN", "operator", JavaParserConstants.LSHIFTASSIGN),
                    new GLSLTokenId("RSIGNEDSHIFTASSIGN", "operator", JavaParserConstants.RSIGNEDSHIFTASSIGN),
                    new GLSLTokenId("RUNSIGNEDSHIFTASSIGN", "operator", JavaParserConstants.RUNSIGNEDSHIFTASSIGN),
                    new GLSLTokenId("ELLIPSIS", "operator", JavaParserConstants.ELLIPSIS),
                    new GLSLTokenId("RUNSIGNEDSHIFT", "operator", JavaParserConstants.RUNSIGNEDSHIFT),
                    new GLSLTokenId("RSIGNEDSHIFT", "operator", JavaParserConstants.RSIGNEDSHIFT),
                    new GLSLTokenId("GT", "operator", JavaParserConstants.GT),
        });
        idToToken = new HashMap<Integer, GLSLTokenId>();
        for (GLSLTokenId token : tokens) {
            idToToken.put(token.ordinal(), token);
        }
    }

    static synchronized GLSLTokenId getToken(int id) {
        if (idToToken == null) {
            init();
        }
        return idToToken.get(id);
    }

    protected synchronized Collection<GLSLTokenId> createTokenIds() {
        if (tokens == null) {
            init();
        }
        return tokens;
    }

    protected synchronized Lexer<GLSLTokenId> createLexer(LexerRestartInfo<GLSLTokenId> info) {
        return new GLSLLexer(info);
    }

    protected String mimeType() {
        return "text/x-glsl";
    }
}
