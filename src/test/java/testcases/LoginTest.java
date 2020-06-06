package testcases;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Generic;

import java.io.IOException;

public class LoginTest extends TestBase {

    @Test
    public void LoginAsBankManager() throws IOException {
        genericKey.waitForPageLoad();
        String actual = TestBase.driver.getCurrentUrl();
        String expected = "http://www.way2automation.com/angularjs-protractor/banking/#/login";
        genericKey.verifyEquals(actual, expected);
        genericKey.clickOnElement(addCustomer.get("bankManagerLoginButton"), "Bank Manager Login Button");
        genericKey.checkForElementDisplay(addCustomer.get("addCustomerButton"), "Add Customer Button");
    }
}
