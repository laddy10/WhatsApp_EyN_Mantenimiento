package utils;

import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MyDriver implements DriverSource {

  private static AndroidDriver driver;

  public static AndroidDriver getDriver() {
    return driver;
  }

  @Override
  public WebDriver newDriver() {
    try {
      DesiredCapabilities caps = new DesiredCapabilities();

      caps.setCapability("automationName", "UiAutomator2");
      caps.setCapability("platformName", "Android");
      caps.setCapability(
              "app",
              System.getProperty(
                      "app", System.getProperty("user.dir") + "/src/test/resources/app/whatsapp.apk"));
      caps.setCapability("appPackage", "com.whatsapp");
      caps.setCapability("appActivity", ".Main");

      caps.setCapability("noReset", true);
      caps.setCapability("fullReset", false);
      caps.setCapability("autoGrantPermissions", true);
      caps.setCapability("autoDismissAlerts", true);
      caps.setCapability("newCommandTimeout", 8000);

      URL hub = new URL("http://127.0.0.1:4723/wd/hub");
      driver = new AndroidDriver(hub, caps);
      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      return driver;

    } catch (Exception e) {
      throw new RuntimeException("Error iniciando el driver de Appium", e);
    }
  }

  @Override
  public boolean takesScreenshots() {
    return true;
  }
}