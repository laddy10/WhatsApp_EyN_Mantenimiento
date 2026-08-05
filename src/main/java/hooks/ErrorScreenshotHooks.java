package hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class ErrorScreenshotHooks {

    private static final String ERROR_FOLDER = "Error";
    private static final String FILENAME = "error.png";

    @Before(order = 5)
    public void limpiarCarpetaError(Scenario scenario) {
        Path errorPath = Paths.get(ERROR_FOLDER);
        if (!Files.exists(errorPath)) {
            return;
        }
        try {
            Files.walk(errorPath)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
            System.out.println("[ErrorScreenshot] 🗑️  Carpeta 'Error/' eliminada.");
        } catch (IOException e) {
            System.err.println("[ErrorScreenshot] ⚠️  No se pudo limpiar: " + e.getMessage());
        }
    }

    @After(order = 10)
    public void capturarSiFallo(Scenario scenario) {
        if (!scenario.isFailed()) {
            return;
        }
        try {
            WebDriver driver = ThucydidesWebDriverSupport.getDriver();
            if (driver == null) {
                System.err.println("[ErrorScreenshot] ⚠️  Driver nulo.");
                return;
            }

            // Desenvolver el facade de Serenity para llegar al driver real de Appium
            if (driver instanceof WebDriverFacade) {
                WebDriver real = ((WebDriverFacade) driver).getProxiedDriver();
                if (real != null) {
                    driver = real;
                }
            }

            if (!(driver instanceof TakesScreenshot)) {
                System.err.println("[ErrorScreenshot] ⚠️  El driver no soporta TakesScreenshot.");
                return;
            }

            Files.createDirectories(Paths.get(ERROR_FOLDER));
            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Files.write(Paths.get(ERROR_FOLDER, FILENAME), bytes);
            System.out.println("[ErrorScreenshot] 📸 Captura guardada: " + ERROR_FOLDER + "/" + FILENAME);

        } catch (Exception e) {
            System.err.println("[ErrorScreenshot] ⚠️  Error al capturar: " + e.getMessage());
        }
    }
}