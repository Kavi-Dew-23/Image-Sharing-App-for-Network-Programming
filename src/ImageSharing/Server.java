package ImageSharing;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	static int port = 6060;
	
    public static void main(String[] args) {
        System.out.println("\t\t Image Sharing Server");
        System.out.println("\t\t=======================");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Waiting for client connection...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");

                InputStream inputStream = socket.getInputStream();

                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                byte[] data = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, bytesRead);
                }
                buffer.flush();

                byte[] imageData = buffer.toByteArray();
                FileOutputStream outputStream = new FileOutputStream("F:\\receivedimage2.jpg");
                outputStream.write(imageData);

                System.out.println("Image received and saved successfully.");

                outputStream.close();
                inputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
