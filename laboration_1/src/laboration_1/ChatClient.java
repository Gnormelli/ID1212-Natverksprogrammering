package laboration_1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter outputWriter;

    public ChatClient(Socket socket){
        try{
            this.socket = socket;
            this.outputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        }catch(IOException e){
            closeConnection(socket, bufferedReader, outputWriter);
        }
    }
    public void sendMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Scanner scanner = new Scanner(System.in);
                    while (socket.isConnected()) {
                        String messageToSend = scanner.nextLine();
                        outputWriter.write(messageToSend);
                        outputWriter.newLine();
                        outputWriter.flush();
                    }
                } catch (IOException e) {
                    closeConnection(socket, bufferedReader, outputWriter);
                }
            }
        }).start();
    }
    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromOthers;
                while (socket.isConnected()){
                   try {
                        msgFromOthers = bufferedReader.readLine();
                        System.out.println(msgFromOthers);
                    }catch(IOException e){
                        System.out.println("Disconnect from server");
                        closeConnection(socket, bufferedReader, outputWriter);
                        break;
                    }
                }
            }

        }).start();
    }

    public void closeConnection(Socket socket, BufferedReader inputReader, BufferedWriter outputWriter){
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
    public static void main(String[] args) throws IOException {
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Connecting...");

        Socket socket = new Socket("localhost", 8000);
        // System.out.println("This works");
        ChatClient chatClient = new ChatClient(socket);
        System.out.println("Connected!");
        chatClient.listenForMessage();
        chatClient.sendMessage();
        //System.out.println("exit sendMessage");

    }

}