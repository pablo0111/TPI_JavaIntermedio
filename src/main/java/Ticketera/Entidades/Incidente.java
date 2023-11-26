package Ticketera.Entidades;

import Ticketera.Servicios.IncidenteDBServicios;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table (name="incidente")
public class Incidente {
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

    public Incidente() {
        estado=EstadoIncidente.ABIERTO;
    }
    public Incidente(long numeroINC, String CUIT, String legajoTecnico, long parentINC, TipoProblemas problema, int hsComplejidad, LocalDateTime fechacreacion, String descripcion, String informacionCierre, EstadoIncidente estado, int idServicio) {
        this.numeroINC = numeroINC;
        this.CUIT = CUIT;
        this.legajoTecnico = legajoTecnico;
        this.parentINC = parentINC;
        this.problema = problema;
        this.hsComplejidad = hsComplejidad;
        this.fechacreacion = fechacreacion;
        this.descripcion = descripcion;
        this.informacionCierre = informacionCierre;
        this.estado = estado;
        this.idServicio = idServicio;
    }

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

    public static List<Incidente> cargarIncidentesTecnico(String legajo, EstadoIncidente estado){
        List<Incidente> resultado = new ArrayList<Incidente>();
        List<String> resultadoDB = new ArrayList<String>();
        IncidenteDBServicios accesoDB = new IncidenteDBServicios();
        try {
            resultadoDB= accesoDB.recuperarIncidentesPorTecnico(legajo, estado);
            if (resultadoDB.size()!=0) for (String dato: resultadoDB)
                                            {
                                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                                                TipoProblemas problema =new TipoProblemas(Integer.parseInt(dato.split("///")[4]));
                                                Incidente item = new Incidente(Long.valueOf(dato.split("///")[0]).longValue(),dato.split("///")[1],dato.split("///")[2],
                                                        Long.valueOf(dato.split("///")[3]).longValue(),problema,Integer.valueOf(dato.split("///")[5]).intValue(),
                                                        LocalDateTime.parse(dato.split("///")[6],formatter),dato.split("///")[7], dato.split("///")[8],
                                                        EstadoIncidente.getPorIndice(Integer.parseInt(dato.split("///")[9])), Integer.valueOf(dato.split("///")[10]).intValue());
                                                resultado.add(item);
                                            }
//long, string,string, long, tipoproblema, int, localdatetime,string,string, estadoincidente, int
            //
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;


    }

    public void cerrar(){
        IncidenteDBServicios accesoDB = new IncidenteDBServicios();

        try {
            accesoDB.cerrarIncidente(this);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void actualizar(){
        IncidenteDBServicios accesoDB = new IncidenteDBServicios();

        try {
            accesoDB.persistirIncidente(this);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void recuperar(){
        IncidenteDBServicios accesoDB = new IncidenteDBServicios();

        try {
            accesoDB.recuperar(this);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Incidente compararIncidentes(Incidente _incPrimario, Incidente _incCompara ){
        Incidente _resultado = new Incidente();
        _resultado.setNumeroINC(_incPrimario.getNumeroINC());
        if (_incPrimario.getCUIT()!=_incCompara.getCUIT()) _resultado.setCUIT(_incCompara.getCUIT());
        else _resultado.setCUIT(_incPrimario.getCUIT());
        if (_incPrimario.getLegajoTecnico()!=_incCompara.getLegajoTecnico()) _resultado.setLegajoTecnico(_incCompara.getLegajoTecnico());
        else _resultado.setLegajoTecnico(_incPrimario.getLegajoTecnico());
        if (_incPrimario.getParentINC()!=_incCompara.getParentINC()) _resultado.setParentINC(_incCompara.getParentINC());
        else _resultado.setParentINC(_incPrimario.getParentINC());
        if (_incPrimario.getProblema().getId()!=_incCompara.getProblema().getId()) _resultado.setProblema(_incCompara.getProblema());
        else _resultado.setProblema(_incPrimario.getProblema());
        if (_incPrimario.getHsComplejidad()!=_incCompara.getHsComplejidad()) _resultado.setHsComplejidad(_incCompara.getHsComplejidad());
        else _resultado.setHsComplejidad(_incPrimario.getHsComplejidad());
        if (_incPrimario.getDescripcion()!=_incCompara.getDescripcion()) _resultado.setDescripcion(_incCompara.getDescripcion());
        else _resultado.setDescripcion(_incPrimario.getDescripcion());
        if (_incPrimario.getInformacionCierre()!=_incCompara.getInformacionCierre()) _resultado.setInformacionCierre(_incCompara.getInformacionCierre());
        else _resultado.setInformacionCierre(_incPrimario.getInformacionCierre());
        if (_incPrimario.getEstado().ordinal()!=_incCompara.getEstado().ordinal()) _resultado.setEstado(_incCompara.getEstado());
        else _resultado.setEstado(_incPrimario.getEstado());
        if (_incPrimario.getIdServicio()!=_incCompara.getIdServicio()) _resultado.setIdServicio(_incCompara.getIdServicio());
        else _resultado.setIdServicio(_incPrimario.getIdServicio());

    return _resultado;
    }


}
