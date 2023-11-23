package Ticketera.Entidades;

import Ticketera.Servicios.ClienteDBServicios;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static Ticketera.Entidades.ServicioEspacialidad.obtenerServiciosActivosCliente;
import static Ticketera.Entidades.ServicioEspacialidad.obtenerServiciosActivosTecnico;

@Data
public class Cliente {
    private String CUIT;
    private String razonSocial;
    private String mailContacto;
    private List<ServicioEspacialidad> serviciosContratados;

    public Cliente() {
        this.serviciosContratados = new ArrayList<ServicioEspacialidad>();
    }

    public void persistir() {
        ClienteDBServicios accesoDB = new ClienteDBServicios();
        try {
            accesoDB.persistirCliente(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public boolean recuperar() {
        ClienteDBServicios accesoDB = new ClienteDBServicios();
        String datos = new String();

        int formaRecupero;
        if (getCUIT() != null) formaRecupero = 0; //por CUIT
        else formaRecupero = 1; //por RazonSocial
        try {
            if (formaRecupero == 0) datos = accesoDB.obtenerCliente(this.getCUIT(), formaRecupero);
            else datos = accesoDB.obtenerCliente(this.getRazonSocial(), formaRecupero);
            this.setCUIT(datos.split(",")[0]);
            this.setRazonSocial(datos.split(",")[1]);
            this.setMailContacto(datos.split(",")[2]);
            serviciosContratados.addAll(obtenerServiciosActivosCliente(getCUIT()));


        } catch (ClienteNoEncontradoException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }



    public void actualizar (){
        ClienteDBServicios accesoDB = new ClienteDBServicios();
        try {
            accesoDB.actualizarCliente(this);
            ServicioEspacialidad.actualizarServicioCliente(getCUIT(),serviciosContratados);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public boolean tieneServicio (ServicioEspacialidad _serv) {
        return serviciosContratados.stream().filter((dato)-> dato.getIndex()==_serv.getIndex()).findFirst().isPresent();

    }

    public void agregarServicio(ServicioEspacialidad _serv) {
        serviciosContratados.add(_serv);

    }
    public void elimnarServicio(ServicioEspacialidad _serv){
        serviciosContratados.remove(_serv);
    }

    public void darDeBaja(){
        ClienteDBServicios accesoDB = new ClienteDBServicios();
        try {
            accesoDB.bajaCliente(getCUIT());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString(){
        return "CUIT: " + this.getCUIT() + " Razon Social: " + this.getRazonSocial() + "\n Email de contacto: " + this.getMailContacto();
    }
}
