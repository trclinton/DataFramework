package testcases;

import base.TestBase;
import org.testng.annotations.Test;
import utilities.DataProviderClass;

public class AddCustomerTest extends TestBase {

    @Test(dataProvider = "CustomerDetails", dataProviderClass = DataProviderClass.class)
    public void AddCustomers(String firstName, String lastName, String postalCode) throws InterruptedException {
        WebUI.clickOnElement(addCustomer.get("addCustomerButton"), "Add Customer Button");
        WebUI.setText(addCustomer.get("firstName"), firstName, "FirstName TextBox");
        WebUI.setText(addCustomer.get("lastName"), lastName, "LastName TextBox");
        WebUI.setText(addCustomer.get("postCode"), postalCode, "PostalCode TextBox");
        WebUI.clickOnElement(addCustomer.get("addCustomerSubmitButton"), "Add Customer Submit Button");
        WebUI.handleAlerts("Customer added successfully");
        Thread.sleep(1000);
    }

}
