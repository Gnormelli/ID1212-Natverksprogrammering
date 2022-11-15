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
            informServer();
        }catch(IOException e ){
            System.out.println("ClientHandler");
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
                informServer();

            }catch(IOException e ){
                System.out.println("run");
                closeEverything(socket, inputReader, outputWriter);
                break;
            }
        }
    }
    public void informServer(){
        for (ClientHandler client : clientHandlers){
           try{
            if(client.id != this.id){
                client.outputWriter.write("HTTP/1.1 200 OK");
                client.outputWriter.write("\r\n");
                client.outputWriter.write("Content-Type: text/html");
                client.outputWriter.write("\r\n");
                client.outputWriter.write("<p> Hello world </p>");
                client.outputWriter.newLine();
                client.outputWriter.flush();
               // client.outputWriter.write(messageToSend);
               // client.outputWriter.newLine();
               // client.outputWriter.flush();
            }
           }catch(IOException e){
               System.out.println("informServer");
               closeEverything(socket, inputReader, outputWriter);
           }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        informServer();
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

