package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runner.RunWith;
import utils.BeforeSuite;
import utils.DataToFeature;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "utils", "hooks"},
        snippets = SnippetType.CAMELCASE,
        tags = "@WH_EyN_Opciones_Rapidas_Opciones_Consultar_Consumo"

)


@RunWith(CustomRunner.class)
public class GeneralRunner {
  @BeforeSuite
  public static void test() throws InvalidFormatException, IOException {
    DataToFeature.overrideFeatureFiles("src/test/resources/features");
  }
}
