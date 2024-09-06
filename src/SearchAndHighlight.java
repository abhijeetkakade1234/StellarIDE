import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;


public class SearchAndHighlight {
    static Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.BLUE);

    /**
     * Highlights the occurrences of a specified text within a JTextPane.
     *
     * @param textPane the JTextPane to search and highlight in
     * @param text     the text to search for and highlight
     * @return         void, no return value
     */
    public static void findAndHighlight(JTextPane textPane, String text) {
        Highlighter highlighter = textPane.getHighlighter();
        highlighter.removeAllHighlights(); // remove all previous highlights

        String textContent = textPane.getText();
        int index = 0;
        while ((index = textContent.indexOf(text, index)) != -1) {
            try {
                int end = index + text.length();
                highlighter.addHighlight(index, end, painter);
                index = end;
            } catch (BadLocationException ex) {
                index++;
            }
        }
    }

    /**
     * Searches for a specified string within a JTextPane, highlights the found text, and replaces it with another string.
     *
     * @param textPane  the JTextPane to search, highlight and replace in
     * @param findText  the text to search for and highlight
     * @param replaceText the text to replace with
     * @return          void, no return value
     */

    /* BUG: Its finding the text form findAndHighlight method and has the 
    count of the text but it does not replace the text instead add the replaceText
    number of time text is found */
    public static void searchHighlightAndReplace(JTextPane textPane, String findText, String replaceText) {
        // first find the text and highlight it
        findAndHighlight(textPane, findText);

        // then replace the text
        String textContent = textPane.getText();
        int index = 0;
        while ((index = textContent.indexOf(findText, index)) != -1) {
            int end = index + findText.length();
            textPane.replaceSelection(replaceText);
            index = end;
        }
    }

}