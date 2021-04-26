import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static Main main;
    static String ruta = "C:\\Users\\java\\IdeaProjects\\FungiCL\\src\\main\\resources\\hongos.csv";
    @BeforeAll
    static void beforeAll(){
        main = new Main();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void escribirHongoEnBaseDeDatos(){
        String nuevoHongo = "hongoprueba;geolocalizacionprueba";
        Main.escribirHongoEnBaseDeDatos(nuevoHongo);
        ArrayList<String> lineas = Main.leerArchivo(ruta);
        boolean existeHongo = lineas.get(lineas.size()-1).equals("hongoprueba;geolocalizacionprueba");
        assertTrue(existeHongo);
    }

    @Test
    void crearHongo() {
        String[] infoHongo = {"nombre","geolocalizacion"};
        Hongo hongoPrueba =new Hongo("nombre","geolocalizacion");
        Hongo hongoReal = Main.crearHongo(infoHongo);
        assertTrue(hongoPrueba.equals(hongoReal));
    }

    @Test
    void validarArchivo() {
        crearArchivo("texto.txt");
        assertTrue(Main.validarArchivo("texto.txt"));
        borrarArchivo("texto.txt");
    }

    @Test
    void existeHongo() {
        assertFalse(Main.existeHongo("inexistente"));
    }

    @Test
    void leerTexto() {
        ByteArrayInputStream steam = new ByteArrayInputStream("s".getBytes());
        System.setIn(steam);
        assertEquals("s", Main.leerTexto());
    }

    // Los uso para probar los métodos de Main
    void crearArchivo(String ruta){
        try {
            File archivo = new File(ruta);
            archivo.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    void borrarArchivo(String ruta){
        File archivo = new File(ruta);
        if (archivo.delete()) {
            System.out.println("Deleted the file: " + archivo.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
}