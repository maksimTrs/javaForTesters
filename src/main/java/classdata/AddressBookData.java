package classdata;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class AddressBookData {
    private String firstName;
    private String lastName;
    private String bookCompany;
    private String bookMobile;
    private String bookEmail;
    private String day;
    private String month;
    private int year;

    boolean checkNewBookData;
}