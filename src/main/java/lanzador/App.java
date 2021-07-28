package lanzador;
/**
 * @author Proyecto FungiAraucania
 * Clase que inicia la interfaz de usuario
 **/
import vista.*;
public class App {
    /**
     * @author Proyecto FungiAraucania
     * @param args
     * Inicia la interfaz de usuario
     **/
    public static void main(String[] args) {
        IniciaSesion iniciarSesion = new IniciaSesion();
        iniciarSesion.setVisible (true);
    }
}
