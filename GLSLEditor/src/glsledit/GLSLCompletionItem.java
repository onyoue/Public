/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package glsledit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.netbeans.api.editor.completion.Completion;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.netbeans.spi.editor.completion.support.CompletionUtilities;
import org.openide.util.Exceptions;
import org.openide.util.Utilities;

/**
 *
 * @author onoue
 */
public class GLSLCompletionItem  implements CompletionItem {

    private String text;
    private static ImageIcon fieldIcon =
            new ImageIcon(Utilities.loadImage("glsledit/glsl_icon.png"));
    private static Color fieldColor = Color.decode("0x0000B2");
    private int caretOffset;
    private int dotOffset;

    public GLSLCompletionItem(String text, int dotOffset, int caretOffset) {
        this.text = text;
        this.dotOffset = dotOffset;
        this.caretOffset = caretOffset;
    }
    
    @Override
    public void defaultAction(JTextComponent jtc) {
        StyledDocument doc = (StyledDocument) jtc.getDocument();
        try {
            doc.remove(dotOffset, jtc.getCaretPosition() - dotOffset);
            doc.insertString(jtc.getCaretPosition(), text, null);
            //doc.insertString(caretOffset, text, null);
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
        }
        //This statement will close the code completion box:
        Completion.get().hideAll();
    }

    @Override
    public void processKeyEvent(KeyEvent ke) {
    }

    @Override
    public int getPreferredWidth(Graphics grphcs, Font font) {
        return CompletionUtilities.getPreferredWidth(text, null, grphcs, font);
    }

    @Override
    public void render(Graphics g, Font defaultFont, Color defaultColor, Color backgroundColor, int width, int height, boolean selected) {
        CompletionUtilities.renderHtml(fieldIcon, text, null, g, defaultFont,
            (selected ? Color.white : fieldColor), width, height, selected);
    }

    @Override
    public CompletionTask createDocumentationTask() {
        return null;
    }

    @Override
    public CompletionTask createToolTipTask() {
        return null;
    }

    @Override
    public boolean instantSubstitution(JTextComponent jtc) {
        return false;
    }

    @Override
    public int getSortPriority() {
        return 0;
    }

    @Override
    public CharSequence getSortText() {
        return text;
    }

    @Override
    public CharSequence getInsertPrefix() {
        return text;
    }

}
