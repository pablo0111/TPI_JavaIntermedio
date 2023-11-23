package Ticketera.Entidades;

public class ClienteNoEncontradoException  extends Exception {

        public ClienteNoEncontradoException() {
            super("Cliente no encontrado");
        }

}
