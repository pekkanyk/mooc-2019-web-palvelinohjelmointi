

import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class HelloServer {

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
            
            File file = new File("index.html");
            Scanner sivu = new Scanner(file);
                try (PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream())) {
                    kirjoittaja.println("HTTP/1.1 200 OK");
                    kirjoittaja.println("Content-Type: text/html;charset=UTF-8");
                    kirjoittaja.println();
                    while (sivu.hasNextLine()){
                    kirjoittaja.println(sivu.nextLine());
                    }
                    sivu.close();
                    kirjoittaja.println();
                    kirjoittaja.println();
                    
                    kirjoittaja.flush();
                    lukija.close();
                }
            socket.close();
            
            
            
        }
        server.close();

    }
}
