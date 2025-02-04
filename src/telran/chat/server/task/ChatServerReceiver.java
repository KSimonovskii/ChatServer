package telran.chat.server.task;

import telran.chat.server.mediation.BlkQueue;
import telran.chat.server.mediation.BlkQueueImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatServerReceiver implements Runnable{

    private final Socket socket;
    private final BlkQueue<String> messageBox;

    public ChatServerReceiver(Socket socket, BlkQueue<String> messageBox) {
        this.socket = socket;
        this.messageBox = messageBox;
    }

    @Override
    public void run() {
        try (Socket socket = this.socket){
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true){
                String msg = socketReader.readLine();
                if (msg == null){
                    System.out.println("Connection " + socket.getInetAddress().getHostAddress() + ": " + socket.getPort() + " is closed");
                    break;
                }
                messageBox.push(msg);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
