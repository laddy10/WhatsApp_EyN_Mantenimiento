package utils.ollama;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.*;

public class OllamaClient {

  private final OkHttpClient client;
  private final String ollamaUrl;
  private final String model;
  private final boolean enabled;

  private static final int MAX_RETRIES = 2;

  public OllamaClient() {
    this.ollamaUrl = System.getProperty("ollama.url", "http://127.0.0.1:11434/api/generate");
    this.model = System.getProperty("ollama.model", "phi3");
    this.enabled = Boolean.parseBoolean(System.getProperty("ollama.enabled", "true"));

    long readTimeout = Long.parseLong(System.getProperty("ollama.timeout", "240"));

    this.client =
        new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .build();
  }

  public boolean isEnabled() {
    return enabled;
  }

  public String ask(String prompt) {
    if (!isEnabled()) {
      return "Ollama analysis is disabled via system properties.";
    }

    JsonObject jsonBody = new JsonObject();
    jsonBody.addProperty("model", model);
    jsonBody.addProperty("prompt", prompt);
    jsonBody.addProperty("stream", false);

    // Optimizaci\u00F3n de par\u00E1metros para velocidad y respuestas
    // deterministas
    JsonObject options = new JsonObject();
    options.addProperty("temperature", 0.3);
    options.addProperty("num_predict", 500);
    options.addProperty("top_k", 10);
    options.addProperty("top_p", 0.5);
    jsonBody.add("options", options);

    RequestBody body =
        RequestBody.create(jsonBody.toString(), MediaType.get("application/json; charset=utf-8"));
    Request request = new Request.Builder().url(ollamaUrl).post(body).build();

    int attempt = 0;
    long backoff = 1000L; // 1 segundo inicial

    while (attempt <= MAX_RETRIES) {
      try (Response response = client.newCall(request).execute()) {
        if (response.isSuccessful() && response.body() != null) {
          JsonObject responseJson =
              JsonParser.parseString(response.body().string()).getAsJsonObject();
          return responseJson.get("response").getAsString();
        } else {
          return "Ollama Server Error: HTTP " + response.code();
        }
      } catch (IOException e) {
        attempt++;
        if (attempt > MAX_RETRIES) {
          return "Error conectando con " + ollamaUrl + " (Intentos excedidos): " + e.getMessage();
        }
        try {
          Thread.sleep(backoff);
          backoff *= 2; // backoff exponencial (1s, 2s)
        } catch (InterruptedException ignored) {
          Thread.currentThread().interrupt();
        }
      }
    }
    return "No se pudo obtener respuesta del modelo local de IA.";
  }
}
