# Automatización de API de productos

Proyecto de pruebas de API con Java 17, Maven, Cucumber y Serenity Screenplay. Las pruebas consultan el catálogo de práctica DummyJSON: `https://dummyjson.com/docs/products`.

## Cobertura

Se automatizan dos endpoints de consulta:

- `GET /products`: consulta el catálogo completo y también valida la paginación con `limit=5`.
- `GET /products/{id}`: consulta un producto existente y prueba el caso negativo de un ID inexistente (`404`).

Los cuatro escenarios son de solo lectura: tres positivos y uno negativo. Cada uno valida código HTTP y contenido relevante de la respuesta.

## Requisitos

- JDK 17 o superior.
- Maven 3.8 o superior.
- Acceso a internet para Maven y Restful-Booker.

## Ejecutar

Desde la carpeta del proyecto:

```sh
mvn clean verify
```

El reporte HTML de Serenity se genera en `target/site/serenity/index.html`. Ábrelo en el navegador para revisar el resumen, cada escenario y sus solicitudes/respuestas.

## Evidencias

Las capturas generadas del reporte están en `evidencias/`:

- Resumen de resultados: [reporte-serenity-resumen.png](evidencias/reporte-serenity-resumen.png)
- Detalle de un escenario aprobado: [reporte-serenity-detalle.png](evidencias/reporte-serenity-detalle.png)

Para capturar otra vista manualmente en Windows, abre `target/site/serenity/index.html` y presiona `Win + Shift + S`.
