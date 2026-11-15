package org.example;

import java.util.Scanner;

public class Estudiante
{
    private String nombre = "";
    private int edad = 0;
    private String grado = "";

    public void registrarEstudiante(Scanner scan)
    {
        System.out.print("Ingrese el nombre: ");
        String nombre = scan.nextLine().trim();
        while (nombre.isEmpty()) {
            System.out.print("Ingrese un nombre válido: ");
            nombre = scan.nextLine().trim();
        }

        int edad = 0;
        while (edad <= 0) {
            try {
                System.out.print("Ingrese la edad: ");
                edad = Integer.parseInt(scan.nextLine());
                if (edad <= 0)
                    System.out.println("La edad debe ser mayor que cero.");
            }
            catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }

        System.out.print("Ingrese el grado: ");
        String grado = scan.nextLine().trim();
        while (grado.isEmpty()) {
            System.out.print("Ingrese un grado válido: ");
            grado = scan.nextLine().trim();
        }

        setNombre(nombre);
        setEdad(edad);
        setGrado(grado);
        System.out.println("Estudiante registrado correctamente.");
    }

    public void listaEstudiante()
    {
        System.out.println("----------------------------------------");
        System.out.println("Nombre : " + getNombre());
        System.out.println("Edad   : " + getEdad());
        System.out.println("Grado  : " + getGrado());
        System.out.println("----------------------------------------");
    }

    public void setNombre(String p_nombre) { nombre = p_nombre; }
    public void setEdad(int p_edad) { edad = p_edad; }
    public void setGrado(String p_grado) { grado = p_grado; }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getGrado() { return grado; }
}
