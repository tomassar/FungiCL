package validaciones;

public class AnalizaContrasenia {

    public static void setMensaje(String mensajePar) {
        mensaje = mensajePar;
    }

    private static String mensaje = " ";

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

    private static boolean verificarContraseniaInsegura() {
        if (mensaje.contains("Largo mínimo de 8 caracteres")) {
            mensaje = "INSEGURA: " + mensaje;
            return true;
        }
        return false;
    }

    private static boolean verificarContraseniaRegular() {
        if (mensaje.contains("Faltan dígitos") || mensaje.contains("Faltan Mayúsculas") || mensaje.contains("Faltan Minúsculas")) {
            mensaje = "REGULAR: " + mensaje;
            return true;
        }
        return false;
    }

    private static boolean verificarContraseniaMedianamenteSegura(){
        if (mensaje.contains("Faltan símbolos")){
            mensaje = "MEDIANAMENTE SEGURA: " + mensaje;
            return true;
        }
        return false;
    }
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

    private static int convertirBooleanAInt(boolean bool) {
        return bool ? 1 : 0;
    }

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

    private static boolean contenerDigito(char contrasenia) {

        return Character.isDigit(contrasenia);
    }

    private static boolean contenerLetrasMayus(char contrasenia) {

        if (Character.isLetter(contrasenia)) {
            return Character.isUpperCase(contrasenia);
        }
        return false;
    }

    private static boolean contenerLetrasMinus(char contrasenia) {

        if (Character.isLetter(contrasenia)) {
            return Character.isLowerCase(contrasenia);
        }
        return false;
    }

    private static boolean contenerSimbolos(char contrasenia){

        return !(Character.isAlphabetic(contrasenia) || Character.isDigit(contrasenia));
    }

    private static boolean verificarLargoMinimo(String contrasenia) {
        return contrasenia.length() >= 8;
    }
}
