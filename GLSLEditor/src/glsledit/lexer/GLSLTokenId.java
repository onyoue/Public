/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package glsledit.lexer;

import org.netbeans.api.lexer.Language;
import org.netbeans.api.lexer.TokenId;

/**
 *
 * @author onoue
 */
public class GLSLTokenId implements TokenId {

    private final String        name;
    private final String        primaryCategory;
    private final int           id;

    GLSLTokenId (
        String                  name,
        String                  primaryCategory,
        int                     id
    ) {
        this.name = name;
        this.primaryCategory = primaryCategory;
        this.id = id;
    }

    @Override
    public String primaryCategory () {
        return primaryCategory;
    }

    @Override
    public int ordinal () {
        return id;
    }

    @Override
    public String name () {
        return name;
    }
    
    private static final Language<GLSLTokenId> language = new GLSLLanguageHierarchy ().language ();

    public static final Language<GLSLTokenId> getLanguage () {
        return language;
    }

}
