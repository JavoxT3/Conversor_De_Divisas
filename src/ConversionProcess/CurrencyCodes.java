package ConversionProcess;

import Desing.Spacing;
import Root.RootSystem;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class CurrencyCodes extends Spacing {

    public void codes () throws IOException, InterruptedException {

        Scanner writing = new Scanner(System.in);

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
        spacing();

        System.out.println("Escriba 1 para volver al menu principal");

        spacing();

        System.out.print("Opción: ");
        int option;

        while (true) {
            if (writing.hasNextInt()) {

                option = writing.nextInt();
                if (option == 1) {
                    break;
                } else {
                    spacing();
                    System.out.println("Elija la opción disponible por favor");
                    spacing();
                    System.out.println("Escriba 1 para volver al menu principal1");
                    spacing();
                    System.out.print("Opción: ");
                }
            } else {
                spacing();
                System.out.println("Valor inválido. Escriba solo números por favor");
                spacing();
                System.out.println("Escriba 1 para volver al menu principal2");
                spacing();
                System.out.print("Opción: ");
                writing.next();
            }
        }
            RootSystem main = new RootSystem();
            main.system();
    }
}
