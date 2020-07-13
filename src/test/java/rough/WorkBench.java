package rough;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import utilities.FilePath;
import utilities.ReadExcel;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;

public class WorkBench {
    public static WebDriver driver;
    @Test
    public void grid1() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("version", "83.0.4103.61");
        capabilities.setPlatform(Platform.ANY);
        driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), capabilities);
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
    }

    @Test
    public void grid2() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("version", "76.0.1");
        capabilities.setPlatform(Platform.ANY);
        driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), capabilities);
        driver.get("https://in.yahoo.com/");
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
    }
}
