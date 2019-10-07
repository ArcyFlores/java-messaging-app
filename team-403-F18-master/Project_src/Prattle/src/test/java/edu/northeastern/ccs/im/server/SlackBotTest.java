package edu.northeastern.ccs.im.server;

import org.junit.jupiter.api.Test;

public class SlackBotTest {
    @Test
    public void slackBotTest(){
        SlackBot.sendFailureNotice("test message for failure notice");
        SlackBot.sendSecurityConcern("testUser");
    }
}
