package edu.northeastern.ccs.im.Utils;

public class PrivateCommandProcessor {

    protected static final String PRIVATE_COMMAND_HEAD = "PRIVATE ";

    public boolean isPrivateCommand(String command) {
        if (command.length() > PRIVATE_COMMAND_HEAD.length()) {
            String commandHead = command.substring(0, PRIVATE_COMMAND_HEAD.length());
            return commandHead.compareToIgnoreCase(PRIVATE_COMMAND_HEAD) == 0;
        }

        return false;
    }

    public String[] getPrivateMessageArguments(String privateCommand) {
        String arguments = privateCommand.substring(PRIVATE_COMMAND_HEAD.length());
        System.out.println(arguments);
        String receiverName = arguments.substring(0, arguments.indexOf(' '));
        //System.out.println(receiverName);
        String content = arguments.substring(arguments.indexOf(' ') + 1);
        return new String[]{receiverName, content};
    }
}
