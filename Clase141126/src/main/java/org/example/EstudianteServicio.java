package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;
import java.text.Normalizer;

public class EstudianteServicio
{
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public void registrarEstudiante(Scanner scan)
    {
        Estudiante estudiante = new Estudiante();
        estudiante.registrarEstudiante(scan);
        estudiantes.add(estudiante);
        System.out.println("Estudiante registrado correctamente.");
    }

    public void listarEstudiantes()
    {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        System.out.println("Total de estudiantes: " + estudiantes.size());
        for (int i = 0; i < estudiantes.size(); i++) {
            System.out.println("ESTUDIANTE " + (i + 1));
            estudiantes.get(i).listaEstudiante();
        }
    }

    public void buscarEstudiantes(String nombre)
    {
        nombre = normalizarNombre(nombre);
        if (nombre.isEmpty()) {
            System.out.println("Ingrese un nombre para buscar.");
            return;
        }
        int encontrados = 0;
        for (Estudiante estudiante : estudiantes) {
            if (normalizarNombre(estudiante.getNombre()).contains(nombre)) {
                estudiante.listaEstudiante();
                encontrados++;
            }
        }
        if (encontrados == 0)
            System.out.println("No se encontraron estudiantes con ese nombre.");
        else
            System.out.println("Coincidencias: " + encontrados);
    }

    private String normalizarNombre(String nombre)
    {
        nombre = Normalizer.normalize(nombre.trim(), Normalizer.Form.NFC);
        nombre = nombre.toLowerCase(Locale.ROOT);
        return nombre.replace('á', 'a').replace('é', 'e').replace('í', 'i')
                .replace('ó', 'o').replace('ú', 'u');
    }

    public void editarOEliminarEstudiante(Scanner scan)
    {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        System.out.println("\n---------- EDITAR O ELIMINAR ------------");
        listarEstudiantes();
        int numero = -1;
        while (numero < 0 || numero > estudiantes.size()) {
            try {
                System.out.print("Ingrese el número del estudiante (0 para volver): ");
                numero = Integer.parseInt(scan.nextLine().trim());
                if (numero < 0 || numero > estudiantes.size())
                    System.out.println("Seleccione un número de la lista.");
            }
            catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
        if (numero == 0)
            return;

        int opcion = -1;
        while (opcion != 0) {
            System.out.println("----------------------------------------");
            System.out.println("1. Editar nombre, edad y grado");
            System.out.println("2. Eliminar registro");
            System.out.println("0. Volver");
            System.out.println("----------------------------------------");
            System.out.print("Ingrese su opción: ");
            try {
                opcion = Integer.parseInt(scan.nextLine().trim());
            }
            catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    // Conserva el registro original hasta completar los nuevos datos.
                    Estudiante actualizado = new Estudiante();
                    actualizado.registrarEstudiante(scan);
                    estudiantes.set(numero - 1, actualizado);
                    System.out.println("Estudiante actualizado correctamente.");
                    return;
                case 2:
                    System.out.println("¿Desea eliminar a " + estudiantes.get(numero - 1).getNombre() + "? (Y/N)");
                    String respuesta = scan.nextLine().trim();
                    while (!respuesta.equalsIgnoreCase("y") && !respuesta.equalsIgnoreCase("n")) {
                        System.out.print("Ingrese Y para eliminar o N para cancelar: ");
                        respuesta = scan.nextLine().trim();
                    }
                    if (respuesta.equalsIgnoreCase("y")) {
                        estudiantes.remove(numero - 1);
                        System.out.println("Estudiante eliminado correctamente.");
                    }
                    else
                        System.out.println("Eliminación cancelada.");
                    return;
                case 0:
                    break;
                default:
                    System.out.println("Ingrese una opción válida del 0 al 2.");
            }
        }
    }

    public void filtrarPorGrado(Scanner scan)
    {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        String nivel = Estudiante.seleccionarNivel(scan);
        int grado = Estudiante.seleccionarGrado(scan, nivel);
        int encontrados = 0;
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNivel().equals(nivel) && estudiante.getGrado() == grado) {
                estudiante.listaEstudiante();
                encontrados++;
            }
        }
        if (encontrados == 0)
            System.out.println("No hay alumnos en " + Estudiante.nombreGrado(grado, nivel) + ". Intente con otro grado.");
        else
            System.out.println("Estudiantes en " + Estudiante.nombreGrado(grado, nivel) + ": " + encontrados);
    }

    public void ordenarPorEdad(Scanner scan)
    {
        if (estudiantes.size() < 2) {
            System.out.println("Se necesitan al menos dos estudiantes para ordenar.");
            return;
        }
        System.out.println("----------------------------------------");
        System.out.println("1. Ordenar de menor a mayor");
        System.out.println("2. Ordenar de mayor a menor");
        System.out.println("0. Volver");
        System.out.println("----------------------------------------");
        int opcion = -1;
        while (opcion < 0 || opcion > 2) {
            System.out.print("Ingrese su opción: ");
            try {
                opcion = Integer.parseInt(scan.nextLine().trim());
                if (opcion < 0 || opcion > 2)
                    System.out.println("Ingrese una opción válida del 0 al 2.");
            }
            catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
        if (opcion == 0)
            return;

        boolean ascendente = opcion == 1;

        for (int i = 1; i < estudiantes.size(); i++) {
            Estudiante auxiliar = estudiantes.get(i);
            int j = i - 1;

            while (j >= 0) {
                boolean desplazar;
                if (ascendente)
                    desplazar = estudiantes.get(j).getEdad() > auxiliar.getEdad();
                else
                    desplazar = estudiantes.get(j).getEdad() < auxiliar.getEdad();

                if (!desplazar)
                    break;

                estudiantes.set(j + 1, estudiantes.get(j));
                j--;
            }

            estudiantes.set(j + 1, auxiliar);
        }
        if (ascendente)
            System.out.println("Estudiantes ordenados por edad de menor a mayor.");
        else
            System.out.println("Estudiantes ordenados por edad de mayor a menor.");
        listarEstudiantes();
    }
}
