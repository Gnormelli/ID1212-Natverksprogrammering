package laboration_2;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    public String coockie;
    private int guessInt;
    private BufferedReader inputReader;
    private BufferedWriter outputWriter;
    private int id;
    private int randomNumber;

    public ClientHandler(Socket serverSocket, int guessInt) {
        try {

            this.guessInt = guessInt;
            this.coockie = "GenerateCookie"; //This needs to generate a cookie
            this.socket = serverSocket;
            this.outputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.id = getUserID();
            setRandomNumber();

            clientHandlers.add(this);

            propagateMessage(serverSocket);
        } catch (IOException e) {
            closeEverything(socket, inputReader, outputWriter);
        }

    }

    //For when we already have a cookie
    public ClientHandler(Socket serverSocket, int guessInt,String coockie) {
        try {
            this.guessInt = guessInt;
            this.coockie = coockie;
            this.socket = serverSocket;
            this.outputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.id = getUserID();
            clientHandlers.add(this);

            propagateMessage(serverSocket);
        } catch (IOException e) {
            closeEverything(socket, inputReader, outputWriter);
        }

    }
    public void setRandomNumber(){
        Random rand = new Random();
        this.randomNumber = rand.nextInt(100);
    }

    public int getUserID() {
        int count = 1;

        for (ClientHandler clientHandler : clientHandlers) {
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

    public void propagateMessage(Socket serverSocket) {

        try {
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

                //Give a message if it is to hige or low or rigth
            }


            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println("\r\n");
            out.println("<html>\n" +
                    "<head>\n" +
                    "<title>Number Guess Game</title>\n"+
                    "</head>\n");
            out.println("<script type = \"text/javascript\">\n" +
                    "function inputfocus(form){\n"
                            + "document.getElementById(\"field\").value"+
                    "}\n" +
                    "</script>");
            out.println("<body> \n" +
                    "<h>" +messageToUser+"</h>\n" +
                    "<form name=\"guessform\">\n" +
                    "<input type=text name=guess>\n" +
                    "<input type=submit value=\"Guess\">\n" +
                    "</form>\n" +
                    "\n" +
                    "</body></html>");
            //out.println("<form action=\"/action_page.php\" method=\"get\" target=\"_blank\">\n" +
            //"  <button type=\"submit\" formmethod=\"post\">Submit using POST</button>");
            out.flush();
            out.close();

        } catch (IOException e) {
            closeEverything(socket, inputReader, outputWriter);
        }
    }


    public int getGuessResult(int guessInt) {

        if (guessInt > randomNumber)
            return -1;
        else if (guessInt < randomNumber)
            return 1;
        else
            return 0;
    }
    public void removeClientHandler() {
        clientHandlers.remove(this);
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

