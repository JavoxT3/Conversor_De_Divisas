package Root;

import ConversionProcess.*;
import Desing.Spacing;

import java.io.IOException;
import java.util.Scanner;

// Clase principal del programa. Aquí se muestra el menú y se controla
// la navegación entre las distintas opciones del sistema.
public class RootSystem extends Spacing {

    // Se mantiene una referencia global al historial de conversiones
    private History history;

    // El historial se recibe desde el main o desde la clase que inicia el sistema
    public RootSystem(History history) {
        this.history = history;
    }

    // Menú principal del programa (método estático porque no usa atributos)
    public static void menu () {
        System.out.println("\n******************************** MENU ********************************\n");
        System.out.println(" 1) Consultar monedas disponibles para convertir");
        System.out.println(" 2) Hacer conversiones populares");
        System.out.println(" 3) Realizar una consulta personalizada");
        System.out.println(" 4) Consultar Historial");
        System.out.println(" 5) Salir");

        System.out.println("\nPor favor, elija una opción válida: ");

        spacing();  // Método heredado de Spacing para hacer una separación visual
    }

    // Método que controla todo el flujo del programa
    public void system () throws IOException, InterruptedException {

        Scanner write = new Scanner(System.in);
        int option;

        System.out.println("\n");

        while (true) { // Bucle infinito hasta que el usuario decida salir

            menu(); // Mostrar menú en cada iteración

            System.out.print("Opción: ");

            // Validación de la opción ingresada
            while (true) {

                // Verifica si el usuario ingresó un número entero
                if (write.hasNextInt()) {
                    option = write.nextInt();
                    spacing();

                    // Si la opción es mayor que 0, se acepta
                    if (option > 0) {
                        break;
                    } else {
                        System.out.println("Por favor, elija una opción válida, inténtelo nuevamente");
                        spacing();
                        System.out.print("Opción: ");
                    }

                } else {
                    // Entrada inválida (letras, símbolos...)
                    spacing();
                    System.out.println("Valor inválido. Escriba solo números por favor");
                    spacing();
                    System.out.print("Opción: ");
                    write.next(); // Limpia la entrada inválida
                }
            }

            // Opción para salir del programa
            if (option == 5) {
                // ⚠️ Aquí creas un nuevo History, pero no se usa para nada
                History history = new History();

                System.out.println("Espero haberte ayudado, vuelve cuando quieras...");
                spacing();
                System.exit(0);
            }

            // Selección de la opción del menú
            switch (option) {

                case 1:
                    // Consulta los códigos soportados por la API
                    CurrencyCodes codesAvailable = new CurrencyCodes();
                    codesAvailable.codes();
                    break;

                case 2:
                    // Muestra conversiones predeterminadas (USD → COP, etc.)
                    ConversionBase conversion = new ConversionBase(history);
                    conversion.conversionBase();
                    break;

                case 3:
                    // Conversión personalizada (usuario elige monedas y monto)
                    CurrencyConversion consult = new CurrencyConversion(history);
                    consult.conver();
                    break;

                case 4:
                    // Muestra el historial de conversiones
                    System.out.println(history);
                    break;
            }
        }
    }
}
