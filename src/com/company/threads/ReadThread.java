package com.company.threads;

import com.company.SocketClientExample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
/* ReadThread metodumuz bureada server tarafindan gelen inputlari okuyacak ve bizim consolumuza basacak */
public class ReadThread extends Thread{
    private BufferedReader bufferedReader;
    private Socket socket;
    private SocketClientExample socketClientExample;

    public ReadThread(Socket socket, SocketClientExample socketClientExample) {
        this.socket = socket;
        this.socketClientExample = socketClientExample;

        try {
            InputStream inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        } catch (IOException e) {
            System.out.println("Gonderilecek mesajda bir hata olustu.");
            e.printStackTrace();
        }
    }

    public void run() {
        while (true){
            try{
                String response = bufferedReader.readLine();
                System.out.println("\n" + response);

                // server mesajindan sonra username basmak icin ,
                if (socketClientExample.getUserName() != null) {
                    System.out.print("[" + socketClientExample.getUserName() + "]: ");
                }
            } catch (IOException e) {
                System.out.println("Serverdan gelen mesaji alirken bir hata olustu: " + e.getMessage());
                e.printStackTrace();
                break;
            }
        }
    }
}
