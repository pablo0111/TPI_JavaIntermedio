package Ticketera.Entidades;

import Ticketera.Servicios.UsuarioDBServicios;

import lombok.Data;


@Data
public class Usuario {
    private String nombreUsuario;
    private String clave;
    private boolean LoginStatus;

    public Usuario(String nombreUsuario, String clave) {
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        LoginStatus = false;
    }

    public boolean Login() throws Exception {
        UsuarioDBServicios accesoDB = new UsuarioDBServicios();
        //busca clave en la base para el usuario
        //comparo clave DB con clave ingresada
        if (clave.equals(accesoDB.obtenerClavedeUsuario(getNombreUsuario())))
        //Si da OK paso el estado login a true y devuelvo true
        setLoginStatus(true);
        else setLoginStatus(false);
        //Si no da OK devuelvo false

    return this.LoginStatus;
    }



}
