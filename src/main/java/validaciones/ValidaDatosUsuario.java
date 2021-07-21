package validaciones;

import modelo.Usuario;

public class ValidaDatosUsuario {
    public static boolean clavesCoinciden(String clave, String confirmacion){
        return clave.equals (confirmacion);
    }

    public static boolean esVacio(String texto){
        return texto.equals("");
    }

    public static boolean esEmail(String email) {
        return email.contains("@");
    }
}
