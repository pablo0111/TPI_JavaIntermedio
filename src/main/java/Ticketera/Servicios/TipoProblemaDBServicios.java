package Ticketera.Servicios;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoProblemaDBServicios extends  BaseDeDatosServicios{
    public TipoProblemaDBServicios() {
        try {
            this.instanciarPro();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> readAllLines() throws Exception {

        List<String> listaServiciosEspecialidad;
        ResultSet reader;

        try
        {
            this.conectar();
            PreparedStatement st = this.getConexion().prepareStatement("select * from tpi_argprog2.tipoproblema");
            listaServiciosEspecialidad = new ArrayList();
            reader = st.executeQuery();
            while (reader.next())
            {
                String objSerEsp = new String();
                objSerEsp= objSerEsp.concat(reader.getString("idtipoproblema"));
                objSerEsp= objSerEsp.concat(",");
                objSerEsp= objSerEsp.concat(reader.getString("Descripcion"));
                objSerEsp= objSerEsp.concat(",");
                objSerEsp= objSerEsp.concat(reader.getString("hsRequeridasETR"));
                listaServiciosEspecialidad.add(objSerEsp);
            }
        } catch (Exception e)
        {
            throw e;
        }
        finally
        {
            this.cerrar();
        }
        return listaServiciosEspecialidad;

    }
}
