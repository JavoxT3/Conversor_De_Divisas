package ToRunCode;

import ConversionProcess.History;
import Root.RootSystem;

import java.io.IOException;

/**
 * Clase principal del programa.
 *
 * Aquí inicia la ejecución de todo el sistema.
 *
 * Su trabajo consiste únicamente en:
 * 1. Crear una instancia de History (que será compartida por todas las clases).
 * 2. Crear una instancia de RootSystem, enviándole el mismo History.
 * 3. Ejecutar el menú principal llamando a rootSystem.system().
 */
public class MainRun {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Se crea una única instancia de History para almacenar
        // todas las conversiones que el usuario realice durante la ejecución.
        History history = new History();

        // Se crea el sistema raíz enviándole la misma instancia de History,
        // así todas las conversiones se guardan en el mismo "historial".
        RootSystem rootSystem = new RootSystem(history);

        // Se inicia el menú principal del programa.
        rootSystem.system();
    }
}
