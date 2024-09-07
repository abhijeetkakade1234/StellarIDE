import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentListener;

public class StellarIDE implements ActionListener {
    JMenuBar menuBar;
    JMenu file, edit, settingsMenu, themes;
    JFrame frame;
    public static JTabbedPane tabbedPane;
    Color backgroundColor = new Color(30, 30, 30); // Dark gray default background color
    JMenuItem darkMode, exit, lightMode, newTab, open, save, saveAs, find, replace;
    JScrollPane scrollPane;

    // File browser component00
    FileSystem fileSystem;

    public StellarIDE() {
        frame = new JFrame("Stellar IDE");
        frame.setSize(800, 800);
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
     * @param e the action event that occurred
     * @return void, no return value
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        // using ruleswitch which was released in Java 17
        switch (source.getText()) {
            case "New Tab" -> {
                String editorName = JOptionPane.showInputDialog(frame, "Save the file before creating a new tab",
                        "File Name", JOptionPane.PLAIN_MESSAGE);
                if (editorName != null && editorName.length() > 0) {
                    JTextPane newEditor = new JTextPane();
                    newEditor.setBackground(backgroundColor); // Very dark gray, almost black

                    // scroll bar for the new editor
                    scrollPane = new JScrollPane(newEditor);
                    scrollPane.setOpaque(true);
                    scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setHorizontalScrollBarPolicy(
                            javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
            // Handle find
            case "Find" -> {
                int selectedIndex = tabbedPane.getSelectedIndex();
                if (selectedIndex >= 0) {
                    JScrollPane selectedScrollPane = (JScrollPane) tabbedPane.getComponentAt(selectedIndex);
                    JViewport viewport = selectedScrollPane.getViewport();
                    JTextPane currentEditor = (JTextPane) viewport.getView();

                    String findText = JOptionPane.showInputDialog(frame, "Find the word", "Find",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (findText != null && findText.length() > 0) {
                        SearchAndHighlight.findAndHighlight(currentEditor, findText);
                    } else {
                        JOptionPane.showMessageDialog(frame, "No text found!", "Alert", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "No tab is currently open.", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
            // Handle find and replace
            case "Replace" -> {
                int selectedIndex = tabbedPane.getSelectedIndex();
                if (selectedIndex >= 0) {
                    JScrollPane selectedScrollPane = (JScrollPane) tabbedPane.getComponentAt(selectedIndex);
                    JViewport viewport = selectedScrollPane.getViewport();
                    JTextPane currentEditor = (JTextPane) viewport.getView();

                    String findText = JOptionPane.showInputDialog(frame, "Find the word", "Find",
                            JOptionPane.OK_CANCEL_OPTION);
                    String replaceText = JOptionPane.showInputDialog(frame, "Replace with the word", "Replace",
                            JOptionPane.PLAIN_MESSAGE);
                    // if findText and replaceText are not null and not equal
                    if (!findText.equals(replaceText) && findText.length() > 0) {
                        SearchAndHighlight.searchHighlightAndReplace(currentEditor, findText, replaceText);
                    } else {
                        JOptionPane.showMessageDialog(frame, "No text found! Or word your looking for is the same", "Alert", JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(frame, "No tab is currently open.", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
            // Handle open action
            case "Open" -> {
                fileSystem = new FileSystem();
            }
            // Handle save action
            case "Save" -> {
            }
            // Handle save as action
            case "Save As" -> {
            }
            // save and exit is remaining
            case "Exit" -> frame.dispose(); // Close the application
            default -> {
            } // Do nothing
        }
    }

    /**
     * Initialize the menu bar, menus, and menu items.
     *
     * @param None
     * @return void
     */
    private void initialize() {
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

        // Initialize menu items for the Edit menu
        find = new JMenuItem("Find");
        replace = new JMenuItem("Replace");

        // Create file browser
  

        // Set the keyboard shortcut keys for the menu items.
        newTab.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); // new tab (Ctrl + N)
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)); // open (Ctrl + O)
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK)); // exit (Ctrl + X)
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK)); // save (Ctrl + S)
        saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK)); // save as (Ctrl + Shift + S)
        find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK)); // find (Ctrl + F)
        replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK)); // replace (Ctrl + R)

        // Initialize menu items for the Theme menu
        darkMode = new JMenuItem("Dark Mode");
        lightMode = new JMenuItem("Light Mode");

        // Add menu items to the File menu
        file.add(newTab);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(exit);

        // Add menu items to the Edit menu
        edit.add(find);
        edit.add(replace);

        // Add menu items to the Theme menu
        themes.add(darkMode);
        themes.add(lightMode);

        settingsMenu.add(themes);

        // Add action listeners to the menu items
        newTab.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        saveAs.addActionListener(this);
        exit.addActionListener(this);
        darkMode.addActionListener(this);
        lightMode.addActionListener(this);
        find.addActionListener(this);
        replace.addActionListener(this);
    }

