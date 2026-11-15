package org.example;

import java.util.Scanner;

public class Persona {

    private String nombre = "";
    private double peso = 0;
    private double talla = 0;

    public void registrarPersona(Scanner scan)
    {
        System.out.println("Ingrese el nombre de la persona: ");
        String name = scan.nextLine();

        while (true) {
            try {
                System.out.println("Ingrese el peso en kilogramos: ");
                double peso = Double.parseDouble(scan.nextLine());
                System.out.println("Ingrese la talla en metros: ");
                double talla = Double.parseDouble(scan.nextLine());

                if (peso <= 0 || talla <= 0) {
                    System.out.println("El peso y la talla no pueden ser números negativos.");
                    continue;
                }

                setNombre(name);
                setPeso(peso);
                setTalla(talla);
                System.out.println("Persona registrada correctamente.");
                return;
            }
            catch (NumberFormatException e) {
                System.out.println("Ingrese números válidos para el peso y la talla. Use punto para los decimales.");
            }
        }
    }

    public void listaPersona()
    {
        System.out.println("----------------------------------------");
        System.out.println("Nombre    : " + getNombre());
        System.out.println("Peso      : " + getPeso() + " kg");
        System.out.println("Talla     : " + getTalla() + " m");
        System.out.println("IMC       : " + getIMC());
        System.out.println("Categoría : " + getCategoriaPeso());
        System.out.println("----------------------------------------");
    }

    public void setNombre(String p_nombre) { nombre = p_nombre; }
    public void setPeso(double p_peso) { peso = p_peso; }
    public void setTalla(double p_talla) { talla = p_talla; }

    public String getNombre() { return nombre; }
    public double getPeso() { return peso; }
    public double getTalla() { return talla; }
    public double getIMC() { return Math.round((peso / Math.pow(talla, 2)) * 100.0) / 100.0; }

    public String getCategoriaPeso()
    {
        double IMC = getIMC();

        if (IMC < 18.5)
            return "Bajo peso";
        else if (IMC < 25)
            return "Normal";
        else if (IMC < 30)
            return "Sobrepeso";
        else
            return "Obesidad";
    }
}
