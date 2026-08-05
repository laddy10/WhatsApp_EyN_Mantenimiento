package utils;

import io.appium.java_client.android.AndroidDriver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.thucydides.core.webdriver.SerenityWebdriverManager;

public class UtilidadesAndroid {

  public static void abrirLinkEnNavegador(String url) {
    // Asegurar protocolo
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
      url = "https://" + url;
    }

    // Obtener el driver y desenvolver el WebDriverFacade
    var webDriver = SerenityWebdriverManager.inThisTestThread().getCurrentDriver();
    AndroidDriver driver;

    if (webDriver instanceof net.thucydides.core.webdriver.WebDriverFacade) {
      driver =
          (AndroidDriver)
              ((net.thucydides.core.webdriver.WebDriverFacade) webDriver).getProxiedDriver();
    } else {
      driver = (AndroidDriver) webDriver;
    }

    Map<String, Object> intentArgs = new HashMap<>();
    intentArgs.put("command", "am");
    intentArgs.put("args", List.of("start", "-a", "android.intent.action.VIEW", "-d", url));

    driver.executeScript("mobile: shell", intentArgs);
  }
}
