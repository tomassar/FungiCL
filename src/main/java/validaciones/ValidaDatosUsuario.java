package validaciones;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class ValidaDatosUsuario {
    public static boolean clavesCoinciden(String clave, String confirmacion){
        return clave.equals (confirmacion);
    }

    public static boolean esVacio(String texto){
        return texto.equals("");
    }

    public static boolean esEmail(String email) {
        return email.contains("@");
    }

    public static byte[] hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //salts are a fundamental principle of password hashing
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec (password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        return hash;
    }

}
