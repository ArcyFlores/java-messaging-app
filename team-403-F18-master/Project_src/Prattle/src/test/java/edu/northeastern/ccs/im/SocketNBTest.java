package edu.northeastern.ccs.im;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.ServerSocket;
import java.nio.channels.SocketChannel;

import static org.junit.jupiter.api.Assertions.*;

public class SocketNBTest {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 4545;

    ServerSocket serverSocket;
    SocketNB socketNB;

    @BeforeEach
    void createServer() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (Exception e) {
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
    void testGetSocket() {
        try {
            socketNB = new SocketNB(HOST, PORT);
            assertEquals("/127.0.0.1:4545", socketNB.getSocket().getRemoteAddress().toString());
        } catch (Exception e) {

        }
        try {
            socketNB.close();
        } catch (Exception e) {

        }
    }
}
