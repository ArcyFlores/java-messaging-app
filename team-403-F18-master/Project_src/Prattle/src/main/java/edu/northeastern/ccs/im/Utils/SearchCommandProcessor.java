package edu.northeastern.ccs.im.Utils;

import edu.northeastern.ccs.im.CRUD;
import edu.northeastern.ccs.im.server.Prattle;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class SearchCommandProcessor {

    private static final String SEARCH_COMMAND_HEAD = "SEARCH MESSAGE FROM: ";

    static Logger logger = Logger.getLogger(Prattle.class.getName());

    public static boolean isSearchCommand(String command) {
        if (command.length() > SEARCH_COMMAND_HEAD.length()) {
            String commandHead = command.substring(0, SEARCH_COMMAND_HEAD.length());
            return commandHead.compareToIgnoreCase(SEARCH_COMMAND_HEAD) == 0;
        }
        return false;
    }

    private static String[] getSearchCommandArguments(String command) {
        String arguments = command.substring(SEARCH_COMMAND_HEAD.length());
        return arguments.split("\\s+");
    }

    private static List<String> getMessageFromSender(String userName) {
        CRUD crud = new CRUD();
        return crud.getSentMessagesFileLines(userName);
    }

    private static List<String> getMessageFromReceiver(String userName) {
        CRUD crud = new CRUD();
        return crud.getReceivedMessagesFileLines(userName);
    }

    public static List<String> getMessageFromSearch(String command) {
        if (isSearchCommand(command)) {
            String[] arguments = getSearchCommandArguments(command);
            String attribute = arguments[0];
            String name = arguments[1];
            if (attribute.compareToIgnoreCase("sender") == 0) {
                return getMessageFromSender(name);
            } else if (attribute.compareToIgnoreCase("receiver") == 0) {
                return getMessageFromReceiver(name);
            } else return Collections.<String> emptyList();
        } else
            return Collections.<String> emptyList();

    }
}
