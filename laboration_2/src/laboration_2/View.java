package laboration_2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class View{

    private Socket socket;
    private int cookie;

    public View(Socket serverSocket, int cookie) {
            this.socket = serverSocket;
            this.cookie = cookie;
    }

    public void propagateMessage(String messageToUser) throws IOException {
        try{
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println("Set-Cookie: cookieForGKServer=" + this.cookie);
            out.println("\r\n");
            out.println("<html>\n" +
                    "<head>\n" +
                    "<title>Number Guess Game</title>\n" +
                    "</head>\n");
            out.println("<script type = \"text/javascript\">\n" +
                    "function inputfocus(form){\n"
                    + "document.getElementById(\"field\").value" +
                    "function cookieCreator(){\n"
                    + "document.cookie = \"" + this.cookie + "\").value" +
                    "}\n" +
                    "</script>");

            out.println("<body> \n" +
                    "<h>" + messageToUser +"</h>\n" +
                    "<form name=\"guessform\">\n" +
                    "<input type=text name=guess>\n" +
                    "<input type=submit value=\"Guess\">\n" +
                    "</form>\n" +
                    "\n" +
                    "</body></html>");
            out.flush();
            out.close();
        }catch(IOException e ){
            System.out.println(e);
            closeEverything(this.socket);
        }
    }

    public void closeEverything(Socket socket) {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}