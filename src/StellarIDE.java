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
    JMenuItem darkMode, exit, lightMode, newTab, open, save, saveAs;

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

    /**
     * A method to handle various actions based on the action event triggered.
     *
     * @param  e   the action event that occurred
     * @return     void, no return value
     */
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

                    // scroll bar for the new editor
                    JScrollPane scrollPane = new JScrollPane(newEditor);
                    scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    // Add the new editor to the tabbed pane
                    tabbedPane.add(editorName + ".java", scrollPane);
                    addSyntaxHighlighting(newEditor);
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect file name", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
            // Handle themes
            case "Dark Mode" -> themeChange("dark");
            case "Light Mode" -> themeChange("light");
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
            default -> {} // Do nothing
        }
    }

    /**
     * Initialize the menu bar, menus, and menu items.
     *
     * @param  None
     * @return         	void
     */
    private  void initialize() {
        // Initialize the menu bar and menus
        menuBar = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        // prettier = new JButton("Prettier");
        settingsMenu = new JMenu("Settings");
        
        themes = new JMenu("Themes");
        // Initialize menu items for the File menu
        newTab = new JMenuItem("New Tab");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveAs = new JMenuItem("Save As");
        exit = new JMenuItem("Exit");

        // Initialize menu items for the Theme menu
        darkMode = new JMenuItem("Dark Mode");
        lightMode = new JMenuItem("Light Mode");

        // Add menu items to the File menu
        file.add(newTab);
        file.add(open);
        file.add(save);
        file.add(saveAs);  
        file.add(exit);

        // Add menu items to the Theme menu
        settingsMenu.add(themes);
        themes.add(darkMode);
        themes.add(lightMode);

        // Add action listeners to the menu items
        newTab.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        saveAs.addActionListener(this);
        exit.addActionListener(this);
        darkMode.addActionListener(this);
        lightMode.addActionListener(this);
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
     * @param theme the theme to apply ("dark" or "white")
     */
    private void themeChange(String theme) {
        switch (theme) {
            case "dark" -> {
                applyTheme(new Color(30, 30, 30), Color.WHITE);
            }
            case "light" ->{
                applyTheme(Color.WHITE, Color.BLACK);
            } 
            default -> {} // Do nothing
        }
    }

    /**
     * Update the background and foreground colors of components
     *
     * @param  backgroundColor	Color for the background
     * @param  foregroundColor	Color for the foreground
     * @return         	void
     */
    private void applyTheme(Color backgroundColor, Color foregroundColor) {

        // Update the background and foreground colors of components
        menuBar.setBackground(backgroundColor);
        menuBar.setForeground(foregroundColor);
        settingsMenu.setBackground(backgroundColor);
        settingsMenu.setForeground(foregroundColor);
        themes.setBackground(backgroundColor);
        themes.setForeground(foregroundColor);
        tabbedPane.setBackground(backgroundColor);
        tabbedPane.setForeground(foregroundColor);
        file.setBackground(backgroundColor);
        file.setForeground(foregroundColor);
        edit.setBackground(backgroundColor);
        edit.setForeground(foregroundColor);
        newTab.setBackground(backgroundColor);
        newTab.setForeground(foregroundColor);
        open.setBackground(backgroundColor);
        open.setForeground(foregroundColor);
        save.setBackground(backgroundColor);
        save.setForeground(foregroundColor);        
        saveAs.setBackground(backgroundColor);
        saveAs.setForeground(foregroundColor);
        exit.setBackground(backgroundColor);
        exit.setForeground(foregroundColor);
        darkMode.setBackground(backgroundColor);
        darkMode.setForeground(foregroundColor);
        lightMode.setBackground(backgroundColor);
        lightMode.setForeground(foregroundColor);


        // Update the background color of the frame
        frame.setBackground(backgroundColor);

        // Apply the background color to all open tabs
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            JTextPane editor = (JTextPane) tabbedPane.getComponentAt(i);
            editor.setBackground(backgroundColor);
        }

        // Update the background color of new tabs and the frame
        frame.repaint();
        frame.revalidate();
    }
}
