package bankManager.testcases;

import base.TestBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.DataProviderClass;

public class AddCustomerTest extends TestBase {

    @BeforeClass
    public void navigateToAddCustomer(){
        WebUI.waitForPageLoad();
        WebUI.clickOnElement(loginScreen.get("bankManagerLoginButton"), "Bank Manager Login Button");
        WebUI.checkForElementDisplay(addCustomer.get("addCustomerButton"), "Add Customer Button");
    }

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

    @AfterClass
    public void OpenAccount() throws InterruptedException {
        WebUI.checkForElementDisplay(openAccount.get("openAccountButton"), "OpenAccount Button");
        WebUI.clickOnElement(openAccount.get("openAccountButton"), "OpenAccount Button");
        int account1 = fetchAccountNumber("Reynold Clinton");
        int account2 = fetchAccountNumber("Shenika Joe");
        int account3 = fetchAccountNumber("Carlton Daniel");
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
        Thread.sleep(1000);
    }
    public int fetchAccountNumber(String customerName){
        WebUI.selectDropdownValues(openAccount.get("customerSelectDropdown"), customerName, "Customer Dropdown");
        WebUI.selectDropdownValues(openAccount.get("currencySelectDropdown"), "Rupee", "Currency Dropdown");
        WebUI.clickOnElement(openAccount.get("processButton"), "Process Button");
        String alertText = WebUI.handleAlerts("Account created successfully");
        String string_AccountNumber = alertText.replaceAll("[^\\d]", " ").trim();
        int accountNumber = Integer.parseInt(string_AccountNumber);
        return accountNumber;
    }

}
