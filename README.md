## 📌 CONVERSOR DE DIVISAS – Java App

Aplicación de consola en Java que permite consultar códigos de monedas, realizar conversiones populares o personalizadas, y guardar un historial de conversiones usando una API externa de tasas de cambio.

## 🚀 Características principales
✔️ 1. Consulta de monedas disponibles

La app consume el endpoint /codes de ExchangeRate-API para obtener todas las monedas soportadas y mostrarlas en pantalla.

✔️ 2. Conversiones populares

Incluye un módulo para convertir cantidades entre monedas comunes o previamente definidas.

✔️ 3. Conversiones personalizadas

## El usuario puede ingresar:

- Monto

- Código de moneda base

- Código de moneda destino

- La app valida que los códigos tengan exactamente 3 letras (regex ^[A-Z]{3}$).

✔️ 4. Historial de conversiones

- Cada conversión realizada se guarda en una lista centralizada en la clase History.
- El usuario puede consultar el historial desde el menú principal.

✔️ 5. Consumo de API REST con HttpClient

El sistema usa java.net.http.HttpClient para realizar llamadas HTTP modernas en Java.

✔️ 6. Formateo de resultados

- Los valores se muestran de forma legible (por ejemplo, con 3 decimales usando DecimalFormat).

## 🛠 Tecnologías utilizadas

- Java 17+

- HttpClient (java.net.http)

- Google Gson – para parsear JSON

- ExchangeRate-API – datos de tasas de cambio

- Regex – validación de códigos de moneda

## 🧠 Cómo funciona
🔹 1. Inicio del programa

- La clase MainRun inicializa el historial y carga el menú principal.

🔹 2. Menú interactivo

- El usuario puede seleccionar entre:



<img width="726" height="535" alt="Captura de pantalla 2025-11-17 013340" src="https://github.com/user-attachments/assets/98384543-05f1-4b18-85c7-8f0ca269bf0c" />




🔹 3. Conversión

- El sistema crea la URL adecuada:

https://v6.exchangerate-api.com/v6/{API_KEY}/pair/{base}/{target}/{amount}


- El JSON devuelto se mapea en el record:

- CurrencyConversionExchangeRate


# Luego se muestran:

- Tasa de cambio

- Monto convertido

- Resultado final

- Y se almacena en el historial.

## 📘 Ejemplo de salida


<img width="624" height="417" alt="Captura de pantalla 2025-11-17 013631" src="https://github.com/user-attachments/assets/66d3e3e2-3c69-4e8a-83fe-d68ffead6333" />


 
## 📜 Instalación y ejecución
1️⃣ Clonar repositorio
- git clone https://github.com/usuario/tu-repositorio.git

2️⃣ Importar el proyecto en tu IDE

- (Eclipse, IntelliJ o NetBeans)

3️⃣ Agregar la librería Gson (si no usas Maven)

- Descargar: https://github.com/google/gson

4️⃣ Ejecutar la clase:
- MainRun.java

## 📂 Historial de conversiones

- Se muestran como:


<img width="437" height="276" alt="Captura de pantalla 2025-11-17 013946" src="https://github.com/user-attachments/assets/459db1fe-d783-4573-9101-785407390262" />



## 👨‍💻 Autor

- Jhonatan Montiel
- Proyecto educativo y personal para practicar:

- Manipulación de API REST

- Gestión de datos en Java

- Diseño modular

- Validación y control de flujo

Formateo numérico y output limpio

# 📬 Contáctame

LinkedIn: https://www.linkedin.com/in/jhonatan-montiel/
