package Ticketera.Entidades;

import Ticketera.Servicios.TipoProblemaDBServicios;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class TipoProblemas {
    private int id;
    private String descripcion;
    private int ETR;

    public TipoProblemas(int id) {
        this.id = id;
        this.recuperar();
    }

    @Override
    public String toString (){
        return getDescripcion();
    }

    public static List<TipoProblemas> obtenerTipoProblemas() throws Exception {
        List<TipoProblemas> resultado = new ArrayList<TipoProblemas>();
        List <String> resultadoString = new TipoProblemaDBServicios().readAllLines() ;
        resultadoString.stream().forEach((linea)->resultado.add( new TipoProblemas(Integer.parseInt(linea.split(",")[0]),linea.split(",")[1],Integer.parseInt(linea.split(",")[2]))));
        return resultado;
    }

    private void recuperar(){
        String datos;
        try {
            datos = new TipoProblemaDBServicios().getServicio(id);
            setDescripcion(datos.split(",")[1]);
            setETR(Integer.parseInt(datos.split(",")[2]));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
