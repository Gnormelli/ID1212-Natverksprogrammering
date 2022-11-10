package laboration_1;

import java.net.Socket;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandler = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ClientHandler(Socket serverSocket) {
        try {
            socket = serverSocket;
            bufferedWriter = new BufferedWriter(OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream))
        }

    }

    @Override
    public void run() {

    }
}
