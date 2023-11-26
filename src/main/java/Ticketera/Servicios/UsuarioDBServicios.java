package Ticketera.Servicios;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class UsuarioDBServicios extends BaseDeDatosServicios{

    public UsuarioDBServicios() {
        try {
            this.instanciarPro();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String obtenerClavedeUsuario(String _user) throws Exception {
        ResultSet reader;
        String clave = new String();

        try
        {
            this.conectar();
            PreparedStatement st = this.getConexion().prepareStatement("SELECT  `nomina`.`clave`\n" +
                    "FROM `tpi_argprog2`.`nomina`\n" +
                    "where `nomina`.`usuario` = '"+_user+"';");
            reader = st.executeQuery();
            reader.next();
            clave = clave.concat(reader.getString(1));

        } catch (Exception e)
        {
            throw e;
        }
        finally
        {
            this.cerrar();
        }
        //DEVOLVER CLIENTE
        return clave;

    }

}
