package configuration;

import javax.swing.*;
import java.awt.*;

public class EditConfiguration extends JFrame {
    JFrame configurationFrame;

    public EditConfiguration() {
        configurationFrame = new JFrame("Edit configuration");
        configurationFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        configurationFrame.setSize(300, 400);

        configurationFrame.setVisible(true);
        centerWindowOnScreen();
    }

    private void centerWindowOnScreen() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        configurationFrame.setLocation(dim.width / 2 - configurationFrame.getSize().width / 2, dim.height / 2 - configurationFrame.getSize().height / 2);
    }
}
