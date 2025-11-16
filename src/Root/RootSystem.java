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
    int option;

        System.out.println("\n");

        while (true) {
            menu();
            System.out.print("Opción: ");

            while (true) {
                if (write.hasNextInt()) {

                    option = write.nextInt();
                    spacing();
                    if (option > 0) {
                        break;
                    } else {
                        System.out.println("Por favor, elija una opción valida, inténtelo nuevamente");
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
                    consult.conver();
            }
        }
    }
}
