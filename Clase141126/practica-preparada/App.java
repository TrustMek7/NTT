package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n----------------------------------------");
            System.out.println("         GESTIÓN DE ESTUDIANTES");
            System.out.println("----------------------------------------");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Buscar estudiante por nombre");
            System.out.println("4. Ordenar estudiantes por edad");
            System.out.println("5. Salir");
            System.out.println("----------------------------------------");
            System.out.print("Ingrese su opción: ");

            try {
                opcion = Integer.parseInt(scan.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1: {
                    System.out.println("\n---------- REGISTRAR ESTUDIANTE ---------");
                    Estudiante estudiante = new Estudiante();
                    estudiante.registrarEstudiante(scan);
                    estudiantes.add(estudiante);
                    break;
                }
                case 2:
                    System.out.println("\n---------- LISTA DE ESTUDIANTES ---------");
                    if (estudiantes.isEmpty())
                        System.out.println("No hay estudiantes registrados.");

                    for (int i = 0; i < estudiantes.size(); i++) {
                        System.out.println("ESTUDIANTE " + (i + 1));
                        estudiantes.get(i).listaEstudiante();
                    }
                    break;
                case 3: {
                    System.out.println("\n----------- BUSCAR ESTUDIANTE -----------");
                    System.out.print("Ingrese el nombre a buscar: ");
                    String nombre = scan.nextLine().trim();
                    boolean encontrado = false;

                    for (int i = 0; i < estudiantes.size(); i++) {
                        if (estudiantes.get(i).getNombre().equalsIgnoreCase(nombre)) {
                            estudiantes.get(i).listaEstudiante();
                            encontrado = true;
                        }
                    }
                    if (!encontrado)
                        System.out.println("No se encontraron estudiantes con ese nombre.");
                    break;
                }
                case 4:
                    if (estudiantes.isEmpty()) {
                        System.out.println("No hay estudiantes registrados.");
                        break;
                    }
                    ordenarPorEdad(estudiantes);
                    System.out.println("Estudiantes ordenados por edad de menor a mayor.");
                    System.out.println("Seleccione la opción 2 para ver la lista.");
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Ingrese una opción válida del 1 al 5.");
            }
        }
    }

    public static void ordenarPorEdad(ArrayList<Estudiante> estudiantes)
    {
        // Inserción: coloca cada estudiante en su lugar entre los anteriores.
        for (int i = 1; i < estudiantes.size(); i++) {
            Estudiante auxiliar = estudiantes.get(i);
            int j = i - 1;

            // Desplaza a la derecha los estudiantes de mayor edad.
            while (j >= 0 && estudiantes.get(j).getEdad() > auxiliar.getEdad()) {
                estudiantes.set(j + 1, estudiantes.get(j));
                j--;
            }

            estudiantes.set(j + 1, auxiliar);
        }
    }
}
