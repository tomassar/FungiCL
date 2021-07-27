package datos;

import modelo.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUsuario {

    @Test
    @DisplayName("Test para verificar creación de un usuario exitosa")
    void crearUsuario() {
        Usuario usuario = new Usuario("genérico", "contrasenia", "generico@generico.com");
        String a= "genérico";
        assertEquals(a, usuario.getNombre());
    }
}