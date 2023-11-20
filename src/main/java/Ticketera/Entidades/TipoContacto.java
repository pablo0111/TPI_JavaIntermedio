package Ticketera.Entidades;

public enum TipoContacto {
    WHATSAPP,
    EMAIL,
    TELEFONO;
    static public TipoContacto getPorIndice(int i){
        if (i==1) {
            return WHATSAPP;
        } else if (i==2) {
            return EMAIL;
        } else return TELEFONO;
    }
}
