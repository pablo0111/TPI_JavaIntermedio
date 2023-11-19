package Ticketera.Entidades;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Empleado  {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Usuario usuario;

    private String legajo;
    private String nombre;
    private String mail;
    private String telefono;
    private TIPOCONTACTO tipoPreferido;
    private Roles rol;

    public Roles getRol() {
        return rol;
    }

    protected void setRol(Roles rol) {
        this.rol = rol;
    }

}
