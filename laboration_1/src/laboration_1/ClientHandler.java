package laboration_1;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private int id;


    public ClientHandler(Socket serverSocket) {
        try {
            this.socket = serverSocket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.id = getUserID();
            clientHandlers.add(this);
            broadcastMessage("Server: A new user " + this.id + " has entered");
        }catch(IOException e ){
            closeEverything(socket, bufferedReader, bufferedWriter);
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
                message = bufferedReader.readLine();
                broadcastMessage(message);
            }catch(IOException e ){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }
    public void broadcastMessage(String messageToSend){
        for (ClientHandler clientHandler : clientHandlers){
           try{
            if(clientHandler.id != this.id){
                clientHandler.bufferedWriter.write(messageToSend);
                clientHandler.bufferedWriter.newLine();
                clientHandler.bufferedWriter.flush();
            }
           }catch(IOException e){
               closeEverything(socket, bufferedReader, bufferedWriter);
           }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("Server: Client " + id + " left");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClientHandler();
        try{
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
