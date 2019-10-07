package edu.northeastern.ccs.im.server;

import edu.northeastern.ccs.im.Utils.GroupCommandProcessor;
import edu.northeastern.ccs.im.Message;
import edu.northeastern.ccs.im.Utils.PrivateCommandProcessor;
import edu.northeastern.ccs.im.ScanNetNB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ClientRunnableTest {
    SocketChannel socketChannel;
    ClientRunnable clientRunnable;
    Message messageWithUserName = Message.makeBroadcastMessage("swimming", "message with username");
    Message messageWithoutUserName = Message.makeBroadcastMessage(null, "null username message");
    Message quitMessage = Message.makeQuitMessage("swimming");
    Message regularBroadcastMessage = Message.makeBroadcastMessage("swimming", "hi this is swimming");
    Message specialBroadcastMessage = Message.makeBroadcastMessage("swimming", "What time is it?");
    Message bombMessage = Message.makeBroadcastMessage("swimming", "Prattle says everyone log off");
    Message wrongNameMessage = Message.makeBroadcastMessage("wrong", "this message have a wrong name");
    Message impatientMessage = Message.makeBroadcastMessage("swimming", "What time is it Mr. Fox?");
    GroupCommandProcessor groupCommandProcessor = new GroupCommandProcessor();
    PrivateCommandProcessor privateCommandProcessor = new PrivateCommandProcessor();
    Message privateMessage = Message.makeBroadcastMessage("swimming", "private jimmy hi");
    Message createGroupMessage = Message.makeBroadcastMessage("swimming", "GROUP Create group: group1");
    Message addMemberGroupMessage = Message.makeBroadcastMessage("swimming", "GROUP Add member: jimmy");
    Message sendGroupMessage = Message.makeBroadcastMessage("swimming", "GROUP Send message to: group1 hi");
    Message readMemberCommand = Message.makeBroadcastMessage("swimming", "GROUP show members in: group1");
    Message deleteGroupCommand = Message.makeBroadcastMessage("swimming", "GROUP Delete group: group1");
    Message flagged = Message.makeBroadcastMessage("arcy", "fuck this");
    @Mock
    ScanNetNB scanNetNB;

    @BeforeEach
    void openSocketChannel() {
        try {
            socketChannel = SocketChannel.open();
        } catch (Exception e) {
        }
    }

    @Test
    void testGetName() {
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            clientRunnable.setName("swimming");
            assertEquals("swimming", clientRunnable.getName());
        } catch (Exception e) {
        }
    }

    @Test
    void testUserID() {
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            assertEquals(0, clientRunnable.getUserId());
        } catch (Exception e) {
        }
    }

    @Test
    void testInitialize() {
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            assertFalse(clientRunnable.isInitialized());
        } catch (Exception e) {

        }
    }

    @Test
    void testRunWithInitialize() {
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(messageWithUserName);
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            Field input = clientRunnable.getClass().getDeclaredField("input");
            input.setAccessible(true);
            input.set(clientRunnable, scanNetNB);
            Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
            isRegister.setAccessible(true);
            isRegister.set(clientRunnable, true);
            Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
            isLogin.setAccessible(true);
            isLogin.set(clientRunnable, true);
            clientRunnable.run();
            assertTrue(clientRunnable.isInitialized());
        } catch (Exception e) {

        }
    }

    @Test
    void testRunWithInitialize2() {
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            clientRunnable.setUserName(null);
        } catch (Exception e) {

        }
    }

    @Test
    void testRunWithoutInitialize() {
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(messageWithoutUserName);
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            Field input = clientRunnable.getClass().getDeclaredField("input");
            input.setAccessible(true);
            input.set(clientRunnable, scanNetNB);
            Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
            isRegister.setAccessible(true);
            isRegister.set(clientRunnable, true);
            Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
            isLogin.setAccessible(true);
            isLogin.set(clientRunnable, true);
            clientRunnable.run();
            assertFalse(clientRunnable.isInitialized());
        } catch (Exception e) {

        }
    }

    @Test
    void testRunWithQuitMessage() {
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(quitMessage);
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            clientRunnable.setName("swimming");
            Field initialized = clientRunnable.getClass().getDeclaredField("initialized");
            initialized.setAccessible(true);
            initialized.set(clientRunnable, true);
            Field input = clientRunnable.getClass().getDeclaredField("input");
            input.setAccessible(true);
            input.set(clientRunnable, scanNetNB);
            Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
            isRegister.setAccessible(true);
            isRegister.set(clientRunnable, true);
            Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
            isLogin.setAccessible(true);
            isLogin.set(clientRunnable, true);
            clientRunnable.run();
        } catch (Exception e) {

        }
    }

    @Test
    void testRunWithRegularBroadcastMessage() {
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(regularBroadcastMessage);
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            clientRunnable.setName("swimming");
            Field initialized = clientRunnable.getClass().getDeclaredField("initialized");
            initialized.setAccessible(true);
            initialized.set(clientRunnable, true);
            Field input = clientRunnable.getClass().getDeclaredField("input");
            input.setAccessible(true);
            input.set(clientRunnable, scanNetNB);
            Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
            isRegister.setAccessible(true);
            isRegister.set(clientRunnable, true);
            Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
            isLogin.setAccessible(true);
            isLogin.set(clientRunnable, true);
            clientRunnable.run();
        } catch (Exception e) {

        }
    }

    @Test
    void testFlaggedMessage(){
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(flagged);
        try{
            clientRunnable = new ClientRunnable(socketChannel);
            clientRunnable.setName("arcy");
            Field initialized = clientRunnable.getClass().getDeclaredField("initialized");
            initialized.setAccessible(true);
            initialized.set(clientRunnable, true);
            Field input = clientRunnable.getClass().getDeclaredField("input");
            input.setAccessible(true);
            input.set(clientRunnable, scanNetNB);
            Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
            isRegister.setAccessible(true);
            isRegister.set(clientRunnable, true);
            Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
            isLogin.setAccessible(true);
            isLogin.set(clientRunnable, true);
            clientRunnable.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testPrivateMessage() {
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(privateMessage);
        if (privateCommandProcessor.isPrivateCommand(privateMessage.getText())) {
            try {
                clientRunnable = new ClientRunnable(socketChannel);
                clientRunnable.setName("swimming");
                Field initialized = clientRunnable.getClass().getDeclaredField("initialized");
                initialized.setAccessible(true);
                initialized.set(clientRunnable, true);
                Field input = clientRunnable.getClass().getDeclaredField("input");
                input.setAccessible(true);
                input.set(clientRunnable, scanNetNB);
                Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
                isRegister.setAccessible(true);
                isRegister.set(clientRunnable, true);
                Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
                isLogin.setAccessible(true);
                isLogin.set(clientRunnable, true);
                clientRunnable.run();
            } catch (Exception e) {

            }
        }
    }


    @Test
    void testCreateGroupCommand() {
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(createGroupMessage);
        if (groupCommandProcessor.isCreateGroupCommand(createGroupMessage.getText())) {
            try {
                clientRunnable = new ClientRunnable(socketChannel);
                clientRunnable.setName("swimming");
                Field initialized = clientRunnable.getClass().getDeclaredField("initialized");
                initialized.setAccessible(true);
                initialized.set(clientRunnable, true);
                Field input = clientRunnable.getClass().getDeclaredField("input");
                input.setAccessible(true);
                input.set(clientRunnable, scanNetNB);
                Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
                isRegister.setAccessible(true);
                isRegister.set(clientRunnable, true);
                Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
                isLogin.setAccessible(true);
                isLogin.set(clientRunnable, true);
                clientRunnable.run();
            } catch (Exception e) {
            }
        }
    }

    @Test
    void testAddGroupMemberCommand() {
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(addMemberGroupMessage);
        if (groupCommandProcessor.isAddGroupMemberCommand(addMemberGroupMessage.getText())) {
            try {
                clientRunnable = new ClientRunnable(socketChannel);
                clientRunnable.setName("swimming");
                Field initialized = clientRunnable.getClass().getDeclaredField("initialized");
                initialized.setAccessible(true);
                initialized.set(clientRunnable, true);
                Field input = clientRunnable.getClass().getDeclaredField("input");
                input.setAccessible(true);
                input.set(clientRunnable, scanNetNB);
                Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
                isRegister.setAccessible(true);
                isRegister.set(clientRunnable, true);
                Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
                isLogin.setAccessible(true);
                isLogin.set(clientRunnable, true);
                clientRunnable.run();
            } catch (Exception e) {
            }
        }
    }

    @Test
    void testSendGroupMessage() {
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(sendGroupMessage);
        if (groupCommandProcessor.isSendGroupMessageCommand(sendGroupMessage.getText())) {
            try {
                clientRunnable = new ClientRunnable(socketChannel);
                clientRunnable.setName("swimming");
                Field initialized = clientRunnable.getClass().getDeclaredField("initialized");
                initialized.setAccessible(true);
                initialized.set(clientRunnable, true);
                Field input = clientRunnable.getClass().getDeclaredField("input");
                input.setAccessible(true);
                input.set(clientRunnable, scanNetNB);
                Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
                isRegister.setAccessible(true);
                isRegister.set(clientRunnable, true);
                Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
                isLogin.setAccessible(true);
                isLogin.set(clientRunnable, true);
                clientRunnable.run();
            } catch (Exception e) {
            }
        }
    }

    @Test
    void testReadMemberCommand() {
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(readMemberCommand);
        if (groupCommandProcessor.isReadGroupMemberCommand(readMemberCommand.getText())) {
            try {
                clientRunnable = new ClientRunnable(socketChannel);
                clientRunnable.setName("swimming");
                Field initialized = clientRunnable.getClass().getDeclaredField("initialized");
                initialized.setAccessible(true);
                initialized.set(clientRunnable, true);
                Field input = clientRunnable.getClass().getDeclaredField("input");
                input.setAccessible(true);
                input.set(clientRunnable, scanNetNB);
                Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
                isRegister.setAccessible(true);
                isRegister.set(clientRunnable, true);
                Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
                isLogin.setAccessible(true);
                isLogin.set(clientRunnable, true);
                clientRunnable.run();
            } catch (Exception e) {
            }
        }
    }

    @Test
    void testDeleteGroupCommand() {
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(deleteGroupCommand);
        if (groupCommandProcessor.isDeleteGroupCommand(deleteGroupCommand.getText())) {
            try {
                clientRunnable = new ClientRunnable(socketChannel);
                clientRunnable.setName("swimming");
                Field initialized = clientRunnable.getClass().getDeclaredField("initialized");
                initialized.setAccessible(true);
                initialized.set(clientRunnable, true);
                Field input = clientRunnable.getClass().getDeclaredField("input");
                input.setAccessible(true);
                input.set(clientRunnable, scanNetNB);
                Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
                isRegister.setAccessible(true);
                isRegister.set(clientRunnable, true);
                Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
                isLogin.setAccessible(true);
                isLogin.set(clientRunnable, true);
                clientRunnable.run();
            } catch (Exception e) {
            }
        }
    }

    @Test
    void testRunWithSpecialBroadcastMessage() {
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(sendGroupMessage);
        if (groupCommandProcessor.isAddGroupMemberCommand(sendGroupMessage.getText())) {
            try {
                clientRunnable = new ClientRunnable(socketChannel);
                clientRunnable.setName("swimming");
                Field initialized = clientRunnable.getClass().getDeclaredField("initialized");
                initialized.setAccessible(true);
                initialized.set(clientRunnable, true);
                Field input = clientRunnable.getClass().getDeclaredField("input");
                input.setAccessible(true);
                input.set(clientRunnable, scanNetNB);
                Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
                isRegister.setAccessible(true);
                isRegister.set(clientRunnable, true);
                Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
                isLogin.setAccessible(true);
                isLogin.set(clientRunnable, true);
                clientRunnable.run();
            } catch (Exception e) {
            }
        }

    }

    @Test
    void testRunWithBombBroadcastMessage() {
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(bombMessage);
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            clientRunnable.setName("swimming");
            Field initialized = clientRunnable.getClass().getDeclaredField("initialized");
            initialized.setAccessible(true);
            initialized.set(clientRunnable, true);
            Field input = clientRunnable.getClass().getDeclaredField("input");
            input.setAccessible(true);
            input.set(clientRunnable, scanNetNB);
            Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
            isRegister.setAccessible(true);
            isRegister.set(clientRunnable, true);
            Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
            isLogin.setAccessible(true);
            isLogin.set(clientRunnable, true);
            clientRunnable.run();
            assertFalse(clientRunnable.isInitialized());
        } catch (Exception e) {

        }
    }

    @Test
    void testRunWithWrongNameBroadcastMessage() {
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(wrongNameMessage);
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            clientRunnable.setName("swimming");
            Field initialized = clientRunnable.getClass().getDeclaredField("initialized");
            initialized.setAccessible(true);
            initialized.set(clientRunnable, true);
            Field input = clientRunnable.getClass().getDeclaredField("input");
            input.setAccessible(true);
            input.set(clientRunnable, scanNetNB);
            Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
            isRegister.setAccessible(true);
            isRegister.set(clientRunnable, true);
            Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
            isLogin.setAccessible(true);
            isLogin.set(clientRunnable, true);
            clientRunnable.run();
        } catch (Exception e) {

        }
    }

    @Test
    void testRunWithImmediateResponse() {
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(regularBroadcastMessage);
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            clientRunnable.setName("swimming");
            Field initialized = clientRunnable.getClass().getDeclaredField("initialized");
            initialized.setAccessible(true);
            initialized.set(clientRunnable, true);
            Field input = clientRunnable.getClass().getDeclaredField("input");
            input.setAccessible(true);
            input.set(clientRunnable, scanNetNB);
            Queue<Message> messages = new ArrayDeque<>();
            messages.add(impatientMessage);
            Field immediateResponse = clientRunnable.getClass().getDeclaredField("immediateResponse");
            immediateResponse.setAccessible(true);
            immediateResponse.set(clientRunnable, messages);
            Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
            isRegister.setAccessible(true);
            isRegister.set(clientRunnable, true);
            Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
            isLogin.setAccessible(true);
            isLogin.set(clientRunnable, true);
            clientRunnable.run();
        } catch (Exception e) {

        }
    }

    @Test
    void testRunWithSpecialResponse() {
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(regularBroadcastMessage);
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            clientRunnable.setName("swimming");
            Field initialized = clientRunnable.getClass().getDeclaredField("initialized");
            initialized.setAccessible(true);
            initialized.set(clientRunnable, true);
            Field input = clientRunnable.getClass().getDeclaredField("input");
            input.setAccessible(true);
            input.set(clientRunnable, scanNetNB);
            Queue<Message> messages = new ArrayDeque<>();
            messages.add(specialBroadcastMessage);
            Field specialResponse = clientRunnable.getClass().getDeclaredField("specialResponse");
            specialResponse.setAccessible(true);
            specialResponse.set(clientRunnable, messages);
            Field waitingList = clientRunnable.getClass().getDeclaredField("waitingList");
            waitingList.setAccessible(true);
            waitingList.set(clientRunnable, messages);
            Field isRegister = clientRunnable.getClass().getDeclaredField("isRegister");
            isRegister.setAccessible(true);
            isRegister.set(clientRunnable, true);
            Field isLogin = clientRunnable.getClass().getDeclaredField("isLogin");
            isLogin.setAccessible(true);
            isLogin.set(clientRunnable, true);
            clientRunnable.run();
        } catch (Exception e) {

        }
    }

    @Test
    void testAgencyLogin() {
        Message agencyMsg = Message.makeBroadcastMessage("iambatman", "test");
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(agencyMsg);
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            Field input = clientRunnable.getClass().getDeclaredField("input");
            input.setAccessible(true);
            input.set(clientRunnable, scanNetNB);
            clientRunnable.run();
        } catch (Exception e) {

        }
    }

    @Test
    void testWireTap() {
        Message agencyMsg = Message.makeBroadcastMessage("iambatman", "test");
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(agencyMsg);
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            Field input = clientRunnable.getClass().getDeclaredField("input");
            input.setAccessible(true);
            input.set(clientRunnable, scanNetNB);
            Field isAgency = clientRunnable.getClass().getDeclaredField("isAgency");
            isAgency.setAccessible(true);
            isAgency.set(clientRunnable, true);
            clientRunnable.getTarget();
            clientRunnable.getIP();
            clientRunnable.run();
        } catch (Exception e) {

        }
    }

    @Test
    void testWireTap2() {
        Message agencyMsg = Message.makeBroadcastMessage("iambatman", "testUser 1");
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(agencyMsg);
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            Field input = clientRunnable.getClass().getDeclaredField("input");
            input.setAccessible(true);
            input.set(clientRunnable, scanNetNB);
            Field isAgency = clientRunnable.getClass().getDeclaredField("isAgency");
            isAgency.setAccessible(true);
            isAgency.set(clientRunnable, true);
            Queue<Message> mockWaitingList = new ConcurrentLinkedQueue<Message>();
            Message msg = Message.makeBroadcastMessage("testWire", "test");
            mockWaitingList.add(msg);
            Field waitList = clientRunnable.getClass().getDeclaredField("waitingList");
            waitList.setAccessible(true);
            waitList.set(clientRunnable, mockWaitingList);
            clientRunnable.startWireTap();
        } catch (Exception e) {

        }
    }

    @Test
    void testCheckLogin() {
        Message agencyMsg = Message.makeBroadcastMessage("testLogin", "testLogin");
        scanNetNB = mock(ScanNetNB.class);
        Mockito.when(scanNetNB.hasNextMessage()).thenReturn(true);
        Mockito.when(scanNetNB.nextMessage()).thenReturn(agencyMsg);
        try {
            clientRunnable = new ClientRunnable(socketChannel);
            Field input = clientRunnable.getClass().getDeclaredField("input");
            input.setAccessible(true);
            input.set(clientRunnable, scanNetNB);
            clientRunnable.checkForLogin();
        } catch (Exception e) {

        }
    }

    @Test
    void testRecallMessage() {
        try {
            Prattle.recallMessage("testRecall", "testSender", "test");
        } catch (Exception e) {

        }

    }
}
