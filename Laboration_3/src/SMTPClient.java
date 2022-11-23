import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;

public class SMTPClient {
    private Socket socket;
    private BufferedReader inputReader;
    private BufferedWriter outputWriter;
    public SMTPClient(Socket socket) {
        try{
            this.socket = socket;
            this.outputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(IOException e){
            closeConnection(socket, inputReader, outputWriter);
        }
    }

    public void startSession() throws IOException {
        String response;
        try {
            response = this.inputReader.readLine();
            System.out.println(response);

            String initiate = "EHLO 85.230.75.18";
            System.out.println("Written to terminal: "+ initiate);
            this.outputWriter.write(initiate);
            this.outputWriter.newLine();

            this.outputWriter.flush();
            while(!(response = this.inputReader.readLine()).contains("250 CHUNKING")){
                System.out.println(response);
            }

            String start = "STARTTLS";
            System.out.println("Written to terminal: " + start);
            this.outputWriter.write(start);
            this.outputWriter.newLine();

            this.outputWriter.flush();
            response = this.inputReader.readLine();
            System.out.println(response);

            ;

            String continueEHLO = "EHLO 85.230.75.18";

            System.out.println("Written to terminal: " + continueEHLO);
            this.outputWriter.write(continueEHLO);
            this.outputWriter.newLine();

            this.outputWriter.flush();
            System.out.println("something");
            while(!(response = this.inputReader.readLine()).isEmpty()){
                System.out.println(response);
            }



//            String username = "a001 LOGIN kfranzen kN4@3QXB5aGzQRa";
//            this.outputWriter.write(username);
//            this.outputWriter.newLine();
//
//            this.outputWriter.flush();
//            response = this.inputReader.readLine();
//            System.out.println(response);

        } catch (IOException e) {
             System.out.println(e);
            throw new RuntimeException(e);
        }
            /**
            String[] supported = socket.getSupportedCipherSuites();
            socket.setEnabledCipherSuites(supported);


             * Is this needed?
             socket.startHandshake();
             */


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
        String host = "smtp.kth.se";
        int port = 587;

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket socket = null;
        socket = new Socket(host, port);
        SMTPClient SMTPClient = new SMTPClient(socket);

        SMTPClient.startSession();
        System.out.println("Connected!");
    }
}
