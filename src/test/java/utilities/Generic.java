package utilities;

import base.TestBase;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.IOException;

public class Generic {

    public static WebDriverWait wait;

    public void waitForPageLoad() {
        try {
            JavascriptExecutor js = (JavascriptExecutor)TestBase.driver;
            if (js.executeScript("return document.readyState").toString().equals("complete")){
                TestBase.log.debug("Page loaded successfully");
                Reporter.log("Page loaded successfully");
                TestBase.test.log(Status.PASS, MarkupHelper.createLabel("Page loaded successfully", ExtentColor.GREEN));
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TestBase.log.debug("Page failed to load");
            Reporter.log("Page failed to load");
            TestBase.test.log(Status.FAIL, MarkupHelper.createLabel("Page failed to load", ExtentColor.RED));
        }
    }

    public void waitForElementPresent(String element_to_be_found, String elementName) {
        wait = new WebDriverWait(TestBase.driver, 20);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(element_to_be_found)));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element_to_be_found)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkForElementDisplay(Object element_to_be_found, String elementName) {
        try {
            waitForElementPresent(element_to_be_found.toString(), elementName);
            TestBase.driver.findElement(By.cssSelector(element_to_be_found.toString()));
            TestBase.log.debug(elementName + " is displayed on the web page");
            Reporter.log(elementName + " is displayed on the web page");
            TestBase.test.log(Status.PASS, MarkupHelper.createLabel(elementName + " is displayed on the web page", ExtentColor.GREEN));
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            TestBase.log.debug("Unable to locate Element : " + elementName);
            Reporter.log("Unable to locate Element : " + elementName);
            TestBase.test.log(Status.FAIL, MarkupHelper.createLabel("Unable to locate Element : " + elementName, ExtentColor.RED));
            return false;
        }
    }

    public void clickOnElement(Object element_to_be_clicked, String elementName) {
        try {
            waitForElementPresent(element_to_be_clicked.toString(), elementName);
            Assert.assertTrue(checkForElementDisplay(element_to_be_clicked.toString(), elementName), "Element not found");
            TestBase.driver.findElement(By.cssSelector(element_to_be_clicked.toString())).click();
            TestBase.log.debug("Successfully clicked on: " + elementName);
            Reporter.log("Successfully clicked on: " + elementName);
            TestBase.test.log(Status.PASS, MarkupHelper.createLabel("Successfully clicked on: " + elementName, ExtentColor.GREEN));
        } catch (Exception e) {
            e.printStackTrace();
            TestBase.log.debug("Failed to click on: " + elementName);
            Reporter.log("Failed to click on: " + elementName);
            TestBase.test.log(Status.FAIL, MarkupHelper.createLabel("Failed to click on: " + elementName, ExtentColor.RED));
        }
    }

    public void setText(Object text_box_element, Object text_to_be_entered, String elementName) {
        try {
            waitForElementPresent(text_box_element.toString(), elementName);
            Assert.assertTrue(checkForElementDisplay(text_box_element.toString(), elementName), "Text Box not found");
            TestBase.driver.findElement(By.cssSelector(text_box_element.toString())).sendKeys(text_to_be_entered.toString());
            TestBase.log.debug("Found : " + elementName + " and value has been set as " + text_to_be_entered);
            Reporter.log("Found : " + elementName + " and value has been set as " + text_to_be_entered);
            TestBase.test.log(Status.PASS, MarkupHelper.createLabel("Found : " + elementName + " and value has been set as " + text_to_be_entered, ExtentColor.GREEN));
        } catch (Exception e) {
            e.printStackTrace();
            TestBase.log.debug("Unable to find text box web element : " + elementName);
            Reporter.log("Unable to find text box web element : " + elementName);
            TestBase.test.log(Status.FAIL, MarkupHelper.createLabel("Unable to find text box web element : " + elementName, ExtentColor.RED));
        }
    }

    public void selectDropdownValues(){

    }

    public void handleAlerts(String AlertText) {
        wait = new WebDriverWait(TestBase.driver, 20);
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String actualText = alert.getText();
            String expectedText = AlertText;
            Assert.assertTrue(alert.getText().contains(AlertText));
            alert.accept();
            TestBase.log.debug("Alert Handled Successfully");
            Reporter.log("Alert Handled Successfully");
            TestBase.test.log(Status.PASS, MarkupHelper.createLabel("Alert Handled Successfully", ExtentColor.GREEN));

        } catch (Exception e) {
            e.printStackTrace();
            TestBase.log.debug("There was no Alert found");
            Reporter.log("There was no Alert found");
            TestBase.test.log(Status.FAIL, MarkupHelper.createLabel("There was no Alert found", ExtentColor.RED));
        }
    }

    public void verifyEquals(String actual, String expected) throws IOException {

        try {
            Assert.assertEquals(actual, expected);
            TestBase.log.debug("Verification is Successful");
            Reporter.log("Verification is Successful");
            TestBase.test.log(Status.PASS, MarkupHelper.createLabel("Verification is Successful", ExtentColor.GREEN));
        }catch (Throwable t){
            TestBase.log.debug("Actual value is different than Expected: " + t.getMessage());
            Reporter.log("Actual value is different than Expected: " + t.getMessage());
            TestBase.test.fail("Actual value is different than Expected: " +  t.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(FilePath.screenshotsPath).build());
        }
    }

}
