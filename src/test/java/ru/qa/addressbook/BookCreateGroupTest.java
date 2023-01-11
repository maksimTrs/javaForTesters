package ru.qa.addressbook;

import classdata.GroupData;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.List;
import java.util.stream.Collectors;


public class BookCreateGroupTest extends Base {

    private static Logger strLogger = Logger.getLogger(BookCreateGroupTest.class);


    @Test(groups = "smoke", testName = "test_create_addressBook_group")
    public void testBookAddGroup() {

        int randomGroupNumberGenerator = (int) (Math.random() * 100 + 3);
        String groupName = "GroupName_1_" + randomGroupNumberGenerator;
        String groupHeader = "GroupHeader_1_" + randomGroupNumberGenerator;
        String groupFooter = "GroupFooter_1_" + randomGroupNumberGenerator;

        GroupsPage groupsPage = new GroupsPage(driver);
        groupsPage.createNewGroup(new GroupData(groupName, groupHeader, groupFooter));
        groupsPage.goToGroupListPage();


        List<String> listOfAddressGroups = groupsPage.getGroupsSection()
                .stream().map(WebElement::getText)
                .collect(Collectors.toList());

        System.out.println(listOfAddressGroups);

        String testGroupName = listOfAddressGroups.stream()
                .filter(group -> group.equals(groupName))
                .findFirst().orElse(null);

        Assert.assertEquals(testGroupName, groupName);


        strLogger.info("********************************************************************************");
        strLogger.info("<<< Test method: " + BookCreateGroupTest.class.getMethods()[0].toString() + " passed >>>");
        strLogger.info("********************************************************************************");

        strLogger.info("********************************************************************************");
        strLogger.info("Clear Group test data");
        strLogger.info("********************************************************************************");


        groupsPage.deleteTestGroup(groupName);
        String checkDeletion = groupsPage.checkGroupDeletion();

        Assert.assertEquals(checkDeletion, "Group has been removed.\n" +
                "return to the group page");

    }

}
