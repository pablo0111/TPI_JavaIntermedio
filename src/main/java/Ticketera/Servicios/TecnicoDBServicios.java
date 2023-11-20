package Ticketera.Servicios;

import Ticketera.Entidades.Tecnico;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Types;

public class TecnicoDBServicios extends BaseDeDatosServicios{
    public TecnicoDBServicios(){
        try {
            this.instanciarPro();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void persistirTecnico(Tecnico _tecn) throws Exception {
        try {

//            this.conectar();
//
//            PreparedStatement st = this.getConexion().prepareStatement("INSERT INTO `tpi_argprog2`.`servicios_especialidades`\n" +
//                    "(`idservicios_especialidades`,\n" +
//                    "`Descripcion`)\n" +
//                    "VALUES (?,?)");
//            st.setNull(1, Types.INTEGER);
//            st.setString(2, _serEsp.getNombre());
//
//
//            st.executeUpdate();




        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            this.cerrar();
        }

    }

}
