package ConversionProcess;

/**
 *
 * Este record representa los datos que devuelve la API al realizar una conversión:
 * - base_code: código de la moneda de origen.
 * - target_code: código de la moneda destino.
 * - conversion_rate: tasa de cambio actual.
 * - conversion_result: resultado final después de convertir el monto.
 */
public record CurrencyConversionExchangeRate (
        String base_code,
        String target_code,
        double conversion_rate,
        double conversion_result
) {

    /**
     * Sobrescribimos el método toString() únicamente para mostrar de forma clara
     * el resultado de la conversión cuando este record se imprime.
     *
     */
    @Override
    public String toString() {
        return "Conversión realizada:\n" +
                "De: " + base_code + " → " + target_code + "\n" +
                "Resultado: " + conversion_result;
    }
}
