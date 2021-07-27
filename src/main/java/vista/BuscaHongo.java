package vista;

import modelo.ComunicaHongoConDatos;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;

public class BuscaHongo extends JDialog {

    private JPanel mainContainer;
    private JButton buscarHongoButton;
    private JButton verListaCompletaButton;
    private JTextField textoBusqueda;

    public BuscaHongo(Menu parent, boolean modal) {
        super (parent, modal);
        setTitle ("Búsqueda de ejemplares");
        mainContainer.setMinimumSize (new Dimension (500, 500));
        mainContainer.setPreferredSize (new Dimension (500, 500));
        mainContainer.setMaximumSize (new Dimension (500, 500));
        parent.setVisible (false);
        setTitle ("Buscar Hongo");
        setLocationRelativeTo (null); // Se abre por default a la mitad de la pantalla.
        this.setContentPane (mainContainer);
        this.pack ();
        this.setDefaultCloseOperation (HIDE_ON_CLOSE);
        this.addWindowListener (new WindowAdapter () {
            public void windowClosing(WindowEvent e) {
                parent.setVisible (true);
            }

        });
        verListaCompletaButton.addActionListener (e -> new ListaHongos (this, ComunicaHongoConDatos.obtenerHongos ()));
        buscarHongoButton.addActionListener (e -> {
            String busqueda = textoBusqueda.getText ();
            new ListaHongos (this, ComunicaHongoConDatos.buscarHongosQueContengan (busqueda));
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
        mainContainer = new JPanel ();
        mainContainer.setLayout (new com.intellij.uiDesigner.core.GridLayoutManager (10, 4, new Insets (50, 50, 50, 50), -1, -1));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer ();
        mainContainer.add (spacer1, new com.intellij.uiDesigner.core.GridConstraints (0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer ();
        mainContainer.add (spacer2, new com.intellij.uiDesigner.core.GridConstraints (0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer ();
        mainContainer.add (spacer3, new com.intellij.uiDesigner.core.GridConstraints (0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer ();
        mainContainer.add (spacer4, new com.intellij.uiDesigner.core.GridConstraints (6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer ();
        mainContainer.add (spacer5, new com.intellij.uiDesigner.core.GridConstraints (4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer ();
        mainContainer.add (spacer6, new com.intellij.uiDesigner.core.GridConstraints (2, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer ();
        mainContainer.add (spacer7, new com.intellij.uiDesigner.core.GridConstraints (5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer ();
        mainContainer.add (spacer8, new com.intellij.uiDesigner.core.GridConstraints (7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer9 = new com.intellij.uiDesigner.core.Spacer ();
        mainContainer.add (spacer9, new com.intellij.uiDesigner.core.GridConstraints (1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer10 = new com.intellij.uiDesigner.core.Spacer ();
        mainContainer.add (spacer10, new com.intellij.uiDesigner.core.GridConstraints (8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer11 = new com.intellij.uiDesigner.core.Spacer ();
        mainContainer.add (spacer11, new com.intellij.uiDesigner.core.GridConstraints (9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        buscarHongoButton = new JButton ();
        buscarHongoButton.setBackground (new Color (-394241));
        buscarHongoButton.setFocusable (false);
        buscarHongoButton.setText ("Buscar hongo");
        mainContainer.add (buscarHongoButton, new com.intellij.uiDesigner.core.GridConstraints (6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textoBusqueda = new JTextField ();
        textoBusqueda.setBackground (new Color (-394241));
        textoBusqueda.setDisabledTextColor (new Color (-10790564));
        textoBusqueda.setEditable (true);
        textoBusqueda.setEnabled (true);
        textoBusqueda.setFocusable (true);
        textoBusqueda.setMargin (new Insets (2, 6, 2, 6));
        textoBusqueda.setName ("");
        textoBusqueda.setText ("Ej: Amanita Musicaria");
        mainContainer.add (textoBusqueda, new com.intellij.uiDesigner.core.GridConstraints (4, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension (150, 30), null, 0, false));
        final JLabel label1 = new JLabel ();
        Font label1Font = this.$$$getFont$$$ ("Microsoft JhengHei UI Light", Font.BOLD, 20, label1.getFont ());
        if (label1Font != null) label1.setFont (label1Font);
        label1.setText ("Fungi Araucanía");
        mainContainer.add (label1, new com.intellij.uiDesigner.core.GridConstraints (3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        verListaCompletaButton = new JButton ();
        verListaCompletaButton.setBackground (new Color (-394241));
        verListaCompletaButton.setFocusable (false);
        verListaCompletaButton.setText ("Ver lista completa");
        mainContainer.add (verListaCompletaButton, new com.intellij.uiDesigner.core.GridConstraints (7, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return mainContainer;
    }

}
