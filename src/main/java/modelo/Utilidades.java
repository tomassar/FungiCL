package modelo;

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

    public static byte[] redimensionar(String inputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);

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

        // writes to output file
        //ImageIO.write(outputImage, formatName, new File(outputImagePath));

        //convertir outputimage a bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(outputImage, formatName, baos );
        byte[] imageInByte = baos.toByteArray();
        return imageInByte;
    }
}
