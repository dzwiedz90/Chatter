package configuration;

import mainWindow.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class EditConfiguration extends JFrame implements ActionListener {
    JFrame configurationFrame;
    JTextField nameField;
    JTextField hostField;
    String name;
    String host;
    JButton saveConfigurationButton;
    MainWindow mainWindow;

    public EditConfiguration(MainWindow mainWindowIn) {
        this.mainWindow = mainWindowIn;
        configurationFrame = new JFrame("Edit configuration");
        configurationFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        configurationFrame.setSize(300, 200);

        ReadConfiguration configuration = new ReadConfiguration();
        this.name = configuration.getName();
        this.host = configuration.getHost();

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
        BorderLayout layout = new BorderLayout();
        configurationFieldsPane.setLayout(layout);

        JPanel namePane = new JPanel();
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(15);
        nameField.setText(this.name);
        namePane.add(nameLabel);
        namePane.add(nameField);

        JPanel hostPane = new JPanel();
        JLabel hostLabel = new JLabel("Host: ");
        hostField = new JTextField(15);
        hostField.setText(this.host);
        hostPane.add(hostLabel);
        hostPane.add(hostField);

        JPanel savePanel = new JPanel();
        saveConfigurationButton = new JButton("Save");
        saveConfigurationButton.addActionListener(this);
        savePanel.add(saveConfigurationButton);

        configurationFieldsPane.add(namePane, BorderLayout.NORTH);
        configurationFieldsPane.add(hostPane, BorderLayout.CENTER);
        configurationFieldsPane.add(savePanel, BorderLayout.SOUTH);

        configurationFrame.add(configurationFieldsPane);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == saveConfigurationButton) {
            this.name = nameField.getText();
            this.host = hostField.getText();
            saveConfiguration();
        }
    }

    private void saveConfiguration() {
        try {
            PrintWriter out = new PrintWriter("configuration.txt");
            out.println(this.name);
            out.println(this.host);

            out.close();
            configurationFrame.dispose();
            mainWindow.readFromConfiguration();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
