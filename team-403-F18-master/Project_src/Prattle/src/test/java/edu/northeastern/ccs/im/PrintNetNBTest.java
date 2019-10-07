package edu.northeastern.ccs.im;

import java.io.*;
import java.net.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.channels.SocketChannel;

import static org.junit.jupiter.api.Assertions.*;

public class PrintNetNBTest {

    ServerSocket serverSocket;

    @BeforeEach
    void initialize() {
        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @AfterEach
    void closeServer() {
        try {
            serverSocket.close();
        } catch (Exception e) {

        }
    }

    @Test
    public void printNetNBTest() throws IOException {
        SocketNB connection = new SocketNB("localhost", 1234);
        PrintNetNB printNetNB_test1 = new PrintNetNB(connection);
        PrintNetNB printNetNB_test2 = new PrintNetNB(connection.getSocket());
        Message msg = Message.makeBroadcastMessage("yuheng", "hello from other side");
        assertTrue(printNetNB_test1.print(msg));
        assertTrue(printNetNB_test2.print(msg));
    }
}