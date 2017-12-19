package de.bwulfert.voiceConsultant.testclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

class SimpleClient implements Runnable {

    private final static int DEFAULT_PORT = 1337;
    private int port = DEFAULT_PORT;

    private Thread clientThread;
    private Socket client;

    public SimpleClient(Socket client) {
        this(client, DEFAULT_PORT);
    }

    public SimpleClient(Socket client, int port) {
        this.client = client;
        this.port = port;
        clientThread = new Thread(this);
        clientThread.start();
    }

    public static void main(String args[]) throws IOException {
        try {
            System.out.println("sending request to peer....");
            // Socket clientSocket = new Socket("192.168.178.24", 1337);
            Socket clientSocket = new Socket("127.0.0.1", 1337);
            SimpleClient c = new SimpleClient(clientSocket);
            System.out.println("successfully conneted to " + clientSocket.getRemoteSocketAddress());

            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            PrintStream ps = new PrintStream(clientSocket.getOutputStream());
            while (true) {
                String s = br1.readLine();
                ps.println(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void run() {
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (true) {
                String st1 = br.readLine();
                System.out.println(" " + st1);
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }


}