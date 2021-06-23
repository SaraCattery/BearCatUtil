package fun.eojhelper.bearcatutil.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by nichenhao on 2021/06/13.
 */
@SpringBootTest
class SecrecyUtilTest {

    private String plainStr = "Bear and cat are in love.";
    private String cypherStr = "2E4539AD93D65027AB633F78721DAA0C04833ED53CD044C19AC7D3C6FEE74268";

    @Test
    void testEncryptByAES() {
        Assertions.assertEquals(cypherStr, SecrecyUtil.encryptByAES(plainStr));
        Assertions.assertEquals(cypherStr, SecrecyUtil.encryptByAES(plainStr, SecrecyUtil.BASE_KEY));
    }

    @Test
    void testDecryptByAES() {
        Assertions.assertEquals(plainStr, SecrecyUtil.decryptByAES(cypherStr));
        Assertions.assertEquals(plainStr, SecrecyUtil.decryptByAES(cypherStr, SecrecyUtil.BASE_KEY));
    }

}
