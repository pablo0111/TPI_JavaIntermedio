package Ticketera.Entidades;

import Ticketera.Servicios.ServicioEspecialidadDBServicios;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ServicioEspacialidad {
    private String nombre;
    private int index;

    public ServicioEspacialidad( int index, String nombre){
        setNombre(nombre);
        setIndex(index);
    }
    public void persistir() {
        ServicioEspecialidadDBServicios accesoDB = new ServicioEspecialidadDBServicios();
        try {
            accesoDB.agregarServicioEspecialidad(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List <ServicioEspacialidad> obtenerServiciosActivos(){
        ServicioEspecialidadDBServicios accesoDB = new ServicioEspecialidadDBServicios();
        List <ServicioEspacialidad> resultadoList = new ArrayList<ServicioEspacialidad>();
        try {
            List<String> lecturaDB = accesoDB.readAllLines();
            for (String linea : lecturaDB) {
                resultadoList.add(new ServicioEspacialidad (Integer.parseInt(linea.split(",")[0]),linea.split(",")[1])) ;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    return resultadoList;
    }

    }



