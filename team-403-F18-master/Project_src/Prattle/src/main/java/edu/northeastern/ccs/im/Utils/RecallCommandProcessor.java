package edu.northeastern.ccs.im.Utils;

public class RecallCommandProcessor {
    protected static final String RECALL_COMMAND_HEAD = "RECALL";

    public boolean isRecallCommand(String command) {
        if (command.length() > RECALL_COMMAND_HEAD.length()) {
            String commandHead = command.substring(0, RECALL_COMMAND_HEAD.length());
            return commandHead.compareToIgnoreCase(RECALL_COMMAND_HEAD) == 0;
        }
        return false;
    }

    public String[] getRecallMessageArguments(String recallCommand) {
        String arguments = recallCommand.substring(RECALL_COMMAND_HEAD.length() + 1);
        String receiverName = arguments.substring(0, arguments.indexOf(' '));
        String content = arguments.substring(arguments.indexOf(' ') + 1);
        return new String[]{receiverName, content};
    }
}
