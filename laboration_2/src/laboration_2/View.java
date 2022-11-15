package laboration_2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class View {

    private Socket socket;
    private BufferedReader inputReader;
    private BufferedWriter outputWriter;

    public View(Socket socket){
        try{
            this.socket = socket;
            this.outputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        }catch(IOException e){
            closeConnection(socket, inputReader, outputWriter);
        }
    }
    public void sendMessage(String host){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String file = "index.html";
                    /**
                        String messageToSend =
                                "GET /" + file + " HTTP/1.1 \n" +
                                "Host: " + host + "\n" +
                                "Content-Type: text/html" ;
                     */
                        outputWriter.write("FUNKA RÅÅÅÅÅ!!!");
                        outputWriter.newLine();
                        outputWriter.flush();
                } catch (IOException e) {
                    closeConnection(socket, inputReader, outputWriter);
                }
            }
        }).start();
    }
    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromServer;
                while (socket.isConnected()){
                   try {
                        msgFromServer = inputReader.readLine();
                        System.out.println(msgFromServer);
                    }catch(IOException e){
                        System.out.println("Disconnect from server");
                        closeConnection(socket, inputReader, outputWriter);
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
        String host = "localhost";
        System.out.println("Connecting...");

        Socket socket = new Socket(host, 8000);
        // System.out.println("This works");
        View view = new View(socket);
        System.out.println("Connected!");
        view.listenForMessage();
        view.sendMessage(host);
        //System.out.println("exit sendMessage");

    }

}