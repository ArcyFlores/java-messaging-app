package edu.northeastern.ccs.im.Utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrivateCommandProcessorTest {

    private static final PrivateCommandProcessor processor = new PrivateCommandProcessor();

    @Test
    void testIsPrivateCommand() {
        String lengthShortCommand = "CMD";
        String lengthLongCommand = "LONG COMMAND FOR TEST";
        String privateCommand = "PRIVATE TEST";
        assertFalse(processor.isPrivateCommand(lengthShortCommand));
        assertFalse(processor.isPrivateCommand(lengthLongCommand));
        assertTrue(processor.isPrivateCommand(privateCommand));
    }

    @Test
    void testGetPrivateMessageArguments() {
        String privateMessage = "private receiver hello";
        String[] result = {"receiver", "hello"};
        assertEquals(result[0], processor.getPrivateMessageArguments(privateMessage)[0]);
        assertEquals(result[1], processor.getPrivateMessageArguments(privateMessage)[1]);
    }
}
