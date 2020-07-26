package customerLogin.testcases;

import base.TestBase;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HarryPotterCustomer extends TestBase {

    @Test
    public void OpenAccount() throws IOException {
        WebUI.waitForPageLoad();
        WebUI.clickOnElement(loginScreen.get("CustomerLoginButton"), "Customer Login Button");
        WebUI.checkForElementDisplay(customer.get("YourNameSelect"), "YourName Dropdown");
        WebUI.selectDropdownValues(customer.get("YourNameSelect"), "Harry Potter", "YourName Dropdown");
        WebUI.clickOnElement(customer.get("loginButton"), "Login Button");

        //Deposit Amount
        WebUI.checkForElementDisplay(customer.get("depositTab"), "Deposit Button");
        WebUI.clickOnElement(customer.get("depositTab"), "Deposit Button");
        WebUI.setText(customer.get("depositTextBox"), "1000", "Amount Deposit TextBox");
        WebUI.clickOnElement(customer.get("depositButton"),"Deposit Button");
        String actual_Message = WebUI.getText(customer.get("message"), "Notification");
        String expected_Message = "Deposit Successful";
        WebUI.verifyEquals(actual_Message, expected_Message);

        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
        Date date = new Date();
        System.out.println(formatter.format(date));
    }
}
