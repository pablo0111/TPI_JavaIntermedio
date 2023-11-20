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
    public ServicioEspacialidad( ServicioEspacialidad _serv){
        setNombre(_serv.getNombre());
        setIndex(_serv.getIndex());
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

    public static List <ServicioEspacialidad> obtenerServiciosActivosTecnico(String _legajo){
        ServicioEspecialidadDBServicios accesoDB = new ServicioEspecialidadDBServicios();
        List <ServicioEspacialidad> resultadoList = new ArrayList<ServicioEspacialidad>();
        try {
            List<String> lecturaDB = accesoDB.readAllLinesTecnicoEspecialidad(_legajo);
            if (lecturaDB.size()!=0)
            for (String linea : lecturaDB) {
                resultadoList.add(new ServicioEspacialidad (Integer.parseInt(linea.split(",")[1]),linea.split(",")[2])) ;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return resultadoList;
    }
    public static void eliminarServicioTecnico(String legajo, ServicioEspacialidad serv){
        ServicioEspecialidadDBServicios accesoDB = new ServicioEspecialidadDBServicios();
        try {
            accesoDB.eliminarEspecilidadTecnico(serv, legajo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void actualizarServicioTecnico(String legajo, List<ServicioEspacialidad> especialidades) {
        List<ServicioEspacialidad> listaEnDB = new ArrayList<>();
        listaEnDB.addAll(obtenerServiciosActivosTecnico(legajo));
        if (listaEnDB.size() == 0 && especialidades.size() != 0) {
            especialidades.stream().forEach((dato) -> dato.persistirEspecialidadTecnico(legajo)); //guardo sus especialidades
        } else if (listaEnDB.size() != 0 && especialidades.size() != 0) {
            //elimino los que sobran
            listaEnDB.stream().forEach((itemDB) ->
            {
                if (!especialidades.stream().anyMatch((itemAct) -> itemDB.getIndex() == itemAct.getIndex())) {
                    ServicioEspacialidad.eliminarServicioTecnico(legajo, itemDB);
                }
            });
            listaEnDB.clear();
            listaEnDB.addAll(obtenerServiciosActivosTecnico(legajo));
            //agrego los que falten
            especialidades.stream().forEach((item)->
            {
              if (!listaEnDB.stream().anyMatch((itemAct) -> item.getIndex() == itemAct.getIndex()))
                  item.persistirEspecialidadTecnico(legajo);
            }
            );
        } else if (listaEnDB.size() != 0 && especialidades.size()== 0)  listaEnDB.stream().forEach((item)-> ServicioEspacialidad.eliminarServicioTecnico(legajo,item));

    }

    protected void persistirEspecialidadTecnico(String legajo){
        ServicioEspecialidadDBServicios accesoDB = new ServicioEspecialidadDBServicios();
        try {
            accesoDB.agregarEspecilidadTecnico(this, legajo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}



