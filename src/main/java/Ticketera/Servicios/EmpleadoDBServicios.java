package Ticketera.Servicios;

import Ticketera.Entidades.Empleado;
import Ticketera.Entidades.ServicioEspacialidad;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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

    public String recuperarEmpleado(String _userdata, int formaRecupero) throws Exception {
        String resultado = new String();
        ResultSet reader;

        try
        {
            this.conectar();
            String sqlStatement; new String();
            if (formaRecupero==0){sqlStatement=("SELECT * " +
                    "FROM `tpi_argprog2`.`nomina`\n" +
                    "where `nomina`.`usuario` ='" + _userdata+"'");}
            else {sqlStatement=("SELECT * " +
                    "FROM `tpi_argprog2`.`nomina`\n" +
                    "where `nomina`.`idnominaLegajo` ='" + _userdata+"'");}
            PreparedStatement st = this.getConexion().prepareStatement(sqlStatement);
            reader = st.executeQuery();
            reader.next();
            resultado= resultado.concat(reader.getString("idnominaLegajo"));
            resultado= resultado.concat(",");
            resultado= resultado.concat(reader.getString("nombre"));
            resultado= resultado.concat(",");
            resultado= resultado.concat(reader.getString("email"));
            resultado= resultado.concat(",");
            resultado= resultado.concat(reader.getString("telefono"));
            resultado= resultado.concat(",");
            resultado= resultado.concat(reader.getString("contactoPreferido"));
            resultado= resultado.concat(",");
            resultado= resultado.concat(reader.getString("rol_id"));
            resultado= resultado.concat(",");
            resultado= resultado.concat(reader.getString("estado"));

        } catch (Exception e)
        {
            throw e;
        }
        finally
        {
            this.cerrar();
        }
        return resultado;


    }
    public void actualizarEmpleado(Empleado _empleado) throws Exception {
        try {

            this.conectar();

            PreparedStatement st = this.getConexion().prepareStatement("UPDATE `tpi_argprog2`.`nomina`\n" +
                    "SET `nombre` = ?,\n" +
                    "`email`= ?,\n" +
                    "`telefono`=?,\n" +
                    "`contactoPreferido`=?\n"+
                    "Where idnominaLegajo = '" + _empleado.getLegajo() + "'");
            st.setString(1, _empleado.getNombre());
            st.setString(2,_empleado.getMail());
            st.setString(3, _empleado.getTelefono());
            st.setInt(4, _empleado.getTipoPreferido().ordinal() +1 );



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
    public String recuperarEmpleadoID(String _user) throws Exception {
        String resultado = new String();
        ResultSet reader;

        try
        {
            this.conectar();
            PreparedStatement st = this.getConexion().prepareStatement("SELECT `nomina`.`idnominaLegajo`\n" +
                    "FROM `tpi_argprog2`.`nomina`\n" +
                    "where `nomina`.`usuario` ='" + _user+"'");
            reader = st.executeQuery();
            reader.next();
            resultado= resultado.concat(reader.getString("idnominaLegajo"));


        } catch (Exception e)
        {
            throw e;
        }
        finally
        {
            this.cerrar();
        }
        return resultado;

    }

    public void bajaLogicaDB(String _legajo) throws Exception {
        try {

            this.conectar();

            PreparedStatement st = this.getConexion().prepareStatement("UPDATE `tpi_argprog2`.`nomina` SET `estado` = '0' WHERE (`idnominaLegajo` = '"+_legajo+"')");
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

