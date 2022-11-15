package laboration_1;

import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class getConectionTry {

    private BufferedReader inputReader;
    private BufferedWriter outputWriter;
    private Socket serverSocket;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(55555);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 55555.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();

            if (clientSocket != null) {
                System.out.println("Connected");
            }
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);


        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
        out.println("\r\n");
        out.println("<p> Hello world </p>");
        out.flush();

        out.close();

        clientSocket.close();
        serverSocket.close();

    }
}
