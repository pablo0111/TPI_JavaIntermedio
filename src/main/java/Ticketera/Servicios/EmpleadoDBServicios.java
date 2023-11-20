package Ticketera.Servicios;

import Ticketera.Entidades.Empleado;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Types;

public class EmpleadoDBServicios extends BaseDeDatosServicios{
    public EmpleadoDBServicios() {
        try {
            this.instanciarPro();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void persistirEmpleado(Empleado _empleado) throws Exception {
        try {

            this.conectar();

            PreparedStatement st = this.getConexion().prepareStatement("INSERT INTO `tpi_argprog2`.`nomina`\n" +
                    "(`idnominaLegajo`,\n" +
                    "`nombre`,\n" +
                    "`email`,\n" +
                    "`telefono`,\n" +
                    "`contactoPreferido`,\n" +
                    "`usuario`,\n" +
                    "`clave`,\n" +
                    "`rol_id`)\n"+
                    "VALUES (?,?,?,?,?,?,?,?)");
            st.setNull(1, Types.BIGINT);
            st.setString(2, _empleado.getNombre());
            st.setString(3,_empleado.getMail());
            st.setString(4, _empleado.getTelefono());
            st.setInt(5, _empleado.getTipoPreferido().ordinal() +1 );
            st.setString(6,_empleado.getUsuario().getNombreUsuario());
            st.setString(7,_empleado.getUsuario().getClave());
            st.setInt(8,_empleado.getRol().ordinal() +1 );


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

}

