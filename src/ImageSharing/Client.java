package ImageSharing;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Client {
    public static void main(String[] args) {
        System.out.println("\t\t Image Sharing Client");
        System.out.println("\t\t=======================");

        try {
            // Path to the image file to be sent
            Path imagePath = Paths.get("F:\\image1.jpg");

            // Read image data into a byte array
            byte[] imageData = Files.readAllBytes(imagePath);

            // Connect to the server
            Socket socket = new Socket("localhost", 4312);

            // Get the output stream to send data to the server
            OutputStream outputStream = socket.getOutputStream();

            // Send the image data to the server
            outputStream.write(imageData);
            System.out.println("Image Sent Successfully.");

            // Close resources
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
