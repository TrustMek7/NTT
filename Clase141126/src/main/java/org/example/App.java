package org.example;

import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        EstudianteServicio servicio = new EstudianteServicio();
        int opcion = 0;

        while (opcion != 7) {
            System.out.println("\n----------------------------------------");
            System.out.println("         GESTIÓN DE ESTUDIANTES");
            System.out.println("----------------------------------------");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Buscar estudiante por nombre");
            System.out.println("4. Ordenar estudiantes por edad");
            System.out.println("5. Editar o eliminar estudiante");
            System.out.println("6. Filtrar por nivel y grado");
            System.out.println("7. Salir");
            System.out.println("----------------------------------------");
            System.out.print("Ingrese su opción: ");

            try {
                opcion = Integer.parseInt(scan.nextLine().trim());
            }
            catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n---------- REGISTRAR ESTUDIANTE ---------");
                    servicio.registrarEstudiante(scan);
                    break;
                case 2:
                    System.out.println("\n---------- LISTA DE ESTUDIANTES ---------");
                    servicio.listarEstudiantes();
                    break;
                case 3:
                    System.out.println("\n----------- BUSCAR ESTUDIANTE -----------");
                    System.out.print("Ingrese el nombre a buscar: ");
                    servicio.buscarEstudiantes(scan.nextLine());
                    break;
                case 4:
                    servicio.ordenarPorEdad(scan);
                    break;
                case 5:
                    servicio.editarOEliminarEstudiante(scan);
                    break;
                case 6:
                    servicio.filtrarPorGrado(scan);
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Ingrese una opción válida del 1 al 7.");
            }
        }
    }

}
