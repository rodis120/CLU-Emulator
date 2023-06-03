package pl.rogal.cluemulator;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CluCipher {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    private final SecretKeySpec key;
    private final IvParameterSpec iv;

    public CluCipher(String key, String iv) {

        var decoder = Base64.getDecoder();

        this.key = new SecretKeySpec(decoder.decode(key), "AES");
        this.iv = new IvParameterSpec(decoder.decode(iv));

    }

    public byte[] encrypt(byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public byte[] decrypt(byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

}
