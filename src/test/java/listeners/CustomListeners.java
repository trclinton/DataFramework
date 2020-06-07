package listeners;

import base.TestBase;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import utilities.FilePath;

import java.io.File;
import java.io.IOException;

public class CustomListeners extends TestBase implements ITestListener {

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
    }

    // When Test case get Started, this method is called.
    public void onTestStart(ITestResult Result) {
        test = extent.createTest(Result.getName());
        if (!genericKey.isTestRunnable(Result.getName())){
            throw new SkipException("Skipping the test "+ Result.getName().toUpperCase()+" as the RunMode has been set to 'N'");
        }
        System.out.println(Result.getName() + " test case started");
    }

    // When Test case get passed, this method is called.
    public void onTestSuccess(ITestResult Result) {
        System.out.println("The name of the testcase passed : " + Result.getName());
    }
}
