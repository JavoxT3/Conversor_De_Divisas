package ConversionProcess;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.VarHandle;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversionBase {

    public URI direccion = URI.create("https://v6.exchangerate-api.com/v6/bfe0c0fe009597bfd96e127f/latest/USD");

    public void conversionBase() throws IOException, InterruptedException {

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

        System.out.println("\n============ CONVERSIONES POPULARES ============\n");
        System.out.println("1 USD Equivale a = " + ars + " ARS - Pesos argentinos");
        System.out.println("1 USD Equivale a = " + bob + " BOB - Pesos bolivianos");
        System.out.println("1 USD Equivale a = " + brl + " BRL - Reales brasileños");
        System.out.println("1 USD Equivale a = " + clp + " CLP - Pesos chilenos");
        System.out.println("1 USD Equivale a = " + cop + " COP - Pesos colombianos");
        System.out.println("1 USD Equivale a = " + usd + " USD - Dólares estadunidenses");


    }


}
