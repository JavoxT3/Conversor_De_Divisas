package ConversionProcess;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.VarHandle;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversionBase {

    public static void separador() {
        System.out.println("\n========================================\n");
    }
    public static void menu () {
        System.out.println("\n******************************** MENU ********************************\n");

        System.out.println("1) Dólar =>> Peso argentino");
        System.out.println("2) Peso argentino =>> Dólar");
        System.out.println("3) Dólar =>> Real brasileño");
        System.out.println("4) Real brasileño =>> Dólar");
        System.out.println("5) Dólar =>> Peso colombiano");
        System.out.println("6) Peso colombiano =>> Dólar");
        System.out.println("7) Dólar =>> Peso boliviano");
        System.out.println("8) Peso boliviano =>> Dólar");
        System.out.println("9) Dólar =>> Peso chileno");
        System.out.println("10)Peso chileno  =>> Dólar");

        System.out.println("7) Histórico de Conversões");
        System.out.println("0) SAIR");
        System.out.println("\n*********************************************************************\n");
        System.out.println("Por favor, elija una opción valida: ");
    }

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

    public URI direccion = URI.create("https://v6.exchangerate-api.com/v6/bfe0c0fe009597bfd96e127f/latest/USD");

    public void conversionBase() throws IOException, InterruptedException {

        while (true) {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(direccion).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

            double ars = rates.get("ARS").getAsDouble();
            double bob = rates.get("BOB").getAsDouble();
            double brl = rates.get("BRL").getAsDouble();
            double clp = rates.get("CLP").getAsDouble();
            double cop = rates.get("COP").getAsDouble();
            double usd = rates.get("USD").getAsDouble();

            menu();

            Scanner option = new Scanner(System.in);
            separador();
            System.out.print("Opción: ");
            int number = option.nextInt();
            separador();


            while (number < 0 || number > 10) {
                System.out.println("Opción no valida");
                menu();
                separador();
                System.out.print("Opción: ");
                number = option.nextInt();
                separador();
            }

            if (number == 0) {
                break;
            }

            System.out.print("Ingrese el monto de dinero que quiere convertir de ");

            switch (number) {
                case 1:
                    System.out.println(one);
                    break;
                case 2:
                    System.out.println(two);
                    break;
                case 3:
                    System.out.println(three);
                    break;
                case 4:
                    System.out.println(four);
                    break;
                case 5:
                    System.out.println(five);
                    break;
                case 6:
                    System.out.println(six);
                    break;
                case 7:
                    System.out.println(seven);
                    break;
                case 8:
                    System.out.println(eight);
                    break;
                case 9:
                    System.out.println(nine);
                    break;
                case 10:
                    System.out.println(ten);
                    break;
            }

            separador();
            System.out.print("Monto: ");
            double amount = option.nextDouble();

            double result = 0;
            String baseCurrency = "";
            String fateCurrency = "";

            switch (number) {
                case 1:
                    result = amount * ars;
                    baseCurrency = "USD";
                    fateCurrency = "ARS";
                    break;
                case 2:
                    result = amount / ars;
                    baseCurrency = "ARS";
                    fateCurrency = "USD";
                    break;
                case 3:
                    result = amount * brl;
                    baseCurrency = "USD";
                    fateCurrency = "BRL";
                    break;
                case 4:
                    result = amount / brl;
                    baseCurrency = "BRL";
                    fateCurrency = "USD";
                    break;
                case 5:
                    result = amount * cop;
                    baseCurrency = "USD";
                    fateCurrency = "COP";
                    break;
                case 6:
                    result = amount / cop;
                    baseCurrency = "COP";
                    fateCurrency = "USD";
                    break;
                case 7:
                    result = amount * bob;
                    baseCurrency = "USD";
                    fateCurrency = "BOB";
                    break;
                case 8:
                    result = amount / bob;
                    baseCurrency = "BOB";
                    fateCurrency = "USD";
                    break;
                case 9:
                    result = amount * clp;
                    baseCurrency = "USD";
                    fateCurrency = "CLP";
                    break;
                case 10:
                    result = amount / clp;
                    baseCurrency = "CLP";
                    fateCurrency = "USD";
                    break;
            }
            separador();
            System.out.printf("Valor %.2f [%s] equivale a =>>> %.2f [%s]\n",
                    amount, baseCurrency, result, fateCurrency);
            separador();

            System.out.println("¿Desea hacer otra converción? Si = 1 / No = 2");
            separador();

            System.out.print("Opción: ");
            int keep  = option.nextInt();

            while (keep < 0 || keep > 2) {

                separador();
                System.out.println("Ingrese un valor valido.");

                separador();

                System.out.println("¿Desea hacer otra converción? Si = 1 / No = 2");

                separador();

                System.out.print("Opción: ");
                keep  = option.nextInt();

            }

            separador();

            if (keep == 1) {

            } else if (keep == 2) {
                break;
            } else {
                System.out.println("Ingrese un valor valido.");
            }



        }

        System.out.println("Finalizando programa");
        separador();

    }
}
