

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class HelloRedirectLoop {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(11111);
        while (true)
        {
            Socket socket = server.accept();
            Scanner lukija = new Scanner(socket.getInputStream());
           
            ArrayList request = new ArrayList();
            while (lukija.hasNextLine()) {
            //System.out.println(lukija.nextLine());
            request.add(lukija.nextLine());
            }
            String osoite = "";
            if (!request.isEmpty()){
                osoite = request.get(0).toString().split(" ")[1];
            }
            
            if (osoite.equals("/quit")) {
                lukija.close();
                socket.close();
                break;
            }
            
                try (PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream())) {
                    kirjoittaja.println("HTTP/1.1 302 Found");
                    kirjoittaja.println("Location: http://localhost:11111");
                    kirjoittaja.println();                    
                    kirjoittaja.flush();
                    lukija.close();
                }
            socket.close();
            
            
            
        }
        server.close();

    }
}
