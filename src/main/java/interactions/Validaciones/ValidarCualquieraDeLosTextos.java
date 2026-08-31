package interactions.Validaciones;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.thucydides.core.annotations.Step;
import utils.AndroidObject;

import java.util.Arrays;
import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ValidarCualquieraDeLosTextos extends AndroidObject implements Interaction {

    private final List<String> textos;

    public ValidarCualquieraDeLosTextos(List<String> textos) {
        this.textos = textos;
    }

    @Override
    @Step("Valida que al menos uno de estos textos esté visible: #textos")
    public <T extends Actor> void performAs(T actor) {
        boolean encontrado = false;
        
        for (String texto : textos) {
            if (textoContiene(actor, texto)) {
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new AssertionError("Fallo en la validación: Ninguno de los textos esperados fue encontrado en la pantalla. Textos buscados: " + textos);
        }
    }

    public static ValidarCualquieraDeLosTextos contieneAlguno(String... textos) {
        return instrumented(ValidarCualquieraDeLosTextos.class, Arrays.asList(textos));
    }
}
