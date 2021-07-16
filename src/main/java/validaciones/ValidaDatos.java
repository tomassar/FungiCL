package validaciones;

import modelo.Usuario;

public class ValidaDatos {

    public static boolean validarClave(String clave, Usuario usuario){
        return usuario.getClave().equals(clave);
    }
}
