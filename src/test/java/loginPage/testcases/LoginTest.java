package loginPage.testcases;

import base.TestBase;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends TestBase {

    @Test
    public void LoginAsBankManager() throws IOException {
        WebUI.waitForPageLoad();
        String actual = TestBase.driver.getCurrentUrl();
        String expected = "http://www.way2automation.com/angularjs-protractor/banking/#/login";
        WebUI.verifyEquals(actual, expected);
        String pageTitle = WebUI.getPageTitle();
        System.out.println(pageTitle);
        String PageHeading = WebUI.getText(loginScreen.get("PageHeading"), "Page Heading");
        System.out.println(PageHeading);
        WebUI.checkForElementDisplay(loginScreen.get("HomeButton"), "Home Button");
        WebUI.checkForElementDisplay(loginScreen.get("CustomerLoginButton"), "Customer Login Button");
        WebUI.checkForElementDisplay(loginScreen.get("bankManagerLoginButton"), "Bank Manager Login Button");

    }
}
