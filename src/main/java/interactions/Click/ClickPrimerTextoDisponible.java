package interactions.Click;

import hooks.ReportHooks;
import io.appium.java_client.MobileBy;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebElement;
import utils.CapturaDePantallaMovil;

import java.util.List;

/**
 * Hace CLIC en la PRIMERA de varias variantes de texto que este presente en
 * pantalla. Resuelve el caso de textos que cambian segun el estado de la linea:
 * p.ej. el boton de Roaming aparece como "Comprar roaming" si NO se tiene el
 * servicio, o "Gestionar Roaming" si YA se tiene. Ambos son validos y el flujo
 * debe poder hacer clic en el que este.
 * <p>
 * Uso en un Task:
 * actor.attemptsTo(
 * ClickPrimerTextoDisponible.entre("Comprar roaming", "Gestionar Roaming")
 * );
 * <p>
 * Si NINGUNA variante aparece, lanza una excepcion clara indicando cuales se
 * buscaron (para que el reporte sea util y no un "no encontrado" a secas).
 */
public class ClickPrimerTextoDisponible implements Interaction {

    private final String[] variantes;

    public ClickPrimerTextoDisponible(String... variantes) {
        this.variantes = variantes;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        for (String texto : variantes) {
            List<WebElement> encontrados = BrowseTheWeb.as(actor).getDriver()
                    .findElements(MobileBy.xpath(
                            "//*[contains(@text,\"" + texto + "\")]"));

            if (!encontrados.isEmpty()) {
                // clic en el ULTIMO que aparezca (el mas reciente en pantalla),
                // siguiendo el criterio que ya usas en el proyecto.
                WebElement objetivo = encontrados.get(encontrados.size() - 1);
                objetivo.click();
                ReportHooks.registrarPaso("Clic en variante presente: '" + texto + "'");
                CapturaDePantallaMovil.tomarCapturaPantalla("Clic en '" + texto + "'");
                return;
            }
        }

        // Ninguna variante aparecio: reporte claro y fallo explicito.
        String buscadas = String.join("' | '", variantes);
        CapturaDePantallaMovil.tomarCapturaPantalla("Ninguna variante encontrada");
        ReportHooks.registrarPaso("No se encontro ninguna de las variantes: '" + buscadas + "'");
        throw new AssertionError(
                "No se encontro ninguna de las variantes esperadas: '" + buscadas + "'. "
                        + "Revisar si el bot cambio el texto o si el estado de la linea "
                        + "muestra una variante nueva no contemplada.");
    }

    public static ClickPrimerTextoDisponible entre(String... variantes) {
        return Instrumented.instanceOf(ClickPrimerTextoDisponible.class)
                .withProperties(new Object[]{variantes});
    }
}