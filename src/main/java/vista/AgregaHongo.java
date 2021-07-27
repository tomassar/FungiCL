package vista;

import modelo.ComunicaHongoConDatos;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class AgregaHongo extends JDialog {
    private JPanel panel1;
    private JTextField textNombre;
    private JTextField textGeo;
    private JTextField textDesc;
    private JButton agregarButton;
    private JButton elegirImágenButton;
    private JCheckBox venenosoCheckBox;
    private JCheckBox comestibleCheckBox;
    private JCheckBox medicinalCheckBox;
    private JCheckBox alucinógenoCheckBox;
    private File imagen = null; //imágen del hongo

    public AgregaHongo(Menu parent, boolean modal) {
        super (parent, modal);
        this.setMinimumSize (new Dimension (500, 500));
        this.setPreferredSize (new Dimension (500, 500));
        this.setMaximumSize (new Dimension (500, 500));
        parent.setVisible (false);
        setTitle ("Agregar Hongo");
        setLocationRelativeTo (null); // Se abre por default a la mitad de la pantalla.
        this.setContentPane (panel1);
        this.pack ();
        setDefaultCloseOperation (HIDE_ON_CLOSE);
        this.addWindowListener (new WindowAdapter () {
            public void windowClosing(WindowEvent e) {
                parent.setVisible (true);
            }

        });
        agregarButton.addActionListener (e -> {
            String nombre = textNombre.getText ();
            String geolocalizacion = textGeo.getText ();
            String descripcion = textDesc.getText ();
            var categorias = new ArrayList ();
            if (venenosoCheckBox.isSelected ()) {
                categorias.add ("VENENOSO");
            }
            if (comestibleCheckBox.isSelected ()) {
                categorias.add ("COMESTIBLE");
            }
            if (medicinalCheckBox.isSelected ()) {
                categorias.add ("MEDICINAL");
            }
            if (alucinógenoCheckBox.isSelected ()) {
                categorias.add ("ALUCINOGENO");
            }
            if (ComunicaHongoConDatos.crearHongo (nombre, geolocalizacion, descripcion, categorias, imagen)) {
                JOptionPane.showMessageDialog (panel1, "Hongo satisfactoriamente creado");
            } else {
                JOptionPane.showMessageDialog (panel1, "El hongo ya existe en nuestra base de datos");
            }
        });
        elegirImágenButton.addActionListener (e -> {
            // Se crea la un JFileChooser para abrir el explorador de archivos
            JFileChooser jFileChooser = new JFileChooser ();
            jFileChooser.setDialogTitle ("Seleccione el archivo");
            // Si se selecciona un archivo desde el JFileChooser se ejecuta la acción
            if (jFileChooser.showOpenDialog (null) == JFileChooser.APPROVE_OPTION) {
                // Se obtiene el archivo seleccionado
                imagen = jFileChooser.getSelectedFile ();
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$ ();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel ();
        panel1.setLayout (new com.intellij.uiDesigner.core.GridLayoutManager (4, 1, new Insets (10, 10, 10, 10), -1, -1));
        final JPanel panel2 = new JPanel ();
        panel2.setLayout (new com.intellij.uiDesigner.core.GridLayoutManager (4, 5, new Insets (0, 0, 0, 0), -1, -1));
        panel1.add (panel2, new com.intellij.uiDesigner.core.GridConstraints (1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel ();
        label1.setText ("Nombre");
        panel2.add (label1, new com.intellij.uiDesigner.core.GridConstraints (0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel ();
        label2.setText ("Geolocalización");
        panel2.add (label2, new com.intellij.uiDesigner.core.GridConstraints (1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel ();
        label3.setText ("Descripción");
        panel2.add (label3, new com.intellij.uiDesigner.core.GridConstraints (2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel ();
        label4.setText ("Categorías");
        panel2.add (label4, new com.intellij.uiDesigner.core.GridConstraints (3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textNombre = new JTextField ();
        panel2.add (textNombre, new com.intellij.uiDesigner.core.GridConstraints (0, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension (150, -1), null, 0, false));
        textGeo = new JTextField ();
        panel2.add (textGeo, new com.intellij.uiDesigner.core.GridConstraints (1, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension (150, -1), null, 0, false));
        textDesc = new JTextField ();
        panel2.add (textDesc, new com.intellij.uiDesigner.core.GridConstraints (2, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension (150, -1), null, 0, false));
        venenosoCheckBox = new JCheckBox ();
        venenosoCheckBox.setText ("Venenoso");
        panel2.add (venenosoCheckBox, new com.intellij.uiDesigner.core.GridConstraints (3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comestibleCheckBox = new JCheckBox ();
        comestibleCheckBox.setText ("Comestible");
        panel2.add (comestibleCheckBox, new com.intellij.uiDesigner.core.GridConstraints (3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        medicinalCheckBox = new JCheckBox ();
        medicinalCheckBox.setText ("Medicinal");
        panel2.add (medicinalCheckBox, new com.intellij.uiDesigner.core.GridConstraints (3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        alucinógenoCheckBox = new JCheckBox ();
        alucinógenoCheckBox.setText ("Alucinógeno");
        panel2.add (alucinógenoCheckBox, new com.intellij.uiDesigner.core.GridConstraints (3, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel ();
        Font label5Font = this.$$$getFont$$$ (null, -1, 18, label5.getFont ());
        if (label5Font != null) label5.setFont (label5Font);
        label5.setText ("Agregar Hongo");
        panel1.add (label5, new com.intellij.uiDesigner.core.GridConstraints (0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        agregarButton = new JButton ();
        agregarButton.setText ("Agregar");
        panel1.add (agregarButton, new com.intellij.uiDesigner.core.GridConstraints (3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        elegirImágenButton = new JButton ();
        elegirImágenButton.setText ("Elegir Imágen");
        panel1.add (elegirImágenButton, new com.intellij.uiDesigner.core.GridConstraints (2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName ();
        } else {
            Font testFont = new Font (fontName, Font.PLAIN, 10);
            if (testFont.canDisplay ('a') && testFont.canDisplay ('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName ();
            }
        }
        Font font = new Font (resultName, style >= 0 ? style : currentFont.getStyle (), size >= 0 ? size : currentFont.getSize ());
        boolean isMac = System.getProperty ("os.name", "").toLowerCase (Locale.ENGLISH).startsWith ("mac");
        Font fontWithFallback = isMac ? new Font (font.getFamily (), font.getStyle (), font.getSize ()) : new StyleContext ().getFont (font.getFamily (), font.getStyle (), font.getSize ());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource (fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
