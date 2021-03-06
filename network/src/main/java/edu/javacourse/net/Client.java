package edu.javacourse.net;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class Client
{
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10 ; i++) {
            SimpleClient simpleClient = new SimpleClient(i);
            simpleClient.start();
        }

    }


}

class SimpleClient extends Thread
{
    private static final  String[] COMMAND = {
            "HELLO", "MORNING", "DAY", "EVENING"
    };
    private int cmdNumber;


    public SimpleClient(int cmdNumber) {
        this.cmdNumber = cmdNumber;
    }

    @Override
    public void run(){
        try {
            //System.out.println("Started: " + LocalDateTime.now());
    Socket socket = new Socket("127.0.0.1", 25226);

    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    String command = COMMAND[cmdNumber % 4];
    String userName =  command + " " + "Vova";
    bw.write(userName);
    bw.newLine();
    bw.flush();

    String answer = br.readLine();
    System.out.println("Client got answer: " + answer);


    bw.close();
    br.close();

            //System.out.println("Finished: " + LocalDateTime.now());

            } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