    /**
     * Add syntax highlighting to the editor
     *
     * @param textPane JTextPane to apply syntax highlighting
     * @return void
     */
    public static void addSyntaxHighlighting(JTextPane textPane) {
        // Add syntax highlighting to the editor
        textPane.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                SwingUtilities.invokeLater(() -> SyntaxHighlighter.applySyntaxHighlight(textPane));

                /* when any key is pressed then this will not highlight word but 
                if any word was previosly highlighted then it will be removed 
                Text here use is Alt + 455 which is non visible  word as no will be using this character */ 
                SearchAndHighlight.findAndHighlight(textPane, "╟");
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                SwingUtilities.invokeLater(() -> SyntaxHighlighter.applySyntaxHighlight(textPane));
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                // this could solve the delay in syntax highlighting
                // not this ->>> SwingUtilities.invokeLater(() -> SyntaxHighlighter.applySyntaxHighlight(textPane));
            }
        });
    }

    /**
     * Apply the selected theme to the IDE, changing the background color of all
     * tabs.
     *
     * @param theme the theme to apply ("dark" or "white")
     */
    private void themeChange(String theme) {
        switch (theme) {
            case "dark" -> {
                applyTheme(new Color(30, 30, 30), Color.WHITE);
            }
            case "light" -> {
                applyTheme(Color.WHITE, Color.BLACK);
            }
            default -> {
            } // Do nothing
        }
    }

    /**
     * Update the background and foreground colors of components
     *
     * @param backgroundColor Color for the background
     * @param foregroundColor Color for the foreground
     * @return void
     */
    private void applyTheme(Color backgroundColor, Color foregroundColor) {
        // Update the background and foreground colors of components
        SyntaxHighlighter.DEFAULT_TEXT_COLOR = (backgroundColor.equals(Color.WHITE)) ? Color.BLACK : Color.WHITE;

        menuBar.remove(settingsMenu); // Remove the menu
        settingsMenu.remove(themes); // Remove the themes menu

        // Update the background and foreground colors of menus
        menuBar.setBackground(backgroundColor);
        menuBar.setForeground(foregroundColor);

        // Update the settings menu and its items
        updateMenuComponent(settingsMenu, backgroundColor, foregroundColor);

        // Update the file menu and its items
        updateMenuComponent(file, backgroundColor, foregroundColor);

        // Update the edit menu and its items
        updateMenuComponent(edit, backgroundColor, foregroundColor);

        // Update themes menu and its items
        updateMenuComponent(themes, backgroundColor, foregroundColor);

        // Update menu items
        updateMenuItem(newTab, backgroundColor, foregroundColor);
        updateMenuItem(open, backgroundColor, foregroundColor);
        updateMenuItem(save, backgroundColor, foregroundColor);
        updateMenuItem(saveAs, backgroundColor, foregroundColor);
        updateMenuItem(exit, backgroundColor, foregroundColor);
        updateMenuItem(darkMode, backgroundColor, foregroundColor);
        updateMenuItem(lightMode, backgroundColor, foregroundColor);
        updateMenuItem(find, backgroundColor, foregroundColor);
        updateMenuItem(replace, backgroundColor, foregroundColor);

        // Re-add menus
        settingsMenu.add(themes);
        menuBar.add(settingsMenu);

        // Update the background color of the frame
        frame.setBackground(backgroundColor);

        // Apply the background color to all open tabs
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            this.scrollPane = (JScrollPane) tabbedPane.getComponentAt(i);
            JViewport viewport = scrollPane.getViewport();
            Component view = viewport.getView();
            if (view instanceof JTextPane editor) {
                editor.setBackground(backgroundColor);
            }
        }

        // Set the menu bar again
        frame.setJMenuBar(menuBar);

        // Update the UI *************************************
        /*
         * telling Swing to recalculate and
         * redraw the entire UI tree for these components.
         * This is particularly important for complex components
         * like JMenus that have multiple layers and states.
         */
        SwingUtilities.updateComponentTreeUI(menuBar);
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            SwingUtilities.updateComponentTreeUI(menuBar.getMenu(i));
        }

        // Force components to repaint and revalidate
        frame.repaint();
        frame.revalidate();
    }

    /**
     * Updates the background color and foreground color of the given JComponent and
     * its children.
     *
     * @param component       the JComponent to update
     * @param backgroundColor the new background color
     * @param foregroundColor the new foreground color
     */
    private void updateMenuComponent(JComponent component, Color backgroundColor, Color foregroundColor) {
        component.setBackground(backgroundColor);
        component.setForeground(foregroundColor);
        if (component instanceof JMenu menu) {
            menu.getPopupMenu().setBackground(backgroundColor);
            menu.getPopupMenu().setForeground(foregroundColor);
        }
        for (Component child : component.getComponents()) {
            if (child instanceof JComponent jComponent) {
                updateMenuComponent(jComponent, backgroundColor, foregroundColor);
            }
        }
    }

    /**
     * A description of the entire Java function.
     *
     * @param menuItem        description of parameter
     * @param backgroundColor description of parameter
     * @param foregroundColor description of parameter
     * @return description of return value
     */
    private void updateMenuItem(JMenuItem menuItem, Color backgroundColor, Color foregroundColor) {
        menuItem.setBackground(backgroundColor);
        menuItem.setForeground(foregroundColor);
        menuItem.updateUI();
    }
}