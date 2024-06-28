
import java.awt.*;
import javax.swing.*;

public class CleverNote extends JFrame
{
    JMenuBar menuBar;
    JTextArea textArea;
    JMenu file, edit, view, settingsMenu;
    
    public CleverNote()
    {
        setSize(500, 500);
        setVisible(true);
        setTitle("CleverNote");
        setLayout(new BorderLayout());

        // Initialize the menu bar and menus
        menuBar = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        view = new JMenu("View");
        settingsMenu = new JMenu("Settings");

        // Initialize menu items
        JMenuItem newtab = new JMenuItem("New Tab");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveas = new JMenuItem("Save As");
        JMenuItem exit = new JMenuItem("Exit");

        // Add menu items to the File menu
        file.add(newtab);
        file.add(open);
        file.add(save);
        file.add(saveas);  
        file.add(exit);
        
        // Add menus to the menu bar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(view);
        menuBar.add(settingsMenu);




        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        // Initialize the text area and add it to the center of the BorderLayout
        textArea = new JTextArea();
        add(textArea, BorderLayout.CENTER);


         // Set default close operation
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
