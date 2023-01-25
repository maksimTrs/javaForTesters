package classdata;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


//@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AddressBookData {
    boolean checkNewBookData;
    private String firstName;
    private String lastName;
    private String bookCompany;
    private String bookMobile;
    private String bookEmail;
    private String day;
    private String month;
    private int year;

    private String filePath;


    public AddressBookData withFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }


    public AddressBookData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AddressBookData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AddressBookData withBookCompany(String bookCompany) {
        this.bookCompany = bookCompany;
        return this;
    }

    public AddressBookData withBookMobile(String bookMobile) {
        this.bookMobile = bookMobile;
        return this;
    }

    public AddressBookData withBookEmail(String bookEmail) {
        this.bookEmail = bookEmail;
        return this;
    }

    public AddressBookData withDay(String day) {
        this.day = day;
        return this;
    }

    public AddressBookData withMonth(String month) {
        this.month = month;
        return this;
    }

    public AddressBookData withYear(int year) {
        this.year = year;
        return this;
    }

    public AddressBookData withCheckNewBookData(boolean checkNewBookData) {
        this.checkNewBookData = checkNewBookData;
        return this;
    }
}