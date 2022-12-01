import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SMTPClient {
    private Socket socket;
    private BufferedReader inputReader;
    private BufferedWriter outputWriter;
    private SSLSocketFactory socketFactory;
    public SMTPClient(Socket socket, SSLSocketFactory factory) {
        try{
            this.socket = socket;
            this.socketFactory = factory;
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

            String ehlo = "EHLO 85.230.75.18";
            System.out.println("Written to terminal: "+ ehlo);
            this.outputWriter.write(ehlo);
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

            this.socket = (SSLSocket) ((SSLSocketFactory) this.socketFactory.getDefault()).createSocket(
                    this.socket,
                    this.socket.getInetAddress().getHostAddress(),
                    this.socket.getPort(),
                    true);

            this.outputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String continueEHLO = "EHLO 85.230.75.18";
            System.out.println("Written to terminal: " + continueEHLO);
            this.outputWriter.write(continueEHLO);
            this.outputWriter.newLine();
            this.outputWriter.flush();
            while(!(response = this.inputReader.readLine()).contains("250 CHUNKING")){
                System.out.println(response);
            }
            System.out.println(response);
            String authLogin = "AUTH LOGIN";

            System.out.println("Written to terminal: " + authLogin);
            this.outputWriter.write(authLogin);
            this.outputWriter.newLine();
            this.outputWriter.flush();
            response = this.inputReader.readLine();
            System.out.println(response);
            
            String username = "kfranzen";
            System.out.println("Written to terminal: " + username);
            username = Base64.getEncoder().encodeToString(username.getBytes());
            this.outputWriter.write(username);
            this.outputWriter.newLine();
            this.outputWriter.flush();
            response = this.inputReader.readLine();
            System.out.println(response);

            String password = "kN4@3QXB5aGzQRa";
            System.out.println("Written to terminal: " + password);
            password = Base64.getEncoder().encodeToString(password.getBytes());
            this.outputWriter.write(password);
            this.outputWriter.newLine();
            this.outputWriter.flush();
            response = this.inputReader.readLine();
            System.out.println(response);

            String mailFrom = "MAIL FROM:<kfranzen@kth.se>";
            System.out.println("Written to terminal: " + mailFrom);
            this.outputWriter.write(mailFrom);
            this.outputWriter.newLine();
            this.outputWriter.flush();
            response = this.inputReader.readLine();
            System.out.println(response);

            String receiptFrom = "RCPT TO:<normelli@kth.se>";
            System.out.println("Written to terminal: " + receiptFrom);
            this.outputWriter.write(receiptFrom);
            this.outputWriter.newLine();
            this.outputWriter.flush();
            response = this.inputReader.readLine();
            System.out.println(response);

            String data = "DATA";
            System.out.println("Written to terminal: " + data);
            this.outputWriter.write(data);
            this.outputWriter.newLine();
            this.outputWriter.flush();
            response = this.inputReader.readLine();
            System.out.println(response);

            String mail = "Hello world \n.";
            System.out.println("Written to terminal: " + mail);
            this.outputWriter.write(mail);
            this.outputWriter.newLine();
            this.outputWriter.flush();
            response = this.inputReader.readLine();
            System.out.println(response);

            String quit = "QUIT";
            System.out.println("Written to terminal: " + quit);
            this.outputWriter.write(quit);
            this.outputWriter.newLine();
            this.outputWriter.flush();
            response = this.inputReader.readLine();
            System.out.println(response);

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
        SMTPClient SMTPClient = new SMTPClient(socket, factory);

        SMTPClient.startSession();
        System.out.println("Connected!");
    }
}
