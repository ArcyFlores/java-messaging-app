package edu.northeastern.ccs.im.server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.net.Socket;

public class PrattleTest {
    Thread server;
    String line;
    File serverGetFile;
    File serverSendFile;
    private static final String HOST = "127.0.0.1";
    private static int PORT = 4545;


    @BeforeEach
    void runServer() {
        server = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Prattle.main(null);
                } catch (Exception e) {
                }
            }
        });
        server.start();
    }

    @AfterEach
    void killServer() {
        server.interrupt();
    }

    @Test
    public void testMain() throws Exception {
        System.setErr(new PrintStream(new FileOutputStream("server_get.txt")));
        System.setOut(new PrintStream(new FileOutputStream("server_send.txt")));

        Thread.sleep(2000);
        Socket client = new Socket(HOST, PORT);
        OutputStream serverGetFromClient = client.getOutputStream();
        PrintStream serverGet = new PrintStream(serverGetFromClient);
        serverGet.print("HLO 8 swimming 2 --");
        serverGet.flush();
        Thread.sleep(2000);
        serverGet.print("BCT 8 swimming 8 swimming");
        serverGet.flush();
        Thread.sleep(2000);
        serverGet.print("BCT 8 swimming 16 hiThisIsSwimming");
        serverGet.flush();
        Thread.sleep(2000);
        serverGet.print("BCT 8 swimming 16 what time is it?");
        serverGet.flush();
        Thread.sleep(2000);
        serverGet.print("BCT 8 swimming 25 group create group: test0");
        serverGet.flush();
        Thread.sleep(2000);
        serverGet.print("BCT 8 swimming 32 group add member: test0 testUser");
        serverGet.flush();
        Thread.sleep(2000);
        serverGet.print("BCT 8 swimming 28 group show members in: test0");
        serverGet.flush();
        Thread.sleep(2000);
        serverGet.print("BCT 8 swimming 24 private testSearch: test");
        serverGet.flush();
        Thread.sleep(2000);
        serverGet.print("BCT 8 swimming 36 search message from: sender swimming");
        serverGet.flush();
        Thread.sleep(2000);
        serverGet.print("BCT 8 swimming 22 recall testSearch test");
        serverGet.flush();
        Thread.sleep(2000);
        serverGet.print("BYE 8 swimming 2 --");
        serverGet.flush();
        Thread.sleep(2000);

        serverGetFile = new File("server_get.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(serverGetFile)));
        line = reader.readLine();
        //assertEquals("HLO 8 swimming 2 --", line);
        line = reader.readLine();
        //assertEquals("BCT 8 swimming 16 hiThisIsSwimming", line);
        line = reader.readLine();
        //assertEquals("BYE 8 swimming 2 --",line);
        reader.close();
        serverGetFile.delete();

        serverSendFile = new File("server_send.txt");
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(serverSendFile)));
        line = reader.readLine();
        //assertEquals("\tBCT 16 Welcome swimming 16  Enter password:", line);
        line = reader.readLine();
        //assertEquals("\tBCT 8 swimming 16 hiThisIsSwimming",line);
        reader.close();
        serverSendFile.delete();
    }

}
