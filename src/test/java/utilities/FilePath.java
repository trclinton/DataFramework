package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilePath {
    public static DateFormat df = new SimpleDateFormat("ddMMyy_HHmmss");
    public static Date dateobj = new Date();
    public static String dateTime = df.format(dateobj);
    public static String directory = System.getProperty("user.dir");
    public static String jsonConfigFilePath = directory + "\\src\\test\\resources\\json\\Config.json";
    public static String jsonORFilePath = directory + "\\src\\test\\resources\\json\\OR.json";
    public static String excelFilePath = directory + "\\src\\test\\resources\\excel\\Datafile.xlsx";
    public static String chromeDriverPath = directory + "\\src\\test\\resources\\drivers\\chromedriver.exe";
    public static String geckoDriverPath = directory + "\\src\\test\\resources\\drivers\\geckodriver.exe";
    public static String IEDriverPath = directory + "\\src\\test\\resources\\drivers\\IEDriverServer.exe";
    public static String screenshotsPath = directory + "\\src\\test\\resources\\Screenshots\\Screenshot_"+ dateTime +".jpg";
    public static String extentReportsPath = directory + "\\src\\test\\resources\\Screenshots\\extentReport.html";
}
