package hooks;

import cucumber.api.java.Before;
import net.thucydides.core.steps.StepEventBus;

public class RegisterMetricasListener {

  private static final ThreadLocal<Boolean> listenerRegistered = ThreadLocal.withInitial(() -> false);

  @Before(order = 1) // Se ejecuta después de otros befores
  public void registerListener() {
    if (!listenerRegistered.get()) {
      if (StepEventBus.getEventBus() != null) {
        StepEventBus.getEventBus().registerListener(new MetricasStepListener());
        listenerRegistered.set(true);
      }
    }
  }
}
