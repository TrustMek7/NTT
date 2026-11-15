# Escuelita — Escenarios BDD

Proyecto individual de la Escuela de Testing NTT para practicar la definición de criterios de aceptación mediante Gherkin.

## Contenido

- `EJ01/configuracion_promociones.feature`: creación de promociones con descuentos y desactivación al finalizar su horario.
- `EJ01/visibilidad_promociones_cliente.feature`: presentación de precios, etiquetas y descuentos según el estado de una promoción.

Los escenarios utilizan `Feature`, `Background`, `Scenario Outline`, tablas de ejemplos y etiquetas para organizar los casos.

## Preparación

Con Node.js y npm instalados, ejecutar desde esta carpeta:

```sh
npm ci
```

## Estado actual

El proyecto incluye Cucumber.js como dependencia de desarrollo y los archivos de especificación. Todavía no contiene definiciones de pasos ni una implementación de la aplicación del restaurante.

Para obtener los pasos pendientes sin ejecutar lógica de negocio:

```sh
npx cucumber-js EJ01/*.feature --dry-run
```

Los pasos aparecerán como no definidos hasta que se implementen. El script `npm test` sigue siendo el marcador inicial de npm y devuelve un error; aún no hay una suite automatizada configurada.
