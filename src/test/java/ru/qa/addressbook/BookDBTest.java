package ru.qa.addressbook;

import classdata.GroupData;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.NumberGenerator.getIntRandomValue;

public class BookDBTest extends BaseTest {

    @DataProvider(name = "group-data-db-provider")
    private Object[][] dataProviderForGroup() {
        return new Object[][]{
                {"Test_Group_7_777_" + getIntRandomValue(), "Test_Group_Header_7_777", "Test_Group_Footer_7_777"}

        };
    }

    @Test(dataProvider = "group-data-db-provider", groups = "smoke", testName = "test_create_addressBook_group_in_DB")
    public void testBookAddGroupAndValidateDB(String groupName, String groupHeader, String groupFooter) throws SQLException {

        GroupsPage groupsPage = new GroupsPage(driver);
        groupsPage.createNewGroup(new GroupData(groupName, groupHeader, groupFooter));
        groupsPage.goToGroupListPage();


        List<String> listOfAddressGroups = groupsPage.getGroupsSection()
                .stream().map(WebElement::getText)
                .filter(s -> s.equals(groupName))
                .collect(Collectors.toList());

        System.out.println("!!!!!!!!!!!   " + listOfAddressGroups);

        assertThat(listOfAddressGroups).isNotEmpty().isNotNull();


        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" +
                    "user=root&password=");


            // Do something with the Connection
            stmt = conn.createStatement();
            rs = stmt.executeQuery(String.format("SELECT group_id, group_name FROM group_list WHERE group_name='%s'",
                    groupName));


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        List<String> listOfAddressesDBGroupNameFiled = new ArrayList<>();
        List<Integer> listOfAddressesDBGroupIDFiled = new ArrayList<>();
        while (rs.next()) {
            String str = rs.getString("group_name");
            int id = rs.getInt("group_id");

            listOfAddressesDBGroupNameFiled.add(str);
            listOfAddressesDBGroupIDFiled.add(id);

            System.out.println("!!!!!!!!!!!   " + listOfAddressesDBGroupNameFiled);
            System.out.println("!!!!!!!!!!!   " + listOfAddressesDBGroupIDFiled);
        }


        assertThat(listOfAddressGroups).isEqualTo(listOfAddressesDBGroupNameFiled);
        assertThat(listOfAddressesDBGroupIDFiled).isNotEmpty().isNotNull();
    }

}
