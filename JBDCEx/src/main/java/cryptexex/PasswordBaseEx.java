package cryptexex;

import com.mysql.cj.util.Base64Decoder;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordBaseEx {
    static final String ALGO = "FPTaptechMD5AndDes";

    public static void main(String[] args) throws Exception {
        long curt = System.currentTimeMillis();
        String password = "jhjsadjsad";
        SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGO);
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKey secretKey = skf.generateSecret(keySpec);
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salte = random.generateSeed(8);
        PBEParameterSpec param = new PBEParameterSpec(salte, 8);
        Cipher cipher = Cipher.getInstance(ALGO);

        //Encrypt
        cipher.init(cipher.ENCRYPT_MODE, secretKey, param);
        String clear = "Encrypt for java";
        byte[] encrypted = cipher.doFinal(clear.getBytes());
        Base64 encoder = new Base64Decoder();
    }
}
