package laboration_2;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Model implements Runnable {

    public static ArrayList<Model> models = new ArrayList<>();
    private Socket socket;
    public String coockie;
    private int guessInt;
    private BufferedReader inputReader;
    private BufferedWriter outputWriter;
    public int userID;
    private int randomNumber;
    private String messageToUser;

    public Model(Socket serverSocket, int guessInt) {
        try {

            this.guessInt = guessInt;

            this.socket = serverSocket;
            this.outputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.userID = createUserID();
            this.coockie = Integer.toString(this.userID);
            setRandomNumber();
            models.add(this);
        } catch (IOException e) {
            closeEverything(socket, inputReader, outputWriter);
        }

    }

    //For when we already have a cookie
    public Model(Socket serverSocket, int guessInt, String coockie) {
        try {
            this.guessInt = guessInt;
            this.coockie = coockie;
            this.socket = serverSocket;
            this.outputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.userID = createUserID();
            models.add(this);
        } catch (IOException e) {
            closeEverything(socket, inputReader, outputWriter);
        }

    }
    public void setRandomNumber(){
        Random rand = new Random();
        this.randomNumber = rand.nextInt(100);
    }

    public int createUserID() {
        int count = 1;
        for (Model model : models) {
            count++;
        }
        return count;
    }


    /**
     * Overrides  Implements Runnable
     */

    @Override
    public void run() {
        String message;

        while (socket.isConnected()) {
            try {
                message = inputReader.readLine();
                System.out.println(message);

                //propagateMessage(this.socket);
                listenForMessage();
            } catch (IOException e) {
                System.out.println(e);
                closeEverything(socket, inputReader, outputWriter);
                break;
            }
        }
    }
    public void listenForMessage(){
        String msgFromServer;
        try {
            msgFromServer = inputReader.readLine();
            System.out.println(msgFromServer);
        }catch(IOException e){
            System.out.println("Server can´t listen anymore");
            closeEverything(socket, inputReader, outputWriter);
        }
    }

    public String createTheMessage() {
        String messageToUser = "Welcome to the Number Guess Game. Guess a number between 1 and 100.";
        if(guessInt != -1){
            int guessResulte = getGuessResult(guessInt); // -1 is to low, 0 is rigth, 1 is to hige.

            switch (guessResulte) {
                case 1:
                    messageToUser = "Nope, guess higher";
                    break;
                case -1:
                    messageToUser = "Nope, guess lower";
                    break;
                case 0:
                    messageToUser = "You made it!!!";
                    setRandomNumber();
                    break;
            }
        }
        return messageToUser;
    }

    private int getGuessResult(int guessInt) {
        if (guessInt > randomNumber)
            return -1;
        else if (guessInt < randomNumber)
            return 1;
        else
            return 0;
    }

    public int getUserID() {
        return userID;
    }

    public void removeClientHandler() {
        models.remove(this);
        //propagateMessage("Server: Client " + id + " left");
    }

    public void closeEverything(Socket socket, BufferedReader inputReader, BufferedWriter outputWriter) {
        removeClientHandler();
        try {
            if (inputReader != null) {
                inputReader.close();
            }
            if (outputWriter != null) {
                outputWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}

