package utilidades;

import modelo.TipoHongo;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

public class Utilidades {
    //Función que ayuda a ejecutar una función lambda con retraso.
    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

    public static byte[] redimensionar(String inputImagePath) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        double radio_original = (double) height/ (double)width;
        int scaledWidth = 220;
        int scaledHeight = (int)(radio_original* (double) scaledWidth); //Matemática para mantener el radio correcto de la imágen
        if(scaledHeight>180){
            //Si la altura es mucha, entonces se toma como punto de partida una altura de 300 y se desprende el ancho dado el radio original
            scaledHeight = 180;
            scaledWidth = (int) ((double) scaledHeight/radio_original);
        }
        // Crea una imágen de salida
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        // Escala la imágen de salida al tamaño deseado
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // Extrae la extensión original del archivo
        String formatName = inputImagePath.substring(inputImagePath
                .lastIndexOf(".") + 1);


        //convertir outputimage a bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(outputImage, formatName, baos );
        return baos.toByteArray();
    }

    public static String crearCadenaCategoriasAPartirDeArrayList(ArrayList<TipoHongo> categoriasArrList){
        String categorias = "";
        if(!categoriasArrList.isEmpty ()){
            for (TipoHongo categoriaEnum:
                    categoriasArrList) {
                categorias += categoriaEnum.toString ()+";";
            }
            categorias = categorias.substring (0, categorias.length () - 1);
        }
        return categorias;
    }

    public static SerialBlob convertirBytesABlob(byte[] bytesImagen) throws SQLException {
        return new javax.sql.rowset.serial.SerialBlob (bytesImagen);
    }

    public static byte[] convertirBlobArregloBytes(Blob blob) throws SQLException {
        byte[] blobAsBytes = null;
        if(blob != null){
            int blobLength = (int) blob.length();
            blobAsBytes = blob.getBytes(1, blobLength);
            //Liberar el blob para liberar memoria
            blob.free();
        }
        return blobAsBytes;
    }

    public static ArrayList<TipoHongo> convertirStringArrListCategorias(String categorias) {
        ArrayList<TipoHongo> categoriasArrList = new ArrayList<> ();
        if(!categorias.equals ("")){
            String[] arrCategorias = categorias.split (";");
            for (String categoria: arrCategorias) {
                categoriasArrList.add (TipoHongo.valueOf (categoria.toUpperCase ()));
            }
        }
        return categoriasArrList;
    }
}
