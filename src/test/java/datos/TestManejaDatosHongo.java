package datos;

import modelo.EstadoHongo;
import modelo.Hongo;
import org.junit.jupiter.api.*;

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
        assertFalse(ManejaDatosHongo.establecerConexion(null, "root", "3306"));
    }

    @Test
    @DisplayName("Test para verificar fallo al crear un hongo")
    void FalloAlCrearHongo(){

      //  Hongo hongo = new Hongo(1, "Genérico", "Genérico", "Descripción: Genérica", "Genéricas", EstadoHongo.valueOf("POR_CONFIRMAR"));
      //  assertFalse(manejo.handleCrear(hongo));
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
