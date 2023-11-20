package Ticketera.Entidades;

public enum EstadoCliente {
    ACTIVO,
    INACTIVO;

    static public EstadoCliente getPorIndice(int i){
        if (i==1) {
            return ACTIVO;

        } else return INACTIVO;
    }
}
