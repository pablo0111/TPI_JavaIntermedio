package Ticketera.Entidades;

import Ticketera.Servicios.ClienteDBServicios;
import lombok.Data;

@Data
public class Cliente {
    private String CUIT;
    private String razonSocial;
    private String mailContacto;

    public void persistir(){
        ClienteDBServicios accesoDB = new ClienteDBServicios();
        try {
            accesoDB.persistirCliente(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
