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


    public static ArrayList<Model> models = new ArrayList<>();

    public Controller(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void startServer(){
        try {
            while(!serverSocket.isClosed()){
                int numberOfGuesses = 0;
                int guessInt = -1;
                String cookie = "nothing";
                int randomNumber = -1;
                Socket socket = serverSocket.accept();

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
                        System.out.println(inputLine);            //prints the entire request
                        if(inputLine.contains("Cookie: ")){
                            System.out.println("Found cookie");
                            cookie = inputLine.split("Cookie: ")[1];
                            cookie = cookie.split(" ")[0];
                        }
                        inputLine = this.inputReader.readLine();
                    }
                    System.out.println("cookie is = " + cookie); //Remove later
                    if (cookie.equals("nothing")){
                        {
                            int count = 1;
                            for (Model models : models) {           //generates cookie based on #of models
                                count++;
                            }
                            cookie = Integer.toString(count);
                        }

                        Model modelFirstTime = new Model(socket, guessInt, cookie);
                        View website = new View(socket, Integer.parseInt(cookie));

                        String messageToUser = modelFirstTime.createTheMessage();
                        website.propagateMessage(messageToUser, Integer.toString(numberOfGuesses));
                        System.out.println("A new user has connected");
                        models.add(modelFirstTime);
                        inputReader.close();
                        Thread thread = new Thread(modelFirstTime);
                        thread.start();

                    }
                    else{
                        for(Model model : models){
                            if(cookie.equals(model.cookie)){
                                randomNumber = model.getRandomNumber();
                                numberOfGuesses = model.getNumberOfGuesses() + 1;
                            }
                        }



                        Model modelNotFirstTime = new Model(socket, guessInt ,cookie, randomNumber, numberOfGuesses);

                        View website = new View(socket, Integer.parseInt(cookie));

                        String messageToUser = modelNotFirstTime.createTheMessage();
                        website.propagateMessage(messageToUser, Integer.toString(numberOfGuesses));
                        System.out.println("A user (with a cookie has returned)");
                        inputReader.close();
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
