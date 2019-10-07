package edu.northeastern.ccs.im;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NextDoesNotExistExceptionTest {
    @Test
    void testException() {
        NextDoesNotExistException exp = new NextDoesNotExistException("test");
        assertEquals("test", exp.getMessage());
    }
}
