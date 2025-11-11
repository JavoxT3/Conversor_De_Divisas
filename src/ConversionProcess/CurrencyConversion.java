package ConversionProcess;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConversion {

    public void conversion (int baseCurrency, int fateCurrency) {

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/bfe0c0fe009597bfd96e127f/pair/" + baseCurrency + "/" + fateCurrency);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Opción no disponible");
        }
    }


}
