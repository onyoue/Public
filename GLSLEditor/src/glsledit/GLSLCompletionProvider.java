/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package glsledit;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionResultSet;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.AsyncCompletionQuery;
import org.netbeans.spi.editor.completion.support.AsyncCompletionTask;
import org.openide.util.Exceptions;

/**
 *
 * @author onoue
 */
public class GLSLCompletionProvider implements CompletionProvider {

    static String[] glslKeywords = {
        "gl_FragColor", "gl_TexCoord", "gl_Position", "gl_MultiTexCoord0"
    };

    public GLSLCompletionProvider() {
    }

    @Override
    public CompletionTask createTask(int type, JTextComponent jtc) {

        if (type != CompletionProvider.COMPLETION_QUERY_TYPE) {
            return null;
        }

        final JTextComponent jTextComponent = jtc;
        final int caretPos = jtc.getCaretPosition();

        return new AsyncCompletionTask(new AsyncCompletionQuery() {

            @Override
            protected void query(CompletionResultSet completionResultSet, Document document, int caretOffset) {

                String filter = null;
                caretOffset = caretPos;
                int startOffset = caretOffset - 1;

                try {
                    final StyledDocument bDoc = (StyledDocument) jTextComponent.getDocument();
                    final int lineStartOffset = getRowFirstNonWhite(bDoc, caretOffset);
                    final char[] line = bDoc.getText(lineStartOffset, caretOffset - lineStartOffset).toCharArray();
                    final int whiteOffset = indexOfWhite(line);
                    filter = new String(line, whiteOffset + 1, line.length - whiteOffset - 1);
                    if (whiteOffset > 0) {
                        startOffset = lineStartOffset + whiteOffset + 1;
                    } else {
                        startOffset = lineStartOffset;
                    }
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                }

                //Iterate through the available locales
                //and assign each country display name
                //to a CompletionResultSet:
                for (int i = 0; i < glslKeywords.length; i++) {
                    //Here we test whether the country starts with the filter defined above:
                    if (!glslKeywords[i].equals("") && glslKeywords[i].startsWith(filter)) {
                        //Here we include the start offset, so that we'll be able to figure out
                        //the number of characters that we'll need to remove:
                        completionResultSet.addItem(new GLSLCompletionItem(glslKeywords[i], startOffset, caretOffset));
                    }
                }
                completionResultSet.finish();

//                crs.addItem(new GLSLCompletionItem("gl_FragColor", caretOffset));
//                crs.addItem(new GLSLCompletionItem("gl_TexCoord", caretOffset));
//                crs.addItem(new GLSLCompletionItem("gl_Position", caretOffset));
//                crs.addItem(new GLSLCompletionItem("gl_MultiTexCoord0", caretOffset));

                //Iterate through the available locales
                //and assign each country display name
                //to a CompletionResultSet:
//                Locale[] locales = Locale.getAvailableLocales();
//                for (int i = 0; i < locales.length; i++) {
//                    final Locale locale = locales[i];
//                    final String country = locale.getDisplayCountry();
//                    if (!country.equals("")) {
//                        crs.addItem(new GLSLCompletionItem(country, caretOffset));
//                    }
//                }
//                crs.finish();
            }
        });

    }

    @Override
    public int getAutoQueryTypes(JTextComponent jtc, String string) {
        return 0;
    }

    static int getRowFirstNonWhite(StyledDocument doc, int offset)
            throws BadLocationException {
        Element lineElement = doc.getParagraphElement(offset);
        int start = lineElement.getStartOffset();
        while (start + 1 < lineElement.getEndOffset()) {
            try {
                if (doc.getText(start, 1).charAt(0) != ' ') {
                    break;
                }
            } catch (BadLocationException ex) {
                throw (BadLocationException) new BadLocationException(
                        "calling getText(" + start + ", " + (start + 1)
                        + ") on doc of length: " + doc.getLength(), start).initCause(ex);
            }
            start++;
        }
        return start;
    }

    static int indexOfWhite(char[] line) {
        int i = line.length;
        while (--i > -1) {
            final char c = line[i];
            if (Character.isWhitespace(c)) {
                return i;
            }
        }
        return -1;
    }
}
