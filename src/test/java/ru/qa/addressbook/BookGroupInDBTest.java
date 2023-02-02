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

public class BookGroupInDBTest extends BaseTest {


    //  localhost  <> 192.168.0.191
    // private static final String DB_CONNECT_URL = "jdbc:mysql://localhost/addressbook?user=root&password=";

    // private static String host = System.getProperty("HUB_HOST");
    //private static final String DB_CONNECT_URL = "jdbc:mysql://" + host + ":3306/addressbook?user=test&password=test";

    private static final String DB_CONNECT_URL = "jdbc:mysql://localhost:3306/addressbook?user=test&password=test" +
            "&autoReconnect=true&useSSL=false";


    private PreparedStatement createPreparedStatement(Connection con, String groupName) throws SQLException {
        String sql = "SELECT group_id, group_name FROM group_list WHERE group_name='%s'";
        //ps.setInt(1, userId);
        return con.prepareStatement(String.format(sql, groupName));
    }


    @DataProvider(name = "group-data-db-provider")
    private Object[][] dataProviderForGroup() {
        return new Object[][]{
                {"Test_Group_DB_" + getIntRandomValue(), "Test_Group_DB_Header_" + getIntRandomValue(),
                        "Test_Group_DB_Footer_" + getIntRandomValue()}

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

        logger.info("UI GroupName = " + listOfAddressGroups);

        assertThat(listOfAddressGroups).containsOnly(groupName);


        List<String> listOfAddressesDBGroupNameFiled = new ArrayList<>();
        List<Integer> listOfAddressesDBGroupIDFiled = new ArrayList<>();


        try (Connection con = DriverManager.getConnection(DB_CONNECT_URL);
             PreparedStatement ps = createPreparedStatement(con, groupName);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String str = rs.getString("group_name");
                int id = rs.getInt("group_id");

                listOfAddressesDBGroupNameFiled.add(str);
                listOfAddressesDBGroupIDFiled.add(id);

                logger.info("DB GroupName = " + listOfAddressesDBGroupNameFiled);
                logger.info("DB GroupName ID = " + listOfAddressesDBGroupIDFiled);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        assertThat(listOfAddressGroups).isEqualTo(listOfAddressesDBGroupNameFiled);
        assertThat(listOfAddressesDBGroupIDFiled).isNotEmpty().isNotNull();


    }

}
