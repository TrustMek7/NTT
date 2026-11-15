package org.example;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Estudiante
{
    private String nombre = "";
    private int edad = 0;
    private String nivel = "";
    private int grado = 0;

    public void registrarEstudiante(Scanner scan)
    {
        System.out.print("Ingrese el nombre: ");
        String nombre = scan.nextLine().trim();
        while (nombre.isEmpty() || nombre.length() > 100) {
            System.out.print("Ingrese un nombre de 1 a 100 caracteres: ");
            nombre = scan.nextLine().trim();
        }

        int edad = 0;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMMuuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        while (edad < 6 || edad > 16) {
            try {
                System.out.print("Ingrese la fecha de nacimiento (ddmmaaaa o dd/mm/aaaa): ");
                String fecha = scan.nextLine().trim();
                if (!fecha.matches("[0-9]{8}|[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
                    System.out.println("Ingrese una fecha con el formato indicado.");
                    continue;
                }
                fecha = fecha.replace("/", "");
                LocalDate nacimiento = LocalDate.parse(fecha, formato);
                LocalDate hoy = LocalDate.now();
                if (nacimiento.isAfter(hoy)) {
                    System.out.println("La fecha de nacimiento no puede ser futura.");
                    continue;
                }
                edad = Period.between(nacimiento, hoy).getYears();
                if (edad < 6 || edad > 16)
                    System.out.println("La edad calculada es " + edad + ". Para esta práctica se admiten edades de 6 a 16 años.");
            }
            catch (DateTimeParseException e) {
                System.out.println("La fecha ingresada no existe. Revise el día, mes y año.");
            }
        }

        System.out.println("Edad calculada a la fecha actual: " + edad + " años.");
        String nivel = seleccionarNivel(scan);
        int grado = seleccionarGrado(scan, nivel);
        int edadEsperada = grado + 5;
        if (nivel.equals("Secundaria"))
            edadEsperada = grado + 11;

        // Valida la selección con la regla de edades de esta práctica.
        while (edad != edadEsperada) {
            System.out.println(nombreGrado(grado, nivel) + " corresponde a " +
                    edadEsperada + " años según esta práctica. El estudiante tiene " + edad + " años.");
            System.out.println("Seleccione nuevamente el nivel y grado.");
            nivel = seleccionarNivel(scan);
            grado = seleccionarGrado(scan, nivel);
            edadEsperada = grado + 5;
            if (nivel.equals("Secundaria"))
                edadEsperada = grado + 11;
        }

        setNombre(nombre);
        setEdad(edad);
        setNivel(nivel);
        setGrado(grado);
    }

    public void listaEstudiante()
    {
        System.out.println("----------------------------------------");
        System.out.println("Nombre : " + getNombre());
        System.out.println("Edad   : " + getEdad());
        System.out.println("Nivel  : " + getNivel());
        System.out.println("Grado  : " + nombreGrado(getGrado(), getNivel()));
        System.out.println("----------------------------------------");
    }

    public void setNombre(String p_nombre) { nombre = p_nombre; }
    public void setEdad(int p_edad) { edad = p_edad; }
    public void setGrado(int p_grado) { grado = p_grado; }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public int getGrado() { return grado; }
    public void setNivel(String p_nivel) { nivel = p_nivel; }
    public String getNivel() { return nivel; }

    public static String seleccionarNivel(Scanner scan)
    {
        System.out.println("----------------------------------------");
        System.out.println("1. Primaria");
        System.out.println("2. Secundaria");
        int opcion = leerOpcion(scan, 2);
        if (opcion == 1)
            return "Primaria";
        return "Secundaria";
    }

    public static String nombreGrado(int grado, String nivel)
    {
        String nombre;
        switch (grado) {
            case 1: nombre = "Primer"; break;
            case 2: nombre = "Segundo"; break;
            case 3: nombre = "Tercer"; break;
            case 4: nombre = "Cuarto"; break;
            case 5: nombre = "Quinto"; break;
            case 6: nombre = "Sexto"; break;
            default: return "Grado no válido";
        }
        return nombre + " grado de " + nivel.toLowerCase();
    }

    public static int seleccionarGrado(Scanner scan, String nivel)
    {
        int maximo = 6;
        if (nivel.equals("Secundaria"))
            maximo = 5;
        System.out.println("----------------------------------------");
        System.out.println("GRADOS DE " + nivel.toUpperCase());
        for (int i = 1; i <= maximo; i++)
            System.out.println(i + ". " + nombreGrado(i, nivel));
        return leerOpcion(scan, maximo);
    }

    private static int leerOpcion(Scanner scan, int maximo)
    {
        while (true) {
            System.out.print("Seleccione una opción del 1 al " + maximo + ": ");
            try {
                int opcion = Integer.parseInt(scan.nextLine().trim());
                if (opcion >= 1 && opcion <= maximo)
                    return opcion;
                System.out.println("Opción fuera del rango permitido.");
            }
            catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }
}
