import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StellarIDE implements ActionListener {
    JMenuBar menuBar;
    public JTextPane  editor;
    JMenu file, edit, prettier, settingsMenu;
    JFrame frame;
    JTabbedPane tabbedPane;
    int i = 0;

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
        menuBar.add(prettier);
        menuBar.add(settingsMenu, BorderLayout.SOUTH);
        
        // Set the menu bar for the frame
        frame.setJMenuBar(menuBar);
        
        // Initialize the tabbed pane and add it to the center of the BorderLayout
        tabbedPane = new JTabbedPane();
        editor = new JTextPane ();
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

        // Syntax Highlighter
        editor.addKeyListener( (KeyListener) new SyntaxHighlighter(){
                @SuppressWarnings("unused")
                public void keyTyped(KeyEvent e) { 
                   // syntaxHighlight();
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
                i++;
                tabbedPane.add("Untitled "+i, new JTextPane());
            }
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
        prettier = new JMenu("prettier");
        settingsMenu = new JMenu("Settings");

        // Initialize menu items
        JMenuItem newTab = new JMenuItem("New Tab");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As");
        JMenuItem exit = new JMenuItem("Exit");

        // Add menu items to the File menu
        file.add(newTab);
        file.add(open);
        file.add(save);
        file.add(saveAs);  
        file.add(exit);

        // Add action listeners to the menu items
        newTab.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        saveAs.addActionListener(this);
        exit.addActionListener(this);

   }
}
