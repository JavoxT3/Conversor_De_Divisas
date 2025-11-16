package ConversionProcess;

import Desing.Spacing;
import Root.RootSystem;
import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

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

    public void conver() throws IOException, InterruptedException {
        CurrencyConversion consult = new CurrencyConversion();
        Scanner write = new Scanner(System.in);

        while (true) {
            consult.codes();

            System.out.println("Escriba la cantidad que desea convertir");
            spacing();
            System.out.print("Monto: ");
            int monto;
            while (true) {
                if (write.hasNextInt()) {
                    monto = write.nextInt();
                    if (monto > 0) {
                        break;
                    } else {
                        spacing();
                        System.out.println("El monto debe ser mayor que 0, inténtelo nuevamente");
                        spacing();
                        System.out.print("Monto: ");
                    }
                } else {
                    spacing();
                    System.out.println("Valor inválido. Escriba solo números por favor");
                    spacing();
                    System.out.print("Monto: ");
                    write.next();
                }
            }
            spacing();
            write.nextLine();
            String base;
            while (true) {
                System.out.println("Escriba desde que moneda desea hacer el cambio");
                spacing();
                System.out.print("De: ");
                base = write.nextLine().trim().toUpperCase();
                if (base.matches("^[A-Z]{3}$")) {
                    break;
                } else {
                    spacing();
                    System.out.println("Código inválido. El código debe tener exactamente 3 letras (ej: USD, COP, ARS).");
                    spacing();
                }
            }

            spacing();
            String convertir;
            while (true) {
                System.out.println("Escriba la moneda a la que quiere hacer el cambio");
                spacing();
                System.out.print("A: ");
                convertir = write.nextLine().trim().toUpperCase();
                if (convertir.matches("^[A-Z]{3}$")) {
                    break;
                } else {
                    spacing();
                    System.out.println("Código inválido. El código debe tener exactamente 3 letras (ej: USD, COP, ARS).");
                    spacing();
                }
            }
            spacing();
            CurrencyConversionExchangeRate moneda = consult.conversion(base, convertir, monto);

            System.out.println("====================== RESULTADO ======================");
            System.out.println(moneda.base_code() + ":" + " Tasa de cambio = " + moneda.conversion_rate());
            System.out.println("\n-----------------------------------------------------\n");
            System.out.println("Monto: " + monto);
            System.out.println(moneda);

            spacing();

            System.out.println("¿Quiere hacer otra conversión? Si = 1 / No = 2");
            int continued = write.nextInt();

            while (continued != 1 && continued != 2) {
                System.out.println("Por favor, elija una de las opciones disponibles");
                continued = write.nextInt();
            }
            if (continued == 1) {
                continue;
            } else {
                RootSystem main = new RootSystem();
                main.system();
            }
        }
    }
}

