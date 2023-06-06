package tests.com.nimi.api.tests;

import com.nimi.RequestUtil;
import com.nimi.api.common.CommonConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.util.Properties;

public class APITestBase {
    public static RequestUtil requestUtil;
    public static Properties properties = new Properties();
    public static String adminAccessToken;
    protected Log logger = LogFactory.getLog(getClass());
    public static String accessToken;

    @BeforeMethod()
    public void setUp(){
        properties = getEnvData(CommonConstants.ENV);
        requestUtil = RequestUtil.setRequestUtils(properties.getProperty(CommonConstants.BASE_URL), properties.getProperty(CommonConstants.BASE_PATH), adminAccessToken);
    }

    public static Properties getEnvData(String env){

        try {

            if(env.equalsIgnoreCase("DEV")){
                FileInputStream fisDev = new FileInputStream("../api-automation/src/main/resources/config/dev.properties");
                properties.load(fisDev);
            } else if (env.equalsIgnoreCase("QA")) {
                FileInputStream fisQA = new FileInputStream("../api-automation/src/main/resources/config/qa.properties");
                properties.load(fisQA);
            } else if (env.equalsIgnoreCase("UAT")) {
                FileInputStream fisUAT = new FileInputStream("../api-automation/src/main/resources/config/uat.properties");
                properties.load(fisUAT);
            }
        }catch (Exception e){

        }
        return properties;
    }
}
