package ConversionProcess;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyCodes {

    public void codes() throws IOException, InterruptedException {

        URI direction = URI.create("https://v6.exchangerate-api.com/v6/bfe0c0fe009597bfd96e127f/codes");

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(direction).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray supportedCodes = jsonObject.getAsJsonArray("supported_codes");

        System.out.println("\n========== CÓDIGOS DE MONEDAS SOPORTADAS ==========\n");

        for (JsonElement element : supportedCodes) {
            JsonArray currencyPair = element.getAsJsonArray();
            String code = currencyPair.get(0).getAsString();
            String name = currencyPair.get(1).getAsString();
            System.out.println(code + " - " + name);
        }
    }
}
