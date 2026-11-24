# Automatización de API de productos

Proyecto de pruebas de API con Java 17, Maven, Cucumber y Serenity Screenplay. Las pruebas consultan el catálogo de práctica DummyJSON: `https://dummyjson.com/docs/products`.

## Cobertura

Se automatizan dos endpoints de consulta:

- `GET /products`: consulta el catálogo completo y valida la paginación con `limit=5`.
- `GET /products/{id}`: consulta un producto existente y prueba el caso negativo de un ID inexistente (`404`).

Los cuatro escenarios son de solo lectura: tres positivos y uno negativo. Cada uno valida el código HTTP y contenido relevante de la respuesta.

## Resultados

La ejecución `mvn clean verify` con JDK 17.0.2 terminó correctamente:

- 4 escenarios ejecutados.
- 3 escenarios positivos y 1 negativo.
- 4 aprobados, 0 fallidos y 0 con errores.
- El escenario negativo confirma que un ID inexistente responde con `404`.

La captura de la pestaña **Test Results** muestra los cuatro escenarios aprobados: [reporte-serenity-resultados.png](evidencias/reporte-serenity-resultados.png).

## Decisiones de diseño

- **Cucumber** expresa los criterios en Gherkin para que cada escenario describa una consulta y su resultado esperado.
- **Serenity Screenplay** separa los pasos de Cucumber de las solicitudes REST. Como el proyecto solo usa el verbo `GET`, una única Task (`GetProducts`) recibe la ruta y sirve para el catálogo, la paginación y el detalle, cumpliendo la pauta de una Task por verbo HTTP utilizado.
- La Question `ResponseCode` centraliza la lectura del estado HTTP; los pasos `Then` agregan aserciones del contenido específico de cada respuesta.
- Se eligió una API de datos de prueba y consultas de solo lectura para evitar modificar registros remotos. Los dos endpoints cubiertos son `/products` y `/products/{id}`; `limit=5` es una variante de consulta del primero.

## Requisitos

- JDK 17.
- Maven 3.8 o superior.
- Acceso a internet para Maven y DummyJSON.

## Ejecutar

Desde la carpeta del proyecto:

```sh
mvn clean verify
```

El reporte HTML de Serenity se genera en `target/site/serenity/index.html`. Ábrelo en el navegador para revisar el resumen, cada escenario y sus solicitudes/respuestas.

## Evidencias

Las capturas completas del reporte están en `evidencias/`:

- [Resumen del reporte](evidencias/reporte-serenity-resumen.png)
- [Resultados de los cuatro escenarios](evidencias/reporte-serenity-resultados.png)
- [Detalle de un escenario aprobado](evidencias/reporte-serenity-detalle.png)
