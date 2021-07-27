package vista;

import modelo.*;
import utilidades.Utilidades;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class IniciarSesion extends JFrame implements ActionListener {
    private JPanel mainContainer;
    private JButton registrarseButton;
    private JTextField textoUsuario;
    private JTextField textoContraseña;
    private JButton iniciarSesiónButton;
    private JLabel errorLabel;
    private JLabel imagen;

    public IniciarSesion() {

        // Tamaño de la ventana
        this.setMinimumSize (new Dimension (500, 500));
        this.setPreferredSize (new Dimension (500, 500));
        this.setMaximumSize (new Dimension (500, 500));

        setTitle ("Fungi Araucanía");
        setLocationRelativeTo (null); // Se abre por default a la mitad de la pantalla.
        this.add (mainContainer);
        setDefaultCloseOperation (EXIT_ON_CLOSE);
        setVisible (true);


        iniciarSesiónButton.addActionListener (e -> {
            String contrasenaTexto = textoContraseña.getText ();
            String nombreDeUsuarioText = textoUsuario.getText ();

            String mensaje = ComunicaUsuarioConDatos.inicarSesion (nombreDeUsuarioText, contrasenaTexto);
            if (mensaje.equals ("Llene los campos vacíos.") || mensaje.equals ("Contraseña incorrecta") || mensaje.equals ("Nombre de usuario o email no existe.")) {
                errorLabel.setText (mensaje);
                Utilidades.setTimeout (() -> errorLabel.setText (""), 5000);
            } else {
                Menu menu = new Menu (this, true, nombreDeUsuarioText);
                menu.setVisible (true);
                errorLabel.setText ("");
            }

        });
        registrarseButton.addActionListener (e -> {
            VentanaCrearCuenta ventanaCrearCuenta = new VentanaCrearCuenta (this, true);
            ventanaCrearCuenta.setVisible (true);
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
        mainContainer = new JPanel ();
        mainContainer.setLayout (new com.intellij.uiDesigner.core.GridLayoutManager (8, 2, new Insets (50, 50, 50, 50), -1, -1));
        mainContainer.setEnabled (true);
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer ();
        mainContainer.add (spacer1, new com.intellij.uiDesigner.core.GridConstraints (0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer ();
        mainContainer.add (spacer2, new com.intellij.uiDesigner.core.GridConstraints (4, 0, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel ();
        panel1.setLayout (new com.intellij.uiDesigner.core.GridLayoutManager (2, 2, new Insets (0, 0, 0, 0), -1, -1));
        mainContainer.add (panel1, new com.intellij.uiDesigner.core.GridConstraints (1, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textoUsuario = new JTextField ();
        textoUsuario.setBackground (new Color (-394241));
        panel1.add (textoUsuario, new com.intellij.uiDesigner.core.GridConstraints (0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension (150, -1), null, 0, false));
        final JLabel label1 = new JLabel ();
        label1.setText ("Usuario:");
        panel1.add (label1, new com.intellij.uiDesigner.core.GridConstraints (0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel ();
        label2.setText ("Contraseña:");
        panel1.add (label2, new com.intellij.uiDesigner.core.GridConstraints (1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textoContraseña = new JPasswordField ();
        textoContraseña.setBackground (new Color (-394759));
        panel1.add (textoContraseña, new com.intellij.uiDesigner.core.GridConstraints (1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension (150, -1), null, 0, false));
        final JPanel panel2 = new JPanel ();
        panel2.setLayout (new com.intellij.uiDesigner.core.GridLayoutManager (1, 1, new Insets (0, 0, 0, 0), -1, -1));
        mainContainer.add (panel2, new com.intellij.uiDesigner.core.GridConstraints (4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        errorLabel = new JLabel ();
        errorLabel.setBackground (new Color (-4519424));
        errorLabel.setEnabled (true);
        errorLabel.setForeground (new Color (-4521977));
        errorLabel.setText ("");
        panel2.add (errorLabel, new com.intellij.uiDesigner.core.GridConstraints (0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        iniciarSesiónButton = new JButton ();
        iniciarSesiónButton.setBackground (new Color (-1));
        iniciarSesiónButton.setFocusable (false);
        iniciarSesiónButton.setForeground (new Color (-13817298));
        iniciarSesiónButton.setText ("Iniciar Sesión");
        mainContainer.add (iniciarSesiónButton, new com.intellij.uiDesigner.core.GridConstraints (3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        registrarseButton = new JButton ();
        registrarseButton.setBackground (new Color (-1));
        registrarseButton.setFocusable (false);
        registrarseButton.setForeground (new Color (-13817297));
        registrarseButton.setText ("Registrarse");
        mainContainer.add (registrarseButton, new com.intellij.uiDesigner.core.GridConstraints (6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel ();
        Font label3Font = this.$$$getFont$$$ ("Arial", -1, -1, label3.getFont ());
        if (label3Font != null) label3.setFont (label3Font);
        label3.setText ("¿No tiene cuenta?");
        mainContainer.add (label3, new com.intellij.uiDesigner.core.GridConstraints (5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer ();
        mainContainer.add (spacer3, new com.intellij.uiDesigner.core.GridConstraints (7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel ();
        panel3.setLayout (new com.intellij.uiDesigner.core.GridLayoutManager (1, 1, new Insets (0, 0, 0, 0), -1, -1));
        mainContainer.add (panel3, new com.intellij.uiDesigner.core.GridConstraints (0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        imagen = new JLabel ();
        imagen.setIcon (new ImageIcon (getClass ().getResource ("/unnamed.png")));
        imagen.setOpaque (false);
        imagen.setText ("");
        panel3.add (imagen, new com.intellij.uiDesigner.core.GridConstraints (0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
