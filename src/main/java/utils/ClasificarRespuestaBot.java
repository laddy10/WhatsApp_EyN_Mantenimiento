package utils;

import models.EstadoConversacion;
import net.serenitybdd.screenplay.Actor;
import questions.TextoQueContengaX;

public class ClasificarRespuestaBot {

    public static EstadoConversacion obtenerEstado(Actor actor) {

        // 🟡 Pantalla inicial NO válida
        if (TextoQueContengaX.verificarTexto("Ideas de regalo").answeredBy(actor)
                || TextoQueContengaX.verificarTexto("¿Qué quieres hacer hoy?").answeredBy(actor)
                || TextoQueContengaX.verificarTexto("Te damos la bienvenida al chat de Claro").answeredBy(actor)
                || TextoQueContengaX.verificarTexto("Menú principal").answeredBy(actor)
                || TextoQueContengaX.verificarTexto("Elige una de las opciones que tenemos para ti").answeredBy(actor)) {

            return EstadoConversacion.PANTALLA_INICIAL;
        }

        // 🧑‍💼 Escalamiento a asesor
        if (TextoQueContengaX.verificarTexto("Voy a comunicarte con uno de nuestros asesores").answeredBy(actor)
                || TextoQueContengaX.verificarTexto("comunicarte con uno de nuestros asesores").answeredBy(actor)
                || TextoQueContengaX.verificarTexto("te comunicaremos con un asesor").answeredBy(actor)) {

            return EstadoConversacion.ESPERANDO_ASESOR;
        }

        // 🔴 Errores conversacionales
        if (TextoQueContengaX.verificarTexto("No entendí").answeredBy(actor)
                || TextoQueContengaX.verificarTexto("respuesta no es válida").answeredBy(actor)
                || TextoQueContengaX.verificarTexto("opciones mostradas anteriormente").answeredBy(actor)
                || TextoQueContengaX.verificarTexto("ingrese el número de opción").answeredBy(actor)
                || TextoQueContengaX.verificarTexto("Aún estoy contigo").answeredBy(actor)
                || TextoQueContengaX.verificarTexto("No logré entender").answeredBy(actor)) {

            return EstadoConversacion.ERROR;
        }

        // 🟢 Flujo normal
        return EstadoConversacion.FLUJO_NORMAL;
    }

    // ✅ Verifica si un texto está presente en pantalla sin lanzar excepción
    public static boolean textoEstaPresente(Actor actor, String texto) {
        try {
            return TextoQueContengaX.verificarTexto(texto).answeredBy(actor);
        } catch (Exception e) {
            return false;
        }
    }
}