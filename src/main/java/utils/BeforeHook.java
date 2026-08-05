package utils;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

import cucumber.api.Scenario;
import jxl.common.Logger;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import org.junit.After;
import org.junit.Before;

public class BeforeHook {

  /********** Log Attribute **********/
  private static final Logger LOGGER = Logger.getLogger(BeforeHook.class);

  @Before
  public void initScenario(Scenario scenario) {
    LOGGER.info(
        "************************************************************************************************");
    LOGGER.info("[ Start stage ] --> " + scenario.getName());
    LOGGER.info(
        "************************************************************************************************");

    OnStage.setTheStage(new OnlineCast()); // ← esto evita el error
  }

  public static void prepareStage(String urlBase) {
    OnStage.setTheStage(new OnlineCast());
    theActorCalled("Usuario").whoCan(CallAnApi.at(urlBase));
  }

  @After
  public void endScenario(Scenario scenario) {
    LOGGER.info(
        "************************************************************************************************");
    LOGGER.info("[ End of stage ] --> " + scenario.getName());
    LOGGER.info(
        "************************************************************************************************");
  }
}
