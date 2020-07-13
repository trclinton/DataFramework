package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import utilities.FilePath;
import utilities.Generic;
import utilities.ReadExcel;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.System.setProperty;

public class TestBase {
    public static WebDriver driver;
    public static JSONParser jsonParser = new JSONParser();
    public static ReadExcel read = new ReadExcel();
    public static LinkedHashMap<Object, LinkedHashMap<Object, Object>> excelConfigData = read.excelFileReader(FilePath.excelFilePath, "Config");
    public static LinkedHashMap<Object, LinkedHashMap<Object, Object>> excelTestData = read.excelFileReader(FilePath.excelFilePath, "TestData");
    public static LinkedHashMap<Object, LinkedHashMap<Object, Object>> excelTestCases = read.excelFileReader(FilePath.excelFilePath, "TestCases");
    public static Map addCustomer;
    public static Map openAccount;
    public static Logger log = Logger.getLogger("Banking Application");
    public static Generic WebUI;
    public static ExtentSparkReporter SparkReporter = new ExtentSparkReporter(FilePath.extentReportsPath);
    public static ExtentReports extent = new ExtentReports();
    public static ExtentTest test;
    public static String forJenkins_browser;

    @BeforeTest
    public void setUp(){
        extent.attachReporter(SparkReporter);
        SparkReporter.config().setReportName("Execution Report");
        SparkReporter.config().setTheme(Theme.DARK);
        SparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        if (driver == null){

            try {
                Object config_Object = jsonParser.parse(new FileReader(FilePath.jsonConfigFilePath));
                Object OR_Object = jsonParser.parse(new FileReader(FilePath.jsonORFilePath));
                JSONObject json_Config = (JSONObject)config_Object;
                JSONObject json_OR = (JSONObject)OR_Object;
                Map map_Application = ((Map) json_Config.get("banking"));
                String banking_URL = (String) map_Application.get("url");
                addCustomer = ((Map)((Map) json_OR.get("bankManager")).get("addCustomer"));
                openAccount = ((Map)((Map) json_OR.get("bankManager")).get("OpenAccount"));
                String browser = excelConfigData.get("Browser").get("Data").toString();
                WebUI = new Generic();

                if((System.getenv("browser") != null) && !System.getenv("browser").isEmpty()){

                    forJenkins_browser = System.getenv("browser");
                    if (forJenkins_browser.equalsIgnoreCase("chrome")){
                        System.setProperty("webdriver.chrome.driver", FilePath.chromeDriverPath);
                        driver = new ChromeDriver();
                        log.debug("Launched Chrome Successfully");
                        extent.setSystemInfo("Browser", browser);
                    }
                    else if (forJenkins_browser.equalsIgnoreCase("firefox")){
                        System.setProperty("webdriver.gecko.driver", FilePath.geckoDriverPath);
                        driver = new FirefoxDriver();
                        log.debug("Launched Firefox Successfully");
                        extent.setSystemInfo("Browser", browser);
                    }
                    else if (forJenkins_browser.equalsIgnoreCase("explorer")){
                        System.setProperty("webdriver.ie.driver", FilePath.IEDriverPath);
                        driver = new InternetExplorerDriver();
                        log.debug("Launched IE Successfully");
                        extent.setSystemInfo("Browser", browser);
                    }
                }

                else{

                    if (browser.equalsIgnoreCase("chrome")){
                        System.setProperty("webdriver.chrome.driver", FilePath.chromeDriverPath);
                        driver = new ChromeDriver();
                        log.debug("Launched Chrome Successfully");
                        extent.setSystemInfo("Browser", browser);
                    }
                    else if (browser.equalsIgnoreCase("firefox")){
                        System.setProperty("webdriver.gecko.driver", FilePath.geckoDriverPath);
                        driver = new FirefoxDriver();
                        log.debug("Launched Firefox Successfully");
                        extent.setSystemInfo("Browser", browser);
                    }
                    else if (browser.equalsIgnoreCase("explorer")){
                        System.setProperty("webdriver.ie.driver", FilePath.IEDriverPath);
                        driver = new InternetExplorerDriver();
                        log.debug("Launched IE Successfully");
                        extent.setSystemInfo("Browser", browser);
                    }
                }

                try {
                    driver.get(banking_URL);
                    log.debug("Successfully navigated to URL: "+banking_URL);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.debug("Failed to open URL: "+banking_URL);
                }

                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();

            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    @AfterTest
    public void flushReports(){
        if (driver==null) {
            return;
        }
        driver.quit();
        driver = null;
        log.debug("Browser has been closed Successfully");
        extent.flush();
    }

}
