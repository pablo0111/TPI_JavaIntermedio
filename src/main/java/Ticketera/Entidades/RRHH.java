package Ticketera.Entidades;

public class RRHH extends Empleado{


    public RRHH() {
        setRol(Roles.COMERCIAL);
    }
    public RRHH(Empleado empleado) {
        setRol(Roles.COMERCIAL);
        setNombre(empleado.getNombre());
        setMail(empleado.getMail());
        setTelefono(empleado.getTelefono());
        setTipoPreferido(empleado.getTipoPreferido());
    }

}
