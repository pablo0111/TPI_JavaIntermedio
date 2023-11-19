package Ticketera.Entidades;

public class RRHH extends Empleado{


    public RRHH() {
        setRol(Roles.RRHH);
    }
    public RRHH(Empleado empleado) {
        setRol(Roles.RRHH);
        setNombre(empleado.getNombre());
        setMail(empleado.getMail());
        setTelefono(empleado.getTelefono());
        setTipoPreferido(empleado.getTipoPreferido());
    }

}
