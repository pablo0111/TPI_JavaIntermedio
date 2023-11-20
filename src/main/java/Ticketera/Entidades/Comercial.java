package Ticketera.Entidades;

public class Comercial extends Empleado{

    public Comercial() {
        setRol(Roles.COMERCIAL);
    }
    public Comercial(Empleado empleado) {
        setRol(Roles.COMERCIAL);
        setNombre(empleado.getNombre());
        setMail(empleado.getMail());
        setTelefono(empleado.getTelefono());
        setTipoPreferido(empleado.getTipoPreferido());
    }
}
