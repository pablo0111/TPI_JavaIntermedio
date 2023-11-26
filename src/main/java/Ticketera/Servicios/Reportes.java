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

    public static String tecnicoMasIncidentesResueltos(int dias) throws Exception {
        Reportes reporte = new Reportes();
        String lineaReporte=new String();
        ResultSet reader;
        try {
            reporte.instanciarPro();
            reporte.conectar();
            PreparedStatement st = reporte.getConexion().prepareStatement("SELECT l.idTecnico, p.nombre, count(*) FROM tpi_argprog2.incidente l \n" +
                    "inner join  tpi_argprog2.nomina p on l.idTecnico = p.idnominaLegajo \n" +
                    "where date(l.fechaCreacion)  >= date (sysdate() -?) \n" +
                    "group by l.idTecnico, p.nombre\n" +
                    "order by 2 desc\n" +
                    "Limit 1;");
            st.setInt(1,dias);
            reader = st.executeQuery();
            while (reader.next())
            {

                lineaReporte= lineaReporte.concat("ID: ");
                lineaReporte= lineaReporte.concat(reader.getString(1));
                lineaReporte= lineaReporte.concat(" | Nombre: ");
                lineaReporte= lineaReporte.concat(reader.getString(2));
                lineaReporte= lineaReporte.concat(" | Cantidad de tickets: ");
                lineaReporte= lineaReporte.concat(reader.getString(3));


            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (lineaReporte.length()==0) lineaReporte = "No hay resultados para mostrar";
        return lineaReporte;

    }

    public static String tecnicoMasIncidentesResueltosEspecialidad(int dias, int especialidad) throws Exception {

        Reportes reporte = new Reportes();
        String lineaReporte=new String();
        ResultSet reader;
        try {
            reporte.instanciarPro();
            reporte.conectar();
            PreparedStatement st = reporte.getConexion().prepareStatement("SELECT l.idTecnico, p.nombre, count(*) FROM tpi_argprog2.incidente l \n" +
                    "inner join  tpi_argprog2.nomina p on l.idTecnico = p.idnominaLegajo \n" +
                    "where date(l.fechaCreacion)  >= date (sysdate() -?) \n" +
                    "and l.idServicio = ?\n" +
                    "group by l.idTecnico, p.nombre\n" +
                    "order by 2 desc\n" +
                    "Limit 1;");
            st.setInt(1,dias);
            st.setInt(2,especialidad);
            reader = st.executeQuery();
            while (reader.next())
            {

                lineaReporte= lineaReporte.concat("ID: ");
                lineaReporte= lineaReporte.concat(reader.getString(1));
                lineaReporte= lineaReporte.concat(" | Nombre: ");
                lineaReporte= lineaReporte.concat(reader.getString(2));
                lineaReporte= lineaReporte.concat(" | Cantidad de tickets: ");
                lineaReporte= lineaReporte.concat(reader.getString(3));


            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (lineaReporte.length()==0) lineaReporte = "No hay resultados para mostrar";
        return lineaReporte;

    }

    public static String tecnicoQueResuelveMasRapidoIncidentes() throws Exception {
        Reportes reporte = new Reportes();
        String lineaReporte=new String();
        ResultSet reader;
        try {
            reporte.instanciarPro();
            reporte.conectar();
            PreparedStatement st = reporte.getConexion().prepareStatement("SELECT l.idTecnico, p.nombre, l.idincidenteNumero, l.duracion FROM tpi_argprog2.incidente l \n" +
                    "inner join  tpi_argprog2.nomina p on l.idTecnico = p.idnominaLegajo \n" +
                    "where l.duracion is not null\n" +
                    "order by 4 asc\n" +
                    "Limit 1;\n");
            reader = st.executeQuery();
            while (reader.next())
            {

                lineaReporte= lineaReporte.concat("ID: ");
                lineaReporte= lineaReporte.concat(reader.getString("l.idTecnico"));
                lineaReporte= lineaReporte.concat(" | Nombre: ");
                lineaReporte= lineaReporte.concat(reader.getString("p.nombre"));
                lineaReporte= lineaReporte.concat(" | Incidente: ");
                lineaReporte= lineaReporte.concat(reader.getString("l.idincidenteNumero"));
                int tiempoSegundos =  reader.getInt("l.duracion");
                lineaReporte= lineaReporte.concat(" | Duracion (D): ");
                lineaReporte= lineaReporte.concat(String.valueOf(tiempoSegundos / (24 * 3600)));
                lineaReporte= lineaReporte.concat(" | Duracion (h): ");
                lineaReporte= lineaReporte.concat(String.valueOf((tiempoSegundos % (24 * 3600))/ 3600) );
                lineaReporte= lineaReporte.concat(" | Duracion (m): ");
                lineaReporte= lineaReporte.concat(String.valueOf((tiempoSegundos % 3600) / 60));
                lineaReporte= lineaReporte.concat(" | Duracion (s): ");
                lineaReporte= lineaReporte.concat(String.valueOf((tiempoSegundos  % 60)));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (lineaReporte.length()==0) lineaReporte = "No hay resultados para mostrar";
        return lineaReporte;

    }


}
