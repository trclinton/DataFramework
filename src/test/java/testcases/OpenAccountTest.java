package testcases;

import base.TestBase;
import org.testng.annotations.Test;

public class OpenAccountTest extends TestBase {

    @Test
    public void OpenAccount() throws InterruptedException {
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
