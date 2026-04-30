import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UdpChatClient {
    public static void main(String[] args) throws Exception {
        String host = "100.101.102.103";
        int port = 5001;

        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName(host);
        Scanner scanner = new Scanner(System.in);

        byte[] buffer = new byte[1024];

        while (true) {
            String message = scanner.nextLine();
            byte[] data = message.getBytes(StandardCharsets.UTF_8);

            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, port);
            socket.send(packet);

            if (message.equalsIgnoreCase("exit")) {
                break;
            }

            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);

            String response = new String(responsePacket.getData(), 0, responsePacket.getLength(), StandardCharsets.UTF_8);
            System.out.println(response);

            if (response.equalsIgnoreCase("exit")) {
                break;
            }
        }

        socket.close();
    }
}