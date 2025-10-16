package seedu.quotely.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyNameTest {
    @Test
    void companyName_validInput_success() {
        CompanyName companyName = new CompanyName("test1");
        try {
            assertInstanceOf(CompanyName.class, companyName);
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void setCompanyName_validInput_CompanyNameSet() {
        CompanyName companyName = new CompanyName("invalid");
        try {
            companyName.setCompanyName("test2");
            assertEquals("test2", companyName.getCompanyName());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }

    @Test
    public void getCompanyName_validInput_returnCompanyName() {
        CompanyName companyName = new CompanyName("test3");
        try {
            assertEquals("test3", companyName.getCompanyName());
        } catch (Exception e) {
            assert false : "Exception should not be thrown";
        }
    }
}
