package vista;

import modelo.ComunicaHongoConDatos;
import modelo.EstadoHongo;
import modelo.Hongo;
import modelo.TipoHongo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListaHongos {
    ArrayList<Hongo> hongos;
    public ListaHongos(BuscaHongo parent, ArrayList<Hongo> hongos){
        this.hongos = hongos;

        //Se crea un Frame prncipal
        JFrame jFrame = new JFrame("Lista de Hongos");
        jFrame.setSize(500, 500);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        // Navegación de ventanas
        parent.setVisible (false);
        ListaHongos listaHongo = this;
        jFrame.addWindowListener (new WindowAdapter () {
            public void windowClosing(WindowEvent e) {
                jFrame.setVisible (false);
                parent.setVisible (true);
            }

        });

        //Se crea un panel en el que se mostrarán los archivos que se enviaron al servidor
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        // Se pone un scroll vertical en la pantalla para poder navegar hacia arriba o abajo en el panel de los archivos que se subieron al servidor
        JScrollPane jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //Título de la ventana
        JLabel jlTitle = new JLabel("Hongos:");
        jlTitle.setFont(new Font ("Arial", Font.BOLD, 25));
        jlTitle.setBorder(new EmptyBorder (20,0,10,0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Se añade el contenido al Frame principal y se hace visible
        jFrame.add(jlTitle);
        jFrame.add(jScrollPane);
        jFrame.setVisible(true);

        for (Hongo hongo:
             hongos) {
            String nombreHongo = hongo.getNombre ();
            int idHongo = hongo.getId ();

            JPanel jpFileRow = new JPanel();
            jpFileRow.setLayout(new BoxLayout(jpFileRow, BoxLayout.X_AXIS));
            JLabel jlFileName = new JLabel(nombreHongo);
            jlFileName.setBorder(new EmptyBorder(10,0, 10,0));

            // Se establece que el nombre sea el idHongo, de tal forma que se pueda obtener el hongo correcto desde el panel.
            jpFileRow.setName((String.valueOf(idHongo)));
            jpFileRow.addMouseListener(getMyMouseListener());

            jpFileRow.add(jlFileName);
            jPanel.add(jpFileRow);
            jFrame.validate();
        }
    }

    /**
     * Cuando se hace click en el nombre del hongo, se abre una ventana nueva (método createFrame) para mostrar información del hongo
     * @return A mouselistener that is used by the jpanel.
     */
    public static MouseListener getMyMouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Obtener l fuente del click que es el panel
                JPanel jPanel = (JPanel) e.getSource();
                // Obtener id del hongo
                int hongoId = Integer.parseInt(jPanel.getName());
                // Loop through the file storage and see which file is the selected one.
                for (Hongo hongo : ComunicaHongoConDatos.obtenerHongos ()) {
                    if (hongo.getId() == hongoId) {
                        JFrame jfPreview = createFrame(hongo);
                        jfPreview.setVisible(true);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    //Se crea la ventana que sirve para visualizar la información del hongo seleccionado
    public static JFrame createFrame(Hongo hongo) {
        String nombre = hongo.getNombre ();
        String descripcion = hongo.getDescripcion ();
        String geolocalizacion = hongo.getGeolocalizacion ();
        ArrayList<TipoHongo> categorias = hongo.getCategorias ();
        byte[] imagen = hongo.getImagen ();
        EstadoHongo estaConfirmado = hongo.getEstado ();

        JFrame jFrame = new JFrame(nombre);
        jFrame.setSize(500, 500);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));


        JLabel jlPrompt = new JLabel(nombre);
        jlPrompt.setFont(new Font("Arial", Font.BOLD, 20));
        jlPrompt.setBorder(new EmptyBorder(20,0,10,0));
        jlPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Se crea un Label para mostrar la imágen del hongo
        JLabel jlImageContent = new JLabel();
        jlImageContent.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Panel que mantiene el label para mostrar la descripción
        JPanel jpImageContent = new JPanel();
        jpImageContent.add(jlImageContent);

        // Para mostrar la imágen
        jlImageContent.setIcon(new ImageIcon(imagen));


        // Se crea un Label para mostrar la descripción del hongo
        JLabel jlFileContent = new JLabel();
        jlFileContent.setAlignmentX(Component.LEFT_ALIGNMENT);
        // Panel que mantiene el label para mostrar la descripción
        JPanel jpFileContent = new JPanel();
        jpFileContent.add(jlFileContent);
        // Se usa HTML para que el párrafo pueda verse completo.
        jlFileContent.setText("<html><p style='width:270px'>" + new String(descripcion) + "</p></html>");



        // Se crea un Label para mostrar la geolocalización
        JLabel jlGeolocalizacion = new JLabel();
        jlGeolocalizacion.setAlignmentX(Component.LEFT_ALIGNMENT);
        // Panel que mantiene el label para mostrar el contenido del archivo.
        JPanel jpGeolocalizacion = new JPanel();
        jpGeolocalizacion.add(jlGeolocalizacion);

        jlGeolocalizacion.setText ("<html><p style='width:270px'>Geolocalización: "+geolocalizacion+"</p></html>");

        // Se crea un Label para mostrar las categorias
        JLabel jlCategorias = new JLabel();
        jlCategorias.setAlignmentX(Component.LEFT_ALIGNMENT);
        // Panel que mantiene el label para mostrar el contenido del archivo.
        JPanel jpCategorias = new JPanel();
        jpCategorias.add(jlCategorias);

        if(categorias.size() != 0){
            String strCategorias = "";
            for (TipoHongo tipoHongo:
                 categorias) {
                strCategorias += tipoHongo.toString ()+", ";
            }
            //Para eliminar la última coma
            strCategorias = strCategorias.substring (0, strCategorias.length () - 2);
            jlCategorias.setText ("<html><p style='width:270px'>Categorías: "+strCategorias+"</p></html>");
        }

        // Se crea un Label para mostrar si está confirmado o no
        JLabel jlEstaConfirmado = new JLabel();
        jlEstaConfirmado.setAlignmentX(Component.LEFT_ALIGNMENT);
        // Panel que mantiene el label para mostrar el contenido del archivo.
        JPanel jpEstaConfirmado = new JPanel();
        jpEstaConfirmado.add(jlEstaConfirmado);

        if(estaConfirmado.toString ().equals ("POR_CONFIRMAR")){
            jlEstaConfirmado.setText ("<html><p style='width:270px;color:red'>Este hongo no está confirmado, por favor tenga discreción.</p></html>");
        }else{
            jlEstaConfirmado.setText ("<html><p style='width:270px;color:green'> Este hongo está confirmado, puede confiar en esta información.</p></html>");
        }


        jPanel.add(jlPrompt);
        jPanel.add(jpImageContent);
        jPanel.add(jpFileContent);
        jPanel.add(jpGeolocalizacion);
        jPanel.add(jpCategorias);
        jPanel.add(jpEstaConfirmado);

        jFrame.add(jPanel);
        jFrame.setLocationRelativeTo(null);

        return jFrame;

    }
}
