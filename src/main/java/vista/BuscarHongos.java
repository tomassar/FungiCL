package vista;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;

public class BuscarHongos extends JDialog {
    private JPanel panel1;
    private JTextField textField1;
    private JButton buscarHongoButton;
    private JButton verListaCompletaButton;

    public BuscarHongos(Menu parent, boolean modal) {
        // Tamaño de la ventana
        super (parent, modal);
        this.setMinimumSize (new Dimension (500, 500));
        this.setPreferredSize (new Dimension (500, 500));
        this.setMaximumSize (new Dimension (500, 500));
        parent.setVisible (false);
        setTitle ("Buscar Hongo");
        setLocationRelativeTo (null); // Se abre por default a la mitad de la pantalla.
        this.setContentPane (panel1);
        this.pack ();
        setDefaultCloseOperation (HIDE_ON_CLOSE);
        BuscarHongos thisClass = this;
        this.addWindowListener (new WindowAdapter () {
            public void windowClosing(WindowEvent e) {
                parent.setVisible (true);
            }

        });
        verListaCompletaButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListaHongos listaHongos = new ListaHongos (thisClass, true);
                listaHongos.setVisible (true);
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
        panel1.setLayout (new com.intellij.uiDesigner.core.GridLayoutManager (1, 1, new Insets (10, 10, 10, 10), -1, -1));
        final JPanel panel2 = new JPanel ();
        panel2.setLayout (new com.intellij.uiDesigner.core.GridLayoutManager (4, 1, new Insets (0, 0, 0, 0), -1, -1));
        panel1.add (panel2, new com.intellij.uiDesigner.core.GridConstraints (0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel ();
        label1.setEnabled (false);
        Font label1Font = this.$$$getFont$$$ (null, -1, 36, label1.getFont ());
        if (label1Font != null) label1.setFont (label1Font);
        label1.setText ("FungiAraucania");
        panel2.add (label1, new com.intellij.uiDesigner.core.GridConstraints (0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField ();
        panel2.add (textField1, new com.intellij.uiDesigner.core.GridConstraints (1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension (150, -1), null, 0, false));
        buscarHongoButton = new JButton ();
        buscarHongoButton.setText ("Buscar Hongo");
        panel2.add (buscarHongoButton, new com.intellij.uiDesigner.core.GridConstraints (2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        verListaCompletaButton = new JButton ();
        verListaCompletaButton.setText ("Ver Lista Completa ");
        panel2.add (verListaCompletaButton, new com.intellij.uiDesigner.core.GridConstraints (3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
