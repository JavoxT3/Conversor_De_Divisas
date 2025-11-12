package ConversionProcess;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConversion {

    public CurrencyConversionExchangeRate conversion (String  baseCurrency, String  fateCurrency, int amount) {

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/bfe0c0fe009597bfd96e127f/pair/" + baseCurrency + "/" + fateCurrency+"/"+amount);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 400 || response.statusCode() == 404) {
                System.out.println("Error al buscar datos");
            } else {
                return new Gson().fromJson(response.body(), CurrencyConversionExchangeRate.class);
            }
        } catch (Exception e) {
            throw new RuntimeException("Opción no disponible");
        }
        return null;
    }


}
