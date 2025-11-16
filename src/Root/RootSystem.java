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
                    write.nextLine();

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
                            break;
                        }
                }
            }
        }

    }
}
