package Ticketera.Servicios;

import java.util.Scanner;

public class MiScanner
{
    public static char leerCaracter() {
        String decision;
        Scanner lectura = new Scanner(System.in);
        System.out.println("Por favor un valor: ");
        decision = lectura.nextLine();

        return decision.charAt(0);
    }

    public static char leerCaracter(char limitacion[]) {
        String decision;
        Scanner lectura = new Scanner(System.in);

        System.out.println("Por favor ingrese su seleccion: ");
        decision = lectura.nextLine();
        while (!comprobarOpcionCorrecta(limitacion, decision.charAt(0))){
            System.out.println("Opcion incorrecta, por favor volver a intentar: ");
            decision = lectura.nextLine();
        }
        return decision.charAt(0);
    }

    public static boolean comprobarOpcionCorrecta(char limitacion[],char lectura) {
        boolean encontrado=false;
        for (int i = 0; i < limitacion.length; i++) {
            if( limitacion[i]==lectura) {
                encontrado = true;
            }

        }
        return encontrado;
    }
    public static String leerTexto() {
        String decision;
        Scanner lectura = new Scanner(System.in);

        System.out.println("Por favor ingrese su texto: ");
        decision = lectura.nextLine();

        return decision;
    }

    public static int leerInt() {
        String decision;
        Scanner lectura = new Scanner(System.in);
        System.out.println("Por favor ingrese un valor numerico entero: ");
        decision = lectura.nextLine();

        return Integer.parseInt(decision);
    }



}
