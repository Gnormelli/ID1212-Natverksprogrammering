/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gustav Normelli
 */
public class View{

   

    public View() {
    }

    public void propagateMessage(String messageToUser, HttpServletResponse res) throws IOException {
        try{
        	PrintWriter out = res.getWriter();
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
   
            out.println("\r\n");
            out.println("<html>\n" +
                    "<head>\n" +
                    "<title>Number Guess Game</title>\n" +
                    "</head>\n");
            out.println("<script type = \"text/javascript\">\n" +
                    "function inputfocus(form){\n"
                    + "document.getElementById(\"field\").value" +                
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
         
        }
    }
}