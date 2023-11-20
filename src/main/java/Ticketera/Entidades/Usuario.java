package Ticketera.Entidades;

import lombok.AccessLevel;
import lombok.Data;


@Data
public class Usuario {
    private String nombreUsuario;
    private String clave;
    private boolean LoginStatus=false;
    public boolean Login(){
        //busca clave en la base para el usuario
        //comparo clave DB con clave ingresada
        //Si da OK paso el estado login a true y devuelvo true
        setLoginStatus(true);
        //Si no da OK devuelvo false
    return true;
    }
    protected String ObtenerClaveDB(){
        //pide a la DB una clave de un usuario
        String a = "Clave";
        return a;
    }
    public Roles ObtenerROL(){
        return Roles.RRHH;
    }

}
