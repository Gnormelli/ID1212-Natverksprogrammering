package laboration_2;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller{
    private ServerSocket serverSocket;
    private BufferedReader inputReader;

    public Controller(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void startServer(){
        try {
            while(!serverSocket.isClosed()){

                Socket socket = serverSocket.accept();

                System.out.println("A new user has connected");
                ClientHandler clientHandler = new ClientHandler(socket);

                System.out.println(socket);

                /**
                this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inputLine;
                while (!(inputLine = this.inputReader.readLine()).equals(""))
                      System.out.println(inputLine);
                this.inputReader.close();
                 */
               // reqeustName = (from above)
               // if(requestName != "favoicon"){
                  //  Thread thread = new Thread(clientHandler);
                //    thread.start();
               // }

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch(IOException exception){
            System.out.println("Error in trying to start conecction");
            System.out.println(exception);
            closeSocket();
        }
    }

    public void closeSocket(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8000);
        Controller server = new Controller(serverSocket);
        server.startServer();

    }
}
