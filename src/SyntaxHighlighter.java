import java.awt.Color;
import java.util.regex.*;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class SyntaxHighlighter{
    
    // Add a color for default text
    private static final Color DEFAULT_TEXT_COLOR = new Color(212, 212, 212); // Light gray

    // Existing patterns (they look good, no changes needed)
    private static final Pattern KEYWORDS_PATTERN = Pattern.compile(
        "\\b(abstract|assert|break|case|catch|class|const|continue|default|do|else|enum|extends|final|finally|for|goto|if|implements|import|instanceof|interface|native|new|null|package|private|protected|public|return|static|strictfp|super|switch|synchronized|this|throw|throws|transient|try|void|volatile|while)\\b");

    private static final Pattern DATA_TYPES_PATTERN = Pattern.compile(
        "\\b(byte|short|int|long|float|double|char|boolean)\\b");

    private static final Pattern NON_PRIMITIVE_TYPES_PATTERN = Pattern.compile(
        "\\b(Byte|Short|Integer|Long|Float|Double|Character|Boolean|String|Object|Class|Enum|Array|List|Set|Map|Queue|Deque|Iterator|Stream|Optional|Thread|Runnable|Exception|Error|Throwable|Annotation|Field|Method|Constructor)\\b");

    private static final Pattern COLLECTIONS_CLASSES_PATTERN = Pattern.compile(
        "\\b(HashMap|ArrayList|LinkedList|HashSet|TreeMap|TreeSet|LinkedHashMap|LinkedHashSet|PriorityQueue|ArrayDeque|Vector)\\b");

    private static final Pattern BOOLEAN_LITERALS_PATTERN = Pattern.compile(
        "\\b(true|false)\\b");

    private static final Pattern LOGICAL_OPERATORS_PATTERN = Pattern.compile(
        "(!|&&|\\|\\||\\^|~|\\?|:)|([^&|]&[^&|])|([^&|]\\|[^&|])");

    // New patterns to add
    private static final Pattern STRING_LITERAL_PATTERN = Pattern.compile(
        "\"([^\"\\\\]|\\\\.)*\"");

    private static final Pattern NUMERIC_LITERAL_PATTERN = Pattern.compile(
        "\\b([0-9]+\\.?[0-9]*|\\.?[0-9]+)([eE][-+]?[0-9]+)?[fFdDlL]?\\b|\\b0x[0-9a-fA-F]+\\b");

    private static final Pattern METHOD_CALL_PATTERN = Pattern.compile(
        "\\b[a-zA-Z_$][a-zA-Z_$0-9]*\\s*\\(");

    private static final Pattern COMMENT_PATTERN = Pattern.compile(
        "//.*|/\\*[\\s\\S]*?\\*/");


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
        applyStyles(doc, text, COMMENT_PATTERN, new Color(106, 153, 85)); // soft green
        applyStyles(doc, text, STRING_LITERAL_PATTERN, new Color(206, 145, 120)); // Soft orange
        applyStyles(doc, text, KEYWORDS_PATTERN, new Color(197, 134, 192)); // Soft purple
        applyStyles(doc, text, DATA_TYPES_PATTERN, new Color(86, 156, 214)); // Bright blue
        applyStyles(doc, text, NON_PRIMITIVE_TYPES_PATTERN, new Color(78, 201, 176)); // Cyan
        applyStyles(doc, text, COLLECTIONS_CLASSES_PATTERN, new Color(220, 220, 170)); // Pale yellow
        applyStyles(doc, text, BOOLEAN_LITERALS_PATTERN, new Color(236, 118, 0)); // Orange
        applyStyles(doc, text, LOGICAL_OPERATORS_PATTERN, new Color(255, 123, 114)); // Soft coral
        applyStyles(doc, text, NUMERIC_LITERAL_PATTERN, new Color(181, 206, 168)); // Light green
        applyStyles(doc, text, METHOD_CALL_PATTERN, new Color(230, 219, 116)); // Light yellow

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
    private static void applyStyles(StyledDocument doc, String text,Pattern pattern, Color color) {
        Matcher matcher = pattern.matcher(text);
        Style style = doc.addStyle("", null);
       StyleConstants.setForeground(style, color);

        while (matcher.find()) {
            doc.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), style, false);
        }
    }
}
