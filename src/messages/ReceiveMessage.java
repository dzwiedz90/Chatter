package messages;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiveMessage extends Thread {

    public void run() {
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(7777);
                System.out.println("Waiting for message...");
                Socket socket = null;
                while ((socket = serverSocket.accept()) != null) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    boolean eof = false;
                    while (!eof) {
                        String line = in.readLine();
                        if (line != null) {
                            System.out.println(line);
                        } else {
                            eof = true;
                        }
                    }
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

