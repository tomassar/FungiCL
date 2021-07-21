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
import datos.*;
import modelo.ComunicaUsuarioConDatos;
import modelo.Utilidades;

public class VentanaCrearCuenta extends JDialog implements ActionListener {
private JPanel panel1;
private JTextField email;
private JPasswordField contrasena;
private JPasswordField contrasenaConfirmacion;
private JTextField nombreDeUsuario;
private JButton registrarseButton;
private JButton volverButton;
private JDialog thisObject;
private JLabel labelAlerta;
JFrame parent;

public VentanaCrearCuenta(JFrame parent, boolean modal) {
    super (parent, modal);
    setTitle ("Crear cuenta");
    this.thisObject = this;
    this.setSize (800, 600);
    this.setLocationRelativeTo (null);
    this.parent = parent;
    parent.setVisible (false);
    this.setDefaultCloseOperation (HIDE_ON_CLOSE);
    this.setContentPane (panel1);
    this.pack ();

    this.addWindowListener (new WindowAdapter () {
        public void windowClosing(WindowEvent e) {
            parent.setVisible (true);
        }

    });
    volverButton.addActionListener (new ActionListener () {
        @Override
        public void actionPerformed(ActionEvent e) {
            parent.setVisible (true);
            thisObject.setVisible (false);
        }
    });
    registrarseButton.addActionListener (new ActionListener () {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] datos = {nombreDeUsuario.getText (), contrasena.getText (), email.getText (), contrasenaConfirmacion.getText ()};
            String resultadoCreacionCuenta = ComunicaUsuarioConDatos.crearCuenta (datos);
            labelAlerta.setText(resultadoCreacionCuenta);
            Utilidades.setTimeout ( () -> {
                labelAlerta.setText ("");
            },5000);

        }
    });
}

@Override
public void actionPerformed(ActionEvent e) {
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
    panel1.setLayout (new com.intellij.uiDesigner.core.GridLayoutManager (1, 1, new Insets (11, 10, 11, 10), -1, -1));
    panel1.setAlignmentX (1.0f);
    panel1.setAlignmentY (1.0f);
    panel1.setPreferredSize (new Dimension (350, 350));
    final JPanel panel2 = new JPanel ();
    panel2.setLayout (new com.intellij.uiDesigner.core.GridLayoutManager (11, 1, new Insets (11, 0, 0, 0), -1, -1));
    panel1.add (panel2, new com.intellij.uiDesigner.core.GridConstraints (0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    final JLabel label1 = new JLabel ();
    Font label1Font = this.$$$getFont$$$ (null, -1, 24, label1.getFont ());
    if (label1Font != null) label1.setFont (label1Font);
    label1.setText ("Crear Cuenta");
    panel2.add (label1, new com.intellij.uiDesigner.core.GridConstraints (0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    email = new JTextField ();
    panel2.add (email, new com.intellij.uiDesigner.core.GridConstraints (2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension (150, -1), null, 0, false));
    contrasena = new JPasswordField ();
    panel2.add (contrasena, new com.intellij.uiDesigner.core.GridConstraints (6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension (150, -1), null, 0, false));
    contrasenaConfirmacion = new JPasswordField ();
    panel2.add (contrasenaConfirmacion, new com.intellij.uiDesigner.core.GridConstraints (8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension (150, -1), null, 0, false));
    nombreDeUsuario = new JTextField ();
    nombreDeUsuario.setText ("");
    panel2.add (nombreDeUsuario, new com.intellij.uiDesigner.core.GridConstraints (4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension (150, -1), null, 0, false));
    registrarseButton = new JButton ();
    registrarseButton.setText ("Registrarse");
    panel2.add (registrarseButton, new com.intellij.uiDesigner.core.GridConstraints (9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    labelAlerta = new JLabel ();
    labelAlerta.setRequestFocusEnabled (false);
    labelAlerta.setText ("");
    panel2.add (labelAlerta, new com.intellij.uiDesigner.core.GridConstraints (10, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    volverButton = new JButton ();
    volverButton.setText ("Volver");
    panel2.add (volverButton, new com.intellij.uiDesigner.core.GridConstraints (10, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label2 = new JLabel ();
    label2.setRequestFocusEnabled (false);
    label2.setText ("Correo");
    panel2.add (label2, new com.intellij.uiDesigner.core.GridConstraints (1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label3 = new JLabel ();
    label3.setText ("Contraseña");
    panel2.add (label3, new com.intellij.uiDesigner.core.GridConstraints (5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel labelConfirmarContrasena = new JLabel ();
    labelConfirmarContrasena.setText ("Confirmar Contraseña");
    panel2.add (labelConfirmarContrasena, new com.intellij.uiDesigner.core.GridConstraints (7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label5 = new JLabel ();
    label5.setText ("Nombre de Usuario");
    panel2.add (label5, new com.intellij.uiDesigner.core.GridConstraints (3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
