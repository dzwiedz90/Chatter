import configuration.EditConfiguration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    static JMenuBar menuBar;
    JMenuItem conf;
    JMenuItem exit;

    public Menu() {
        JMenuItem file = new JMenuItem("File");
        conf = new JMenuItem("Configuration");
        conf.addActionListener(this);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        JMenuItem about = new JMenuItem("About");

        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu aboutMenu = new JMenu("About");

        fileMenu.add(file);
        fileMenu.add(conf);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        menuBar.add(fileMenu);

        aboutMenu.add(about);
        menuBar.add(aboutMenu);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if(source == conf){
            EditConfiguration editConfiguration = new EditConfiguration();
        }
        if(source == exit){
            System.exit(0);
        }
    }
}
