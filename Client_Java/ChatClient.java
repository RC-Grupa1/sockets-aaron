import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClient {
    public static void main(String[] args) {
        String host = "100.69.10.127";
        int port = 5000;

        try (
                Socket socket = new Socket(host, port);
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))
        ) {
            while (true) {
                String message = console.readLine();
                out.write(message);
                out.newLine();
                out.flush();

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                String response = in.readLine();
                if (response == null) break;

                System.out.println(response);

                if (response.equalsIgnoreCase("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}