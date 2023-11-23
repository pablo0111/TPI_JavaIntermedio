package Ticketera.Entidades;

import Ticketera.Servicios.IncidenteDBServicios;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;


@Data
@Entity
@Table (name="incidente")
public class Incidente {
    public Incidente() {
        estado=EstadoIncidente.ABIERTO;
    }

    @Id
    @Column (name ="idincidenteNumero")
    private long numeroINC;
    @Column (name = "idClienteCUIT")
    private String CUIT;
    @Column (name = "idTecnico")
    private String legajoTecnico;
    @Column (name = "idParent")
    private long parentINC;
    @Column (name= "idTipoProblema")
    private TipoProblemas problema;
    private int hsComplejidad;
    @Column (name= "fechaEstimadaResolucion")
    private LocalDateTime fechacreacion= LocalDateTime.now();
    @Column (name = "descripcionProblema")
    private String descripcion;
    @Column (name = "descripcionResolucion")
    private String informacionCierre;
    @Column (name = "idEstado")
    private EstadoIncidente estado;
    @Column (name = "idServicio")
    private int idServicio;

    public void tiempoEstimadoResolucion(){
        System.out.println("Fecha hora estimada de resolucion: " +this.fechacreacion.plusMinutes(getProblema().getETR()+getHsComplejidad()));
    }

    public int crearIncidente(){
        IncidenteDBServicios accesoDB = new IncidenteDBServicios();
        int resultado;
        try {
            resultado= accesoDB.persisirCrearIncidente(this);
            setNumeroINC(resultado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

}
