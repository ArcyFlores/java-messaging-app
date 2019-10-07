package edu.northeastern.ccs.im;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.io.PrintWriter;

/**
 * The type Crud.
 */
public final class CRUD {

    private static final String RECEIVED_MESSAGES = "Received_Messages.txt";

    // Project database directory
    private static String dbDirectory =
            System.getProperty("user.dir") + File.separator + "Prattle_DB";

    // All-User data path info
    private static String userDataDirectoryName =
            dbDirectory + File.separator + "User_Data";
    private static String allUserDataFileName = "All_Users.txt";
    private static Path allUserFilePath = Paths.get(userDataDirectoryName, allUserDataFileName);


    // Naughty list
    private static String flaggedMessagesDirectory =
            dbDirectory + File.separator + "Flagged_Messages";


    // Individual member data path info
    // The remaining path is defined when user is added
    private static String memberProfilesDirectoryName =
            userDataDirectoryName + File.separator + "Member_Profiles";

    // Group data
    // The remaining path is defined when group is created
    private static String groupDataDirectoryName =
            dbDirectory + File.separator + "Group_Data";
    private static String allGroupsDataDirectoryName =
            groupDataDirectoryName + File.separator + "All_Groups";

    // Subpoena data
    private static String subpoenaDataDirectoryName = dbDirectory + File.separator + "Bat_Cave";


