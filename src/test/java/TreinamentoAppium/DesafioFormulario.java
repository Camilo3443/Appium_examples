import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DesafioFormulario {

    public AndroidDriver getAndroidDriver ( ) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("plataformName", "Android");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/luis.camilo/Documents/Camilo/Appium_java_android/src/main/resources/CTAppium-1-1.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;

    }

    @Test
    public void preencherFormulario ( ) throws MalformedURLException, InterruptedException {

        AndroidDriver driver = getAndroidDriver();


        //selecionar fomulario
        driver.findElement(By.xpath("//android.widget.TextView[@text='Formulário']")).click();

        //escrever Nome]
        MobileElement campoNome = (MobileElement) driver.findElement(MobileBy.AccessibilityId("nome"));
        campoNome.sendKeys("test");

        //valida texto
        String text = campoNome.getText();
        Assert.assertEquals("test", text);

        //clicar no combo
        driver.findElement(MobileBy.AccessibilityId("console")).click();

        //escolher opcao
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='PS4']")).click();

        String text2 = driver.findElement(By.xpath("//android.widget.Spinner/android.widget.TextView")).getText();
        Assert.assertEquals("PS4", text2);

        driver.findElement(By.className("android.widget.CheckBox")).click();
        driver.findElement(By.className("android.widget.Switch")).click();

        MobileElement check = (MobileElement) driver.findElement(By.className("android.widget.CheckBox"));
        MobileElement swtch = (MobileElement) driver.findElement(By.className("android.widget.Switch"));
        check.getAttribute("checked");
        Assert.assertTrue(check.getAttribute("checked").equals("true"));
        Assert.assertTrue(swtch.getAttribute("checked").equals("false"));


        driver.quit();

    }

}