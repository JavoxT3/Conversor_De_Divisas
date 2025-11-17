package ConversionProcess;

import Desing.Spacing;
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

// Clase que muestra los códigos soportados por la API.
// Extiende Spacing para poder usar el método spacing() que imprime saltos visuales.
public class CurrencyCodes extends Spacing {


    // Método que obtiene y muestra la lista de códigos de moneda soportados por la API
    public void codes() throws IOException, InterruptedException {

        Scanner writing = new Scanner(System.in);

        // URL de la API para obtener los códigos de las monedas
        URI direction = URI.create("https://v6.exchangerate-api.com/v6/bfe0c0fe009597bfd96e127f/codes");

        // Cliente HTTP para realizar solicitudes
        HttpClient client = HttpClient.newHttpClient();

        // Construcción de la solicitud GET
        HttpRequest request = HttpRequest.newBuilder().uri(direction).GET().build();

        // Envía la solicitud y almacena la respuesta como String
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Convierte la respuesta en un objeto JSON
        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

        // Extrae el array donde vienen los códigos de moneda soportados
        JsonArray supportedCodes = jsonObject.getAsJsonArray("supported_codes");

        System.out.println("\n========== CÓDIGOS DE MONEDAS SOPORTADAS ==========\n");

        // Cada elemento de supported_codes es un array con dos datos:
        // [0] = código de moneda (USD, COP, EUR...)
        // [1] = nombre de la moneda
        for (JsonElement element : supportedCodes) {
            JsonArray currencyPair = element.getAsJsonArray();
            String code = currencyPair.get(0).getAsString();
            String name = currencyPair.get(1).getAsString();

            // Imprime el código junto con su nombre
            System.out.println(code + " - " + name);
        }

        spacing();

        // Opción para volver al menú principal
        System.out.println("Escriba 1 para volver al menu principal");

        spacing();

        System.out.print("Opción: ");

        int option;

        // Validación de la opción ingresada por el usuario
        while (true) {

            // Verifica si el usuario ingresó un número
            if (writing.hasNextInt()) {

                option = writing.nextInt();

                // Única opción válida: volver al menú
                if (option == 1) {
                    break; // sale del ciclo
                } else {
                    spacing();
                    System.out.println("Elija la opción disponible por favor");
                    spacing();
                    System.out.println("Escriba 1 para volver al menu principal");
                    spacing();
                    System.out.print("Opción: ");
                }

            } else {
                // Si el usuario escribe letras o símbolos
                spacing();
                System.out.println("Valor inválido. Escriba solo números por favor");
                spacing();
                System.out.println("Escriba 1 para volver al menu principal");
                spacing();
                System.out.print("Opción: ");
                writing.next(); // Descarta el valor inválido
            }
        }

        // Al elegir 1, volvemos al menú principal
        return;
    }
}
