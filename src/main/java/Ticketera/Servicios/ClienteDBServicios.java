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

    public String obtenerCliente(String cuit) throws Exception {

        ResultSet reader;
        String cliente = new String();

        try
        {
            this.conectar();
            PreparedStatement st = this.getConexion().prepareStatement("select * from cliente where idClienteCUIT ='"+cuit);
            reader = st.executeQuery();
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

}
