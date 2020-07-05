package listeners;

import base.TestBase;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;
import rough.HostAddress;
import utilities.FilePath;
import utilities.MonitorMails;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CustomListeners extends TestBase implements ITestListener, ISuiteListener {

    public void onTestFailure(ITestResult result) {

        System.out.println("The name of the testcase failed is : " + result.getName());
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(FilePath.screenshotsPath);
            try {
                FileUtils.copyFile(SrcFile, DestFile);
                log.debug("Successfully captured a screenshot");
                Reporter.log("Successfully captured a screenshot");
            } catch (IOException e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
                log.debug("Exception while taking screenshot");
            }
        }

        Reporter.log("<a target = \"_blank\" href=" + FilePath.screenshotsPath + ">Screenshot</a>");
        Reporter.log("<br>");
        Reporter.log("<a target = \"_blank\" href=" + FilePath.screenshotsPath + "><img src= "+FilePath.screenshotsPath+"></img></a>");
        try {
            test.fail(result.getName().toUpperCase() + " Failed with an exception: " +  result.getThrowable().getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(FilePath.screenshotsPath).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // When Test case get Skipped, this method is called.
    public void onTestSkipped(ITestResult Result) {
        System.out.println("The name of the testcase Skipped is : " + Result.getName());
        TestBase.log.debug(Result.getName() + " test has been Skipped.");
        Reporter.log(Result.getName() + " test has been Skipped.");
        TestBase.test.log(Status.SKIP, MarkupHelper.createLabel(Result.getName() + " test has been Skipped.", ExtentColor.BLUE));
    }

    // When Test case get Started, this method is called.
    public void onTestStart(ITestResult Result) {
        test = extent.createTest(Result.getName());
        if (!WebUI.isTestRunnable(Result.getName())){
            throw new SkipException("Skipping the test "+ Result.getName().toUpperCase()+" as the RunMode has been set to 'N'");
        }
        System.out.println(Result.getName() + " test case started");
    }

    // When Test case get passed, this method is called.
    public void onTestSuccess(ITestResult Result) {
        System.out.println("The name of the testcase passed : " + Result.getName());
    }

    public void onFinish(ISuite arg0){

        MonitorMails mails = new MonitorMails();
        String localHostAddress = null;
        try {
            localHostAddress = "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/job/DataDrivenProject/Extent_20Report/";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            mails.sendMail(FilePath.server, FilePath.from, FilePath.to, FilePath.subject, FilePath.messageBody + " : " + localHostAddress);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
