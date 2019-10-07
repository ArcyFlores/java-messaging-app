package edu.northeastern.ccs.im.Utils;//package edu.northeastern.ccs.im.server;

import org.junit.jupiter.api.Test;

public class LoggerSwitchTest {
    @Test
    public void testLogger() {
        LoggerSwitch.main(null);
        LoggerSwitch.main("test");
    }
}
