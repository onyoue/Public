/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package glsledit.lexer;

import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;
import glsledit.jcclexer.JavaCharStream;
import glsledit.jcclexer.JavaParserTokenManager;
import glsledit.jcclexer.Token;

class GLSLLexer implements Lexer<GLSLTokenId> {

    private LexerRestartInfo<GLSLTokenId> info;
    private JavaParserTokenManager javaParserTokenManager;


    GLSLLexer (LexerRestartInfo<GLSLTokenId> info) {
        this.info = info;
        JavaCharStream stream = new JavaCharStream (info.input ());
        javaParserTokenManager = new JavaParserTokenManager (stream);
    }

    public org.netbeans.api.lexer.Token<GLSLTokenId> nextToken () {
        Token token = javaParserTokenManager.getNextToken ();
        if (info.input ().readLength () < 1) return null;
        return info.tokenFactory ().createToken (GLSLLanguageHierarchy.getToken (token.kind));
    }

    public Object state () {
        return null;
    }

    public void release () {
    }
}
