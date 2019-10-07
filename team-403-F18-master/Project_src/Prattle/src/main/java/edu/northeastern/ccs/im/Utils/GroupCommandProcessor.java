package edu.northeastern.ccs.im.Utils;

public class GroupCommandProcessor {

    protected static final String GROUP_COMMAND_HEAD = "GROUP ";
    protected static final String GROUP_CREATE_COMMAND = "GROUP Create group: ";
    protected static final String GROUP_ADD_MEMBER_COMMAND = "GROUP Add member: ";
    protected static final String GROUP_SEND_MESSAGE_COMMAND = "GROUP Send message to: ";
    protected static final String GROUP_READ_MEMBER_COMMAND = "GROUP Show members in: ";
    protected static final String GROUP_DELETE_COMMAND = "GROUP Delete group: ";

    public boolean isGroupCommand(String command) {
        if (command.length() > GROUP_COMMAND_HEAD.length()) {
            String commandHead = command.substring(0, GROUP_COMMAND_HEAD.length());
            return commandHead.compareToIgnoreCase(GROUP_COMMAND_HEAD) == 0;
        }
        return false;
    }

    public boolean isCreateGroupCommand(String command) {
        if (command.length() > GROUP_CREATE_COMMAND.length()) {
            String commandHead = command.substring(0, GROUP_CREATE_COMMAND.length());
            return commandHead.compareToIgnoreCase(GROUP_CREATE_COMMAND) == 0;
        }
        return false;
    }

    public String getCreateGroupName(String createCommand) {
        return createCommand.substring(GROUP_CREATE_COMMAND.length());
    }

    public boolean isAddGroupMemberCommand(String command) {
        if (command.length() > GROUP_ADD_MEMBER_COMMAND.length()) {
            String commandHead = command.substring(0, GROUP_ADD_MEMBER_COMMAND.length());
            return commandHead.compareToIgnoreCase(GROUP_ADD_MEMBER_COMMAND) == 0;
        }
        return false;
    }

    public String[] getAddGroupMemberArguments(String addMemberCommand) {
        String arguments = addMemberCommand.substring(GROUP_ADD_MEMBER_COMMAND.length());
        String[] split = arguments.split("\\s+");
        return split;
    }

    public boolean isSendGroupMessageCommand(String command) {
        if (command.length() > GROUP_SEND_MESSAGE_COMMAND.length()) {
            String commandHead = command.substring(0, GROUP_SEND_MESSAGE_COMMAND.length());
            return commandHead.compareToIgnoreCase(GROUP_SEND_MESSAGE_COMMAND) == 0;
        }
        return false;
    }

    public String[] getSendGroupMessageArguments(String sendMessageCommand) {
        String arguments = sendMessageCommand.substring(GROUP_SEND_MESSAGE_COMMAND.length());
        String groupName = arguments.substring(0, arguments.indexOf(' '));
        String content = arguments.substring(arguments.indexOf(' ') + 1);
        return new String[]{groupName, content};
    }

    public boolean isReadGroupMemberCommand(String command) {
        if (command.length() > GROUP_READ_MEMBER_COMMAND.length()) {
            String commandHead = command.substring(0, GROUP_READ_MEMBER_COMMAND.length());
            return commandHead.compareToIgnoreCase(GROUP_READ_MEMBER_COMMAND) == 0;
        }
        return false;
    }

    public String getGroupNameFromReadCommand(String readCommand) {
        return readCommand.substring(GROUP_READ_MEMBER_COMMAND.length());
    }

    public boolean isDeleteGroupCommand(String command) {
        if (command.length() > GROUP_DELETE_COMMAND.length()) {
            String commandHead = command.substring(0, GROUP_DELETE_COMMAND.length());
            return commandHead.compareToIgnoreCase(GROUP_DELETE_COMMAND) == 0;
        }
        return false;
    }

    public String getGroupNameFromDeleteCommand(String readCommand) {
        return readCommand.substring(GROUP_DELETE_COMMAND.length());
    }


}
