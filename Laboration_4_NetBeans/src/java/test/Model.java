/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Random;

/**
 *
 * @author Gustav Normelli
 */
public class Model {
	
 

    private int guessInt;
    private int randomNumber;
    private String messageToUser;
    private int numberOfGuesses;
	
    public Model()  {
    	numberOfGuesses = 0;
    	Random rand = new Random();
        this.randomNumber = rand.nextInt(100);
    }
    
    public void setGuess(int guess) {
    	this.guessInt = guess;
    }
    
    private void generateAndSetRandomNumber(){
        Random rand = new Random();
        this.randomNumber = rand.nextInt(100);
    }
    
    public void incrementNumberOfGuesses(){
        numberOfGuesses++;
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

}
