package interactions.Click;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.thucydides.core.annotations.Step;
import utils.AndroidObject;
import hooks.ReportHooks;

import java.util.concurrent.TimeUnit;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ClickSeguroParaMenus extends AndroidObject implements Interaction {

    private final String botonMenu;
    private final String textoValidacion;

    public ClickSeguroParaMenus(String botonMenu, String textoValidacion) {
        this.botonMenu = botonMenu;
        this.textoValidacion = textoValidacion;
    }

    @Override
    @Step("Click seguro en '#botonMenu' validando la apertura con '#textoValidacion'")
    public <T extends Actor> void performAs(T actor) {
        boolean menuAbierto = false;
        int maxIntentos = 3;

        for (int i = 1; i <= maxIntentos; i++) {
            ReportHooks.registrarPaso("Intento " + i + " de abrir el menú: " + botonMenu);
            
            // 1. Clic tradicional al botón (incluye su espera original)
            ClickByText(actor, botonMenu);
            
            // 2. Espera rápida para verificar si la sábana (Bottom sheet) se abrió realmente
            // Reducimos temporalmente el implicit wait a 1 segundo para buscar rápido
            androidDriver(actor).manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            try {
                long startTime = System.currentTimeMillis();
                // Esperamos un máximo de 4 segundos a que aparezca el texto dentro de la sábana
                while ((System.currentTimeMillis() - startTime) < 4000) {
                    if (textoContiene(actor, textoValidacion)) {
                        menuAbierto = true;
                        break;
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ignored) {}
                }
            } finally {
                // Restauramos siempre el implicit wait normal (asumimos 15s)
                androidDriver(actor).manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            }
            
            if (menuAbierto) {
                ReportHooks.registrarPaso("El menú se abrió exitosamente en el intento " + i);
                break;
            } else {
                ReportHooks.registrarPaso("Al parecer la animación impidió el clic. Reintentando...");
            }
        }
        
        if (!menuAbierto) {
            throw new RuntimeException("Fallo por Intermitencia: No se logró abrir el menú '" + botonMenu + "' después de " + maxIntentos + " intentos.");
        }
    }

    public static ClickSeguroParaMenus de(String botonMenu, String textoValidacion) {
        return instrumented(ClickSeguroParaMenus.class, botonMenu, textoValidacion);
    }
}
