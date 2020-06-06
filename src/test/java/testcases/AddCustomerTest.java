package testcases;

import base.TestBase;
import org.testng.annotations.Test;
import utilities.DataProviderClass;

public class AddCustomerTest extends TestBase {

    @Test(dataProvider = "CustomerDetails", dataProviderClass = DataProviderClass.class)
    public void addCustomers(String firstName, String lastName, String postalCode) throws InterruptedException {
        genericKey.clickOnElement(addCustomer.get("addCustomerButton"), "Add Customer Button");
        genericKey.setText(addCustomer.get("firstName"), firstName, "FirstName TextBox");
        genericKey.setText(addCustomer.get("lastName"), lastName, "LastName TextBox");
        genericKey.setText(addCustomer.get("postCode"), postalCode, "PostalCode TextBox");
        genericKey.clickOnElement(addCustomer.get("addCustomerSubmitButton"), "Add Customer Submit Button");
        genericKey.handleAlerts("Customer added successfully");
        Thread.sleep(1000);
    }

}
