package ConversionProcess;

import Desing.Spacing;
import Root.RootSystem;
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

public class ConversionBase extends Spacing {

    public static void menu () {
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
            spacing();;
            System.out.print("Opción: ");
            int number = option.nextInt();
            spacing();;


            while (number < 0 || number > 10) {
                System.out.println("Opción no valida");
                menu();
                spacing();;
                System.out.print("Opción: ");
                number = option.nextInt();
                spacing();;
            }

            if (number == 0) {
                    option.close();
                    RootSystem main = new RootSystem();
                    main.system();
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

            spacing();;
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
            spacing();


            System.out.printf("Valor: %.2f [%s]\n[%s] → [%s] = %.2f\n",
                    amount, baseCurrency, baseCurrency, fateCurrency, result);
            spacing();;

            System.out.println("¿Desea hacer otra converción? Si = 1 / No, volver = 0");
            spacing();;

            System.out.print("Opción: ");
            int keep  = option.nextInt();

            while (keep < 0 || keep > 2) {

                spacing();;
                System.out.println("Ingrese un valor valido.");

                spacing();;

                System.out.println("¿Desea hacer otra converción? Si = 1 / No, volver = 2");

                spacing();;

                System.out.print("Opción: ");
                keep  = option.nextInt();
                option.nextLine();

            }

            if (keep == 1) {

            } else {
                RootSystem main = new RootSystem();
                main.system();
            }


        }
    }
}
