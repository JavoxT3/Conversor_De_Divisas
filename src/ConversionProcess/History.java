package ConversionProcess;

import java.util.ArrayList;
import java.util.List;

public class History {
    public void setHistorial(String result) {
        List historial = new ArrayList();
        historial.add(result);

        String toString() {
            return "Resultado: \n" +
                    historial;

        }

    }

}
