package Ticketera.Servicios;

import Ticketera.Entidades.ServicioEspacialidad;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ServicioEspecialidadDBServicios extends BaseDeDatosServicios{

    public ServicioEspecialidadDBServicios() {
        try {
            this.instanciarPro();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void agregarServicioEspecialidad(ServicioEspacialidad _serEsp)throws Exception{

        try {

            this.conectar();

            PreparedStatement st = this.getConexion().prepareStatement("INSERT INTO `tpi_argprog2`.`servicios_especialidades`\n" +
                    "(`idservicios_especialidades`,\n" +
                    "`Descripcion`)\n" +
                    "VALUES (?,?)");
            st.setNull(1, Types.INTEGER);
            st.setString(2, _serEsp.getNombre());


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

    public void agregarEspecilidadTecnico(ServicioEspacialidad _serEsp, String legajo) throws Exception {
        try {

            this.conectar();

            PreparedStatement st = this.getConexion().prepareStatement("INSERT INTO `tpi_argprog2`.`tecnico_especialidad`\n" +
                    "(`legajoID`,\n" +
                    "`servicio_especialidadID`)\n" +
                    "VALUES (?,?)");
            st.setLong(1, Long.parseLong(legajo));
            st.setInt(2, _serEsp.getIndex());


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
    public List<String> readAllLines() throws Exception {

        List<String> listaServiciosEspecialidad;
        ResultSet reader;

        try
        {
            this.conectar();
            PreparedStatement st = this.getConexion().prepareStatement("select * from tpi_argprog2.servicios_especialidades");
            listaServiciosEspecialidad = new ArrayList();
            reader = st.executeQuery();
            while (reader.next())
            {
                String objSerEsp = new String();
                objSerEsp= objSerEsp.concat(reader.getString("idservicios_especialidades"));
                objSerEsp= objSerEsp.concat(",");
                objSerEsp= objSerEsp.concat(reader.getString("Descripcion"));

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
