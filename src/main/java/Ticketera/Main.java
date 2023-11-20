package Ticketera;

import Ticketera.Entidades.*;
import Ticketera.Servicios.MiScanner;

import java.sql.SQLOutput;

import static Ticketera.Entidades.Roles.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Empleado emp = new Empleado();
        emp.setNombre("Juan");
        emp.setMail("juan@mail.com");
        emp.setTipoPreferido(TIPOCONTACTO.WHATSAPP);

        Usuario user1 = new Usuario();


        //LOGUEO y generación del tipo de empleado

        Empleado emp2 = new RRHH(emp);
        System.out.println("Opciones disponibles: ");
        switch (emp2.getRol()) {
            case RRHH:{
                System.out.println("1. Dar de alta un técnico");
                System.out.println("2. Dar de baja un técnico");
                System.out.println("3. Agregar o Quitar Especilidad a un técnico");
                System.out.println("4. Reporte de tickets del dia");
                char decision = MiScanner.leerCaracter();
                System.out.println(decision);
                switch (decision) {
                    case '1': {
                        Tecnico tecnicoNuevo = new Tecnico();
                        System.out.println("Ingresar Nombre");
                        tecnicoNuevo.setNombre(MiScanner.leerTexto());
                        System.out.println("Ingresar Mail");
                        tecnicoNuevo.setMail(MiScanner.leerTexto());
                        System.out.println("Ingresar Telefono");
                        tecnicoNuevo.setMail(MiScanner.leerTexto());
                        System.out.println("Ingresar Metodo de contacto preferido");
                        System.out.println("1. Whatsapp ");
                        System.out.println("2. Email");
                        System.out.println("3. Telefono");
                        int contactoPreferido = MiScanner.leerInt();
                        tecnicoNuevo.setTipoPreferido(TIPOCONTACTO.getPorIndice((contactoPreferido)));
                        //PERSISTIR TECNICO
                        System.out.println("Proceso de alta OK");
                        break;
                    }
                    case '2': {

                        break;
                    }
                    case '3': {

                        break;
                    }
                    case '4': {

                        break;
                    }
                }


                break;
            }
            case OPERADORMDA:{
                System.out.println("usted solo puede crear un incidente");
                System.out.println("Ingrese CUIT del cliente (Solo numeros, sin -)");





                break;
            }
            case COMERCIAL:{
                System.out.println("1. Dar de alta un cliente");
                System.out.println("2. Dar de baja un cliente");
                System.out.println("3. Modificar datos de un cliente");
                System.out.println("4. Modificar servicios de un cliente");
                System.out.println("5. Dar de alta un servicio-especialidad");
                char decision = MiScanner.leerCaracter();
                System.out.println(decision);
                switch (decision) {
                    case '1': {
                        Cliente clienteNuevo = new Cliente();
                        System.out.println("Ingresar CUIT (Solo numeros, sin -)");
                        clienteNuevo.setCUIT(MiScanner.leerTexto());
                        System.out.println("Ingresar Razon Social");
                        clienteNuevo.setRazonSocial(MiScanner.leerTexto());
                        System.out.println("Ingresar eMail de contacto: ");
                        clienteNuevo.setMailContacto(MiScanner.leerTexto());
                        clienteNuevo.persistir();
                        System.out.println("Desea agregar servicios al cliente: S-Si N-No");
                        char sino = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                        if (sino == 'S') {
                            //agregar Servicios-Especialidades
                        }
                        //PERSISTIR CLIENTE
                        System.out.println("Proceso de alta OK");
                        break;
                    }
                    case '2': {

                        break;
                    }
                    case '3': {

                        break;
                    }
                    case '4': {

                        break;
                    }
                }
                break;
            }
            case TECNICO: {
                System.out.println("1. Ver tickets asignados");
                System.out.println("2. Resolver un ticket");
                System.out.println("3. Cambiar ETR");
                System.out.println("4. Modificar metodo de contacto preferido");
                break;
            }

            case ADMIN:{
                System.out.println("Sistema auto-gestionado ---");
                break;
            }

        }



    }
}