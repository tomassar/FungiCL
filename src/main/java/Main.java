import GUI.IniciarSesion;
import GUI.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static HashMap<String, Hongo> hashMapHongos = new HashMap<>();
    static String ruta = "C:\\Users\\java\\IdeaProjects\\FungiCL\\src\\main\\resources\\hongos.csv";

    public static void main(String[] args) {
        IniciarSesion ventana = new IniciarSesion();
        Menu ventanaMenu = new Menu();
    }

    public static void iniciar() {
        while (true) {
            if (validarArchivo(ruta)) {
                leerArchivo(ruta);
                mostrarMenu();
                String opcion = leerTexto();
                manejoDeOpciones(opcion);
            } else {
                System.out.println("Archivo no existe");
                break;
            }
        }
    }

    public static void mostrarMenu() {
        System.out.println("[1] Mostrar todos los hongos");
        System.out.println("[2] Mostrar hongo por nombre");
        System.out.println("[3] Agregar hongo a la base de datos");
        System.out.println("[0] Salir");
    }

    public static void manejoDeOpciones(String opcion) {
        switch (opcion) {
            case "1":
                mostrarHongos();
                break;
            case "2":
                iniciarBusquedaHongo();
                break;
            case "3":
                iniciarAgregarHongo();
            case "0":
                break; // no sé como salir del switch
        }
    }

    private static void iniciarAgregarHongo() {
        String nombreHongo = preguntarNombreHongo();
        nombreHongo = arreglarNombreHongo(nombreHongo);
        if (!existeHongo(nombreHongo)) {
            agregarHongo(nombreHongo);
        } else {
            System.out.println("El hongo ya existe");
        }
    }

    private static void iniciarBusquedaHongo() {
        String nombreHongo = preguntarNombreHongo();
        nombreHongo = arreglarNombreHongo(nombreHongo);
        validarExistenciaHongo(nombreHongo);
    }

    public static ArrayList<String> leerArchivo(String ruta) {
        ArrayList<String> lineas = new ArrayList<>();
        String cadena;
        FileReader f = null;
        try {
            f = new FileReader(ruta);
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                lineas.add(cadena);
            }
            b.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (f != null)
                    f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        crearHashMapHongos(lineas);
        return lineas;
    }

    public static void escribirHongoEnBaseDeDatos(String linea) {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            File archivo = new File(ruta);
            // Si el archivo no existe, se crea
            fw = new FileWriter(archivo.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write("\n" + linea);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void crearHashMapHongos(ArrayList<String> contenido) {
        for (String s : contenido) {
            String[] arrHongo = s.split(";");
            Hongo hongo = crearHongo(arrHongo);
            String nombreHongo = arreglarNombreHongo(hongo.getNombre());
            hashMapHongos.put(nombreHongo, hongo);
        }
    }

    public static Hongo crearHongo(String[] arrHongo) {
        return new Hongo(arrHongo[0], arrHongo[1]);
    }

    private static String arreglarNombreHongo(String nombre) { //cambiar nombre del método
        nombre = nombre.toLowerCase();
        nombre = nombre.replace(" ", "");
        return nombre;
    }

    private static Hongo retornarHongoPorNombre(String nombre) {
        String nombreFormateado = arreglarNombreHongo(nombre);
        return hashMapHongos.get(nombreFormateado);
    }

    private static void mostrarHongo(String nombreHongo) {
        Hongo hongo = retornarHongoPorNombre(nombreHongo);
        System.out.println("Nombre: " + hongo.getNombre() + ", Geolocalización: " + hongo.getGeolocalizacion());
    }

    private static String preguntarNombreHongo() {
        System.out.println("Ingrese el nombre del hongo");
        return leerTexto();
    }

    public static boolean validarArchivo(String ruta) {
        File file = new File(ruta);
        return file.isFile();
    }

    public static void validarExistenciaHongo(String nombreHongo) {
        if (!existeHongo(nombreHongo)) {
            System.out.println("El hongo ingresado no existe o no está en nuestra base de datos");
        } else {
            mostrarHongo(nombreHongo);
        }
    }

    public static boolean existeHongo(String nombreHongo) {
        return hashMapHongos.containsKey(nombreHongo);
    }

    public static void mostrarHongos() {
        for (Map.Entry<String, Hongo> entry : hashMapHongos.entrySet()) {
            String nombre = entry.getKey();
            Hongo hongo = entry.getValue();
            System.out.print("Nombre: " + nombre + ", Geolocalización: " + hongo.getGeolocalizacion() + "\n");
        }
    }

    public static void agregarHongo(String nombreHongo) {
        String[] caracteristicas = preguntarCaracteristicasHongo(nombreHongo);
        String linea = caracteristicas[0] + ";" + caracteristicas[1];
        escribirHongoEnBaseDeDatos(linea);
        iniciar();
    }

    private static String[] preguntarCaracteristicasHongo(String nombreHongo) {
        String[] caracteristicas = new String[2];
        System.out.println("Ingrese la geolocalización del hongo " + nombreHongo);
        caracteristicas[0] = nombreHongo;
        caracteristicas[1] = leerTexto();
        return caracteristicas;
    }

    public static String leerTexto() {
        Scanner teclado = new Scanner(System.in);
        String texto = "";
        try {
            texto = teclado.nextLine();
        } catch (Exception e) {
            System.out.println("Ingrese un texto válido.");
            teclado.next();
        }
        return texto;
    }
}