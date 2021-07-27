package datos;

import modelo.EstadoHongo;
import modelo.Hongo;
import org.junit.jupiter.api.*;
import modelo.TipoHongo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestManejaDatosHongo {

    private static ManejaDatosHongo manejo;

    @BeforeEach
    void setUp() {
        manejo = new ManejaDatosHongo();
    }

    @AfterEach
    void tearDown() {
        manejo = null;
        System.gc();
    }

    @Test
    @DisplayName("Test para verificar conexiones fallidas")
    void conexionFallida(){
        assertFalse(manejo.handleEstablecerConexion(null, "root", "3306"));
    }

    @Test
    @DisplayName("Test para verificar creación de un hongo")
    void crearHongo(){
        ArrayList<TipoHongo> genericas = new ArrayList<>();
       Hongo hongo = new Hongo("Genérico", "Genérico", "Descripción: Genérica", genericas, EstadoHongo.valueOf("POR_CONFIRMAR"),null);
        assertTrue(manejo.handleCrear(hongo));
    }

    @Test
    @DisplayName("Test para verificar fallo al cargar hongos desde la base de datos")
    void FalloAlCargarHongos(){

        assertEquals(0, manejo.handleObtenerHongos().size());
    }

    @Test
    @DisplayName("Test para verificar fallo al buscar hongos por nombre en la base de datos")
    void FalloAlBuscarHongos(){

        assertEquals(0, manejo.handleBuscarHongoPorNombre("inexistente").size());
    }
}
