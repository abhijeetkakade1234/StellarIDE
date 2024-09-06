import java.awt.Color;
import java.util.regex.*;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

// this class is working but not as we wanted!!!!!!!!!!!!!!!!!!!
// BUG: Fix required after first line syntax highlighting is improper
public class SyntaxHighlighter{
    
    // Add a color for default text
    public static Color DEFAULT_TEXT_COLOR = new Color(212, 212, 212); // Light gray

    // Existing patterns (they look good, no changes needed)
    private static final String[] keywords = {"abstract", "assert", "break", "case", "catch", "class", "const", "continue", "default", "do", "else", "enum", "extends", "final", "finally", "for", "goto", "if", "implements", "import", "instanceof", "interface", "native", "new", "null", "package", "private", "protected", "public", "return", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while"};

    private static final String[] dataTypes = {"byte", "short", "int", "long", "float", "double", "char", "boolean"};

    private static final String[] nonPrimitiveTypes = {"Byte", "Short", "Integer", "Long", "Float", "Double", "Character", "Boolean", "String", "Object", "Class", "Enum", "Array", "List", "Set", "Map", "Queue", "Deque", "Iterator", "Stream", "Optional", "Thread", "Runnable", "Exception", "Error", "Throwable", "Annotation", "Field", "Method", "Constructor"};

    private static final String[] collectionClasses = {"HashMap", "ArrayList", "LinkedList", "HashSet", "TreeMap", "TreeSet", "LinkedHashMap", "LinkedHashSet", "PriorityQueue", "ArrayDeque", "Vector"};

    private static final String[] booleanLiterals = {"true", "false"};

    // LOGICAL_OPERATORS_PATTERN
    private static final String[] logicalOperators = {"!", "&&", "||", "^", "~", "?", ":", "&", "|"};


    /**
     * Applies syntax highlighting to the given JTextPane.
     *
     * @param  textPane  the JTextPane to apply syntax highlighting to
     */
    public static void applySyntaxHighlight(JTextPane textPane) {
        
        StyledDocument doc = textPane.getStyledDocument();
        String text = textPane.getText();
        Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        doc.setCharacterAttributes(0, text.length(), defaultStyle, true);

        // Set default text color
        StyleConstants.setForeground(defaultStyle, DEFAULT_TEXT_COLOR);
        doc.setCharacterAttributes(0, text.length(), defaultStyle, true);


        // Apply styles in the correct order && Colors for syntax highlighting
        
       
        applyStyles(doc, text, keywords, new Color(197, 134, 192), textPane); // Soft purple
        applyStyles(doc, text, dataTypes, new Color(86, 156, 214), textPane); // Bright blue
        applyStyles(doc, text, nonPrimitiveTypes, new Color(78, 201, 176), textPane); // Cyan
        applyStyles(doc, text, collectionClasses, new Color(220, 220, 170), textPane); // Pale yellow
        applyStyles(doc, text, booleanLiterals, new Color(236, 118, 0), textPane); // Orange
        applyStyles(doc, text, logicalOperators, new Color(255, 123, 114), textPane); // Soft coral
        

        // Repaint the text pane
        textPane.repaint();
    }

    /**
     * Apply styles to the given text based on the specified pattern and color.
     *
     * @param  doc     the styled document to apply styles to
     * @param  text    the text to which styles are applied
     * @param  pattern the pattern to match in the text
     * @param  color   the color to set for the matched pattern
     */
     private static void applyStyles(StyledDocument doc, String text, String[] syntaxs, Color color, JTextPane textPane) {
        Style style = doc.addStyle("customStyle", null);
        StyleConstants.setForeground(style, color);

        for (String syntax : syntaxs) {
            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(syntax) + "\\b");
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                doc.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), style, false);
                textPane.repaint();
                textPane.revalidate();
            }
        }
    }
}
