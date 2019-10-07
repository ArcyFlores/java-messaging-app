package edu.northeastern.ccs.im;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MessageTest {
    private String myName = "swimming";
    private String broadcastMessage = "This is a broadcast message";
    private String helloMessage = "This is a hello message";
    private String nullMessage = null;
    private String helloHandle = "HLO";
    private String acknowledgeHandle = "ACK";
    private String noAcknowledgeHandle = "NAK";
    private String quitHandle = "BYE";
    private String broadcastHandle = "BCT";

    @Test
    void testMakeQuitMessage() {
        Message message = Message.makeQuitMessage(myName);
        assertEquals(myName, message.getName());
        assertNull(message.getText());
        assertFalse(message.isBroadcastMessage());
        assertFalse(message.isInitialization());
        assertFalse(message.isDisplayMessage());
        assertFalse(message.isAcknowledge());
        assertTrue(message.terminate());
        assertEquals("BYE 8 swimming 2 --", message.toString());
    }

    @Test
    void testMakeBroadcastMessage() {
        Message message = Message.makeBroadcastMessage(myName, broadcastMessage);
        assertEquals(myName, message.getName());
        assertEquals(broadcastMessage, message.getText());
        assertTrue(message.isBroadcastMessage());
        assertFalse(message.isInitialization());
        assertTrue(message.isDisplayMessage());
        assertFalse(message.isAcknowledge());
        assertFalse(message.terminate());
        assertEquals("BCT 8 swimming 27 This is a broadcast message", message.toString());
    }

    @Test
    void testMakeHelloMessage() {
        Message message = Message.makeHelloMessage(helloMessage);
        assertNull(message.getName());
        assertEquals(helloMessage, message.getText());
        assertFalse(message.isBroadcastMessage());
        assertTrue(message.isInitialization());
        assertFalse(message.isDisplayMessage());
        assertFalse(message.isAcknowledge());
        assertFalse(message.terminate());
        assertEquals("HLO 2 -- 23 This is a hello message", message.toString());
    }

    @Test
    void testMakeNoAcknowledgeMessage() {
        Message message = Message.makeNoAcknowledgeMessage();
        assertNull(message.getName());
        assertNull(message.getText());
        assertFalse(message.isBroadcastMessage());
        assertFalse(message.isInitialization());
        assertFalse(message.isDisplayMessage());
        assertFalse(message.isAcknowledge());
        assertFalse(message.terminate());
        assertEquals("NAK 2 -- 2 --", message.toString());
    }

    @Test
    void testMakeAcknowledgeMessage() {
        Message message = Message.makeAcknowledgeMessage(myName);
        assertEquals(myName, message.getName());
        assertNull(message.getText());
        assertFalse(message.isBroadcastMessage());
        assertFalse(message.isInitialization());
        assertFalse(message.isDisplayMessage());
        assertTrue(message.isAcknowledge());
        assertFalse(message.terminate());
        assertEquals("ACK 8 swimming 2 --", message.toString());
    }

    @Test
    void testMakeSimpleLoginMessage() {
        Message message = Message.makeSimpleLoginMessage(myName);
        assertEquals(myName, message.getName());
        assertNull(message.getText());
        assertFalse(message.isBroadcastMessage());
        assertTrue(message.isInitialization());
        assertFalse(message.isDisplayMessage());
        assertFalse(message.isAcknowledge());
        assertFalse(message.terminate());
        assertEquals("HLO 8 swimming 2 --", message.toString());
    }

    @Test
    void testMakeMessage() {
        Message messageHello = Message.makeMessage(helloHandle, myName, helloMessage);
        Message messageAcknowledge = Message.makeMessage(acknowledgeHandle, myName, nullMessage);
        Message messageNoAcknowledge = Message.makeMessage(noAcknowledgeHandle, myName, nullMessage);
        Message messageQuit = Message.makeMessage(quitHandle, myName, nullMessage);
        Message messageBroadcast = Message.makeMessage(broadcastHandle, myName, broadcastMessage);
    }

}