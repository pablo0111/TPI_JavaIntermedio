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

    @Override
    public String toString(){
        return super.toString();
    }
}

