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

    public void eliminarEspecilidadTecnico(ServicioEspacialidad _serEsp, String legajo) throws Exception{
        try {

            this.conectar();

            PreparedStatement st = this.getConexion().prepareStatement("DELETE FROM `tpi_argprog2`.`tecnico_especialidad`\n" +
                    "WHERE `legajoID` = ?\n" +
                    " AND `servicio_especialidadID` = ?" );
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


    public List<String> readAllLinesTecnicoEspecialidad(String _legajo)throws Exception {
        List<String> listaServiciosEspecialidad;
        ResultSet reader;

        try
        {
            this.conectar();
            PreparedStatement st = this.getConexion().prepareStatement("SELECT l.legajoID, l.servicio_especialidadID, p.Descripcion FROM tpi_argprog2.tecnico_especialidad l left join tpi_argprog2.servicios_especialidades p on l.servicio_especialidadID = p.idservicios_especialidades where legajoID='"+_legajo + "'");
            listaServiciosEspecialidad = new ArrayList();
            reader = st.executeQuery();
            while (reader.next())
            {
                String objSerEsp = new String();
                objSerEsp= objSerEsp.concat(reader.getString("legajoID"));
                objSerEsp= objSerEsp.concat(",");
                objSerEsp= objSerEsp.concat(reader.getString("servicio_especialidadID"));
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


    public List<String> readAllLinesClienteEspecialidad(String _cuit)throws Exception {
        List<String> listaServiciosEspecialidad;
        ResultSet reader;

        try
        {
            this.conectar();
            PreparedStatement st = this.getConexion().prepareStatement("SELECT l.clienteCUIT_id, l.servicio_especialidad_id, p.Descripcion FROM tpi_argprog2.servicios_contratados l left join tpi_argprog2.servicios_especialidades p on l.servicio_especialidad_id = p.idservicios_especialidades \n" +
                    "where l.clienteCUIT_id='"+_cuit + "'");
            listaServiciosEspecialidad = new ArrayList();
            reader = st.executeQuery();
            while (reader.next())
            {
                String objSerEsp = new String();
                objSerEsp= objSerEsp.concat(reader.getString("clienteCUIT_id"));
                objSerEsp= objSerEsp.concat(",");
                objSerEsp= objSerEsp.concat(reader.getString("servicio_especialidad_id"));
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

    public void agregarEspecilidadCliente(ServicioEspacialidad _serEsp, String cuit) throws Exception {
        try {

            this.conectar();

            PreparedStatement st = this.getConexion().prepareStatement("INSERT INTO `tpi_argprog2`.`servicios_contratados`\n" +
                    "(`clienteCUIT_id`,\n" +
                    "`servicio_especialidad_id`)\n" +
                    "VALUES (?,?)");
            st.setLong(1, Long.parseLong(cuit));
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

    public void eliminarEspecilidadCliente(ServicioEspacialidad _serEsp, String cuit) throws Exception{
        try {

            this.conectar();

            PreparedStatement st = this.getConexion().prepareStatement("DELETE FROM `tpi_argprog2`.`servicios_contratados`\n" +
                    "WHERE `clienteCUIT_id` = ?\n" +
                    " AND `servicio_especialidad_id` = ?" );
            st.setLong(1, Long.parseLong(cuit));
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
}
