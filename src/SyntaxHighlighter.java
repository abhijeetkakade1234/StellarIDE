public class SyntaxHighlighter {
    
    public static void syntaxHighlight() {
           // check in textArea  for syntax
           String[] otherKeywords = {
            "abstract", "assert", "break", "case", "catch", "class", "const", 
            "continue", "default", "do", "else", "enum", "extends", "final", 
            "finally", "for", "goto", "if", "implements", "import", "instanceof", 
            "interface", "native", "new", "null", "package", "private", 
            "protected", "public", "return", "static", "strictfp", "super", 
            "switch", "synchronized", "this", "throw", "throws", "transient", 
            "try", "void", "volatile", "while"
        };
        
           String[] dataTypes = {
            "byte", "short", "int", "long", "float", "double", "char", "boolean"
        };

        String[] nonPrimitiveTypes = {
            "Byte", "Short", "Integer", "Long", "Float", "Double", "Character", "Boolean", 
            "String", "Object", "Class", "Enum", "Array", "List", "Set", "Map", "Queue", 
            "Deque", "Iterator", "Stream", "Optional", "Thread", "Runnable", "Exception", 
            "Error", "Throwable", "Annotation", "Field", "Method", "Constructor"
        };
        
        String[] collectionsClasses = {
            "HashMap", "ArrayList", "LinkedList", "HashSet", "TreeMap", "TreeSet",
            "LinkedHashMap", "LinkedHashSet", "PriorityQueue", "ArrayDeque", "Vector"
        };
        
        String[] booleanLiterals = {
            "true", "false"
        };

        String[] logicalOperators = {
            "!", "&", "|", "^", "~", "&&", "||", "?", ":"
        };
        
        
    }
}
