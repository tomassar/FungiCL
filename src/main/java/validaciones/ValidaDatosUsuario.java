package validaciones;
/**
 * @author Proyecto FungiAraucania
 * Constructor de la clase ManejaDatosHongo.
 * Realiza conexión con la base de datos del proyecto.
 * No requiere parámetros.
 **/
public class ValidaDatosUsuario {
    /**
     * Método que verifica si la clave ingresada por el usuario coincide
     * con la que dijo previamente. Las claves deben coincidir.
     * @param clave clave creada por el usuario.
     * @param confirmacion clave confirmada.
     * @return boolean.
     */
    public static boolean clavesCoinciden(String clave, String confirmacion){
        return clave.equals (confirmacion);
    }
    /**
     * Revisa si la cadena de texto está o no vacía.
     * Se utiliza para crear tanto un usuario como un hongo.
     * @param texto cadena enviada a analizar.
     * @return boolean.
     */
    public static boolean esVacio(String texto){
        return texto.equals("");
    }
    /**
     * Revisa si la cadena contiene o no el símbolo @.
     * @param email cadena enviada a analizar.
     * @return boolean.
     */
    public static boolean esEmail(String email) {
        return email.contains("@");
    }
}
