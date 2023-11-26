package Ticketera.Servicios;

import Ticketera.Entidades.EstadoIncidente;
import Ticketera.Entidades.Incidente;
import Ticketera.Entidades.TipoProblemas;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class IncidenteDBServicios extends BaseDeDatosServicios{
    public IncidenteDBServicios(){
        try {
            this.instanciarPro();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int persisirCrearIncidente(Incidente _incidente) throws Exception {
        int idIncidenteNuevo = 0;
        try {

            this.conectar();

            PreparedStatement st = this.getConexion().prepareStatement("INSERT INTO `tpi_argprog2`.`incidente`\n" +
                    "(`idincidenteNumero`,\n" +
                    "`idClienteCUIT`,\n" +
                    "`idTecnico`,\n" +
                    "`idParent`,\n" +
                    "`idTipoProblema`,\n" +
                    "`horasAdic`,\n" +
                    "`fechaCreacion`,\n" +
                    "`descripcionProblema`,\n" +
                    "`descripcionResolucion`,\n" +
                    "`idEstado`,\n" +
                    "`idServicio`)\n" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            st.setNull(1, Types.BIGINT);
            st.setLong (2,Long.parseLong(_incidente.getCUIT()));
            st.setInt(3,Integer.parseInt(_incidente.getLegajoTecnico()));
            st.setNull(4, Types.BIGINT);
            st.setInt(5, _incidente.getProblema().getId());
            st.setInt(6, _incidente.getHsComplejidad());
            st.setObject(7, Timestamp.valueOf(_incidente.getFechacreacion()));
            //st.setDate(7, java.sql.Date.valueOf(_incidente.getFechacreacion().toLocalDate()));
            st.setString(8, _incidente.getDescripcion());
            st.setNull(9,Types.VARCHAR);
            st.setInt(10,_incidente.getEstado().ordinal()+1);
            st.setInt(11,_incidente.getIdServicio());

            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {
                idIncidenteNuevo = rs.getInt(1);
            }



        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            this.cerrar();
        }
        return idIncidenteNuevo;
    }

    public void cerrarIncidente(Incidente _incidente) throws Exception{
        try {

            this.conectar();

            PreparedStatement st = this.getConexion().prepareStatement("UPDATE `tpi_argprog2`.`incidente`\n" +
                    "SET\n" +
                    "`descripcionResolucion` = ?,\n" +
                    "`idEstado` = ?\n" +
                    "WHERE `idincidenteNumero` = ?");
            st.setString(1, _incidente.getInformacionCierre());
            st.setInt (2, _incidente.getEstado().ordinal()+1);
            st.setLong(3,_incidente.getNumeroINC());

            st.executeUpdate();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            this.cerrar();
        }

    }

    public void persistirIncidente(Incidente _incidente) throws Exception{
        try {
            Incidente incCompara= new Incidente();
            incCompara.setNumeroINC(_incidente.getNumeroINC());
            incCompara.recuperar();
            Incidente persistencia = Incidente.compararIncidentes(incCompara,_incidente);
            this.conectar();

            PreparedStatement st = this.getConexion().prepareStatement("UPDATE `tpi_argprog2`.`incidente`\n" +
                    "SET\n" +
                    "`idClienteCUIT` = ?,\n" +
                    "`idTecnico` = ?,\n" +
                    "`idParent` = ?,\n" +
                    "`idTipoProblema` = ?,\n" +
                    "`horasAdic` = ?,\n" +
                    "`fechaCreacion` = ?,\n" +
                    "`descripcionProblema` = ?,\n" +
                    "`descripcionResolucion` = ?,\n" +
                    "`idEstado` = ?,\n" +
                    "`idServicio` = ?\n" +
                    "WHERE `idincidenteNumero` = ?");
            st.setLong(11,_incidente.getNumeroINC());
            st.setLong (1,Long.parseLong(persistencia.getCUIT()));
            st.setInt(2,Integer.parseInt(persistencia.getLegajoTecnico()));
            st.setLong (3, persistencia.getParentINC());
            st.setInt(4, persistencia.getProblema().getId());
            st.setInt(5, persistencia.getHsComplejidad());
            st.setDate(6, java.sql.Date.valueOf(persistencia.getFechacreacion().toLocalDate())); //**//**//**//**//**//**//***/*/*/*/*//*/
            st.setString(7, persistencia.getDescripcion());
            st.setString(8, persistencia.getInformacionCierre());
            st.setInt(9,persistencia.getEstado().ordinal()+1);
            st.setInt(10,persistencia.getIdServicio());

            st.executeUpdate();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            this.cerrar();
        }

    }
    public void recuperar(Incidente _incidenteACompletar)throws Exception{
        try
        {
            this.conectar();
            PreparedStatement st = this.getConexion().prepareStatement("SELECT * FROM tpi_argprog2.incidente where idincidenteNumero=?");
            st.setLong(1,_incidenteACompletar.getNumeroINC());
            ResultSet reader = st.executeQuery();
            reader.next();
            _incidenteACompletar.setCUIT(reader.getString("idClienteCUIT"));
            _incidenteACompletar.setLegajoTecnico(reader.getString("idTecnico"));
            if (reader.getString("idParent")!= null) _incidenteACompletar.setParentINC(reader.getLong("idParent"));
            else _incidenteACompletar.setParentINC(0);
            _incidenteACompletar.setProblema(new TipoProblemas(reader.getInt("idTipoProblema")));
            if (reader.getString("horasAdic")!= null) _incidenteACompletar.setHsComplejidad(reader.getInt("horasAdic"));
            else _incidenteACompletar.setHsComplejidad(0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            _incidenteACompletar.setFechacreacion(LocalDateTime.parse(reader.getString("fechaCreacion"),formatter));
            _incidenteACompletar.setDescripcion(reader.getString("descripcionProblema"));
            if (reader.getString("descripcionResolucion")!= null) _incidenteACompletar.setInformacionCierre(reader.getString("descripcionResolucion"));
            else _incidenteACompletar.setInformacionCierre("");
            _incidenteACompletar.setEstado(EstadoIncidente.getPorIndice(reader.getInt("idEstado")));
            _incidenteACompletar.setIdServicio(reader.getInt("idServicio"));

        } catch (Exception e)
        {
            throw e;
        }
        finally
        {
            this.cerrar();
        }

    }

    public List<String> recuperarIncidentesPorTecnico (String legajo, EstadoIncidente estado)throws Exception{
        List<String> listadoIncidentes;
        ResultSet reader;

        try
        {
            this.conectar();
            PreparedStatement st = this.getConexion().prepareStatement("SELECT * FROM tpi_argprog2.incidente where idTecnico=? and idEstado=?");
            st.setInt(1,Integer.parseInt(legajo));
            st.setInt(2, estado.ordinal()+1);
            listadoIncidentes = new ArrayList();
            reader = st.executeQuery();
            while (reader.next())
            {
                String objSerEsp = new String();
                objSerEsp= objSerEsp.concat(reader.getString("idincidenteNumero"));
                objSerEsp= objSerEsp.concat("///");
                objSerEsp= objSerEsp.concat(reader.getString("idClienteCUIT"));
                objSerEsp= objSerEsp.concat("///");
                objSerEsp= objSerEsp.concat(reader.getString("idTecnico"));
                objSerEsp= objSerEsp.concat("///");
                if (reader.getString("idParent")!= null) objSerEsp= objSerEsp.concat(reader.getString("idParent"));
                        else objSerEsp= objSerEsp.concat("0");
                objSerEsp= objSerEsp.concat("///");
                objSerEsp= objSerEsp.concat(reader.getString("idTipoProblema"));
                objSerEsp= objSerEsp.concat("///");
                if (reader.getString("horasAdic")!= null) objSerEsp= objSerEsp.concat(reader.getString("horasAdic"));
                        else objSerEsp= objSerEsp.concat("0");
                objSerEsp= objSerEsp.concat("///");
                objSerEsp= objSerEsp.concat(reader.getString("fechaCreacion"));
                objSerEsp= objSerEsp.concat("///");
                objSerEsp= objSerEsp.concat(reader.getString("descripcionProblema"));
                objSerEsp= objSerEsp.concat("///");
                if (reader.getString("descripcionResolucion")!= null) objSerEsp= objSerEsp.concat(reader.getString("descripcionResolucion"));
                        else objSerEsp= objSerEsp.concat(" ");
                objSerEsp= objSerEsp.concat("///");
                objSerEsp= objSerEsp.concat(reader.getString("idEstado"));
                objSerEsp= objSerEsp.concat("///");
                objSerEsp= objSerEsp.concat(reader.getString("idServicio"));


                listadoIncidentes.add(objSerEsp);
            }
        } catch (Exception e)
        {
            throw e;
        }
        finally
        {
            this.cerrar();
        }
        return listadoIncidentes;

    }

}
