# Practica1411 — Proyecto inicial Java

Proyecto individual de la Escuela de Testing NTT para practicar la estructura básica de una aplicación Java con Maven.

## Contenido

- `src/main/java/org/example/App.java`: punto de entrada que imprime `Hello World!`.
- `src/test/java/org/example/AppTest.java`: prueba inicial de JUnit.
- `pom.xml`: configuración Maven con JUnit 3.8.1 como dependencia de pruebas.

## Ejecución

Requisitos: JDK y Maven instalados y disponibles en la terminal.

Desde esta carpeta:

```sh
mvn -Dmaven.compiler.source=8 -Dmaven.compiler.target=8 package
java -cp target/classes org.example.App
```

## Pruebas

```sh
mvn -Dmaven.compiler.source=8 -Dmaven.compiler.target=8 test
```

El proyecto conserva la aplicación y la prueba de la plantilla inicial, como base para próximos ejercicios.
