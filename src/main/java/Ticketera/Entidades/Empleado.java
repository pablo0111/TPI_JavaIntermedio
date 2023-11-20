package Ticketera.Entidades;


import Ticketera.Servicios.EmpleadoDBServicios;
import lombok.Data;


@Data
public class Empleado  {

    private Usuario usuario;

    private String legajo;
    private String nombre;
    private String mail;
    private String telefono;
    private TipoContacto tipoPreferido;
    private Roles rol;

    public Roles getRol() {
        return rol;
    }

    protected void setRol(Roles rol) {
        this.rol = rol;
    }

    public void persistir(){
        EmpleadoDBServicios accesoDB = new EmpleadoDBServicios();
        try {
            accesoDB.persistirEmpleado(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
