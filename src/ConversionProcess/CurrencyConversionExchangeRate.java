package ConversionProcess;

public record CurrencyConversionExchangeRate  (String base_code,
                                             String target_code,
                                             double conversion_rate,
                                             double conversion_result) {


    @Override
    public String toString() {
        return "Conversión\n" +
                "De: " + base_code + " → " + target_code + " - " +
                "Resultado: " + conversion_result;

    }
}
