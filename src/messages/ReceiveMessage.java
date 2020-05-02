package messages;


import mainWindow.MainWindow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiveMessage extends Thread {
    MainWindow mainWindow;

    public ReceiveMessage(MainWindow mainWindowIn)
    {
        this.mainWindow = mainWindowIn;
    }

    public void run() {
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(7777);
                System.out.println("Waiting for message...");
                Socket socket = null;
                String[] receivedMessage = new String[2];
                while ((socket = serverSocket.accept()) != null) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    boolean eof = false;
                    int i = 0;
                    while (!eof) {
                        String line = in.readLine();
                        if (line != null) {
                            receivedMessage[i] = line;
                            i++;
                        } else {
                            eof = true;
                        }
                    }
                    this.mainWindow.displayReceivedMessage(receivedMessage[0], receivedMessage[1]);
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

