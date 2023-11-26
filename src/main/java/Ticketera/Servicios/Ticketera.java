package Ticketera.Servicios;

import Ticketera.Entidades.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Ticketera {

    public static void ejecutar(Empleado emp){
        System.out.println("Opciones disponibles: ");
        switch (emp.getRol()) {
            case RRHH:{
                System.out.println("1. Dar de alta un técnico");
                System.out.println("2. Dar de baja un técnico");
                System.out.println("3. Agregar o Quitar Especialidad a un técnico");
                System.out.println("4. Reporte de tickets del día");
                System.out.println("5. Técnico con más tickets en N días");
                System.out.println("6. Técnico con más tickets en N días para una especialidad");
                System.out.println("7. Técnico que resolvió el incidente más rápido");
                char decision = MiScanner.leerCaracter();
                switch (decision) {
                    case '1': {
                        Tecnico tecnicoNuevo = new Tecnico();
                        System.out.println("Ingresar Nombre");
                        tecnicoNuevo.setNombre(MiScanner.leerTexto());
                        System.out.println("Ingresar Mail");
                        tecnicoNuevo.setMail(MiScanner.leerTexto());
                        System.out.println("Ingresar Teléfono");
                        tecnicoNuevo.setTelefono(MiScanner.leerTexto());
                        System.out.println("Ingresar Método de contacto preferido");
                        System.out.println("1. Whatsapp ");
                        System.out.println("2. Email");
                        System.out.println("3. Teléfono");
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
                                ServicioEspacialidad.obtenerServiciosActivos().stream().forEach((dato) -> System.out.println("Selección: " + dato.getIndex() + " Servicio: " + dato.getNombre()));
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
                        System.out.println("Ingresar el legajo del técnico a dar de baja: ");
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
                        System.out.println("Ingresar el legajo del técnico a editar: ");
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
                            ServicioEspacialidad.obtenerServiciosActivosTecnico(emp2.getLegajo()).stream().forEach((dato) -> System.out.println("Selección: " + dato.getIndex() + " Servicio: " + dato.getNombre()));
                            System.out.println();
                            boolean continuar = true;
                            while (continuar) {
                                System.out.println("Especialidades disponibles");
                                ServicioEspacialidad.obtenerServiciosActivos().stream().filter((dato)-> !emp2.tieneEspecialidad(dato)).forEach((dato)-> System.out.println("Selección: " + dato.getIndex() + " Servicio: " + dato.getNombre()));

                                int seleccion =  MiScanner.leerInt();
                                emp2.agregarEspecilidad(new ServicioEspacialidad(ServicioEspacialidad.obtenerServiciosActivos().stream().filter((dato)-> dato.getIndex()==seleccion).findFirst().get()));
                                System.out.println("¿Desea agregar otra especialidad?  S-Si N-No");
                                char sino2 = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                                if (sino2 == 'n' || sino2 == 'N' ) continuar = false;
                            }
                            emp2.actualizar();
                        }
                        System.out.println("Edición completada");
                        System.out.println("Desea eliminar especialidades al técnico: S-Si N-No");
                        sino = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                        if (sino == 'S' || sino=='s') {
                            //listo todas las especialidades
                            System.out.println("Especialidades actuales");
                            ServicioEspacialidad.obtenerServiciosActivosTecnico(emp2.getLegajo()).stream().forEach((dato) -> System.out.println("Selección: " + dato.getIndex() + " Servicio: " + dato.getNombre()));
                            System.out.println();
                            boolean continuar = true;
                            while (continuar) {
                                System.out.println("Elegir especialidad a eliminar: ");
                                int seleccion =  MiScanner.leerInt();
                                emp2.elimnarEspecilidad(new ServicioEspacialidad(ServicioEspacialidad.obtenerServiciosActivos().stream().filter((dato)-> dato.getIndex()==seleccion).findFirst().get()));
                                System.out.println("¿Desea eliminar otra especialidad?  S-Si N-No");
                                char sino2 = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                                if (sino2 == 'n' || sino2 == 'N' ) continuar = false;
                            }
                            emp2.actualizar();
                            System.out.println("Edición completada");
                        }

                        break;
                    }
                    case '4': {
                        try {
                           Reportes.incidentesAsignadosPorTecnico().stream().forEach((linea)-> System.out.println(linea));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                    case '5':
                        System.out.println("Ingresar la cantidad N de días: ");
                        int seleccion = MiScanner.leerInt();
                        System.out.println("Resultado de 'Técnico con más tickets en "+seleccion+" dias'");
                        try {
                            System.out.println(Reportes.tecnicoMasIncidentesResueltos(seleccion));;
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case '6':
                        System.out.println("Ingresar la cantidad N de días: ");
                        seleccion = MiScanner.leerInt();
                        System.out.println("Elegir servicio: ");
                        ServicioEspacialidad.obtenerServiciosActivos().stream().forEach((dato)-> System.out.println("Selección: " + dato.getIndex() + " Servicio: " + dato.getNombre()));
                        int seleccion1=MiScanner.leerInt();
                        System.out.println("Resultado de 'Técnico con más tickets en "+seleccion+" dias para el servicio "+ServicioEspacialidad.obtenerServiciosActivos().stream().filter((caso)->caso.getIndex()==seleccion1).findFirst().get().getNombre()+"'");
                        try {
                            System.out.println(Reportes.tecnicoMasIncidentesResueltosEspecialidad(seleccion,seleccion1));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case '7':
                        System.out.println("El incidente que se resolvió más rápido fue:");
                        try {
                            System.out.println(Reportes.tecnicoQueResuelveMasRapidoIncidentes());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                }


                break;
            }
            case OPERADORMDA:{
                Cliente caller = new Cliente();
                Incidente nIncidente = new Incidente();
                boolean seguir= true;
                System.out.println("Para poder crear un incidente debe ingresar el cliente, debajo las opciones de búsqueda");
                while (seguir) {
                    do {
                        System.out.println("1. Obtener cliente por CUIT (Solo números, sin -)");
                        System.out.println("2. Obtener cliente por Razón Social");
                        int opcion = MiScanner.leerInt();
                        if (opcion == 1) {
                            System.out.println("--Selección: CUIT");
                            caller.setCUIT(MiScanner.leerTexto());
                        }
                        else {
                            System.out.println("--Selección: Razón Social");
                            caller.setRazonSocial(MiScanner.leerTexto());
                        }
                    } while (!caller.recuperar());
                    nIncidente.setCUIT(caller.getCUIT());
                    System.out.println("Buscando Servicios activos....\n");
                    final int[] contador = {0};
                    ServicioEspacialidad.obtenerServiciosActivosCliente(caller.getCUIT()).stream().forEach((dato) -> {
                        System.out.println("Selección: " + dato.getIndex() + " Servicio: " + dato.getNombre());
                        contador[0]++;
                    });
                    if (contador[0] ==0) System.out.println("Cliente sin servicios activos");
                    else {
                        nIncidente.setIdServicio(  MiScanner.leerInt());
                        System.out.println("Ingrese la descripción del problema");
                        nIncidente.setDescripcion(MiScanner.leerTexto());
                        System.out.println("Ingrese tipo de problema");
                        List<TipoProblemas> listadoProblemas;
                        try {
                            listadoProblemas=TipoProblemas.obtenerTipoProblemas();
                            listadoProblemas.stream().forEach((problema)-> System.out.println("Selección: "+problema.getId()+ ". Descripción: " + problema.toString()));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        int seleccionProblema = MiScanner.leerInt();
                        nIncidente.setProblema(listadoProblemas.stream().filter((dato)-> dato.getId()==seleccionProblema).findFirst().get());
                        System.out.println("¿Desea ingresar horas adicionales por la complejidad del problema?  S-Si N-No");

                        char sino = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                        if (sino == 'S' || sino=='s') {
                            //agregar Servicios-Especialidades
                            nIncidente.setHsComplejidad(MiScanner.leerInt());
                        }
                        Tecnico nTecnico= new Tecnico();
                        int[] referenciaTecnicos = nTecnico.listarTecnicosServicioEspecialidad(nIncidente.getIdServicio());
                        System.out.println("Seleccione el técnico");
                        nTecnico.setLegajo(String.valueOf(MiScanner.leerInt()));
                        nTecnico.recuperar();
                        System.out.println("Técnico seleccionado: " +nTecnico.toString());
                        nIncidente.setLegajoTecnico(nTecnico.getLegajo());
                        System.out.println("Fecha y hora actual: "+ LocalDateTime.now());
                        System.out.println("Horas requeridas por tabla: "+nIncidente.getProblema().getETR());
                        System.out.println("Horas agregadas en llamado: "+ nIncidente.getHsComplejidad());
                        nIncidente.tiempoEstimadoResolucion();
                        int numeroIncidente= nIncidente.crearIncidente();
                        if (numeroIncidente!=0) System.out.println("Incidente creado con éxito, numero de ticket: > "+ numeroIncidente+ " <\n");
                    }
                    System.out.println("¿Desea cargar asignar un ticket padre?  S-Si N-No");
                    char sino = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                    if (sino == 'S' || sino=='s') {
                        int incpadre= 0;
                        boolean bucle = true;
                        while (bucle)
                         incpadre=MiScanner.leerInt();
                        if (incpadre <nIncidente.getNumeroINC()) {
                            bucle=false;
                            nIncidente.setParentINC(incpadre);
                            nIncidente.actualizar();
                        }
                    }

                    System.out.println("¿Desea cargar un nuevo ticket?  S-Si N-No");
                    sino = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                    if (sino == 'N' || sino=='n') seguir=false;
                }


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
                        System.out.println("Ingresar CUIT (Solo números, sin -)");
                        clienteNuevo.setCUIT(MiScanner.leerTexto());
                        System.out.println("Ingresar Razón Social");
                        clienteNuevo.setRazonSocial(MiScanner.leerTexto());
                        System.out.println("Ingresar eMail de contacto: ");
                        clienteNuevo.setMailContacto(MiScanner.leerTexto());
                        clienteNuevo.persistir();
                        System.out.println("Desea agregar servicios al cliente: S-Si N-No");
                        char sino = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                        if (sino == 'S' || sino=='s') {
                            //agregar Servicios-Especialidades
                        }
                        //PERSISTIR CLIENTE
                        System.out.println("Proceso de alta OK");
                        break;
                    }
                    case '2': {
                        Cliente clienteNuevo = new Cliente();
                        System.out.println("Ingresar CUIT (Solo números, sin -)");
                        clienteNuevo.setCUIT(MiScanner.leerTexto());
                        clienteNuevo.recuperar();
                        System.out.println(clienteNuevo.toString());
                        System.out.println("¿Confirma dar de baja el cliente?  S-Si N-No");
                        char sino = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                        if (sino == 'S' || sino=='s') {
                            clienteNuevo.darDeBaja();
                        }
                        System.out.println("Proceso de baja OK");
                        break;
                    }
                    case '3': {
                        Cliente clienteNuevo = new Cliente();
                        System.out.println("Ingresar CUIT (Solo números, sin -)");
                        clienteNuevo.setCUIT(MiScanner.leerTexto());
                        clienteNuevo.recuperar();
                        System.out.println(clienteNuevo.toString());
                        System.out.println("¿Desea cambiar el mail?  S-Si N-No");
                        char sino = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                        if (sino == 'S' || sino=='s') {
                            clienteNuevo.setMailContacto(MiScanner.leerTexto());
                        }
                        System.out.println("¿Desea cambiar la Razón Social?  S-Si N-No");
                        sino = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                        if (sino == 'S' || sino=='s') {
                            clienteNuevo.setRazonSocial(MiScanner.leerTexto());
                        }
                        System.out.println("¿Confirma los cambios?  S-Si N-No");
                        System.out.println(clienteNuevo.toString());
                        sino = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                        if (sino == 'S' || sino=='s') {
                            clienteNuevo.actualizar();
                            System.out.println("Proceso de modificaciones completado");
                        } else System.out.println("Proceso de modificaciones CANCELADO");

                        break;
                    }
                    case '4': {
                        Cliente cliente = new Cliente();
                        System.out.println("Ingresar CUIT (Solo números, sin -)");
                        cliente.setCUIT(MiScanner.leerTexto());
                        cliente.recuperar();
                        System.out.println(cliente.toString() +"\n");
                        System.out.println("Desea agregar servicios al cliente: S-Si N-No");
                        char sino = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                        if (sino == 'S' || sino=='s') {
                            //listo todas las especialidades
                            System.out.println("Servicios actuales");
                            ServicioEspacialidad.obtenerServiciosActivosCliente(cliente.getCUIT()).stream().forEach((dato) -> System.out.println("Selección: " + dato.getIndex() + " Servicio: " + dato.getNombre()));
                            System.out.println();
                            boolean continuar = true;
                            while (continuar) {
                                System.out.println("Servicios disponibles");
                                ServicioEspacialidad.obtenerServiciosActivos().stream().filter((dato)-> !cliente.tieneServicio(dato)).forEach((dato)-> System.out.println("Selección: " + dato.getIndex() + " Servicio: " + dato.getNombre()));

                                int seleccion =  MiScanner.leerInt();
                                cliente.agregarServicio(new ServicioEspacialidad(ServicioEspacialidad.obtenerServiciosActivos().stream().filter((dato)-> dato.getIndex()==seleccion).findFirst().get()));
                                System.out.println("¿Desea agregar otro servicios?  S-Si N-No");
                                char sino2 = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                                if (sino2 == 'n' || sino2 == 'N' ) continuar = false;
                            }
                            cliente.actualizar();
                            System.out.println("Edición completada");
                        }
                        System.out.println("Desea eliminar servicios al cliente: S-Si N-No");
                        sino = MiScanner.leerCaracter(new char[]{'S','s', 'N','n'});
                        if (sino == 'S' || sino=='s') {
                            //listo todas las especialidades
                            System.out.println("Servicios actuales");
                            ServicioEspacialidad.obtenerServiciosActivosCliente(cliente.getCUIT()).stream().forEach((dato) -> System.out.println("Selección: " + dato.getIndex() + " Servicio: " + dato.getNombre()));
                            System.out.println();
                            boolean continuar = true;
                            while (continuar) {
                                System.out.println("Elegir servicio a eliminar: ");
                                int seleccion = MiScanner.leerInt();
                                cliente.elimnarServicio(new ServicioEspacialidad(ServicioEspacialidad.obtenerServiciosActivos().stream().filter((dato) -> dato.getIndex() == seleccion).findFirst().get()));
                                System.out.println("¿Desea eliminar otra especialidad?  S-Si N-No");
                                char sino2 = MiScanner.leerCaracter(new char[]{'S', 's', 'N', 'n'});
                                if (sino2 == 'n' || sino2 == 'N') continuar = false;
                            }
                            cliente.actualizar();
                            System.out.println("Edición completada");
                        }
                        break;
                    }
                    case '5': {
                        ServicioEspacialidad unServicio = new ServicioEspacialidad();
                        System.out.println("Ingresar nombre del servicio: ");
                        unServicio.setNombre(MiScanner.leerTexto());
                        unServicio.persistir();
                        System.out.println("Servicio Agregado con éxito");
                        break;
                    }
                }
                break;
            }
            case TECNICO: {
                Tecnico nTecnico= new Tecnico();
                nTecnico.setLegajo(emp.getLegajo());
                nTecnico.recuperar();
                List<String> resultado= nTecnico.cargarIncidentesAbiertos();
                System.out.println("Trabajo pendiente:  ");
                resultado.stream().forEach((linea)-> System.out.println(linea));
                System.out.println();
                System.out.println("¿Que desea realizar? ");
                System.out.println("1. Resolver un ticket");
                System.out.println("2. Cambiar ETR");
                System.out.println("3. Modificar método de contacto preferido");

                int eleccion = MiScanner.leerInt();
                switch (eleccion){
                    case 1:
                        System.out.println("¿Que ticket desea resolver? ");
                        resultado.stream().forEach((linea)-> System.out.println(linea));
                        int op = MiScanner.leerInt();
                        if (nTecnico.getIncidentesAbiertos().stream().filter((caso)->caso.getNumeroINC()==op).count()==1) {
                            System.out.println("Indicar comentario de cierre: ");
                            nTecnico.getIncidentesAbiertos().stream().filter((caso)->caso.getNumeroINC()==op).findFirst().get().setInformacionCierre(MiScanner.leerTexto());
                            nTecnico.getIncidentesAbiertos().stream().filter((caso)->caso.getNumeroINC()==op).findFirst().get().setEstado(EstadoIncidente.RESUELTO);
                            nTecnico.cerrarIncidente(op);
                            System.out.println("Se cerro el ticket "+ op + " con éxito");
                        } else System.out.println("Dato ingresado incorrecto, no se encontró un ticket ");

                        break;
                    case 2:
                        System.out.println("¿Que ticket desea modificar? ");
                        resultado.stream().forEach((linea)-> System.out.println(linea));
                        op = MiScanner.leerInt();
                        if (nTecnico.getIncidentesAbiertos().stream().filter((caso)->caso.getNumeroINC()==op).count()==1) {
                            System.out.println("Datos actuales: ");
                            System.out.println("Hs adicionales por complejidad: "+ nTecnico.getIncidentesAbiertos().stream().filter((caso)->caso.getNumeroINC()==op).findFirst().get().getHsComplejidad());
                            System.out.println("Ingrese un valor de horas, debe ser menor que el actual");
                            int hsadic_mod = MiScanner.leerInt();
                            if (hsadic_mod < nTecnico.getIncidentesAbiertos().stream().filter((caso)->caso.getNumeroINC()==op).findFirst().get().getHsComplejidad()) {
                                nTecnico.getIncidentesAbiertos().stream().filter((caso)->caso.getNumeroINC()==op).findFirst().get().setHsComplejidad(hsadic_mod);
                                nTecnico.actualizarIncidente(op);
                                System.out.println("Se modifico el ticket con éxito");
                            }
                            else System.out.println("La cantidad de horas ingresadas es igual o mayor que el valor actual. No se realizan cambios");

                        } else System.out.println("Dato ingresado incorrecto, no se encontró un ticket ");


                        break;
                    case 3:
                        System.out.println("Su método de contacto actual es:");
                        System.out.println(nTecnico.getTipoPreferido().toString());
                        System.out.println("Seleccione su nuevo método de envío:");
                        TipoContacto contactos[] = TipoContacto.values();
                        final int[] i = {1};
                        Arrays.stream(contactos).forEach((item)-> {
                            System.out.println("Opción "+ i[0] + " - Tipo de contacto: "+item);
                            i[0]++;
                        });
                        op=MiScanner.leerCaracter(new char[]{'1','2','3'})-'0';
                        nTecnico.setTipoPreferido(contactos[op-1]);
                        nTecnico.actualizar();
                        System.out.println("Cambio realizado correctamente");
                        System.out.println("Nuevo contacto preferido");
                        System.out.println(nTecnico.getTipoPreferido().toString());
                        break;

                }



                break;
            }

            case ADMIN:{
                System.out.println("Sistema auto-gestionado ---");
                break;
            }

        }



    }
}
