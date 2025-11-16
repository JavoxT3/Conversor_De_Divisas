package ConversionProcess;

import Desing.Spacing;
import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConversion extends Spacing {

    public void codes() {
        HttpClient user = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create("https://v6.exchangerate-api.com/v6/bfe0c0fe009597bfd96e127f/codes")).GET().build();

        HttpResponse<String> responds;
        try {
            responds = user.send(request1, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        JsonObject jsonObject = JsonParser.parseString(responds.body()).getAsJsonObject();
        JsonArray codes = jsonObject.getAsJsonArray("supported_codes");

        System.out.println("\n========== CÓDIGOS DE MONEDAS SOPORTADAS ==========\n");

        for (JsonElement item : codes) {
            JsonArray currency = item.getAsJsonArray();
            String code = currency.get(0).getAsString();
            String name = currency.get(1).getAsString();
            System.out.println(code + " - " + name);
        }

        spacing();
    }

    public CurrencyConversionExchangeRate conversion (String  baseCurrency, String  fateCurrency, int amount) {


        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/bfe0c0fe009597bfd96e127f/pair/" + baseCurrency + "/" + fateCurrency+"/"+amount);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Moneda no válida o API no respondió correctamente");
            }
                return new Gson().fromJson(response.body(), CurrencyConversionExchangeRate.class);

        } catch (Exception e) {
            throw new RuntimeException("Error en la conversión" + e.getMessage());
        }
    }


}
