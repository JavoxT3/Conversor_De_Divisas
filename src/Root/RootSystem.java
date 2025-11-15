package Root;

import ConversionProcess.ConversionBase;
import ConversionProcess.CurrencyCodes;
import ConversionProcess.CurrencyConversion;
import ConversionProcess.CurrencyConversionExchangeRate;
import Desing.Spacing;

import java.io.IOException;
import java.util.Scanner;

public class RootSystem extends Spacing {

public static void menu () {
    System.out.println("\n******************************** MENU ********************************\n");
    System.out.println(" 1) Consultar monedas disponibles para convertir");
    System.out.println(" 2) Hacer conversiónes populares");
    System.out.println(" 3) Realizar una consulta personalizada");
    System.out.println(" 4) Consultar Historia");
    System.out.println(" 5) Salir");

    System.out.println("\nPor favor, elija una opción valida: ");

    spacing();
}

    public void system () throws IOException, InterruptedException {
    Scanner write = new Scanner(System.in);
    menu();
    System.out.print("Opción: ");
    int option = write.nextInt();
    spacing();


    while (option < 1 || option > 5) {
        System.out.println("Elija una de las opciones disponibles por favor");
        spacing();
        menu();
        System.out.print("Opción: ");
        option = write.nextInt();
        spacing();

    }
        if (option == 5) {
            System.out.println("Espero haberte ayudado, vuelve cuando quieras...");
            spacing();
            System.exit(0);
        }

    switch (option) {
        case 1:
            CurrencyCodes codesAvailable = new CurrencyCodes();
            codesAvailable.codes();
            break;
        case 2:

            ConversionBase conversion = new ConversionBase();
            conversion.conversionBase();
            break;
        case 3:
            CurrencyConversion consult = new CurrencyConversion();

            System.out.println("Escriba la cantidad que desea convertir");
            spacing();
            System.out.print("Monto: ");
            int monto = write.nextInt();
            write.nextLine();
            spacing();
            System.out.println("Escriba desde que moneda desea hacer el cambio");
            spacing();
            System.out.print("De: ");
            String base = write.nextLine();

            spacing();
            System.out.println("Escriba la moneda a la que quiere hacer el cambio");
            System.out.print("A: ");
            String convertir = write.nextLine();
            spacing();
            CurrencyConversionExchangeRate moneda = consult.conversion(base, convertir, monto);

            System.out.println(monto);
            System.out.println(moneda);
            break;
        }
    }
}
