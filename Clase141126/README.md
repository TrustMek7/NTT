# Clase141126 — Gestión de estudiantes

Proyecto individual de la Escuela de Testing NTT: aplicación Java de consola para practicar programación orientada a objetos y gestión de estudiantes.

## Funcionalidades

- Registrar y listar estudiantes.
- Buscar estudiantes por nombre.
- Ordenar por edad.
- Editar o eliminar registros.
- Filtrar por nivel y grado.

## Estructura

- `src/main/java/org/example/`: aplicación principal y clases `Persona`, `Estudiante` y `EstudianteServicio`.
- `src/test/java/org/example/`: prueba inicial de JUnit.
- `practica-preparada/`: material adicional de la práctica.
- `pom.xml`: configuración del proyecto Maven y dependencia de JUnit 3.8.1.

## Ejecución

Requisitos: JDK y Maven instalados y disponibles en la terminal.

Desde esta carpeta, compilar y ejecutar:

```sh
mvn -Dmaven.compiler.source=8 -Dmaven.compiler.target=8 package
java -cp target/classes org.example.App
```

El menú permite seleccionar las operaciones de gestión y salir con la opción `7`.

## Pruebas

```sh
mvn -Dmaven.compiler.source=8 -Dmaven.compiler.target=8 test
```

La prueba incluida corresponde a la plantilla inicial del proyecto; no cubre todavía las operaciones de gestión de estudiantes.
