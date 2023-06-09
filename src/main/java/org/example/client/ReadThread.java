package org.example.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread {
    private BufferedReader input;
    private Socket socket;
    private final ChatClient client;

    public ReadThread (Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Error getting input stream..: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String response = input.readLine();
                System.out.println("\n" + response);

                // prints the username after displaying the server's message.
                if (client.getUsername() != null) {
                    System.out.print("[" + client.getUsername() + "]: ");
                }
            } catch (IOException e) {
                System.err.println("Error reading from server: " + e.getMessage());
                break;
            }
        }
    }
}
