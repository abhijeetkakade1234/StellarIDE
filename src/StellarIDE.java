import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentListener;

public class StellarIDE implements ActionListener {
    JMenuBar menuBar;
    JMenu file ,edit ,settingsMenu, themes; 
    JFrame frame;
    JTabbedPane tabbedPane; 
    Color backgroundColor = new Color(30, 30, 30); // Dark gray default background color
    

    public StellarIDE() {
        frame = new JFrame("Stellar IDE");
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setTitle("StellarIDE");
        frame.setLayout(new BorderLayout());

        // intialize the menu bar
        initialize();
        
        // Add menus to the menu bar
        menuBar.add(file);
        menuBar.add(edit);
        // menuBar.add(prettier);
        menuBar.add(settingsMenu);
        
        
        // Set the menu bar for the frame
        frame.setJMenuBar(menuBar);
        
        // Initialize the tabbed pane and add it to the center of the BorderLayout
        tabbedPane = new JTabbedPane();
        frame.add(tabbedPane, BorderLayout.CENTER);
        JPopupMenu popup = new JPopupMenu();
        JMenuItem closeTab = new JMenuItem("close tab");
        popup.add(closeTab);
        tabbedPane.setComponentPopupMenu(popup);
        // Close tab Event Listener
        closeTab.addActionListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            if (selectedIndex >= 0) {
                tabbedPane.remove(selectedIndex);
            }
        });
        
        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        // using ruleswitch which was released in Java 17
        switch (source.getText()) {
            case "New Tab" -> {
                String editorName = JOptionPane.showInputDialog(frame, "Save the file before creating a new tab", "File Name", JOptionPane.PLAIN_MESSAGE);
                if (editorName != null && editorName.length() > 0) {
                    JTextPane newEditor = new JTextPane();
                    newEditor.setBackground(backgroundColor); // Very dark gray, almost black
                    tabbedPane.add(editorName + ".java", newEditor);
                    addSyntaxHighlighting(newEditor);
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect file name", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
            // Handle themes
            case "Dark Mode" -> themeChange("dark");
            case "White Mode" -> themeChange("white");
            // Handle open action
            case "Open" -> {
            }
            // Handle save action
            case "Save" -> {
            }
            // Handle save as action
            case "Save As" -> {
            }
            case "Exit" -> frame.dispose(); // Close the application
            default -> {
                // do nothing
            }
        }
    }
    private  void initialize() {
        // Initialize the menu bar and menus
        menuBar = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        // prettier = new JButton("Prettier");
        settingsMenu = new JMenu("Settings");
        themes = new JMenu("Themes");

        // Initialize menu items for the File menu
        JMenuItem newTab = new JMenuItem("New Tab");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As");
        JMenuItem exit = new JMenuItem("Exit");

        // Initialize menu items for the Theme menu
        JMenuItem darkMode = new JMenuItem("Dark Mode");
        JMenuItem whiteMode = new JMenuItem("White Mode");

        // Add menu items to the File menu
        file.add(newTab);
        file.add(open);
        file.add(save);
        file.add(saveAs);  
        file.add(exit);

        // Add menu items to the Theme menu
        settingsMenu.add(themes);
        themes.add(darkMode);
        themes.add(whiteMode);

        // Add action listeners to the menu items
        newTab.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        saveAs.addActionListener(this);
        exit.addActionListener(this);
        darkMode.addActionListener(this);
        whiteMode.addActionListener(this);
   }

   /**
    * Add syntax highlighting to the editor
    *
    * @param  textPane	JTextPane to apply syntax highlighting
    * @return         	void
    */
   private void addSyntaxHighlighting(JTextPane textPane) {
    // Add syntax highlighting to the editor
        textPane.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                SwingUtilities.invokeLater(() -> SyntaxHighlighter.applySyntaxHighlight(textPane));
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                SwingUtilities.invokeLater(() -> SyntaxHighlighter.applySyntaxHighlight(textPane));
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                // Not needed for plain text components
            }
        });
    } 
    
    /**
     * Apply the selected theme to the IDE, changing the background color of all tabs.
     *
     * @param  theme   the theme to apply ("dark" or "white")
     * @return         void
     */
    private void themeChange(String theme) {
        if (theme.equals("dark")) {
            backgroundColor = new Color(30, 30, 30); // Dark gray
        } else if (theme.equals("white")) {
            backgroundColor = Color.WHITE; // White
        }

        // Apply the background color to all open tabs
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            JTextPane editor = (JTextPane) tabbedPane.getComponentAt(i);
            editor.setBackground(backgroundColor);
        }

        // Update the background color of new tabs
        frame.repaint();
    }
}
