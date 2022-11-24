package laboration_2;

import java.awt.*;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

public class ClientSimulator {
    private HttpClient httpClient;
    private HttpRequest request;
    private HttpResponse<String> response;
    private int upperBound;
    private int lowerBound;
    private int randomGuess;
    private int numberOfGuesses;
    private boolean continueIteration;
    private String cookie;

    public ClientSimulator() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.upperBound = 100;
        this.lowerBound = 0;
        this.response = null;
        this.numberOfGuesses = 0;
        this.continueIteration = false;
        this.cookie = "";
    }

    public void runSimulation() throws IOException, InterruptedException {
        int numTests = 0;
        URI uri = null;
        try {
            while(numTests<100) {
                // Initial request for website
                uri = URI.create("http://localhost:8000/");
                this.request = HttpRequest.newBuilder().GET().uri(uri).build();
                receivedResponse();
                generateAndSetRandomGuess();
                continueIteration = true;
                while (continueIteration) {
                    uri = URI.create("http://localhost:8000/?guess=" + randomGuess);
                    this.request = HttpRequest.newBuilder().GET().uri(uri).build();

                    if(cookie != null) {
                        uri = URI.create("http://localhost:8000/?guess=" + randomGuess);
                        HttpRequest.Builder builder = HttpRequest.newBuilder();
                        builder.GET();
                        builder.headers("Cookie", cookie);
                        this.request = builder.uri(uri).build();
                    }
                    receivedResponse();
                    checkResponseFromServer();
                }
                numTests++;
                System.out.println("This is the number of tests that have been run: " +numTests);
            }
            System.out.println("The average number of guesses were: " + this.numberOfGuesses/100);
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void receivedResponse() throws IOException, InterruptedException {
        this.response = this.httpClient.send(this.request,
                HttpResponse.BodyHandlers.ofString());

        List<String> headers = response.headers().allValues("Set-cookie");        //
        cookie = headers.get(0).split("=")[1];
        System.out.println("Status code: " + this.response.statusCode());
        System.out.println("Headers: " + this.response.headers().allValues("content-type"));
        System.out.println("Body: " + this.response.body());
    }


    public void checkResponseFromServer() {
        int guessResult = getGuessResponse();
        switch (guessResult) {
            case 1:
                this.upperBound = randomGuess;
                generateAndSetRandomGuess();
                ++numberOfGuesses;
                break;
            case 2:
                this.lowerBound = randomGuess;
                generateAndSetRandomGuess();
                ++numberOfGuesses;
                break;
            case 0: if (this.response.body().contains("You made it!!!<br>"))
                    this.continueIteration = false;
                    this.upperBound = 100;
                    this.lowerBound = 0;
                    ++numberOfGuesses;
                    cookie = Integer.toString(Integer.valueOf(cookie) + 1);
                    break;
        }
    }
    public void generateAndSetRandomGuess(){
        this.randomGuess = ThreadLocalRandom.current().nextInt(this.lowerBound, this.upperBound);
    }

    private int getGuessResponse() {
        if (this.response.body().contains("Nope, guess lower"))
            return 1;
        else if (this.response.body().contains("Nope, guess higher."))
            return 2;
        else
            return 0;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ClientSimulator clientSimulator = new ClientSimulator();
        clientSimulator.runSimulation();
    }

}
