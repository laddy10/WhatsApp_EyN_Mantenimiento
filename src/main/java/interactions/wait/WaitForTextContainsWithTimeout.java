package interactions.wait;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import questions.TextoQueContengaX;

import java.util.Arrays;
import java.util.List;

public class WaitForTextContainsWithTimeout implements Question<Boolean> {

    private final List<String> textos;
    private final int timeoutSeconds;

    public WaitForTextContainsWithTimeout(int timeoutSeconds, List<String> textos) {
        this.timeoutSeconds = timeoutSeconds;
        this.textos = textos;
    }

    public static WaitForTextContainsWithTimeout esperar(int timeoutSeconds, String... textos) {
        return new WaitForTextContainsWithTimeout(timeoutSeconds, Arrays.asList(textos));
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000L);

        while (System.currentTimeMillis() < endTime) {

            for (String texto : textos) {

                if (TextoQueContengaX.verificarTexto(texto).answeredBy(actor)) {
                    return true;
                }
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return false;
    }
}