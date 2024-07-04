import java.awt.Color;
import java.util.regex.*;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

public class SyntaxHighlighter {
    
    // check in textArea  for syntax
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
        "\\b(!|&|\\||\\^|~|&&|\\|\\||\\?|:)\\b");
        
        
    public static void applySyntaxHighlight(JTextPane textPane) {


        // Apply styles for each pattern
        /* applyStyles(doc, text, KEYWORDS_PATTERN, Color.RED);
        applyStyles(doc, text, DATA_TYPES_PATTERN, Color.BLUE);
        applyStyles(doc, text, NON_PRIMITIVE_TYPES_PATTERN, Color.GREEN);
        applyStyles(doc, text, COLLECTIONS_CLASSES_PATTERN, Color.CYAN);
        applyStyles(doc, text, BOOLEAN_LITERALS_PATTERN, Color.MAGENTA);
        applyStyles(doc, text, LOGICAL_OPERATORS_PATTERN, Color.DARK_GRAY); */
    }

    private static void applyStyle(StyledDocument doc, String text,Pattern pattern, Color color) {
        Matcher matcher = pattern.matcher(text);
        Style style = doc.addStyle("", null);

        while (matcher.find()) {
            doc.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), style, false);
        }
    }
}
