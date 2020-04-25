package configuration;

import javax.swing.*;
import java.awt.*;

public class EditConfiguration extends JFrame {
    JFrame configurationFrame;

    public EditConfiguration() {
        configurationFrame = new JFrame("Edit configuration");
        configurationFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        configurationFrame.setSize(300, 400);
        addConfigurationFields();

        configurationFrame.setVisible(true);
        centerWindowOnScreen();
    }

    private void centerWindowOnScreen() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        configurationFrame.setLocation(dim.width / 2 - configurationFrame.getSize().width / 2, dim.height / 2 - configurationFrame.getSize().height / 2);
    }

    private void addConfigurationFields() {
        JPanel configurationFieldsPane = new JPanel();

        GridLayout layout = new GridLayout(3,2);

        JPanel namePane = new JPanel();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        namePane.add(nameLabel);
        namePane.add(nameField);

        JPanel hostPane = new JPanel();
        JLabel hostLabel = new JLabel("Host: ");
        JTextField hostField = new JTextField(15);
        hostPane.add(hostLabel);
        hostPane.add(hostField);

        JPanel themePane = new JPanel();
        JLabel themeLabel = new JLabel("Theme:");
        JTextField themeField = new JTextField(14);
        themePane.add(themeLabel);
        themePane.add(themeField);

        configurationFieldsPane.add(namePane);
        configurationFieldsPane.add(hostPane);
        configurationFieldsPane.add(themePane);

        configurationFrame.add(configurationFieldsPane);
    }
}
