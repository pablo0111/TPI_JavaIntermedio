package Ticketera.Entidades;

public enum TIPOCONTACTO {
    WHATSAPP,
    EMAIL,
    TELEFONO;
    static public TIPOCONTACTO getPorIndice(int i){
        if (i==1) {
            return WHATSAPP;
        } else if (i==2) {
            return EMAIL;
        } else return TELEFONO;
    }
}
