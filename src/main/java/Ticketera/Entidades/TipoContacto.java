package Ticketera.Entidades;

public enum TipoContacto {
    WHATSAPP,
    EMAIL,
    TELEFONO;
    static public TipoContacto getPorIndice(int i){
       TipoContacto[] resul = TipoContacto.values();
       return resul[i-1];
    }
}
