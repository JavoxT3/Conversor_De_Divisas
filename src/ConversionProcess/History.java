package ConversionProcess;

import Desing.Spacing;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de almacenar el historial de conversiones.
 * Cada vez que se realiza una conversión, se guarda como un texto dentro de una lista.
 * Luego, este historial se puede imprimir desde el menú principal.
 */
public class History extends Spacing {

    // Lista donde se almacenan todas las conversiones realizadas
    private List<String> historial = new ArrayList<>();

    /**
     * Agrega un nuevo registro al historial.
     *
     * @param result Texto con la información de la conversión realizada.
     */
    public void add(String result) {
        historial.add(result); // Se agrega la conversión a la lista
    }

    /**
     * Retorna una representación en texto del historial completo.
     * Si no hay elementos, devuelve un mensaje indicando que está vacío.
     */
    @Override
    public String toString() {
        if (historial.isEmpty()) {
            return "No hay conversiones registradas aún.";
        }

        return "Historial de conversiones:\n\n"
                + "========================================\n\n"
                + String.join("\n", historial); // Une todas las líneas en un solo texto
    }
}
