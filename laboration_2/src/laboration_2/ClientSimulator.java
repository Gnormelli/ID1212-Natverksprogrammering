package laboration_2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ClientSimulator {
    private HttpClient httpClient;
    private HttpRequest request;
    private HttpResponse<String> response;
    private URI initialURI;

    public ClientSimulator() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.initialURI= URI.create("http://localhost:8000/");
        this.request = HttpRequest.newBuilder().GET().uri(this.initialURI).build();
        this.response = null;
    }

    public void runSimulation() throws IOException, InterruptedException {
        int numTests = 0;
        try {
            while(numTests<99) {
                //while(this.response.toString().contains() );
                this.response = this.httpClient.send(this.request,
                        HttpResponse.BodyHandlers.ofString());
                System.out.println("Status code: " + this.response.statusCode());
                System.out.println("Headers: " + this.response.headers().allValues("content-type"));
                System.out.println("Body: " + this.response.body());
                numTests++;
                System.out.println("This is the number of tests that have been run: " +numTests);
            }
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
    public String checkResponseFromServer(HttpResponse<String> response) {
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
    */

    public static void main(String[] args) throws IOException, InterruptedException {
        ClientSimulator clientSimulator = new ClientSimulator();
        clientSimulator.runSimulation();
    }

}
