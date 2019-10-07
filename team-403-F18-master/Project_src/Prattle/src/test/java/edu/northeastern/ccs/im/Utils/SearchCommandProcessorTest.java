package edu.northeastern.ccs.im.Utils;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class SearchCommandProcessorTest {
    private String shortCommand = "short";
    private String longCommand = "long command for is search command test";
    private String searchFromSenderCommand = "search message from: sender testSearch";
    private String searchFromReceiverCommand = "search message from: receiver testSearch";
    private String searchBugCommand = "search message from: bug bug";

    @Test
    public void testGetMessage1() {
        assertEquals(Collections.<String>emptyList(), SearchCommandProcessor.getMessageFromSearch(shortCommand));
    }

    @Test
    public void testGetMessage2() {
        assertEquals(Collections.<String>emptyList(), SearchCommandProcessor.getMessageFromSearch(longCommand));
    }

    @Test
    public void testGetMessage3() {
        SearchCommandProcessor.getMessageFromSearch(searchFromReceiverCommand);
    }

    @Test
    public void testGetMessage4() {
        SearchCommandProcessor.getMessageFromSearch(searchFromSenderCommand);
    }

    @Test
    public void testGetMessage5() {
        SearchCommandProcessor.getMessageFromSearch(searchBugCommand);
    }
}
