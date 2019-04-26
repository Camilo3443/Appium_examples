package suport;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static AndroidDriver<MobileElement> driver;


    public static AndroidDriver<MobileElement> getDriver ( ) throws MalformedURLException {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }

    private static void createDriver ( ) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("plataformName", "Android");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/luis.camilo/Documents/Camilo/Appium_java_android/src/main/resources/CTAppium-1-1.apk");

        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    public static void killDriver ( ) {
        if (driver != null) {
            driver.quit();
            driver = null;
            {

            }
        }
    }

}

