package Desing;

/**
 * Esta clase contiene herramientas visuales para mejorar la apariencia
 * de los mensajes en consola.
 *
 * En este caso solo incluye un método: spacing()
 * que imprime una línea separadora para organizar visualmente el menú
 * o las respuestas del sistema.
 */
public class Spacing {

    /**
     * Método estático para imprimir una separación visual en consola.
     *
     * static → permite llamarlo sin crear un objeto de Spacing.
     *
     * Ejemplo:
     * Spacing.spacing();
     */
    public static void spacing() {
        System.out.println("\n========================================\n");
    }
}
