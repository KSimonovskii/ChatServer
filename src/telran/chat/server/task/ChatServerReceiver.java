package telran.chat.server.task;

import telran.model.Message;
import telran.chat.server.mediation.BlkQueue;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ChatServerReceiver implements Runnable{

    private final Socket socket;
    private final BlkQueue<Message> messageBox;

    public ChatServerReceiver(Socket socket, BlkQueue<Message> messageBox) {
        this.socket = socket;
        this.messageBox = messageBox;
    }

    @Override
    public void run() {
        try (Socket socket = this.socket){
            ObjectInputStream socketInput = new ObjectInputStream(socket.getInputStream());

            while (true){
                Message msg = (Message) socketInput.readObject();
                if (msg == null){
                    System.out.println("Connection " + socket.getInetAddress().getHostAddress() + ": " + socket.getPort() + " is closed");
                    break;
                }
                messageBox.push(msg);
            }
        } catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }

    }
}
