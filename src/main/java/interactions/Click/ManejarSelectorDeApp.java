package interactions.Click;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import utils.AndroidObject;
import hooks.ReportHooks;

import java.util.concurrent.TimeUnit;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ManejarSelectorDeApp extends AndroidObject implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            // Bajamos el timeout temporalmente a 3 segundos para una busqueda rapida
            androidDriver(actor).manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            
            boolean popupVisible = false;
            try {
                popupVisible = androidDriver(actor)
                        .findElementByAndroidUIAutomator("new UiSelector().text(\"Chrome\")")
                        .isDisplayed();
            } catch (Exception e) {
                // No apareció la opcion de Chrome
            }

            if (popupVisible) {
                ReportHooks.registrarPaso("Se detectó el selector de apps de Android. Seleccionando Chrome...");
                androidDriver(actor)
                        .findElementByAndroidUIAutomator("new UiSelector().text(\"Chrome\")")
                        .click();
                
                // Algunos dispositivos pueden requerir confirmar con "Siempre" o "Solo una vez"
                try {
                     androidDriver(actor)
                        .findElementByAndroidUIAutomator("new UiSelector().textMatches(\"(?i).*solo una vez.*|.*siempre.*\")")
                        .click();
                } catch (Exception ignored) {
                }
            } else {
                 ReportHooks.registrarPaso("No se detectó el selector de apps, continuando flujo normal.");
            }
        } finally {
            // Restauramos el implicit wait por defecto (asumido 15 segundos en Appium)
            androidDriver(actor).manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }
    }

    public static ManejarSelectorDeApp siAparece() {
        return instrumented(ManejarSelectorDeApp.class);
    }
}
