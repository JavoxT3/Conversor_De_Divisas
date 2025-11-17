package ConversionProcess;

import Desing.Spacing;
import Root.RootSystem;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

/**
 * Clase encargada de manejar las conversiones populares y rápidas,
 * como USD -> ARS, USD -> COP, USD -> BRL, etc.
 *
 * Se comunica con la API para obtener las tasas de cambio
 * y almacena cada conversión realizada en el historial.
 */
public class ConversionBase extends Spacing {

    // Referencia al historial compartido, para guardar cada conversión
    private History history;

    /**
     * Constructor que recibe la misma instancia de History que usa el programa.
     */
    public ConversionBase(History history) {
        this.history = history;
    }

    /**
     * Menú de opciones para las conversiones populares.
     */
    public static void menu() {
        System.out.println("\n****************************************************************\n");
        System.out.println("Escoja la opción de la conversión: \n");
        System.out.println("0) Volver");
        System.out.println("1) Dólar =>> Peso argentino");
        System.out.println("2) Peso argentino =>> Dólar");
        System.out.println("3) Dólar =>> Real brasileño");
        System.out.println("4) Real brasileño =>> Dólar");
        System.out.println("5) Dólar =>> Peso colombiano");
        System.out.println("6) Peso colombiano =>> Dólar");
        System.out.println("7) Dólar =>> Peso boliviano");
        System.out.println("8) Peso boliviano =>> Dólar");
        System.out.println("9) Dólar =>> Peso chileno");
        System.out.println("10) Peso chileno  =>> Dólar");
        System.out.println("\n*********************************************************************\n");
        System.out.println("Por favor elija una opción correcta");
    }

    // Mensajes usados en pantalla según la opción del usuario
    String one = "Dólar a Peso argentino.";
    String two = "Peso argentino a Dólar.";
    String three = "Dólar a Real brasileño.";
    String four = "Real brasileño a Dólar.";
    String five = "Dólar a Peso colombiano.";
    String six = "Peso colombiano a Dólar.";
    String seven = "Dólar a Peso boliviano.";
    String eight = "Peso boliviano a Dólar.";
    String nine = "Dólar a Peso chileno.";
    String ten = "Peso chileno  a Dólar.";

    // URL principal para obtener tasas de cambio con base en USD
    public URI direccion = URI.create(
            "https://v6.exchangerate-api.com/v6/bfe0c0fe009597bfd96e127f/latest/USD"
    );

    /**
     * Método principal que ejecuta el flujo de las conversiones rápidas.
     * Hace peticiones a la API, captura errores, valida datos
     * y guarda los resultados en el historial.
     */
    public void conversionBase() throws IOException, InterruptedException {

        while (true) {

            // --- Petición a la API ---
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(direccion).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            // Convertimos a objeto JSON
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

            // Extraemos las tasas de cambio necesarias
            double ars = rates.get("ARS").getAsDouble();
            double bob = rates.get("BOB").getAsDouble();
            double brl = rates.get("BRL").getAsDouble();
            double clp = rates.get("CLP").getAsDouble();
            double cop = rates.get("COP").getAsDouble();
            double usd = rates.get("USD").getAsDouble();

            // Mostramos menú
            menu();

            Scanner option = new Scanner(System.in);
            spacing();
            System.out.print("Opción: ");
            int number;

            // --- Validación de opción ---
            while (true) {
                if (option.hasNextInt()) {
                    number = option.nextInt();
                    if (number >= 0 && number <= 10) {
                        break;
                    }
                }
                spacing();
                System.out.println("Opción no valida");
                menu();
                spacing();
                System.out.print("Opción: ");
                option.nextLine();
            }

            // Volver al menú principal
            if (number == 0) {
                RootSystem main = new RootSystem(history);
                main.system();
                return;
            }

            // --- Selección de tipo de conversión ---
            spacing();
            System.out.print("Ingrese el monto de dinero que quiere convertir de ");
            switch (number) {
                case 1 -> System.out.println(one);
                case 2 -> System.out.println(two);
                case 3 -> System.out.println(three);
                case 4 -> System.out.println(four);
                case 5 -> System.out.println(five);
                case 6 -> System.out.println(six);
                case 7 -> System.out.println(seven);
                case 8 -> System.out.println(eight);
                case 9 -> System.out.println(nine);
                case 10 -> System.out.println(ten);
            }

            // --- Lectura del monto ---
            spacing();
            System.out.print("Monto: ");
            double amount;

            while (true) {
                if (option.hasNextDouble()) {
                    amount = option.nextDouble();
                    if (amount > 0) break;
                }
                spacing();
                System.out.println("Por favor ingrese un monto válido");
                spacing();
                System.out.print("Monto: ");
                option.nextLine();
            }

            // --- Variables para el resultado ---
            double result = 0;
            String baseCurrency = "";
            String fateCurrency = "";

            // --- Cálculos según la opción ---
            switch (number) {
                case 1 -> { result = amount * ars; baseCurrency = "USD"; fateCurrency = "ARS"; }
                case 2 -> { result = amount / ars; baseCurrency = "ARS"; fateCurrency = "USD"; }
                case 3 -> { result = amount * brl; baseCurrency = "USD"; fateCurrency = "BRL"; }
                case 4 -> { result = amount / brl; baseCurrency = "BRL"; fateCurrency = "USD"; }
                case 5 -> { result = amount * cop; baseCurrency = "USD"; fateCurrency = "COP"; }
                case 6 -> { result = amount / cop; baseCurrency = "COP"; fateCurrency = "USD"; }
                case 7 -> { result = amount * bob; baseCurrency = "USD"; fateCurrency = "BOB"; }
                case 8 -> { result = amount / bob; baseCurrency = "BOB"; fateCurrency = "USD"; }
                case 9 -> { result = amount * clp; baseCurrency = "USD"; fateCurrency = "CLP"; }
                case 10 -> { result = amount / clp; baseCurrency = "CLP"; fateCurrency = "USD"; }
            }

            // Mostrar resultado final en pantalla
            spacing();
            System.out.printf(
                    "Valor: %.2f [%s]\n[%s] → [%s] = %.2f\n",
                    amount, baseCurrency, baseCurrency, fateCurrency, result
            );

            // Crear texto para historial
            String resultText = amount + " " + baseCurrency + " → " +
                    result + " " + fateCurrency;

            // Guardar en historial
            history.add(resultText);

            // Preguntar si desea continuar
            spacing();
            System.out.println("¿Desea hacer otra conversión? Si = 1 / No, volver = 2");
            spacing();
            System.out.print("Opción: ");
            int keep;

            while (true) {
                if (option.hasNextInt()) {
                    keep = option.nextInt();
                    if (keep == 1) break;
                    if (keep == 2) return;
                }
                spacing();
                System.out.println("Opción no valida, intente de nuevo");
                spacing();
                System.out.print("Opción: ");
                option.nextLine();
            }
        }
    }
}
