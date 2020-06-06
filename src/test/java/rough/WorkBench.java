package rough;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.FilePath;
import utilities.ReadExcel;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public class WorkBench {

    public void takeScreenShot(WebDriver driver, String fileWithPath){

        TakesScreenshot scrShot =((TakesScreenshot) driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);

        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        WorkBench wb = new WorkBench();

        System.setProperty("webdriver.chrome.driver", FilePath.chromeDriverPath);
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.guru99.com/take-screenshot-selenium-webdriver.html");
        String fileWithPath = "C:\\Users\\reyno\\IdeaProjects\\DataFramework\\src\\test\\resources\\Screenshots\\test.jpg";
        wb.takeScreenShot(driver, fileWithPath);



    }
}
