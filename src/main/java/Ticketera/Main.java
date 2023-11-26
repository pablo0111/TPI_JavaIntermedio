package Ticketera;

import Ticketera.Entidades.*;
import Ticketera.Servicios.MiScanner;
import Ticketera.Servicios.Ticketera;


public class Main {
//    private static EntityManager manager;
//    private static EntityManagerFactory emf;
    public static void main(String[] args) {
        System.out.println("Bienvenido a la Ticketera");

    if (args.length==0) {
        System.out.println("Modo administrador");
        System.out.println("Desea ser:");
        System.out.println("1. RRHH");
        System.out.println("2. Comercial");
        System.out.println("3. Tecnico");
        System.out.println("4. Operador MDA");
        int op = MiScanner.leerInt();
        switch (op){
            case 1:
                 String[] args2 = {"pedro","1234"}; //RRHH -2
                args = args2;
                break;
            case 2:
                String[] args3 = {"patricia","1234"}; //COMERCIAL - 5
                args = args3;
                break;
            case 3:
               String[] args4 = {"robertito","1234"}; //TECNICO -3
                args = args4;
                break;
            case 4:
                String[] args5 = {"eva","1234"}; //OPERADORMDA -4
                args = args5;
                break;

        }
    }

        Usuario user1 = new Usuario(args[0], args[1]);
        Empleado emp = new Empleado();

        try {

            //LOGUEO y generación del tipo de empleado
            if (user1.Login()) {
                emp.setUsuario(user1);
                emp.recuperar();
                String textoWelcome = "Bienvenido "+ emp.getNombre();
                String lineaSubrayada = new String(new char[textoWelcome.length()]).replace("\0", "-");
                System.out.println(textoWelcome);
                System.out.println(lineaSubrayada);
                Ticketera.ejecutar(emp);

            } else System.out.println("Usuario o Clave incorrecta");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}