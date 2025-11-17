package ConversionProcess;

import Desing.Spacing;
import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Scanner;

// Clase principal encargada del proceso de conversión de monedas.
// Extiende Spacing para poder usar el método spacing() que solo imprime saltos/espacios.
public class CurrencyConversion extends Spacing {

    private History history; // Objeto History para registrar el historial de conversiones

    // Constructor: recibe el historial para poder guardar los resultados
    public CurrencyConversion(History history) {
        this.history = history;
    }

    // Método que consulta y muestra todos los códigos de moneda soportados por la API
    public void codes() {

        // Cliente HTTP para hacer la petición
        HttpClient user = HttpClient.newHttpClient();

        // Construcción del request GET
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/bfe0c0fe009597bfd96e127f/codes"))
                .GET()
                .build();

        HttpResponse<String> responds;

        try {
            // Envía la solicitud y recibe la respuesta como String
            responds = user.send(request1, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            // Si hay un error, se lanza una excepción
            throw new RuntimeException(e);
        }

        // Convierte el texto recibido en un JSON manipulable
        JsonObject jsonObject = JsonParser.parseString(responds.body()).getAsJsonObject();

        // Extrae el array "supported_codes"
        JsonArray codes = jsonObject.getAsJsonArray("supported_codes");

        System.out.println("\n========== CÓDIGOS DE MONEDAS SOPORTADAS ==========\n");

        // Recorre la lista de monedas: cada elemento es un array [código, nombre]
        for (JsonElement item : codes) {
            JsonArray currency = item.getAsJsonArray();
            String code = currency.get(0).getAsString(); // USD, COP, etc.
            String name = currency.get(1).getAsString(); // United States Dollar, Colombian Peso...
            System.out.println(code + " - " + name);
        }

        spacing();
    }

    // Método que realiza la conversión solicitando a la API:
    // baseCurrency → moneda base (USD)
    // fateCurrency → moneda destino (COP)
    // amount → monto
    public CurrencyConversionExchangeRate conversion(String baseCurrency, String fateCurrency, int amount) {

        // Crea la dirección de la API con los datos proporcionados
        URI direccion = URI.create(
                "https://v6.exchangerate-api.com/v6/bfe0c0fe009597bfd96e127f/pair/"
                        + baseCurrency + "/" + fateCurrency + "/" + amount
        );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).GET().build();

        try {
            // Envía la petición
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Si la API responde con un error
            if (response.statusCode() != 200) {
                throw new RuntimeException("Moneda no válida o API no respondió correctamente");
            }

            // Convierte la respuesta JSON en un objeto del record CurrencyConversionExchangeRate
            return new Gson().fromJson(response.body(), CurrencyConversionExchangeRate.class);

        } catch (Exception e) {
            throw new RuntimeException("Error en la conversión" + e.getMessage());
        }
    }

    // Método principal del proceso de conversión, ejecutado desde el menú
    public void conver() throws IOException, InterruptedException {

        // Se crea un objeto para ejecutar las funcionalidades de esta misma clase
        CurrencyConversion consult = new CurrencyConversion(history);

        Scanner write = new Scanner(System.in);

        while (true) {

            // Mostrar lista de códigos soportados
            consult.codes();

            // ==========================
            // VALIDACIÓN DEL MONTO
            // ==========================
            System.out.println("Escriba la cantidad que desea convertir");
            spacing();
            System.out.print("Monto: ");

            int monto;

            // Bucle hasta que el usuario escriba un número válido y mayor que 0
            while (true) {

                if (write.hasNextInt()) {  // Verifica si el valor es numérico
                    monto = write.nextInt();

                    if (monto > 0) {       // Verifica que sea mayor que 0
                        break;
                    } else {
                        spacing();
                        System.out.println("El monto debe ser mayor que 0, inténtelo nuevamente");
                        spacing();
                        System.out.print("Monto: ");
                    }

                } else { // Si el usuario introduce letras u otro símbolo
                    spacing();
                    System.out.println("Valor inválido. Escriba solo números por favor");
                    spacing();
                    System.out.print("Monto: ");
                    write.next(); // Limpia el valor incorrecto
                }
            }

            spacing();
            write.nextLine(); // Limpia buffer

            // ============================================
            // VALIDACIÓN DEL CÓDIGO DE MONEDA → BASE
            // ============================================
            String base;

            while (true) {

                System.out.println("Escriba desde que moneda desea hacer el cambio");
                spacing();
                System.out.print("De: ");

                // Limpia espacios y convierte a mayúsculas
                base = write.nextLine().trim().toUpperCase();

                // Regex que valida EXACTAMENTE 3 letras mayúsculas
                if (base.matches("^[A-Z]{3}$")) {
                    break;
                } else {
                    spacing();
                    System.out.println("Código inválido. El código debe tener exactamente 3 letras (ej: USD, COP, ARS).");
                    spacing();
                }
            }

            spacing();

            // ============================================
            // VALIDACIÓN DEL CÓDIGO DE MONEDA → DESTINO
            // ============================================
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

            // Llama al método que hace la conversión real
            CurrencyConversionExchangeRate moneda = consult.conversion(base, convertir, monto);

            // Prepara una entrada para el historial
            String log = monto + " " + base + " → " +
                    moneda.conversion_result() + " " + convertir;

            // Guarda en el historial
            history.add(log);

            // ==========================
            // MOSTRAR RESULTADO
            // ==========================
            System.out.println("====================== RESULTADO ======================\n");
            double rate = moneda.conversion_rate() * 10000;
            //El valor viene así: 2.6651E-4
            //Lo multiplicamos por 10,000 para quitarle el "E-4":
            DecimalFormat df = new DecimalFormat("0.000");
            //Luego lo formateamos a 3 decimales:
            System.out.println(moneda.base_code() + ": Tasa de cambio = " + df.format(rate));


            System.out.println("\n-----------------------------------------------------\n");
            System.out.println("Monto: " + monto);
            System.out.println(moneda); // toString del record

            spacing();

            // ==========================
            // ¿CONTINUAR O SALIR?
            // ==========================
            System.out.println("¿Quiere hacer otra conversión? Si = 1 / No = 2");
            spacing();
            System.out.print("Opción: ");

            int continued;

            // Bucle para validar opción 1 o 2
            while (true) {

                if (write.hasNextInt()) {

                    continued = write.nextInt();

                    if (continued == 1) {
                        break; // repetir
                    } else if (continued == 2) {
                        return; // salir del método
                    } else {
                        spacing();
                        System.out.println("Opción no valida");
                        spacing();
                        System.out.println("\"¿Quiere hacer otra conversión? Si = 1 / No = 2\"");
                        spacing();
                        System.out.print("Opción: ");
                    }

                } else {
                    spacing();
                    System.out.println("Valor inválido. Escriba solo números por favor");
                    spacing();
                    System.out.print("Opción: ");
                    write.next();
                }
            }
        }
    }
}
