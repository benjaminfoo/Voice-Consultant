package de.bwulfert.voiceConsultant.server;

import de.bwulfert.assistant.server.plugins.*;
import de.bwulfert.voiceConsultant.server.plugins.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

// @Component
public class AssistantServer implements Runnable {

    private final static int DEFAULT_PORT = 1337;
    private int port = DEFAULT_PORT;

    private ServerSocket serverSocket;

    private Set<AbstractCommand> commands = new HashSet<AbstractCommand>();

    public AssistantServer() throws IOException {
        this(DEFAULT_PORT);
    }

    public AssistantServer(int port) throws IOException {
        this.port = port;

        this.serverSocket = new ServerSocket(this.port);
        initializeCommands();

        printStartup();
        waitAndHandleConnection();
    }

    private void initializeCommands(){
        commands.add(new ExecuteShellScript());
        commands.add(new KeywordCommand());
        commands.add(new SayCommand());
        commands.add(new ListenCommand());

        System.out.println("Loading commands ...");
        for (AbstractCommand command : commands) {
            System.out.println("Loaded command: " + command.getName() + " - Commands: " + Arrays.toString(command.getAliases()));
        }
    }

    public void run() {
       clientMap.forEach((socket, thread) -> {
           try {
               BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
               while (true) {
                   String st1 = br.readLine();
                   System.out.println("client: " + st1);
                   if (st1.toLowerCase().startsWith("say")) {
                       System.out.println("AssistantServer: The client wants me to say something ...");

                       String toBeSaid = "" + st1.toLowerCase().replaceFirst("say", "").trim();
                       System.out.println("AssistantServer: The client wants me to say: " + toBeSaid);

                       Runtime.getRuntime().exec("/home/pi/speech.sh " + toBeSaid);
                   }
               }
           } catch (IOException e) {
               System.out.println(e);
           }
       });
    }


    public void printStartup() {
        System.out.println("Home-Assistant-AssistantServer started @ " + new Date(System.currentTimeMillis()));
        System.out.println("Using address: " + serverSocket.getLocalSocketAddress());
    }

    /*
    public static void main(String args[]) throws IOException {
        AssistantServer homeServer = new AssistantServer();
        homeServer.printStartup();
        homeServer.waitAndHandleConnection();
    }
    */

    private Map<Socket, Thread> clientMap = new HashMap<>();
    public void waitAndHandleConnection() throws IOException {
        System.out.println("AssistantServer: Waiting for client-requests ...");
        Socket client = serverSocket.accept();
        System.out.println("AssistantServer: request accepted from client: " + client.getRemoteSocketAddress());

        Thread serverThread = new Thread(this);
        serverThread.start();

        // TODO: implement multiple clients and threads, make variables local
        // this.clientMap.put(client,  serverThread);

        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
        PrintStream ps2 = new PrintStream(client.getOutputStream());
        while (true) {
            String st = br2.readLine();
            ps2.println(st);
        }
    }

}