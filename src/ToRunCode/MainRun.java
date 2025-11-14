package ToRunCode;

import ConversionProcess.ConversionBase;
import ConversionProcess.CurrencyCodes;
import ConversionProcess.CurrencyConversion;
import ConversionProcess.CurrencyConversionExchangeRate;

import java.io.IOException;
import java.util.Scanner;

public class MainRun {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner write = new Scanner(System.in);

        System.out.println("\n******************************** MENU ********************************\n");

        System.out.println(" 1) Consultar monedas disponibles para convertir");
        System.out.println(" 2) Hacer conversiónes populares");
        System.out.println(" 3) Realizar una consulta personalizada");
        System.out.println(" 4) Consultar Historia");

        System.out.println("\n*********************************************************************\n");

        /*CurrencyConversion consulta = new CurrencyConversion();

        System.out.println("Escriba la cantidad que desea convertir");
        int monto = write.nextInt();
        write.nextLine();
        System.out.println("Escriba desde que moneda desea hacer el cambio");
        String base = write.nextLine();
        System.out.println("Escriba la moneda a la que quiere hacer el cambio");
        String convertir = write.nextLine();

        CurrencyConversionExchangeRate moneda = consulta.conversion(base, convertir, monto);

        System.out.println(monto);
        System.out.println(moneda);
*/
        ConversionBase converson = new ConversionBase();
        converson.conversionBase();

        //CurrencyCodes codigos = new CurrencyCodes();
        //codigos.codes();
    }
}
