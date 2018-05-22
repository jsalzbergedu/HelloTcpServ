import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple TCP server
 * @author Jacob Salzberg
 */
public class HelloTcpServ {
    /**
     * The port number
     */
    public static final int PORT = 4006;

    /**
     * The string to send
     */
    public static final String SEND = "I am server";

    /**
     * The entry point of the program.
     * 
     * @param args Command line arguments. Not used.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Bonjour m'sieur. Je suis votre serveur ce soir.");
        ServerSocket ss = new ServerSocket(PORT);
        Socket s = ss.accept();
        // Get a PrintWriter to send to the client
        OutputStream os = s.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);

        // Get a buffered reader to receive from the client
        InputStream is = s.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String receive = "Message not received";
        while (true) {
            String maybeNull = br.readLine();
            receive = (maybeNull == null) ? "Message not received" : maybeNull;

            pw.println(SEND);
            pw.flush();
            System.out.println(receive);
        }
    }
}
