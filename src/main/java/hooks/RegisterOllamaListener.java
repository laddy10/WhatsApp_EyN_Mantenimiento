package hooks;

import cucumber.api.java.Before;
import net.thucydides.core.steps.StepEventBus;

public class RegisterOllamaListener {

  private static final ThreadLocal<Boolean> listenerRegistered = ThreadLocal.withInitial(() -> false);

  @Before(order = 0)
  public void registerListener() {
    if (!listenerRegistered.get()) {
      if (StepEventBus.getEventBus() != null) {
        StepEventBus.getEventBus().registerListener(new OllamaStepListener());
        listenerRegistered.set(true);
      }
    }
  }
}
