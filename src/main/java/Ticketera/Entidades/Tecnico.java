package Ticketera.Entidades;

import Ticketera.Servicios.EmpleadoDBServicios;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static Ticketera.Entidades.ServicioEspacialidad.obtenerServiciosActivosTecnico;

@Data
public class Tecnico extends Empleado  {
    private List<ServicioEspacialidad> especialidades;
    public Tecnico() {
        setRol(Roles.TECNICO);
        especialidades = new ArrayList<ServicioEspacialidad>();
    }

    public void agregarEspecilidad(ServicioEspacialidad _serv) {
        especialidades.add(_serv);

    }

    public String persistir(){
        setLegajo(super.persistir()); //Guardo el empleado en nomina
        if (especialidades.size()!=0){
            especialidades.stream().forEach((dato)-> dato.persistirEspecialidadTecnico(this.getLegajo())); //guardo sus especialidades
        }
        return getLegajo();
    }

    public void recuperar(){
        super.recuperar();
        especialidades.addAll(obtenerServiciosActivosTecnico(getLegajo()));
    }

    public boolean tieneEspecialidad (ServicioEspacialidad _serv) {
        return especialidades.stream().filter((dato)-> dato.getIndex()==_serv.getIndex()).findFirst().isPresent();

    }

    public void actualizar(){
        super.actualizar();
        ServicioEspacialidad.actualizarServicioTecnico(getLegajo(),especialidades);
    }

    public void elimnarEspecilidad(ServicioEspacialidad _serv){
        especialidades.remove(_serv);
    }
    /**
     * Imprime los técnicos con su ID y su Nombre que tienen la especialidad que recibe como parámetro.
     *
     * @param especialidad recibe un int con el id de especialidad
     * @return Devuelve un array con los id de los técnicos listados
     *
     */

    public int[] listarTecnicosServicioEspecialidad (int especialidad) {
        List <String> listado = new ArrayList<>();
        int[] salida;

        listado = new ServicioEspacialidad().obtenerTecnicosConEspecialidad(especialidad);
        salida = new int[listado.size()];
        if (listado.size()!=0) {
            int index=0;
            for (String linea: listado) {
                System.out.println("Tenico Codigo: "+ linea.split(",")[0]+ " Nombre: " + linea.split(",")[1]);
                salida[index]=Integer.parseInt(linea.split(",")[0]);
                index++;
            }
        }
        return salida;
    }

    @Override
    public String toString(){
        return super.toString();
    }
}

