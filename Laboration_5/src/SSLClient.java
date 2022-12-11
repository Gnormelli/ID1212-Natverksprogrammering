import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SSLClient {
    private SSLSocket socket;
    private BufferedReader inputReader;
    private BufferedWriter outputWriter;

    public SSLClient(SSLSocket socket){
        try{
            this.socket = socket;
            this.outputWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(IOException e){
            closeConnection(socket, inputReader, outputWriter);
        }
    }

    public void startSession(){
        String response;
        try {
            String[] supported = socket.getSupportedCipherSuites();
            socket.setEnabledCipherSuites(supported);

            /**
             * Is this needed?
            socket.startHandshake();
             */

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

            String header1 = "a003 fetch ";

            String header2 = " body[header]";
            String headerComp = "";
            for (int i = 2; i <= 10; i++) {
                headerComp = header1 + Integer.valueOf(i) + header2;
                this.outputWriter.write(headerComp);
                this.outputWriter.newLine();

                this.outputWriter.flush();
                while(!(response = this.inputReader.readLine()).contains("FETCH completed")){
                    if (response.contains("=?utf-8?")) {
                        if(response.contains("=?utf-8?q?")){
                            printURLEncoded(response);
                        }
                        else {
                            String decoded = base64ToUTF8Subject(response);
                            System.out.println(decoded);
                        }
                    }

                }
            }

            System.out.println("------------Above is from Fetch");

            String body = "a003 fetch 8 body[TEXT]";
            this.outputWriter.write(body);
            this.outputWriter.newLine();

            this.outputWriter.flush();
            while(!(response = this.inputReader.readLine()).contains("FETCH completed")){
                if(!response.contains("8 FETCH (BODY[TEXT]") && !response.equals("") && !response.equals(" FLAGS (\\Seen))")){
                    String decoded = base64ToUTF8mailText(response);
                    System.out.println(decoded);
                }
            }
            System.out.println("------------Above is from Fetch");

        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }


    public String base64ToUTF8Subject(String response){
        String decode = response.split("utf-8\\?B\\?")[1];
        decode = decode.split("\\?")[0];
        String decodedString = base64ToUTF8mailText(decode);
        System.out.print("Subject: ");
        return decodedString;
    }

     public String base64ToUTF8mailText(String decode){
         byte[] decodedBytes = Base64.getDecoder().decode(decode);
         return new String(decodedBytes);

     }

    public void printURLEncoded(String response) throws IOException {
        int responseLength = response.length();
        response = makeURLDecodedFormatedSoWeCanDecodeIt(response);
        response = java.net.URLDecoder.decode(response, StandardCharsets.UTF_8);
        System.out.print("Subject: ");
        System.out.print(response);
        while (responseLength == 84){
            response = this.inputReader.readLine();
            responseLength = response.length();
            response = makeURLDecodedFormatedSoWeCanDecodeIt(response);
            response = java.net.URLDecoder.decode(response, StandardCharsets.UTF_8);
            System.out.print(response);
        }
        System.out.println();
    }
    public String makeURLDecodedFormatedSoWeCanDecodeIt(String response){
        response = response.split("utf-8\\?q\\?")[1];
        response = response.split("\\?")[0];
        response = response.replace("=", "%");
        response = response.replace("_", " ");
        return response;
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
        SSLClient SSLClient = new SSLClient(socket);

        SSLClient.startSession();
        System.out.println("Connected!");
    }
}

