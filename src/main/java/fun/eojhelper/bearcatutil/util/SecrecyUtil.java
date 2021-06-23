package fun.eojhelper.bearcatutil.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Created by nichenhao on 2021/06/13.
 */
public class SecrecyUtil {

    public static final String BASE_KEY = "BearCatAreInLove";

    private static final Logger LOGGER = LoggerFactory.getLogger(SecrecyUtil.class);
    private static final String AES_ALGORITHM = "AES";
    private static final IvParameterSpec IV_PARAMETER_SPEC = new IvParameterSpec("0000000000000000".getBytes());

    public static String encryptByAES(String plainStr) {
        return encryptByAES(plainStr, BASE_KEY);
    }

    public static String encryptByAES(String plainStr, String key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), AES_ALGORITHM), IV_PARAMETER_SPEC);
            return convertByteToHexString(cipher.doFinal(Arrays.copyOf(plainStr.getBytes(), 16 * (plainStr.getBytes().length / 16 + 1))));
        } catch (Exception e) {
            LOGGER.warn("AES加密失败, plainStr:{}, e:{}", plainStr, ExceptionUtil.getCause(e));
            return "";
        }
    }

    public static String decryptByAES(String cypherStr) {
        return decryptByAES(cypherStr, BASE_KEY);
    }

    public static String decryptByAES(String cypherStr, String key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), AES_ALGORITHM), IV_PARAMETER_SPEC);
            return new String(cipher.doFinal(convertHexStringToByte(cypherStr.toLowerCase())), StandardCharsets.UTF_8).trim();
        } catch (Exception e) {
            LOGGER.warn("AES解密失败, cypherStr:{}, e:{}", cypherStr, ExceptionUtil.getCause(e));
            return "";
        }
    }

    private static String convertByteToHexString(byte[] data) {
        StringBuffer result = new StringBuffer();
        String hexString = "";
        for (byte b : data) {
            hexString = Integer.toHexString(b & 255);
            result.append(hexString.length() == 1 ? "0" + hexString : hexString);
        }
        return result.toString().toUpperCase();
    }

    private static byte[] convertHexStringToByte(String data) {
        int length = data.length() / 2;
        byte [] result = new byte[length];
        for (int i = 0; i < length; i++) {
            int first = Integer.parseInt(data.substring(i * 2, i * 2 + 1), 16);
            int second = Integer.parseInt(data.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (first * 16 + second);
        }
        return result;
    }

}
