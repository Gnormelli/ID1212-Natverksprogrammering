package laboration_2;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Controller{
    private ServerSocket serverSocket;
    private BufferedReader inputReader;
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    public Controller(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void startServer(){
        try {
            while(!serverSocket.isClosed()){
                int guessInt = -1;
                String cookie = "nothing";
                Socket socket = serverSocket.accept();

                this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inputLine;
                inputLine = this.inputReader.readLine();
                if (!inputLine.contains("favicon")) {
                    if(inputLine.contains("guess")){
                        String result = inputLine.split("guess=")[1];
                        String guessString = result.split(" HTTP/")[0];
                        guessInt = Integer.parseInt(guessString);
                    }

                    while(!inputLine.equals(""))
                    {
                        System.out.println(inputLine);
                        if(inputLine.contains("Cookie ")){
                            System.out.println("Found coockie");
                            cookie = "cookie code";   // This needs to be implemented
                        }
                        inputLine = this.inputReader.readLine();
                    }
                    if (cookie.equals("nothing")){
                        ClientHandler clientHandlerFirstTime = new ClientHandler(socket, guessInt);
                        //Generate cookie
                        System.out.println("A new user has connected");
                        System.out.println(socket);
                        clientHandlers.add(clientHandlerFirstTime);
                        Thread thread = new Thread(clientHandlerFirstTime);
                        thread.start();

                    }
                    else{
                        for(ClientHandler clientHandler : clientHandlers){
                            if(clientHandler.coockie.equals(cookie)){
                                //clientHandler.propagateMessage();  //Send it over to that handeler somehow
                            }
                        }
                        ClientHandler clientHandlerNotFirstTime = new ClientHandler(socket, guessInt ,cookie);
                        System.out.println("A new user has connected");


                        Thread thread = new Thread(clientHandlerNotFirstTime);
                        thread.start();

                    }
                }
            }
        }catch(IOException exception){
            System.out.println("Error in trying to start connection");
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