    /**
     * Returns true if file in specified path exists.
     *
     * @param filePath the file path
     * @return return true if file exists, otherwise return false
     */
    boolean fileExists(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * Getter for All_User filepath.
     *
     * @return a string representing the All_User file path.
     */
    String getAllUserFilePath() {
        return allUserFilePath.toString();
    }

    /**
     * Getter for member profile directory path.
     *
     * @return a string representing the member profile directory
     */
    String getMemberProfilesDirectoryName() {
        return memberProfilesDirectoryName;
    }

    /**
     * Getter for group directory path.
     *
     * @return a string representing the directory which contains group data
     */
    String getGroupDataDirectoryName() {
        return groupDataDirectoryName;
    }

    /**
     * Initializes Prattle database if not present.
     *
     * @return return true if db was created, false if it did not
     * (ideally, this means it already existed)
     */
    private boolean initPrattleDB() {
        if (!new File(dbDirectory).exists()) {
            new File(dbDirectory).mkdirs();
            return true;
        }
        return false;
    }

    /**
     * Initilaizes the directory that houses All_User_Data data file.
     *
     * @return boolean boolean
     */
    boolean initUserDataDirectory() {
        if (!new File(userDataDirectoryName).exists()) {
            new File(userDataDirectoryName).mkdirs();
            return true;
        }
        return false;
    }

    /**
     * Initializes the directory that houses all member profiles.
     *
     * @return return true if db was created, false if it did not (ideally, this means it already
     * existed)
     */
    public boolean initMemberProfilesDirectory() {
        if (!new File(memberProfilesDirectoryName).exists()) {
            new File(memberProfilesDirectoryName).mkdirs();
            return true;
        }
        return false;
    }

    /**
     * Initializes the directory that houses all data pertaining to groups.
     *
     * @return true if db was created, false if it did not (ideally, this means it already existed)
     */
    boolean initGroupDataDirectory() {
        if (!new File(groupDataDirectoryName).exists()) {
            new File(groupDataDirectoryName).mkdirs();
            return true;
        }
        return false;
    }

    /**
     * Initializes the directory that houses all data pertaining to groups.
     *
     * @return true if db was created, false if it did not (ideally, this means it already existed)
     */
    public boolean initSubpoenaDataDirectory() {
        if (!new File(subpoenaDataDirectoryName).exists()) {
            new File(subpoenaDataDirectoryName).mkdirs();
            return true;
        }
        return false;
    }

    private String getSubpoenaFilePath(String target) {
        return subpoenaDataDirectoryName + File.separator + "subpeona_target_" + target + ".txt";
    }

    /**
     * Write into subpoena file.
     *
     * @param target the target
     * @param msg    the msg
     */
    public void writeIntoSubpoenaFile(String target, Message msg) {
        appendToFile(getSubpoenaFilePath(target), msg.getName() + ": " + msg.getText());
    }

    /**
     * Init subpoena file boolean.
     *
     * @param target the target
     * @return the boolean
     */
    public boolean initSubpoenaFile(String target) {
        String path = subpoenaDataDirectoryName + File.separator + "subpeona_target_" + target + ".txt";
        File file = new File(path);
        try {
            return file.createNewFile();
        } catch (Exception e) {

        }
        return true;
    }


    /**
     * Initilaizes the directory that contains the groups.
     *
     * @return boolean boolean
     */
    public boolean initAllGroupsDirectory() {
        if (!new File(allGroupsDataDirectoryName).exists()) {
            new File(allGroupsDataDirectoryName).mkdirs();
            return true;
        }
        return false;
    }

    /**
     * Initializes "All_Users.txt" file with fields (userID, userName, lastLogin).
     *
     * @return true if db was created, false if it did not (ideally, this means it already existed)
     */
    public boolean initAllUserDataFile() {
        initPrattleDB();
        //initial entry represents columns in the database
        String initEntry = "UserID, UserName, LastLogin";
        try {
            //create and write to file; does not create file if file exists
            Files.write(allUserFilePath, initEntry.getBytes(), StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            e.getMessage();
            return false;
        }
        return true;
    }

    /**
     * Initializes directory that represents a member profile.
     *
     * @param userName to append to filepath
     * @return
     */
    private String initNewMember(String userName) {
        String path = memberProfilesDirectoryName + File.separator + userName;
        if (!new File(path).exists()) {
            new File(path).mkdirs();
            return path;
        }
        return null;
    }

    /**
     * Initializes the directory that contains flagged messages and users.
     *
     * @return true if directory was created, otherwise false
     */
    public boolean initFlaggedMessagesDirectory() {
    if (!new File(flaggedMessagesDirectory).exists()) {
            new File(flaggedMessagesDirectory).mkdirs();
            return true;
        }
      return false;
    }

    /**
     * Initializes directory that represents a user who has been flagged.
     *
     * @param userName to append to filepath
     * @return string string
     */
    String initNewFlaggedUserDirectory(String userName) {
        String path = flaggedMessagesDirectory + File.separator + userName;
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
        return path;
    }

    /**
     * Initializes a new group directory if it does not exist.
     *
     * @param groupName
     * @return a string denoting to group directory name
     */
    private String initNewGroupDirectory(String groupName) {
        String path = allGroupsDataDirectoryName + File.separator + groupName;
        if (!new File(path).exists()) {
            new File(path).mkdirs();
            return path;
        }
        return null;
    }

    /**
     * Creates new group entry, containing a groupMembers.txt, messages.txt.
     *
     * @param groupName the group name
     * @param creator   the creator
     * @return boolean boolean
     */
    public boolean createNewGroup(String groupName, String creator) {
        initAllGroupsDirectory();
        String newGroupDirectory = initNewGroupDirectory(groupName);

        String initMemberEntry = "";
        String initMessagesEntry = "Sender,Time";
        Path groupMemberPath = Paths.get(newGroupDirectory, "Group_Members.txt");
        Path groupMessagesPath = Paths.get(newGroupDirectory, "Group_Messages.txt");
        try {
            Files.write(groupMemberPath, initMemberEntry.getBytes(), StandardOpenOption.CREATE_NEW);
            Files.write(groupMessagesPath, initMessagesEntry.getBytes(), StandardOpenOption.CREATE_NEW);
            return true;
        } catch (IOException e) {
            e.getMessage();
            return false;
        }
    }

    /**
     * Add member to specified group.
     *
     * @param userName  the user name
     * @param groupName the group name
     * @return boolean boolean
     */
    public boolean addMemberToGroup(String userName, String groupName) {
        String groupDirectory = allGroupsDataDirectoryName + File.separator + groupName;
        Path groupPath = Paths.get(groupDirectory, "Group_Members.txt");
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(groupPath.toString(), true))) {
            bw.append(userName);
            bw.newLine();
        } catch (IOException e) {
            e.getMessage();
        }
        return true;
    }

