package laboration_1;

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
            propagateMessage("User with id " + this.id + " joined the chat");
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
                propagateMessage(message);
            }catch(IOException e ){
                closeEverything(socket, inputReader, outputWriter);
                break;
            }
        }
    }
    public void propagateMessage(String messageToSend){
        for (ClientHandler clientHandler : clientHandlers){
           try{
            if(clientHandler.id != this.id){
                clientHandler.outputWriter.write(messageToSend);
                clientHandler.outputWriter.newLine();
                clientHandler.outputWriter.flush();
            }
           }catch(IOException e){
               closeEverything(socket, inputReader, outputWriter);
           }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        propagateMessage("Server: Client " + id + " left");
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
