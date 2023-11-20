package Ticketera.Servicios;

import Ticketera.Entidades.Cliente;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDBServicios extends BaseDeDatosServicios{

    public ClienteDBServicios() {
        try {
            this.instanciarPro();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void persistirCliente(Cliente _cliente) throws Exception {
        try {

            this.conectar();

            PreparedStatement st = this.getConexion().prepareStatement("INSERT INTO `tpi_argprog2`.`cliente`\n" +
                        "(`idClienteCUIT`,\n" +
                        "`RazonSocial`,\n" +
                        "`eMail`)\n" +
                        "VALUES(?,?,?)");
                st.setLong(1, Long.parseLong(_cliente.getCUIT()));
                st.setString(2, _cliente.getRazonSocial());
                st.setString(3,_cliente.getMailContacto());

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
    public void bajaCliente(String _cuit) throws Exception{
        try {


            this.conectar();

            PreparedStatement st = this.getConexion().prepareStatement("UPDATE `tpi_argprog2`.`cliente`\n" +
                    "SET\n" +
                    "estado = 0\n"+
                    "WHERE `idClienteCUIT` = "+ _cuit+";");
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

    public String obtenerCliente(String dato, int formaRecupero) throws Exception {

        ResultSet reader;
        String cliente = new String();

        try
        {   String sqlStatement= new String();
            if (formaRecupero==0) sqlStatement ="select * from cliente where idClienteCUIT ='"+dato+"'";
            else sqlStatement="select * from cliente where RazonSocial ='"+dato+"'";
            this.conectar();
            PreparedStatement st = this.getConexion().prepareStatement(sqlStatement);
            reader = st.executeQuery();
            reader.next();
            cliente= cliente.concat(reader.getString("idClienteCUIT"));
            cliente= cliente.concat(",");
            cliente= cliente.concat(reader.getString("RazonSocial"));
            cliente= cliente.concat(",");
            cliente= cliente.concat(reader.getString("eMail"));


        } catch (Exception e)
        {
            throw e;
        }
        finally
        {
            this.cerrar();
        }
        //DEVOLVER CLIENTE
        return cliente;
    }

    public void actualizarCliente(Cliente _cliente)throws Exception {

        try {
            this.conectar();

            PreparedStatement st = this.getConexion().prepareStatement("UPDATE `tpi_argprog2`.`cliente`\n" +
                    "SET\n" +
                    "RazonSocial = '"+ _cliente.getRazonSocial() +"', " +
                    "eMail= '" + _cliente.getMailContacto() + "'" +
                    " WHERE `idClienteCUIT` = ?");
            st.setLong(1,Long.parseLong(_cliente.getCUIT()));
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
