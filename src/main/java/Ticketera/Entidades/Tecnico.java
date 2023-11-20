package Ticketera.Entidades;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Empleado  {
    private List<ServicioEspacialidad> especialidades;
    public Tecnico() {
        setRol(Roles.TECNICO);
        especialidades = new ArrayList<ServicioEspacialidad>();
    }

    public void agregarEspecilidad(ServicioEspacialidad _serv) {


    }

}
