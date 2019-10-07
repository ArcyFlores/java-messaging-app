package edu.northeastern.ccs.im;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ScanNetNBTest {
    public String serverHost = "localhost";
    public int serverPort = 1234;

    private ServerSocketChannel serverSocket;
    private int clientSerial = 0;

    // test tcp non-blocking channel,
    @Test
    public void testScanNetNB() throws IOException, InterruptedException {
        // start server
        startServerNonBlocking();

        Thread.sleep(500); // wait server to be ready, before start client,

        // start clients
        startClientOnce();

        //startClientNullMessage();

        // shutdown server,
        Thread.sleep(3000); // wait client to be handled,
//        shutdownServer();
    }

    // start non-blocking server,
    @Test
    private void startServerNonBlocking() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = ServerSocketChannel.open();
                    serverSocket.configureBlocking(false);
                    serverSocket.socket().bind(new InetSocketAddress(serverHost, serverPort));
                    while (true) {
                        try {
                            SocketChannel socket = serverSocket.accept();
                            if (socket != null) {
                                socket.configureBlocking(false);
                                ScanNetNB input = new ScanNetNB(socket);
                                while (input.hasNextMessage()) {
                                    assertTrue(input.hasNextMessage());
                                    String message_string = input.nextMessage().getText();
                                    assertEquals("hello world", message_string);
                                }
                                NextDoesNotExistException exception = assertThrows(NextDoesNotExistException.class, () ->
                                {
                                    input.nextMessage();
                                });
                                assertEquals("No next line has been typed in at the keyboard", exception.getMessage());
                                input.close();
                            }
                        } catch (AssertionError e) {
                            System.err.println("Caught Assertion: " + e.toString());
                        } catch (Exception e) {
                            System.err.println("Caught Exception: " + e.toString());
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "t-server-threads").start();
    }

    private void startClientOnce() throws IOException {
        // start client in a new thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SocketChannel client = SocketChannel.open(new InetSocketAddress(serverHost, serverPort));
                    // write
                    String request = "hello world";
                    Message msg = Message.makeBroadcastMessage("yuheng", request);
                    byte[] bs = msg.toString().getBytes(StandardCharsets.UTF_8);
                    ByteBuffer buffer = ByteBuffer.wrap(bs);
                    while (buffer.hasRemaining()) {
                        client.write(buffer);
                    }
                    Thread.sleep(2000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t-channelClient-" + clientSerial++).start();
    }

    private void startClientNullMessage() {
        // start client in a new thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SocketChannel client = SocketChannel.open(new InetSocketAddress(serverHost, serverPort));
                    Thread.sleep(2000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t-channelClient-" + clientSerial++).start();
    }

}
