package utils;

import interactions.wait.WaitFor;
import interactions.wait.WaitForTextContains;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import java.util.ArrayList;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.NoSuchElementException;

import static utils.Constantes.VALIDACIONES_PIN;

public class AndroidObject {

  public void HideKeyboard(Actor actor) {
    androidDriver(actor).hideKeyboard();
  }

  // SCROLL
  public void SwipeToElement(Actor actor, String label) {
    androidDriver(actor)
        .findElementByAndroidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                + "new UiSelector().text(\""
                + label
                + "\"));")
        .click();
  }

  public void UnScrollArribaInicio(Actor actor) {
    try {
      androidDriver(actor)
          .findElementByAndroidUIAutomator(
              "new UiScrollable(new UiSelector().resourceIdMatches(\"android:id/list\").scrollable(true)).scrollBackward()");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void UnScrollAbajo(Actor actor) {
    try {
      androidDriver(actor)
          .findElementByAndroidUIAutomator(
              "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()");
    } catch (Exception e) {
    }
  }

  public static void scrollToText(Actor actor, String texto) {
    try {
      androidDriver(actor)
          .findElement(
              new MobileBy.ByAndroidUIAutomator(
                  ("new UiScrollable(new UiSelector().scrollable(true))"
                      + ".scrollIntoView(new UiSelector().textContains(\""
                      + texto
                      + "\"))")));
    } catch (Exception e) {
      throw new RuntimeException("No se encontró el texto al hacer scroll: " + texto, e);
    }
  }

  // VALIDACIONES
  public boolean validarTexto(Actor actor, String text) {
    try {
      return androidDriver(actor)
          .findElementByAndroidUIAutomator("new UiSelector().text(\"" + text + "\")")
          .isDisplayed();
    } catch (NoSuchElementException e) {
      System.out.println("Texto no encontrado: " + text);
      return false;
    }
  }

  public void ElTextoContiene(Actor actor, String text) {
    androidDriver(actor)
        .findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + text + "\")")
        .isDisplayed();
  }

  public boolean textoContiene(Actor actor, String text) {
    String safe = text.replace("\\", "\\\\").replace("\"", "\\\"");
    try {
      // por texto
      if (androidDriver(actor)
          .findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + safe + "\")")
          .isDisplayed()) return true;

      // por content-desc (por si el bot lo pone en description)
      if (androidDriver(actor)
          .findElementByAndroidUIAutomator("new UiSelector().descriptionContains(\"" + safe + "\")")
          .isDisplayed()) return true;

      return false;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  // CLICK
  public void ClickByText(Actor actor, String text) {
    actor.attemptsTo(WaitFor.aTime(1000));
    androidDriver(actor)
        .findElementByAndroidUIAutomator("new UiSelector().text(\"" + text + "\")")
        .click();
  }

  public void ClickElTextoContiene(Actor actor, String text) {
    androidDriver(actor)
        .findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + text + "\")")
        .click();
  }

  String texto = "";

  public ArrayList<Character> LeerMensaje(Actor actor) {
    androidDriver(actor).openNotifications();
    actor.attemptsTo(WaitFor.aTime(3000));
    try {
      texto =
          androidDriver(actor)
              .findElementByAndroidUIAutomator(
                  "new UiSelector().textContains(\"digite el siguiente codigo:\")")
              .getText();
    } catch (Exception e) {
      System.out.println(e);
    }

    ArrayList<Character> lista = new ArrayList<>();
    for (int i = 0; i < texto.length(); i++) {
      if (Character.isDigit(texto.charAt(i))) {
        lista.add(texto.charAt(i));
      }
    }

    return lista;
  }

  public ArrayList<Character> LeerPINValidacion(Actor actor) {
    androidDriver(actor).openNotifications();
    actor.attemptsTo(WaitForTextContains.withAnyTextContains(VALIDACIONES_PIN));

    String[] triggerTexts = {
      "ingresar el siguiente código PIN de validación:", "código de seguridad", "código PIN"
    };

    texto = "";
    for (String trigger : triggerTexts) {
      try {
        texto =
            androidDriver(actor)
                .findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + trigger + "\")")
                .getText();
        if (!texto.isEmpty()) break;
      } catch (Exception ignored) {
      }
    }

    ArrayList<Character> lista = new ArrayList<>();
    if (!texto.isEmpty()) {
      java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\d{4,}");
      java.util.regex.Matcher m = p.matcher(texto);
      if (m.find()) {
        String code = m.group();
        for (char c : code.toCharArray()) {
          lista.add(c);
        }
      } else {
        for (int i = 0; i < texto.length(); i++) {
          if (Character.isDigit(texto.charAt(i))) {
            lista.add(texto.charAt(i));
          }
        }
      }
    }

    return lista;
  }

  public void DigitarNumeros(Actor actor, String digito) {
    switch (digito) {
      case "0":
        androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
        break;
      case "1":
        androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        break;
      case "2":
        androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
        break;
      case "3":
        androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_3));
        break;
      case "4":
        androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_4));
        break;
      case "5":
        androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_5));
        break;
      case "6":
        androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_6));
        break;
      case "7":
        androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_7));
        break;
      case "8":
        androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_8));
        break;
      case "9":
        androidDriver(actor).pressKey(new KeyEvent(AndroidKey.DIGIT_9));
        break;

      default:
        break;
    }
  }

  public void Atras(Actor actor) {
    androidDriver(actor).navigate().back();
  }

  public AndroidDriver getAndroidDriver(Actor actor) {
    return androidDriver(actor);
  }

  @SuppressWarnings("unchecked")
  public static AndroidDriver androidDriver(Actor actor) {
    return (AndroidDriver) ((WebDriverFacade) getDriver(actor)).getProxiedDriver();
  }

  private static WebDriverFacade getDriver(Actor actor) {
    return ((WebDriverFacade) BrowseTheWeb.as(actor).getDriver());
  }

  public TouchAction withAction(Actor actor) {
    return new TouchAction(androidDriver(actor));
  }

  public void SwitchtoFrame(Actor actor, int id) {
    androidDriver(actor).switchTo().frame(id);
  }
}