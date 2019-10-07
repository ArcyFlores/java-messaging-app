package edu.northeastern.ccs.im.server;

import edu.northeastern.ccs.im.Message;
import edu.northeastern.ccs.im.server.ServerConstants;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class ServerConstantsTest {
    ServerConstants server_constant = new ServerConstants();

    String date_command_test = "What is the date?";
    String time_command_test = "What time is it?";
    String impatient_test = "What time is it Mr. Fox?";
    String hello_test = "Hello";
    String query_test = "How are you?";
    String cool_test = "WTF";
    String null_test = "";

    @Test
    public void test_dateBroadcastResponses() {
        GregorianCalendar cal = new GregorianCalendar();
        String dateMessage = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR);
        assertEquals(dateMessage,
                server_constant.getBroadcastResponses(date_command_test).get(0).getText());
    }

    @Test
    public void test_timeBroadcastResponses() {
        GregorianCalendar cal = new GregorianCalendar();
        String timeMessage = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
        assertEquals(timeMessage,
                server_constant.getBroadcastResponses(time_command_test).get(0).getText());
    }

    @Test
    public void test_impatientBroadcastResponses() {
        ArrayList<String> impatient_command_result = new ArrayList<>();
        GregorianCalendar cal = new GregorianCalendar();
        String impatient_message = "The time is now";
        Message impatientMessage = Message.makeBroadcastMessage("Mr. Fox",
                cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE));
        impatient_command_result.add(impatient_message);
        impatient_command_result.add(impatientMessage.getText());

        for (int i = 0; i < impatient_command_result.size(); i++) {
            assertEquals(impatient_command_result.get(i),
                    server_constant.getBroadcastResponses(impatient_test).get(i).getText());
        }
    }

    @Test
    public void test_helloBroadcastResponses() {
        ArrayList<String> hello_command_result = new ArrayList<>();
        hello_command_result.add("Hello.  How are you?");
        hello_command_result.add("I can communicate with you to test your code.");

        for (int i = 0; i < hello_command_result.size(); i++) {
            assertEquals(hello_command_result.get(i),
                    server_constant.getBroadcastResponses(hello_test).get(i).getText());
        }
    }

    @Test
    public void test_queryBroadcastResponses() {
        ArrayList<String> query_command_result = new ArrayList<>();
        query_command_result.add("Why are you asking me this?");
        query_command_result.add("I am a computer program. I run.");

        for (int i = 0; i < query_command_result.size(); i++) {
            assertEquals(query_command_result.get(i),
                    server_constant.getBroadcastResponses(query_test).get(i).getText());
        }
    }

    @Test
    public void test_coolBroadcastResponses() {
        ArrayList<String> cool_command_result = new ArrayList<>();
        cool_command_result.add("OMG ROFL TTYL");

        for (int i = 0; i < cool_command_result.size(); i++) {
            assertEquals(cool_command_result.get(i),
                    server_constant.getBroadcastResponses(cool_test).get(i).getText());
        }
    }

    @Test
    public void test_nullBroadcastResponses() {
        assertNull(server_constant.getBroadcastResponses(null_test));
    }
}