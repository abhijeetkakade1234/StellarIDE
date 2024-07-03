import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StellarIDE implements ActionListener {
    JMenuBar menuBar;
    public JTextArea textArea;
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
        
        // Add menus to the menu bar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(prettier);
        menuBar.add(settingsMenu, BorderLayout.SOUTH);

        // Add action listeners to the menu items
        newTab.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        saveAs.addActionListener(this);
        exit.addActionListener(this);

        // Set the menu bar for the frame
        frame.setJMenuBar(menuBar);

        // Initialize the tabbed pane and add it to the center of the BorderLayout
        tabbedPane = new JTabbedPane();
        textArea = new JTextArea();
        tabbedPane.add("Tab 1", textArea);
        frame.add(tabbedPane, BorderLayout.CENTER);

        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        if (source.getText().equals("New Tab")) {
            i++;
            tabbedPane.add("Untitled "+i, new JTextArea());
        } else if (source.getText().equals("Open")) {
            // Handle open action
        } else if (source.getText().equals("Save")) {
            // Handle save action
        } else if (source.getText().equals("Save As")) {
            // Handle save as action
        } else if (source.getText().equals("Exit")) {
            frame.dispose(); // Close the application
        }
    }
}
