package edu.northeastern.ccs.im;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class CRUDTest {

    CRUD crud;
    public static final String prattleDB =
            System.getProperty("user.dir") + File.separator + "Prattle_DB";
    public static final Path dbPath = Paths.get(prattleDB);


    @Test
    void testFileExits() throws Exception {
        crud = new CRUD();
        String fakeFilePath = "/Fake/file.txt";
        String realFilePath = "path/test.txt";
        assertFalse(crud.fileExists(fakeFilePath));
        File folder = new File("path");
        folder.mkdir();
        File file = new File("path", "test.txt");
        file.createNewFile();
        assertTrue(crud.fileExists(realFilePath));
        file.delete();
        folder.delete();
    }

    @Test
    void testGetAllUserFilePath() {
        crud = new CRUD();
        try {
            Field allUserFilePath = crud.getClass().getDeclaredField("allUserFilePath");
            allUserFilePath.setAccessible(true);
            allUserFilePath.set(crud, Paths.get("test", "file.txt"));
            assertEquals("test/file.txt", crud.getAllUserFilePath());
        } catch (Exception e) {
        }
    }

    @Test
    void testGetMemberProfilesDirectoryName() {
        crud = new CRUD();
        try {
            Field memberProfilesDirectoryName = crud.getClass().getDeclaredField("memberProfilesDirectoryName");
            memberProfilesDirectoryName.setAccessible(true);
            memberProfilesDirectoryName.set(crud, "testDirectoryName");
            assertEquals("testDirectoryName", crud.getMemberProfilesDirectoryName());
        } catch (Exception e) {
        }
    }

    @Test
    void testGetGroupDataDirectoryName() {
        crud = new CRUD();
        try {
            Field groupDataDirectoryName = crud.getClass().getDeclaredField("groupDataDirectoryName");
            groupDataDirectoryName.setAccessible(true);
            groupDataDirectoryName.set(crud, "testDirectoryName");
            assertEquals("testDirectoryName", crud.getGroupDataDirectoryName());
        } catch (Exception e) {

        }
    }

    @Test
    void testInitUserDataDirectory() {
        crud = new CRUD();
        try {
            Field userDataDirectoryName = crud.getClass().getDeclaredField("userDataDirectoryName");
            userDataDirectoryName.setAccessible(true);
            userDataDirectoryName.set(crud, "user_data_test_directory");
            assertTrue(crud.initUserDataDirectory());
            assertFalse(crud.initUserDataDirectory());
            File folder = new File("user_data_test_directory");
            folder.deleteOnExit();
        } catch (Exception e) {

        }
    }

    @Test
    void testInitMemberProfilesDirectory() {
        crud = new CRUD();
        try {
            Field memberProfilesDirectoryName = crud.getClass().getDeclaredField("memberProfilesDirectoryName");
            memberProfilesDirectoryName.setAccessible(true);
            memberProfilesDirectoryName.set(crud, "member_profile_dict_name");
            assertTrue(crud.initMemberProfilesDirectory());
            assertFalse(crud.initMemberProfilesDirectory());
            File folder = new File("member_profile_dict_name");
            folder.deleteOnExit();
        } catch (Exception e) {

        }
    }

    @Test
    void testInitFlaggedMessagesDirectory() {
        crud = new CRUD();
        try {
            Field flaggedProfiles = crud.getClass().getDeclaredField("flaggedMessagesDirectory");
            flaggedProfiles.setAccessible(true);
            flaggedProfiles.set(crud, "flagged_Messages_Directory");
            File folder = new File("flagged_Messages_Directory");
            crud.initFlaggedMessagesDirectory();
            folder.deleteOnExit();
            crud.deleteMisc(dbPath);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    void testParseMessages() {
        crud = new CRUD();
        List<String> test = new ArrayList<>();
        test.add("Hello World");
        test.add("This is a test.");
        try {
            crud.parseMessages(test);
        } catch (NoSuchElementException e) {
            e.getMessage();
        }

    }

    @Test
    void testFlagged() {
        crud = new CRUD();
        try {
            assertEquals(0, crud.getNaughtyMessagesFileLines("arcy").size());
            Field flaggedProfilesDirectoryName = crud.getClass().getDeclaredField("flaggedMessagesDirectory");
            flaggedProfilesDirectoryName.setAccessible(true);
            flaggedProfilesDirectoryName.set(crud, "flagged_Messages_Directory");
            File folder = new File("flagged_Messages_Directory/arcy");
            folder.mkdirs();
            Path filePath = Paths.get("flagged_Messages_Directory/swimming", "Naughty_messages.txt");
            String initEntry = "";
            Files.write(filePath, initEntry.getBytes(), StandardOpenOption.CREATE_NEW);
            assertTrue(crud.addFlaggedMessage("I'm an asshole", "arcy", "127.0.0.1"));
            assertEquals(1, crud.getNaughtyMessagesFileLines("arcy").size());
            assertTrue(crud.deleteMemberProfile("arcy"));
            File delFolder = new File("flagged_Messages_Directory");
            delFolder.deleteOnExit();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    void testInitGroupDataDirectory() {
        crud = new CRUD();
        try {
            Field groupDataDirectoryName = crud.getClass().getDeclaredField("groupDataDirectoryName");
            groupDataDirectoryName.setAccessible(true);
            groupDataDirectoryName.set(crud, "group_data_dict_name");
            assertTrue(crud.initGroupDataDirectory());
            assertFalse(crud.initGroupDataDirectory());
            File folder = new File("group_data_dict_name");
            folder.deleteOnExit();
        } catch (Exception e) {

        }
    }

    @Test
    void testInitAllGroupsDirectory() {
        crud = new CRUD();
        try {
            Field allGroupsDataDirectoryName = crud.getClass().getDeclaredField("allGroupsDataDirectoryName");
            allGroupsDataDirectoryName.setAccessible(true);
            allGroupsDataDirectoryName.set(crud, "all_groups_data_dict_name");
            assertTrue(crud.initAllGroupsDirectory());
            assertFalse(crud.initAllGroupsDirectory());
            File folder = new File("all_groups_data_dict_name");
            folder.deleteOnExit();
        } catch (Exception e) {

        }
    }

    @Test
    void testInitAllUserDataFile() {
        crud = new CRUD();
        try {
            Field dbDirectory = crud.getClass().getDeclaredField("dbDirectory");
            dbDirectory.setAccessible(true);
            dbDirectory.set(crud, "db_test");
            Field allUserFilePath = crud.getClass().getDeclaredField("allUserFilePath");
            allUserFilePath.setAccessible(true);
            Path allUserPath = Paths.get("db_test", "all_user_file.txt");
            allUserFilePath.set(crud, allUserPath);
            assertTrue(crud.initAllUserDataFile());
            assertFalse(crud.initAllUserDataFile());
            File file = new File("db_test/all_user_file.txt");
            file.deleteOnExit();
            File folder = new File("db_test");
            folder.delete();
        } catch (Exception e) {
        }
    }

    @Test
    void testCreateNewGroup() {
        crud = new CRUD();
        try {
            Field allGroupsDataDirectoryName = crud.getClass().getDeclaredField("allGroupsDataDirectoryName");
            allGroupsDataDirectoryName.setAccessible(true);
            allGroupsDataDirectoryName.set(crud, "all_group_data_dict");
            assertTrue(crud.createNewGroup("GROUPNAME", "USERNAME"));
            assertTrue(crud.deleteGroup("GROUPNAME"));
            File folder = new File("all_group_data_dict");
            folder.deleteOnExit();
        } catch (Exception e) {

        }
    }

    @Test
    void testAddMemberToGroup() {
        crud = new CRUD();
        try {
            Field allGroupsDataDirectoryName = crud.getClass().getDeclaredField("allGroupsDataDirectoryName");
            allGroupsDataDirectoryName.setAccessible(true);
            allGroupsDataDirectoryName.set(crud, "all_group_data_dict");
            assertTrue(crud.createNewGroup("GROUPNAME", "USERNAME"));
            assertTrue(crud.addMemberToGroup("USER2", "GROUPNAME"));
            assertTrue(crud.deleteGroup("GROUPNAME"));
            File folder = new File("all_group_data_dict");
            folder.deleteOnExit();
        } catch (Exception e) {

        }
    }

    @Test
    void testLogGroupMessage() {
        crud = new CRUD();
        try {
            Field allGroupsDataDirectoryName = crud.getClass().getDeclaredField("allGroupsDataDirectoryName");
            allGroupsDataDirectoryName.setAccessible(true);
            allGroupsDataDirectoryName.set(crud, "all_group_data_dict");
            assertTrue(crud.createNewGroup("GROUPNAME", "USERNAME"));
            assertTrue(crud.logGroupMessage("USER3", "test", "GROUPNAME"));
            assertTrue(crud.deleteGroup("GROUPNAME"));
            File folder = new File("all_group_data_dict");
            folder.deleteOnExit();
        } catch (Exception e) {

        }
    }

    @Test
    void testAddSentMessage() {
        crud = new CRUD();
        try {
            Field memberProfilesDirectoryName = crud.getClass().getDeclaredField("memberProfilesDirectoryName");
            memberProfilesDirectoryName.setAccessible(true);
            memberProfilesDirectoryName.set(crud, "member_profile_dict");
            File folder = new File("member_profile_dict/swimming");
            folder.mkdirs();
            Path filePath = Paths.get("member_profile_dict/swimming", "Sent_Messages.txt");
            String initEntry = "";
            Files.write(filePath, initEntry.getBytes(), StandardOpenOption.CREATE_NEW);
            assertTrue(crud.addSentMessage("test from swimming", "swimming", "yuheng"));
            assertTrue(crud.deleteMemberProfile("swimming"));
            File delFolder = new File("member_profile_dict");
            delFolder.deleteOnExit();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    void testAddSentMessage2() {
        crud = new CRUD();
        try {
            Field memberProfilesDirectoryName = crud.getClass().getDeclaredField("memberProfilesDirectoryName");
            memberProfilesDirectoryName.setAccessible(true);
            memberProfilesDirectoryName.set(crud, "member_profile_dict");
            File folder = new File("member_profile_dict/swimming");
            folder.mkdirs();
            Path filePath = Paths.get("member_profile_dict/swimming", "Sent_Messages.txt");
            String initEntry = "";
            Files.write(filePath, initEntry.getBytes(), StandardOpenOption.CREATE_NEW);
            assertTrue(crud.addSentMessage("test from swimming", "swimming", "yuheng", "1.2.3.4"));
            assertTrue(crud.deleteMemberProfile("swimming"));
            File delFolder = new File("member_profile_dict");
            delFolder.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void testAddReceivedMessage() {
        crud = new CRUD();
        try {
            Field memberProfilesDirectoryName = crud.getClass().getDeclaredField("memberProfilesDirectoryName");
            memberProfilesDirectoryName.setAccessible(true);
            memberProfilesDirectoryName.set(crud, "member_profile_dict");
            File folder = new File("member_profile_dict/swimming");
            folder.mkdirs();
            Path filePath = Paths.get("member_profile_dict/swimming", "Received_Messages.txt");
            String initEntry = "";
            Files.write(filePath, initEntry.getBytes(), StandardOpenOption.CREATE_NEW);
            assertTrue(crud.addReceivedMessage("test from swimming", "swimming", "yuheng"));
            assertTrue(crud.deleteMemberProfile("swimming"));
            File delFolder = new File("member_profile_dict");
            delFolder.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddReceivedMessage2() {
        crud = new CRUD();
        try {
            Field memberProfilesDirectoryName = crud.getClass().getDeclaredField("memberProfilesDirectoryName");
            memberProfilesDirectoryName.setAccessible(true);
            memberProfilesDirectoryName.set(crud, "member_profile_dict");
            File folder = new File("member_profile_dict/swimming");
            folder.mkdirs();
            Path filePath = Paths.get("member_profile_dict/swimming", "Received_Messages.txt");
            String initEntry = "";
            Files.write(filePath, initEntry.getBytes(), StandardOpenOption.CREATE_NEW);
            assertTrue(crud.addReceivedMessage("test from swimming", "swimming", "yuheng", "1.2.3.4"));
            assertTrue(crud.deleteMemberProfile("swimming"));
            File delFolder = new File("member_profile_dict");
            delFolder.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFlaggedMessages() {
        crud = new CRUD();
        String sender = "Naughty_Sender";
        crud.addFlaggedMessage("Missed message 1", sender, "1111");
        crud.addFlaggedMessage("Missed message 2", sender, "1111");
        crud.addFlaggedMessage("Missed message 3", sender, "1111");
        crud.addFlaggedMessage("Missed message 4", sender, "1111");
        crud.addFlaggedMessage("Missed message 5", sender, "1111");
        crud.addFlaggedMessage("Missed message 6", sender, "1111");

        ArrayList<String> naughtyMessages = (ArrayList<String>) crud.getNaughtyMessages(sender);
        AtomicInteger i = new AtomicInteger(1);
        naughtyMessages.forEach(line -> assertEquals("Naughty_Sender " + "Missed message " + i.getAndIncrement(), line));
        crud.deleteMisc(dbPath);
    }

    @Test
    void testCreateNewMemberProfile() {
        crud = new CRUD();
        try {
            Field memberProfilesDirectoryName = crud.getClass().getDeclaredField("memberProfilesDirectoryName");
            memberProfilesDirectoryName.setAccessible(true);
            memberProfilesDirectoryName.set(crud, "member_profile_dict");
            assertTrue(crud.createNewMemberProfile("yuheng", "123", "123", "123"));
            Thread.sleep(1000);
            crud.deleteMemberProfile("yuheng");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReadPassword() {
        crud = new CRUD();
        crud.initUserDataDirectory();
        crud.initAllUserDataFile();
        crud.createNewMemberProfile("arcy", "123", "password", "abcde");
        assertEquals("password", crud.readPasswordFromUser("arcy"));
        crud.deleteMisc(dbPath);
    }

    @Test
    void testReadSalt() {
        crud = new CRUD();
        crud = new CRUD();
        crud.initUserDataDirectory();
        crud.initAllUserDataFile();
        crud.createNewMemberProfile("arcy", "123", "password", "abcde");
        assertEquals("abcde", crud.readSaltFromUser("arcy"));
        crud.deleteMisc(dbPath);
    }

    @Test
    void testReadFile() {
        crud = new CRUD();
        try {
            Field dbDirectory = crud.getClass().getDeclaredField("dbDirectory");
            dbDirectory.setAccessible(true);
            dbDirectory.set(crud, "db_test");
            Field allUserFilePath = crud.getClass().getDeclaredField("allUserFilePath");
            allUserFilePath.setAccessible(true);
            Path allUserPath = Paths.get("db_test", "all_user_file.txt");
            allUserFilePath.set(crud, allUserPath);
            crud.initAllUserDataFile();
            File file = new File("db_test/all_user_file.txt");
            crud.readFile(allUserPath);
            Thread.sleep(1000);
            file.deleteOnExit();
            File folder = new File("db_test");
            folder.delete();
        } catch (Exception e) {

        }
    }

    @Test
    void testReadFromGroupMember() {
        crud = new CRUD();
        try {
            Field allGroupsDataDirectoryName = crud.getClass().getDeclaredField("allGroupsDataDirectoryName");
            allGroupsDataDirectoryName.setAccessible(true);
            allGroupsDataDirectoryName.set(crud, "all_group_data_dict");
            crud.createNewGroup("GROUPNAME", "USERNAME");
            crud.readFileFromGroupMember("GROUPNAME");
            Thread.sleep(1000);
            crud.deleteGroup("GROUPNAME");
            File folder = new File("all_group_data_dict");
            folder.deleteOnExit();
        } catch (Exception e) {

        }
    }

    @Test
    void testWriteToDBFromRunnable() {
        crud = new CRUD();
        crud.initUserDataDirectory();
        assertFalse(crud.writeToDBFromRunnable("123", "username", "2018-4-1"));
    }


    @Test
    void testAddMissedMessage() {
        crud = new CRUD();
        try {
            Field memberProfilesDirectoryName = crud.getClass().getDeclaredField("memberProfilesDirectoryName");
            memberProfilesDirectoryName.setAccessible(true);
            memberProfilesDirectoryName.set(crud, "member_profile_dict");
            File folder = new File("member_profile_dict/swimming");
            folder.mkdirs();
            Path filePath = Paths.get("member_profile_dict/swimming", "Missed_Messages.txt");
            String initEntry = "";
            Files.write(filePath, initEntry.getBytes(), StandardOpenOption.CREATE_NEW);
            assertTrue(crud.addMissedMessage("test from swimming", "swimming", "yuheng", "1111"));
            List<ArrayList<String>> missedMessages = crud.readMissedMessage("swimming");
            assertTrue(crud.deleteMemberProfile("swimming"));
            File delFolder = new File("member_profile_dict");
            delFolder.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void testSearchFromReceiver() {
        crud = new CRUD();
        try {
            crud.getReceivedMessagesFileLines("swimming");
            Field memberProfilesDirectoryName = crud.getClass().getDeclaredField("memberProfilesDirectoryName");
            memberProfilesDirectoryName.setAccessible(true);
            memberProfilesDirectoryName.set(crud, "member_profile_dict");
            File folder = new File("member_profile_dict/swimming");
            folder.mkdirs();
            Path filePath = Paths.get("member_profile_dict/swimming", "Received_Messages.txt");
            String initEntry = "";
            Files.write(filePath, initEntry.getBytes(), StandardOpenOption.CREATE_NEW);
            assertTrue(crud.addReceivedMessage("test from swimming", "swimming", "yuheng"));
            crud.getReceivedMessagesFileLines("swimming");
            assertTrue(crud.deleteMemberProfile("swimming"));
            File delFolder = new File("member_profile_dict");
            delFolder.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSearchFromSender() {
        crud = new CRUD();
        try {
            crud.getSentMessagesFileLines("swimming");
            Field memberProfilesDirectoryName = crud.getClass().getDeclaredField("memberProfilesDirectoryName");
            memberProfilesDirectoryName.setAccessible(true);
            memberProfilesDirectoryName.set(crud, "member_profile_dict");
            File folder = new File("member_profile_dict/swimming");
            folder.mkdirs();
            Path filePath = Paths.get("member_profile_dict/swimming", "Sent_Messages.txt");
            String initEntry = "";
            Files.write(filePath, initEntry.getBytes(), StandardOpenOption.CREATE_NEW);
            assertTrue(crud.addSentMessage("test from swimming", "swimming", "yuheng"));
            crud.getSentMessagesFileLines("swimming");
            assertTrue(crud.deleteMemberProfile("swimming"));
            File delFolder = new File("member_profile_dict");
            delFolder.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteMissedMessage() {
        crud = new CRUD();
        try {
            Field memberProfilesDirectoryName = crud.getClass().getDeclaredField("memberProfilesDirectoryName");
            memberProfilesDirectoryName.setAccessible(true);
            memberProfilesDirectoryName.set(crud, "member_profile_dict");
            File folder = new File("member_profile_dict/swimming");
            folder.mkdirs();
            Path filePath = Paths.get("member_profile_dict/swimming", "Missed_Messages.txt");
            String initEntry = "";
            Files.write(filePath, initEntry.getBytes(), StandardOpenOption.CREATE_NEW);
            assertTrue(crud.addMissedMessage("test from swimming", "swimming", "yuheng", "1111"));
            List<ArrayList<String>> missedMessages = crud.readMissedMessage("swimming");
            assertTrue(crud.deleteRecallMessage("swimming", "yuheng", "test from swimming"));
            assertTrue(crud.deleteMemberProfile("swimming"));
            File delFolder = new File("member_profile_dict");
            delFolder.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriteIntoSubpoenaFile() {
        crud = new CRUD();
        try {
            Field subpoenaDataDirectoryName = crud.getClass().getDeclaredField("subpoenaDataDirectoryName");
            subpoenaDataDirectoryName.setAccessible(true);
            subpoenaDataDirectoryName.set(crud, "subpoena_dict");
            crud.initSubpoenaDataDirectory();
            crud.initSubpoenaFile("testUser");
            Message msg = Message.makeBroadcastMessage("test", "test");
            crud.writeIntoSubpoenaFile("testUser", msg);
            File delFile = new File("subpoena_dict/subpeona_target_testUser.txt");
            delFile.delete();
            File delFolder = new File("subpoena_dict");
            delFile.deleteOnExit();
        } catch (Exception e) {

        }
    }

}
