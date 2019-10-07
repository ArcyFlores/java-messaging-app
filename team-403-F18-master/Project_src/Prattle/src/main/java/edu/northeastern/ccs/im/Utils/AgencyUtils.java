package edu.northeastern.ccs.im.Utils;

import edu.northeastern.ccs.im.server.ClientRunnable;

public class AgencyUtils {
    public static String getTarget(String msg) {
        String[] arguments = msg.split("\\s+");
        return arguments[0];
    }

    public static Integer getTimer(String msg) {
        String[] arguments = msg.split("\\s+");
        return Integer.parseInt(arguments[1]);
    }

    public static boolean isTarget(String sender, String receiver, ClientRunnable tt) {
        return tt.isAgency() && (tt.getTarget().matches(sender) || tt.getTarget().matches(receiver));
    }

    public static boolean isTarget(String sender, ClientRunnable tt) {
        return tt.isAgency() && tt.getTarget().matches(sender);
    }
}
