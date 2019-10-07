package edu.northeastern.ccs.im.Utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GroupCommandProcessorTest {

    private static final GroupCommandProcessor processor = new GroupCommandProcessor();

    @Test
    void testIsGroupCommand() {
        String lengthShortCommand = "CMD";
        String lengthLongCommand = "LONG COMMAND FOR TEST";
        String groupCommand = "GROUP TEST";
        assertFalse(processor.isGroupCommand(lengthShortCommand));
        assertFalse(processor.isGroupCommand(lengthLongCommand));
        assertTrue(processor.isGroupCommand(groupCommand));
    }

    @Test
    void testIsCreateGroupCommand() {
        String lengthShortCommand = "CMD";
        String lengthLongCommand = "LONG COMMAND FOR TEST CREATE GROUP";
        String createGroupCommand = "GROUP CREATE GROUP: TEST";
        assertFalse(processor.isCreateGroupCommand(lengthShortCommand));
        assertFalse(processor.isCreateGroupCommand(lengthLongCommand));
        assertTrue(processor.isCreateGroupCommand(createGroupCommand));
    }

    @Test
    void testGetCreateGroupName() {
        String createGroupCommand = "GROUP CREATE GROUP: TEST";
        assertEquals("TEST", processor.getCreateGroupName(createGroupCommand));
    }

    @Test
    void testIsAddGroupMemberCommand() {
        String lengthShortCommand = "CMD";
        String lengthLongCommand = "LONG COMMAND FOR TEST ADD GROUP MEMBER COMMAND";
        String addMemberCommand = "GROUP Add member: GROUPNAME USERNAME";
        assertFalse(processor.isAddGroupMemberCommand(lengthShortCommand));
        assertFalse(processor.isAddGroupMemberCommand(lengthLongCommand));
        assertTrue(processor.isAddGroupMemberCommand(addMemberCommand));
    }

    @Test
    void testGetAddGroupMemberArguments() {
        String addMemberCommand = "GROUP Add member: GROUPNAME USERNAME";
        String[] exp = {"GROUPNAME", "USERNAME"};
        assertEquals(exp[0], processor.getAddGroupMemberArguments(addMemberCommand)[0]);
        assertEquals(exp[1], processor.getAddGroupMemberArguments(addMemberCommand)[1]);
    }

    @Test
    void testIsSendGroupMessageCommand() {
        String lengthShortCommand = "CMD";
        String lengthLongCommand = "LONG COMMAND FOR TEST SEND GROUP MESSAGE COMMAND";
        String sendMessageCommand = "GROUP Send message to: GROUPNAME test message";
        assertFalse(processor.isSendGroupMessageCommand(lengthShortCommand));
        assertFalse(processor.isSendGroupMessageCommand(lengthLongCommand));
        assertTrue(processor.isSendGroupMessageCommand(sendMessageCommand));
    }

    @Test
    void testGetSendGroupMessageArguments() {
        String sendMessageCommand = "GROUP Send message to: GROUPNAME test message";
        String[] exp = {"GROUPNAME", "test message"};
        assertEquals(exp[0], processor.getSendGroupMessageArguments(sendMessageCommand)[0]);
        assertEquals(exp[1], processor.getSendGroupMessageArguments(sendMessageCommand)[1]);
    }

    @Test
    void testIsReadGroupMemberCommand() {
        String lengthShortCommand = "CMD";
        String lengthLongCommand = "LONG COMMAND FOR TEST READ GROUP MEMBER COMMAND";
        String readMemberCommand = "GROUP Show members in: GROUPNAME";
        assertFalse(processor.isReadGroupMemberCommand(lengthShortCommand));
        assertFalse(processor.isReadGroupMemberCommand(lengthLongCommand));
        assertTrue(processor.isReadGroupMemberCommand(readMemberCommand));
    }

    @Test
    void testGetGroupNameFromReadCommand() {
        String readMemberCommand = "GROUP Show members in: GROUPNAME";
        assertEquals("GROUPNAME", processor.getGroupNameFromReadCommand(readMemberCommand));
    }

    @Test
    void testIsDeleteGroupCommand() {
        String lengthShortCommand = "CMD";
        String lengthLongCommand = "LONG COMMAND FOR TEST DELETE GROUP COMMAND";
        String deleteCommand = "GROUP Delete group: GROUPNAME";
        assertFalse(processor.isDeleteGroupCommand(lengthShortCommand));
        assertFalse(processor.isDeleteGroupCommand(lengthLongCommand));
        assertTrue(processor.isDeleteGroupCommand(deleteCommand));
    }

    @Test
    void testGetGroupNameFromDeleteCommand() {
        String deleteCommand = "GROUP Delete group: GROUPNAME";
        assertEquals("GROUPNAME", processor.getGroupNameFromDeleteCommand(deleteCommand));
    }


}
