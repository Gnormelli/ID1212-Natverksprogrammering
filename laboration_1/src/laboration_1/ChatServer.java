package laboration_1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ChatServer{
    private ServerSocket serverSocket;

    public ChatServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void startServer(){
        try {
            while(!serverSocket.isClosed()){

                Socket socket = serverSocket.accept();
                System.out.println("New user connected");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch(IOException exception){

        }
    }
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1337);
        ChatServer server = new ChatServer(serverSocket);
        server.startServer();

    }
}
