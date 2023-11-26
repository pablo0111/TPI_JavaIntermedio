package Ticketera.Entidades;

public enum EstadoIncidente {
    ABIERTO,
    RESUELTO,
    ENESPERA,
    CANCELADO;
    static public EstadoIncidente getPorIndice(int i){
        EstadoIncidente[] resul = EstadoIncidente.values();
        return resul[i-1];
    }
}
