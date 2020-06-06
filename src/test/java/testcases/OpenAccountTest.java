package testcases;

import base.TestBase;
import org.testng.annotations.Test;

public class OpenAccountTest extends TestBase {

    @Test
    public void openAccount(){
        genericKey.clickOnElement(openAccount.get("openAccountButton"), "OpenAccount Button");
//        genericKey.clickOnElement(openAccount.get(""),"CustomerName Dropdown");
    }
}
