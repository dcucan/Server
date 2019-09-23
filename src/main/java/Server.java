import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = new ServerSocket(8080);

        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(isr);


        OutputStream os = socket.getOutputStream();
        PrintStream ps = new PrintStream(os, true);

        while (true) {

            while (true) {
                String line = br.readLine();
                if (line.matches("USER .+")) {
                    ps.println("name ok");
                    break;
                } else {
                    ps.println("ERR: name is missing");
                }
            }

            while(true) {
                String line = br.readLine();
                if (line.matches("MESSAGE .+")) {
                    ps.println("message ok");
                    break;
                } else {
                    ps.println("ERR: message is missing");
                }
            }

            String line = br.readLine();
            if (line.equals("QUIT")) {
                ps.println("QUIT");
                break;
            }

            System.out.println(line);


        }

        br.close();

        socket.close();


    }
}
