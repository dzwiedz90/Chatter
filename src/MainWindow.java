import javax.swing.*;
import java.awt.*;

public class MainWindow {
    protected JFrame mainWindow;
    protected JTextArea displayForMessages;
    protected JTextField sendMessageField;
    protected JButton sendMessageButton;

    public MainWindow() {
        super();
        mainWindow = new JFrame("Chatter");
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setSize(500, 300);
        BorderLayout layout = new BorderLayout();
        mainWindow.setLayout(layout);

        Menu menuBar = new Menu();
        mainWindow.setJMenuBar(Menu.menuBar);
        addDisplayForMessages();
        addSendMessageField();

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

        displayPane.add(displayForMessages);
        mainWindow.add(displayPane, BorderLayout.NORTH);
    }

    private void addSendMessageField() {
        JPanel sendMessagePanel = new JPanel();
        sendMessageField = new JTextField(38);
        sendMessageButton = new JButton("Send");
        sendMessagePanel.add(sendMessageField);
        sendMessagePanel.add(sendMessageButton);
        mainWindow.add(sendMessagePanel, BorderLayout.SOUTH);
    }
}
