package tests;
import com.nimi.Browser;
import com.nimi.ui.common.CommonConstants;
import com.nimi.ui.pages.Pages;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UITestBase {
    public static Properties properties = new Properties();

    @BeforeTest()
    public void setUp(){
        properties = getEnvData(CommonConstants.ENV);
        Browser.getDriver(CommonConstants.BROWSER);
        Pages.loginPage().loadUrl(properties.getProperty(CommonConstants.BASE_URL));
    }

    public static Properties getEnvData(String env){

        try {

            if(env.equalsIgnoreCase("DEV")){
                FileInputStream fisDev = new FileInputStream("../web-automation/src/main/resources/config/qa.properties");
                properties.load(fisDev);
            } else if (env.equalsIgnoreCase("QA")) {
                FileInputStream fisQA = new FileInputStream("../web-automation/src/main/resources/config/qa.properties");
                properties.load(fisQA);
            } else if (env.equalsIgnoreCase("UAT")) {
                FileInputStream fisUAT = new FileInputStream("../web-automation/src/main/resources/config/qa.properties");
                properties.load(fisUAT);
            }
        }catch (Exception e){

        }
        return properties;
    }

    @AfterSuite
    public void killDriver(){
        String systemType = System.getProperty("os.name").toLowerCase();

        if (systemType.contains("win")) {
            try {
                // Selenium drivers don't always close properly, kill them
                System.out.println("Close one or more driver exe files");
                Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
                Runtime.getRuntime().exec("taskkill /f /im operadriver.exe");
                Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
                Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
            } catch (IOException e) {
                System.out.println("Failed to close one or more driver exe files");
            }
        }
    }
}
