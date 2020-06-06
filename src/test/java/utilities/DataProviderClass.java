package utilities;


import base.TestBase;
import org.testng.annotations.DataProvider;

public class DataProviderClass extends TestBase {

    @DataProvider(name = "CustomerDetails")
    public Object[][] getCustomerData(){
        String data1_firstName = excelTestData.get("First Name").get("Input_Data1").toString();
        String data1_lastName = excelTestData.get("Last Name").get("Input_Data1").toString();
        String data1_postalCode = excelTestData.get("Postal Code").get("Input_Data1").toString();

        String data2_firstName = excelTestData.get("First Name").get("Input_Data2").toString();
        String data2_lastName = excelTestData.get("Last Name").get("Input_Data2").toString();
        String data2_postalCode = excelTestData.get("Postal Code").get("Input_Data2").toString();

        String data3_firstName = excelTestData.get("First Name").get("Input_Data3").toString();
        String data3_lastName = excelTestData.get("Last Name").get("Input_Data3").toString();
        String data3_postalCode = excelTestData.get("Postal Code").get("Input_Data3").toString();

        return new Object[][] {{data1_firstName, data1_lastName, data1_postalCode}, {data2_firstName, data2_lastName, data2_postalCode},
                {data3_firstName, data3_lastName, data3_postalCode}};
    }
}
