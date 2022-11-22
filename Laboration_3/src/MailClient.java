import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class MailClient {


    private SSLSocket socket;
    private BufferedReader inputReader;
    private BufferedWriter outputWriter;

    public MailClient(SSLSocket socket){
        try{
            this.socket = socket;
            this.outputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(IOException e){
            closeConnection(socket, inputReader, outputWriter);
        }
    }

    public void startClient(){
        String response;
        try {
            Scanner scanner = new Scanner(System.in);
            String[] supported = socket.getSupportedCipherSuites();
            socket.setEnabledCipherSuites(supported);

            response = this.inputReader.readLine();
            System.out.println(response);

            //String username = scanner.nextLine();
            String username = "a001 LOGIN kfranzen kN4@3QXB5aGzQRa";
            this.outputWriter.write(username);
            this.outputWriter.newLine();

            this.outputWriter.flush();
            response = this.inputReader.readLine();
            System.out.println(response);

//            String password = scanner.nextLine();
            String inbox = "a002 select inbox";
            this.outputWriter.write(inbox);
            this.outputWriter.newLine();


            this.outputWriter.flush();

            while(!(response = this.inputReader.readLine()).contains("SELECT completed")){
                System.out.println(response);
            }

            System.out.println("------------Above is from Select");

            String emails = "a003 fetch 12 full";
            this.outputWriter.write(emails);
            this.outputWriter.newLine();

            this.outputWriter.flush();
            while(!(response = this.inputReader.readLine()).contains("FETCH completed")){
                System.out.println(response);
            }
            System.out.println("------------Above is from Fetch");

            String header = "a003 fetch 8 body[header]";
            this.outputWriter.write(header);
            this.outputWriter.newLine();

            this.outputWriter.flush();
            while(!(response = this.inputReader.readLine()).contains("FETCH completed")){
                System.out.println(response);
            }
            System.out.println("------------Above is from Fetch");

        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
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
        String host = "webmail.kth.se";
        int port = 993;

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = null;
        socket = (SSLSocket) factory.createSocket(host, port);
        MailClient mailClient = new MailClient(socket);
        

        mailClient.startClient();
        System.out.println("Connected!");

    }
}

