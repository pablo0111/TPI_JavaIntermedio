package Ticketera;

import Ticketera.Entidades.*;
import Ticketera.Servicios.MiScanner;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido a la Ticketera");

        String[] args2 = {"pedro","1234"}; //RRHH -2
//        String[] args2 = {"patricia","1234"}; //COMERCIAL - 5
//        String[] args2 = {"juan","1234"}; //TECNICO -3
//        String[] args2 = {"eva","1234"}; //OPERADORMDA -4
        Usuario user1 = new Usuario(args2[0], args2[1]);
        Empleado emp = new Empleado();

        try {
            if (user1.Login()) {
                emp.setUsuario(user1);
                emp.recuperar();
                System.out.println("fin");

            } else System.out.println("Usuario o Clave incorrecta");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


       // Empleado emp = new Tecnico();
//        emp.setNombre("Roberto");
//        emp.setMail("roberto@mail.com");
//        emp.setTipoPreferido(TipoContacto.EMAIL);
//        Usuario user1 = new Usuario();
//        user1.setNombreUsuario("roberto");
//        user1.setClave("1234");
//        emp.setUsuario(user1);
//        emp.persistir();

//        List<ServicioEspacialidad> resultadoList = new ArrayList<ServicioEspacialidad>();
//        resultadoList = ServicioEspacialidad.obtenerServiciosActivos();
//        resultadoList.stream().forEach((dato) -> System.out.println("Seleccion: "+dato.getIndex()+" Servicio: " + dato.getNombre() ) );
//        ServicioEspacialidad.obtenerServiciosActivos().stream().forEach((dato) -> System.out.println("Seleccion: "+dato.getIndex()+" Servicio: " + dato.getNombre() ) );
//        System.out.println("listo");
//        Cliente uncliente = new Cliente(); //PRUEBA BAJA OK
//        uncliente.setCUIT("12123123121");
//        uncliente.darDeBaja();

        //LOGUEO y generación del tipo de empleado


        System.out.println("Opciones disponibles: ");
        switch (emp.getRol()) {
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
                        tecnicoNuevo.setTelefono(MiScanner.leerTexto());
                        System.out.println("Ingresar Metodo de contacto preferido");
                        System.out.println("1. Whatsapp ");
                        System.out.println("2. Email");
                        System.out.println("3. Telefono");
                        int contactoPreferido = MiScanner.leerInt();
                        tecnicoNuevo.setTipoPreferido(TipoContacto.getPorIndice((contactoPreferido)));
                        System.out.println("Ingresar Usuario de Login");
                        String usuario = (MiScanner.leerTexto());
                        System.out.println("Ingresar Contraseña de Login");
                        String contrasena = (MiScanner.leerTexto());
                        tecnicoNuevo.setUsuario(new Usuario(usuario,contrasena));
                        System.out.println("Desea agregar especialidades al técnico: S-Si N-No");
                        char sino = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                        if (sino == 'S' || sino=='s') {
                            //listo todas las especialidades
                            boolean continuar = true;
                            while (continuar) {
                                ServicioEspacialidad.obtenerServiciosActivos().stream().forEach((dato) -> System.out.println("Seleccion: " + dato.getIndex() + " Servicio: " + dato.getNombre()));
                                int seleccion =  MiScanner.leerInt();
                                tecnicoNuevo.agregarEspecilidad(new ServicioEspacialidad(ServicioEspacialidad.obtenerServiciosActivos().stream().filter((dato)-> dato.getIndex()==seleccion).findFirst().get()));
                                System.out.println("¿Desea agregar otra especialidad?  S-Si N-No");
                                char sino2 = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                                if (sino2 == 'n' || sino2 == 'N' ) continuar = false;
                            }

                        }
                        tecnicoNuevo.persistir();
                        //PERSISTIR TECNICO
                        System.out.println("Proceso de alta OK");
                        break;
                    }
                    case '2': {
                        System.out.println("Ingresar el legajo del tecnico a dar de baja: ");
                        String respuesta = MiScanner.leerTexto();
                        Empleado emp2 = new Empleado();
                        emp2.setLegajo(respuesta);
                        emp2.recuperar();
                        System.out.println(emp2.toString());
                        System.out.println("¿Confirma la baja?  S-Si N-No");
                        char sino=MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                        if (sino=='S' || sino=='s') emp.bajaLogica(emp2);
                        System.out.println("Baja completada correctamente");
                        break;
                    }
                    case '3': {
                        System.out.println("Ingresar el legajo del tecnico a editar: ");
                        String respuesta = MiScanner.leerTexto();
                        Tecnico emp2 = new Tecnico();
                        emp2.setLegajo(respuesta);
                        emp2.recuperar();
                        System.out.println(emp2.toString());
                        System.out.println("Desea agregar especialidades al técnico: S-Si N-No");
                        char sino = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                        if (sino == 'S' || sino=='s') {
                            //listo todas las especialidades
                            System.out.println("Especialidades actuales");
                            ServicioEspacialidad.obtenerServiciosActivosTecnico(emp2.getLegajo()).stream().forEach((dato) -> System.out.println("Seleccion: " + dato.getIndex() + " Servicio: " + dato.getNombre()));
                            System.out.println();
                            boolean continuar = true;
                            while (continuar) {
                                System.out.println("Especialidades disponibles");
                                ServicioEspacialidad.obtenerServiciosActivos().stream().filter((dato)-> !emp2.tieneEspecialidad(dato)).forEach((dato)-> System.out.println("Seleccion: " + dato.getIndex() + " Servicio: " + dato.getNombre()));

                                int seleccion =  MiScanner.leerInt();
                                emp2.agregarEspecilidad(new ServicioEspacialidad(ServicioEspacialidad.obtenerServiciosActivos().stream().filter((dato)-> dato.getIndex()==seleccion).findFirst().get()));
                                System.out.println("¿Desea agregar otra especialidad?  S-Si N-No");
                                char sino2 = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                                if (sino2 == 'n' || sino2 == 'N' ) continuar = false;
                            }
                            emp2.actualizar();
                        }
                        System.out.println("Edicion completada");
                        System.out.println("Desea eliminar especialidades al técnico: S-Si N-No");
                        sino = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                        if (sino == 'S' || sino=='s') {
                            //listo todas las especialidades
                            System.out.println("Especialidades actuales");
                            ServicioEspacialidad.obtenerServiciosActivosTecnico(emp2.getLegajo()).stream().forEach((dato) -> System.out.println("Seleccion: " + dato.getIndex() + " Servicio: " + dato.getNombre()));
                            System.out.println();
                            boolean continuar = true;
                            while (continuar) {
                                System.out.println("Elejir especilidad a elminar: ");
                                int seleccion =  MiScanner.leerInt();
                                emp2.elimnarEspecilidad(new ServicioEspacialidad(ServicioEspacialidad.obtenerServiciosActivos().stream().filter((dato)-> dato.getIndex()==seleccion).findFirst().get()));
                                System.out.println("¿Desea eliminar otra especialidad?  S-Si N-No");
                                char sino2 = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                                if (sino2 == 'n' || sino2 == 'N' ) continuar = false;
                            }
                            emp2.actualizar();
                            System.out.println("Edicion completada");
                        }

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