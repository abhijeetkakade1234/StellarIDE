import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

// This class is used to open a file and display its content in Stellar IDE
public class FileSystem {
    

    public FileSystem() {
        JFileChooser fileChooser = new JFileChooser();

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            try{
                // file is not java file then throw error
                if (selectedFile.getName().endsWith(".class")) {
                    throw new IOException();
                }

                String content = new String(Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath())));
                
                // Create a new TextPane and display the content
                JTextPane newEditor = new JTextPane();
                newEditor.setText(content);
                
                // Create a new ScrollPane for the TextPane
                JScrollPane scrollPane = new JScrollPane(newEditor);
                scrollPane.setOpaque(true);
                scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setHorizontalScrollBarPolicy(
                javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                
                // Add the new TextPane to the tabbed pane in Stellarium IDE
                // Assuming you have a reference to the tabbedPane in Stellarium IDE
                StellarIDE.tabbedPane.add(selectedFile.getName(), scrollPane);
                SyntaxHighlighter.applySyntaxHighlight(newEditor);
                SwingUtilities.invokeLater(() -> StellarIDE.addSyntaxHighlighting(newEditor));

                
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
            }
        }
    }
}
