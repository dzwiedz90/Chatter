package mainWindow;

import configuration.ReadConfiguration;
import messages.ReceiveMessage;
import messages.SendMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame implements ActionListener, KeyListener {
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
        ReceiveMessage receiver = new ReceiveMessage(this);
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
        sendMessageField.addKeyListener(this);
        sendMessageButton = new JButton("Send");
        sendMessageButton.addActionListener(this);
        sendMessagePanel.add(sendMessageField);
        sendMessagePanel.add(sendMessageButton);
        mainWindow.add(sendMessagePanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == sendMessageButton) {
            sendMessage();
        }
    }

    public void readFromConfiguration() {
        ReadConfiguration configuration = new ReadConfiguration();
        this.name = configuration.getName();
        this.host = configuration.getHost();
    }

    public void displayReceivedMessage(String name, String message) {
        displayForMessages.append(name + ": " + message + "\n");
    }

    public void keyPressed(KeyEvent event) {
        Object source = event.getKeyCode();
        if (source.equals(10)) {
            sendMessage();
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    private void sendMessage() {
        displayForMessages.append(this.name + ": " + sendMessageField.getText() + "\n");
        String toSend = sendMessageField.getText();
        sendMessageField.setText("");
        sendMessageField.requestFocus();
        SendMessage sendMessage = new SendMessage(toSend, this.name, this.host);
    }
}
