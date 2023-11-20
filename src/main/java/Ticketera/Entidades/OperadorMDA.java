package Ticketera.Entidades;

public class OperadorMDA extends  Empleado{
    public OperadorMDA() {
        setRol(Roles.OPERADORMDA);
    }
    public OperadorMDA(Empleado empleado) {
        setRol(Roles.OPERADORMDA);
        setNombre(empleado.getNombre());
        setMail(empleado.getMail());
        setTelefono(empleado.getTelefono());
        setTipoPreferido(empleado.getTipoPreferido());
    }
}
