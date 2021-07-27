package utilidades;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

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
        int scaledWidth = 240;
        int scaledHeight = (int)(radio_original* (double) scaledWidth); //Matemática para mantener el radio correcto de la imágen
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
}
