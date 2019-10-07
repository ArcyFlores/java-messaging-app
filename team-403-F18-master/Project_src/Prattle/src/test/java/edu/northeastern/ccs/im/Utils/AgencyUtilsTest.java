package edu.northeastern.ccs.im.Utils;

import edu.northeastern.ccs.im.server.ClientRunnable;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class AgencyUtilsTest {
    @Test
    void testGetTarget() {
        assertEquals("test", AgencyUtils.getTarget("test 4"));
    }

    @Test
    void testGetTimer() {
        assertTrue(AgencyUtils.getTimer("test 4") == 4);
    }

    @Test
    void isTarget1() {
        ClientRunnable clientRunnable = mock(ClientRunnable.class);
        Mockito.when(clientRunnable.isAgency()).thenReturn(true);
        Mockito.when(clientRunnable.getTarget()).thenReturn("test");
        assertTrue(AgencyUtils.isTarget("test", clientRunnable));
    }

    @Test
    void isTarget2() {
        ClientRunnable clientRunnable = mock(ClientRunnable.class);
        Mockito.when(clientRunnable.isAgency()).thenReturn(false);
        Mockito.when(clientRunnable.getTarget()).thenReturn("test");
        assertFalse(AgencyUtils.isTarget("test", clientRunnable));
        assertFalse(AgencyUtils.isTarget("test", "test", clientRunnable));
    }

    @Test
    void isTarget3() {
        ClientRunnable clientRunnable = mock(ClientRunnable.class);
        Mockito.when(clientRunnable.isAgency()).thenReturn(true);
        Mockito.when(clientRunnable.getTarget()).thenReturn("false");
        assertFalse(AgencyUtils.isTarget("test", clientRunnable));
        assertFalse(AgencyUtils.isTarget("test", "test", clientRunnable));
        assertTrue(AgencyUtils.isTarget("false", "test", clientRunnable));
        assertTrue(AgencyUtils.isTarget("test", "false", clientRunnable));
    }


}
