package Ticketera.Servicios;

import Ticketera.Entidades.Incidente;

import java.io.IOException;
import java.sql.*;



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
            st.setDate(7, java.sql.Date.valueOf(_incidente.getFechacreacion().toLocalDate()));
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


}
