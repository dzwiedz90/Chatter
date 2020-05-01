package mainWindow;

import configuration.ReadConfiguration;
import messages.ReceiveMessage;
import messages.SendMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {
    protected JFrame mainWindow;
    protected JTextArea displayForMessages;
    protected JTextField sendMessageField;
    protected JButton sendMessageButton;
    String name;
    String host;

    public MainWindow() {
        super();
        mainWindow = new JFrame("Chatter");
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setSize(500, 300);
        BorderLayout layout = new BorderLayout();
        mainWindow.setLayout(layout);

        Menu menuBar = new Menu(this);
        mainWindow.setJMenuBar(Menu.menuBar);
        addDisplayForMessages();
        addSendMessageField();
        readFromConfiguration();
        ReceiveMessage receiver = new ReceiveMessage();
        receiver.start();

        mainWindow.setVisible(true);
        centerWindowOnScreen();
    }

    private void centerWindowOnScreen() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainWindow.setLocation(dim.width / 2 - mainWindow.getSize().width / 2, dim.height / 2 - mainWindow.getSize().height / 2);
    }

    private void addDisplayForMessages() {
        JPanel displayPane = new JPanel();
        displayForMessages = new JTextArea(10, 45);
        displayForMessages.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayForMessages, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        displayPane.add(scrollPane);
        mainWindow.add(displayPane, BorderLayout.NORTH);
    }

    private void addSendMessageField() {
        JPanel sendMessagePanel = new JPanel();
        sendMessageField = new JTextField(38);
        sendMessageButton = new JButton("Send");
        sendMessageButton.addActionListener(this);
        sendMessagePanel.add(sendMessageField);
        sendMessagePanel.add(sendMessageButton);
        mainWindow.add(sendMessagePanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == sendMessageButton) {
            displayForMessages.append(this.name + ": " + sendMessageField.getText() + "\n");
            sendMessageField.setText("");
            sendMessageField.requestFocus();
            SendMessage sendMessage = new SendMessage(sendMessageField.getText(), this.name, this.host);
        }
    }

    public void readFromConfiguration() {
        ReadConfiguration configuration = new ReadConfiguration();
        this.name = configuration.getName();
        this.host = configuration.getHost();
    }
}
