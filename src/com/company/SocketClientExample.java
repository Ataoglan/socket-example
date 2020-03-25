package com.company;

import com.company.threads.ReadThread;
import com.company.threads.WriteThread;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientExample {
    private String hostName;
    private int port;
    private String userName;

    public SocketClientExample(String hostName, int port){
        this.hostName = hostName;
        this.port = port;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void execute(){
        try {
            Socket socket = new Socket(hostName, port);

            System.out.println("Chat serverina baglandin !!!");

            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();
        } catch (UnknownHostException e) {
            System.out.println("Server bulunamadi");;
        } catch (IOException e) {
            System.out.println("I/O Hatasi: " + e.getMessage());
        }
    }

    public static void main(String[] args){
        if (args.length<2) return;

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        SocketClientExample clientExample = new SocketClientExample(hostname, port);
        clientExample.execute();
    }

}
