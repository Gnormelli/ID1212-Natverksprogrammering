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
        View website;
        try {
            while(!serverSocket.isClosed()){

                int modelCount = 0;
                int currentModel = 0;
                int guessInt = -1;
                String cookie = "nothing";
                int randomNumber = -1;
                int cookieCount = 0;
                this.socket = serverSocket.accept();

                this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inputLine = this.inputReader.readLine();

                if (!inputLine.contains("favicon")) {

                    if(inputLine.contains("guess")){
                        String result = inputLine.split("guess=")[1];
                        String guessString = result.split(" HTTP/")[0];
                        if(!guessString.equals("")){             //in case user presses guess with a empty field
                            guessInt = Integer.parseInt(guessString);
                        }
                    }

                    while(!inputLine.equals(""))
                    {
                        System.out.println(inputLine);            //prints the entire request
                        if(inputLine.contains("cookieForGKServer=")){
                          //  System.out.println("Found cookie");
                            cookie = inputLine.split("cookieForGKServer=")[1];
                            cookie = cookie.split(";")[0];
                        }
                        inputLine = this.inputReader.readLine();
                    }
                    //System.out.println("cookie is = " + cookie);

                    if (cookie.equals("nothing")){
                        {
                            for (Model models : models) {           //generates cookie based on # of models
                                cookieCount++;
                            }
                            cookie = Integer.toString(cookieCount);
                        }

                        Model modelFirstTime = new Model(this.socket, guessInt, cookie);
                        modelFirstTime.generateAndSetRandomNumber();
                        website = new View(this.socket, Integer.parseInt(cookie));


                        models.add(modelFirstTime);
                        modelCount = cookieCount;
                        sendMessageAndStartThread(modelCount, website);

                    }
                    else{
                        for(Model model : models){
                            if(cookie.equals(model.cookie)){
                                currentModel = modelCount;
                            }
                            modelCount++;
                        }

                        try{  //If a sends request with a cookie but the model list is empty
                            models.get(currentModel);
                        }catch(IndexOutOfBoundsException e){
                            Model modelNotFirstTime = new Model(this.socket, guessInt ,cookie, randomNumber, 0);
                            models.add(modelCount, modelNotFirstTime);
                        }

                        models.get(currentModel).setGuess(guessInt);
                        if(guessInt != -1){
                            models.get(currentModel).incrementNumberOfGuesses();
                        }

                        //Model modelNotFirstTime = new Model(this.socket, guessInt ,cookie, randomNumber, numberOfGuesses); //get from models list instead

                        try{
                            website = new View(this.socket, Integer.parseInt(cookie));
                        }catch(NumberFormatException e){
                            website = new View(this.socket, modelCount);
                        }
                        sendMessageAndStartThread(currentModel, website);

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

    private void sendMessageAndStartThread(int modelNumber, View view) throws IOException {

        String messageToUser = models.get(modelNumber).createTheMessage();
        view.propagateMessage(messageToUser);
        System.out.println("A new http request");
        inputReader.close();
        Thread thread = new Thread(models.get(modelNumber));
        thread.start();

    }

    private void invalidGuess() throws IOException {

    }
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8000);
        Controller server = new Controller(serverSocket);
        server.startServer();

    }
}
