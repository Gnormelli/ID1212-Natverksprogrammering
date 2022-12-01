package com.servlet;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Model implements Runnable {

    public static ArrayList<Model> models = new ArrayList<>();
    private Socket socket;
    private int guessInt;
    private BufferedReader inputReader;
    public String cookie;
    private int randomNumber;
    private String messageToUser;
    private int numberOfGuesses;
        //For first time entry (no cookie from start)
    public Model(Socket socket, int guessInt, String cookie) throws IOException {

        try {
            this.socket = socket;
            this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.guessInt = guessInt;
            this.cookie = cookie;
            models.add(this);
        }catch(IOException e){
            System.out.println(e);
            closeEverything(socket,inputReader);
        }
    }

    //For when we already have a cookie
    public Model(Socket socket, int guessInt, String cookie, int randomNumber, int numberOfGuesses) throws IOException {
        try {
            this.socket = socket;
            this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.guessInt = guessInt;
            this.cookie = cookie;
            this.randomNumber = randomNumber;
            this.numberOfGuesses = numberOfGuesses;

            models.add(this);
        }catch(IOException e){
            System.out.println(e);
            closeEverything(socket,inputReader);
        }

    }
    public void generateAndSetRandomNumber(){
        Random rand = new Random();
        this.randomNumber = rand.nextInt(100);
    }
    public void incrementNumberOfGuesses(){
        numberOfGuesses++;
    }

    public void setGuess(int guess){
        guessInt = guess;
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
                listenForMessage();

            } catch (IOException e) {
                System.out.println(e);
                closeEverything(socket, inputReader);
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
            closeEverything(socket, inputReader);
        }
    }

    public String createTheMessage() {
        messageToUser = "Welcome to the Number Guess Game. Guess a number between 1 and 100.";
        if(guessInt != -1){
            int guessResult = getGuessResult(guessInt); // -1 is too low, 0 is right, 1 is too high.

            switch (guessResult) {
                case 1:
                    messageToUser = "Nope, guess higher.<br>" +
                                    "Number of guesses: " + numberOfGuesses;
                    break;
                case -1:
                    messageToUser = "Nope, guess lower.<br>" +
                            "Number of guesses: " + numberOfGuesses;
                    break;
                case 0:
                    messageToUser = "You made it!!!<br>" +
                            "Number of guesses: " + numberOfGuesses;
                    generateAndSetRandomNumber();
                    numberOfGuesses = 0;
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


    public void removeClientHandler() {
        models.remove(this);
    }

    public void closeEverything(Socket socket, BufferedReader inputReader) {
        removeClientHandler();
        try {
            if (inputReader != null) {
                inputReader.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}

