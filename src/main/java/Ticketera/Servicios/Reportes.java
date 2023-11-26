package Ticketera.Servicios;

import Ticketera.Entidades.EstadoIncidente;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Reportes extends BaseDeDatosServicios{

    public static List<String> incidentesAsignadosPorTecnico() throws Exception {
        Reportes reporte = new Reportes();
        List<String> lineaReporte;
        ResultSet reader;
        try {
            reporte.instanciarPro();
            reporte.conectar();
            PreparedStatement st = reporte.getConexion().prepareStatement("SELECT l.idTecnico, p.nombre, l.idClienteCUIT, l.fechaCreacion, l.descripcionProblema, l.idEstado FROM tpi_argprog2.incidente l \n" +
                    "inner join  tpi_argprog2.nomina p on l.idTecnico = p.idnominaLegajo \n" +
                    "where date(l.fechaCreacion)  = date (sysdate())");
            lineaReporte = new ArrayList();
            reader = st.executeQuery();
            while (reader.next())
            {
                String objSerEsp = new String();
                objSerEsp= objSerEsp.concat("ID: ");
                objSerEsp= objSerEsp.concat(reader.getString("l.idTecnico"));
                objSerEsp= objSerEsp.concat(" | Nombre: ");
                objSerEsp= objSerEsp.concat(reader.getString("p.nombre"));
                objSerEsp= objSerEsp.concat(" | CUIT Cliente: ");
                objSerEsp= objSerEsp.concat(reader.getString("l.idClienteCUIT"));
                objSerEsp= objSerEsp.concat(" | Fecha Creacion: ");
                objSerEsp= objSerEsp.concat(reader.getString("l.fechaCreacion"));
                objSerEsp= objSerEsp.concat(" | Descripcion: ");
                objSerEsp= objSerEsp.concat(reader.getString("l.descripcionProblema"));
                objSerEsp= objSerEsp.concat(" | Estado: ");
                objSerEsp= objSerEsp.concat(EstadoIncidente.getPorIndice(reader.getInt("l.idEstado")).toString());

                lineaReporte.add(objSerEsp);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lineaReporte;

    }


}
