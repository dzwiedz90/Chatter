package messages;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendMessage {
    private String message;
    private String name;
    private String host;

    public SendMessage(String messageIn, String nameIn, String hostIn) {
        this.message = messageIn;
        this.name = nameIn;
        this.host = hostIn;

        sendMessage();
    }

    private void sendMessage() {
        try {
            Socket socket = new Socket(this.host, 7777);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
            PrintWriter outToSocket = new PrintWriter(bufferedOutputStream, false);

            outToSocket.println(name);
            outToSocket.println(message);
            outToSocket.flush();
            outToSocket.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Blad polaczenia");
            System.exit(1);
        }
    }

}
