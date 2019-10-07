package edu.northeastern.ccs.im;

import edu.northeastern.ccs.im.Utils.GroupCommandProcessor;
import org.junit.jupiter.api.Test;

public class GroupTest {

    @Test
    public void main() {
        CRUD crud = new CRUD();
        GroupCommandProcessor groupCommandProcessor = new GroupCommandProcessor();
        String test = "group create group: testGroupName";
        String failureTest = "wrong test";
        String failureTest2 = "wrong wrong wrong wrong test";
        String addMember = "group add member: testGroupName testUserName";
        System.out.println(groupCommandProcessor.getCreateGroupName(test));
        System.out.println(groupCommandProcessor.isCreateGroupCommand(test));
        System.out.println(groupCommandProcessor.isCreateGroupCommand(failureTest));
        System.out.println(groupCommandProcessor.isCreateGroupCommand(failureTest2));
        System.out.println(groupCommandProcessor.getAddGroupMemberArguments(addMember));
//        System.out.println(crud.readFileFromGroupMember("myGroup").get(1));
    }
}
