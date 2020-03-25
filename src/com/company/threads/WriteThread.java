package com.company.threads;

import com.company.SocketClientExample;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
/*WriteThread classimiz ise burada gonderilecek olan mesaji okuyup servera iletilmesini saglayacak*/
public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private SocketClientExample clientExample;

    public WriteThread(Socket socket, SocketClientExample clientExample) {
        this.socket = socket;
        this.clientExample = clientExample;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {

        Console console = System.console();

        String userName = console.readLine("\nEnter your name: ");
        clientExample.setUserName(userName);
        writer.println(userName);

        String text;

        do {
            text = console.readLine("[" + userName + "]: ");
            writer.println(text);

        } while (!text.equals("bye"));

        try {
            socket.close();
        } catch (IOException ex) {

            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
}
