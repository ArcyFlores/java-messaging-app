package edu.northeastern.ccs.im.server;


import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;

public class SlackBot {
    public static void sendSecurityConcern(String userName) {
        SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
        api.call(new SlackMessage("Security Concern Bot", userName + " tried to login with a wrong password!"));
    }

    public static void sendFailureNotice(String failureDetail) {
        SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
        api.call(new SlackMessage("Failure Notice Bot", failureDetail));
    }
}
