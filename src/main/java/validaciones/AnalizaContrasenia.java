package validaciones;
/**
 * @author Proyecto FungiAraucania
 * Constructor de la clase ManejaDatosHongo.
 * Realiza conexión con la base de datos del proyecto.
 * No requiere parámetros.
 **/
public class AnalizaContrasenia {
    /**
     * @param mensajePar atributo que se envía desde el paquete modelo
     * para limpiar el mensaje una vez devuelve la seguridad de la
     * contraseña.
     */
    public static void setMensaje(String mensajePar) {
        mensaje = mensajePar;
    }

    private static String mensaje = " ";
    /**
     * Método público que retorna que tan segura es una contrasenia.
     * @param contrasenia contraseña enviada por la clase
     * ComunicaUsuarioConDatos del paquete modelo para ser analizada.
     * @return String
     */
    public static String verificarContrasenia(String contrasenia) {

        agregarMensaje(contrasenia);

        if (verificarContraseniaInsegura()) {
            return mensaje;
        }
        if (verificarContraseniaRegular()) {
            return mensaje;
        }
        if(verificarContraseniaMedianamenteSegura()){
            return mensaje;
        }
        return "Contraseña segura";
    }
    /**
     * Método privado que verifica si la clave tiene o no un largo de al menos
     * 8 caracteres.
     * @return boolean.
     */
    private static boolean verificarContraseniaInsegura() {
        if (mensaje.contains("Largo mínimo de 8 caracteres")) {
            mensaje = "INSEGURA: " + mensaje;
            return true;
        }
        return false;
    }
    /**
     * Método privado que verifica si la clave tiene o no: dígitos, mayúsculas y minúsculas.
     * @return boolean.
     */
    private static boolean verificarContraseniaRegular() {
        if (mensaje.contains("Faltan dígitos") || mensaje.contains("Faltan Mayúsculas") || mensaje.contains("Faltan Minúsculas")) {
            mensaje = "REGULAR: " + mensaje;
            return true;
        }
        return false;
    }
    /**
     * Método privado que verifica si la clave tiene o no símbolos.
     * @return boolean.
     */
    private static boolean verificarContraseniaMedianamenteSegura(){
        if (mensaje.contains("Faltan símbolos")){
            mensaje = "MEDIANAMENTE SEGURA: " + mensaje;
            return true;
        }
        return false;
    }
    /**
     * Método privado que calcula la cantidad de dígitos, minúsculas, mayúsculas y
     * símbolos que contiene el argumento enviado por el usuario, y revisa el largo del mismo.
     * @param contrasenia atributo analizado.
     */
    private static void agregarMensaje(String contrasenia) {

        int boolDigitos = 0;
        int boolMinusculas = 0;
        int boolMayusculas = 0;
        int boolLargo = 0;
        int boolsimbolos = 0;

        for (int i = 0; i < contrasenia.length(); i++) {
            boolDigitos += convertirBooleanAInt(contenerDigito(contrasenia.charAt(i)));
            boolMinusculas += convertirBooleanAInt(contenerLetrasMinus(contrasenia.charAt(i)));
            boolMayusculas += convertirBooleanAInt(contenerLetrasMayus(contrasenia.charAt(i)));
            boolLargo += convertirBooleanAInt(verificarLargoMinimo(contrasenia));
            boolsimbolos += convertirBooleanAInt(contenerSimbolos(contrasenia.charAt(i)));
        }
        mensaje = generarMensaje(boolDigitos, boolMinusculas, boolMayusculas, boolLargo, boolsimbolos);
    }
    /**
     * Método que convierte un booleano a entero.
     * @param bool booleano a convertir.
     * @return int.
     */
    private static int convertirBooleanAInt(boolean bool) {
        return bool ? 1 : 0;
    }
    /**
     * Método privado que genera un mensaje, dependiendo de si los atributos son 0 o no.
     * @param digitos entero con la cantidad de dígitos de la contraseña.
     * @param minusculas entero con la cantidad de minúsculas de la contraseña.
     * @param mayusculas entero con la cantidad de mayúsculas de la contraseña.
     * @param largo entero con 1 o 0 que indica si el largo de la contraseña es mayor o igual que 8 o no.
     * @param simbolos entero con la cantidad de símbolos de la contraseña.
     * @return String.
     */
    private static String generarMensaje(int digitos, int minusculas, int mayusculas, int largo, int simbolos) {

        if (digitos == 0) {
            mensaje += "Faltan dígitos,";
        }
        if (minusculas == 0) {
            mensaje += " Faltan minúsculas,";
        }
        if (mayusculas == 0) {
            mensaje += " Faltan mayúsculas,";
        }
        if(simbolos == 0){
            mensaje += " Faltan símbolos,";
        }
        if (largo == 0) {
            mensaje += " Largo mínimo de 8 caracteres.";
        }
        return mensaje.substring(0, mensaje.length()-1);
    }
    /**
     * Método que verifica si el caracter es o no un número.
     * @param contrasenia caracter a analizar.
     * @return boolean.
     */
    private static boolean contenerDigito(char contrasenia) {

        return Character.isDigit(contrasenia);
    }
    /**
     * Método que verifica si el caracter es o no una mayúscula.
     * @param contrasenia caracter a analizar.
     * @return boolean.
     */
    private static boolean contenerLetrasMayus(char contrasenia) {

        if (Character.isLetter(contrasenia)) {
            return Character.isUpperCase(contrasenia);
        }
        return false;
    }
    /**
     * Método que verifica si el caracter es o no una minúscula.
     * @param contrasenia caracter a analizar.
     * @return boolean.
     */
    private static boolean contenerLetrasMinus(char contrasenia) {

        if (Character.isLetter(contrasenia)) {
            return Character.isLowerCase(contrasenia);
        }
        return false;
    }
    /**
     * Método que verifica si el caracter es o no un símbolo.
     * @param contrasenia caracter a analizar.
     * @return boolean.
     */
    private static boolean contenerSimbolos(char contrasenia){

        return !(Character.isAlphabetic(contrasenia) || Character.isDigit(contrasenia));
    }
    /**
     * Método que verifica si el largo es o no mayor a 8.
     * @param contrasenia caracter a analizar.
     * @return boolean.
     */
    private static boolean verificarLargoMinimo(String contrasenia) {
        return contrasenia.length() >= 8;
    }
}
