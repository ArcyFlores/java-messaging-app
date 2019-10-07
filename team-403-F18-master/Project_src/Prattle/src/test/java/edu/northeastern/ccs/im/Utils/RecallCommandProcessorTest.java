package edu.northeastern.ccs.im.Utils;

import edu.northeastern.ccs.im.Utils.RecallCommandProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecallCommandProcessorTest {
    private static final RecallCommandProcessor processor = new RecallCommandProcessor();

    @Test
    void testIsPrivateCommand() {
        String lengthShortCommand = "cmd";
        String lengthLongCommand = "LONG COMMAND FOR TEST";
        String privateCommand = "RECALL TEST";
        assertFalse(processor.isRecallCommand(lengthShortCommand));
        assertFalse(processor.isRecallCommand(lengthLongCommand));
        assertTrue(processor.isRecallCommand(privateCommand));
    }

    @Test
    void testGetPrivateMessageArguments() {
        String privateMessage = "recall receiver hello";
        String[] result = {"receiver", "hello"};
        assertEquals(result[0], processor.getRecallMessageArguments(privateMessage)[0]);
        assertEquals(result[1], processor.getRecallMessageArguments(privateMessage)[1]);
    }
}

