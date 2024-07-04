package us.codecraft.webmagic.utils;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class CharsetUtilsTest {

    @Test
    void testDetectCharset() throws IOException {
        assertNull(CharsetUtils.detectCharset(null, new byte[0]));
    }

}
