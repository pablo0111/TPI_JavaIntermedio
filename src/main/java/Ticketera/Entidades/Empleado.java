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
    private boolean estadoActividad;
    public Roles getRol() {
        return rol;
    }

    protected void setRol(Roles rol) {
        this.rol = rol;
    }

    public String persistir(){
        EmpleadoDBServicios accesoDB = new EmpleadoDBServicios();
        String legajo;
        try {
            accesoDB.persistirEmpleado(this);
            legajo=accesoDB.recuperarEmpleadoID(this.getUsuario().getNombreUsuario());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return legajo;
    }

    public void recuperar(){
        EmpleadoDBServicios accesoDB = new EmpleadoDBServicios();
        String datos= new String();
        int formaRecupero;
        if (getLegajo()==null) formaRecupero=0; //por usuario
        else formaRecupero=1; //por legajo
        try {
            if (formaRecupero==0) datos = accesoDB.recuperarEmpleado(this.usuario.getNombreUsuario(), formaRecupero);
            else datos = accesoDB.recuperarEmpleado(this.getLegajo(), formaRecupero);
            this.setLegajo(datos.split(",")[0]);
            this.setNombre(datos.split(",")[1]);
            this.setMail(datos.split(",")[2]);
            this.setTelefono(datos.split(",")[3]);
            this.setTipoPreferido(TipoContacto.getPorIndice(Integer.parseInt(datos.split(",")[4])));
            this.setRol(Roles.getPorIndice((Integer.parseInt(datos.split(",")[5]))));
            this.setEstadoActividad((Integer.parseInt(datos.split(",")[6]))!=0);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void actualizar (){
        EmpleadoDBServicios accesoDB = new EmpleadoDBServicios();
        try {
            accesoDB.actualizarEmpleado(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void bajaLogica(Empleado emp){
        if (getRol()==Roles.RRHH){
            EmpleadoDBServicios accesoDB = new EmpleadoDBServicios();
            try {
                accesoDB.bajaLogicaDB(emp.getLegajo());
                legajo=accesoDB.recuperarEmpleadoID(this.getUsuario().getNombreUsuario());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else System.out.println("No tiene permisos para realizar la operacion");
    }

    @Override
    public String toString(){
        return "Legajo: " + getLegajo() + " Nombre: " + getNombre();
    }
}
