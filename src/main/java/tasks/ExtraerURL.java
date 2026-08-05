package tasks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtraerURL {

  public static String desdeTexto(String texto) {
    Pattern pattern = Pattern.compile("(clro\\.co/\\S+)");
    Matcher matcher = pattern.matcher(texto);
    if (matcher.find()) {
      return matcher.group(1); // devuelve la primera coincidencia
    }

    // Patrón para URLs de yoiz.me
    Pattern patternYoiz = Pattern.compile("(yoiz\\.me/\\S+)");
    Matcher matcherYoiz = patternYoiz.matcher(texto);
    if (matcherYoiz.find()) {
      return matcherYoiz.group(1);
    }

    // Patrón genérico para HTTPS
    Pattern patternHttps = Pattern.compile("https://([^\\s]+)");
    Matcher matcherHttps = patternHttps.matcher(texto);
    if (matcherHttps.find()) {
      return matcherHttps.group(1);
    }

    throw new RuntimeException("No se encontró una URL válida en el texto.");
  }
}
