package Ticketera.Entidades;

public enum Roles {
    ADMIN,
    RRHH,
    TECNICO,
    OPERADORMDA,
    COMERCIAL;
    static public Roles getPorIndice(int i){
        Roles[] resul = Roles.values();
        return resul[i-1];
    }
}
