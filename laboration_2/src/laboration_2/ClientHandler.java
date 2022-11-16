package laboration_2;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader inputReader;
    private BufferedWriter outputWriter;
    private int id;


    public ClientHandler(Socket serverSocket) {
        try {
            this.socket = serverSocket;
            this.outputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.id = getUserID();
            clientHandlers.add(this);
            propagateMessage(serverSocket);
        }catch(IOException e ){
            closeEverything(socket, inputReader, outputWriter);
        }

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

        while(socket.isConnected()){
            try{
                message = inputReader.readLine();
                System.out.println(message);
                //propagateMessage(message);
            }catch(IOException e ){
                closeEverything(socket, inputReader, outputWriter);
                break;
            }
        }
    }
    public void propagateMessage(Socket serverSocket){

        try{

            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println("\r\n");
            out.println("<p> Can we connect? Yes </p>");
            out.println("<button type=\"button\">Click Me!</button>");
            out.flush();

            out.close();
            System.out.println("im here");
        }catch(IOException e){
            closeEverything(socket, inputReader, outputWriter);

        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        //propagateMessage("Server: Client " + id + " left");
    }

    public void closeEverything(Socket socket, BufferedReader inputReader, BufferedWriter outputWriter){
        removeClientHandler();
        try{
            if (inputReader != null){
                inputReader.close();
            }
            if(outputWriter != null){
                outputWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
