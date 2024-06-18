
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;

public class CleverNote extends Frame
{
    MenuBar menuBar;
    TextArea textArea;
    Menu file, edit, view, settingsMenu;
    
    public CleverNote()
    {
        setSize(500, 500);
        setVisible(true);
        setTitle("CleverNote");
        setLayout(new BorderLayout());

        // Initialize the menu bar and menus
        menuBar = new MenuBar();
        file = new Menu("File");
        edit = new Menu("Edit");
        view = new Menu("View");
        settingsMenu = new Menu("Settings");

        // Initialize menu items
        MenuItem newtab = new MenuItem("New Tab");
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");
        MenuItem saveas = new MenuItem("Save As");
        MenuItem exit = new MenuItem("Exit");

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
        setMenuBar(menuBar);

        // Initialize the text area and add it to the center of the BorderLayout
        textArea = new TextArea();
        textArea.setSize(getPreferredSize());
        add(textArea, BorderLayout.CENTER);
    }
}
