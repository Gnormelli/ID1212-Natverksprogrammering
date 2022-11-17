package laboration_2;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Controller{
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader inputReader;
    public static ArrayList<Model> models = new ArrayList<>();

    public Controller(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void startServer(){
        try {
            while(!serverSocket.isClosed()){
                int guessInt = -1;
                String cookie = "nothing";
                this.socket = serverSocket.accept();

                this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inputLine = this.inputReader.readLine();

                if (!inputLine.contains("favicon")) {
                    if(inputLine.contains("guess")){
                        String result = inputLine.split("guess=")[1];
                        String guessString = result.split(" HTTP/")[0];
                        guessInt = Integer.parseInt(guessString);
                    }

                    while(!inputLine.equals(""))
                    {
                        System.out.println(inputLine);
                        if(inputLine.contains("Cookie: ")){
                            System.out.println("Found cookie");
                            cookie = inputLine.split("Cookie: ")[1];   // This needs to be implemented
                            cookie = cookie.split(" ")[0];

                        }
                        inputLine = this.inputReader.readLine();
                    }
                    System.out.println(cookie); //Remove later
                    if (cookie.equals("nothing")){
                        {
                            int count = 1;
                            for (Model models : models) {
                                count++;
                            }
                            cookie = Integer.toString(count);
                        }

                        Model modelFirstTime = new Model(socket, guessInt);
                        int userID = modelFirstTime.getUserID();

                        View website = new View(this.socket, userID);    //user id mot cookie senare

                        String messageToUser = modelFirstTime.createTheMessage();
                        website.propagateMessage(messageToUser);

                        System.out.println("A new user has connected");
                        models.add(modelFirstTime);
                        Thread thread = new Thread(modelFirstTime);
                        thread.start();

                    }
                    else{
                        Model modelNotFirstTime = new Model(socket, guessInt ,cookie);
                        System.out.println("A new user has connected");

                        Thread thread = new Thread(modelNotFirstTime);
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