    /**
     * Adds sent message to user's "Sent_Messages" file.
     *
     * @param userName  the user name
     * @param message   the message
     * @param groupName the group name
     * @return boolean boolean
     */
    public boolean logGroupMessage(String userName, String message, String groupName) {
        String groupDirectory = allGroupsDataDirectoryName + File.separator + groupName;
        Path groupPath = Paths.get(groupDirectory, "Group_Messages.txt");
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(groupPath.toString(), true))) {
            bw.append(userName + " " + message + " " + groupName);
            bw.newLine();
        } catch (IOException e) {
            e.getMessage();
        }
        return true;
    }

    /**
     * Adds sent message to user's "Sent_Messages" file.
     *
     * @param message  the message
     * @param userName the user name
     * @param receiver the receiver
     * @return boolean boolean
     */
    boolean addSentMessage(String message, String userName, String receiver) {
        Path path = Paths.get(
                memberProfilesDirectoryName, userName + File.separator + "Sent_Messages.txt");
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(path.toString(), true))) {
            bw.newLine();
            bw.append(receiver);
            bw.append(", ");
            bw.append(message);
        } catch (IOException e) {
            e.getMessage();
        }
        return true;
    }

    /**
     * Add sent message boolean.
     *
     * @param message  the message
     * @param userName the user name
     * @param receiver the receiver
     * @param ip       the ip
     * @return the boolean
     */
    public boolean addSentMessage(String message, String userName, String receiver, String ip) {
        Path path = Paths.get(
                memberProfilesDirectoryName, userName + File.separator + "Sent_Messages.txt");
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(path.toString(), true))) {
            bw.newLine();
            bw.append(userName);
            bw.append(", ");
            bw.append(ip);
            bw.append(", ");
            bw.append(receiver);
            bw.append(", ");
            bw.append(message);
        } catch (IOException e) {
            e.getMessage();
        }
        return true;
    }

    /**
     * Adds sent message to user's "Sent_Messages" file.
     *
     * @param message  the message
     * @param userName the user name
     * @param sender   the sender
     * @return boolean boolean
     */
    boolean addReceivedMessage(String message, String userName, String sender) {
        Path path = Paths.get(
                memberProfilesDirectoryName, userName + File.separator + RECEIVED_MESSAGES);
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(path.toString(), true))) {
            bw.newLine();
            bw.append(sender);
            bw.append(", ");
            bw.append(message);
        } catch (IOException e) {
            e.getMessage();
        }
        return true;
    }

    /**
     * Add received message boolean.
     *
     * @param message  the message
     * @param userName the user name
     * @param sender   the sender
     * @param ip       the ip
     * @return the boolean
     */
    public boolean addReceivedMessage(String message, String userName, String sender, String ip) {
        Path path = Paths.get(
                memberProfilesDirectoryName, userName + File.separator + RECEIVED_MESSAGES);
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(path.toString(), true))) {
            bw.newLine();
            bw.append(sender);
            bw.append(", ");
            bw.append(userName);
            bw.append(", ");
            bw.append(ip);
            bw.append(", ");
            bw.append(message);
        } catch (IOException e) {
            e.getMessage();
        }
        return true;
    }

    /**
     * Creates new member profile directory containing a userInfo.txt , sentMessages.txt,
     * receivedMessages.txt.
     *
     * @param userName the user name
     * @param userID   the user id
     * @param password the password
     * @param salt     the salt
     * @return boolean boolean
     */
    public boolean createNewMemberProfile(String userName, String userID, String password, String salt) {
        initMemberProfilesDirectory();
        String newUserDirectory = initNewMember(userName);
        String initUIEntry = String.format("UserID, UserName, Password, Salt, DateJoined\n" +
                userID + "," + userName + "," + password + "," + salt + "," + LocalDateTime.now());
        String initSMEntry = "Recipient, MessageContents";
        String initRMEntry = "Sender, MessageContents";
        Path userDataPath = Paths.get(newUserDirectory, "User_Info.txt");
        Path sentMessagesPath = Paths.get(newUserDirectory, "Sent_Messages.txt");
        Path receivedMessagesPath = Paths.get(newUserDirectory, RECEIVED_MESSAGES);
        try {
            //create and write to file; does not create file if file exists
            Files.write(userDataPath, initUIEntry.getBytes(), StandardOpenOption.CREATE_NEW);
            Files.write(sentMessagesPath, initSMEntry.getBytes(), StandardOpenOption.CREATE_NEW);
            Files.write(receivedMessagesPath, initRMEntry.getBytes(), StandardOpenOption.CREATE_NEW);
            return true;
        } catch (IOException e) {
            e.getMessage();
            return false;
        }
    }

    /**
     * Reads file from AbsolutePath.
     *
     * @param path the path
     * @return the list
     */
    public List<String> readFile(Path path) {
        try {
            //create list to hold lines
            List<String> list = Files.readAllLines(path);
            // this line will likely have to be changed to achieve more useful functionality.
            // for now it prints the contents of the DB file (useful for testing)
            list.forEach(line -> System.out.println(line));
            //this is probably all we need
            return list;
        } catch (IOException e) {
          e.getMessage();
        }
        return null;
    }

    /**
     * Read file from group member list.
     *
     * @param groupName the group name
     * @return the list
     */
    public List<String> readFileFromGroupMember(String groupName) {
        String groupDirectory = allGroupsDataDirectoryName + File.separator + groupName;
        Path path = Paths.get(groupDirectory, "Group_Members.txt");
        return readFile(path);
    }

    /**
     * Writes string to absolutePath. Designed to write to database from Prattle ClientRunnable.
     * (Intention is to remove parameters, and instead use userID and userName fields in
     * ClientRunnable.
     *
     * @param userId   from Prattle.ClientRunnable
     * @param userName from Prattle.ClientRunnable
     * @return the boolean
     */
    @SuppressWarnings("Duplicates")
    public boolean writeToDBFromRunnable(String userId, String userName) {
        // if file does not exist, create file with fields (refer to intiFile() description)
        this.initAllUserDataFile();
        //entry is initialized with user data, and last login (these parameters will be replaced with
        //fields from ClientRunnable
        String userEntry = String.format("%s, %s, %s", userId, userName, LocalDateTime.now());
        //create BufferedWriter(FileWriter) used to append input to .txt file
        //'true' indicates append rather than overwrite to file
        try (BufferedWriter buffWriter = new BufferedWriter(
                new FileWriter(allUserFilePath.toString(), true))) {
            //append user data to database file
            buffWriter.append(userEntry);
            buffWriter.newLine();
        } catch (IOException e) {
            e.getMessage();
            return false;
        }
        return true;
    }

    /**
     * Writes string to absolutePath. Designed to write to database from Prattle ClientRunnable.
     * (Intention is to remove parameters, and instead use userID and userName fields in
     * ClientRunnable. This class is for testing purposes.
     *
     * @param userId   from Prattle.ClientRunnable
     * @param userName from Prattle.ClientRunnable
     * @param time     from test
     * @return the boolean
     */
    @SuppressWarnings("Duplicates")
    boolean writeToDBFromRunnable(String userId, String userName, String time) {
        // if file does not exist, create file with fields (refer to intiFile() description)
        initAllUserDataFile();
        //entry is initialized with user data, and last login
        String userEntry = String.format("%s, %s, %s", userId, userName, time);
        //create BufferedWriter(FileWriter) used to append input to .txt file
        //'true' indicates append rather than overwrite to file
        try (BufferedWriter buffWriter = new BufferedWriter(
                new FileWriter(allUserFilePath.toString(), true))) {
            //append user data to database file
            buffWriter.append(userEntry);
            buffWriter.newLine();
        } catch (IOException e) {
            e.getMessage();
            return false;
        }
        return true;
    }

    /**
     * Recursively deletes group directories and all contents.
     *
     * @param groupName the group name
     * @return boolean boolean
     */
    public boolean deleteGroup(String groupName) {
        Path groupFilePath = Paths.get(allGroupsDataDirectoryName + File.separator + groupName);
        return deleteMisc(groupFilePath);
    }

    /**
     * Recursively deletes member profile directory and all contents.
     *
     * @param userName the user name
     * @return boolean boolean
     */
    boolean deleteMemberProfile(String userName) {
        Path userFilePath = Paths.get(memberProfilesDirectoryName + File.separator + userName);
        return deleteMisc(userFilePath);
    }

    /**
     * Helper function used in recursive delete functions.
     *
     * @param filePath the file path
     * @return boolean boolean
     */
    boolean deleteMisc(Path filePath) {
        try (Stream<Path> stream = Files.walk(filePath)) {
            stream.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Determines whether the member's profile already exists.
     *
     * @param userName the user name
     * @return true if it does false, otherwise
     */
    public boolean memberProfileExists(String userName) {
        Path memberProfilePath = Paths.get(memberProfilesDirectoryName + File.separator + userName);
        File tmpDir = new File(String.valueOf(memberProfilePath));
        return tmpDir.exists();
    }

    /**
     * Read password from user string.
     *
     * @param userName the user name
     * @return the string
     */
    public String readPasswordFromUser(String userName) {
        String profileDirectory = memberProfilesDirectoryName + File.separator + userName;
        Path path = Paths.get(profileDirectory, "User_Info.txt");
        File file = new File(path.toString());
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            String temp = br.readLine();
            line = br.readLine();
            String[] lines = line.split(",");
            System.out.println(lines[2]);
            return lines[2];
        } catch (Exception e) {

        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e) {
            }
        }
        return "";
    }

    /**
     * Read salt from user string.
     *
     * @param userName the user name
     * @return the string
     */
    public String readSaltFromUser(String userName) {
        String profileDirectory = memberProfilesDirectoryName + File.separator + userName;
        Path path = Paths.get(profileDirectory, "User_Info.txt");
        File file = new File(path.toString());
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            String temp = br.readLine();
            line = br.readLine();
            String[] lines = line.split(",");
            System.out.println(lines[3]);
            return lines[3];
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e) {

            }
        }
        return "";
    }

    /**
     * Init missed message file boolean.
     *
     * @param userName the user name
     * @return the boolean
     * @returnre
     */
    public boolean initMissedMessageFile(String userName) {
        String missedMessagePathString = memberProfilesDirectoryName + File.separator + userName + File.separator + "Missed_Messages.txt";
        if (!this.fileExists(missedMessagePathString)) {
            String initMMEntry = String.format("DateReceived,Sender,MessageContents");
            Path missedMessagesPath = Paths.get(missedMessagePathString);
            try {
                Files.write(missedMessagesPath, initMMEntry.getBytes(), StandardOpenOption.CREATE_NEW);
                return true;
            } catch (IOException e) {
            }
        }
        return false;
    }

    /**
     * Adds sent message to user's "Missed_Messages" file.
     *
     * @param message  the message sent
     * @param receiver the receiver of the message (user)
     * @param sender   the sender's userID
     * @param senderIP the sender ip
     * @return true if the message was successfully written
     */
    public boolean addMissedMessage(String message, String receiver, String sender, String senderIP) {
        this.initMissedMessageFile(receiver);
        Path path = Paths.get(
                memberProfilesDirectoryName, receiver + File.separator + "Missed_Messages.txt");
        return this.appendMessageUserRecord(message, path.toString(), sender, senderIP);
    }


    /**
     * Read missed message list.
     *
     * @param userName the user name
     * @return the list
     */
    public List<ArrayList<String>> readMissedMessage(String userName) {
        String missedMessagePathString = memberProfilesDirectoryName + File.separator + userName + File.separator + "Missed_Messages.txt";

        List<ArrayList<String>> missedMessages = new ArrayList<>();
        if (!this.fileExists(missedMessagePathString)) {
            return null;
        }

        String profileDirectory = memberProfilesDirectoryName + File.separator + userName;
        Path path = Paths.get(profileDirectory, "Missed_Messages.txt");
        File file = new File(path.toString());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                ArrayList<String> missedMessage = new ArrayList<String>();
                missedMessage.add(parts[1]);
                missedMessage.add(parts[2]);
                missedMessages.add(missedMessage);
            }
        } catch (IOException e) {

        }
        return missedMessages;
    }

    /**
     * Adds a flagged message to the flagged user's file
     *
     * @param naughtyMessage the naughty message
     * @param naughtyUser    the user who is being flagged for inappropriate content
     * @param naughtyUserIP  the IP of the user who sent the inappropriate message
     * @return boolean boolean
     */
    public boolean addFlaggedMessage(String naughtyMessage, String naughtyUser, String naughtyUserIP){
        this.createFlaggedUserProfile(naughtyUser);
        Path path = Paths.get(flaggedMessagesDirectory + File.separator + naughtyUser + File.separator + "Naughty_Messages.txt");
        return this.appendNaughtytMessageEntry(naughtyUser, naughtyMessage, naughtyUserIP, path.toString());
    }

    /**
     * Creates a flagged user profile.
     * @param userName the user to create flagged messages for
     * @return true if the flagged user profile was created properly
     */
    private boolean createFlaggedUserProfile(String userName){
        String flaggedUserDirectory = initNewFlaggedUserDirectory(userName);
        String initFlaggedEntry = "Date,Sender,Message,Receiver,SenderIP";
        Path naughtyMessagesPath = Paths.get(flaggedUserDirectory, "Naughty_messages.txt");
        try{
            Files.write(naughtyMessagesPath, initFlaggedEntry.getBytes(), StandardOpenOption.CREATE_NEW);
            return true;
        }catch (IOException e) {
            return false;
        }
    }

    /**
     * Writes entry to flagged messages folder for given naughty user.
     *
     * @param naughtyUsername the user who is being flagged for inappropriate content
     * @param naughtyMessage the message containing the inappropriate content
     * @param naughtyUserIP the IP of the user who sent the inappropriate message
     * @param filePath the path to write to
     * @return
     */
    private boolean appendNaughtytMessageEntry(String naughtyUsername, String naughtyMessage, String naughtyUserIP, String filePath){
        initNewFlaggedUserDirectory(naughtyUsername);
        String appendMessage = this.getNYdate() + "," + naughtyUsername + "," + naughtyMessage + "," + naughtyUserIP;
        return this.appendToFile(filePath, appendMessage);
    }

    /**
     * Retrieves the inappropriate messages that have been deemed inappropriate.
     *
     * @param userName the username who has the naughty messages
     * @return list list
     */
    public List<String> getNaughtyMessages(String userName){
        List<String> naughtyLines = this.getNaughtyMessagesFileLines(userName);
        return this.parseMessages(naughtyLines);
    }

    /**
     * Parses a list of filelines and returns messages in the following format: "sender/recipient
     * messageContents
     *
     * @param fileLines the list of file lines to be parsed;
     * @return list list
     */
    List<String> parseMessages(List<String> fileLines){
        List<String> messages = new ArrayList<>();
        for(String line : fileLines){
            try (
                    Scanner scan = new Scanner(line);
            ) {
                StringBuilder sb = new StringBuilder();
                scan.useDelimiter("[,]");
                scan.next();
                String sender = scan.next();
                String messageContents = scan.next();
                sb.append(sender + " " + messageContents);
                messages.add(sb.toString());
            } catch(Exception e){
                e.getMessage();
            }
        }
        return messages;
    }

    /**
     * Get the naughty messages from the naughty messages file.
     *
     * @param userName the user name
     * @return list list
     */
    List<String> getNaughtyMessagesFileLines(String userName){
        String path = flaggedMessagesDirectory + File.separator + userName + "Naughty_Messages.txt";
        return getFileLines(path);
//        if (new File(path).exists()) {
//            List<String> fileLines = this.readFile(Paths.get(path));
//            if  (fileLines != null){
//                fileLines.remove(0);
//            }
//            return fileLines;
//        }
//        return new LinkedList<>();
    }

    private boolean appendMessageUserRecord(String message, String path, String user, String senderIP) {
        String appendMessage = this.getNYdate() + "," + user + "," + message + "," + senderIP;
        return this.appendToFile(path, appendMessage);
    }


    private boolean appendToFile(String path, String line) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(path, true))) {
            bw.newLine();
            bw.append(line);
            bw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Returns today's date in the following format: "yyyy-mm-dd".
     *
     * @return date in nyc
     */
    private String getNYdate() {
        ZonedDateTime nycTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        return nycTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Gets lines containing information from sent messages file of the given user.
     *
     * @param userName the user name
     * @return sent messages file lines
     */
    public List<String> getSentMessagesFileLines(String userName) {
        String path = memberProfilesDirectoryName + File.separator + userName + File.separator + "Sent_Messages.txt";
        return getFileLines(path);
//        if (new File(path).exists()) {
//            List<String> fileLines = this.readFile(Paths.get(path));
//            if (fileLines != null) {
//                fileLines.remove(0);
//            }
//            return fileLines;
//        }
//        return new LinkedList<>();
    }

    /**
     * Gets lines containing information from received messages file of the given user.
     *
     * @param userName the user name
     * @return received messages file lines
     */
    public List<String> getReceivedMessagesFileLines(String userName) {
        String path = memberProfilesDirectoryName + File.separator + userName + File.separator + RECEIVED_MESSAGES;
        return getFileLines(path);

//        if (new File(path).exists()) {
//            List<String> fileLines = this.readFile(Paths.get(path));
//            if (fileLines != null) {
//                fileLines.remove(0);
//            }
//            return fileLines;
//        }
//        return new LinkedList<>();
    }

    public void cleanMissMessage(String userName) throws IOException {
        String missedMessagePathString =
                memberProfilesDirectoryName + File.separator + userName + File.separator + "Missed_Messages.txt";

        PrintWriter pw = new PrintWriter(missedMessagePathString);
        pw.close();

    }
    /**
     * Helper function used in all getFileLines code.
     * @param path path to parse
     * @return list of strings
     */
    private List<String> getFileLines(String path){
        List<String> fileLines = new LinkedList<>();
        if (new File(path).exists()) {
            fileLines = this.readFile(Paths.get(path));
            if (fileLines != null) {
                fileLines.remove(0);
            }
            return fileLines;
        }
        return fileLines;
    }

    /**
     * Delete recall message boolean.
     *
     * @param receiver the receiver
     * @param sender   the sender
     * @param content  the content
     * @return the boolean
     * @throws IOException the io exception
     */
    public boolean deleteRecallMessage(String receiver, String sender, String content) throws IOException {
        String profileDirectory = memberProfilesDirectoryName + File.separator + receiver;
        Path path = Paths.get(profileDirectory, "Missed_Messages.txt");
        Path path_tmp = Paths.get(profileDirectory, "temp.txt");
        File file = new File(path.toString());
        File tempFile = new File(path_tmp.toString());
        BufferedWriter writer = null;
        BufferedReader reader = null;
        boolean successful = false;
        try {
            reader = new BufferedReader(new FileReader(file));
            try {
                writer = new BufferedWriter(new FileWriter(tempFile));
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.isEmpty()) {
                            continue;
                        }
                        String[] parts = line.split(",");
                        if (parts[1].compareToIgnoreCase(sender) == 0 && parts[2].compareToIgnoreCase(content) == 0) {
                            continue;
                        }
                        writer.write(line + System.getProperty("line.separator"));

                    }
                    writer.close();
                    reader.close();
                    successful = tempFile.renameTo(file);
                    System.out.println(successful);
                } catch (IOException e) {

                }
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (Exception e) {
                }
            }
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
            }
        }
        return successful;
    }
}
